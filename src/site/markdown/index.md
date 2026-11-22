# Plexus XML Utilities

A collection of various utility classes to ease working with XML.

This library consists of XML classes (`org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`):
1. that have been extracted from [`plexus-utils`](../plexus-utils/) 4,
2. then updated to use [`maven-api-xml`](https://maven.apache.org/ref/4-LATEST/api/maven-api-xml/)/[`maven-xml`](https://maven.apache.org/ref/4-LATEST/impl/maven-xml/index.html)

This is the 4.x line: it requires Java 17 and works only under Maven 4. The 3.x line, for Maven 3, is
built from the [`3.x` branch](https://github.com/codehaus-plexus/plexus-xml/tree/3.x) and requires
Java 8; the [README](https://github.com/codehaus-plexus/plexus-xml#status) explains which one to use.
