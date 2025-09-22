# ---- Build stage (JDK 23) ----
FROM eclipse-temurin:23-jdk AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test || (chmod +x gradlew && ./gradlew clean build -x test)

# ---- Run stage (JRE 23) ----
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
