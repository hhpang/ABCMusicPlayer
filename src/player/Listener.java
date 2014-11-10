package player;
import grammar.ABCMusicBaseListener;
import grammar.ABCMusicParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


import javax.sound.sampled.Line;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;


public class Listener extends ABCMusicBaseListener {
	/*
	 * Summary of how this listener works:
	 * When exiting parts of the parser, the listener does different things. 
	 * 
	 * Exit Note: a Note is constructed. This takes into account octaves from lowercase, octaves
	 * from symbols, accidentals, key signatures changing note accidentals, measures storing
	 * a note's accidental, checking whether this note is a part of a chord or tuplet
	 * 
	 * Exit Triplet/Duplet/Quadruplet: each note is added to appropriate list of the tuplet
	 * 
	 * Exit Chord: constructs notes by going through exitNote, and each Note is added to a chord list
	 * 
	 * Exit measure: each measure is added to a list for each line, which is denoted by a starting voice
	 * and ends with the end of that voice 
	 * 
	 * Exit line: all handling of lyrics is taken care of here, with a result of one or 
     * multiple MusicLine objects that store the lines of lyrics. Also, repeats are handled here 
     * 
     * Exit header: all necessary information is stored from the header values, namely, key signature, meter,
     * tempo, default note length, voices
     * 
     * Exit tune: a PieceOfMusic object is created, ready to be passed to Sequence Player
	 */
    
    List<MusicElement> elementsInMeasure;
    List<Note> elementsInChord;
    List<MusicElement> elementsInTuplet;
    List<Measure> measuresInLine;
    String currentVoiceName;
    String lyrics;
    Map<String, List<MusicLine>> linesInVoices;
    List<String> voiceNames;
    List<Voice> voices;
    List<Lyric> lyricsInLine;
    List<MusicLine> linesInSong;
    Map<String, Boolean> voicesInFirstEnding = new HashMap<String, Boolean>();
    PieceOfMusic music;
    String baseNote; 
    int ticksPerBeat = 1;
    
    boolean repeatStartedInCurrentMeasure = false;
    boolean repeatEndedInCurrentMeasure = false;
    boolean repeatStartsInNextMeasure = false;
    Map<String, Boolean> insideRepeat = new HashMap<String, Boolean>();

    Map<String, List<MusicLine>> linesInVoiceRepeats = new HashMap<String, List<MusicLine>>();
    
    // make markers to check whether a Note object is in a Chord or Tuplet object
    boolean inChord = false;
    boolean inTuplet = false;
    
    Duration defaultNoteLength;
    Duration meter; 
    Duration givenDuration; 
    String keySignature; 
    int beatsPerMinute; 
    
    int octaveInt = 0;
    int accidental = 0; 
    int tempoValue; 
    
    MusicHeader header;
    
    Map<String, HashMap<String, Integer>> keys = new HashMap<String, HashMap<String, Integer>>();
    HashMap<String, Integer> keyNotes = new HashMap<String, Integer>(); 
    HashMap<String, Integer> keyFlats = new HashMap<String, Integer>();  
    
    Map<String,Integer> accidentalsInMeasure = new HashMap<String,Integer>(); 

	
	@Override
	public void enterMusic(ABCMusicParser.MusicContext ctx) {
	}
	@Override public void exitMusic(ABCMusicParser.MusicContext ctx) { }

