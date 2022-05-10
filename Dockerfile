FROM openjdk:17-alpine
EXPOSE 8080
ADD target/spring-boot-hangman.war spring-boot-hangman.war
ENTRYPOINT ["java", "-jar", "/spring-boot-hangman.war"]