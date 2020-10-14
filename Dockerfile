#This build need volume-mounting option so it is not suggested to use this file.

FROM maven:3.6.0-jdk-11 as build
COPY ./src /home/app/src
COPY ./AllSuitesTest.xml /home/app/AllSuitesTest.xml
COPY ./docker-compose.yaml /home/app/docker-compose.yaml
COPY ./dockerStart.bat /home/app/dockerStart.bat
COPY ./dockerStop.bat /home/app/dockerStop.bat
COPY ./dockerStart.sh /home/app/dockerStart.sh
COPY ./dockerStop.sh /home/app/dockerStop.sh
COPY ./NonParallelTests.xml /home/app/NonParallelTests.xml
COPY ./ParallelTests.xml /home/app/ParallelTests.xml
COPY ./pom.xml /home/app/pom.xml
WORKDIR /home/app
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get clean;
RUN mvn install -DskipTests
CMD mvn clean test

