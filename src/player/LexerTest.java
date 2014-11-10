package player;
import static org.junit.Assert.assertEquals;
import grammar.ABCMusicLexer;

import java.io.File;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

/*
 * testing strategy: test that each token is recognized by the lexer, as well
 *  as more complex sequences of tokens.
 *  
 *  We've divided this up into sections that correspond to the different types of tokens in our lexer.
 */

public class LexerTest {
	
	//Header Labels
    @Test 
    public void composerLabelTokenTest() {
    	verifyLexer("C:", new String[] {"C:"});
    }
    
    @Test
    public void testTitleLabelToken() {
        verifyLexer("T :", new String[] {"T :"});
    }
    
    @Test
    public void testLengthLabelToken() {
        verifyLexer("L  :  ", new String[] {"L  :  "});
    }
    
    @Test
    public void testVoiceLabelToken() {
        verifyLexer("V:", new String[] {"V:"});
    }
    
    @Test
    public void testMeterLabelToken() {
        verifyLexer("M : ", new String[] {"M : "});
    }
    
    @Test
    public void testKeyLabelToken() {
        verifyLexer("K : ", new String[] {"K : "});
    }
    
    @Test
    public void testTempoLabelToken() {
        verifyLexer("T : ", new String[] {"T : "});
    }
    
    @Test
    public void testLyricLabelToken() {
        verifyLexer("w : ", new String[] {"w : "});
    }
	//header fields
	
    @Test 
    public void numberTokenTest() {
    	verifyLexer("X : 3019\n" , new String[] {"X : 3019\n"});
    }
    
    @Test 
    public void titleTokenTest() {
    	verifyLexer("T : It's a small-world after all\n" , new String[] {"T : It's a small-world after all\n"});
    }
	
    @Test 
    public void composerTokenTest() {
    	System.out.println("composer");
    	verifyLexer("C: Ludwig von Beethoven, Jr.\n" , new String[] {"C: Ludwig von Beethoven, Jr.\n"});
    }
    
    @Test 
    public void lengthTokenTest() {
    	verifyLexer("L: 1/8\n" , new String[] {"L: 1/8\n"});
    }
    
    @Test 
    public void voiceTokenTest() {
    	verifyLexer("V: lower\n" , new String[] {"V: lower\n"});
    }
    
    @Test
    public void testFMeterCommonTimeToken() {
        verifyLexer("M: C|\n", new String[] {"M: C|\n"});
    }
    
    @Test
    public void testFTempoToken() {
        verifyLexer("Q: 3 / 2 = 212\n", new String[] {"Q: 3 / 2 = 212\n"});
    }
    
    @Test
    public void testFKeyToken() {
        verifyLexer("K: F\n", new String[] {"K: F\n"});
    }
    
    
    //Lyric Token
    
    @Test
    public void testLyricToken() {
    	System.out.println("test");
        verifyLexer("w: h 76g57652in-the~town*Where I was_\\-\n", new String[] 
        		{"w: h 76g57652in-the~town*Where I was_\\-\n"});
    }
    
//musical attributes
    
	
    @Test
    public void testSharpToken() {
        verifyLexer("^", new String[] {"^"});
    }
    
    @Test
    public void testFlatToken() {
        verifyLexer("_", new String[] {"_"});
    }
    
    @Test
    public void testBaseNoteUppercaseToken() {
        verifyLexer("B", new String[] {"B"});
    }
    
    @Test
    public void testBaseNoteLowercaseToken() {
        verifyLexer("g", new String[] {"g"});
    }
    
    @Test
    public void testRestToken() {
        verifyLexer("z", new String[] {"z"});
    }

    @Test
    public void testDupletSpecToken() {
        verifyLexer("(2", new String[] {"(2"});
    }
    
    @Test
    public void testTripletSpecToken() {
        verifyLexer("(3", new String[] {"(3"});
    }
    
    @Test
    public void testQradrupletSpecToken() {
        verifyLexer("(4", new String[] {"(4"});
    }
    
    @Test
    public void testOctaveHigherToken() {
        verifyLexer("''", new String[] {"''"});
    }
    
    @Test
    public void testOctaveLowerToken() {
        verifyLexer(",,", new String[] {",,"});
    }
    
    
    @Test
    public void testTempoToken() {
        verifyLexer("1/2 =10", new String[] {"1/2 =10"});
    }
    
    
    @Test
    public void testDurationFractionToken() {
        verifyLexer("3/2", new String[] {"3/2"});
    }
    
