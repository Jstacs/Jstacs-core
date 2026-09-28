# Contributing to Jstacs

## Development

Create a branch from `master`:

```bash
git checkout master
git pull
git checkout -b my-change
```

Make your changes and verify the project:

```bash
mvn clean verify
```

New functionality and bug fixes should include tests where practical.

Follow the style of the surrounding code and add Javadoc for new public APIs where appropriate.

## Pull requests

Open pull requests against `master`.

Keep changes focused and avoid unrelated formatting or generated-file changes. Before submitting, ensure:

```bash
mvn clean verify
```

completes successfully.

## Releasing

Development versions use Maven snapshot versions, for example:

```text
2.1.3-SNAPSHOT
```

Assuming the current version is `2.1.3-SNAPSHOT`, first verify the project:

```bash
mvn clean verify
```

Create and push the corresponding release tag:

```bash
git tag v2.1.3
git push origin v2.1.3
```

On GitHub, create and publish a GitHub Release from tag `v2.1.3`.

The release workflow removes the `-SNAPSHOT` suffix in CI and publishes version `2.1.3` to Maven Central.

Then bump the local development version by changing it from `2.1.3-SNAPSHOT` to e.g. `2.1.4-SNAPSHOT` in `pom.xml`, then commit and push:

```bash
git add pom.xml
git commit -m "Prepare for 2.1.4-SNAPSHOT"
git push
```

## Reporting issues

For bug reports, include where possible:

- Jstacs version
- Java version
- operating system
- steps or a minimal example reproducing the issue
- expected and actual behavior
- relevant error messages or stack traces

## License

Contributions are distributed under the same license as the project. See [LICENSE](LICENSE).