	@Override public void enterElement(ABCMusicParser.ElementContext ctx) { }
	@Override public void exitElement(ABCMusicParser.ElementContext ctx) { }

	
	@Override public void enterMeasure(ABCMusicParser.MeasureContext ctx) {
	    // clear out the elementsInMeasure list by assigning it to a new list
	    elementsInMeasure = new ArrayList<MusicElement>();	    
	    if(repeatStartsInNextMeasure) {
	        repeatStartedInCurrentMeasure = true;
	        insideRepeat.put(currentVoiceName, true);
	        linesInVoiceRepeats.put(currentVoiceName, new ArrayList<MusicLine>());
	    }
	    repeatStartsInNextMeasure = false;
	    if(ctx.BARLINE().getText().equals(":|")) {
	        repeatEndedInCurrentMeasure = true;
	        insideRepeat.put(currentVoiceName, false);
	    } else if(ctx.BARLINE().getText().equals("|:") || ctx.BARLINE().getText().equals("|]")) {
	        repeatStartsInNextMeasure = true;
	    }
	    if(ctx.getChild(0).getText().equals("[1")) {
	        voicesInFirstEnding.put(currentVoiceName, true);
	    } else {
	    }
	    // clear the accidentals stored for the measure
	    accidentalsInMeasure.clear(); 
	}
    @Override public void exitMeasure(ABCMusicParser.MeasureContext ctx) {
        boolean currentMeasureIsPartOfFirstEnding;
        if(voicesInFirstEnding.get(currentVoiceName)!=null) currentMeasureIsPartOfFirstEnding = true;
        else currentMeasureIsPartOfFirstEnding = false;
        measuresInLine.add(new Measure(elementsInMeasure, repeatStartedInCurrentMeasure, repeatEndedInCurrentMeasure, currentMeasureIsPartOfFirstEnding));
        
        if(repeatEndedInCurrentMeasure) {
            voicesInFirstEnding.remove(currentVoiceName);
        }
        
        repeatStartedInCurrentMeasure = false;
        repeatEndedInCurrentMeasure = false;
    }

	@Override public void enterDuplet(ABCMusicParser.DupletContext ctx) {
	    inTuplet = true;
	    elementsInTuplet = new ArrayList<MusicElement>();
	}
	
    @Override public void exitDuplet(ABCMusicParser.DupletContext ctx) {
        inTuplet = false;
        Duration multiplier = new Duration(3, 2);
        for(int i=0; i<elementsInTuplet.size(); i++) {
            MusicElement note = elementsInTuplet.get(i);
            elementsInMeasure.add(note.elementWithMultipliedDuration(multiplier));
        }
    }

	@Override public void enterQuadruplet(ABCMusicParser.QuadrupletContext ctx) {
	    inTuplet = true;
	    elementsInTuplet = new ArrayList<MusicElement>();
	}
	@Override public void exitQuadruplet(ABCMusicParser.QuadrupletContext ctx) {
	    inTuplet = false;
        Duration multiplier = new Duration(3, 4);
        for(int i=0; i<elementsInTuplet.size(); i++) {
            MusicElement note = elementsInTuplet.get(i);
            elementsInMeasure.add(note.elementWithMultipliedDuration(multiplier));
        }
	}

