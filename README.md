# java-based-kafka

Instructions to set up and run the application

1. JDK 17 install (this is spring boot 3.0.0 project there is required at least jdk 17 and above version)
2. Install maven (this is maven based project)
3. Install intellij
4. build the project with( mvn clean install)
5. Run the project with (spring-boot:run)

KAFKA
1. Install kafka & zookeper (download apache kafka binary file)
2. then extract 
3. and go to bin window directory

Commands
Start Zookeper
   C:\kafka_2.13-3.9.0\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties

Start kafka server
C:\kafka_2.13-3.9.0\bin\windows>kafka-server-start.bat ..\..\config\server.properties

Consumer
C:\kafka_2.13-3.9.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic json-place-holder --from beginning