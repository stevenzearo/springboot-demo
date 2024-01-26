FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","no-sql-demo", "&&", "useradd", "-r", "-g", "no-sql-demo", "no-sql-demo"]
ADD ./build/distributions/no-sql-demo-1.0-SNAPSHOT.tar /app
CMD ["USER", "no-sql-demo"]
CMD ["/app/no-sql-demo-1.0-SNAPSHOT/bin/no-sql-demo"]