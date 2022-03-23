# Assignment 2 - KWIC Extensions

## Report

// TODO
` ` Assignment Report

// TODO

## Development

### Environment

- JDK 11
- Build Tool: Apache Maven 3.8.4
- IDE: IntelliJ IDEA 2021.3.2

### Continuous Integration

The authoritative test script for error detection is found at [./run-integration-test.sh](./run-integration-test.sh).

1. Cleans build directory to avoid using stale artefacts.
2. Runs source unit test.
3. Install dependencies.
4. Compile to bytecode.
5. Package into jar.
6. System test against jar.

#### Local Test

pre-defined [git hook](./dev/pre-commit)

#### Remote Github

[github action](./.github/workflows/flow.yaml)
