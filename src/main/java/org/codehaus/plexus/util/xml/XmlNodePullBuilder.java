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

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.api.xml.XmlNode;
import org.codehaus.plexus.util.xml.pull.MXParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Builds an {@link XmlNode} from the {@link XmlPullParser} in this artifact.
 * <p>
 * This is a port of {@code org.apache.maven.internal.xml.XmlNodeBuilder} from Apache Maven, which is deprecated
 * there and which reaches back into this artifact for its parser. {@link org.apache.maven.api.xml.XmlService} is
 * the supported replacement for reading a {@code Reader} or {@code InputStream}, but it parses through StAX and
 * exposes neither an {@link XmlPullParser} overload nor a trim flag, so it cannot serve
 * {@link Xpp3DomBuilder#build(XmlPullParser, boolean, Xpp3DomBuilder.InputLocationBuilder)} or the
 * {@code trim == false} callers.
 * <p>
 * All methods attempt to fully parse the XML. The caller is responsible for closing {@code InputStream} and
 * {@code Reader} arguments.
 */
final class XmlNodePullBuilder {

    private XmlNodePullBuilder() {}

    static XmlNode build(InputStream is, String encoding, boolean trim) throws XmlPullParserException, IOException {
        XmlPullParser parser = new MXParser();
        parser.setInput(is, encoding);
        return build(parser, trim, null);
    }

    static XmlNode build(Reader reader, boolean trim, Xpp3DomBuilder.InputLocationBuilder locationBuilder)
            throws XmlPullParserException, IOException {
        XmlPullParser parser = new MXParser();
        parser.setInput(reader);
        return build(parser, trim, locationBuilder);
    }

    static XmlNode build(XmlPullParser parser, boolean trim, Xpp3DomBuilder.InputLocationBuilder locationBuilder)
            throws XmlPullParserException, IOException {
        boolean spacePreserve = false;
        String name = null;
        String value = null;
        Object location = null;
        Map<String, String> attrs = null;
        List<XmlNode> children = null;
        int eventType = parser.getEventType();
        boolean emptyTag = false;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                emptyTag = parser.isEmptyElementTag();
                if (name == null) {
                    name = parser.getName();
                    location = locationBuilder != null ? locationBuilder.toInputLocation(parser) : null;
                    int attributesSize = parser.getAttributeCount();
                    if (attributesSize > 0) {
                        attrs = new HashMap<>();
                        for (int i = 0; i < attributesSize; i++) {
                            String aname = parser.getAttributeName(i);
                            String avalue = parser.getAttributeValue(i);
                            attrs.put(aname, avalue);
                            spacePreserve = spacePreserve || ("xml:space".equals(aname) && "preserve".equals(avalue));
                        }
                    }
                } else {
                    if (children == null) {
                        children = new ArrayList<>();
                    }
                    children.add(build(parser, trim, locationBuilder));
                }
            } else if (eventType == XmlPullParser.TEXT) {
                String text = parser.getText();
                if (trim && !spacePreserve) {
                    text = text.trim();
                }
                value = value != null ? value + text : text;
            } else if (eventType == XmlPullParser.END_TAG) {
                return XmlNode.newInstance(
                        name,
                        children == null ? (value != null ? value : emptyTag ? null : "") : null,
                        attrs,
                        children,
                        location);
            }
            eventType = parser.next();
        }
        throw new IllegalStateException("End of document found before returning to 0 depth");
    }
}
