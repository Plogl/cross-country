import java.util.ArrayList;

public class Capital implements ICapital{
    String state;
    public String getState() {
        return state;
    }
    String capital;
    public String getCapital() {
        return capital;
    }
    Double latitude;
    public Double getLatitude() {
        return latitude;
    }
    Double langitude;
    public Double getLongitude() {
        return langitude;
    }
    ArrayList<String> border = new ArrayList<>();
    public ArrayList<String> getborder() {
        return border;
    }
    public Capital(String state,String capital,Double latitude,Double langitude,ArrayList<String> border){
        this.state = state;
        this.capital = capital;
        this.latitude = latitude;
        this.langitude = langitude;
        this.border = border;
    }
}
