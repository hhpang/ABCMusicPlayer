package player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import sound.LyricListener;
import sound.SequencePlayer;

public class MeasureTest {
    
	/*
	 * Testing strategy:
	 * Equals: We test equals() method to make sure it is reflexive, symmetric, transitive, and
	 * test to make sure it returns true when durations are equal, and false otherwise.
	 * 
	 * CountNotes: we count notes in an empty measure, a measure with only notes, a measure with rests and notes,
	 * and a measure with chords, rests, and notes
	 * 
	 * AddToPlayer: We test both addToPlayer methods -- the one that uses lyrics and the one that doesn't
	 * 
	 * isBeginRepeat tests: we test a case in which isBeginRepeat should return true, and a case in which it should
	 * return false.
	 * 
	 * isEndRepeat tests: analogous to isBeginRepeat tests
	 * 
	 * getElements: we do a simple test to make sure that getElements returns the list of elements contained in the measure
	 * 
	 * getDeepCopy: we do a simple test of taking a deep copy of a measure, and checking to see that the copy 
	 * equals the original
	 * 
	 */
	//equals tests
	
	@Test
	public void measuresAreEqual() {
		Duration duration = new Duration(3, 4);
		//first measure
		//note
		Note note = new Note('B', 0, 0, duration);
		//rest
		Rest rest = new Rest(duration);
		//chord
		Note chordNote1 = new Note('G', 0, -2, duration);
		Note chordNote2 = new Note('D', 0, 0, duration);
		Note chordNote3 = new Note('F', 3, 0, duration);
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
		Measure measure1 = new Measure(musicElementList, false, false);
		//second measure
		//note
		Note note2 = new Note('B', 0, 0, duration);
		//rest
		Rest rest2 = new Rest(duration);
		//chord
		Note chordNote4 = new Note('G', 0, -2, duration);
		Note chordNote5 = new Note('D', 0, 0, duration);
		Note chordNote6 = new Note('F', 3, 0, duration);
		ArrayList<Note> chordList2 = new ArrayList<Note>();
		chordList2.add(0, chordNote4);
		chordList2.add(1,chordNote5);
		chordList2.add(2, chordNote6);
		Chord chord2 = new Chord(chordList2, duration);
		ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
		musicElementList2.add(note2);
		musicElementList2.add(rest2);
		musicElementList2.add(chord2);
		//create second measure
		Measure measure2 = new Measure(musicElementList2, false, false);
		//third measure
		//note
		Note note3 = new Note('B', 0, 0, duration);
		//rest
		Rest rest3 = new Rest(duration);
		//chord
		Note chordNote7 = new Note('G', 0, -2, duration);
		Note chordNote8 = new Note('D', 0, 0, duration);
		Note chordNote9 = new Note('F', 3, 0, duration);
		ArrayList<Note> chordList3 = new ArrayList<Note>();
		chordList3.add(0, chordNote7);
		chordList3.add(1,chordNote8);
		chordList3.add(2, chordNote9);
		Chord chord3 = new Chord(chordList3, duration);
		ArrayList<MusicElement> musicElementList3 = new ArrayList<MusicElement>();
		musicElementList3.add(note3);
		musicElementList3.add(rest3);
		musicElementList3.add(chord3);
		//create third measure
		Measure measure3 = new Measure(musicElementList3, false, false);
		assertEquals(true, measure1.equals(measure1)); //reflexivity
		assertEquals(true, measure1.equals(measure2)); //test that measure1 equals measure2 
		assertEquals(true, measure2.equals(measure1)); //symmetry
		assertEquals(true, measure1.equals(measure2) && measure2.equals(measure3) && measure1.equals(measure3)); //transitivity
	}
	
