jedit
==============================================

Steps to build -
---------------------------------------------

1. [Install Maven](https://maven.apache.org/install.html)
2. Install the voce library to your local Maven repository:
- ```git clone https://github.com/jrichardsz/voce.git``` [repo](https://github.com/jrichardsz/voce)
- Change to the offline-jars directory and run the following commands.
- ```mvn install:install-file -Dfile=sphinx4-1.0.jar -DgroupId=edu.cmu.sphinx -DartifactId=sphinx4 -Dversion=1.0 -Dpackaging=jar```
- ```mvn install:install-file -Dfile=jsapi-1.0.jar -DgroupId=javax.speech -DartifactId=jsapi -Dversion=1.0 -Dpackaging=jar```



