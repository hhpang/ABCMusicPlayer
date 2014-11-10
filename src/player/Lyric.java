package player;

public class Lyric {
    
    public String syllable;
    public int numberOfNotes;
    
    /**
     * Constructor method for Lyric
     * @param syllable : String representing the syllable of the lyric
     * @param notes : int representing the number of notes for which the lyric lasts
     */
    public Lyric(String syllable, int notes) {
        this.syllable = syllable;
        this.numberOfNotes = notes;
    }
    
    public Lyric getDeepCopy() {
        return new Lyric(syllable, numberOfNotes);
    }
    
    /**
     * equals method for Lyric
     * @param object to compare to lyric object
     * @return boolean saying if the two objects are equal
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof Lyric)) {
                return false;
            }
        Lyric that = (Lyric) _that;
            return (this.syllable.equals(that.syllable) && 
            		(this.numberOfNotes == that.numberOfNotes));
    }

    /**
     * Returns the string of the current syllable
     * @return String of syllable
     */
    public String toString() {
    	return this.syllable;
    }
}