    //duration tests 
    
    @Test
    public void testDurationIntegerToken() {
        verifyLexer("5", new String[] {"5"});
    }
    
    @Test
    public void testDurationSlashToken() {
        verifyLexer("/", new String[] {"/"});
    }
    
    @Test
    public void testDurationDenominatorToken() {
        verifyLexer("/3", new String[] {"/3"});
    }
    
    @Test
    public void testDurationNumeratorToken() {
        verifyLexer("5/", new String[] {"5/"});
    }
    
    //barlines 
    
    @Test
    public void testBarlineToken() {
        verifyLexer("|", new String[] {"|"});
    }
    
    @Test
    public void testDoubleBarlineToken() {
        verifyLexer("||", new String[] {"||"});
    }
    
    @Test
    public void testBarlineRepeatToken() {
        verifyLexer(":|", new String[] {":|"});
    }

//Open and Close brackets
    
    @Test
    public void testOpenParenToken() {
        verifyLexer("[", new String[] {"["});
    }
    
    @Test
    public void testCloseParenToken() {
        verifyLexer("]", new String[] {"]"});
    }
    
//Repeat tokens
    
    @Test
    public void testNthRepeatToken() {
        verifyLexer("[2", new String[] {"[2"});
    }
    
//    @Test
//    public void testMeterTimeSignatureToken() {
//        verifyLexer("3/4", new String[] {"3/4"});
//    }
    
    //key accidentals
    
    @Test
    public void testKeyAccidentalSharpToken() {
        verifyLexer("#", new String[] {"#"});
    }
    
    @Test
    public void testKeyAccidentalFlatToken() {
        verifyLexer("b", new String[] {"b"});
    }
    
    @Test
    public void testModeMinorToken() {
        verifyLexer("m", new String[] {"m"});
    }
    
    //comments, whitespaces, end of line
    
    @Test
    public void testCommentToken() {
        verifyLexer("% Hello, world.  Nice Weather!31@ \n", new String[] {"% Hello, world.  Nice Weather!31@ \n"});
    }
    
    @Test
    public void testWhitespaceToken() {
        verifyLexer("     ", new String[] {"     "});
    }
    
    @Test
    public void testEndOfLineToken() {
        verifyLexer("\r\n", new String[] {"\r\n"});
    }
    
    //combination tests - test to see if lexer can separate series of tokens from a complex string
    
    @Test
    public void testCombinationWithComment() {
        verifyLexer("[A2B3/ C/2% WHAT's up, dude? \n", new String[] {"[", "A", "2", "B", "3/", " ",
        		"C", "/2","% WHAT's up, dude? \n"});
    }
    
    @Test
    public void testCombinationMusicLine() {
        verifyLexer("[f2b] ^d'2 E,/3 \n", new String[] {"[", "f", "2", "b", "]", " ",
        		"^", "d", "'","2", " ", "E", ",", "/3", " ", "\n"});
    }
    
    @Test
    public void testCombinationMusicBars() {
        verifyLexer("A b __c | d E,2 f'3/4 ||\n", new String[] {"A", " ", "b", " ", "__", "c", " ", "|",
        		" ", "d", " ","E", ",", "2", " ", "f", "'", "3/4", " ", "||", "\n"});			
    }
    
    @Test
    public void testCombinationMusicLyrics() {
        verifyLexer("d2b ^e | z C,,4 g''1/4 ||\nw:6.005 is awe-some.\n", new String[] {"d", "2", "b", " ", "^", "e", " ", "|",
        		" ", "z", " ","C", ",,", "4", " ", "g", "''", "1/4", " ", "||", "\n", "w:6.005 is awe-some.\n"});			
    }
    
//verifyLexer function

    public void verifyLexer(String input, String[] expectedTokens) {
        CharStream stream = new ANTLRInputStream(input);
        ABCMusicLexer lexer = new ABCMusicLexer(stream);
        lexer.reportErrorsAsExceptions();
        List<? extends Token> actualTokens = lexer.getAllTokens();
        System.out.println(actualTokens);
        assertEquals(expectedTokens.length, actualTokens.size());
        
        for(int i = 0; i < actualTokens.size(); i++) {
             String actualToken = actualTokens.get(i).getText();
             String expectedToken = expectedTokens[i];
             assertEquals(actualToken, expectedToken);
        }
    }

    }
    