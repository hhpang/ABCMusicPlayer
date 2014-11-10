package player;

import java.util.List;

import sound.SequencePlayer;

public class LyricsIterator {

    public List<Lyric> lyrics;
    private int syllableCounter;
    private int noteCounter;
    
    /**
     * LyricsIterator is an iterator through a list of syllables in lyrics
     * @param lyrics list of Lyric objects
     */
    public LyricsIterator(List<Lyric> lyrics) {
        this.lyrics = lyrics;
        syllableCounter = 0;
        noteCounter = 1;
    }
    
    /**
     * Based on which syllables have already been seen,
     * check whether the current LyricsIterator still has lyrics
     * @return true if there is another lyric to be added to the player
     */
    public boolean hasNextLyric() {
        if (syllableCounter >= lyrics.size()) return false;
        return true;
    }
    
    /**
     * adds next lyric to the player
     * @param player: the SequencePlayer we are using
     * @param currentTick: the current tick count of the player
     */
    public void addNextLyric(SequencePlayer player, int currentTick) {
        if (syllableCounter >= lyrics.size()) {
            throw new RuntimeException("Cannot add next lyric because last lyric has already been reached.");
        }
        Lyric lyric = lyrics.get(syllableCounter);
        String syllable = lyric.syllable;
        if (!syllable.equals("|")) {
        	if (noteCounter==1) {
                player.addLyricEvent(syllable, currentTick);
            }
            noteCounter++;
            if (noteCounter>lyric.numberOfNotes) {
                noteCounter = 1;
                syllableCounter++;
            }
        }
    }
 
    /**
     * "Unsticks" syllable counter at beginning of every measure,  
     * that is, increases syllable counter after the barline is passed over
     * Needed to account for bars (|) within lyrics.
     */
    public void unstick() {
    	if (lyrics.get(syllableCounter).syllable.equals("|")){
        	syllableCounter++;
    	}	
    	}
    

    /**
     * @return String representation of LyricsIterator 
     * "LYRIC: each lyric as a string Length = number of notes for which lyric lasts"
     */
    public String toString() {
    	StringBuilder out = new StringBuilder();
    	for (int i=0; i < this.lyrics.size(); i++){
    		out.append(" LYRIC: " + lyrics.get(i).toString() + " Length = " + lyrics.get(i).numberOfNotes);
    	}
    	String output = new String(out);
    	return output;
    }
}
