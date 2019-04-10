#!/usr/bin/env bash
mvn clean package
mvn exec:java -DTextDemo
