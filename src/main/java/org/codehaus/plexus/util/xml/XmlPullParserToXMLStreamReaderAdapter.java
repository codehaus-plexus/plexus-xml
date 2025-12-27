package org.codehaus.plexus.util.xml;

/*
 * Copyright The Codehaus Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Adapter that wraps an XmlPullParser and implements the XMLStreamReader interface.
 * This allows using XmlPullParser with APIs that expect XMLStreamReader.
 */
class XmlPullParserToXMLStreamReaderAdapter implements XMLStreamReader {
    private final XmlPullParser parser;
    private int eventType;

    XmlPullParserToXMLStreamReaderAdapter(XmlPullParser parser) throws XMLStreamException {
        this.parser = parser;
        try {
            this.eventType = parser.getEventType();
        } catch (XmlPullParserException e) {
            throw new XMLStreamException(e);
        }
    }

    @Override
    public int next() throws XMLStreamException {
        try {
            eventType = parser.next();
            return convertEventType(eventType);
        } catch (Exception e) {
            throw new XMLStreamException(e);
        }
    }

    @Override
    public boolean hasNext() throws XMLStreamException {
        return eventType != XmlPullParser.END_DOCUMENT;
    }

    @Override
    public int getEventType() {
        return convertEventType(eventType);
    }

    @Override
    public String getElementText() throws XMLStreamException {
        try {
            return parser.nextText();
        } catch (Exception e) {
            throw new XMLStreamException(e);
        }
    }

    @Override
    public String getLocalName() {
        return parser.getName();
    }

    @Override
    public String getNamespaceURI() {
        return parser.getNamespace();
    }

    @Override
    public String getPrefix() {
        return parser.getPrefix();
    }

    @Override
    public String getAttributeValue(String namespaceURI, String localName) {
        return parser.getAttributeValue(namespaceURI, localName);
    }

    @Override
    public int getAttributeCount() {
        return parser.getAttributeCount();
    }

    @Override
    public String getAttributeNamespace(int index) {
        return parser.getAttributeNamespace(index);
    }

    @Override
    public String getAttributeLocalName(int index) {
        return parser.getAttributeName(index);
    }

    @Override
    public String getAttributePrefix(int index) {
        return parser.getAttributePrefix(index);
    }

    @Override
    public String getAttributeValue(int index) {
        return parser.getAttributeValue(index);
    }

    @Override
    public String getText() {
        return parser.getText();
    }

    @Override
    public Location getLocation() {
        return new Location() {
            @Override
            public int getLineNumber() {
                return parser.getLineNumber();
            }

            @Override
            public int getColumnNumber() {
                return parser.getColumnNumber();
            }

            @Override
            public int getCharacterOffset() {
                return -1;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        };
    }

    private int convertEventType(int pullEventType) {
        switch (pullEventType) {
            case XmlPullParser.START_DOCUMENT:
                return START_DOCUMENT;
            case XmlPullParser.END_DOCUMENT:
                return END_DOCUMENT;
            case XmlPullParser.START_TAG:
                return START_ELEMENT;
            case XmlPullParser.END_TAG:
                return END_ELEMENT;
            case XmlPullParser.TEXT:
                return CHARACTERS;
            case XmlPullParser.CDSECT:
                return CDATA;
            case XmlPullParser.ENTITY_REF:
                return ENTITY_REFERENCE;
            case XmlPullParser.IGNORABLE_WHITESPACE:
                return SPACE;
            case XmlPullParser.PROCESSING_INSTRUCTION:
                return PROCESSING_INSTRUCTION;
            case XmlPullParser.COMMENT:
                return COMMENT;
            case XmlPullParser.DOCDECL:
                return DTD;
            default:
                return 0;
        }
    }

    // Unsupported methods that are not needed for basic XML reading
    @Override
    public QName getName() {
        return new QName(getNamespaceURI(), getLocalName(), getPrefix());
    }

    @Override
    public boolean isStartElement() {
        return getEventType() == START_ELEMENT;
    }

    @Override
    public boolean isEndElement() {
        return getEventType() == END_ELEMENT;
    }

    @Override
    public boolean isCharacters() {
        return getEventType() == CHARACTERS;
    }

    @Override
    public boolean isWhiteSpace() {
        return getEventType() == SPACE;
    }

    @Override
    public boolean hasName() {
        return getEventType() == START_ELEMENT || getEventType() == END_ELEMENT;
    }

    @Override
    public boolean hasText() {
        int type = getEventType();
        return type == CHARACTERS || type == CDATA || type == SPACE || type == ENTITY_REFERENCE;
    }

    @Override
    public QName getAttributeName(int index) {
        return new QName(getAttributeNamespace(index), getAttributeLocalName(index), getAttributePrefix(index));
    }

    @Override
    public String getAttributeType(int index) {
        return parser.getAttributeType(index);
    }

    @Override
    public boolean isAttributeSpecified(int index) {
        return true;
    }

    @Override
    public int getNamespaceCount() {
        return 0;
    }

    @Override
    public String getNamespacePrefix(int index) {
        return null;
    }

    @Override
    public String getNamespaceURI(int index) {
        return null;
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return null;
    }

    @Override
    public String getNamespaceURI(String prefix) {
        return null;
    }

    @Override
    public int getTextLength() {
        return getText() != null ? getText().length() : 0;
    }

    @Override
    public int getTextStart() {
        return 0;
    }

    @Override
    public char[] getTextCharacters() {
        String text = getText();
        return text != null ? text.toCharArray() : new char[0];
    }

    @Override
    public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) {
        char[] source = getTextCharacters();
        int copied = Math.min(length, source.length - sourceStart);
        System.arraycopy(source, sourceStart, target, targetStart, copied);
        return copied;
    }

    @Override
    public String getEncoding() {
        return parser.getInputEncoding();
    }

    @Override
    public String getCharacterEncodingScheme() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public boolean isStandalone() {
        return false;
    }

    @Override
    public boolean standaloneSet() {
        return false;
    }

    @Override
    public String getPITarget() {
        return null;
    }

    @Override
    public String getPIData() {
        return null;
    }

    @Override
    public Object getProperty(String name) {
        return null;
    }

    @Override
    public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
        // Not implemented
    }

    @Override
    public void close() throws XMLStreamException {
        // Not implemented - XmlPullParser doesn't have close
    }

    @Override
    public int nextTag() throws XMLStreamException {
        int eventType = next();
        while ((eventType == CHARACTERS && isWhiteSpace())
                || (eventType == CDATA && isWhiteSpace())
                || eventType == SPACE
                || eventType == PROCESSING_INSTRUCTION
                || eventType == COMMENT) {
            eventType = next();
        }
        if (eventType != START_ELEMENT && eventType != END_ELEMENT) {
            throw new XMLStreamException("expected start or end tag", getLocation());
        }
        return eventType;
    }
}
