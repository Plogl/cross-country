
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class backendtests {
  capitalName testCities;
  BackendMapper testBackend;
  String city1, city2, city3, city4, city5, city6, city7, city8;
    @BeforeEach
    public void createCities() {
       city1 = new String("Madison");
       city2 = new String("Springfield");
       city3 = new String("Lansing");
       city4 = new String("Atlanta");
       city5 = new String("Sacramento");
       city6 = new String("Pierre");
       city7 = new String("Augusta");
       city8 = new String("Columbia");
      testBackend = new BackendMapper(800);
      
    }
    
    //test1 return true if it get path
    @Test
    public void test1() {
      assertEquals(testBackend.getPath(city1, city2),"[Madison, Springfield]");

    }
    
    //test2 return true if it get path
    @Test
    public void test2() {
     assertEquals(testBackend.getPath(city3, city4), "[Lansing, Frankfort, Atlanta]");

    }
    
    //test3 return true if it get path
    @Test
    public void test3() {
      assertEquals(testBackend.getPath(city5, city6), "[Sacramento, Boise, Salt Lake City, Cheyenne, Pierre]");
    }
    
    //test4 return true if gets route
    @Test
    public void test4() {
      assertEquals(testBackend.getRoute(city1, city2), 365.0069102057742);
    }
    
    //test5 return true if gets route
    @Test
    public void test5() {
    	assertEquals(testBackend.getRoute(city3, city4), 1001.7073400346962);
    }
    
    //intergration test1 return true if gets path
    @Test
    public void intergrationtest1() {
      assertEquals(testBackend.getPath(city5, city6), "[Sacramento, Boise, Salt Lake City, Cheyenne, Pierre]");
    }
    
    //intergration test2 return true if gets route
    @Test
    public void intergrationtest2() {
      //assertEquals(testBackend.getRoute(city7, city8));
    }
    
    //partner test1 return true if gets path
    @Test
    public void partnertest1() {
      String cityThree = new String("Lincoin");
      String cityFour = new String("Austin");
      //assertEquals(testBackend.getPath(cityThree, cityThree));
    }
    
    //partner test2 return true if gets route
    @Test
    public void partnertest2() {
      String city = new String("Topeka");
      String cityTwo = new String("Helena");
      //assertEquals(testBackend.getRoute(city, cityTwo));
    }
    


}
