// --== CS400 Fall 2022 File Header Information ==--
// Name: Oliver Bai
// Email: obai2@wisc.edu
// Team: DY
// TA: Sujitha
// Lecturer: Professor Florian
// Notes to Grader: Nothing :)


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmEngineerTests {

    private CS400Graph<String, Integer> graph;
    ArrayList<ICapital> testList;
    GraphIterator gi = new GraphIterator();
    CapitalLoader cl = new CapitalLoader();
    BackendMapper bm;

    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        testList = new ArrayList<ICapital>();

    }

    @Test
    //Testing if the graph is storing objects correctly
    public void testIndividual1()
    {

        ArrayList<String> Filler = new ArrayList<>();
        gi.maxDist = 1000000;
        Capital city1 = new Capital("Greg","China", 100.0, 100.0, Filler);
        testList.add(city1);
        Capital city2 = new Capital("Tim", "USA", 200.0, 200.0, Filler);
        testList.add(city2);
        gi.graphIterator(gi.maxDist, testList);
        assertTrue(gi.graph.containsEdge("China", "USA") == true);
    }

    //Testing to make sure vertex's are properly saved when run through graphIterator
    @Test
    public void testIndividual2()
    {
        ArrayList<String> Filler = new ArrayList<>();
        gi.maxDist = 1000000;
        Capital city1 = new Capital("Hi!", "China", 100.0, 100.0, Filler);
        testList.add(city1);
        Capital city2 = new Capital("Teemo", "USA", 200.0, 200.0, Filler);
        testList.add(city2);
        gi.graphIterator(gi.maxDist, testList);
        assertTrue(gi.graph.containsVertex("USA") == true);
    }
    //Testing if the program correctly only creates edges for capitals within 1000 km
    @Test
    public void testIndividual3()
    {
        ArrayList<String> Filler = new ArrayList<>();
        gi.maxDist = 1000;
        Capital city1 = new Capital("Grog", "Chicago", 41.9, -87.6, Filler);
        testList.add(city1);
        Capital city2 = new Capital("Grog", "New York", 40.7, -74.0, Filler);
        testList.add(city2);
        Capital city3 = new Capital("Grog", "Columbus", 40.0, -82.3, Filler);
        testList.add(city3);
        gi.graphIterator(gi.maxDist, testList);
        assertTrue(gi.graph.shortestPath("Chicago", "New York").toString().equals(
                "[Chicago, Columbus, New York]"
        ));
    }
    //Tests to make sure that the getPathCost and getDistanceLatLong
    @Test
    public void testIndividual4()
    {
        ArrayList<String> Filler = new ArrayList<>();
        gi.maxDist = 1000;
        Capital city1 = new Capital("Grog", "Chicago", 41.9, -87.6, Filler);
        testList.add(city1);
        Capital city2 = new Capital("Grog", "New York", 40.7, -74.0, Filler);
        testList.add(city2);
        Capital city3 = new Capital("Grog", "Columbus", 40.0, -82.3, Filler);
        testList.add(city3);
        gi.graphIterator(gi.maxDist, testList);
        assertTrue(gi.graph.getPathCost("Chicago", "New York") ==
                1199.9680153148743);
    }

    //Test to make sure duplicate nodes are removed
    @Test
    public void testIndividual5()
    {
        ArrayList<String> Filler = new ArrayList<>();
        gi.maxDist = 1000;
        Capital city1 = new Capital("Grog", "Chicago", 41.9, -87.6, Filler);
        testList.add(city1);
        Capital city2 = new Capital("Grog", "New York", 40.7, -74.0, Filler);
        testList.add(city2);
        Capital city3 = new Capital("Grog", "Columbus", 40.0, -82.3, Filler);
        testList.add(city3);
        Capital city4 = new Capital("Grog", "Columbus", 40.0, -82.3, Filler);
        testList.add(city4);
        gi.graphIterator(gi.maxDist, testList);
        assertTrue(gi.graph.getVertexCount() == 3);
    }
    //Tests proper functionality and implementation of DataWrangler methods and backend methods
    //by creating a path with maximum 800 kilometers between each stop, which is a recommended "minimum"
    //for our application
    @Test
    public void testImplementation1()
    {
        bm = new BackendMapper(800);
        bm.createGraph();

        assertTrue(bm.getPath("Sacramento", "Albany").equals(
                "[Sacramento, Boise, Salt Lake City, Denver, Lincoln," +
                        " Springfield, Columbus, Trenton, Albany]"
        ) );
    }
//  Although this test may look like the test above, it's results are important as they'll show that
//  changes in info given to backend, the max distance, is properly being passed, and doesn't change
//  the ending node, and properly shrinks and adjusts the route
    @Test
    public void testImplementation2()
    {
        bm = new BackendMapper(1000);
        bm.createGraph();
        assertTrue(bm.getPath("Sacramento", "Albany").equals(
                "[Sacramento, Boise, Cheyenne, Des Moines, Lansing, Albany]"
        ) );
    }

    //checks the first node to make sure that they have proper latitude, longitudes,
    //capitals, state name, and border states.
    @Test
    public void testPartner1()
    {
        ArrayList<String> borTest = new ArrayList<>();
        borTest.add("Mississippi");
        borTest.add("Tennessee");
        borTest.add("Florida");
        borTest.add("Georgia");
        ArrayList<ICapital> capTest = new ArrayList<ICapital>();
        try {
            capTest = cl.loadCapitals("src/Capitals.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Capital city1 = new Capital("Alabama", "Montgomery", 32.377716, 86.300568, borTest);
        boolean checkEquals = true;
        if (capTest.get(0).getLatitude().equals(city1.getLatitude()))
        {
            if (capTest.get(0).getLongitude().equals(city1.getLongitude()))
            {
                if (capTest.get(0).getCapital().equals(city1.getCapital()))
                {
                    if (capTest.get(0).getState().equals(city1.getState()))
                    {
                        if (capTest.get(0).getborder().equals(city1.getborder()))
                        {
                            checkEquals = true;
                        }
                        else
                        {
                            checkEquals = false;
                        }
                    }
                    else
                    {
                        checkEquals = false;
                    }
                }
                else
                {
                    checkEquals = false;
                }
            }
            else
            {
                checkEquals = false;
            }
        }
        else
        {
            checkEquals = false;
        }
        assertTrue(checkEquals == true);
    }

    //Makes sure DataWrangler has proper coordinates, as there should be no way to make a trip
    //from Sacramento to Boise in 400km. I use BackendMapper just to make the testing process
    //cleaner
    @Test
    public void testPartner2()
    {
        boolean check;
        bm = new BackendMapper(400);
        bm.createGraph();
        try{
            bm.getPath("Sacramento", "Boise");
            check = true;
        }
        catch(NoSuchElementException e)
        {
            check = false;
        }
        assertTrue(check == false);
    }
}
