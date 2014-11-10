package player;

import java.util.List;

import sound.SequencePlayer;

/**
 * Voice represents the voices of a music piece and has the ability to  
 * encompass different voices in the musical piece 
 * List of MusicLine objects cannot be null or empty 
 */
public class Voice {
    public List<MusicLine> lines;
    
    Voice(List<MusicLine> lines) {
        this.lines = lines;
    }
    
    /**
     * Equals method for Voice
     * @return boolean saying whether object being compared is equal to our Voice object
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof Voice)) {
                return false;
            }
        Voice that = (Voice) _that;
            return (this.lines.equals(that.lines));
    }
    /**
     * Adds each voice to the player at the same start count
     * @param player: the SequencePlayer to which we are adding the voices
     * @param ticksPerBeat: ticks per beat, as determined by the piece of music 
     */
    public void addToPlayer(SequencePlayer player, int ticksPerBeat){
        int currentCount = 0;
        for (int i=0; i<lines.size(); i++) {
            currentCount = lines.get(i).addToPlayer(player, currentCount, ticksPerBeat);
        }
	
    }
}
