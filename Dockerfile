
# build
FROM maven:3.9.9-ibm-semeru-21-jammy as builder

WORKDIR /opt/app
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

#start
FROM eclipse-temurin:23.0.1_11-jre
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT  ["java", "-jar", "/opt/app/*.jar"]



#FROM openjdk:17
#ADD /target/walletapp-0.0.1-SNAPSHOT.jar backend.jar
#ENTRYPOINT  ["java", "-jar", "backend.jar"]