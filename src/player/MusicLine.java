package player;

import java.util.ArrayList;
import java.util.List;

import sound.SequencePlayer;

/**
 * MusicLine organizes the measures (containing music) and lyrics in each line as a single object
 *
 */
public class MusicLine {
    public String name;
    private List<Measure> measures;
    private List<Lyric> lyrics;
    /**
     * constructor method for MusicLine
     * @param name : String representing name of this line
     * @param measures: List of Measures contained in this line
     * @param lyrics: List of Lyrics contained in this line
     */
    public MusicLine(String name, List<Measure> measures, List<Lyric> lyrics) {
        this.name = name;
        this.measures = measures;
        this.lyrics = lyrics;
    }
    /**
     * @param object to be compared to MusicLine object
     * @return boolean saying whether objects are equal
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof MusicLine)) {
                return false;
            }
    	MusicLine that = (MusicLine) _that;
            return (this.name.equals(that.name) && 
            		(this.measures.equals(that.measures)) && (this.lyrics.equals(that.lyrics)));
    }
    /**
     * 
     * @return list of Measures which comprise the MusicLine
     */
    
    public List<Measure> getMeasures() {
        List<Measure> newMeasures = new ArrayList<Measure>();
        for(int i=0; i<measures.size(); i++) {
            Measure measure = measures.get(i);
            newMeasures.add(measures.get(i).getDeepCopy());
        }
        return newMeasures;
    }
    
    /**
     * 
     * @return list of Lyrics which comprise the MusicLine
     */
    public List<Lyric> getLyrics() {
        List<Lyric> newLyrics = new ArrayList<Lyric>();
        for(int i=0; i<lyrics.size(); i++) {
            newLyrics.add(lyrics.get(i).getDeepCopy());
        }
        return newLyrics;
    }
    /**
     * Adds a MusicLine to the MIDI player
     * @param player - the Sequence Player to which we are adding music
     * @param currentCount - current tick count
     * @param ticksPerBeat -  number of ticksPerBeat for a given song
     * @return tick count after MusicLine is added
     */
    public int addToPlayer(SequencePlayer player, int currentCount, int ticksPerBeat) {

        if(!lyrics.isEmpty()) {
            LyricsIterator iter = new LyricsIterator(lyrics);
            for(int i=0; i<measures.size(); i++) {
                currentCount = measures.get(i).addToPlayer(player, currentCount, ticksPerBeat, iter);
            }
        } else {
            for(int i=0; i<measures.size(); i++) {
                currentCount = measures.get(i).addToPlayer(player, currentCount, ticksPerBeat);
            }
        }
        return currentCount;
    }
    /**
     * Counts number of notes/chords in measure.
     * This method is needed for handling repeats in songs.
     * @param begin -  beginning index
     * @param end - ending index
     * @return int number of notes/chords in the measure
     */

    public int countNotesBeforeBeginningOfMeasure(int begin) {
        if (begin==0) return 0;
        System.out.println("counting with begin="+begin);
        int total = 0;
        begin = Math.max(0, begin);
        begin = Math.min(measures.size(), begin);
        for(int i=0; i<begin; i++) {
            System.out.println("looping thru");
            total+= measures.get(i).countNotes();
        }
        return total;
    }

    
}
