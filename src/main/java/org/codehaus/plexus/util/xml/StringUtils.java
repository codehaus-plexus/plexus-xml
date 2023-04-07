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
package org.codehaus.plexus.util.xml;

import java.util.StringTokenizer;

/**
 * <p>
 * Common <code>String</code> manipulation routines, extracted from Plexus Utils and trimmed down for Plexus Xml.
 * </p>
 * <p>
 * Originally from <a href="http://jakarta.apache.org/turbine/">Turbine</a> and the GenerationJavaCore library.
 * </p>
 *
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 * @author <a href="mailto:gcoladonato@yahoo.com">Greg Coladonato</a>
 * @author <a href="mailto:bayard@generationjava.com">Henri Yandell</a>
 * @author <a href="mailto:ed@codehaus.org">Ed Korthof</a>
 * @author <a href="mailto:rand_mcneely@yahoo.com">Rand McNeely</a>
 * @author Stephen Colebourne
 * @author <a href="mailto:fredrik@westermarck.com">Fredrik Westermarck</a>
 * @author Holger Krauth
 * @author <a href="mailto:alex@purpletech.com">Alexander Day Chaffee</a>
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 * @since 1.0
 *
 */
class StringUtils {
    /**
     * <p>
     * <code>StringUtils</code> instances should NOT be constructed in standard programming. Instead, the class should
     * be used as <code>StringUtils.trim(" foo ");</code>.
     * </p>
     * <p>
     * This constructor is public to permit tools that require a JavaBean manager to operate.
     * </p>
     */
    private StringUtils() {}

    /**
     * Checks if a String is <code>null</code> or empty.
     * <p>
     * <strong>Note:</strong> In releases prior 3.5.0, this method trimmed the input string such that it worked
     * the same as {@link #isBlank(String)}. Since release 3.5.0 it no longer returns {@code true} for strings
     * containing only whitespace characters.
     *
     * @param str the String to check
     * @return <code>true</code> if the String is <code>null</code>, or length zero
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.isEmpty()));
    }

    // Splitting
    // --------------------------------------------------------------------------

    /**
     * @param text The string to parse.
     * @param separator Characters used as the delimiters. If <code>null</code>, splits on whitespace.
     * @return an array of parsed Strings
     */
    public static String[] split(String text, String separator) {
        return split(text, separator, -1);
    }

    /**
     * <p>
     * Splits the provided text into a array, based on a given separator.
     * </p>
     * <p>
     * The separator is not included in the returned String array. The maximum number of splits to perform can be
     * controlled. A <code>null</code> separator will cause parsing to be on whitespace.
     * </p>
     * <p>
     * This is useful for quickly splitting a String directly into an array of tokens, instead of an enumeration of
     * tokens (as <code>StringTokenizer</code> does).
     * </p>
     *
     * @param str The string to parse.
     * @param separator Characters used as the delimiters. If <code>null</code>, splits on whitespace.
     * @param max The maximum number of elements to include in the array. A zero or negative value implies no limit.
     * @return an array of parsed Strings
     */
    private static String[] split(String str, String separator, int max) {
        StringTokenizer tok;
        if (separator == null) {
            // Null separator means we're using StringTokenizer's default
            // delimiter, which comprises all whitespace characters.
            tok = new StringTokenizer(str);
        } else {
            tok = new StringTokenizer(str, separator);
        }

        int listSize = tok.countTokens();
        if ((max > 0) && (listSize > max)) {
            listSize = max;
        }

        String[] list = new String[listSize];
        int i = 0;
        int lastTokenBegin;
        int lastTokenEnd = 0;
        while (tok.hasMoreTokens()) {
            if ((max > 0) && (i == listSize - 1)) {
                // In the situation where we hit the max yet have
                // tokens left over in our input, the last list
                // element gets all remaining text.
                String endToken = tok.nextToken();
                lastTokenBegin = str.indexOf(endToken, lastTokenEnd);
                list[i] = str.substring(lastTokenBegin);
                break;
            } else {
                list[i] = tok.nextToken();
                lastTokenBegin = str.indexOf(list[i], lastTokenEnd);
                lastTokenEnd = lastTokenBegin + list[i].length();
            }
            i++;
        }
        return list;
    }

    /**
     * <p>
     * Repeat a String <code>n</code> times to form a new string.
     * </p>
     *
     * @param str String to repeat
     * @param repeat number of times to repeat str
     * @return String with repeated String
     * @throws NegativeArraySizeException if <code>repeat &lt; 0</code>
     * @throws NullPointerException if str is <code>null</code>
     */
    public static String repeat(String str, int repeat) {
        StringBuilder buffer = new StringBuilder(repeat * str.length());
        for (int i = 0; i < repeat; i++) {
            buffer.append(str);
        }
        return buffer.toString();
    }

    /**
     * Remove all duplicate whitespace characters and line terminators are replaced with a single space.
     *
     * @param s a not null String
     * @return a string with unique whitespace.
     * @since 1.5.7
     */
    public static String removeDuplicateWhitespace(String s) {
        StringBuilder result = new StringBuilder();
        int length = s.length();
        boolean isPreviousWhiteSpace = false;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            boolean thisCharWhiteSpace = Character.isWhitespace(c);
            if (!(isPreviousWhiteSpace && thisCharWhiteSpace)) {
                result.append(c);
            }
            isPreviousWhiteSpace = thisCharWhiteSpace;
        }
        return result.toString();
    }

    /**
     * Parses the given String and replaces all occurrences of '\n', '\r' and '\r\n' with the system line separator.
     *
     * @param s a not null String
     * @param ls the wanted line separator ("\n" on UNIX), if null using the System line separator.
     * @return a String that contains only System line separators.
     * @throws IllegalArgumentException if ls is not '\n', '\r' and '\r\n' characters.
     * @since 1.5.7
     */
    public static String unifyLineSeparators(String s, String ls) {
        if (s == null) {
            return null;
        }

        if (ls == null) {
            ls = System.getProperty("line.separator");
        }

        if (!(ls.equals("\n") || ls.equals("\r") || ls.equals("\r\n"))) {
            throw new IllegalArgumentException("Requested line separator is invalid.");
        }

        int length = s.length();

        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '\r') {
                if ((i + 1) < length && s.charAt(i + 1) == '\n') {
                    i++;
                }

                buffer.append(ls);
            } else if (s.charAt(i) == '\n') {
                buffer.append(ls);
            } else {
                buffer.append(s.charAt(i));
            }
        }

        return buffer.toString();
    }
}
