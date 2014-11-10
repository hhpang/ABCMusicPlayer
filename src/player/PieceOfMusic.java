package player;

import java.util.List;

import sound.SequencePlayer;

public class PieceOfMusic {

    public MusicHeader header;
    public List<Voice> voices;
    public int ticksPerBeat;
    
    /**
     * Constructor method for PieceOfMusic
     * @param header: MusicHeader object associated with the piece of music
     * @param voices: Voices that make up the piece of music
     * @param ticksPerBeat : ticks per beat for the piece
     */
    public PieceOfMusic(MusicHeader header, List<Voice> voices, int ticksPerBeat) {
        this.header = header;
        this.voices = voices;
        this.ticksPerBeat = ticksPerBeat;
    }
    
    /**
     * equals method for PieceOfMusic.  Only if the header, voices, and ticksPerBeat are equal can
     * two pieces of music be equal.
     * @return boolean saying whether the object that is passed in and the PieceOfMusic object are equal
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof PieceOfMusic)) {
                return false;
            }
        PieceOfMusic that = (PieceOfMusic) _that;
            return (this.header.equals(that.header) && (this.voices.equals(that.voices)) &&
            		(this.ticksPerBeat == that.ticksPerBeat));
    }    

    /**
     * 
     * @return beats per minute of the piece
     */
    public int getBeatsPerMinute() {
    	return this.header.beatsPerMinute;
    }
    
    /**
     * 
     * @return ticks per beat of the piece.  
     */
    public int getTicksPerBeat() {
    	return this.ticksPerBeat;
    }

    /**
     * Adds the entire piece of music to the MIDI player
     * @param player: the SequencePlayer to which we are adding the piece
     */
    public void addToPlayer(SequencePlayer player){
    	for (int i=0; i < voices.size(); i++) {
    		voices.get(i).addToPlayer(player, this.ticksPerBeat);
    	}
    }
    
}
