FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","jpa-demo", "&&", "useradd", "-r", "-g", "jpa-demo", "jpa-demo"]
ADD ./build/distributions/jpa-demo-1.0-SNAPSHOT.tar /app
CMD ["USER", "jpa-demo"]
CMD ["/app/jpa-demo-1.0-SNAPSHOT/bin/jpa-demo"]