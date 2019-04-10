# jedit

## Prerequisites -
1. [Install Maven](https://maven.apache.org/install.html)
2. Install the voce library to your local Maven repository:
- ```git clone https://github.com/jrichardsz/voce.git``` ([link to repository])(https://github.com/jrichardsz/voce)
- Change to the offline-jars directory and run the following commands.
- ```mvn install:install-file -Dfile=sphinx4-1.0.jar -DgroupId=edu.cmu.sphinx -DartifactId=sphinx4 -Dversion=1.0 -Dpackaging=jar```
- ```mvn install:install-file -Dfile=jsapi-1.0.jar -DgroupId=javax.speech -DartifactId=jsapi -Dversion=1.0 -Dpackaging=jar```
- Change directory to the cloned folder. 
- ```mvn clean package```
- ```mvn clean install```
- [Optional] Remove the cloned directory

3. Set up the environment for the [Google Cloud Speech Library](https://cloud.google.com/speech-to-text/docs/quickstart-client-libraries), follow these steps after changing directory to the clone repostory:
- ```echo 'export GOOGLE_APPLICATION_CREDENTIALS="$PWD/Jedit-d618ac66ba93.json"' >> ~/.bashrc``` 

## Steps to build the Maven project -
1. Change directory to the cloned directory.
2. ```mvn package```

## Steps to run the Maven project -
1. ```mvn -exec:java -DTextDemo```