	@Override public void enterLine(ABCMusicParser.LineContext ctx) {
	    if(ctx.VOICE()!=null) { 	        // if there is a voice label
	        currentVoiceName = ctx.VOICE().getText();
	        currentVoiceName = currentVoiceName.substring(currentVoiceName.indexOf(':')+1, currentVoiceName.indexOf('\n'));
	    } else currentVoiceName = "default";
	    measuresInLine = new ArrayList<Measure>();
	    lyricsInLine = new ArrayList<Lyric>();
	    if(ctx.BARLINE()!=null) {
	        if(ctx.BARLINE().getText().equals("|:") || ctx.BARLINE().getText().equals("|]")) {
	            repeatStartsInNextMeasure = true;
	            repeatStartedInCurrentMeasure = true;
	            insideRepeat.put(currentVoiceName, true);
	            linesInVoiceRepeats.put(currentVoiceName, new ArrayList<MusicLine>());
	        }
	    }
	}
	@Override public void exitLine(ABCMusicParser.LineContext ctx) {
	       if(ctx.measure().size()==0) return;
	       if (ctx.LYRIC() != null) {
	            lyrics = ctx.LYRIC().getText();
	            lyrics = lyrics.substring(lyrics.indexOf(':') + 1);
	            lyrics = lyrics.trim(); //remove beginning and ending whitespace
	            lyrics = lyrics.replaceAll("\\s+", " ");
	            lyrics = lyrics.replaceAll("\\s\\*", "*");
	            lyrics = lyrics.replaceAll("\\*", "-"); //an asterisk is equivalent to an extra hyphen
	            lyrics = lyrics.replaceAll("\\\\-", "@"); //replace /- with an obscure character so we can remember where it was
	            //(we were able to find no other way to differentiate it from a normal hyphen)
	            String[] lyricSplit = lyrics.split("[-\\s]");
	            for (int i = 0; i < lyricSplit.length; i++){
	                int underscoreNum = 0;
	                for (int count=0; count < lyricSplit[i].length(); count++){
	                    if (lyricSplit[i].charAt(count) == '_'){
	                        underscoreNum++;
	                    }
	                }
	                if (underscoreNum > 0){
	                    lyricSplit[i] = lyricSplit[i].substring(0, lyricSplit[i].indexOf('_'));
	                }
	                if (lyricSplit[i].indexOf('~') != -1) {
	                    lyricSplit[i] = lyricSplit[i].replaceAll("~", " ");
	                }
	                if (lyricSplit[i].indexOf('@') != -1) {
	                    lyricSplit[i] = lyricSplit[i].replaceAll("@", "-");
	                }
	                lyricsInLine.add(new Lyric(lyricSplit[i], underscoreNum + 1));
	            }
	        }
	        List<String> lyricsAsStrings = new ArrayList<String>();
	        for (int index = 0; index < lyricsInLine.size(); index++){
	            lyricsAsStrings.add(lyricsInLine.get(index).toString());
	        }
		if(linesInVoiceRepeats.get(currentVoiceName)!=null && linesInVoiceRepeats.get(currentVoiceName).size()>0) {
		    if(insideRepeat.get(currentVoiceName)==true) { // This means the entire line is inside the repeat
		        MusicLine line = new MusicLine(currentVoiceName, measuresInLine, lyricsInLine);
	              List<Measure> measures = line.getMeasures();
	              int firstEndingBeginsAtMeasure = -1;
	              for(int i=0; i<measures.size(); i++) {
	                    Measure measure = measures.get(i);
	                    if(measure.isPartOfFirstEnding()) {
	                        firstEndingBeginsAtMeasure = i;
	                        break;
	                    }
	                }
	              
	              if(!linesInVoiceRepeats.containsKey(currentVoiceName)) {
	                    linesInVoiceRepeats.put(currentVoiceName, new ArrayList<MusicLine>());
	                }
	              
	              if(firstEndingBeginsAtMeasure>-1) {
	                  MusicLine partWithoutFirstEnding = subLine(line, 0, firstEndingBeginsAtMeasure);
	                  linesInVoiceRepeats.get(partWithoutFirstEnding).add(line);

	              } else {
	                  linesInVoiceRepeats.get(currentVoiceName).add(line);
	              }
		        
		        linesInVoices.get(currentVoiceName).add(line);
		        
		        
		    } else { // This means the line started inside the repeat, but it ended somewhere in the middle
		        MusicLine line = new MusicLine(currentVoiceName, measuresInLine, lyricsInLine);
		        List<Measure> measures = line.getMeasures();
		        int lyricRangeEnd = 0;
		        int repeatEndedInMeasure = 0;
                int firstEndingBeginsAtMeasure = -1;
		        for(int i=0; i<measures.size(); i++) {
		            Measure measure = measures.get(i);
		            if(measure.isEndRepeat()) {
		                repeatEndedInMeasure = i;
		            }
		            if(measure.isPartOfFirstEnding()) {
		                firstEndingBeginsAtMeasure = i;
		            }
		        }

		        MusicLine partialRepeatLine = subLine(line, 0, repeatEndedInMeasure+1);
	            MusicLine remainderOfLine = subLine(line, repeatEndedInMeasure+1, measures.size());
		        if(firstEndingBeginsAtMeasure>-1) {
		            MusicLine partialRepeatLineWithoutFirstEnding = subLine(line, 0, firstEndingBeginsAtMeasure);
		            linesInVoiceRepeats.get(currentVoiceName).add(partialRepeatLineWithoutFirstEnding);
		        } else {
		            linesInVoiceRepeats.get(currentVoiceName).add(partialRepeatLine);
		        }
	            linesInVoices.get(currentVoiceName).add(partialRepeatLine);
	            
	            // Add the cache of repeat lines to the main line list
		        List<MusicLine> repeatedLines = linesInVoiceRepeats.get(currentVoiceName);
		        for(int i=0; i<repeatedLines.size(); i++) {
		            linesInVoices.get(currentVoiceName).add(repeatedLines.get(i));
		        }
		        linesInVoices.get(currentVoiceName).add(remainderOfLine);

		        linesInVoiceRepeats.put(currentVoiceName, new ArrayList<MusicLine>());
		        
		    }
		} else if(insideRepeat.get(currentVoiceName)!=null && insideRepeat.get(currentVoiceName)==true) { // This means the line didn't start inside the repeat, but the repeat began somewhere in the middle
		    
            MusicLine line = new MusicLine(currentVoiceName, measuresInLine, lyricsInLine);
            List<Measure> measures = line.getMeasures();
            int repeatBeganInMeasure = 0;
            int firstEndingBeginsAtMeasure = -1;
            for(int i=0; i<measures.size(); i++) {
                Measure measure = measures.get(i);
                if(measure.isBeginRepeat()) {
                    repeatBeganInMeasure = i;
                }
                if(measure.isPartOfFirstEnding() && firstEndingBeginsAtMeasure==-1) {
                    firstEndingBeginsAtMeasure=i;
                }
            }

            MusicLine partialRepeatLine = subLine(line, repeatBeganInMeasure, measures.size());
            if(!linesInVoiceRepeats.containsKey(currentVoiceName)) {
                linesInVoiceRepeats.put(currentVoiceName, new ArrayList<MusicLine>());
            }
            
            if(firstEndingBeginsAtMeasure>-1) {
                MusicLine partialRepeatLineWithoutFirstEnding = subLine(line, repeatBeganInMeasure, firstEndingBeginsAtMeasure);
                linesInVoiceRepeats.get(currentVoiceName).add(partialRepeatLineWithoutFirstEnding);
            } else {
                linesInVoiceRepeats.get(currentVoiceName).add(partialRepeatLine);
            }
            
            linesInVoices.get(currentVoiceName).add(line);
		    
		} else { // This means there is either a repeat that began and ended within the current line,
		         // or no repeat at all.
            MusicLine line = new MusicLine(currentVoiceName, measuresInLine, lyricsInLine);
            List<Measure> measures = line.getMeasures();
            int repeatBeganInMeasure = -1;
            int repeatEndedInMeasure = -1;
            int firstEndingBeginsAtMeasure = -1;
            for(int i=0; i<measures.size(); i++) {
                Measure measure = measures.get(i);
                if(measure.isBeginRepeat()) {
                    repeatBeganInMeasure = i;
                }
                if(measure.isEndRepeat()) {
                    repeatEndedInMeasure = i;
                }
                if(measure.isPartOfFirstEnding() && firstEndingBeginsAtMeasure==-1) {
                    firstEndingBeginsAtMeasure = i;
                }
            }
            if (repeatBeganInMeasure>-1 && repeatEndedInMeasure >-1) { // There is a self-contained repeat
                MusicLine beforeRepeat = subLine(line, 0, repeatBeganInMeasure);
                MusicLine repeat = subLine(line, repeatBeganInMeasure, repeatEndedInMeasure+1);
                MusicLine afterRepeat = subLine(line, repeatEndedInMeasure+1, measures.size());
                
                linesInVoices.get(currentVoiceName).add(beforeRepeat);
                linesInVoices.get(currentVoiceName).add(repeat);
                if(firstEndingBeginsAtMeasure>-1) {
                    MusicLine repeatWithoutFirstEnding = subLine(line, repeatBeganInMeasure, firstEndingBeginsAtMeasure);
                    linesInVoices.get(currentVoiceName).add(repeatWithoutFirstEnding);
                } else {
                    linesInVoices.get(currentVoiceName).add(repeat);
                }
                linesInVoices.get(currentVoiceName).add(afterRepeat);

            
            } else { // If we made it through all the above code, then it's just a normal line and we don't have to worry about repeats
                linesInVoices.get(currentVoiceName).add(new MusicLine(currentVoiceName, measuresInLine, lyricsInLine));
            }
		}
            
	}

