# Plexus XML

[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-xml.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/org.codehaus.plexus/plexus-xml)
[![GitHub CI](https://github.com/codehaus-plexus/plexus-xml/actions/workflows/maven.yml/badge.svg)](https://github.com/codehaus-plexus/plexus-xml/actions)
[![Reproducible Builds](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jvm-repo-rebuild/reproducible-central/master/content/org/codehaus/plexus/plexus-xml/badge.json)](https://github.com/jvm-repo-rebuild/reproducible-central/blob/master/content/org/codehaus/plexus/plexus-xml/README.md)

The XML classes — `Xpp3Dom`, `Xpp3DomBuilder`, `Xpp3DomWriter` and the XML pull parser — that used to live in
`plexus-utils`, in the packages `org.codehaus.plexus.util.xml` and `org.codehaus.plexus.util.xml.pull`.

## Status

Maintained. **Two lines exist and they are not interchangeable** — pick deliberately:

| Line  |  Requires   |     Works under     |                         Use when                          |
|-------|-------------|---------------------|-----------------------------------------------------------|
| `3.x` | Java 8      | Maven 3 and Maven 4 | You split from `plexus-utils` 4 and want the same classes |
| `4.x` | **Java 17** | **Maven 4 only**    | You are building for Maven 4                              |

`3.x` is the straight extraction from `plexus-utils` 4 — same classes, same packages — and is maintained on
the [`3.x` branch](https://github.com/codehaus-plexus/plexus-xml/tree/3.x).

`4.x` is rebuilt on Maven 4's [`maven-xml-api`](https://maven.apache.org/ref/4-LATEST/api/maven-api-xml/)
and works only under Maven 4.

If you are here because upgrading `plexus-utils` 3 → 4 gave you a `NoClassDefFoundError` on `Xpp3Dom`,
you want **`3.x`**.

## Using it

```xml
<dependency>
  <groupId>org.codehaus.plexus</groupId>
  <artifactId>plexus-xml</artifactId>
  <version>4.1.1</version>
</dependency>
```

Check the badge above for the current version, and read the table before taking it.

## Requirements

Java 17 for `4.x`. Java 8 for `3.x`.

## Documentation

- [Project site](https://codehaus-plexus.github.io/plexus-xml/)
- [Javadoc](https://javadoc.io/doc/org.codehaus.plexus/plexus-xml)
- [Release notes](https://github.com/codehaus-plexus/plexus-xml/releases)

## Contributing

See [CONTRIBUTING.md](https://github.com/codehaus-plexus/.github/blob/master/CONTRIBUTING.md). In short:
`mvn verify` builds, and run `mvn spotless:apply` before pushing or CI will fail on formatting.

Please report security vulnerabilities privately — see
[SECURITY.md](https://github.com/codehaus-plexus/.github/blob/master/SECURITY.md), not a public issue.
