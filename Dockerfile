FROM fabric8/java-centos-openjdk8-jdk:1.5.4


 ADD ./mngr.jar /root/mngr.jar

 EXPOSE 10080

 ENTRYPOINT ["java","-jar","-server", "/root/mngr.jar"]
