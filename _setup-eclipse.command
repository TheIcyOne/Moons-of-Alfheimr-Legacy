#!/bin/bash
cd "${0%/*}"
./gradlew setupDecompWorkspace --refresh-dependencies && ./gradlew eclipse
