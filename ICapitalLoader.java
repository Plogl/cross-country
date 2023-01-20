import java.io.FileNotFoundException;
import java.util.List;
/**
 * Instances of this interface can be used to load book data from a CSV file.
**/
public interface ICapitalLoader {
    /**
     *
     * @param //filepathToXTML
     * @return a list of capital's states, state, altitude, latitude, border
     * @throws //FileNotFoundException
     */
    List<ICapital> loadCapitals(String filepathToCSV) throws FileNotFoundException;
}
