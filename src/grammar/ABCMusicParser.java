// Generated from ABCMusic.g4 by ANTLR 4.0

package grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ABCMusicParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER_LABEL=1, TITLE_LABEL=2, COMPOSER_LABEL=3, LENGTH_LABEL=4, VOICE_LABEL=5, 
		METER_LABEL=6, TEMPO_LABEL=7, KEY_LABEL=8, LYRIC_LABEL=9, NUMBER=10, TITLE=11, 
		COMPOSER=12, LENGTH=13, VOICE=14, F_METER=15, F_TEMPO=16, F_KEY=17, LYRIC=18, 
		ACCIDENTAL=19, BASE_NOTE=20, REST=21, DUPLET_SPEC=22, TRIPLET_SPEC=23, 
		QUADRUPLET_SPEC=24, OCTAVE=25, TEMPO=26, DURATION=27, BARLINE=28, OPENPAREN=29, 
		CLOSEPAREN=30, NTH_REPEAT=31, KEY_ACCIDENTAL=32, MODE_MINOR=33, COMMENT=34, 
		WHITESPACE=35, END_OF_LINE=36;
	public static final String[] tokenNames = {
		"<INVALID>", "NUMBER_LABEL", "TITLE_LABEL", "COMPOSER_LABEL", "LENGTH_LABEL", 
		"VOICE_LABEL", "METER_LABEL", "TEMPO_LABEL", "KEY_LABEL", "LYRIC_LABEL", 
		"NUMBER", "TITLE", "COMPOSER", "LENGTH", "VOICE", "F_METER", "F_TEMPO", 
		"F_KEY", "LYRIC", "ACCIDENTAL", "BASE_NOTE", "'z'", "DUPLET_SPEC", "TRIPLET_SPEC", 
		"QUADRUPLET_SPEC", "OCTAVE", "TEMPO", "DURATION", "BARLINE", "'['", "']'", 
		"NTH_REPEAT", "KEY_ACCIDENTAL", "'m'", "COMMENT", "WHITESPACE", "END_OF_LINE"
	};
	public static final int
		RULE_tune = 0, RULE_piece = 1, RULE_header = 2, RULE_music = 3, RULE_line = 4, 
		RULE_measure = 5, RULE_element = 6, RULE_note_or_rest = 7, RULE_note_element = 8, 
		RULE_rest_element = 9, RULE_chord = 10, RULE_tuplet = 11, RULE_duplet = 12, 
		RULE_triplet = 13, RULE_quadruplet = 14;
	public static final String[] ruleNames = {
		"tune", "piece", "header", "music", "line", "measure", "element", "note_or_rest", 
		"note_element", "rest_element", "chord", "tuplet", "duplet", "triplet", 
		"quadruplet"
	};

	@Override
	public String getGrammarFileName() { return "ABCMusic.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public ABCMusicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TuneContext extends ParserRuleContext {
		public List<TerminalNode> END_OF_LINE() { return getTokens(ABCMusicParser.END_OF_LINE); }
		public TerminalNode EOF() { return getToken(ABCMusicParser.EOF, 0); }
		public TerminalNode END_OF_LINE(int i) {
			return getToken(ABCMusicParser.END_OF_LINE, i);
		}
		public PieceContext piece() {
			return getRuleContext(PieceContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public TuneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tune; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterTune(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitTune(this);
		}
	}

	public final TuneContext tune() throws RecognitionException {
		TuneContext _localctx = new TuneContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tune);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); header();
			setState(31); piece();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==END_OF_LINE) {
				{
				{
				setState(32); match(END_OF_LINE);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PieceContext extends ParserRuleContext {
		public MusicContext music() {
			return getRuleContext(MusicContext.class,0);
		}
		public List<TerminalNode> END_OF_LINE() { return getTokens(ABCMusicParser.END_OF_LINE); }
		public TerminalNode END_OF_LINE(int i) {
			return getToken(ABCMusicParser.END_OF_LINE, i);
		}
		public PieceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_piece; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterPiece(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitPiece(this);
		}
	}

	public final PieceContext piece() throws RecognitionException {
		PieceContext _localctx = new PieceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_piece);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40); music();
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(41); match(END_OF_LINE);
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode LENGTH(int i) {
			return getToken(ABCMusicParser.LENGTH, i);
		}
		public List<TerminalNode> END_OF_LINE() { return getTokens(ABCMusicParser.END_OF_LINE); }
		public List<TerminalNode> F_METER() { return getTokens(ABCMusicParser.F_METER); }
		public List<TerminalNode> VOICE() { return getTokens(ABCMusicParser.VOICE); }
		public TerminalNode F_METER(int i) {
			return getToken(ABCMusicParser.F_METER, i);
		}
		public TerminalNode NUMBER() { return getToken(ABCMusicParser.NUMBER, 0); }
		public List<TerminalNode> COMPOSER() { return getTokens(ABCMusicParser.COMPOSER); }
		public TerminalNode VOICE(int i) {
			return getToken(ABCMusicParser.VOICE, i);
		}
		public List<TerminalNode> F_TEMPO() { return getTokens(ABCMusicParser.F_TEMPO); }
		public List<TerminalNode> COMMENT() { return getTokens(ABCMusicParser.COMMENT); }
		public TerminalNode END_OF_LINE(int i) {
			return getToken(ABCMusicParser.END_OF_LINE, i);
		}
		public TerminalNode COMMENT(int i) {
			return getToken(ABCMusicParser.COMMENT, i);
		}
		public TerminalNode F_TEMPO(int i) {
			return getToken(ABCMusicParser.F_TEMPO, i);
		}
		public List<TerminalNode> LENGTH() { return getTokens(ABCMusicParser.LENGTH); }
		public TerminalNode F_KEY() { return getToken(ABCMusicParser.F_KEY, 0); }
		public TerminalNode TITLE() { return getToken(ABCMusicParser.TITLE, 0); }
		public TerminalNode COMPOSER(int i) {
			return getToken(ABCMusicParser.COMPOSER, i);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(NUMBER);
			setState(48); match(TITLE);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPOSER) | (1L << LENGTH) | (1L << VOICE) | (1L << F_METER) | (1L << F_TEMPO) | (1L << COMMENT) | (1L << END_OF_LINE))) != 0)) {
				{
				{
				setState(49);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPOSER) | (1L << LENGTH) | (1L << VOICE) | (1L << F_METER) | (1L << F_TEMPO) | (1L << COMMENT) | (1L << END_OF_LINE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); match(F_KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MusicContext extends ParserRuleContext {
		public List<TerminalNode> END_OF_LINE() { return getTokens(ABCMusicParser.END_OF_LINE); }
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public TerminalNode END_OF_LINE(int i) {
			return getToken(ABCMusicParser.END_OF_LINE, i);
		}
		public MusicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_music; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterMusic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitMusic(this);
		}
	}

	public final MusicContext music() throws RecognitionException {
		MusicContext _localctx = new MusicContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_music);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(57); line();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(60); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(62); match(END_OF_LINE);
					}
					} 
				}
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public TerminalNode BARLINE() { return getToken(ABCMusicParser.BARLINE, 0); }
		public List<MeasureContext> measure() {
			return getRuleContexts(MeasureContext.class);
		}
		public List<TerminalNode> END_OF_LINE() { return getTokens(ABCMusicParser.END_OF_LINE); }
		public TerminalNode COMMENT() { return getToken(ABCMusicParser.COMMENT, 0); }
		public TerminalNode VOICE() { return getToken(ABCMusicParser.VOICE, 0); }
		public TerminalNode LYRIC() { return getToken(ABCMusicParser.LYRIC, 0); }
		public TerminalNode END_OF_LINE(int i) {
			return getToken(ABCMusicParser.END_OF_LINE, i);
		}
		public MeasureContext measure(int i) {
			return getRuleContext(MeasureContext.class,i);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_line);
		int _la;
		try {
			int _alt;
			setState(97);
			switch (_input.LA(1)) {
			case VOICE:
			case ACCIDENTAL:
			case BASE_NOTE:
			case REST:
			case DUPLET_SPEC:
			case TRIPLET_SPEC:
			case QUADRUPLET_SPEC:
			case BARLINE:
			case OPENPAREN:
			case NTH_REPEAT:
			case COMMENT:
			case WHITESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(68); match(COMMENT);
					}
				}

				setState(72);
				_la = _input.LA(1);
				if (_la==VOICE) {
					{
					setState(71); match(VOICE);
					}
				}

				setState(75);
				_la = _input.LA(1);
				if (_la==BARLINE) {
					{
					setState(74); match(BARLINE);
					}
				}

				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(77); measure();
					}
					}
					setState(80); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ACCIDENTAL) | (1L << BASE_NOTE) | (1L << REST) | (1L << DUPLET_SPEC) | (1L << TRIPLET_SPEC) | (1L << QUADRUPLET_SPEC) | (1L << OPENPAREN) | (1L << NTH_REPEAT) | (1L << WHITESPACE))) != 0) );
				setState(82); match(END_OF_LINE);
				setState(84);
				_la = _input.LA(1);
				if (_la==LYRIC) {
					{
					setState(83); match(LYRIC);
					}
				}

				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(86); match(END_OF_LINE);
						}
						} 
					}
					setState(91);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case END_OF_LINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(93); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(92); match(END_OF_LINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(95); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureContext extends ParserRuleContext {
		public TerminalNode BARLINE() { return getToken(ABCMusicParser.BARLINE, 0); }
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public MeasureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterMeasure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitMeasure(this);
		}
	}

	public final MeasureContext measure() throws RecognitionException {
		MeasureContext _localctx = new MeasureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_measure);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(100); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(99); element();
				}
				}
				setState(102); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ACCIDENTAL) | (1L << BASE_NOTE) | (1L << REST) | (1L << DUPLET_SPEC) | (1L << TRIPLET_SPEC) | (1L << QUADRUPLET_SPEC) | (1L << OPENPAREN) | (1L << NTH_REPEAT) | (1L << WHITESPACE))) != 0) );
			setState(104); match(BARLINE);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(105); match(WHITESPACE);
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public Note_or_restContext note_or_rest() {
			return getRuleContext(Note_or_restContext.class,0);
		}
		public TerminalNode NTH_REPEAT() { return getToken(ABCMusicParser.NTH_REPEAT, 0); }
		public TerminalNode WHITESPACE() { return getToken(ABCMusicParser.WHITESPACE, 0); }
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_element);
		try {
			setState(114);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111); note_or_rest();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112); match(NTH_REPEAT);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113); match(WHITESPACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Note_or_restContext extends ParserRuleContext {
		public Rest_elementContext rest_element() {
			return getRuleContext(Rest_elementContext.class,0);
		}
		public Note_elementContext note_element() {
			return getRuleContext(Note_elementContext.class,0);
		}
		public TupletContext tuplet() {
			return getRuleContext(TupletContext.class,0);
		}
		public ChordContext chord() {
			return getRuleContext(ChordContext.class,0);
		}
		public Note_or_restContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note_or_rest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterNote_or_rest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitNote_or_rest(this);
		}
	}

	public final Note_or_restContext note_or_rest() throws RecognitionException {
		Note_or_restContext _localctx = new Note_or_restContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_note_or_rest);
		try {
			setState(120);
			switch (_input.LA(1)) {
			case ACCIDENTAL:
			case BASE_NOTE:
			case WHITESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); note_element();
				}
				break;
			case REST:
				enterOuterAlt(_localctx, 2);
				{
				setState(117); rest_element();
				}
				break;
			case DUPLET_SPEC:
			case TRIPLET_SPEC:
			case QUADRUPLET_SPEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(118); tuplet();
				}
				break;
			case OPENPAREN:
				enterOuterAlt(_localctx, 4);
				{
				setState(119); chord();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Note_elementContext extends ParserRuleContext {
		public TerminalNode OCTAVE() { return getToken(ABCMusicParser.OCTAVE, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public TerminalNode ACCIDENTAL() { return getToken(ABCMusicParser.ACCIDENTAL, 0); }
		public TerminalNode BASE_NOTE() { return getToken(ABCMusicParser.BASE_NOTE, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public TerminalNode DURATION() { return getToken(ABCMusicParser.DURATION, 0); }
		public Note_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterNote_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitNote_element(this);
		}
	}

	public final Note_elementContext note_element() throws RecognitionException {
		Note_elementContext _localctx = new Note_elementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_note_element);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_la = _input.LA(1);
			if (_la==ACCIDENTAL) {
				{
				setState(122); match(ACCIDENTAL);
				}
			}

			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(125); match(WHITESPACE);
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131); match(BASE_NOTE);
			setState(135);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(132); match(WHITESPACE);
					}
					} 
				}
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(139);
			_la = _input.LA(1);
			if (_la==OCTAVE) {
				{
				setState(138); match(OCTAVE);
				}
			}

			setState(144);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(141); match(WHITESPACE);
					}
					} 
				}
				setState(146);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(148);
			_la = _input.LA(1);
			if (_la==DURATION) {
				{
				setState(147); match(DURATION);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rest_elementContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public TerminalNode REST() { return getToken(ABCMusicParser.REST, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public TerminalNode DURATION() { return getToken(ABCMusicParser.DURATION, 0); }
		public Rest_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rest_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterRest_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitRest_element(this);
		}
	}

	public final Rest_elementContext rest_element() throws RecognitionException {
		Rest_elementContext _localctx = new Rest_elementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rest_element);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150); match(REST);
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(151); match(WHITESPACE);
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(158);
			_la = _input.LA(1);
			if (_la==DURATION) {
				{
				setState(157); match(DURATION);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChordContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public TerminalNode CLOSEPAREN() { return getToken(ABCMusicParser.CLOSEPAREN, 0); }
		public Note_elementContext note_element(int i) {
			return getRuleContext(Note_elementContext.class,i);
		}
		public TerminalNode OPENPAREN() { return getToken(ABCMusicParser.OPENPAREN, 0); }
		public List<Note_elementContext> note_element() {
			return getRuleContexts(Note_elementContext.class);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public ChordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterChord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitChord(this);
		}
	}

	public final ChordContext chord() throws RecognitionException {
		ChordContext _localctx = new ChordContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_chord);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(OPENPAREN);
			setState(164);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(161); match(WHITESPACE);
					}
					} 
				}
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(167); note_element();
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(168); match(WHITESPACE);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(175); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(174); note_element();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(177); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(179); match(WHITESPACE);
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185); match(CLOSEPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupletContext extends ParserRuleContext {
		public DupletContext duplet() {
			return getRuleContext(DupletContext.class,0);
		}
		public QuadrupletContext quadruplet() {
			return getRuleContext(QuadrupletContext.class,0);
		}
		public TripletContext triplet() {
			return getRuleContext(TripletContext.class,0);
		}
		public TupletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuplet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterTuplet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitTuplet(this);
		}
	}

	public final TupletContext tuplet() throws RecognitionException {
		TupletContext _localctx = new TupletContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_tuplet);
		try {
			setState(190);
			switch (_input.LA(1)) {
			case DUPLET_SPEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); duplet();
				}
				break;
			case TRIPLET_SPEC:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); triplet();
				}
				break;
			case QUADRUPLET_SPEC:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); quadruplet();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DupletContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public Note_elementContext note_element(int i) {
			return getRuleContext(Note_elementContext.class,i);
		}
		public TerminalNode DUPLET_SPEC() { return getToken(ABCMusicParser.DUPLET_SPEC, 0); }
		public List<Note_elementContext> note_element() {
			return getRuleContexts(Note_elementContext.class);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public DupletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_duplet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterDuplet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitDuplet(this);
		}
	}

	public final DupletContext duplet() throws RecognitionException {
		DupletContext _localctx = new DupletContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_duplet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(DUPLET_SPEC);
			setState(196);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(193); match(WHITESPACE);
					}
					} 
				}
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			setState(199); note_element();
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(200); match(WHITESPACE);
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			setState(206); note_element();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TripletContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public Note_elementContext note_element(int i) {
			return getRuleContext(Note_elementContext.class,i);
		}
		public List<Note_elementContext> note_element() {
			return getRuleContexts(Note_elementContext.class);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public TerminalNode TRIPLET_SPEC() { return getToken(ABCMusicParser.TRIPLET_SPEC, 0); }
		public TripletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterTriplet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitTriplet(this);
		}
	}

	public final TripletContext triplet() throws RecognitionException {
		TripletContext _localctx = new TripletContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_triplet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(TRIPLET_SPEC);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(209); match(WHITESPACE);
					}
					} 
				}
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(215); note_element();
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(216); match(WHITESPACE);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			setState(222); note_element();
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(223); match(WHITESPACE);
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			setState(229); note_element();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuadrupletContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(ABCMusicParser.WHITESPACE); }
		public Note_elementContext note_element(int i) {
			return getRuleContext(Note_elementContext.class,i);
		}
		public List<Note_elementContext> note_element() {
			return getRuleContexts(Note_elementContext.class);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(ABCMusicParser.WHITESPACE, i);
		}
		public TerminalNode QUADRUPLET_SPEC() { return getToken(ABCMusicParser.QUADRUPLET_SPEC, 0); }
		public QuadrupletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quadruplet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).enterQuadruplet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ABCMusicListener ) ((ABCMusicListener)listener).exitQuadruplet(this);
		}
	}

	public final QuadrupletContext quadruplet() throws RecognitionException {
		QuadrupletContext _localctx = new QuadrupletContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_quadruplet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(QUADRUPLET_SPEC);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(232); match(WHITESPACE);
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			setState(238); note_element();
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(239); match(WHITESPACE);
					}
					} 
				}
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			setState(245); note_element();
			setState(249);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(246); match(WHITESPACE);
					}
					} 
				}
				setState(251);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			setState(252); note_element();
			setState(256);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(253); match(WHITESPACE);
					}
					} 
				}
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(259); note_element();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3&\u0108\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\3\2\3\2\3\2\7\2$\n\2\f\2\16\2\'\13\2\3\2\3\2\3\3\3\3\7\3-\n\3\f\3\16"+
		"\3\60\13\3\3\4\3\4\3\4\7\4\65\n\4\f\4\16\48\13\4\3\4\3\4\3\5\6\5=\n\5"+
		"\r\5\16\5>\3\5\7\5B\n\5\f\5\16\5E\13\5\3\6\5\6H\n\6\3\6\5\6K\n\6\3\6\5"+
		"\6N\n\6\3\6\6\6Q\n\6\r\6\16\6R\3\6\3\6\5\6W\n\6\3\6\7\6Z\n\6\f\6\16\6"+
		"]\13\6\3\6\6\6`\n\6\r\6\16\6a\5\6d\n\6\3\7\6\7g\n\7\r\7\16\7h\3\7\3\7"+
		"\7\7m\n\7\f\7\16\7p\13\7\3\b\3\b\3\b\5\bu\n\b\3\t\3\t\3\t\3\t\5\t{\n\t"+
		"\3\n\5\n~\n\n\3\n\7\n\u0081\n\n\f\n\16\n\u0084\13\n\3\n\3\n\7\n\u0088"+
		"\n\n\f\n\16\n\u008b\13\n\3\n\5\n\u008e\n\n\3\n\7\n\u0091\n\n\f\n\16\n"+
		"\u0094\13\n\3\n\5\n\u0097\n\n\3\13\3\13\7\13\u009b\n\13\f\13\16\13\u009e"+
		"\13\13\3\13\5\13\u00a1\n\13\3\f\3\f\7\f\u00a5\n\f\f\f\16\f\u00a8\13\f"+
		"\3\f\3\f\7\f\u00ac\n\f\f\f\16\f\u00af\13\f\3\f\6\f\u00b2\n\f\r\f\16\f"+
		"\u00b3\3\f\7\f\u00b7\n\f\f\f\16\f\u00ba\13\f\3\f\3\f\3\r\3\r\3\r\5\r\u00c1"+
		"\n\r\3\16\3\16\7\16\u00c5\n\16\f\16\16\16\u00c8\13\16\3\16\3\16\7\16\u00cc"+
		"\n\16\f\16\16\16\u00cf\13\16\3\16\3\16\3\17\3\17\7\17\u00d5\n\17\f\17"+
		"\16\17\u00d8\13\17\3\17\3\17\7\17\u00dc\n\17\f\17\16\17\u00df\13\17\3"+
		"\17\3\17\7\17\u00e3\n\17\f\17\16\17\u00e6\13\17\3\17\3\17\3\20\3\20\7"+
		"\20\u00ec\n\20\f\20\16\20\u00ef\13\20\3\20\3\20\7\20\u00f3\n\20\f\20\16"+
		"\20\u00f6\13\20\3\20\3\20\7\20\u00fa\n\20\f\20\16\20\u00fd\13\20\3\20"+
		"\3\20\7\20\u0101\n\20\f\20\16\20\u0104\13\20\3\20\3\20\3\20\2\21\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36\2\3\5\16\22$$&&\u0123\2 \3\2\2\2\4*"+
		"\3\2\2\2\6\61\3\2\2\2\b<\3\2\2\2\nc\3\2\2\2\ff\3\2\2\2\16t\3\2\2\2\20"+
		"z\3\2\2\2\22}\3\2\2\2\24\u0098\3\2\2\2\26\u00a2\3\2\2\2\30\u00c0\3\2\2"+
		"\2\32\u00c2\3\2\2\2\34\u00d2\3\2\2\2\36\u00e9\3\2\2\2 !\5\6\4\2!%\5\4"+
		"\3\2\"$\7&\2\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%"+
		"\3\2\2\2()\7\1\2\2)\3\3\2\2\2*.\5\b\5\2+-\7&\2\2,+\3\2\2\2-\60\3\2\2\2"+
		".,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\62\7\f\2\2\62\66\7\r\2"+
		"\2\63\65\t\2\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2"+
		"\679\3\2\2\28\66\3\2\2\29:\7\23\2\2:\7\3\2\2\2;=\5\n\6\2<;\3\2\2\2=>\3"+
		"\2\2\2><\3\2\2\2>?\3\2\2\2?C\3\2\2\2@B\7&\2\2A@\3\2\2\2BE\3\2\2\2CA\3"+
		"\2\2\2CD\3\2\2\2D\t\3\2\2\2EC\3\2\2\2FH\7$\2\2GF\3\2\2\2GH\3\2\2\2HJ\3"+
		"\2\2\2IK\7\20\2\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\7\36\2\2ML\3\2\2\2M"+
		"N\3\2\2\2NP\3\2\2\2OQ\5\f\7\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2"+
		"ST\3\2\2\2TV\7&\2\2UW\7\24\2\2VU\3\2\2\2VW\3\2\2\2W[\3\2\2\2XZ\7&\2\2"+
		"YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\d\3\2\2\2][\3\2\2\2^`\7&\2"+
		"\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2cG\3\2\2\2c_\3\2\2"+
		"\2d\13\3\2\2\2eg\5\16\b\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ij\3"+
		"\2\2\2jn\7\36\2\2km\7%\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\r"+
		"\3\2\2\2pn\3\2\2\2qu\5\20\t\2ru\7!\2\2su\7%\2\2tq\3\2\2\2tr\3\2\2\2ts"+
		"\3\2\2\2u\17\3\2\2\2v{\5\22\n\2w{\5\24\13\2x{\5\30\r\2y{\5\26\f\2zv\3"+
		"\2\2\2zw\3\2\2\2zx\3\2\2\2zy\3\2\2\2{\21\3\2\2\2|~\7\25\2\2}|\3\2\2\2"+
		"}~\3\2\2\2~\u0082\3\2\2\2\177\u0081\7%\2\2\u0080\177\3\2\2\2\u0081\u0084"+
		"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0089\7\26\2\2\u0086\u0088\7%\2\2\u0087\u0086\3\2"+
		"\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008e\7\33\2\2\u008d\u008c\3"+
		"\2\2\2\u008d\u008e\3\2\2\2\u008e\u0092\3\2\2\2\u008f\u0091\7%\2\2\u0090"+
		"\u008f\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2"+
		"\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0097\7\35\2\2\u0096"+
		"\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\23\3\2\2\2\u0098\u009c\7\27\2"+
		"\2\u0099\u009b\7%\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009f"+
		"\u00a1\7\35\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\25\3\2\2"+
		"\2\u00a2\u00a6\7\37\2\2\u00a3\u00a5\7%\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8"+
		"\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a9\u00ad\5\22\n\2\u00aa\u00ac\7%\2\2\u00ab\u00aa\3\2"+
		"\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b2\5\22\n\2\u00b1\u00b0\3"+
		"\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b8\3\2\2\2\u00b5\u00b7\7%\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bc\7 \2\2\u00bc\27\3\2\2\2\u00bd\u00c1\5\32\16"+
		"\2\u00be\u00c1\5\34\17\2\u00bf\u00c1\5\36\20\2\u00c0\u00bd\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\31\3\2\2\2\u00c2\u00c6\7\30\2"+
		"\2\u00c3\u00c5\7%\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00cd\5\22\n\2\u00ca\u00cc\7%\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00d1\5\22\n\2\u00d1\33\3\2\2\2\u00d2\u00d6\7\31"+
		"\2\2\u00d3\u00d5\7%\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d9\u00dd\5\22\n\2\u00da\u00dc\7%\2\2\u00db\u00da\3\2\2\2\u00dc"+
		"\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2"+
		"\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e4\5\22\n\2\u00e1\u00e3\7%\2\2\u00e2"+
		"\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\5\22\n\2\u00e8"+
		"\35\3\2\2\2\u00e9\u00ed\7\32\2\2\u00ea\u00ec\7%\2\2\u00eb\u00ea\3\2\2"+
		"\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0"+
		"\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f4\5\22\n\2\u00f1\u00f3\7%\2\2\u00f2"+
		"\u00f1\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2"+
		"\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00fb\5\22\n\2\u00f8"+
		"\u00fa\7%\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe"+
		"\u0102\5\22\n\2\u00ff\u0101\7%\2\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2"+
		"\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105\3\2\2\2\u0104"+
		"\u0102\3\2\2\2\u0105\u0106\5\22\n\2\u0106\37\3\2\2\2)%.\66>CGJMRV[ach"+
		"ntz}\u0082\u0089\u008d\u0092\u0096\u009c\u00a0\u00a6\u00ad\u00b3\u00b8"+
		"\u00c0\u00c6\u00cd\u00d6\u00dd\u00e4\u00ed\u00f4\u00fb\u0102";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}