# Plexus XML

[![Maven Central 4.x](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central%204.x&versionPrefix=4.)](https://central.sonatype.com/artifact/org.codehaus.plexus/plexus-xml/versions)
[![Maven Central 3.x](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central%203.x&versionPrefix=3.)](https://central.sonatype.com/artifact/org.codehaus.plexus/plexus-xml/versions)
[![GitHub CI](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg)](https://github.com/codehaus-plexus/plexus-xml/actions)
[![Reproducible Builds](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jvm-repo-rebuild/reproducible-central/master/content/org/codehaus/plexus/plexus-xml/badge.json)](https://github.com/jvm-repo-rebuild/reproducible-central/blob/master/content/org/codehaus/plexus/plexus-xml/README.md)

Plexus XML contains the XML classes that [`plexus-utils`](https://github.com/codehaus-plexus/plexus-utils) shipped up to its 3.x
line, in the packages `org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`. `plexus-utils` 4
no longer contains them.

The library provides the following classes:

- `Xpp3Dom`, `Xpp3DomBuilder`, `Xpp3DomWriter` and `Xpp3DomUtils`, a small XML document model with its
  parser, writer and merge helpers.
- `XmlPullParser` and its implementation `MXParser`, and `XmlSerializer` and its implementation
  `MXSerializer`.
- `XmlStreamReader` and `XmlStreamWriter`, which detect the encoding of an XML stream.
- `XMLWriter`, `PrettyPrintXMLWriter` and `CompactXMLWriter`, plus the helpers `XmlUtil` and `XmlWriterUtil`.

## Status

Maintained. Two lines are released from this repository, and they are not interchangeable:

| Line  |          Branch           |       Requires       |                         Use when                          |
|-------|---------------------------|----------------------|-----------------------------------------------------------|
| `3.x` | [`3.x`][branch-3.x]       | Java 8               | Your code runs in Maven 3, or in both Maven 3 and Maven 4 |
| `4.x` | [`master`][branch-master] | Java 17, since 4.1.0 | Your code targets Maven 4                                 |

[branch-3.x]: https://github.com/codehaus-plexus/plexus-xml/tree/3.x
[branch-master]: https://github.com/codehaus-plexus/plexus-xml/tree/master

`3.x` keeps the implementation that `plexus-utils` 3 had and has no dependencies:
`plexus-utils` 3 = `plexus-utils` 4 + `plexus-xml` 3.

`4.x` rebuilds `Xpp3Dom` on top of `XmlNode` from Maven 4's
[`maven-api-xml`](https://maven.apache.org/ref/4-LATEST/api/maven-api-xml/), and depends on `maven-api-xml`
and `maven-xml`. Don't use it in a plugin or extension that must run in Maven 3: Maven 3 exports its own
`Xpp3Dom` class to plugins, and that class lacks the `XmlNode` constructor that `Xpp3DomBuilder` 4.x calls.

If upgrading `plexus-utils` from 3 to 4 gave you a `NoClassDefFoundError` on `Xpp3Dom` and
you don't target Maven 4, add `3.x`.

## Using it

```xml
<dependency>
  <groupId>org.codehaus.plexus</groupId>
  <artifactId>plexus-xml</artifactId>
</dependency>
```

Add a `<version>` element with the current version of the line that you chose in [Status](#status). The
badges at the top of this page show the current version of each line.

## Requirements

Java 17 for `4.x`. Java 8 for `3.x`.

## Documentation

The following pages document the library:

- [Project site](https://codehaus-plexus.github.io/plexus-xml/)
- [Javadoc](https://javadoc.io/doc/org.codehaus.plexus/plexus-xml)
- [Release notes](https://github.com/codehaus-plexus/plexus-xml/releases)

## Building

Building `master` requires JDK 17 or later and Maven 3.9.0 or later. To build and run the tests:

```shell
mvn verify
```

A local build formats the sources with Spotless. On CI the build only checks the format and fails on
unformatted code, so commit the formatted files. To format without building:

```shell
mvn spotless:apply
```

## Contributing

See [CONTRIBUTING.md](https://github.com/codehaus-plexus/.github/blob/master/CONTRIBUTING.md), and report
bugs in the [issue tracker](https://github.com/codehaus-plexus/plexus-xml/issues). Changes for Maven 3 users
go to the `3.x` branch.

Report security vulnerabilities privately, as described in
[SECURITY.md](https://github.com/codehaus-plexus/.github/blob/master/SECURITY.md), not in a public issue.

## License

Plexus XML is licensed under the
[Apache License, Version 2.0](https://github.com/codehaus-plexus/plexus-xml/blob/master/LICENSE.txt).