	@Override public void enterNote_element(ABCMusicParser.Note_elementContext ctx) { }
	@Override public void exitNote_element(ABCMusicParser.Note_elementContext ctx) {
        // Examine if base note is lowercase to increase octave and
        // Change recognition of base note to upper case
        if ((int) ctx.BASE_NOTE().getText().charAt(0) >= 97) {
            baseNote = ctx.BASE_NOTE().getText().substring(0, 1).toUpperCase();
            octaveInt = 1;
        } else {
            // base note is just equal to the note that's already upper case 
            baseNote = ctx.BASE_NOTE().getText().substring(0,1); 
            octaveInt = 0;
        }

        if (ctx.ACCIDENTAL() != null) {
            // Change the accidental token into corresponding integer value
            switch (ctx.ACCIDENTAL().getText()) {
            case "^":
                accidental = 1;
                break;
            case "^^":
                accidental = 2;
                break;
            case "=":
                accidental = 0;
                break;
            case "_":
                accidental = -1;
                break;
            case "__":
                accidental = -2;
                break;
            }
            // Add accidental to the accidental in measure list
            if (accidentalsInMeasure.containsKey(baseNote)) {
                accidentalsInMeasure.remove(baseNote);
            }
            accidentalsInMeasure.put(baseNote, accidental);
        } else if (keySignature != "C" && keySignature != "Am") {
            // Find the accidental by looking through two hashmaps
            // keys and keyNotes hashmaps are made in enterTune()
            // Check whether current base note needs a new accidental according
            // to the current key signature
            if (keys.get(keySignature).get(baseNote) != null) {
                accidental = keys.get(keySignature).get(baseNote);
            } else {
                // Set accidental to 0 if current note is not in hashmap 
                accidental = 0;
            }
        } else if (accidentalsInMeasure.containsKey(baseNote)) {
            // This note has an accidental in this measure, so change it accordingly
            accidental = accidentalsInMeasure.get(baseNote);
        } else {
            // Set accidental back to 0 if key signature is C or A minor
            accidental = 0;
        }
     
        if (ctx.OCTAVE() != null) {
            // Find the octave token in terms of corresponding integer value
            String octave = ctx.OCTAVE().getText();
            if (octave.contains(",")) {
                octaveInt -= octave.length();
            } else if (octave.contains("'")) {
                octaveInt += octave.length();
            }
        }
        if (ctx.DURATION() != null) {
            givenDuration = new Duration(ctx.DURATION().toString());
        } else {
            // Set default Duration to be fixed relatively
            givenDuration = new Duration(1, 1);
        }
        // Find new Duration, changed by the default note length and meter
        // definitions
        Duration newDuration = new Duration(givenDuration.numerator * defaultNoteLength.numerator * meter.numerator,
                givenDuration.denominator * defaultNoteLength.denominator * meter.denominator);


        // Construct a new Note
        ticksPerBeat = lcm(ticksPerBeat, newDuration.denominator);
        Note note = new Note(baseNote.charAt(0), octaveInt, accidental, newDuration);
        if (inChord) {
            elementsInChord.add(note);
        } else if (inTuplet) {
            elementsInTuplet.add(note);
        } else {
            // Add Note to the Measure list
            elementsInMeasure.add(note);
        }
    }

