Plexus-Xml
============

[![Build Status](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg)](https://github.com/codehaus-plexus/plexus-xml/actions)
[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml)

This library consists of XML classes (`org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`) that:
1. have been extracted from [`plexus-utils`](https://github.com/codehaus-plexus/plexus-utils/) 4,
2. then updated to use [`maven-xml-api`](https://github.com/apache/maven/tree/maven-4.0.0-alpha-7/api/maven-api-xml)/[`maven-xml-impl`](https://github.com/apache/maven/tree/maven-4.0.0-alpha-7/maven-xml-impl)

For publishing [the site](https://codehaus-plexus.github.io/plexus-xml/) do the following:

```
mvn -Preporting verify site site:stage scm-publish:publish-scm
```
