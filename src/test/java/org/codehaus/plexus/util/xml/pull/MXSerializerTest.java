package org.codehaus.plexus.util.xml.pull;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MXSerializerTest {

    @Test
    void serialize() throws Exception {

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
    void deserialize() throws Exception {
        MXParser parser = new MXParser();
        parser.setInput(new StringReader(expectedOutput()));
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            eventType = parser.next();
        }
    }

    private String expectedOutput() {
        return "<?xml version=\"1.0\" standalone=\"yes\"?>" + "<root>"
                + "<char>BACKSPACE: </char>"
                + "<char>CHARACTER TABULATION: \t</char>"
                + "<char>LINE FEED (LF): \n</char>"
                + "<char>LINE TABULATION: </char>"
                + "<char>CARRIAGE RETURN (CR): \r</char>"
                + "<char>SHIFT IN: </char>"
                + "</root>";
    }

    /**
     * Tests MJAVADOC-793.
     */
    @Test
    void writeNullValues() throws IOException {
        // should be no-ops
        new MXSerializer().writeElementContent(null, null);
        new MXSerializer().writeAttributeValue(null, null);
        final StringWriter stringWriter = new StringWriter();
        new MXSerializer().writeElementContent(null, stringWriter);
        assertEquals("", stringWriter.toString());
        new MXSerializer().writeAttributeValue(null, stringWriter);
        assertEquals("", stringWriter.toString());
    }
}
