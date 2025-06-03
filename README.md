Plexus-Xml
==========

[![Build Status](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg)](https://github.com/codehaus-plexus/plexus-xml/actions)
[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central&versionPrefix=3.)](https://search.maven.org/artifact/org.codehaus.plexus/plexus-xml)

This library consists of XML classes (`org.codehaus.plexus.util.xml`) that have been extracted from [`plexus-utils`](https://github.com/codehaus-plexus/plexus-utils/) 3:

> `plexus-utils` 3 = `plexus-utils` 4 + `plexus-xml` 3.

For publishing [the site](https://codehaus-plexus.github.io/plexus-xml/) do the following:

```
mvn -Preporting verify site site:stage scm-publish:publish-scm
```

