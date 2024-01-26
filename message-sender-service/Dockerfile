FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","message-sender-service", "&&", "useradd", "-r", "-g", "message-sender-service", "message-sender-service"]
ADD ./build/distributions/message-sender-service-1.0-SNAPSHOT.tar /app
CMD ["USER", "message-sender-service"]
CMD ["/app/message-sender-service-1.0-SNAPSHOT/bin/message-sender-service"]