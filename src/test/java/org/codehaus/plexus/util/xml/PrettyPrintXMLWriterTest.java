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

import javax.swing.text.html.HTML.Tag;

import java.io.StringWriter;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test of {@link org.codehaus.plexus.util.xml.PrettyPrintXMLWriter}
 *
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 * @author <a href="mailto:belingueres@gmail.com">Gabriel Belingueres</a>
 * @version $Id: $Id
 * @since 3.4.0
 */
class PrettyPrintXMLWriterTest {
    StringWriter w;

    PrettyPrintXMLWriter writer;

    /**
     * <p>setUp.</p>
     */
    @BeforeEach
    void setUp() {
        initWriter();
    }

    /**
     * <p>tearDown.</p>
     */
    @AfterEach
    void tearDown() {
        writer = null;
        w = null;
    }

    private void initWriter() {
        w = new StringWriter();
        writer = new PrettyPrintXMLWriter(w);
    }

    /**
     * <p>testDefaultPrettyPrintXMLWriter.</p>
     */
    @Test
    void defaultPrettyPrintXMLWriter() {
        writer.startElement(Tag.HTML.toString());

        writeXhtmlHead(writer);

        writeXhtmlBody(writer);

        writer.endElement(); // Tag.HTML

        assertEquals(expectedResult(PrettyPrintXMLWriter.LS), w.toString());
    }

    /**
     * <p>testPrettyPrintXMLWriterWithGivenLineSeparator.</p>
     */
    @Test
    void prettyPrintXMLWriterWithGivenLineSeparator() {
        writer.setLineSeparator("\n");

        writer.startElement(Tag.HTML.toString());

        writeXhtmlHead(writer);

        writeXhtmlBody(writer);

        writer.endElement(); // Tag.HTML

        assertEquals(expectedResult("\n"), w.toString());
    }

    /**
     * <p>testPrettyPrintXMLWriterWithGivenLineIndenter.</p>
     */
    @Test
    void prettyPrintXMLWriterWithGivenLineIndenter() {
        writer.setLineIndenter("    ");

        writer.startElement(Tag.HTML.toString());

        writeXhtmlHead(writer);

        writeXhtmlBody(writer);

        writer.endElement(); // Tag.HTML

        assertEquals(expectedResult("    ", PrettyPrintXMLWriter.LS), w.toString());
    }

    /**
     * <p>testEscapeXmlAttribute.</p>
     */
    @Test
    void escapeXmlAttribute() {
        // Windows
        writer.startElement(Tag.DIV.toString());
        writer.addAttribute("class", "sect\r\nion");
        writer.endElement(); // Tag.DIV
        assertEquals("<div class=\"sect&#10;ion\"/>", w.toString());

        // Mac
        initWriter();
        writer.startElement(Tag.DIV.toString());
        writer.addAttribute("class", "sect\rion");
        writer.endElement(); // Tag.DIV
        assertEquals("<div class=\"sect&#13;ion\"/>", w.toString());

        // Unix
        initWriter();
        writer.startElement(Tag.DIV.toString());
        writer.addAttribute("class", "sect\nion");
        writer.endElement(); // Tag.DIV
        assertEquals("<div class=\"sect&#10;ion\"/>", w.toString());
    }

    /**
     * <p>testendElementAlreadyClosed.</p>
     */
    @Test
    void testendElementAlreadyClosed() {
        writer.startElement(Tag.DIV.toString());
        writer.addAttribute("class", "someattribute");
        writer.endElement();
        assertThrows(
                NoSuchElementException.class,
                () -> // Tag.DIV closed
                writer.endElement());
    }

    private void writeXhtmlHead(XMLWriter writer) {
        writer.startElement(Tag.HEAD.toString());
        writer.startElement(Tag.TITLE.toString());
        writer.writeText("title");
        writer.endElement(); // Tag.TITLE
        writer.startElement(Tag.META.toString());
        writer.addAttribute("name", "author");
        writer.addAttribute("content", "Author");
        writer.endElement(); // Tag.META
        writer.startElement(Tag.META.toString());
        writer.addAttribute("name", "date");
        writer.addAttribute("content", "Date");
        writer.endElement(); // Tag.META
        writer.endElement(); // Tag.HEAD
    }

    private void writeXhtmlBody(XMLWriter writer) {
        writer.startElement(Tag.BODY.toString());
        writer.startElement(Tag.P.toString());
        writer.writeText("Paragraph 1, line 1. Paragraph 1, line 2.");
        writer.endElement(); // Tag.P
        writer.startElement(Tag.DIV.toString());
        writer.addAttribute("class", "section");
        writer.startElement(Tag.H2.toString());
        writer.writeText("Section title");
        writer.endElement(); // Tag.H2
        writer.endElement(); // Tag.DIV
        writer.endElement(); // Tag.BODY
    }

    private String expectedResult(String lineSeparator) {
        return expectedResult("  ", lineSeparator);
    }

    private String expectedResult(String lineIndenter, String lineSeparator) {
        return "<html>" + lineSeparator + lineIndenter
                + "<head>" + lineSeparator + lineIndenter
                + lineIndenter + "<title>title</title>"
                + lineSeparator
                + lineIndenter
                + lineIndenter + "<meta name=\"author\" content=\"Author\"/>"
                + lineSeparator
                + lineIndenter
                + lineIndenter + "<meta name=\"date\" content=\"Date\"/>"
                + lineSeparator
                + lineIndenter
                + "</head>" + lineSeparator + lineIndenter
                + "<body>" + lineSeparator + lineIndenter
                + lineIndenter + "<p>Paragraph 1, line 1. Paragraph 1, line 2.</p>"
                + lineSeparator
                + lineIndenter
                + lineIndenter + "<div class=\"section\">"
                + lineSeparator
                + lineIndenter
                + lineIndenter + lineIndenter + "<h2>Section title</h2>"
                + lineSeparator
                + lineIndenter
                + lineIndenter + "</div>" + lineSeparator + lineIndenter
                + "</body>" + lineSeparator + "</html>";
    }
}
