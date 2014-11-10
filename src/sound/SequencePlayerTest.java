package sound;

import static org.junit.Assert.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

/**
 * Test some things without Didit
 * 
 * @category no_didit
 */

public class SequencePlayerTest {

    // Test for warm-up piece1
    @Test
    public void testPiece1() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player;
        // Create a new player, with 140 beats per minute, 48 ticks per beat
        // and a LyricListener that prints each lyric that it sees.
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        // Add the notes of piece1
        player = new SequencePlayer(140, 48, listener);
        
        // add a whole note
        player.addNote(new Pitch('C').toMidiNote(), 0, 48);
        
        // another whole note
        player.addNote(new Pitch('C').toMidiNote(), 48, 48);
        
        // add 3/4 note
        player.addNote(new Pitch('C').toMidiNote(), 96, 36);
        
        // add 1/4 note 
        player.addNote(new Pitch('D').toMidiNote(), 132, 12);
        player.addNote(new Pitch('E').toMidiNote(), 144, 48);
        // end measure 1 
        
        player.addNote(new Pitch('E').toMidiNote(), 192, 36);
        player.addNote(new Pitch('D').toMidiNote(), 228, 12);
        player.addNote(new Pitch('E').toMidiNote(), 240, 36);
        player.addNote(new Pitch('F').toMidiNote(), 276, 12);
        
        // add note with duration of 2 beats 
        player.addNote(new Pitch('G').toMidiNote(), 288, 96);
        // end measure 2 
        
