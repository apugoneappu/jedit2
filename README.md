# Jedit

Jedit is an open-source multimedia text editor, with emphasis on simplicity and ease of use with a clean and simple GUI. 
It supports regular editing options such as font styling, printing, autosave, find-replace and text formatting.

Morever, thoughtful features like ___Speech-to-text___, ___Text-to-speech___ and the ability to ___save document as audio file___ makes Jedit a convenient choice, not only for casual users but also for specially abled people.

Jedit, is however, a much more powerful tool than meets the eye.

This version introduces an all new ___multi-clipboard feature___, now allowing the user to paste from any of the last six selections that were copied.</br>Other features like ___Regular Expressions in the find mode___ and ___Dark theme___ are aimed at increasing the productivity of power users.



## [Software requirement specification (SRS) document](https://docs.google.com/document/d/19rsumU4RA4xAgYwJQ8I1sW9n2HOq506zWT5-1q7sbfI/edit?usp=sharing)
Use case diagram -
![Use case diagram](https://drive.google.com/uc?export=view&id=1LBezxdmMROcVJnladBF8DJV4kGEE7pUA)

-------------------------------------
## GUI Overview -
File menu 
![File Menu](https://drive.google.com/uc?export=view&id=1VlL-rKaIrXe_4m3FqdUmy8OP7_0zYbiE)

View menu
![View Menu](https://drive.google.com/uc?export=view&id=1xm1JwPTygxrFZoJXGJ8OMX1li4pNJ0hk)

Edit menu
![Edit Menu](https://drive.google.com/uc?export=view&id=1EMeiaQSP5N2ibElRocIYBtgP9m-bMg-f)

Format menu
![Format Menu](https://drive.google.com/uc?export=view&id=17op2njWS7Wi1hrgi6n7eH7n8nvwPi6db)

Speech menu 
![Speech Menu](https://drive.google.com/uc?export=view&id=1y9zWPeE2X52RX3PjiiQGcjwCpZPDK6U_)

Clipboard menu
![Clipboard Menu](https://drive.google.com/uc?export=view&id=1lPdUNpohXPO-8jPVWEOzfCUiXnohbym6)

## Platforms supported -
Built with Java, so platform independent.

## Build status - 
[![Build Status](https://travis-ci.com/apugoneappu/jedit.svg?branch=greatest)](https://travis-ci.com/apugoneappu/jedit)

## Prerequisites -
1. [Install Maven](https://maven.apache.org/install.html)
2. Install the voce library to your local Maven repository:
- ```git clone https://github.com/jrichardsz/voce.git``` ([link to repository](https://github.com/jrichardsz/voce))
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
1. Run the bash script scripts/compile.sh</br>The first build may take some time as some components are downloaded from the Maven repository. Please be patient.


## Steps to run the Maven project -
1. Run the bash script scripts/compile_run.sh