	@Test
	public void measuresNotEqual() {
		Duration duration = new Duration(3, 4);
		//first measure
		//note
		Note note = new Note('B', 0, 0, duration);
		//rest
		Rest rest = new Rest(duration);
		//chord
		Note chordNote1 = new Note('G', 0, -2, duration);
		Note chordNote2 = new Note('D', 0, 0, duration);
		Note chordNote3 = new Note('F', 3, 0, duration);
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
		Measure measure1 = new Measure(musicElementList, false, false);
		//second measure
		//SECOND MEASURE
		//note
		Note note2 = new Note('B', 1, 0, duration); // this note is different
		//rest
		Rest rest2 = new Rest(duration);
		//chord
		Note chordNote4 = new Note('G', 0, -2, duration);
		Note chordNote5 = new Note('D', 0, 0, duration);
		Note chordNote6 = new Note('F', 3, 0, duration);
		ArrayList<Note> chordList2 = new ArrayList<Note>();
		chordList2.add(0, chordNote4);
		chordList2.add(1,chordNote5);
		chordList2.add(2, chordNote6);
		Chord chord2 = new Chord(chordList2, duration);
		ArrayList<MusicElement> musicElementList2 = new ArrayList<MusicElement>();
		musicElementList2.add(note2);
		musicElementList2.add(rest2);
		musicElementList2.add(chord2);
		//create second measure
		Measure measure2 = new Measure(musicElementList2, false, false);
		assertEquals(false, measure1.equals(measure2));
	}
	
    
    
    
    //countNotes tests 
    @Test
    public void countNotesEmptyElementsTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>(); 
        Measure measure = new Measure(elements, false, false);
        assertEquals(0, measure.countNotes()); 
    }
    
    @Test
    public void countNotesAllNotesTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('A', 0, 3, new Duration("1")));
        elements.add(new Note('B', 1, -1, new Duration("2")));
        Measure measure = new Measure(elements, false, false);
        assertEquals(2, measure.countNotes()); 
    }
    
    @Test
    public void countNotesChordTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        List<Note> chordList = new ArrayList<Note>();
        Note note1 = new Note('A', 0, 3, new Duration("1"));
        Note note2 = new Note('B', 1, -1, new Duration("2"));
        chordList.add(note1);
        chordList.add(note2);
        Chord chord = new Chord(chordList, new Duration("1"));
        elements.add(chord);
        Measure measure = new Measure(elements, false, false);
        assertEquals(1, measure.countNotes()); 
    }
    
    @Test
    public void countNotesChordsNotesAndRestTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        List<Note> chordList = new ArrayList<Note>();
        Note note1 = new Note('F', 2, 0, new Duration("/3"));
        Note note2 = new Note('C', 1, 0, new Duration("/3"));
        chordList.add(note1);
        chordList.add(note2);
        Chord chord = new Chord(chordList, new Duration("1/3"));
        elements.add(chord);
        elements.add(new Note('E', 0, -1, new Duration("2")));
        elements.add(new Note('C', 2, -2, new Duration("/4")));
        elements.add(new Rest(new Duration("/3")));
        Measure measure = new Measure(elements, false, false);
        assertEquals(3, measure.countNotes()); 
    }
    
    
    //isBeginRepeat and isEndRepeat tests
    
    @Test
    public void beginRepeatTrueTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('A', 0, 3, new Duration("1")));
        elements.add(new Note('B', 1, -1, new Duration("2")));
        Measure measure = new Measure(elements, true, false);
        assertEquals(true, measure.isBeginRepeat());
    }
    
    @Test
    public void beginRepeatFalseTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('A', 0, 3, new Duration("1")));
        elements.add(new Note('B', 1, -1, new Duration("2")));
        Measure measure = new Measure(elements, false, true);
        assertEquals(false, measure.isBeginRepeat());
    }
    
    @Test
    public void endRepeatTrueTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('A', 0, 3, new Duration("1")));
        elements.add(new Note('B', 1, -1, new Duration("2")));
        Measure measure = new Measure(elements, false, true);
        assertEquals(true, measure.isEndRepeat());
    }
    
    @Test
    public void endRepeatFalseTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('A', 0, 3, new Duration("1")));
        elements.add(new Note('B', 1, -1, new Duration("2")));
        Measure measure = new Measure(elements, true, false);
        assertEquals(false, measure.isEndRepeat());
    }
    
    //addToPlayer tests
    @Test
    public void addToPlayerLyricsInMeasureTest() {  //prints out "Hel-lo there" in console
        List<Lyric> lyrics =  new ArrayList<Lyric>(); 
        lyrics.add(new Lyric("Hel",1)); 
        lyrics.add(new Lyric("lo", 1)); 
        lyrics.add(new Lyric("there", 2)); 
        LyricsIterator lyricIterator = new LyricsIterator(lyrics); 
        
        List<MusicElement> elements = new ArrayList<MusicElement>(); 
        elements.add(new Note('C',0,1, new Duration(1,2))); 
        elements.add(new Note('G',0,-1, new Duration(1,2))); 
        elements.add(new Note('C',1,0, new Duration(1,2))); 
        elements.add(new Note('A',1,1, new Duration(1,2))); 
        Measure measure = new Measure(elements, false, false);
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
        SequencePlayer player = new SequencePlayer(32, 32, listener);
        measure.addToPlayer(player, 0, 32, lyricIterator);  
        player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    //Test getting deep copy
    public void getDeepCopyTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>(); 
        List<Note> notes = new ArrayList<Note>(); 
        notes.add(new Note('A',0,1, new Duration(1,4))); 
        notes.add(new Note('B',-1,-1, new Duration(3,4))); 
        notes.add(new Note('C',1,0, new Duration(1,8))); 
        elements.add(new Chord(notes, new Duration(1,4))); 
        elements.add(new Note('D',1,0, new Duration(1,16))); 
        Measure measure = new Measure(elements, false, false);
        Measure measureCopy = measure.getDeepCopy(); 
        assertTrue(measureCopy.equals(measure)); 
    }
    
    
  //add different music elements to measure, then add to player
    @Test
    public void addToPlayerDifferentMusicElementsTest() { 
    	//doesn't print out anything in console, but plays two notes (with a rest between them)
        List<MusicElement> elements = new ArrayList<MusicElement>(); 
        List<Note> notes = new ArrayList<Note>(); 
        notes.add(new Note('C',0,1, new Duration(1,2))); 
        notes.add(new Note('G',0,-1, new Duration(1,2))); 
        notes.add(new Note('C',1,0, new Duration(1,2))); 
        elements.add(new Chord(notes, new Duration(1,4)));
        elements.add(new Rest(new Duration(1, 3)));
        elements.add(new Note('A',1,1, new Duration(1,4))); 
        Measure measure = new Measure(elements, false, false);
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
        SequencePlayer player = new SequencePlayer(32, 32, listener);
        measure.addToPlayer(player, 0, 32); 
        player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
    //getElements test
    
    @Test 
    public void getElementsTest() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('B', 0, 1, new Duration("/2")));
        elements.add(new Note('E', 1, -1, new Duration("2/7")));
        elements.add(new Rest(new Duration("/")));
        Measure measure = new Measure(elements, true, false);
        assertEquals(elements, measure.getElements());
    }
    
    
    //getDeepCopy test
    
    @Test
    public void getDeepCopy2Test() {
        List<MusicElement> elements = new ArrayList<MusicElement>();
        elements.add(new Note('B', 0, 1, new Duration("/2")));
        elements.add(new Note('E', 1, -1, new Duration("2/7")));
        elements.add(new Rest(new Duration("/")));
        Measure measure = new Measure(elements, true, false);
        assertEquals(measure, measure.getDeepCopy());
    }
    
}
