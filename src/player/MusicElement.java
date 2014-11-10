package player;
import java.util.List;

import sound.SequencePlayer;
/**
 * Database definition for Music 
 * Music = Note(name: char, octave: int, accidental: int, duration: Duration) 
 *         + Rest(duration: Duration) + Chord(list: Note, duration: Duration) 
 */
public interface MusicElement {
    /**
     * Check if the music element is a rest, so that the appropriate duration
     * in the music can be added and skipped to next note or chord 
     * @return true if music element is a rest, and false if note or chord
     */
    
    public boolean isRest();
    
    /**
     * Adds the note, chord, or rest to the player to be played 
     * @param player: the SequencePlayer that uses MIDI to play music
     * @param currentCount: where the player currently is 
     * @param ticksPerBeat: number of ticks per beat for the piece that corresponds to music 
     * @return value of current count to be passed on to next call of addToPlayer
     */
    public int addToPlayer(SequencePlayer player, int currentCount, int ticksPerBeat);
    
    /**
     * General method to convert to a String if testing 
     * for equality or others is needed 
     * @return String of the object
     */
    public String toString();
    
    /** Determines whether two MusicElements are equivalent.
     * 
     * @param other MusicElement to compare to
     * @return boolean value saying whether the two expressions are equal
     */
    public boolean equals(Object other);
    /**
     * Get duration of the MusicElement
     * @return Duration as a Duration object
     */
    
    public Duration getDuration();
    
    /**
     * Returns an identical MusicElement except with a different duration
     * calculated by multiplying the current duration by the given duration.
     * @return MusicElement of same type as the original, with modified duration.
     */
    public MusicElement elementWithMultipliedDuration(Duration dur);
    
    /**
     * Returns a deep copy of this music element. Should be functionally equivalent
     * but contain no pointers to parts of this music element.
     * @return MusicElement that is a deep copy of the current MusicElement
     */
    public MusicElement getDeepCopy();
    
}

