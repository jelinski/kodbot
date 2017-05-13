// Generated from C:\STUDIA\kodbotLang\Kodbot.g4 by ANTLR 4.1
package pl.yeloon.magisterium.resolver.parser.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KodbotLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MOVE=1, JUMP=2, TURN_LEFT=3, TURN_RIGHT=4, INCREMENT_ID=5, DECREMENT_ID=6, 
		NUMBER=7, MAIN=8, L_BRACKET=9, R_BRACKET=10, L_PARENTHESIS=11, R_PARENTHESIS=12, 
		REPEAT=13, EQUAL=14, PLUS=15, MINUS=16, SEMICOLON=17, ID=18, WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'move'", "'jump'", "'left'", "'right'", "INCREMENT_ID", "DECREMENT_ID", 
		"NUMBER", "'main'", "'{'", "'}'", "'('", "')'", "'repeat'", "'='", "'+'", 
		"'-'", "';'", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"MOVE", "JUMP", "TURN_LEFT", "TURN_RIGHT", "INCREMENT_ID", "DECREMENT_ID", 
		"NUMBER", "MAIN", "L_BRACKET", "R_BRACKET", "L_PARENTHESIS", "R_PARENTHESIS", 
		"REPEAT", "EQUAL", "PLUS", "MINUS", "SEMICOLON", "ID", "WS"
	};


	public KodbotLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Kodbot.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\25u\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b"+
		"\6\bH\n\b\r\b\16\bI\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\7\23j\n\23\f\23\16\23m\13\23\3\24\6\24p\n\24\r\24"+
		"\16\24q\3\24\3\24\2\25\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21"+
		"\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1"+
		"%\24\1\'\25\2\3\2\5\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"w\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5.\3\2\2\2\7\63\3\2\2\2\t8\3\2\2\2\13"+
		">\3\2\2\2\rB\3\2\2\2\17G\3\2\2\2\21K\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27"+
		"T\3\2\2\2\31V\3\2\2\2\33X\3\2\2\2\35_\3\2\2\2\37a\3\2\2\2!c\3\2\2\2#e"+
		"\3\2\2\2%g\3\2\2\2\'o\3\2\2\2)*\7o\2\2*+\7q\2\2+,\7x\2\2,-\7g\2\2-\4\3"+
		"\2\2\2./\7l\2\2/\60\7w\2\2\60\61\7o\2\2\61\62\7r\2\2\62\6\3\2\2\2\63\64"+
		"\7n\2\2\64\65\7g\2\2\65\66\7h\2\2\66\67\7v\2\2\67\b\3\2\2\289\7t\2\29"+
		":\7k\2\2:;\7i\2\2;<\7j\2\2<=\7v\2\2=\n\3\2\2\2>?\5%\23\2?@\7-\2\2@A\7"+
		"-\2\2A\f\3\2\2\2BC\5%\23\2CD\7/\2\2DE\7/\2\2E\16\3\2\2\2FH\4\62;\2GF\3"+
		"\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\20\3\2\2\2KL\7o\2\2LM\7c\2\2MN\7"+
		"k\2\2NO\7p\2\2O\22\3\2\2\2PQ\7}\2\2Q\24\3\2\2\2RS\7\177\2\2S\26\3\2\2"+
		"\2TU\7*\2\2U\30\3\2\2\2VW\7+\2\2W\32\3\2\2\2XY\7t\2\2YZ\7g\2\2Z[\7r\2"+
		"\2[\\\7g\2\2\\]\7c\2\2]^\7v\2\2^\34\3\2\2\2_`\7?\2\2`\36\3\2\2\2ab\7-"+
		"\2\2b \3\2\2\2cd\7/\2\2d\"\3\2\2\2ef\7=\2\2f$\3\2\2\2gk\t\2\2\2hj\t\3"+
		"\2\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l&\3\2\2\2mk\3\2\2\2np\t\4"+
		"\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\b\24\2\2t(\3"+
		"\2\2\2\6\2Ikq";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}