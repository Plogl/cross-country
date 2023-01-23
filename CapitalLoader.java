import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CapitalLoader implements ICapitalLoader {
    public CapitalLoader() {}//ICapitalLoader is a placeholder
    public ArrayList<ICapital> loadCapitals(String filepathToCSV) throws FileNotFoundException
    {
    ArrayList<ICapital> listofcapital = new ArrayList<>();
    File file = new File(filepathToCSV);
    Scanner scnr = new Scanner(file);
    scnr.useDelimiter("\n");
    int i=4;

    String skip = scnr.nextLine();
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] listOfLine = line.split(",");
            String state = listOfLine[0];
            String capital = listOfLine[1];
            Double latitude = Double.valueOf(listOfLine[2]);
            Double langitude = Double.valueOf(listOfLine[3]);
            /**
             * I'm not sure whether change the data.
             */
            ArrayList border = new ArrayList();
            while(i<listOfLine.length){
                //System.out.print(listOfLine[0]+listOfLine[i]+"\n");
                border.add(listOfLine[i]);
                i+=1;
            }
            i=4;
            Capital node = new Capital(state,capital,latitude,langitude,border);
            listofcapital.add(node);
        }
    return listofcapital;
    }

    public Double distance(String capital1, String capital2, ArrayList<ICapital> store){
        //Automatically transfer state to capitals
        Double x1 = -1.00;
        Double x2 = -1.00;
        Double y1 = -1.00;
        Double y2 = -1.00;
        for(ICapital i:store){
            if(i.getState().equals(capital1)||i.getCapital().equals(capital1)){
                x1=Double.valueOf(i.getLatitude());
                y1=Double.valueOf(i.getLongitude());
            }
            if(i.getState().equals(capital2)||i.getCapital().equals(capital2)){
                x2=Double.valueOf(i.getLatitude());
                y2=Double.valueOf(i.getLongitude());
            }
        }
        if(x1.equals(-1.00)||x2.equals(-1.00)){
            throw new RuntimeException("Check Your Input Please");
        }
        Double result = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        return result;
    }


    public static void main(String[] args){
        CapitalLoader capitalLoader1 = new CapitalLoader();
        //try{capitalLoader1.loadCapitals("/Users/littlerookie/Desktop/JavaProject/P3/src/Capitals.csv");}
        try{capitalLoader1.loadCapitals("src/Capitals.csv");}
        catch (Exception e){System.out.print("File not found");}
        }

}
