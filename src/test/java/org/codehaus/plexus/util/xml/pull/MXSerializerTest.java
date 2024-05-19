package org.codehaus.plexus.util.xml.pull;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MXSerializerTest {

    @Test
    void testSerialize() throws Exception {

        StringWriter writer = new StringWriter();

        MXSerializer sr = new MXSerializer();
        sr.setOutput(writer);

        sr.startDocument(null, Boolean.TRUE);
        sr.startTag(null, "root");
        for (int i : Arrays.asList(8, 9, 10, 11, 13, 15)) {
            sr.startTag(null, "char");
            sr.text(Character.getName(i) + ": " + ((char) i));
            sr.endTag(null, "char");
        }

        sr.endTag(null, "root");
        sr.endDocument();
        assertEquals(expectedOutput(), writer.toString());
    }

    @Test
    void testDeserialize() throws Exception {
        MXParser parser = new MXParser();
        parser.setInput(new StringReader(expectedOutput()));
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            eventType = parser.next();
        }
    }

    private String expectedOutput() {
        StringBuilder out = new StringBuilder();
        out.append("<?xml version=\"1.0\" standalone=\"yes\"?>");
        out.append("<root>");
        out.append("<char>BACKSPACE: </char>");
        out.append("<char>CHARACTER TABULATION: \t");
        out.append("</char><char>LINE FEED (LF): \n");
        out.append("</char><char>LINE TABULATION: </char>");
        out.append("<char>CARRIAGE RETURN (CR): \r");
        out.append("</char><char>SHIFT IN: </char>");
        out.append("</root>");
        return out.toString();
    }
}
