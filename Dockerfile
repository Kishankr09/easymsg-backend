FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# 🔥 Give permission to mvnw
RUN chmod +x mvnw

# 🔥 Build project
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/easymsg-1.0.0.jar"]
