# docker run -d --name mngr -p 10080:10080 --add-host mysql.kunlong.com:172.18.47.32 mngr

FROM anapsix/alpine-java

ARG JAR_FILE

ADD ./mngr.jar /root/mngr.jar

EXPOSE 10080
# RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone


ENV JAVA_OPTS "-server -Xms256M -Xmx1024M -Xss1M -XX:MinHeapFreeRatio=30 -XX:MaxHeapFreeRatio=50 -XX:MaxMetaspaceSize=256M -XX:MinMetaspaceFreeRatio=20 -XX:MaxMetaspaceFreeRatio=30 -Duser.timezone=GMT+08"


ENTRYPOINT ["java", "-Duser.timezone=GMT+08","-jar","-server", "/root/mngr.jar"]
