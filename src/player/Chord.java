package player;

import java.util.ArrayList;
import java.util.List;

import sound.SequencePlayer;

/**
 * 
 * Chord represents a chord in the musical piece 
 */
public class Chord implements MusicElement{
    
    List<Note> notes;
    Duration duration;
    /**
     * Constructor method for Chord
     * @param notes: list of Notes in the chord
     * @param duration: Duration object associated with the chord
     */
    public Chord(List<Note> notes, Duration duration) {
        this.notes = notes;
        this.duration = duration;
    }
    
    /**
     * @param object to be compared with the chord object
     * @returns boolean saying whether the two objects are equal
     */
        public boolean equals(Object _that){
        	if (!(_that instanceof Chord)) {
                    return false;
                }
            Chord that = (Chord) _that;
            return (this.notes.equals(that.notes) && (this.duration.equals(that.duration)));
        }

    /**
     * Returns false because this is a chord
     */
    @Override
    public boolean isRest() {
        return false;
    }


    /**
     * Adds the rest to the player to be played 
     * @param player: the SequencePlayer that uses MIDI to play music
     * @param currentCount: where the player currently is 
     * @param ticksPerBeat: number of ticks per beat for the piece that corresponds to music 
     * @return value of current count to be passed on to next call of addToPlayer
     */
    @Override
    public int addToPlayer(SequencePlayer player, int currentCount, int ticksPerBeat) {
        // We only want to advance the count once -- all the notes in the chord
        // should start at the same time. We will advance the count based on the
        // first note in the chord. Store the advanced count in a variable to be
        // returned, but don't use it to add the other notes.
        int advancedCount = notes.get(0).addToPlayer(player, currentCount, ticksPerBeat);
        
        // Now, add all the other notes without changing the count.
        for(int i=0; i<notes.size(); i++) {
            notes.get(i).addToPlayer(player, currentCount, ticksPerBeat);
        }
        
        // Return the advanced count.
        return advancedCount;
    }
    
    /**
     * @return duration of the chord
     */
    @Override
    public Duration getDuration() {
        return duration;
    }
    /**
     * Returns an identical Chord except with a different duration
     * calculated by multiplying the current duration by the given duration.
     * @return Chord with modified duration.
     */
    @Override
    public MusicElement elementWithMultipliedDuration(Duration dur) {
        List<Note> newNotes = new ArrayList<Note>();
        for (int i=0; i<notes.size(); i++) {
            newNotes.add((Note)(notes.get(i).elementWithMultipliedDuration(dur)));
        }
        Duration newDur = new Duration(duration.numerator*dur.numerator, duration.denominator*dur.denominator);
        return new Chord(newNotes, newDur);
    }
    
    /**
     * Returns all the relevant Note information as a string
     * @return String representing the note 
     */
    @Override
    public String toString() { 
        StringBuilder noteNames = new StringBuilder(); 
        for(int i=0; i<notes.size(); i++) {
            noteNames.append(notes.get(i).toString());
            noteNames.append(",");
        }
        return "Chord("+noteNames+this.duration.toDouble()+")"; 
    }
    
    /**
     * @return a deep copy of the Chord object
     */
    @Override
    public Chord getDeepCopy() {
        // All are primitives except for duration, so it should be OK
        List<Note> newNotes = new ArrayList<Note>();
        for(int i=0; i<notes.size(); i++) {
            newNotes.add((Note)(notes.get(i).getDeepCopy()));
        }
        return new Chord(newNotes, duration.getDeepCopy());
    }

}
