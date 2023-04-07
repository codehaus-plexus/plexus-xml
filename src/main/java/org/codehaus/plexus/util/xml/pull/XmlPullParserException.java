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
package org.codehaus.plexus.util.xml.pull;

/**
 * This exception is thrown to signal XML Pull Parser related faults.
 *
 * @author <a href="http://www.extreme.indiana.edu/~aslom/">Aleksander Slominski</a>
 */
public class XmlPullParserException extends Exception {
    /**
     * @deprecated use generic getCause() method
     */
    @Deprecated
    protected Throwable detail;

    protected int row = -1;

    protected int column = -1;

    /*
     * public XmlPullParserException() { }
     */

    public XmlPullParserException(String s) {
        super(s);
    }

    /*
     * public XmlPullParserException(String s, Throwable throwable) { super(s); this.detail = throwable; } public
     * XmlPullParserException(String s, int row, int column) { super(s); this.row = row; this.column = column; }
     */

    public XmlPullParserException(String msg, XmlPullParser parser, Throwable chain) {
        super(
                (msg == null ? "" : msg + " ")
                        + (parser == null ? "" : "(position:" + parser.getPositionDescription() + ") ")
                        + (chain == null ? "" : "caused by: " + chain),
                chain);

        if (parser != null) {
            this.row = parser.getLineNumber();
            this.column = parser.getColumnNumber();
        }
        this.detail = chain;
    }

    /**
     * @deprecated Use the generic <code>getCause()</code> method
     * @return the cause
     */
    @Deprecated
    public Throwable getDetail() {
        return getCause();
    }

    // public void setDetail(Throwable cause) { this.detail = cause; }
    public int getLineNumber() {
        return row;
    }

    public int getColumnNumber() {
        return column;
    }

    /*
     * public String getMessage() { if(detail == null) return super.getMessage(); else return super.getMessage() +
     * "; nested exception is: \n\t" + detail.getMessage(); }
     */

    // NOTE: code that prints this and detail is difficult in J2ME
    @Override
    public void printStackTrace() {
        if (getCause() == null) {
            super.printStackTrace();
        } else {
            synchronized (System.err) {
                System.err.println(super.getMessage() + "; nested exception is:");
                getCause().printStackTrace();
            }
        }
    }
}
