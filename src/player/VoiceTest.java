package player;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.LyricListener;
import sound.SequencePlayer;


public class VoiceTest {
    /*
     * Testing strategy:
     * equals: We test equals() method to make sure it is reflexive, symmetric, transitive, and
 *  test to make sure it returns true when Voices are equal, and false otherwise.
     * addToPlayer (our only method besides constructor): attempt to add one Voice to a player,
     * then add multiple voices to the player
     */
	
	@Test
	public void equalsTestIsEqual() {
        // Create list of MusicLines
        Duration duration = new Duration(1, 2);
        Note note = new Note('A', -3, 2, duration);
        Rest rest = new Rest(duration);
        Note chordNote1 = new Note('A', -1, 1, duration);
        Note chordNote2 = new Note('A', -2, 0, duration);
        Note chordNote3 = new Note('C', 2, 2, duration);
        ArrayList<Note> chordList = new ArrayList<Note>();
        chordList.add(0, chordNote1);
        chordList.add(1,chordNote2);
        chordList.add(2, chordNote3);
        Chord chord = new Chord(chordList, duration);
        
        ArrayList<MusicElement> musicElementList1 = new ArrayList<MusicElement>();
        ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
        musicElementList1.add(note);
        musicElementList1.add(rest);
        musicElementList2.add(chord);
        Measure measure1 = new Measure(musicElementList1, false, false);
        Measure measure2 = new Measure(musicElementList2, false, false);
        ArrayList<Measure> measureList1 = new ArrayList<Measure>();
        ArrayList<Measure> measureList2 = new ArrayList<Measure>();
        measureList1.add(measure1);
        measureList2.add(measure2); 
        Lyric lyric1 = new Lyric("I", 1);
        Lyric lyric2 = new Lyric("love", 1);
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(lyric1);
        lyricList.add(lyric2);
        
        List<MusicLine> musiclines = new ArrayList<MusicLine>(); 
        MusicLine musicLine1 = new MusicLine("1", measureList1, lyricList);
        MusicLine musicLine2 = new MusicLine("2", measureList2, lyricList); 
        musiclines.add(musicLine2); 
        musiclines.add(musicLine1); 
        
        // Voices are created
        Voice voice1 = new Voice(musiclines);  
	    Voice voice2 = new Voice(musiclines);
	    Voice voice3 = new Voice(musiclines);
	    assertEquals(true, voice1.equals(voice1));
	    assertEquals(true, voice1.equals(voice2));
	    assertEquals(true, voice2.equals(voice1));
	    assertEquals(true, voice1.equals(voice2) && voice2.equals(voice3) && voice1.equals(voice3));
	}

