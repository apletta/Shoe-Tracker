.PHONY = make jar runjar test clean

CLASSPATH = .:junit-platform-console-standalone-1.3.2.jar:json-simple-1.1.1.jar

make: 
	javac -cp $(CLASSPATH) -d . application/*.java

run:
	javac -cp $(CLASSPATH) -d . application/*.java
	java -cp $(CLASSPATH) application.Main

jar: 
	javac -cp $(CLASSPATH) -d . application/*.java
	jar cvmf manifest.txt executable.jar .

runjar:
	java -jar executable.jar

zip:
	zip team.zip application/* *

test: 
	javac -cp $(CLASSPATH) *.java
	java -jar junit-platform-console-standalone-1.3.2.jar --class-path $(CLASSPATH) -p ""

clean:
	\rm application/*.class
	\rm executable.jar
