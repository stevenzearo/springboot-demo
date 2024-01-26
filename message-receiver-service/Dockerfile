FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","message-receiver-service", "&&", "useradd", "-r", "-g", "message-receiver-service", "message-receiver-service"]
ADD ./build/distributions/message-receiver-service-1.0-SNAPSHOT.tar /app
CMD ["USER", "message-receiver-service"]
CMD ["/app/message-receiver-service-1.0-SNAPSHOT/bin/message-receiver-service"]