	@Test
	public void equalsTestNotEqual() {
        // Create list of MusicLines
        Duration duration = new Duration(1, 2);
        Note note = new Note('A', -3, 2, duration);
        Rest rest = new Rest(duration);
        Note chordNote1 = new Note('A', -1, 1, duration);
        Note chordNote2 = new Note('A', -2, 0, duration);
        Note chordNote3 = new Note('C', 2, 2, duration);
        ArrayList<Note> chordList = new ArrayList<Note>();
        chordList.add(0, chordNote1);
        chordList.add(1,chordNote2);
        chordList.add(2, chordNote3);
        Chord chord = new Chord(chordList, duration);
        
        ArrayList<MusicElement> musicElementList1 = new ArrayList<MusicElement>();
        ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
        musicElementList1.add(note);
        musicElementList1.add(rest);
        musicElementList2.add(chord);
        Measure measure1 = new Measure(musicElementList1, false, false);
        Measure measure2 = new Measure(musicElementList2, false, false);
        ArrayList<Measure> measureList1 = new ArrayList<Measure>();
        ArrayList<Measure> measureList2 = new ArrayList<Measure>();
        measureList1.add(measure1);
        measureList2.add(measure2); 
        Lyric lyric1 = new Lyric("I", 1);
        Lyric lyric2 = new Lyric("love", 1);
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(lyric1);
        lyricList.add(lyric2);
        List<MusicLine> musiclines = new ArrayList<MusicLine>(); 
        MusicLine musicLine1 = new MusicLine("1", measureList1, lyricList);
        MusicLine musicLine2 = new MusicLine("2", measureList2, lyricList); 
        musiclines.add(musicLine1); 
        musiclines.add(musicLine2); 
        List<MusicLine> musiclines2 = new ArrayList<MusicLine>(); 
        musiclines2.add(musicLine1); //musicLines2 has musicLine1 but not musicLine2 -- this is why
        //the two voices are different
        // Voices are created
        Voice voice1 = new Voice(musiclines);  
	    Voice voice2 = new Voice(musiclines2);
	    assertEquals(false, voice1.equals(voice2));
	}
    @Test
    public void addToPlayerSingleVoiceTest() {
        // Create list of MusicLines
        Duration duration = new Duration(1, 2);
        Note note = new Note('A', -3, 2, duration);
        Rest rest = new Rest(duration);
        Note chordNote1 = new Note('A', -1, 1, duration);
        Note chordNote2 = new Note('A', -2, 0, duration);
        Note chordNote3 = new Note('C', 2, 2, duration);
        ArrayList<Note> chordList = new ArrayList<Note>();
        chordList.add(0, chordNote1);
        chordList.add(1,chordNote2);
        chordList.add(2, chordNote3);
        Chord chord = new Chord(chordList, duration);
        
        ArrayList<MusicElement> musicElementList1 = new ArrayList<MusicElement>();
        ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
        musicElementList1.add(note);
        musicElementList1.add(rest);
        musicElementList2.add(chord);
        Measure measure1 = new Measure(musicElementList1, false, false);
        Measure measure2 = new Measure(musicElementList2, false, false);
        ArrayList<Measure> measureList1 = new ArrayList<Measure>();
        ArrayList<Measure> measureList2 = new ArrayList<Measure>();
        measureList1.add(measure1);
        measureList2.add(measure2); 
        Lyric lyric1 = new Lyric("hi", 1);
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(lyric1);
        
        List<MusicLine> musiclines = new ArrayList<MusicLine>(); 
        MusicLine musicLine1 = new MusicLine("1", measureList1, lyricList);
        MusicLine musicLine2 = new MusicLine("2", measureList2, lyricList); 
        musiclines.add(musicLine2); 
        musiclines.add(musicLine1); 
        
        // Voice is created
        Voice voice = new Voice(musiclines);  
        
        // Add to player to test
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(12, 32, listener);
            voice.addToPlayer(player, 32); 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void addToPlayerMultipleVoicesTest() {
        // Create list of MusicLines
        Duration duration = new Duration(1, 2);
        //FIRST VOICE
        Note note = new Note('A', -3, 2, duration);
        Rest rest = new Rest(duration);
        Note chordNote1 = new Note('B', 0, 1, duration);
        Note chordNote2 = new Note('F', 1, 0, duration);
        Note chordNote3 = new Note('D', 1, 2, duration);
        ArrayList<Note> chordList = new ArrayList<Note>();
        chordList.add(0, chordNote1);
        chordList.add(1,chordNote2);
        chordList.add(2, chordNote3);
        Chord chord = new Chord(chordList, duration);
        
        ArrayList<MusicElement> musicElementList1 = new ArrayList<MusicElement>();
        ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
        musicElementList1.add(note);
        musicElementList1.add(rest);
        musicElementList2.add(chord);
        //create two measures
        Measure measure1 = new Measure(musicElementList1, false, false);
        Measure measure2 = new Measure(musicElementList2, false, false);
        ArrayList<Measure> measureList1 = new ArrayList<Measure>();
        ArrayList<Measure> measureList2 = new ArrayList<Measure>();
        measureList1.add(measure1);
        measureList2.add(measure2); 
        Lyric lyric1 = new Lyric("Hi", 1);
        ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
        lyricList.add(lyric1);
        
        List<MusicLine> musiclines = new ArrayList<MusicLine>(); 
        MusicLine musicLine1 = new MusicLine("1", measureList1, lyricList);
        MusicLine musicLine2 = new MusicLine("2", measureList2, lyricList); 
        musiclines.add(musicLine2); 
        musiclines.add(musicLine1); 
        
        // First voice is created
        Voice voice1 = new Voice(musiclines);  
        
        //SECOND VOICE
        Note note2 = new Note('C', 3, 2, duration);
        Rest rest2 = new Rest(duration);
        Note note3 = new Note('F', 2, 0, duration);
        
        ArrayList<MusicElement> musicElementList1b = new ArrayList<MusicElement>();
        ArrayList<MusicElement> musicElementList2b = new ArrayList<MusicElement>();
        musicElementList1b.add(note2);
        musicElementList1b.add(rest2);
        musicElementList2b.add(note3);
        //create two measures
        Measure measure1b = new Measure(musicElementList1b, false, false);
        Measure measure2b = new Measure(musicElementList2b, false, false);
        ArrayList<Measure> measureList1b = new ArrayList<Measure>();
        ArrayList<Measure> measureList2b = new ArrayList<Measure>();
        measureList1b.add(measure1b);
        measureList2b.add(measure2b); 
        List<MusicLine> musiclines2 = new ArrayList<MusicLine>(); 
        List<Lyric> lyricList2 = new ArrayList<Lyric>(); //no lyrics for second voice
        MusicLine musicLine1b = new MusicLine("1b", measureList1, lyricList2);
        MusicLine musicLine2b = new MusicLine("2b", measureList2, lyricList2); 
        musiclines2.add(musicLine1b); 
        musiclines2.add(musicLine2b); 
        
     // Second voice is created
        Voice voice2 = new Voice(musiclines2);  
        
        // Add to player to test
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(12, 32, listener);
            voice1.addToPlayer(player, 32); 
            voice2.addToPlayer(player, 32); 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }


}
