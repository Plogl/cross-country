runTests: tests.class others.class
	java --module-path ./javafx-sdk-19/lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar --include-classname=.* -c FrontEndDeveloperRoleTests
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=backendtests
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

tests.class: FrontEndDeveloperRoleTests.java FrontendMapper.class
	javac --module-path ./javafx-sdk-19/lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontEndDeveloperRoleTests.java AlgorithmEngineerTests.java backendtests.java DataWranglerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	javac -cp .:junit5.jar backendtests.java
	javac -cp .:junit5.jar DataWranglerTests.java
	

run: FrontendMapper.class others.class
	java --module-path ./javafx-sdk-19/lib/ --add-modules javafx.controls FrontendMapper

FrontendMapper.class: FrontendMapper.java
	javac --module-path ./javafx-sdk-19/lib/ --add-modules javafx.controls FrontendMapper.java

others.class: BackendMapperInterface.java BackendMapper.java FrontendMapperInterface.java ICapital.java Capital.java CapitalLoader.java CS400Graph.java GraphADT.java GraphIterator.java CapitalLoader.java
	javac BackendMapperInterface.java
	javac BackendMapper.java
	javac FrontendMapperInterface.java
	javac ICapital.java
	javac Capital.java
	javac CapitalLoader.java
	javac CS400Graph.java
	javac GraphADT.java
	javac GraphIterator.java
	javac ICapitalLoader.java
	
clean:
	rm -rf *.class
