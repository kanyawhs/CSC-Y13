
/**
 * Creates objects for CD class and stores in a file
 * 
 * Current problems:
 * Don't know how to call method to add CD to array list
 *
 * @author Kanya Farley
 * @version 9/03/2026
 */
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
public class CDArchive
{
    private ArrayList<CD> albums = new ArrayList<CD>();
    /**
     * Constructor for objects of class CDArchive
     */
    public CDArchive()
    {
        CD Wake = new CD("Hail The Sun", "Wake", 2014, 3122); // initializes object on its own
    }

    void saveToFile (String CDs) {
        File myFile = new File ("CDs.csv");
        try {
            FileWriter writer = new FileWriter(myFile);
            for (CD thisCD : albums) {
                writer.write(thisCD.getArtistName() + ", " +
                thisCD.getAlbumName() + ", " +
                thisCD.getReleaseYear() + ", " +
                thisCD.getRunTime() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Sorry! Couldn't write file");
        }
    }

    void loadFromFile () {
        try {
            File myFile = new File ("CDs.csv");
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                String line = read.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void addCD(CD tempCD) {
        albums.add(tempCD);
    }
    
    void displayAll() {
        for (CD currentCD: albums) {
            //currentCD.toString();
            System.out.println(currentCD.toString());
        }
    }
}