	@Override public void enterTune(ABCMusicParser.TuneContext ctx) { 
	    //Add hashmap of notes and sharps or flats
	    keyNotes.put("F",1); 
	    // Make values for keys hashmap
	    keys.put("G", keyNotes);
	    keys.put("Em", keyNotes); 
	    // Create new hashmap by adding one more value to the old one 
	    HashMap<String,Integer> keyNotesD = new HashMap<String,Integer>(keyNotes);
	    keyNotesD.put("C",1); 
	    keys.put("D", keyNotesD); 
	    keys.put("Bm", keyNotesD);
	    
	    // New hashmap must be created for each corresponding key signature
	    HashMap<String,Integer> keyNotesA = new HashMap<String,Integer>(keyNotesD);
	    keyNotesA.put("G",1); 
	    keys.put("A", keyNotesA);
	    keys.put("F#m", keyNotesA); 
	    
	    HashMap<String,Integer> keyNotesE = new HashMap<String,Integer>(keyNotesA);
	    keyNotesE.put("D",1); 
	    keys.put("E", keyNotesE); 
	    keys.put("C#m", keyNotesE);
	    
	    HashMap<String,Integer> keyNotesB = new HashMap<String,Integer>(keyNotesE);
	    keyNotesB.put("A", 1); 
	    keys.put("B", keyNotesB); 
	    keys.put("G#m", keyNotesB); 
	    
	    HashMap<String,Integer> keyNotesFSharp = new HashMap<String,Integer>(keyNotesB);
	    keyNotesFSharp.put("E", 1); 
	    keys.put("F#", keyNotesFSharp);
	    keys.put("D#m", keyNotesFSharp); 
	    
	    HashMap<String,Integer> keyNotesCSharp = new HashMap<String,Integer>(keyNotesFSharp);
	    keyNotesCSharp.put("B",1); 
	    keys.put("C#", keyNotesCSharp); 
	    keys.put("A#m", keyNotesCSharp); 

	    // Now create hashmaps for flat key signatures
	    keyFlats.put("B", -1); 
	    keys.put("F", keyFlats); 
	    keys.put("Dm", keyFlats);
	    
	    HashMap<String, Integer> keyFlatsB = new HashMap<String, Integer>(keyFlats);
	    keyFlatsB.put("E",-1); 
	    keys.put("Bb", keyFlatsB);
	    keys.put("Gm", keyFlatsB); 
	    
        HashMap<String, Integer> keyFlatsE = new HashMap<String, Integer>(keyFlatsB);
        keyFlatsE.put("A", -1);
        keys.put("Eb", keyFlatsE);
        keys.put("Cm", keyFlatsE); 
        
        HashMap<String, Integer> keyFlatsA = new HashMap<String, Integer>(keyFlatsE);
        keyFlatsA.put("D", -1); 
        keys.put("Ab", keyFlatsA);
        keys.put("Fm", keyFlatsA);
        
        HashMap<String, Integer> keyFlatsD = new HashMap<String, Integer>(keyFlatsA);
        keyFlatsD.put("G", -1);
        keys.put("Db", keyFlatsD);
        keys.put("Bbm", keyFlatsD);
        
        HashMap<String, Integer> keyFlatsG = new HashMap<String, Integer>(keyFlatsD);
        keyFlatsG.put("C", -1); 
        keys.put("Gb", keyFlatsG);
        keys.put("Ebm", keyFlatsG); 
        
        HashMap<String, Integer> keyFlatsC = new HashMap<String, Integer>(keyFlatsG);
        keyFlatsC.put("F", -1); 
        keys.put("Cb", keyFlatsC);
        keys.put("Abm", keyFlatsC);
	    
	}
	@Override public void exitTune(ABCMusicParser.TuneContext ctx) {
        voices = new ArrayList<Voice>();
        for(int i=0; i<voiceNames.size(); i++) {
           String voiceName = voiceNames.get(i);
           List<MusicLine> lines = linesInVoices.get(voiceName);
           voices.add(new Voice(lines));
        }
        music = new PieceOfMusic(header, voices, ticksPerBeat); 
	    
	}

