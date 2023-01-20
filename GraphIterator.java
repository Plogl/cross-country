// --== CS400 Fall 2022 File Header Information ==--
// Name: Oliver Bai
// Email: obai2@wisc.edu
// Team: DY
// TA: Sujitha
// Lecturer: Professor Florian
// Notes to Grader: Hope the comments are alright

import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

//Wrote all my code here to keep it more organized and separate from the CS400Graph.java
public class GraphIterator {
    //creates an instance of CS400Graph for testing
    CS400Graph<String, Double> graph = new CS400Graph<>();
    //sets the max distance you are willing to travel (in kilometers), set to 1000 km default
    double maxDist = 1000;

    //calculates distance from given latitude and longitude pairs
    double getDistanceLatLong(double lat1, double long1, double lat2, double long2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below, basic trig
        double dLon = deg2rad(long2-long1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }
    //conversion for above equation, degrees to radian
    double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    //takes a given list that will be provided by the Data Wrangler of Capital objects and inserts them into
    //a given graph, in this case we generated a graph above as we aren't sure when it will be called
    //will change return type to CS400Graph if needed, teammates weren' sure
    public void graphIterator(Double maxDistance, ArrayList<ICapital> list)
    {
        for (int i = 0; i < list.size(); i++) //goes through the given list and inserts all vertices
        {
            graph.insertVertex(list.get(i).getCapital());//duplicate check occurs in CS400 insertVertex method
        }
        for (int i = 0; i < list.size(); i++)//run through the list again, this time checking if an edge can be created
        {
            //second iterator, to make sure every capital is compared to every other capital
            for (int j = 0; j < list.size(); j++)
            if (getDistanceLatLong(list.get(i).getLatitude(), list.get(i).getLongitude(),
                    list.get(j).getLatitude(), list.get(j).getLongitude()) <= maxDist &&
                    i != j) //makes sure edges are made only when within max distance travelled
            {
                //inserts edge with distance calculated from calculation method
                graph.insertEdge(list.get(i).getCapital(), list.get(j).getCapital(),
                        getDistanceLatLong(list.get(i).getLatitude(), list.get(i).getLongitude(),
                                list.get(j).getLatitude(), list.get(j).getLongitude()));
            }
        }
    }
    public static void main(String[] args0)
    {
        GraphIterator b = new GraphIterator();
        CapitalLoader cap = new CapitalLoader();
        ArrayList<ICapital> capitals = new ArrayList<ICapital>();
        try {
			capitals = cap.loadCapitals("src/Capitals.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        b.graphIterator(0.0, capitals);
        System.out.println(b.graph.shortestPath("Chicago", "New York").toString());
        System.out.println(b.graph.getPathCost("Chicago", "New York"));
    }
}
