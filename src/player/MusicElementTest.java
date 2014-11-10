package player;
import org.junit.Test;

import sound.LyricListener;
import sound.SequencePlayer;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MusicElementTest {
	
	/*
	 * Testing strategy:
	 * 
	 * isRest: test on an object from each class (Note, Chord, Rest)
	 * 
	 * addToPlayer: test on an object from each class (Note, Chord, Rest)
	 * 
	 * toString: test on an object from each class (Note, Chord, Rest)
	 * 
	 * equals: test the equals method in each class to make sure it is reflexive, symmetric, and transitive,
	 * as well as that it returns false when objects are not equal
	 * 
	 * elementWithMultipliedDuration: test on an object from each class (Note, Chord, Rest)
	 */
	Duration oneHalf = new Duration(1, 2);
	Rest rest = new Rest(oneHalf);
	Note note = new Note('A', 0, 1, oneHalf);
	Note secondNote = new Note('C', -1, 2, oneHalf);
	
	//isRest tests
	
	@Test
	public void testRestIsRest() {
   		assertEquals(rest.isRest(), true);
    }
    
   	@Test
	public void testNoteIsRest() {
   		assertEquals(note.isRest(), false);
    }
   	
   	@Test
	public void testChordIsRest() {
   		List<Note> chordList = new ArrayList<Note>();
   		chordList.add(note);
   		chordList.add(secondNote);
   		Chord chord = new Chord(chordList, oneHalf);
   		assertEquals(chord.isRest(), false);
    }
   	
   	//addToPlayer tests
   	@Test 
    public void addNoteToPlayerTest() {
        int currentCount = 0;
        int ticksPerBeat = 64;
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(32, ticksPerBeat, listener);
            Note note = new Note('C', 1, 0, new Duration(1, 4));
            note.addToPlayer(player, currentCount, ticksPerBeat);
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
   	
    @Test 
    public void addRestToPlayerTest() {
        int ticksPerBeat = 64;
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(32, ticksPerBeat, listener);
            Note note = new Note('D', 1, 0, new Duration(1,4));
            int count1 = note.addToPlayer(player, 0, ticksPerBeat);
            Rest rest = new Rest(new Duration(1,4));
            int count2 = rest.addToPlayer(player,count1,ticksPerBeat); 
            Note note2 = new Note('D', 1, 0, new Duration(1,4));
            note2.addToPlayer(player, count2, ticksPerBeat);
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
   	
    @Test 
    public void addChordToPlayerTest() {
        int ticksPerBeat = 64;
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(32, ticksPerBeat, listener);
            List<Note> notes = new ArrayList<Note>(); 
            notes.add(new Note('C',0,0, new Duration(1,2))); 
            notes.add(new Note('G',0,0, new Duration(1,2))); 
            notes.add(new Note('C',1,0, new Duration(1,2))); 
            Chord chord = new Chord(notes, new Duration(1,4)); 
            chord.addToPlayer(player, 0, ticksPerBeat); 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    
   	//toString tests
    @Test
    public void noteToStringTest() { 
        Note note = new Note('A', 1, 1, new Duration(1,1)); 
        assertEquals("Note(A,1,1,1.0)", note.toString());
    }
    @Test
    public void restToStringTest() { 
        Rest rest = new Rest(new Duration(1,2)); 
        assertEquals("Rest(0.5)", rest.toString());
    }
    @Test
    public void chordToStringTest() { 
        List<Note> notes = new ArrayList<Note>(); 
        notes.add(new Note('C', 1, 0, new Duration(1,2)));
        notes.add(new Note('D', -1, 1, new Duration(1,2)));
        notes.add(new Note('E', -1, 0, new Duration(1,4)));
        Chord chord = new Chord(notes, new Duration(1,2)); 
        assertEquals("Chord(Note(C,1,0,0.5),Note(D,-1,1,0.5),Note(E,-1,0,0.25),0.5)", chord.toString());
    }
    
    //equals Tests
    
    @Test
    public void noteEqualsNote(){
    	Note note1 = new Note('A', 0, 1, new Duration(1,2));
    	Note note2 = new Note('A', 0, 1, new Duration(1,2));
    	assertEquals(true, note1.equals(note2));
    }
    
    @Test
    public void noteNotEqualNote(){
    	Note note1 = new Note('A', 0, 1, new Duration(1,2));
    	Note note2 = new Note('B', 0, 1, new Duration(1,2));
    	assertEquals(false, note1.equals(note2));
    }
   	
    @Test
    public void noteNotEqualRest() {
    	Note note1 = new Note('A', 0, 1, new Duration(1,2));
    	Rest rest1 = new Rest(new Duration(1,2));
    	assertEquals(false, note1.equals(rest1));
    }
    
    @Test
    public void restEqualsRest(){
    	Rest rest1 = new Rest(new Duration(1,2));
    	Rest rest2 = new Rest(new Duration(1,2));
    	assertEquals(true, rest2.equals(rest1));
    }
    
    @Test
    public void restNotEqualRest(){
    	Rest rest1 = new Rest(new Duration(1,3));
    	Rest rest2 = new Rest(new Duration(1,2));
    	assertEquals(false, rest2.equals(rest1));
    }
    
    @Test
    public void restNotEqualChord() {
    	Rest rest1 = new Rest(new Duration(1,3));
    	Note note1 = new Note('B', 0, -1, new Duration(3,4));
    	Note note2 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList = new ArrayList<Note>();
    	chordList.add(note1);
    	chordList.add(note2);
    	Chord chord1 = new Chord(new ArrayList<Note>(chordList), new Duration(3,4));
    	assertEquals(false, rest1.equals(chord1));
    }
    
    @Test
    public void chordEqualChord(){
    	//first chord
    	Note note1 = new Note('B', 0, -1, new Duration(3,4));
    	Note note2 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList = new ArrayList<Note>();
    	chordList.add(note1);
    	chordList.add(note2);
    	Chord chord1 = new Chord(new ArrayList<Note>(chordList), new Duration(3,4));
    	//second chord
    	Note note3 = new Note('B', 0, -1, new Duration(3,4));
    	Note note4 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList2 = new ArrayList<Note>();
    	chordList2.add(note3);
    	chordList2.add(note4);
    	Chord chord2 = new Chord(new ArrayList<Note>(chordList2), new Duration(3,4));
    	assertEquals(true, chord2.equals(chord1));
    }
    
    @Test
    public void chordNotEqualChord(){
    	//first chord
    	Note note1 = new Note('B', 0, -1, new Duration(3,4));
    	Note note2 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList = new ArrayList<Note>();
    	chordList.add(note1);
    	chordList.add(note2);
    	Chord chord1 = new Chord(new ArrayList<Note>(chordList), new Duration(3,4));
    	//second chord
    	Note note3 = new Note('C', 0, -1, new Duration(3,4));
    	Note note4 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList2 = new ArrayList<Note>();
    	chordList.add(note3);
    	chordList.add(note4);
    	Chord chord2 = new Chord(new ArrayList<Note>(chordList2), new Duration(3,4));
    	assertEquals(false, chord2.equals(chord1));
    }
    
    @Test
    public void chordNotEqualNote(){
    	//first chord
    	Note note1 = new Note('B', 0, -1, new Duration(3,4));
    	Note note2 = new Note('C', 1, 2, new Duration(3,4));
    	ArrayList<Note> chordList = new ArrayList<Note>();
    	chordList.add(note1);
    	chordList.add(note2);
    	Chord chord1 = new Chord(new ArrayList<Note>(chordList), new Duration(3,4));
    	//second chord
    	Note note3 = new Note('B', 0, -1, new Duration(3,4));
    	assertEquals(false, note3.equals(chord1));
    }
    
   	//elementWithMultipliedDuration tests
    @Test
    public void noteMulDurTest() { 
        Note note = new Note('G',1,0,new Duration(1,2)); 
        MusicElement newNote = note.elementWithMultipliedDuration(new Duration(1,4)); 
        assertTrue(newNote.getDuration().toDouble() == .125); 
    }
    @Test
    public void restMulDurTest() { 
        Rest rest = new Rest(new Duration(1,2)); 
        MusicElement newRest = rest.elementWithMultipliedDuration(new Duration(1,2)); 
        assertTrue(newRest.getDuration().toDouble() == .25); 
    }
    @Test
    public void chordMulDurTest() { 
        List<Note> notes = new ArrayList<Note>(); 
        notes.add(new Note('C', 1, 1, new Duration(1,16)));
        notes.add(new Note('D', -1, 1, new Duration(1,8)));
        notes.add(new Note('E', 1, 0, new Duration(1,2)));
        Chord chord = new Chord(notes, new Duration(1,4)); 
        MusicElement newChord = chord.elementWithMultipliedDuration(new Duration(1,4)); 
        assertTrue(newChord.getDuration().toDouble() == 0.0625); 
    }
}
    