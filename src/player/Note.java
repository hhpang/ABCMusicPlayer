package player;

import sound.Pitch;
import sound.SequencePlayer;

/**
 * A Note represents a note in a music piece  
 */
public class Note implements MusicElement{

    private char baseNote ;
    private int octave ;
    private int accidental ;
    private Duration duration ;
    
    /**
     * Cosntructor for Note object
     * @param baseNote: Letter of the note (A-G, a-g)
     * @param octave : integer repesenting the octave of the note (positive numbers are higher; negative numbers are lower)
     * @param accidental : integer repesenting the accidental of the note (positive numbers are sharps; negative numbers are flats)
     * @param duration : Duration object assoicated with the note
     */
    public Note(char baseNote, int octave, int accidental, Duration duration) {
        //baseNote is A-G
        this.baseNote = Character.toUpperCase(baseNote);
        this.octave = octave;
        this.accidental = accidental ;
        this.duration = duration ;
    }
/**
 * @param object to be compared with the note object
 * @returns boolean saying whether the two objects are equal
 */
    public boolean equals(Object _that){
    	if (!(_that instanceof Note)) {
                return false;
            }
        Note that = (Note) _that;
        return (this.baseNote == that.baseNote && (this.octave == that.octave) && 
            		(this.accidental == that.accidental) && (this.duration.equals(that.duration)));
    }

    /**
     * Returns false because this is a Note
     */
    @Override
    public boolean isRest() {
        return false;
    }

    /**
     * Sets the pitch of the note - implements accidentals and octave changes
     * @param note: Note to add pitch to
     * @return the note in terms of a pitch
     */
    public Pitch toPitch(Note note) {
        Pitch newPitch = new Pitch(this.baseNote);
        newPitch = newPitch.accidentalTranspose(this.accidental);
        newPitch = newPitch.octaveTranspose(this.octave);
        return newPitch;
    }

    /**
     * Adds the note to the player to be played 
     * @param player: the SequencePlayer that uses MIDI to play music
     * @param currentCount: where the player currently is 
     * @param ticksPerBeat: number of ticks per beat for the piece that corresponds to music 
     * @return value of current count to be passed on to next call of addToPlayer
     */
    @Override
    public int addToPlayer(SequencePlayer player, int currentCount, int ticksPerBeat) {
        Pitch pitch = toPitch(this);
        int note = pitch.toMidiNote();
        int numTicks = (this.duration.numerator*ticksPerBeat)/
                (this.duration.denominator);
        player.addNote(note, currentCount, numTicks);
        currentCount += numTicks;
        return currentCount;
    }
    
    /**
     * Gets the duration of the note 
     */
    @Override
    public Duration getDuration() {
        return duration;
    }
    
    /**
     * Returns an identical Note except with a different duration
     * calculated by multiplying the current duration by the given duration.
     * Needed for calculating duration of tuplets.
     * @param Duration representing the multiplicative factor by which we multiply the original duration
     * @return Note, with modified duration.
     */
    @Override
    public MusicElement elementWithMultipliedDuration(Duration dur) {
        Duration newDur = new Duration(duration.numerator*dur.numerator, duration.denominator*dur.denominator);
        return new Note(baseNote, octave, accidental, newDur);
    }
    
    /**
     * Returns all the relevant Note information as a string
     * @return String representing the note 
     */
    @Override
    public String toString() { 
        return "Note("+this.baseNote+","+this.octave+","+this.accidental+","+this.duration.toDouble()+")"; 
    }
    
    /**
     * @return deep copy of the Note object
     */
    @Override
    public MusicElement getDeepCopy() {
        // All are primitives except for duration, so it should be OK
        return new Note(baseNote, octave, accidental, duration.getDeepCopy());
    }
}