        // 4 triplets
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 384, 16);
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 400, 16);
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 416, 16);
        player.addNote(new Pitch('G').toMidiNote(), 432, 16);
        player.addNote(new Pitch('G').toMidiNote(), 448, 16);
        player.addNote(new Pitch('G').toMidiNote(), 464, 16);
        player.addNote(new Pitch('E').toMidiNote(), 480, 16);
        player.addNote(new Pitch('E').toMidiNote(), 496, 16);
        player.addNote(new Pitch('E').toMidiNote(), 512, 16);
        player.addNote(new Pitch('C').toMidiNote(), 528, 16);
        player.addNote(new Pitch('C').toMidiNote(), 544, 16);
        player.addNote(new Pitch('C').toMidiNote(), 560, 16);
        // end measure 3
        
        player.addNote(new Pitch('G').toMidiNote(), 576, 36);
        player.addNote(new Pitch('F').toMidiNote(), 612, 12);
        player.addNote(new Pitch('E').toMidiNote(), 624, 36);
        player.addNote(new Pitch('D').toMidiNote(), 660, 12);
        player.addNote(new Pitch('C').toMidiNote(), 672, 96);
        // end measure 4
        
        player.play();
    }
    
    @Test
    public void testPiece2() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player;
        // Create a new player, with 200 beats per minute, 24 ticks per beat
        // and a LyricListener that prints each lyric that it sees.
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        // Add the notes of piece2
        player = new SequencePlayer(200, 24, listener);
        
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 0, 12);
        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 0, 12);

        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 12, 12);
        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 12, 12);
        
        // now a 1/8 note rest
        
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 36, 12);
        
        // another 1/8 note rest
        
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 60, 12);
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 60, 12);
        
        player.addNote(new Pitch('F').accidentalTranspose(1).toMidiNote(), 72, 24);
        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 72, 24);
        
        // end measure 1
        
        player.addNote(new Pitch('G').toMidiNote(), 96, 24);
        player.addNote(new Pitch('B').toMidiNote(), 96, 24);
        player.addNote(new Pitch('A').octaveTranspose(1).toMidiNote(), 96, 24);
        
        // 1/4 note rest
        
        player.addNote(new Pitch('G').toMidiNote(), 144, 24);
        
        // 1/4 note rest
        
        // end measure 2
        
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 192, 36);
        
        player.addNote(new Pitch('G').toMidiNote(), 228, 12);
        
        // 1/4 note rest
        
        player.addNote(new Pitch('E').toMidiNote(), 264, 24);
        
        // end measure 3
        
        player.addNote(new Pitch('E').toMidiNote(), 288, 12);
        
        player.addNote(new Pitch('A').toMidiNote(), 300, 24);
        
        player.addNote(new Pitch('B').toMidiNote(), 324, 24);
        
        player.addNote(new Pitch('B').accidentalTranspose(1).toMidiNote(), 348, 12);
        
        player.addNote(new Pitch('A').toMidiNote(), 360, 24);
        
        // end measure 4

        // triplet
        player.addNote(new Pitch('G').toMidiNote(), 384, 16);

        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 400, 16);
        
        player.addNote(new Pitch('G').octaveTranspose(1).toMidiNote(), 416, 16);
        // end triplet
        
        player.addNote(new Pitch('A').octaveTranspose(1).toMidiNote(), 432, 24);
        
        player.addNote(new Pitch('F').octaveTranspose(1).toMidiNote(), 456, 12);
        
        player.addNote(new Pitch('G').octaveTranspose(1).toMidiNote(), 468, 12);
        
        // end measure 5. that was a toughie.
        
        // 1/8 note rest here
        
        player.addNote(new Pitch('E').octaveTranspose(1).toMidiNote(), 492, 24);
        
        player.addNote(new Pitch('C').octaveTranspose(1).toMidiNote(), 516, 12);
        
        player.addNote(new Pitch('D').octaveTranspose(1).toMidiNote(), 528, 12);
        
        player.addNote(new Pitch('B').toMidiNote(), 540, 18);

        player.play();
    
    }
    
    @Test
    public void testPiece3() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player;
        // Create a new player, with 100 beats per minute, 2 ticks per beat
        // and a LyricListener that prints each lyric that it sees.
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        // Add the notes of piece3
        player = new SequencePlayer(100, 2, listener);
        
        // Begins with a 2-beat rest, so first note starts at tick 4.
        
        player.addNote(new Pitch('D').toMidiNote(), 4, 2);
        player.addLyricEvent("A", 4);
        
        // end measure 1
        
        player.addNote(new Pitch('G').toMidiNote(), 6, 4);
        player.addLyricEvent("ma", 6);
        
        player.addNote(new Pitch('B').toMidiNote(), 10, 1);
        player.addLyricEvent("zing", 10);

        player.addNote(new Pitch('G').toMidiNote(), 11, 1);
        
        // end measure 2

        player.addNote(new Pitch('B').toMidiNote(), 12, 4);
        player.addLyricEvent("grace!", 12);

        player.addNote(new Pitch('A').toMidiNote(), 16, 2);
        player.addLyricEvent("How", 16);
        
        // end measure 3
        
        player.addNote(new Pitch('G').toMidiNote(), 18, 4);
        player.addLyricEvent("sweet", 18);

        player.addNote(new Pitch('E').toMidiNote(), 22, 2);
        player.addLyricEvent("the", 22);

        
        // end measure 4
        
        player.addNote(new Pitch('D').toMidiNote(), 24, 4);
        player.addLyricEvent("sound", 24);
        
        player.addNote(new Pitch('D').toMidiNote(), 28, 2);
        player.addLyricEvent("That", 28);

        // end measure 5
        
        player.addNote(new Pitch('G').toMidiNote(), 30, 4);
        player.addLyricEvent("saved", 30);
        
        player.addNote(new Pitch('B').toMidiNote(), 34, 1);
        player.addLyricEvent("a", 34);

        player.addNote(new Pitch('G').toMidiNote(), 35, 1);
        
        // end measure 6
        
        player.addNote(new Pitch('B').toMidiNote(), 36, 4);
        player.addLyricEvent("wretch", 36);

        player.addNote(new Pitch('A').toMidiNote(), 40, 2);
        player.addLyricEvent("like", 40);

        // end measure 7
        
        player.addNote(new Pitch('D').octaveTranspose(1).toMidiNote(), 42, 8);
        player.addLyricEvent("me.", 42);
        
        player.play();

    }
}
