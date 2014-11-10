package player;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import grammar.ABCMusicLexer;

import java.io.File;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

import sound.LyricListener;
import sound.SequencePlayer;


public class MusicLineTest {
/*Testing strategy:
 * 
 * equals: We test equals() method to make sure it is reflexive, symmetric, transitive, and
 *  test to make sure it returns true when MusicLines are equal, and false otherwise.
 * (This also tests our equals methods for Note, Chord, Measure, Lyric, and Rest.)
 * 
 * getMeasures: construct a MusicLine, then make sure getLyrics returns the correct list of Measures
 * associated with the MusicLine.
 * 
 * countNotesInMeasureRangeInclusive : construct a music line with a certain number of notes/chords, 
 * and make sure that countNotesInMeasureRangeInclusive returns the correct number of notes/chords.
 * 
 * getLyrics: construct a musicLine with some lyrics, then make sure getLyrics returns the correct lyrics.
 * 
 * addToPlayer: add a musicLine (consisting of a Note, Rest, and Chord) to the player, then play it to test it
 */
	
	//equals tests
	@Test
	public void equalsTestIsEqual() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		MusicLine musicLine2 = new MusicLine("default", measureList, lyricList);
		assertEquals(true, musicLine1.equals(musicLine2)); 
	}
	
	
	@Test
	public void equalsTestReflexive() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		assertEquals(true, musicLine1.equals(musicLine1)); 
	}
	
	@Test
	public void equalsTestSymmetric() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		MusicLine musicLine2 = new MusicLine("default", measureList, lyricList);
		assertEquals(true, musicLine1.equals(musicLine2) && musicLine2.equals(musicLine1)); 
	}
	
	@Test
	public void equalsTestTrasitive() {
		Duration duration = new Duration(3, 4);
		//first measure
		//note
		Note note = new Note('A', -1, 2, duration);
		//rest
		Rest rest = new Rest(duration);
		//chord
		Note chordNote1 = new Note('B', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		//list of music elements in first measure
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		//create first measure
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//second measure
		Note note2 = new Note('A', -1, 2, duration);
		Rest rest2 = new Rest(duration);
		Note chordNote4 = new Note('B', -1, 2, duration);
		Note chordNote5 = new Note('A', 0, 2, duration);
		Note chordNote6 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList2 = new ArrayList<Note>();
		chordList2.add(0, chordNote4);
		chordList2.add(1,chordNote5);
		chordList2.add(2, chordNote6);
		Chord chord2 = new Chord(chordList2, duration);
		ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
		musicElementList2.add(note2);
		musicElementList2.add(rest2);
		musicElementList2.add(chord2);
		Measure measure2 = new Measure(musicElementList2, false, false);
		ArrayList<Measure> measureList2 = new ArrayList<Measure>();
		measureList2.add(measure2);
		//third measure
		Note note3 = new Note('A', -1, 2, duration);
		Rest rest3 = new Rest(duration);
		Note chordNote7 = new Note('B', -1, 2, duration);
		Note chordNote8 = new Note('A', 0, 2, duration);
		Note chordNote9 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList3 = new ArrayList<Note>();
		chordList3.add(0, chordNote7);
		chordList3.add(1,chordNote8);
		chordList3.add(2, chordNote9);
		Chord chord3 = new Chord(chordList3, duration);
		ArrayList<MusicElement> musicElementList3 = new ArrayList<MusicElement>();
		musicElementList3.add(note3);
		musicElementList3.add(rest3);
		musicElementList3.add(chord3);
		Measure measure3 = new Measure(musicElementList3, false, false);
		ArrayList<Measure> measureList3 = new ArrayList<Measure>();
		measureList3.add(measure3);
		//lyrics
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		ArrayList<Lyric> lyricList2 = new ArrayList<Lyric>();
		ArrayList<Lyric> lyricList3 = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		lyricList2.add(lyric1);
		lyricList2.add(lyric2);
		lyricList3.add(lyric1);
		lyricList3.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		MusicLine musicLine2 = new MusicLine("default", measureList2, lyricList2);
		MusicLine musicLine3 = new MusicLine("default", measureList3, lyricList3);
		assertEquals(true, musicLine1.equals(musicLine2) && musicLine2.equals(musicLine3) && musicLine1.equals(musicLine3)); 
	}
	
	@Test
	public void equalsTestNameNotEqual() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		MusicLine musicLine2 = new MusicLine("deFault", measureList, lyricList);
		assertEquals(false, musicLine2.equals(musicLine1));
	}
	
	@Test
	public void equalsTestMeasuresNotEqual() {
		Duration duration = new Duration(1, 2);
		//first measure
		//note
		Note note = new Note('A', -1, 2, duration);
		//rest
		Rest rest = new Rest(duration);
		//chord
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		//list of music elements in first measure
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		//create first measure
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//second measure
		Note note2 = new Note('A', -1, 2, duration);
		Rest rest2 = new Rest(duration);
		Note chordNote4 = new Note('B', -1, 2, duration);
		Note chordNote5 = new Note('A', 0, 2, duration);
		Note chordNote6 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList2 = new ArrayList<Note>();
		chordList.add(0, chordNote4);
		chordList.add(1,chordNote5);
		chordList.add(2, chordNote6);
		Chord chord2 = new Chord(chordList2, duration);
		ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
		musicElementList2.add(note2);
		musicElementList2.add(rest2);
		musicElementList2.add(chord2);
		Measure measure2 = new Measure(musicElementList2, false, false);
		ArrayList<Measure> measureList2 = new ArrayList<Measure>();
		measureList2.add(measure2);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricList);
		MusicLine musicLine2 = new MusicLine("default", measureList2, lyricList);
		assertEquals(false, musicLine1.equals(musicLine2));
	}
	
	
	@Test
	public void equalsTestLyricsNotEqual() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricListA = new ArrayList<Lyric>();
		lyricListA.add(lyric1);
		lyricListA.add(lyric2);
		Lyric lyric3 = new Lyric("Hi!", 1);
		Lyric lyric4 = new Lyric("world", 1);
		ArrayList<Lyric> lyricListB = new ArrayList<Lyric>();
		lyricListB.add(lyric3);
		lyricListB.add(lyric4);
		MusicLine musicLine1 = new MusicLine("default", measureList, lyricListA);
		MusicLine musicLine2 = new MusicLine("default", measureList, lyricListB);
		assertEquals(false, musicLine1.equals(musicLine2));
	}
	
	//getMeasures tests
	@Test 
	public void getMeasuresTest() {
		Duration duration = new Duration(1, 2);
		Note note = new Note('A', -1, 2, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('A', -1, 2, duration);
		Note chordNote2 = new Note('A', 0, 2, duration);
		Note chordNote3 = new Note('C', 1, -1, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("Hi", 1);
		Lyric lyric2 = new Lyric("world", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine = new MusicLine("default", measureList, lyricList);
		List<Measure> output = musicLine.getMeasures();
		assertEquals(output, measureList);
	}
	
	//countNotesInMeasureRangeInclusive tests
	@Test
	public void countNotesInMeasureRangeInclusiveTest() {
		Duration duration = new Duration(3, 1);
		Note note = new Note('G', 0, 0, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('D', 0, -1, duration);
		Note chordNote2 = new Note('B', 1, 1, duration);
		Note chordNote3 = new Note('E', 2, 0, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("What's", 1);
		Lyric lyric2 = new Lyric("up", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine = new MusicLine("default", measureList, lyricList);
		int output = musicLine.countNotesBeforeBeginningOfMeasure(1);
		assertEquals(output, 2);
	}
	
	//getLyrics tests
	@Test
	public void getLyricsTest() {
		Duration duration = new Duration(3, 1);
		Note note = new Note('G', 0, 0, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('D', 0, -1, duration);
		Note chordNote2 = new Note('B', 1, 1, duration);
		Note chordNote3 = new Note('E', 2, 0, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("What's", 1);
		Lyric lyric2 = new Lyric("up", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine = new MusicLine("default", measureList, lyricList);
		List<Lyric> output = musicLine.getLyrics();
		assertEquals(lyricList, output);
	}
	
	//addToPlayer test
	@Test
	public void addToPlayerTest() {
		Duration duration = new Duration(3, 1);
		Note note = new Note('G', 0, 0, duration);
		Rest rest = new Rest(duration);
		Note chordNote1 = new Note('D', 0, -1, duration);
		Note chordNote2 = new Note('B', 1, 1, duration);
		Note chordNote3 = new Note('E', 2, 0, duration);
		ArrayList<Note> chordList = new ArrayList<Note>();
		chordList.add(0, chordNote1);
		chordList.add(1,chordNote2);
		chordList.add(2, chordNote3);
		Chord chord = new Chord(chordList, duration);
		ArrayList<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note);
		musicElementList.add(rest);
		musicElementList.add(chord);
		Measure measure = new Measure(musicElementList, false, false);
		ArrayList<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		Lyric lyric1 = new Lyric("What's", 1);
		Lyric lyric2 = new Lyric("up", 1);
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		MusicLine musicLine = new MusicLine("default", measureList, lyricList);
    
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(100, 8, listener);
    		musicLine.addToPlayer(player, 0, 8);
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
		
	}
	
}
