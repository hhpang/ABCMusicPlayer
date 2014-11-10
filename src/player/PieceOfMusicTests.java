package player;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import sound.LyricListener;
import sound.SequencePlayer;
import static org.junit.Assert.*;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


public class PieceOfMusicTests {
	/*
	 * Testing strategy:
	 * equals: test to make sure it is reflexive, symmetric, transitive,
	 *  and test to make sure it returns true when PiecesOfMusic are equal, and false otherwise.
	 * 
	 * getBeatsPerMinute: correctly return the beats per minute of a PieceOfMusic that we construct
	 * 
	 * getTicksPerBeat:  correctly return the ticks per beat of a PieceOfMusic that we construct
	 * 
	 * addToPlayer: Tests adding entire piece of music to player, with datatypes that are constructed in the tests.
	 * (So this doesn't use the lexer/parser).  This is the highest level test we have 
	 * besides our end to end testing.
	 */
	//equals tests
	@Test 
	public void equalsTestIsEqual() {
		Note note1 = new Note('A', 2, 0, new Duration(1,4));
		Rest rest1 = new Rest(new Duration(1,4));
		Note note2 = new Note('B', 2, 0, new Duration(1,4));
		List<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note1);
		musicElementList.add(rest1);
		musicElementList.add(note2);
		Measure measure = new Measure(musicElementList, false, false);
		//construct list of measures
		List<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//lyrics
		Lyric lyric1 = new Lyric("It", 1);
		Lyric lyric2 = new Lyric("works.", 1);
		List<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		//construct musicLine
		MusicLine musicLine = new MusicLine("line1", measureList, lyricList);
		List<MusicLine> musicLineList = new ArrayList<MusicLine>();
		musicLineList.add(musicLine);
		//construct voice
		Voice defaultVoice = new Voice(musicLineList);
		List<Voice> voiceList = new ArrayList<Voice>();
		voiceList.add(defaultVoice);
		List<String> voices = new ArrayList<String>();
		voices.add("default");
		PieceOfMusic pieceOfMusic1 = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		PieceOfMusic pieceOfMusic2 = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
	            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		PieceOfMusic pieceOfMusic3 = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
	            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		assertEquals(true, pieceOfMusic1.equals(pieceOfMusic1));
		assertEquals(true, pieceOfMusic1.equals(pieceOfMusic2));
		assertEquals(true, pieceOfMusic1.equals(pieceOfMusic2) && pieceOfMusic2.equals(pieceOfMusic3) && 
				pieceOfMusic1.equals(pieceOfMusic3));
	}
	
	@Test 
	public void equalsTestNotEqual() {
		Note note1 = new Note('A', 2, 0, new Duration(1,4));
		Rest rest1 = new Rest(new Duration(1,4));
		Note note2 = new Note('B', 2, 0, new Duration(1,4));
		List<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note1);
		musicElementList.add(rest1);
		musicElementList.add(note2);
		Measure measure = new Measure(musicElementList, false, false);
		//construct list of measures
		List<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//lyrics
		Lyric lyric1 = new Lyric("It", 1);
		Lyric lyric2 = new Lyric("works.", 1);
		List<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		//construct musicLine
		MusicLine musicLine = new MusicLine("line1", measureList, lyricList);
		List<MusicLine> musicLineList = new ArrayList<MusicLine>();
		musicLineList.add(musicLine);
		//construct voice
		Voice defaultVoice = new Voice(musicLineList);
		List<Voice> voiceList = new ArrayList<Voice>();
		voiceList.add(defaultVoice);
		List<String> voices = new ArrayList<String>();
		voices.add("default");
		PieceOfMusic pieceOfMusic1 = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		PieceOfMusic pieceOfMusic2 = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
	            voices, new Duration(3,4), "1/4=50", 100), voiceList, 9); 
		assertEquals(false, pieceOfMusic1.equals(pieceOfMusic2));
	}
	
	
	@Test
	public void addToPlayerTest() {
		//notes and rests in measure
		Note note1 = new Note('A', 2, 0, new Duration(1,4));
		Rest rest1 = new Rest(new Duration(1,4));
		Note note2 = new Note('B', 2, 0, new Duration(1,4));
		List<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note1);
		musicElementList.add(rest1);
		musicElementList.add(note2);
		Measure measure = new Measure(musicElementList, false, false);
		//construct list of measures
		List<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//lyrics
		Lyric lyric1 = new Lyric("It", 1);
		Lyric lyric2 = new Lyric("works.", 1);
		List<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		//construct musicLine
		MusicLine musicLine = new MusicLine("line1", measureList, lyricList);
		List<MusicLine> musicLineList = new ArrayList<MusicLine>();
		musicLineList.add(musicLine);
		//construct voice
		Voice defaultVoice = new Voice(musicLineList);
		List<Voice> voiceList = new ArrayList<Voice>();
		voiceList.add(defaultVoice);
		List<String> voices = new ArrayList<String>();
		voices.add("default");
		PieceOfMusic pieceOfMusic = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		
		// Add to pieceOfMusic to player
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        try {
            SequencePlayer player = new SequencePlayer(pieceOfMusic.getBeatsPerMinute(), pieceOfMusic.getTicksPerBeat(), listener);
            pieceOfMusic.addToPlayer(player); 
            player.play();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void getBeatsPerMinuteTest() {
		Note note1 = new Note('A', 2, 0, new Duration(1,4));
		Rest rest1 = new Rest(new Duration(1,4));
		Note note2 = new Note('B', 2, 0, new Duration(1,4));
		List<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note1);
		musicElementList.add(rest1);
		musicElementList.add(note2);
		Measure measure = new Measure(musicElementList, false, false);
		//construct list of measures
		List<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//lyrics
		Lyric lyric1 = new Lyric("It", 1);
		Lyric lyric2 = new Lyric("works.", 1);
		List<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		//construct musicLine
		MusicLine musicLine = new MusicLine("line1", measureList, lyricList);
		List<MusicLine> musicLineList = new ArrayList<MusicLine>();
		musicLineList.add(musicLine);
		//construct voice
		Voice defaultVoice = new Voice(musicLineList);
		List<Voice> voiceList = new ArrayList<Voice>();
		voiceList.add(defaultVoice);
		List<String> voices = new ArrayList<String>();
		voices.add("default");
		PieceOfMusic pieceOfMusic = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
            voices, new Duration(3,4), "1/4=50", 100), voiceList, 10);
		assertEquals(100, pieceOfMusic.getBeatsPerMinute());
	}
	
	@Test
    public void getTicksPerBeatTest() { 
		Note note1 = new Note('A', 2, 0, new Duration(1,4));
		Rest rest1 = new Rest(new Duration(1,4));
		Note note2 = new Note('B', -1, 0, new Duration(1,4));
		List<MusicElement> musicElementList = new ArrayList<MusicElement>();
		musicElementList.add(note1);
		musicElementList.add(rest1);
		musicElementList.add(note2);
		Measure measure = new Measure(musicElementList, false, false);
		//construct list of measures
		List<Measure> measureList = new ArrayList<Measure>();
		measureList.add(measure);
		//lyrics
		Lyric lyric1 = new Lyric("It", 1);
		Lyric lyric2 = new Lyric("works.", 1);
		List<Lyric> lyricList = new ArrayList<Lyric>();
		lyricList.add(lyric1);
		lyricList.add(lyric2);
		//construct musicLine
		MusicLine musicLine = new MusicLine("line1", measureList, lyricList);
		List<MusicLine> musicLineList = new ArrayList<MusicLine>();
		musicLineList.add(musicLine);
		//construct voice
		Voice defaultVoice = new Voice(musicLineList);
		List<Voice> voiceList = new ArrayList<Voice>();
		voiceList.add(defaultVoice);
		List<String> voices = new ArrayList<String>();
		voices.add("default");
		PieceOfMusic pieceOfMusic = new PieceOfMusic(new MusicHeader("16", "This is a song", "Music guy", new Duration(1,4), 
            voices, new Duration(3,4), "1/4=50", 100), voiceList, 5);
		System.out.println(pieceOfMusic.getTicksPerBeat());
		assertEquals(5, pieceOfMusic.getTicksPerBeat());
    }
}
