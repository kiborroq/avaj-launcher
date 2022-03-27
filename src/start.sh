#!/bin/sh

find . -name '*.class' -delete
find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java ru.school.avaj.Main scenario.txt