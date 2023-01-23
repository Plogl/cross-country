import java.util.List;

// This is the interface for backend
public interface BackendMapperInterface {



/* This method will return the shortest route
 */
    public void addCity(capitalName city);

/* This method set the filter for the cities(node)
 */
    public double getRoute(String city1, String city2);

/*
remove all city (node)
 */
    public String getPath(String city1, String city2);



}