	@Override public void enterChord(ABCMusicParser.ChordContext ctx) {
	    elementsInChord = new ArrayList<Note>();
	    inChord = true; 
	}
	@Override public void exitChord(ABCMusicParser.ChordContext ctx) {
	    // Construct a new Chord while
	    // setting duration of Chord to the duration of the first Note
		Duration chordDuration = elementsInChord.get(0).getDuration();
		ticksPerBeat = lcm(ticksPerBeat, chordDuration.denominator);
	    MusicElement chord = new Chord(elementsInChord, chordDuration); 
	    // Add Chord to Measure list
	    elementsInMeasure.add(chord);
	    inChord = false;
	}

	@Override public void enterHeader(ABCMusicParser.HeaderContext ctx) {}
	@Override public void exitHeader(ABCMusicParser.HeaderContext ctx) {
        // Store meter and default note length as instances of Duration
        // in order to fix the duration of notes and access numerators and
        // denominators
	    String meterStr = ctx.F_METER().toString();
	    meterStr = meterStr.substring(Math.max(meterStr.indexOf(':')+1, 0), meterStr.indexOf('\n'));
        if (meterStr.contains("C")) {
            meter = new Duration(4, 4);
        } else if (meterStr.contains("C|")) {
            meter = new Duration(2, 4);
        } else {
        	meterStr = meterStr.replaceAll("\\s","");
            meter = new Duration(meterStr);
        }
	    if(ctx.LENGTH().toString().equals("[]")) {
	    	if ((float)meter.numerator/meter.denominator >= 0.75) {
	    		defaultNoteLength = new Duration(1, 8);
	    	} else {
	    		defaultNoteLength = new Duration(1, 16);
	    	}
	    } else {
	        String lengthStr = ctx.LENGTH().toString().replaceAll("\\s", "");
	        lengthStr = lengthStr.substring(lengthStr.indexOf(':')+1, lengthStr.indexOf(']'));
	        defaultNoteLength = new Duration(lengthStr);
	    }
        // Store tempo as integer value
        String tempoAsString = ctx.F_TEMPO().toString().replaceAll("\\s", ""); 
        Duration tempo;
        if (tempoAsString.contains("=") && tempoAsString.contains("/")) {
            // Make tempo a Duration object to access numerator and denominator of tempo
            tempo =  new Duration(tempoAsString.substring(tempoAsString.indexOf(":") + 1, 
            		tempoAsString.indexOf("=")));  
            tempoValue = Integer.parseInt(tempoAsString.substring(tempoAsString.indexOf("=") + 1, 
            		tempoAsString.indexOf("]")));
        } else {
            tempoValue = 100; 
            tempo = new Duration(1, defaultNoteLength.denominator); 
        }
        
            // Find the beats per minute to be passed later into SequencePlayer 
            beatsPerMinute = (int) tempo.numerator * tempoValue * meter.numerator / meter.denominator / tempo.denominator ; 
         
        
        // Store key value for later access to hashmap when creating Notes
        keySignature = ctx.F_KEY().toString().replaceAll("\\s+",""); 
        String parsedKey = keySignature.substring(keySignature.indexOf(":")+1); 
        // Check whether key signature has valid note
        if (keys.containsKey(parsedKey)) {
            keySignature = parsedKey; 
        } else {
            //Assume there is a key of C if there is none specified
            keySignature = "C"; 
        }
        
        // Store other values as Strings
        String headerNumber = ctx.NUMBER().toString();
        String headerTitle = ctx.TITLE().toString();
        String headerComposer = ctx.LENGTH().toString();
        List<TerminalNode> headerVoices = ctx.VOICE();
        voiceNames = new ArrayList<String>();
        linesInVoices = new HashMap<String, List<MusicLine>>();
        if (headerVoices.size() == 0) {
            linesInVoices.put("default", new ArrayList<MusicLine>()); 
            voiceNames.add("default"); 
            insideRepeat.put("default", true);
        }
        for(int i=0; i<headerVoices.size(); i++) {
            String voice = headerVoices.get(i).getText();
            voice = voice.substring(voice.indexOf(':')+1, voice.indexOf('\n'));
            linesInVoices.put(voice, new ArrayList<MusicLine>());
            voiceNames.add(voice);
            insideRepeat.put(voice, true);
        }

        // Construct new Music Header
        header = new MusicHeader(headerNumber, headerTitle, headerComposer, defaultNoteLength, voiceNames,
                meter, tempoAsString, beatsPerMinute);
    }

