
/**
 * This is the blueprint for CD objects
 * 
 * Current problems:
 * not sure how to do toString method yet
 *
 * @author Kanya Farley
 * @version 06/03/2026
 */
public class CD
{
    // instance variables
    private String artistName;
    private String albumName;
    int releaseYear;
    int runTime; // in mins
    double rating; // 0.0 to 5.0
    double value; // $

    /**
     * Constructor for objects of class CD
     */
    public CD(String artistName, String albumName, int releaseYear, int runTime)
    {
        this.artistName = artistName;
        this.albumName = albumName;
        this.releaseYear = releaseYear;
        this.runTime = runTime;
    }
    
    public String getArtistName() {
        return(this.artistName);
    }
    
    public String getAlbumName() {
        return(this.albumName);
    }
    
    public int getReleaseYear() {
        return(this.releaseYear);
    }
    
    public int getRunTime() {
        return(this.runTime);
    }
    
    public String toString() {
        String CDinfo = ("Artist name : " + this.artistName + ", Album name: " + this.albumName + ", release year: " + this.releaseYear + ", run time in seconds: " + this.runTime);
        return(CDinfo);
    }
}
