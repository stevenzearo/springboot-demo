FROM openjdk:17-jdk-alpine
CMD ["groupadd", "-r","mybatis-demo", "&&", "useradd", "-r", "-g", "mybatis-demo", "mybatis-demo"]
ADD ./build/distributions/mybatis-demo-1.0-SNAPSHOT.tar /app
CMD ["USER", "mybatis-demo"]
CMD ["/app/mybatis-demo-1.0-SNAPSHOT/bin/mybatis-demo"]