package player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.LyricListener;
import sound.SequencePlayer;

 /*
  * Testing strategy:
  * hasNextLyric: test case when there is a next lyric, and when the lyrics are empty
  * 
  * addNextLyric: test to make sure several syllables are added correctly.  Verify by looking at 
  * lyrics printed out in console.
  * 
  * toString: test several different lyric objects (with different lengths/types of characters) and 
  * make sure they are correctly represented as a string.
  */

public class LyricIteratorTest {
    //Test hasNextLyric method
    @Test
    public void basicHasNextLyricTest() {
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(new Lyric("I", 1));
        lyricList.add(new Lyric("lo", 1));
        lyricList.add(new Lyric("ove", 1));
        LyricsIterator lyricsIt = new LyricsIterator(lyricList); 
        assertTrue(lyricsIt.hasNextLyric()); 
    }
    
    @Test
    public void hasNextLyricEmptyTest() {
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        LyricsIterator lyricsIt = new LyricsIterator(lyricList); 
        assertTrue(!lyricsIt.hasNextLyric()); 
    }
    
    
    //Test addNextLyrics method
    @Test
    public void addNextLyricTest() { 
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(new Lyric("I", 1));
        lyricList.add(new Lyric("lo", 2));
        LyricsIterator lyricsIt = new LyricsIterator(lyricList);
     
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(12, 32, listener);
            // Add to player to test
            lyricsIt.addNextLyric(player, 0);
            lyricsIt.addNextLyric(player, 1); 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
    
    //Test toString
    @Test
    public void toStringLyricTest() {
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(new Lyric("I", 1));
        lyricList.add(new Lyric("lo-", 2));
        lyricList.add(new Lyric("ove", 1));
        lyricList.add(new Lyric("|", 1));
        LyricsIterator lyricsIt = new LyricsIterator(lyricList);
        assertEquals(" LYRIC: I Length = 1 LYRIC: lo- Length = 2 LYRIC: ove Length = 1 LYRIC: | Length = 1", lyricsIt.toString()); 
    }
}
