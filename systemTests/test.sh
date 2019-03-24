#!/usr/bin/env bash

## generate expected output
python systemTests/generate_expected_out.py

## Variables
jar_file="out/artifacts/CS4218_Shell_2019_jar/CS4218-Shell-2019.jar"

## command line files
cmd_lines=systemTests/commandLines

## expected files
expected_dir=systemTests/expected

## actual dir
actual_dir=systemTests/actual

for cmd_file in $cmd_lines/*; do
    base=$(basename "$cmd_file")
    echo "**************"
    echo Test $base
    echo "**************"
    actual_file="$actual_dir"/"$base".actual
    expected_file="$expected_dir"/"$base".out
    java -jar $jar_file < $cmd_file > $actual_file
    diff $actual_file $expected_file
    echo "-----------------"
done