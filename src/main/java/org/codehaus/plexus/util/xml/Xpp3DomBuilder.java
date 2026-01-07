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

import javax.xml.stream.XMLStreamException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.maven.api.xml.XmlService;
import org.apache.maven.internal.xml.XmlNodeBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParser;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 *
 */
public class Xpp3DomBuilder {
    private static final boolean DEFAULT_TRIM = true;

    public static Xpp3Dom build(Reader reader) throws XmlPullParserException, IOException {
        return build(reader, null);
    }

    /**
     * @since 3.2.0
     */
    public static Xpp3Dom build(Reader reader, InputLocationBuilder locationBuilder)
            throws XmlPullParserException, IOException {
        return build(reader, DEFAULT_TRIM, locationBuilder);
    }

    public static Xpp3Dom build(InputStream is, String encoding) throws XmlPullParserException, IOException {
        return build(is, encoding, DEFAULT_TRIM);
    }

    public static Xpp3Dom build(InputStream is, String encoding, boolean trim)
            throws XmlPullParserException, IOException {
        try (InputStream closeMe = is) {
            try {
                if (trim) {
                    return new Xpp3Dom(XmlService.read(is, null));
                } else {
                    return new Xpp3Dom(XmlNodeBuilder.build(is, encoding, trim));
                }
            } catch (XMLStreamException e) {
                throw new XmlPullParserException(e.getMessage(), null, e);
            }
        }
    }

    public static Xpp3Dom build(Reader reader, boolean trim) throws XmlPullParserException, IOException {
        return build(reader, trim, null);
    }

    /**
     * @since 3.2.0
     */
    public static Xpp3Dom build(Reader reader, boolean trim, InputLocationBuilder locationBuilder)
            throws XmlPullParserException, IOException {
        try (Reader closeMe = reader) {
            if (trim) {
                try {
                    XmlService.InputLocationBuilder xlb =
                            locationBuilder != null ? locationBuilder::toInputLocation : null;
                    return new Xpp3Dom(XmlService.read(reader, xlb));
                } catch (XMLStreamException e) {
                    throw new XmlPullParserException(e.getMessage(), null, e);
                }
            } else {
                org.apache.maven.internal.xml.XmlNodeBuilder.InputLocationBuilder xlb = locationBuilder != null
                        ? parser -> {
                            try {
                                return locationBuilder.toInputLocation(
                                        new XmlPullParserToXMLStreamReaderAdapter((XmlPullParser) parser));
                            } catch (XMLStreamException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        : null;
                return new Xpp3Dom(XmlNodeBuilder.build(reader, trim, xlb));
            }
        }
    }

    public static Xpp3Dom build(XmlPullParser parser) throws XmlPullParserException, IOException {
        return build(parser, DEFAULT_TRIM);
    }

    public static Xpp3Dom build(XmlPullParser parser, boolean trim) throws XmlPullParserException, IOException {
        return build(parser, trim, null);
    }

    /**
     * @since 3.2.0
     */
    public static Xpp3Dom build(XmlPullParser parser, boolean trim, InputLocationBuilder locationBuilder)
            throws XmlPullParserException, IOException {
        if (trim) {
            try {
                XmlService.InputLocationBuilder xlb = locationBuilder != null ? locationBuilder::toInputLocation : null;
                return new Xpp3Dom(XmlService.read(new XmlPullParserToXMLStreamReaderAdapter(parser), xlb));
            } catch (XMLStreamException e) {
                throw new XmlPullParserException(e.getMessage(), null, e);
            }
        } else {
            org.apache.maven.internal.xml.XmlNodeBuilder.InputLocationBuilder xlb = locationBuilder != null
                    ? p -> {
                        try {
                            return locationBuilder.toInputLocation(
                                    new XmlPullParserToXMLStreamReaderAdapter((XmlPullParser) p));
                        } catch (XMLStreamException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    : null;
            return new Xpp3Dom(XmlNodeBuilder.build(parser, trim, xlb));
        }
    }

    /**
     * Input location builder interface, to be implemented to choose how to store data.
     *
     * @since 3.2.0
     */
    public interface InputLocationBuilder {
        Object toInputLocation(javax.xml.stream.XMLStreamReader parser);
    }
}
