package player;

import java.util.List;

/**
 * MusicHeader includes all the signatures for the music piece
 */
public class MusicHeader {
    String number;
    String title;
    String composer;
    Duration defaultNoteLength;
    List<String> voices;
    Duration meter;
    String tempo;
    int beatsPerMinute;
   /**
    * Constructor method for MusicHeader
    * @param number: String representing the number of the piece
    * @param title: String representing the title of the piece
    * @param composer: String representing the composer of the piece
    * @param defaultNoteLength: Duration object representing the default note length
    * @param voices: List of Strings representing the voices in the piece
    * @param meter: Duration object representing the meter of the piece
    * @param tempo: String representing the tempo of the piece, in the form of a standard note length 
    * equals a certain number of beats, e.g. 1/4=100
    * @param beatsPerMinute: an int value representing the number of beats per minute
    */
    public MusicHeader(String number, String title, String composer, Duration defaultNoteLength, 
            List<String> voices, Duration meter, String tempo, int beatsPerMinute) {
        this.number = number;
        this.title = title;
        this.composer = composer;
        this.defaultNoteLength = defaultNoteLength;
        this.voices = voices; 
        this.meter = meter;
        this.tempo = tempo;
        this.beatsPerMinute = beatsPerMinute;
    }
    
    /**
     * equals method for MusicHeader
     * @return boolean saying whether object equals the given MusicHeader object
     */
    public boolean equals(Object _that){
    	if (!(_that instanceof MusicHeader)) {
                return false;
            }
    	MusicHeader that = (MusicHeader) _that;
            return (this.number.equals(that.number)) && this.title.equals(that.title) && (this.composer.equals(that.composer)
            		&& (this.defaultNoteLength.equals(that.defaultNoteLength)) && (this.voices.equals(that.voices)) && 
            		(this.meter.equals(that.meter)) && (this.beatsPerMinute == that.beatsPerMinute));
    }

}
