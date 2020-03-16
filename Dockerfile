FROM openjdk:8-jdk-alpine
ARG GIT_COMMIT=unspecified
LABEL git_commit=$GIT_COMMIT
COPY /target/scala-2.13/scala-currency-converter-assembly-0.1.jar /currency-converter.jar
ENTRYPOINT exec java $* -jar /currency-converter.jar