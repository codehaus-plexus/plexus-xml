package org.codehaus.plexus.util.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

public class TestUtils {

    public static String readAllFrom(Reader input) throws IOException {
        StringWriter output = new StringWriter();
        char[] buffer = new char[16384];
        int n = 0;
        while (0 <= (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        output.flush();
        return output.toString();
    }
    /**
     * <p>
     * How many times is the substring in the larger String.
     * </p>
     * <p>
     * <code>null</code> returns <code>0</code>.
     * </p>
     *
     * @param str the String to check
     * @param sub the substring to count
     * @return the number of occurrences, 0 if the String is <code>null</code>
     * @throws NullPointerException if sub is <code>null</code>
     */
    public static int countMatches(String str, String sub) {
        if (sub.isEmpty()) {
            return 0;
        }
        if (str == null) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}
