# =========================
# 1. BUILD STAGE
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# copy source code
COPY . /app

# build jar
RUN mvn clean package -DskipTests


# =========================
# 2. RUNTIME STAGE
# =========================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# copy hasil build dari stage sebelumnya
COPY --from=build /app/target/*.jar app.jar

# run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
