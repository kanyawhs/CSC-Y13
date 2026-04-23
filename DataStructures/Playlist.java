
/**
 * Creates song objects
 * Doesn't quite work yet...
 *
 * @author Kanya Farley
 * @version 23/04
 */
import java.util.Scanner;
public class Playlist
{
    Song firstSong;
    Song prevSong;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class Playlist
     */
    public Playlist()
    {
        System.out.println("Enter title of first song: ");
        String firstSongTitle = kb.nextLine();
        firstSong = new Song(firstSongTitle);
        System.out.println("Enter artist name (or press enter to leave blank): ");
        String firstSongArtist = kb.nextLine();
        if (firstSongArtist.length() > 0) {
            firstSong.setArtist(firstSongArtist);
        }
        System.out.println("Enter album name (or press enter to leave blank): ");
        String firstSongAlbum = kb.nextLine();
        if (firstSongAlbum.length() > 0) {
            firstSong.setAlbum(firstSongAlbum);
        }
        System.out.println("Enter release year (or press enter to leave blank): ");
        String firstSongYear = kb.nextLine();
        if (firstSongYear.length() < 0) {
            firstSong.setYear(Integer.parseInt(firstSongYear));
        }
        firstSong.createPlaylist();
        prevSong = firstSong;
        
        boolean cont = true;
        while (cont) {
            System.out.println("Add another song? Enter 'y' or 'n'");
            String input = kb.nextLine();
            if (input.toLowerCase().equals("y")) {
                addSong();
            } else if (!(input.toLowerCase().equals("n")) && !(input.toLowerCase().equals("y"))) {
                System.out.println("Sorry, don't understand...");
            } else {
                cont = false;
            }
        }

        // prints songs from first to last
        Song temp = firstSong;
        while (temp.getPlaylist() != null) {
            System.out.println(temp.getName());
            if (temp.getArtist().length() == 0) {
                System.out.print(" by " + temp.getArtist());
            }
            if (temp.getAlbum().length() == 0) {
                System.out.print(", " + temp.getAlbum());
            }
            if (temp.getYear() == 0) {
                System.out.print(", " + temp.getYear());
            }
            temp = temp.getPlaylist();
        }
        System.out.println(temp.getName());
    }

    public Song addSong() {
        System.out.println("Enter title of song: ");
        String songTitle = kb.nextLine();
        Song song = new Song(songTitle);
        prevSong.getPlaylist().createPlaylist(song);
        System.out.println("Enter artist name (or press enter to leave blank): ");
        String songArtist = kb.nextLine();
        if (songArtist.length() > 0) {
            song.setArtist(songArtist);
        }
        System.out.println("Enter album name (or press enter to leave blank): ");
        String songAlbum = kb.nextLine();
        if (songAlbum.length() > 0) {
            song.setAlbum(songAlbum);
        }
        System.out.println("Enter release year (or press enter to leave blank): ");
        String songYear = kb.nextLine();
        if (kb.hasNextInt()) {
            song.setYear(Integer.parseInt(songYear));
        }
        prevSong = song;
        return(song);
    }
}
