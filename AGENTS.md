# Agent guide for plexus-xml

`org.codehaus.plexus:plexus-xml` holds `Xpp3Dom`, `Xpp3DomBuilder`, `Xpp3DomWriter` and the XML pull
parser extracted from `plexus-utils`. The README is explicit that two lines exist: `3.x` (branch
`3.x`) needs Java 8 and works under Maven 3 and 4, while this `4.x` line on `master` requires Java 17
and works only under Maven 4. It builds on `maven-api-xml` at compile time and `maven-xml` at runtime;
the POM sets both through `mavenXmlVersion`, and `javaVersion` 17.

## Build and test

- CI calls the shared `codehaus-plexus/.github` `maven.yml@master` workflow, which runs
  `mvn verify javadoc:javadoc` on Linux, Windows and macOS. This repository's workflow excludes JDK 8
  and 11 from the matrix because the code needs Java 17; change that exclusion together with the Java
  floor. A push to master also deploys a snapshot.
- Unit tests use JUnit 5 (`junit-jupiter-api` and `junit-jupiter-params`) and run through Surefire
  with `childDelegation` on, so the test classes are used, not Surefire's own `plexus-utils`. There
  are many test files, so iterate with targeted tests.
- The JAR sets `Automatic-Module-Name: org.codehaus.plexus.util.xml`; keep that name stable.
- Do not change the 3.x/4.x compatibility story casually: the README tells consumers on Maven 3 which
  line they need.
- Run `mvn spotless:apply` on JDK 17 to 21 before pushing; it also formats Markdown, except
  `src/site/markdown`.

## Documentation updates

A change that users or contributors can notice ships with the documentation it affects, in the same
pull request. A refactor with no visible effect needs none.

- Behaviour of a public class in `org.codehaus.plexus.util.xml` or `...xml.pull`: its Javadoc, which
  is the reference.
- The release lines, the Java or Maven requirement, or the `maven-api-xml` dependency: the "Status"
  and "Requirements" sections of `README.md`, and `src/site/markdown/index.md`.
- `src/site/markdown/index.md` is the only site page, listed as Introduction in `src/site/site.xml`
  together with the Javadoc; add a menu item there when adding a page.
- A new convention, build gate or test fixture: this file.

