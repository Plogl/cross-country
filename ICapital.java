import java.util.ArrayList;
public interface ICapital {
    /**
     * Return the state Name
     */
    String getState();
    /**
     * Return the Capital Name
     */
    String getCapital();
    /**
     * Return the altitude
     */
    Double getLatitude();
    /**
     * Return the longitude
     */
    Double getLongitude();
    /**
     * Return borders
     */
    ArrayList<String> getborder();
}
