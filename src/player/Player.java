package player;

import grammar.ABCMusicLexer;
import grammar.ABCMusicParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import player.Listener;


public class Player {
    
    private final ParseTreeListener listener = new Listener(); 
    
    /**
     * Parses a string representation of an abc file
     * @param text: the string representation of the abc file
     * @return : a PieceOfMusic that represents the abc file
     */
    public PieceOfMusic parse(String text) {
        return runListener(text);
    }
    
    /**
     * Runs the listener on the given input string.
     * 
     * @param input The input string.
     */
    private PieceOfMusic runListener(String input) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        ABCMusicLexer lexer = new ABCMusicLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        ABCMusicParser parser = new ABCMusicParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree;
        tree = parser.tune(); // "tune" is the starter rule.
        
        // Generate GUI of parse tree for easier debugging
        //((RuleContext)tree).inspect(parser);

        // Walk the tree with the listener.
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return ((Listener)listener).getPieceOfMusic();
    }
}