	@Override public void enterNote_or_rest(ABCMusicParser.Note_or_restContext ctx) { }
	@Override public void exitNote_or_rest(ABCMusicParser.Note_or_restContext ctx) { }

	@Override public void enterRest_element(ABCMusicParser.Rest_elementContext ctx) { }
	@Override public void exitRest_element(ABCMusicParser.Rest_elementContext ctx) {
        if (ctx.DURATION() != null) {
            givenDuration = new Duration(ctx.DURATION().toString());
        } else {
            // Set default Duration to be fixed relatively 
            givenDuration = new Duration(1, 1);
        }
        // Find new Duration, changed by the default note length and meter definitions
        Duration newDuration = new Duration(givenDuration.numerator * defaultNoteLength.numerator * meter.numerator,
                givenDuration.denominator * defaultNoteLength.denominator * meter.denominator);
        // Construct new instance of Rest by creating new instance of Duration
        ticksPerBeat = lcm(ticksPerBeat, newDuration.denominator);
        MusicElement rest = new Rest(newDuration);
        // Add new element of Rest to Measure list
        elementsInMeasure.add(rest);
	}

	@Override public void enterTriplet(ABCMusicParser.TripletContext ctx) {
	    inTuplet = true;
	    elementsInTuplet = new ArrayList<MusicElement>();
	}
	@Override public void exitTriplet(ABCMusicParser.TripletContext ctx) {
	    inTuplet = false;
        Duration multiplier = new Duration(2, 3);
        for(int i=0; i<elementsInTuplet.size(); i++) {
            MusicElement note = elementsInTuplet.get(i);
            elementsInMeasure.add(note.elementWithMultipliedDuration(multiplier));
        }
	}

