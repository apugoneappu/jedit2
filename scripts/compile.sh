#!/usr/bin/env bash
git clone https://github.com/jrichardsz/voce.git
cd voce/offline-jars
mvn install:install-file -Dfile=sphinx4-1.0.jar -DgroupId=edu.cmu.sphinx -DartifactId=sphinx4 -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jsapi-1.0.jar -DgroupId=javax.speech -DartifactId=jsapi -Dversion=1.0 -Dpackaging=jar
cd ..
mvn clean package
mvn clean install
cd ..
rm -rf voce
echo 'export GOOGLE_APPLICATION_CREDENTIALS="$PWD/Jedit-d618ac66ba93.json"' >> ~/.bashrc
mvn clean package
