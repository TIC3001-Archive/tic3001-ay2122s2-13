#!/bin/bash

# source code test
mvn clean
mvn test
mvn install
mvn compile


# package into jar
mvn package
# test jar output




exit 0
