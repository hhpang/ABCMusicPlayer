/*    
    public static void demo() throws MidiUnavailableException, InvalidMidiDataException { 
        LyricListener listener = new LyricListener() {
            public void processLyricEvent(String text) {
                System.out.println(text);
            }
        };
        
        List<Lyric> lyrics = new ArrayList<Lyric>();
        lyrics.add(new Lyric("Oh", 2));
        lyrics.add(new Lyric("say", 1));
        lyrics.add(new Lyric("can", 1));
        lyrics.add(new Lyric("you", 1));
        lyrics.add(new Lyric("see", 1));
        lyrics.add(new Lyric("by", 1));
        lyrics.add(new Lyric("the", 1));
        lyrics.add(new Lyric("dawn's", 1));
        lyrics.add(new Lyric("ear", 1));
        lyrics.add(new Lyric("ly", 1));
        lyrics.add(new Lyric("light", 1));
        
        LyricsIterator lyricsIter = new LyricsIterator(lyrics);
        
        //create sequence player
        SequencePlayer player = new SequencePlayer(80, 4, listener);
        
        //list of durations that we have in this piece
        Duration wholeBeat = new Duration(1, 1);
        Duration twoBeats = new Duration(2, 1);
        Duration threeBeats = new Duration(3, 1);
        Duration threeQuarterBeat = new Duration(3, 4);
        Duration oneQuarterBeat = new Duration (1, 4);

//TREBLE VOICE
//measure1        
        Rest trebleNote1 = new Rest(twoBeats);
        Note trebleNote2 = new Note('G', 0, 0, threeQuarterBeat);
        Note trebleNote3 = new Note('E', 0, 0, oneQuarterBeat);
        List<MusicElement> trebleMeasureList1 = new ArrayList<MusicElement>();
        trebleMeasureList1.add(trebleNote1);
        trebleMeasureList1.add(trebleNote2);
        trebleMeasureList1.add(trebleNote3);
        Measure trebleMeasure1 = new Measure(trebleMeasureList1,
                false, false);

//measure2
        Note trebleNote4 = new Note('C', 0, 0, wholeBeat);
        ArrayList<Note> chordList1 = new ArrayList<Note>();
        chordList1.add(new Note('E', 0, 0, wholeBeat));
        chordList1.add(new Note('C', 0, 0, wholeBeat));
        chordList1.add(new Note('G', -1, 0, wholeBeat));
        Chord trebleChord1 = new Chord(chordList1, wholeBeat);
        
        ArrayList<Note> chordList5 = new ArrayList<Note>();
        chordList5.add(new Note('G', 0, 0, wholeBeat));
        chordList5.add(new Note('G', -1, 0, wholeBeat));
        chordList5.add(new Note('D', 0, 0, wholeBeat));
        Chord trebleChord2 = new Chord(chordList5, wholeBeat);
        
        List<MusicElement> trebleMeasureList2 = Arrays.asList(new MusicElement[] {
                trebleNote4, trebleChord1, trebleChord2});
        Measure trebleMeasure2 = new Measure(trebleMeasureList2,
                false, false);

//measure3
        
        ArrayList<Note> chordList6 = new ArrayList<Note>();
        chordList6.add(new Note('C', 1, 0, twoBeats));
        chordList6.add(new Note('E', 0, 0, twoBeats));
        chordList6.add(new Note('C', 0, 0, twoBeats));
        Chord trebleChord3 = new Chord(chordList6, twoBeats);

        Note trebleNote5 = new Note('E', 1, 0, threeQuarterBeat);       
        Note trebleNote6 = new Note('D', 1, 0, oneQuarterBeat);
        
        List<MusicElement> trebleMeasureList3 = Arrays.asList(new MusicElement[] {
                trebleChord3, trebleNote5, trebleNote6});
        Measure trebleMeasure3 = new Measure(trebleMeasureList3,
                false, false);
        
//measure4
        
        ArrayList<Note> chordList7 = new ArrayList<Note>();
        chordList7.add(new Note('C', 1, 0, wholeBeat));
        chordList7.add(new Note('E', 0, 0, wholeBeat));
        chordList7.add(new Note('C', 0, 0, wholeBeat));
        Chord trebleChord4 = new Chord(chordList7, wholeBeat);
        
        ArrayList<Note> chordList8 = new ArrayList<Note>();
        chordList8.add(new Note('E', 0, 0, wholeBeat));
        chordList8.add(new Note('C', 0, 0, wholeBeat));
        Chord trebleChord5 = new Chord(chordList8, wholeBeat);
        
        ArrayList<Note> chordList9 = new ArrayList<Note>();
        chordList9.add(new Note('F', 0, 1, wholeBeat));
        chordList9.add(new Note('C', 0, 0, wholeBeat));
        Chord trebleChord6 = new Chord(chordList9, wholeBeat);
        
        List<MusicElement> trebleMeasureList4 = Arrays.asList(new MusicElement[] {
                trebleChord4, trebleChord5, trebleChord6});
        Measure trebleMeasure4 = new Measure(trebleMeasureList4,
                false, false);
        
 // measure 5
        
        ArrayList<Note> chordList10 = new ArrayList<Note>();
        chordList10.add(new Note('B', -1, 0, twoBeats));
        chordList10.add(new Note('G', 0, 0, twoBeats));
        Chord trebleChord7 = new Chord(chordList10, twoBeats);
        
        Rest trebleNote7 = new Rest(twoBeats);
        
        List<MusicElement> trebleMeasureList5 = Arrays.asList(new MusicElement[] {
                trebleChord7, trebleNote7});
        Measure trebleMeasure5 = new Measure(trebleMeasureList5,
                false, false);
        
        //list of all the treble measures
        List<Measure> trebleMeasureList = Arrays.asList(new Measure[] {
                trebleMeasure1, trebleMeasure2, trebleMeasure3, trebleMeasure4,
                trebleMeasure5});
        
        //create treble Voice
        Voice TrebleVoice = new Voice(trebleMeasureList);
        
        //add all the treble measures to the player
        int count = 0;
        for (int i=0 ; i < trebleMeasureList.size() ; i++) {
            count = trebleMeasureList.get(i).addToPlayer(player, count, 4, lyricsIter);
            
        }
        
        
//BASS VOICE
        
        //measure1
        Rest bassNote1 = new Rest(twoBeats);
        Note bassNote2 = new Note('G', -2, 0, threeQuarterBeat);
        Note bassNote3 = new Note('E', -2, 0, oneQuarterBeat);
        List<MusicElement> bassMeasureList1 = Arrays.asList(bassNote1, bassNote2, bassNote3);
        Measure bassMeasure1 = new Measure(bassMeasureList1, false, false);
        
        
        //measure2
        Note bassNote4 = new Note('C', -2, 0, wholeBeat);
        Note bassNote5 = new Note('G', -1, 0, wholeBeat);
        Note bassNote6 = new Note('B', -2, 0, wholeBeat);
        List<MusicElement> bassMeasureList2 = Arrays.asList(new MusicElement[] 
                {bassNote4, bassNote5, bassNote6});
        Measure bassMeasure2 = new Measure(bassMeasureList2, false, false);
        
        //measure3
        Note bassNote7 = new Note('A', -2, 0, twoBeats);
        ArrayList<Note> chordList11 = new ArrayList<Note>();
        chordList11.add(new Note('B', 1, 0, wholeBeat));
        chordList11.add(new Note('E', 0, 0, wholeBeat));
        chordList11.add(new Note('A', -2, -1, wholeBeat));
        Chord baseChord1 = new Chord(chordList11, wholeBeat);
        List<MusicElement> bassMeasureList3 = Arrays.asList(new MusicElement[] 
                {bassNote7, baseChord1});
        Measure bassMeasure3 = new Measure(bassMeasureList3, false, false);
        
        
        //measure4
       
        Note bassNote9 = new Note('A', -2, 0, twoBeats);
        Note bassNote10 = new Note('D', -2, 0, wholeBeat);
        List<MusicElement> bassMeasureList4 = Arrays.asList(new MusicElement[] 
                {bassNote9, bassNote10});
        Measure bassMeasure4 = new Measure(bassMeasureList4, false, false);
        
        //measure5
        Note bassNote11 = new Note('G', -2, 0, threeBeats);
        Rest bassNote12 = new Rest(wholeBeat);
        List<MusicElement> bassMeasureList5 = Arrays.asList(new MusicElement[] 
                {bassNote11, bassNote12});
        Measure bassMeasure5 = new Measure(bassMeasureList5, false, false);
 
        //list of all of the bass measures
        List<Measure> bassMeasureList = Arrays.asList(new Measure[] {
                bassMeasure1, bassMeasure2, bassMeasure3, bassMeasure4,
                bassMeasure5});
        
        //construct voice from list of bass measures
        Voice bassVoice = new Voice(bassMeasureList);
        
        //add bass measures to player
        int bassCount = 0;
        for (int i=0 ; i < bassMeasureList.size() ; i++) {
            bassCount = bassMeasureList.get(i).addToPlayer(player, bassCount, 4);
        }
        player.play();
        

    }
    
    */
    

