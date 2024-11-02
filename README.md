Plexus-Xml
============

[![Build Status](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg)](https://github.com/codehaus-plexus/plexus-xml/actions)
[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central&versionPrefix=3.)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml)
[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml)
[![Reproducible Builds](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jvm-repo-rebuild/reproducible-central/master/content/org/codehaus/plexus/plexus-xml/badge.json)](https://github.com/jvm-repo-rebuild/reproducible-central/blob/master/content/org/codehaus/plexus/plexus-xml/README.md)

This library consists of XML classes (`org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`) that:

1. have been extracted from [`plexus-utils`](https://github.com/codehaus-plexus/plexus-utils/) 4:\
   this step is released as `plexus-xml` 3, maintained in [3.x branch](tree/3.x)\
   [![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central&versionPrefix=3.)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml),

2. then updated to use Maven 4-specific [`maven-xml-api`](https://github.com/apache/maven/tree/maven-4.0.0-beta-5/api/maven-api-xml)/[`maven-xml-impl`](https://github.com/apache/maven/tree/maven-4.0.0-beta-5/maven-xml-impl):\
   this is the `master` branch from which `plexus-xml` 4 is released\
   Version 4.x requires Java 17 (like Maven 4)
   [![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml)

For publishing [the site](https://codehaus-plexus.github.io/plexus-xml/) do the following:

```
mvn -Preporting verify site site:stage scm-publish:publish-scm
```
