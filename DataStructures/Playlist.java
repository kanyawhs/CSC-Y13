
/**
 * Creates song objects
 * Doesn't quite work yet...
 *
 * @author Kanya Farley
 * @version 30/04
 */
import java.util.Scanner;
public class Playlist
{
    Song firstSong;
    Song prevSong;
    Scanner kb = new Scanner(System.in);
    Song temp;
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
        prevSong = firstSong;
        option();
    }

    public void option() {
        boolean cont = true;
        while (cont) {
            System.out.println("Add another song? Enter 'y' or 'n'");
            String input = kb.nextLine();
            if (input.toLowerCase().equals("y")) {
                addSong();
                option();
            } else if (input.toLowerCase().equals("n")) {
                printPlaylist();
                cont = false;
            } else {
                System.out.println("Sorry, don't understand...");
            }
        }      
    }

    public void addSong() {
        System.out.println("Enter title of song: ");
        String songTitle = kb.nextLine();
        Song song = new Song(songTitle);
        prevSong.setNextSong(song);
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
        if (songYear.length() < 0) {
            song.setYear(Integer.parseInt(songYear));
        }
        prevSong = song;
    }

    public void printPlaylist() { // doesn't quite work right yet
        // prints songs from first to last
        Song temp = prevSong;
        while (temp.getNextSong() != null) {
            System.out.print(temp.getName());
            if (temp.getArtist().length() > 0) {
                System.out.print(" by " + temp.getArtist());
            }
            if (temp.getAlbum().length() > 0) {
                System.out.print(", " + temp.getAlbum());
            }
            if (temp.getYear() > 0) {
                System.out.print(", " + temp.getYear() + "\n");
            }
            temp = temp.getNextSong();
        }
        System.out.println(temp.getName());
    }
}
