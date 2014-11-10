package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; 

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.LyricListener;
import sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {

    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * (Your code should not exit the application abnormally using
     * System.exit().)
     * 
     * @param fileName the name of input abc file
     * @throws IOException 
     */
    public static void play(String fileName) throws IOException {
        // Read in the file name and create entire string of file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while((line =bufferedReader.readLine())!=null){
         musicFile.append(line).append("\n");
        }
        // Parse music
        Player musicPlayer = new Player();
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());

        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };

        try {
            // Make SequencePlayer player based on calculated beats and ticks 
            SequencePlayer player = new SequencePlayer(music.header.beatsPerMinute, music.ticksPerBeat, listener);
            music.addToPlayer(player);
            // Play through SequencePlayer 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Play any abc file here   
        // play("sample_abc/little_night_music.abc"); 
//        play("sample_abc/waxies_dargle.abc");
//        play("sample_abc/invention.abc"); 
//        play("sample_abc/prelude.abc"); 
//        play("sample_abc/barbie_girl.abc");
//       play("sample_abc/take_me_out_to_the_ball_game.abc"); 
//        play("sample_abc/simple_gifts.abc");
        
    }
}


