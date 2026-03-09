
/**
 * Write a description of class Main here.
 * 
 * Current Problems:
 * Have I made a proper instance of CDArchive?
 * How do I call loadFromFile()?
 * addCD() also still not proper created
 *
 * @author Kanya Farley
 * @version 9/03/2026
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
    // instance variables
    CDArchive archive = new CDArchive();
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
        boolean active = true;
        archive.loadFromFile();
        while (active) {
            System.out.println("Add an album? Enter 'yes' or 'no'");
            String userInput = kb.nextLine();
            if (userInput.equals("yes") || userInput.equals("Yes") || userInput.equals("YES")) {
                System.out.println("Enter artist name!");
                String artistName = kb.nextLine();
                System.out.println("Enter album name for " + artistName);
                String albumName = kb.nextLine();
                System.out.println("What year was " + albumName + " by " + artistName + " released? Enter an integer.");
                int releaseYear = kb.nextInt();
                System.out.println("What's the run time (in seconds) for " + albumName + " (" + releaseYear + ") by " + artistName + "?");
                int runTime = kb.nextInt();
                kb.nextLine();
                
                CD tempCD = new CD(artistName, albumName, releaseYear, runTime);
                archive.addCD(tempCD);
            } else if (userInput.equals("no") || userInput.equals("No") || userInput.equals("NO")) {
                System.out.println("Okay! Here are all the albums you've entered: ");
                archive.displayAll();
                archive.saveToFile(" ");
                active = false;
            }
            
        }
    }
}
