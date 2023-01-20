
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BackendMapper implements BackendMapperInterface{

  
  public ArrayList<ICapital> list;
  public CapitalLoader cap = new CapitalLoader();
  public GraphIterator GI = new GraphIterator();
  

  public BackendMapper(int max) {
	  GI.maxDist = (double) max;
	  GI.graph =  new CS400Graph<>();
	  this.createGraph();
  }
  
  public void addCity(capitalName city) {
	  GI.graph.insertVertex(city.getName());
  }

  public double getRoute(String city1, String city2) {
	
    return GI.graph.getPathCost(city1, city2);
  }
  
  public String getPath(String city1, String city2) {
    return GI.graph.shortestPath(city1, city2).toString();
  }
  
  public void createGraph() {
	  ArrayList<ICapital> capitals = new ArrayList<ICapital>();
      try {
			capitals = cap.loadCapitals("Capitals.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      GI.graphIterator(0.0, capitals);
  }
  

  public static void main(String[] args0) {
	  BackendMapper bm = new BackendMapper(500);
	  bm.createGraph();
	  bm.getRoute("Sacramento", "Albany");
	  bm.getPath("Sacramento", "Albany");
  }
}
