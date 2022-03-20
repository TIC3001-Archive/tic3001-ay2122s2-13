#!/bin/bash

# source code test
mvn clean
mvn install
mvn compile
mvn test

# package into jar
mvn package
# test jar output

test_codes=("1" "2" "3")

echo "running test"
blankfiledir="./sample_test_cases/blank.txt"
for i in "${test_codes[@]}"; do

  folder="./sample_test_cases/Test${i}"

  java -jar NoelLim/KWIC.jar "${folder}/Titles${i}.txt" "${folder}/Ignored${i}.txt" "${folder}/Required${i}.txt"

  cmp --print-chars "${folder}/Output${i}.txt" "${folder}/Titles${i}-output.txt"

  if [ $? -eq 0 ]; then
    echo "Test result: PASSED  ${i}"
  else
    echo "Test result: FAILED"
    exit 1
  fi
done

exit 0
