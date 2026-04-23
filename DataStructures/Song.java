
/**
 * Blueprint for creating songs + a playlist
 *
 * @author Kanya Farley
 * @version 23/04
 */
public class Song
{
    private String name;
    private String artist;
    private String album;
    private int year;
    
    Song song;
    /**
     * Constructor for objects of class Song
     */
    public Song()
    {
        this.name = "untitled";
    }

    /** Constructor for a song with a name */
    public Song(String name) {
        this.name = name;
    }

    /* setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    /* getters */
    public String getName() {
        return(this.name);
    }
    
    public String getArtist() {
        return(this.artist);
    }
    
    public String getAlbum() {
        return(this.album);
    }
    
    public int getYear() {
        return(this.year);
    }
    
    /* create playlist */
    public void createPlaylist() {
        this.song = new Song();
    }
    
    public void createPlaylist(Song firstSong) {
        this.song = song;
    }
    
    /* set and get playlist */
    public void setPlaylist(Song song) {
        this.song = song;
    }
    
    public Song getPlaylist() {
        return(this.song);
    }
}
