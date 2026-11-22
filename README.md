# Plexus XML 3.x

[![Maven Central 3.x](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central%203.x&versionPrefix=3.)](https://central.sonatype.com/artifact/org.codehaus.plexus/plexus-xml/versions)
[![GitHub CI](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg?branch=3.x)](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml?query=branch%3A3.x)

This is the `3.x` line of Plexus XML, the XML classes that [`plexus-utils`](https://github.com/codehaus-plexus/plexus-utils)
shipped up to its 3.x line, in the packages `org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`.
`plexus-utils` 4 no longer contains them, so `plexus-utils` 3 = `plexus-utils` 4 + `plexus-xml` 3. The library has
no dependencies.

The library provides the following classes:

- `Xpp3Dom`, `Xpp3DomBuilder`, `Xpp3DomWriter` and `Xpp3DomUtils`, a small XML document model with its parser,
  writer and merge helpers.
- `XmlPullParser` and its implementation `MXParser`, and `XmlSerializer` and its implementation `MXSerializer`.
- `XmlStreamReader` and `XmlStreamWriter`, which detect the encoding of an XML stream.
- `XMLWriter`, `PrettyPrintXMLWriter` and `CompactXMLWriter`, plus the helpers `XmlUtil` and `XmlWriterUtil`.

## Status

This branch receives bug fixes, not new features. The `3.x` line is the one to use when your code runs in Maven 3, or
in both Maven 3 and Maven 4. The `4.x` line, built from `master`, is rebuilt on Maven 4's `maven-api-xml` and works
only under Maven 4: Maven 3 exports its own `Xpp3Dom` class to plugins, and that class lacks the constructor that
`Xpp3DomBuilder` 4.x calls. The [`master` README](https://github.com/codehaus-plexus/plexus-xml/blob/master/README.md)
explains the choice in full.

If upgrading `plexus-utils` from 3 to 4 gave you a `NoClassDefFoundError` on `Xpp3Dom` and you don't target Maven 4,
add this line.

A defect in `MXParser` or `Xpp3Dom` often exists in the copy that `plexus-utils` 3 still bundles, so a fix here often
needs a matching change on its `plexus-utils-3.x` branch, and the other way round.

## Using it

```xml
<dependency>
  <groupId>org.codehaus.plexus</groupId>
  <artifactId>plexus-xml</artifactId>
  <version>3.1.1</version>
</dependency>
```

3.1.1 is the latest `3.x` release; the badge above shows the current one.

## Requirements

Java 8 or later. The JAR sets the automatic module name `org.codehaus.plexus.util.xml`, the same name as the `4.x`
line. Building the project needs Maven 3.9.0 or later.

## Documentation

- [Javadoc](https://javadoc.io/doc/org.codehaus.plexus/plexus-xml/3.1.1)
- [Release notes](https://github.com/codehaus-plexus/plexus-xml/releases)

The [project site](https://codehaus-plexus.github.io/plexus-xml/) is published from `master` only and describes
`4.x`. Publishing it from this branch would replace it, so do not.

## Building

```shell
mvn verify
```

A local build formats the sources with Spotless. CI only checks the format and fails on unformatted code, so run
`mvn spotless:apply` on JDK 17 to 21 and commit the result.

## Releasing

Releases of this line are cut from the `3.x` branch the same way as from `master`; the steps and the one-time setup
are in [RELEASING.md](https://github.com/codehaus-plexus/.github/blob/master/RELEASING.md):

```shell
mvn release:prepare release:perform
```

The release drafter keeps a separate draft for this branch: edit that one, and publish it without marking it the
latest release. Skip the site.

## Contributing

Conventions for contributors and agents are in [AGENTS.md](AGENTS.md); see also
[CONTRIBUTING.md](https://github.com/codehaus-plexus/.github/blob/master/CONTRIBUTING.md). Open pull requests
against `3.x`, and report bugs in the [issue tracker](https://github.com/codehaus-plexus/plexus-xml/issues).

Report security vulnerabilities privately, as described in
[SECURITY.md](https://github.com/codehaus-plexus/.github/blob/master/SECURITY.md), not in a public issue.

## License

Plexus XML is licensed under the
[Apache License, Version 2.0](https://github.com/codehaus-plexus/plexus-xml/blob/3.x/LICENSE.txt).
