# Agent guide for plexus-xml 3.x

`org.codehaus.plexus:plexus-xml` 3.x holds `Xpp3Dom`, `Xpp3DomBuilder`, `Xpp3DomWriter` and the XML pull parser as
they were in `plexus-utils` 3, and is the line for Maven 3 (it also works under Maven 4). It lives on the `3.x`
branch; `master` builds `4.x`, which needs Java 17 and works only under Maven 4. This line has no dependencies and
targets Java 8.

## Build and test

- Build with `mvn verify` on JDK 17 or later; the parent POM sets the Java release to 8 and the minimum Maven version
  to 3.9.0.
- CI runs `.github/workflows/maven.yml` on every push and pull request. It calls the shared Plexus build workflow,
  which runs `mvn verify javadoc:javadoc` on Linux, Windows and macOS with several JDKs, including 8. The deploy job
  it also calls is limited to pushes to `3.x`.
- `.github/workflows/release-drafter.yml` drafts the release notes of this line from the labels of merged pull
  requests, with `.github/release-drafter-3.x.yml`; label every pull request.
- Unit tests use JUnit 5 and run through Surefire with `childDelegation` on, so the test classes are used, not
  Surefire's own `plexus-utils`. There are many test files, so iterate with targeted tests.
- The JAR sets `Automatic-Module-Name: org.codehaus.plexus.util.xml`, the same name as `4.x`; keep that name stable.
- Run `mvn spotless:apply` on JDK 17 to 21 before pushing; it also formats Markdown, except `src/site/markdown`.
- Keep public API compatible: Maven 3 consumers rely on this line. No new dependencies.

## Fixes that belong to both lines

- A fix for a class that exists in both `3.x` and `4.x` goes to `master` first and is ported here, or the reverse;
  say in the pull request which one it is the twin of.
- `plexus-utils` 3 still bundles its own copy of these classes, so a fix to `MXParser`, `Xpp3Dom` or another XML
  class here usually also needs a port to the `plexus-utils-3.x` branch of
  [plexus-utils](https://github.com/codehaus-plexus/plexus-utils). The surrogate-pair fix in `MXParser` (3.1.1 here,
  3.6.4 there) is such a pair.
- A change that depends on `maven-api-xml`, or on Java 17, does not come here.

## Documentation updates

A change that users or contributors can notice ships with the documentation it affects, in the same pull request. A
refactor with no visible effect needs none.

- Behaviour of a public class in `org.codehaus.plexus.util.xml` or `...xml.pull`: its Javadoc, which is the
  reference.
- The release line, the Java or Maven requirement, or the coordinates: `README.md`.
- `src/site/markdown/index.md` is the only site page, listed as Introduction in `src/site/site.xml` together with the
  Javadoc. The site is published from `master` only, so keep this page consistent with the `master` one.
- A fix that also lands on `master` (or the other way round) updates the documentation of both lines in the same
  step, so the `README.md` and Javadoc of the two lines never contradict each other.
- A new convention, build gate or test fixture: this file.

