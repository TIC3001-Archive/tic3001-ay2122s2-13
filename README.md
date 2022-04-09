<<<<<<< HEAD
# Test
=======
# Assignment 2 - KWIC Extensions

## Report

```./a1_report.pdf``` Assignment 1 Report
```./a2_report.pdf``` Assignment 2 Report


## Repositories

### Assignment 1 (Legacy)

This codebase contains 2 repositories from Assignment 1 in its subfolder. Change to respective working directories and
see individual README.md for usage.

1) ```./PipeAndFilter``` Pipe and Filter Design
2) ```./AbstractDataType``` Abstract Data Type Design

### Assignment 2

Assignment 2 follows file structure for Maven support.

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
6. System test against jar. Test files are found at  [Directory for Sample Cases](#directory-for-sample-cases)

#### Local Test

##### pre-defined [git hook](./dev/pre-commit)

copy into git-hook folder in local environment.

#### Remote Github

See [github action](./.github/workflows/flow.yaml)

#### Directory for Sample Cases

```./sample_test_cases``` Test folder. Test sets are stored in individual subfolders.

## Run

```java -jar NoelLim/KWIC.jar [title] [ignore] [required]``` Run program where “titles”, “ignore”, and “required” are
input file paths.

### Example

```java -jar NoelLim/KWIC.jar ./sample_test_cases/Test1/Titles1.txt ./sample_test_cases/Test1/Ignored1.txt ./sample_test_cases/Test1/Titles1.txt ./sample_test_cases/Test1/Required1.txt```

## Output

Output file is in the same parent folder as input file and file name is appended with "-output".
>>>>>>> fda26ea117004ee055925c6ede754640a830269c
