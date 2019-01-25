FROM openjdk:8-jre
COPY ./build/libs/*.jar /tmp
EXPOSE 8080
CMD java -Xms256m -Xmx4096m -jar -Dgrails.env=default -Dserver.port=8080  -Duser.timezone=Asia/Hongkong /tmp/*.jar
