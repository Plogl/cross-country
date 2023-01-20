
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DataWranglerTests extends CapitalLoader {
    private CS400Graph<String, Integer> graph;
    ArrayList<ICapital> testList;
    GraphIterator gi = new GraphIterator();
    CapitalLoader cl = new CapitalLoader();
    BackendMapper running;

    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        testList = new ArrayList<ICapital>();

    }
    /**
     * Test for the CSV implement including Size, Borders number, Random State's name
     * @throws FileNotFoundException
     */
    @Test
    public void test1() throws FileNotFoundException {//Test for the placeholder from Data Drawl, this is a database to store the Players from xml;
        CapitalLoader test1 = new CapitalLoader();
        ArrayList<ICapital> store = new ArrayList<>();
        store = test1.loadCapitals("Capitals.csv");
        assertTrue(store.size()==50 && store.get(0).getborder().size()==4
        && store.get(21).getState().equals("Michigan"));

    }

    @Test
    /**
     * Try to output all the Capitals
     */
    public void test2() throws FileNotFoundException {
        CapitalLoader test2 = new CapitalLoader();
        ArrayList<ICapital> store = new ArrayList<>();
        store = test2.loadCapitals("Capitals.csv");
        int i=0;
        try{while (i<50)
            {
                i+=1;
            }
            assertTrue(true);
        }
        catch(Exception e) {assertFalse(true);}
    }
    @Test
    /**
     * Do the double floating calculation for find the distance between two nodes
     */
    public void test3() throws FileNotFoundException {
        Double number;
        CapitalLoader test3 = new CapitalLoader();
        ArrayList<ICapital> store = new ArrayList<>();
        store = test3.loadCapitals("Capitals.csv");
        try{
            number= Math.sqrt(Math.pow(store.get(4).getLatitude(),2)+Math.pow(store.get(4).getLongitude(),2));
            assertTrue(true);
        }
        catch(Exception e) {assertFalse(true);}
    }
    @Test
    /**
     * Test for the distance calculation
     */
    public void test4() throws FileNotFoundException {
        Double number;
        CapitalLoader test4 = new CapitalLoader();
        ArrayList<ICapital> store = new ArrayList<>();
        store = test4.loadCapitals("Capitals.csv");
        try{
            number= test4.distance("Michigan","Wisconsin",store);;
            assertTrue(true);
        }
        catch(Exception e) {assertFalse(true);}
    }
    @Test
    /**
     * Test for input the error data
     */
    public void test5() throws FileNotFoundException {
        Double number;
        CapitalLoader test5= new CapitalLoader();
        ArrayList<ICapital> store = new ArrayList<>();
        store = test5.loadCapitals("Capitals.csv");
        try{
            number= test5.distance("Sun","Chase",store);
            assertFalse(true);
        }
        catch(Exception e) {
            assertTrue(true);}
    }

    @Test
    public void testIntegration1()
    {
        boolean check;
        running = new BackendMapper(400);

        try{
            running.getPath("Springfield", "Concord");
            check = true;
        }
        catch(NoSuchElementException e)
        {
            check = false;
        }

        assertTrue(check == true);
    }
    @Test
    public void testIntegration2()
    {
        ArrayList<String> borderTest = new ArrayList<>();
        //Maryland,Annapolis,38.978764,76.490936,Virginia,West Virginia,Delaware,Pennsylvania
        borderTest.add("Virginia");
        borderTest.add("West Virginia");
        borderTest.add("Delaware");
        borderTest.add("Pennsylvania");
        ArrayList<ICapital> capTest = new ArrayList<ICapital>();
        try {
            capTest = cl.loadCapitals("Capitals.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int idx = -1;
        for(int x = 0; x < capTest.size(); x++) {
        	if(capTest.get(x).getState().equals("Maryland")) {
        		idx = x;
        	}
        }
        Capital city1 = new Capital("Maryland", "Annapolis", 38.978764, 76.490936, borderTest);
        boolean checkEquals = true;
        if (capTest.get(idx).getLatitude().equals(city1.getLatitude()))
        {
            if (capTest.get(idx).getLongitude().equals(city1.getLongitude()))
            {
                if (capTest.get(idx).getCapital().equals(city1.getCapital()))
                {
                    if (capTest.get(idx).getState().equals(city1.getState()))
                    {
                        if (capTest.get(idx).getborder().equals(city1.getborder()))
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




    @Test
    public void testCodeReviewOfAlgorithmEngineer1()
    {
        running = new BackendMapper(800);
        running.createGraph();

        assertTrue(running.getPath("Sacramento", "Albany").equals(
                "[Sacramento, Boise, Salt Lake City, Denver, Lincoln," +
                        " Springfield, Columbus, Trenton, Albany]"
        ) );
    }
    @Test
    public void testCodeReviewOfAlgorithmEngineer2()
    {
        running = new BackendMapper(800);
        running.createGraph();

        assertTrue(running.getPath("Sacramento", "Albany").equals(
                "[Sacramento, Boise, Salt Lake City, Denver, Lincoln," +
                        " Springfield, Columbus, Trenton, Albany]"
        ) );
    }
}
