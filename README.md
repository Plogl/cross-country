# cross country
 
-A test run of this code can be found as Cross-Country-Example.gif, and tests as JUnit-Tests-Example.gif-

A trip planner that uses capital cities as stops between your starting and ending city. 

I was the backend and algorithm developer for this project. I created files with 'AlgorithmEngineer' and "Backend" as the prefix, 
GraphADT.java, and created the AlgorithmEngineerTests.java, which is a set of JUnit tests for the program. 

This application will allow the user to start from a user-selected US capital and the user will choose which US capital they would like to visit. 
From the selected starting capital, the program takes the latitude and longitude of every US capital city, and attempts to draw edges between them that fit the given max distance provided by the user.
If a distance falls outside of the maximum range provided, no edge is drawn. Once a graph is created, Dijkstra's is used to find the shortest path that can be taken
to reach your destination in the shortest distance.  

To run this code, download all folders into a file, and use a JavaFX compatible compiler. Make sure JavaFX is installed properly, and to look at tests make sure JUnit is installed properly. 
I wouldn't recommend going through all the hassle, so I've included example run throughs of the code working as a whole, and of my JUnit tests.


An example run of code can be found under Book-Catalogue-Example.gif.