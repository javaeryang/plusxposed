#! /bin/bash

./gradlew clean build bintrayUpload -PbintrayUser=Javaer -PbintrayKey=060d1a039d9fc7251daadadc9dddc0d3108909b8 -PdryRun=false

echo "upload finish"