WPATH = "E:\Ming-Yeh Chou\Computer Science\Software Studio\lab04"
CPATH = .;.\gson.jar


all: *.java
	javac -classpath $(CPATH) *.java -Xlint
	jar -cfm PokedexGUI.jar manifest.mf *.class
	java -jar PokedexGUI.jar
clean: *.class
	del *.class