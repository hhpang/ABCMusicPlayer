package player;

import static org.junit.Assert.assertEquals;
import grammar.ABCMusicLexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

import sound.LyricListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import sound.SequencePlayer;
/**
 * Test the parser (generates parse tree) and plays songs from the given
 * file.  Disabled didit because these tests are verified by listening to the music manually.
 * @category no_didit
 */

public class ParserTest {
    @Test
    public void parseABCSong() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/abc_song.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            System.out.println("starting to play ABC song");
            player.play();
            System.out.println("finished playing ABC song");
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
   }

    @Test
    public void parseFurElise() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/fur_elise.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }

        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());

        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        musicPlayer.parse(musicFile.toString());

        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };

        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            System.out.println("starting to play Fur Elise");
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void parseSimpleRepeat() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/simple_repeat.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        
        Player musicPlayer = new Player();
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        musicPlayer.parse(musicFile.toString());
        
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        
        try {
            SequencePlayer player = new SequencePlayer(10, 128, listener);
            music.addToPlayer(player);
            System.out.println("about to play");
            player.play();
            System.out.println("just finished playing");
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
     
    @Test
    public void parseInvention() throws IOException {
    	System.out.println("INVENTION!");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/invention.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
        
    }

    @Test
    public void parseLittleNight() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/little_night_music.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
    }
 
    @Test
    public void parsePaddy() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/paddy.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
       
    }
    
    @Test
    public void parsePiece1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/piece1.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
    }
  
    @Test
    public void parsePiece2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/piece2.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
    }
  
    @Test
    public void parsePiece3() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/piece3.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
        
    }

    @Test
    public void parseScale() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/scale.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
    }
   
    @Test
    public void parsePrelude() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/prelude.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
  
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());

        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };

        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            System.out.println("starting to play Prelude");
            player.play();
            System.out.println("finished playing Prelude");
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseWaxiesDargle() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/waxies_dargle.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
        
    }

    @Test
    public void parseSimpleGifts() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/simple_gifts.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            System.out.println("starting to play simple gifts song");
            System.out.println(music.getBeatsPerMinute());
            System.out.println(music.getTicksPerBeat());
            player.play();
            System.out.println("finished playing simple gifts  song");
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
   
    @Test
    public void parseBallGame() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/take_me_out_to_the_ball_game.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }

        
        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
         
        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseStarSpangledBanner() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/star_spangled_banner.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }

        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());

        PieceOfMusic music = musicPlayer.parse(musicFile.toString());
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(music.getBeatsPerMinute(), music.getTicksPerBeat(), listener);
            music.addToPlayer(player);
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

       
    @Test
    public void parseBarbieGirl() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("sample_abc/barbie_girl.abc"));
        StringBuffer musicFile = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            musicFile.append(line).append("\n");
        }

        Player musicPlayer = new Player();
        musicPlayer.parse(musicFile.toString());
    }
}
    

