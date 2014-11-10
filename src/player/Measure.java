package player;

import java.util.ArrayList;
import java.util.List;

import sound.SequencePlayer;
/**
 * Measure organizes MusicElements into groups and keeps track of repeats in music 
 */
public class Measure {
    
    private List<MusicElement> elements;
    private boolean beginRepeat;
    private boolean endRepeat;
    private boolean partOfFirstEnding;
    
    /**
     * Constructor method for Measure
     * @param elements List of MusicElements that comprise the Measure
     * @param beginRepeat: true if a repeat section begins at this measure, false otherwise
     * @param endRepeat: true if a repeat section ends at this measure, false otherwise
     */
    public Measure(List<MusicElement> elements, boolean beginRepeat, boolean endRepeat, boolean partOfFirstEnding) {
        this.elements = elements;
        this.beginRepeat = beginRepeat;
        this.endRepeat = endRepeat;
        this.partOfFirstEnding = partOfFirstEnding;
    }
    
    public Measure(List<MusicElement> elements, boolean beginRepeat, boolean endRepeat) {
        this(elements, beginRepeat, endRepeat, false);
    }
    
    /**
     * 
     * @return boolean representing whether a repeat section BEGINS at this measure (true if yes, false if not)
     */
    public boolean isBeginRepeat() { return beginRepeat; }
    
    /**
     * 
     * @return boolean representing whether a repeat section ENDS at this measure (true if yes, false if not)
     */
    public boolean isEndRepeat() { return endRepeat; }
    
    /**
     * @param object to compare Measure object to
     * @return boolean saying whether the two objects are equal
     */
    
    public boolean isPartOfFirstEnding() { return partOfFirstEnding; }
    
    public boolean equals(Object _that){
    	if (!(_that instanceof Measure)) {
                return false;
            }
        Measure that = (Measure) _that;
            return (this.elements.equals(that.elements) && (this.beginRepeat == that.beginRepeat) && 
            		(this.endRepeat == that.endRepeat));
    }
    
    /**
     * 
     * @return : List of MusicElements in the measure
     */
    public List<MusicElement> getElements() {
        List<MusicElement> newElements = new ArrayList<MusicElement>();
        for(int i=0; i<elements.size(); i++) {
            newElements.add(elements.get(i).getDeepCopy());
        }
        return newElements;
    }
    
    /**
     * Higher level adding Measure to player. Each Voice has its own separate Measure.  
     * @param player SequencePlayer to play music
     * @param currentCount current place of music 
     * @param ticksPerBeat given determined ticks per beat
     * @return current location in the music piece played 
     */
    public int addToPlayer (SequencePlayer player, 
            int currentCount, int ticksPerBeat) {
        for (int i = 0; i < this.elements.size(); i ++) {
            currentCount = this.elements.get(i).addToPlayer(player, currentCount, ticksPerBeat);
        }
        return currentCount;
    }
    /**
     * adds Measures to the MIDI player
     * @param player: the Sequence player to which we are adding measures
     * @param currentCount: the current tick count of the player
     * @param ticksPerBeat: the number of ticks per beat for the song we are playing 
     * @param lyrics : LyricsIterator object representing our lyrics
     * @return integer representing the new tick count of the player
     */
    public int addToPlayer (SequencePlayer player, 
        int currentCount, int ticksPerBeat, LyricsIterator lyrics) {
    	lyrics.unstick();
        for (int i = 0; i < this.elements.size(); i ++) {
            MusicElement element = this.elements.get(i);
            if(!element.isRest() && lyrics.hasNextLyric()) {
            	lyrics.addNextLyric(player, currentCount);
            }
            currentCount = element.addToPlayer(player, currentCount, ticksPerBeat);
        }
        return currentCount;
        
    }
    
    /**
     * @return deep copy of the Measure object
     */
    public Measure getDeepCopy() {
        return new Measure(getElements(), isBeginRepeat(), isEndRepeat(), isPartOfFirstEnding());
    }
    
    /**
     * 
     * @return number of notes/chords in a measure
     */
    public int countNotes() { 
        int count = 0;
        for(int i=0; i<elements.size(); i++) {
            if(!elements.get(i).isRest()) count++; //so we're technically counting a chord as a note
        }
        return count;
    }
    
    /**
     * @return String representation of the Measure
     */
    @Override
    public String toString() {
        String str="Measure(";
        for(int i=0; i<elements.size(); i++) {
            str = str+elements.get(i).toString();
            str = str+", ";
        }
        str = str + "beginRepeat=";
        if(beginRepeat) str = str+"TRUE, ";
        if(beginRepeat) str = str+"FALSE, "; 
        
        str = str + "endRepeat=";
        if(endRepeat) str = str+"TRUE)";
        if(endRepeat) str = str+"FALSE)";
        
        return str;
    }
}