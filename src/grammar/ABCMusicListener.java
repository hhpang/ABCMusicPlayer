// Generated from ABCMusic.g4 by ANTLR 4.0

package grammar;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface ABCMusicListener extends ParseTreeListener {
	void enterMusic(ABCMusicParser.MusicContext ctx);
	void exitMusic(ABCMusicParser.MusicContext ctx);

	void enterElement(ABCMusicParser.ElementContext ctx);
	void exitElement(ABCMusicParser.ElementContext ctx);

	void enterMeasure(ABCMusicParser.MeasureContext ctx);
	void exitMeasure(ABCMusicParser.MeasureContext ctx);

	void enterDuplet(ABCMusicParser.DupletContext ctx);
	void exitDuplet(ABCMusicParser.DupletContext ctx);

	void enterQuadruplet(ABCMusicParser.QuadrupletContext ctx);
	void exitQuadruplet(ABCMusicParser.QuadrupletContext ctx);

	void enterLine(ABCMusicParser.LineContext ctx);
	void exitLine(ABCMusicParser.LineContext ctx);

	void enterNote_element(ABCMusicParser.Note_elementContext ctx);
	void exitNote_element(ABCMusicParser.Note_elementContext ctx);

	void enterTune(ABCMusicParser.TuneContext ctx);
	void exitTune(ABCMusicParser.TuneContext ctx);

	void enterHeader(ABCMusicParser.HeaderContext ctx);
	void exitHeader(ABCMusicParser.HeaderContext ctx);

	void enterChord(ABCMusicParser.ChordContext ctx);
	void exitChord(ABCMusicParser.ChordContext ctx);

	void enterNote_or_rest(ABCMusicParser.Note_or_restContext ctx);
	void exitNote_or_rest(ABCMusicParser.Note_or_restContext ctx);

	void enterRest_element(ABCMusicParser.Rest_elementContext ctx);
	void exitRest_element(ABCMusicParser.Rest_elementContext ctx);

	void enterTriplet(ABCMusicParser.TripletContext ctx);
	void exitTriplet(ABCMusicParser.TripletContext ctx);

	void enterTuplet(ABCMusicParser.TupletContext ctx);
	void exitTuplet(ABCMusicParser.TupletContext ctx);

	void enterPiece(ABCMusicParser.PieceContext ctx);
	void exitPiece(ABCMusicParser.PieceContext ctx);
}