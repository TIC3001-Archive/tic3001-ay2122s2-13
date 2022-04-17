#!/bin/bash

# source code test
mvn clean
mvn test
mvn install
mvn compile


# package into jar
mvn package
# test jar output


test_codes=("1" "2" "3" "4" "5" "6" "7" "8")

echo "running test"

for i in "${test_codes[@]}"; do

  folder="./sample_test_cases/TestCase${i}"
  manual_list_file_path="${folder}/ListOfFiles${i}.in"
  queries="${folder}/Queries${i}.txt"
  actual_output="${folder}/ActualOutput${i}.txt"
  expected_output="${folder}/ExpectedOutput${i}.txt"

  java -jar NoelLim/KWIC.jar "${manual_list_file_path}" < "${queries}" > "${actual_output}"

  cmp --print-chars "${expected_output}" "${actual_output}"

  if [ $? -eq 0 ]; then
    echo "Test result: PASSED  ${i}"
  else
    echo "Test result: FAILED"
    exit 1
  fi
done


exit 0
