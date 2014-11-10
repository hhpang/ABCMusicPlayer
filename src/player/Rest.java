package player;

import sound.SequencePlayer;

public class Rest implements MusicElement{

    private Duration duration;
    
    /**
     * Constructor method for Rest
     * Rest represents a rest in the music piece
     * @param duration: duration on the rest 
     */
    public Rest (Duration duration) {
        this.duration = duration;
    }
    
    /**
     * Equals method for Rest
     * @param object to be compared with the rest object
     * @returns boolean saying whether the two objects are equal
     */
        public boolean equals(Object _that){
        	if (!(_that instanceof Rest)) {
                    return false;
                }
            Rest that = (Rest) _that;
            return (this.duration.equals(that.duration));
        }
    
    /**
     * Returns true because this is a Rest
     */
    @Override
    public boolean isRest() {
        return true;
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
        int numTicks = (this.duration.numerator*ticksPerBeat)/
                (this.duration.denominator);
        currentCount += numTicks;
        return currentCount;
    }
    
    /**
     * @return the Duration associated with the Rest
     */
    @Override
    public Duration getDuration() {
        return duration;
    }
    
    /**
     * Returns an identical Rest except with a different duration
     * calculated by multiplying the current duration by the given duration.
     * @param Duration representing the multiplicative factor by which we multiply the original duration
     * @return Rest, with modified duration.
     */
    @Override
    public MusicElement elementWithMultipliedDuration(Duration dur) {
        Duration newDur = new Duration(duration.numerator*dur.numerator, duration.denominator*dur.denominator);
        return new Rest(newDur);
    }
    
    /**
     * Returns all the relevant Rest information as a string
     */
    @Override
    public String toString() { 
        return ("Rest("+this.duration.toDouble()+")"); 

    }
    
    /**
     * @return a deep copy of the Rest object
     */
    @Override
    public MusicElement getDeepCopy() {
        // All are primitives except for duration, so it should be OK
        return new Rest(duration.getDeepCopy());
    }

}