	@Override public void enterTuplet(ABCMusicParser.TupletContext ctx) { }
	@Override public void exitTuplet(ABCMusicParser.TupletContext ctx) { }

	@Override public void enterPiece(ABCMusicParser.PieceContext ctx) { }
	@Override public void exitPiece(ABCMusicParser.PieceContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
	
	public PieceOfMusic getPieceOfMusic() {
		return music;
	}
//the following methods are found at http://stackoverflow.com/questions/4201860/how-to-find-gcf-lcm-on-a-set-of-numbers

private static int gcd(int a, int b)
{
    while (b > 0)
    {
        int temp = b;
        b = a % b; // % is remainder
        a = temp;
    }
    return a;
}

private static int gcd(int[] input)
{
    int result = input[0];
    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
    return result;
}

private static int lcm(int a, int b)
{
	float out = a*(b / (float)gcd(a, b));
    return (int)out;
}

private static int lcm(int[] input)
{
    int result = input[0];
    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
    return result;
}

private MusicLine subLine(MusicLine line, int startMeasure, int endMeasure) {
    List<Lyric> lyrics = line.getLyrics();
    List<Measure> measures = line.getMeasures();
    startMeasure = Math.max(startMeasure, 0);
    
    endMeasure = Math.min(endMeasure, measures.size());
    int startLyric = line.countNotesBeforeBeginningOfMeasure(startMeasure);
    int endLyric = line.countNotesBeforeBeginningOfMeasure(endMeasure);

    startLyric = Math.max(startLyric, 0);
    endLyric = Math.min(endLyric, lyrics.size()-1);
    List<Measure> subMeasures = measures.subList(startMeasure, endMeasure);
    List<Lyric> subLyrics;

    if(lyrics.size()>0) subLyrics = lyrics.subList(startLyric, endLyric+1);
    else subLyrics = new ArrayList<Lyric>();
    for(int i=0; i<subLyrics.size(); i++) {
    }
    if(subMeasures.size()==0) {
    }
    return new MusicLine(currentVoiceName, subMeasures, subLyrics);

}

}
