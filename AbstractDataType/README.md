# Requirements

For production you will need JDK 11 support and maven for build. Source code is tested on Linux/Monterey.

# Run

Clone the repo.

## Test

Please take note of EOL in Unix / Windows system. 


```sample_test_cases``` Test folder. Test sets are stored in individual subfolders.
```./run-integration-test.sh``` Run integration test.

### Build

In root of repository,

```mvn package``` package \
```./NoelLim/KWIC.jar``` generated jar is single executable fatty \

### Run

```java -jar ./NoelLim/KWIC.jar [source]``` run jar with source path as argument.

