FROM openjdk:8-jdk-alpine
COPY /var/lib/jenkins/workspace/currency-converter/target/scala-2.13/scala-currency-converter-assembly-0.1.jar /currency-converter.jar
ENTRYPOINT exec java $* -jar /currency-converter.jar