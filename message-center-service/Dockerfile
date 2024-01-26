FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","message-center-service", "&&", "useradd", "-r", "-g", "message-center-service", "message-center-service"]
ADD ./build/distributions/message-center-service-1.0-SNAPSHOT.tar /app
CMD ["USER", "message-center-service"]
CMD ["/app/message-center-service-1.0-SNAPSHOT/bin/message-center-service"]