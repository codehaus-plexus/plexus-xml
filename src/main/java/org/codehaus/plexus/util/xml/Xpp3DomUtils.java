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

import org.codehaus.plexus.util.xml.pull.XmlSerializer;

import java.io.IOException;

/** @author Jason van Zyl */
@Deprecated
public class Xpp3DomUtils
{
    /**
     * @deprecated use {@link Xpp3Dom#CHILDREN_COMBINATION_MODE_ATTRIBUTE}
     */
    @Deprecated
    public static final String CHILDREN_COMBINATION_MODE_ATTRIBUTE = Xpp3Dom.CHILDREN_COMBINATION_MODE_ATTRIBUTE;

    /**
     * @deprecated use {@link Xpp3Dom#CHILDREN_COMBINATION_MERGE}
     */
    @Deprecated
    public static final String CHILDREN_COMBINATION_MERGE = Xpp3Dom.CHILDREN_COMBINATION_MERGE;

    /**
     * @deprecated use {@link Xpp3Dom#CHILDREN_COMBINATION_APPEND}
     */
    @Deprecated
    public static final String CHILDREN_COMBINATION_APPEND = Xpp3Dom.CHILDREN_COMBINATION_APPEND;

    /**
     * @deprecated use {@link Xpp3Dom#DEFAULT_CHILDREN_COMBINATION_MODE}
     */
    @Deprecated
    public static final String DEFAULT_CHILDREN_COMBINATION_MODE = Xpp3Dom.DEFAULT_CHILDREN_COMBINATION_MODE;

    /**
     * @deprecated use {@link Xpp3Dom#SELF_COMBINATION_MODE_ATTRIBUTE}
     */
    @Deprecated
    public static final String SELF_COMBINATION_MODE_ATTRIBUTE = Xpp3Dom.SELF_COMBINATION_MODE_ATTRIBUTE;

    /**
     * @deprecated use {@link Xpp3Dom#SELF_COMBINATION_OVERRIDE}
     */
    @Deprecated
    public static final String SELF_COMBINATION_OVERRIDE = Xpp3Dom.SELF_COMBINATION_OVERRIDE;

    /**
     * @deprecated use {@link Xpp3Dom#SELF_COMBINATION_MERGE}
     */
    @Deprecated
    public static final String SELF_COMBINATION_MERGE = Xpp3Dom.SELF_COMBINATION_MERGE;

    /**
     * @deprecated use {@link Xpp3Dom#ID_COMBINATION_MODE_ATTRIBUTE}
     */
    @Deprecated
    public static final String ID_COMBINATION_MODE_ATTRIBUTE = Xpp3Dom.ID_COMBINATION_MODE_ATTRIBUTE;

    /**
     * @deprecated use {@link Xpp3Dom#KEYS_COMBINATION_MODE_ATTRIBUTE}
     */
    @Deprecated
    public static final String KEYS_COMBINATION_MODE_ATTRIBUTE = Xpp3Dom.KEYS_COMBINATION_MODE_ATTRIBUTE;

    /**
     * @deprecated use {@link Xpp3Dom#DEFAULT_SELF_COMBINATION_MODE}
     */
    @Deprecated
    public static final String DEFAULT_SELF_COMBINATION_MODE = Xpp3Dom.DEFAULT_SELF_COMBINATION_MODE;

    /**
     * @deprecated use {@link Xpp3Dom#writeToSerializer(String, XmlSerializer)}
     */
    @Deprecated
    public void writeToSerializer( String namespace, XmlSerializer serializer, Xpp3Dom dom )
        throws IOException
    {
        dom.writeToSerializer( namespace, serializer );
    }

    /**
     * Merge two DOMs, with one having dominance in the case of collision.
     *
     * @see #CHILDREN_COMBINATION_MODE_ATTRIBUTE
     * @see #SELF_COMBINATION_MODE_ATTRIBUTE
     * @param dominant The dominant DOM into which the recessive value/attributes/children will be merged
     * @param recessive The recessive DOM, which will be merged into the dominant DOM
     * @param childMergeOverride Overrides attribute flags to force merging or appending of child elements into the
     *            dominant DOM
     * @return merged DOM
     * @deprecated use {@link Xpp3Dom#mergeXpp3Dom(Xpp3Dom, Xpp3Dom, Boolean)}
     */
    @Deprecated
    public static Xpp3Dom mergeXpp3Dom( Xpp3Dom dominant, Xpp3Dom recessive, Boolean childMergeOverride )
    {
        return Xpp3Dom.mergeXpp3Dom( dominant, recessive, childMergeOverride );
    }

    /**
     * Merge two DOMs, with one having dominance in the case of collision. Merge mechanisms (vs. override for nodes, or
     * vs. append for children) is determined by attributes of the dominant root node.
     *
     * @see #CHILDREN_COMBINATION_MODE_ATTRIBUTE
     * @see #SELF_COMBINATION_MODE_ATTRIBUTE
     * @param dominant The dominant DOM into which the recessive value/attributes/children will be merged
     * @param recessive The recessive DOM, which will be merged into the dominant DOM
     * @return merged DOM
     * @deprecated use {@link Xpp3Dom#mergeXpp3Dom(Xpp3Dom, Xpp3Dom)}
     */
    @Deprecated
    public static Xpp3Dom mergeXpp3Dom( Xpp3Dom dominant, Xpp3Dom recessive )
    {
        return Xpp3Dom.mergeXpp3Dom( dominant, recessive );
    }

    /**
     * @deprecated Use {@link org.codehaus.plexus.util.StringUtils#isNotEmpty(String)} instead
     */
    @Deprecated
    public static boolean isNotEmpty( String str )
    {
        return ( str != null && str.length() > 0 );
    }

    /**
     * @deprecated Use {@link org.codehaus.plexus.util.StringUtils#isEmpty(String)} instead
     */
    @Deprecated
    public static boolean isEmpty( String str )
    {
        return ( str == null || str.length() == 0 );
    }
}
