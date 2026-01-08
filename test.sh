#!/bin/sh

./gradlew build || exit 1

java -jar app/build/libs/app-all.jar
