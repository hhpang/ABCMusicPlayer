// Generated from ABCMusic.g4 by ANTLR 4.0

package grammar;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class ABCMusicBaseListener implements ABCMusicListener {
	@Override public void enterMusic(ABCMusicParser.MusicContext ctx) { }
	@Override public void exitMusic(ABCMusicParser.MusicContext ctx) { }

	@Override public void enterElement(ABCMusicParser.ElementContext ctx) { }
	@Override public void exitElement(ABCMusicParser.ElementContext ctx) { }

	@Override public void enterMeasure(ABCMusicParser.MeasureContext ctx) { }
	@Override public void exitMeasure(ABCMusicParser.MeasureContext ctx) { }

	@Override public void enterDuplet(ABCMusicParser.DupletContext ctx) { }
	@Override public void exitDuplet(ABCMusicParser.DupletContext ctx) { }

	@Override public void enterQuadruplet(ABCMusicParser.QuadrupletContext ctx) { }
	@Override public void exitQuadruplet(ABCMusicParser.QuadrupletContext ctx) { }

	@Override public void enterLine(ABCMusicParser.LineContext ctx) { }
	@Override public void exitLine(ABCMusicParser.LineContext ctx) { }

	@Override public void enterNote_element(ABCMusicParser.Note_elementContext ctx) { }
	@Override public void exitNote_element(ABCMusicParser.Note_elementContext ctx) { }

	@Override public void enterTune(ABCMusicParser.TuneContext ctx) { }
	@Override public void exitTune(ABCMusicParser.TuneContext ctx) { }

	@Override public void enterHeader(ABCMusicParser.HeaderContext ctx) { }
	@Override public void exitHeader(ABCMusicParser.HeaderContext ctx) { }

	@Override public void enterChord(ABCMusicParser.ChordContext ctx) { }
	@Override public void exitChord(ABCMusicParser.ChordContext ctx) { }

	@Override public void enterNote_or_rest(ABCMusicParser.Note_or_restContext ctx) { }
	@Override public void exitNote_or_rest(ABCMusicParser.Note_or_restContext ctx) { }

	@Override public void enterRest_element(ABCMusicParser.Rest_elementContext ctx) { }
	@Override public void exitRest_element(ABCMusicParser.Rest_elementContext ctx) { }

	@Override public void enterTriplet(ABCMusicParser.TripletContext ctx) { }
	@Override public void exitTriplet(ABCMusicParser.TripletContext ctx) { }

	@Override public void enterTuplet(ABCMusicParser.TupletContext ctx) { }
	@Override public void exitTuplet(ABCMusicParser.TupletContext ctx) { }

	@Override public void enterPiece(ABCMusicParser.PieceContext ctx) { }
	@Override public void exitPiece(ABCMusicParser.PieceContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}