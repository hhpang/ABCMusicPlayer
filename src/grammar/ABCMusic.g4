/**
 * This file is the grammar file used by ANTLR.
 *
 * In order to compile this file, navigate to this directory
 * (<src/grammar>) and run the following command:
 *
 * java -jar ../../antlr.jar ABCMusic.g4
 */

grammar ABCMusic;

/*
 * This puts 'package grammar;' at the top of the output Java files.
 * Do not change these lines unless you know what you're doing.
 */
@header {
package grammar;
}

/*
 * This adds code to the generated lexer and parser. This makes the lexer and
 * parser throw errors if they encounter invalid input. Do not change these
 * lines unless you know what you're doing.
 */
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        removeErrorListeners();
        addErrorListener(new ExceptionThrowingErrorListener());
    }

    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 */

NUMBER_LABEL : 'X' ' '* ':' ' '*;
TITLE_LABEL : 'T' ' '* ':' ' '*;
COMPOSER_LABEL : 'C' ' '* ':' ' '*;
LENGTH_LABEL : 'L' ' '* ':' ' '*;
VOICE_LABEL : 'V' ' '* ':' ' '*;
METER_LABEL : 'M' ' '* ':' ' '*;
TEMPO_LABEL : 'Q' ' '* ':' ' '*;
KEY_LABEL : 'K' ' '* ':' ' '*;
LYRIC_LABEL: 'w' ' '* ':' ' '*;

// Recognize whatever comes after the corresponding labels and before linefeeds 
NUMBER	: NUMBER_LABEL ~[\r\n%]+ END_OF_LINE; 
TITLE	: TITLE_LABEL ~[\r\n%]+ END_OF_LINE; 
COMPOSER: COMPOSER_LABEL ~[\r\n%]+  END_OF_LINE;
LENGTH	: LENGTH_LABEL ~[\r\n%]+ END_OF_LINE; 
VOICE	: VOICE_LABEL ~[\r\n%]+ END_OF_LINE;   
F_METER : METER_LABEL ~[\r\n%]+ END_OF_LINE;
F_TEMPO	: TEMPO_LABEL ~[\r\n%]+ END_OF_LINE; 
F_KEY	: KEY_LABEL ~[\r\n%]+ END_OF_LINE; 

LYRIC	: LYRIC_LABEL ~[\r\n%]+ END_OF_LINE; 

// These are the tokens that deal with certain aspects of notes and music
ACCIDENTAL	: '^'|'_'| '^^' | '__' | '=' ;
BASE_NOTE	: [A-G] | [a-g] ;
REST		: 'z';
DUPLET_SPEC : '(' WHITESPACE* '2' ;
TRIPLET_SPEC : '(' WHITESPACE*  '3' ;
QUADRUPLET_SPEC : '(' WHITESPACE* '4' ;
OCTAVE		: /[,]+/ | /[']+/  ; 
TEMPO		: [0-9]+ ' '* '/' ' '* [0-9]+ ' '* '=' ' '* [0-9]+ ;
DURATION	: [0-9]* '/' [0-9]* |[0-9]+ ; 
BARLINE		: '|' | '||' | '[|' | '|]' | ':|' | '|:'; 

OPENPAREN	: '[';
CLOSEPAREN  : ']' ; 

NTH_REPEAT	: '[1' | '[2' ; 
KEY_ACCIDENTAL: '#' | 'b' ; 
MODE_MINOR  : 'm' ; 
COMMENT		: '%\n'| '%' .*? '\n'; 
WHITESPACE  :  [ \t]+;

// The mark of the end of a line can be a linefeed or a comment 
END_OF_LINE	: '\n' | '\r\n' | COMMENT; 


/* Each field below has one name character, followed by text or digit, followed by 
   an end-of-line (either linefeed or comment) */ 

/*
 * These are the parser rules. They define the structures used by the parser.
 *
 * You should make sure you have one rule that describes the entire input.
 * This is the 'start rule'. The start rule should end with the special
 * predefined token EOF so that it describes the entire input. 
 *
 * For more information, see
 * http://www.antlr.org/wiki/display/ANTLR4/Parser+Rules#ParserRules-StartRulesandEOF
 */
 
// 'tune' is the starter 
tune	: header piece END_OF_LINE* EOF;

piece   : music END_OF_LINE*;
header	: NUMBER TITLE (COMPOSER | LENGTH | VOICE | F_METER | F_TEMPO | COMMENT | END_OF_LINE)* F_KEY ;
music	: line+ END_OF_LINE*; 

line	: COMMENT? VOICE? BARLINE? measure+ END_OF_LINE (LYRIC)? END_OF_LINE* | END_OF_LINE+;
measure : element+ BARLINE WHITESPACE* ;
element : note_or_rest | NTH_REPEAT | WHITESPACE ;


note_or_rest: note_element | rest_element | tuplet | chord ;
note_element: ACCIDENTAL? WHITESPACE* BASE_NOTE WHITESPACE* OCTAVE? WHITESPACE* DURATION?; 
rest_element: REST WHITESPACE* DURATION?;
chord : OPENPAREN WHITESPACE* note_element WHITESPACE* note_element+ WHITESPACE* CLOSEPAREN ;

tuplet : duplet | triplet | quadruplet ;

duplet : DUPLET_SPEC WHITESPACE* note_element WHITESPACE* note_element ;
triplet: TRIPLET_SPEC WHITESPACE* note_element WHITESPACE* note_element WHITESPACE* note_element ;
quadruplet : QUADRUPLET_SPEC WHITESPACE* note_element WHITESPACE* note_element WHITESPACE* note_element WHITESPACE* note_element ;


		 
