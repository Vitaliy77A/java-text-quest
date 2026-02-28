FROM tomcat:10.1.50-jdk25
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/java-text-quest-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080