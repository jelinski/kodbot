package pl.jellysoft.kodbot.resolver.parser.antlr;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KodbotParser extends Parser {

    public static final int
            MOVE = 1, JUMP = 2, TURN_LEFT = 3, TURN_RIGHT = 4, INCREMENT_ID = 5, DECREMENT_ID = 6,
            NUMBER = 7, MAIN = 8, L_BRACKET = 9, R_BRACKET = 10, L_PARENTHESIS = 11, R_PARENTHESIS = 12,
            REPEAT = 13, EQUAL = 14, PLUS = 15, MINUS = 16, SEMICOLON = 17, ID = 18, WS = 19;

    public static final String[] tokenNames = {
            "<INVALID>", "'move'", "'jump'", "'left'", "'right'", "INCREMENT_ID",
            "DECREMENT_ID", "NUMBER", "'main'", "'{'", "'}'", "'('", "')'", "'repeat'",
            "'='", "'+'", "'-'", "';'", "ID", "WS"
    };

    public static final int
            RULE_start = 0, RULE_main = 1, RULE_functionDef = 2, RULE_block = 3, RULE_stat = 4,
            RULE_expr = 5, RULE_repeat = 6, RULE_functionCall = 7, RULE_var = 8, RULE_assignWithAddition = 9,
            RULE_assignWithSubtraction = 10, RULE_assign = 11, RULE_move = 12, RULE_jump = 13,
            RULE_turnLeft = 14, RULE_turnRight = 15, RULE_increment = 16, RULE_decrement = 17;

    public static final String[] ruleNames = {
            "start", "main", "functionDef", "block", "stat", "expr", "repeat", "functionCall",
            "var", "assignWithAddition", "assignWithSubtraction", "assign", "move",
            "jump", "turnLeft", "turnRight", "increment", "decrement"
    };

    public static final String _serializedATN =
            "\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\25q\4\2\t\2\4\3\t" +
                    "\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4" +
                    "\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23" +
                    "\t\23\3\2\3\2\6\2)\n\2\r\2\16\2*\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3" +
                    "\5\3\6\3\6\6\69\n\6\r\6\16\6:\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7" +
                    "\5\7G\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3" +
                    "\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3" +
                    "\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\2\24\2\4\6\b\n\f" +
                    "\16\20\22\24\26\30\32\34\36 \"$\2\3\4\2\t\t\24\24k\2(\3\2\2\2\4,\3\2\2" +
                    "\2\6/\3\2\2\2\b\62\3\2\2\2\n8\3\2\2\2\fF\3\2\2\2\16J\3\2\2\2\20P\3\2\2" +
                    "\2\22R\3\2\2\2\24T\3\2\2\2\26Z\3\2\2\2\30`\3\2\2\2\32d\3\2\2\2\34f\3\2" +
                    "\2\2\36h\3\2\2\2 j\3\2\2\2\"l\3\2\2\2$n\3\2\2\2&)\5\4\3\2\')\5\6\4\2(" +
                    "&\3\2\2\2(\'\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\3\3\2\2\2,-\7\n\2" +
                    "\2-.\5\b\5\2.\5\3\2\2\2/\60\7\24\2\2\60\61\5\b\5\2\61\7\3\2\2\2\62\63" +
                    "\7\13\2\2\63\64\5\n\6\2\64\65\7\f\2\2\65\t\3\2\2\2\669\5\f\7\2\679\5\16" +
                    "\b\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\13\3\2\2\2" +
                    "<G\5\32\16\2=G\5\34\17\2>G\5\36\20\2?G\5 \21\2@G\5\20\t\2AG\5\30\r\2B" +
                    "G\5\24\13\2CG\5\26\f\2DG\5\"\22\2EG\5$\23\2F<\3\2\2\2F=\3\2\2\2F>\3\2" +
                    "\2\2F?\3\2\2\2F@\3\2\2\2FA\3\2\2\2FB\3\2\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2" +
                    "\2\2GH\3\2\2\2HI\7\23\2\2I\r\3\2\2\2JK\7\17\2\2KL\7\r\2\2LM\5\22\n\2M" +
                    "N\7\16\2\2NO\5\b\5\2O\17\3\2\2\2PQ\7\24\2\2Q\21\3\2\2\2RS\t\2\2\2S\23" +
                    "\3\2\2\2TU\7\24\2\2UV\7\20\2\2VW\5\22\n\2WX\7\21\2\2XY\5\22\n\2Y\25\3" +
                    "\2\2\2Z[\7\24\2\2[\\\7\20\2\2\\]\5\22\n\2]^\7\22\2\2^_\5\22\n\2_\27\3" +
                    "\2\2\2`a\7\24\2\2ab\7\20\2\2bc\5\22\n\2c\31\3\2\2\2de\7\3\2\2e\33\3\2" +
                    "\2\2fg\7\4\2\2g\35\3\2\2\2hi\7\5\2\2i\37\3\2\2\2jk\7\6\2\2k!\3\2\2\2l" +
                    "m\7\7\2\2m#\3\2\2\2no\7\b\2\2o%\3\2\2\2\7(*8:F";

    public static final ATN _ATN = ATNSimulator.deserialize(_serializedATN.toCharArray());

    protected static final DFA[] _decisionToDFA;

    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public KodbotParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "Kodbot.g4";
    }

    @Override
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final StartContext start() throws RecognitionException {
        StartContext _localctx = new StartContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_start);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(38);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        setState(38);
                        switch (_input.LA(1)) {
                            case MAIN: {
                                setState(36);
                                main();
                            }
                            break;
                            case ID: {
                                setState(37);
                                functionDef();
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(40);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == MAIN || _la == ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final MainContext main() throws RecognitionException {
        MainContext _localctx = new MainContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_main);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(42);
                match(MAIN);
                setState(43);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionDefContext functionDef() throws RecognitionException {
        FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_functionDef);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(45);
                match(ID);
                setState(46);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_block);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(48);
                match(L_BRACKET);
                setState(49);
                stat();
                setState(50);
                match(R_BRACKET);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StatContext stat() throws RecognitionException {
        StatContext _localctx = new StatContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_stat);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(54);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        setState(54);
                        switch (_input.LA(1)) {
                            case MOVE:
                            case JUMP:
                            case TURN_LEFT:
                            case TURN_RIGHT:
                            case INCREMENT_ID:
                            case DECREMENT_ID:
                            case ID: {
                                setState(52);
                                expr();
                            }
                            break;
                            case REPEAT: {
                                setState(53);
                                repeat();
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(56);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOVE) | (1L << JUMP) | (1L << TURN_LEFT) | (1L << TURN_RIGHT) | (1L << INCREMENT_ID) | (1L << DECREMENT_ID) | (1L << REPEAT) | (1L << ID))) != 0));
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExprContext expr() throws RecognitionException {
        ExprContext _localctx = new ExprContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_expr);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(68);
                switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                    case 1: {
                        setState(58);
                        move();
                    }
                    break;

                    case 2: {
                        setState(59);
                        jump();
                    }
                    break;

                    case 3: {
                        setState(60);
                        turnLeft();
                    }
                    break;

                    case 4: {
                        setState(61);
                        turnRight();
                    }
                    break;

                    case 5: {
                        setState(62);
                        functionCall();
                    }
                    break;

                    case 6: {
                        setState(63);
                        assign();
                    }
                    break;

                    case 7: {
                        setState(64);
                        assignWithAddition();
                    }
                    break;

                    case 8: {
                        setState(65);
                        assignWithSubtraction();
                    }
                    break;

                    case 9: {
                        setState(66);
                        increment();
                    }
                    break;

                    case 10: {
                        setState(67);
                        decrement();
                    }
                    break;
                }
                setState(70);
                match(SEMICOLON);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final RepeatContext repeat() throws RecognitionException {
        RepeatContext _localctx = new RepeatContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_repeat);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(72);
                match(REPEAT);
                setState(73);
                match(L_PARENTHESIS);
                setState(74);
                var();
                setState(75);
                match(R_PARENTHESIS);
                setState(76);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionCallContext functionCall() throws RecognitionException {
        FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_functionCall);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(78);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VarContext var() throws RecognitionException {
        VarContext _localctx = new VarContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_var);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(80);
                _la = _input.LA(1);
                if (!(_la == NUMBER || _la == ID)) {
                    _errHandler.recoverInline(this);
                }
                consume();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignWithAdditionContext assignWithAddition() throws RecognitionException {
        AssignWithAdditionContext _localctx = new AssignWithAdditionContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_assignWithAddition);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(82);
                match(ID);
                setState(83);
                match(EQUAL);
                setState(84);
                ((AssignWithAdditionContext) _localctx).firstRight = var();
                setState(85);
                match(PLUS);
                setState(86);
                ((AssignWithAdditionContext) _localctx).secondRight = var();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignWithSubtractionContext assignWithSubtraction() throws RecognitionException {
        AssignWithSubtractionContext _localctx = new AssignWithSubtractionContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_assignWithSubtraction);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(88);
                match(ID);
                setState(89);
                match(EQUAL);
                setState(90);
                ((AssignWithSubtractionContext) _localctx).firstRight = var();
                setState(91);
                match(MINUS);
                setState(92);
                ((AssignWithSubtractionContext) _localctx).secondRight = var();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignContext assign() throws RecognitionException {
        AssignContext _localctx = new AssignContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_assign);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(94);
                match(ID);
                setState(95);
                match(EQUAL);
                setState(96);
                var();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final MoveContext move() throws RecognitionException {
        MoveContext _localctx = new MoveContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_move);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(98);
                match(MOVE);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final JumpContext jump() throws RecognitionException {
        JumpContext _localctx = new JumpContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_jump);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(100);
                match(JUMP);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TurnLeftContext turnLeft() throws RecognitionException {
        TurnLeftContext _localctx = new TurnLeftContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_turnLeft);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(102);
                match(TURN_LEFT);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TurnRightContext turnRight() throws RecognitionException {
        TurnRightContext _localctx = new TurnRightContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_turnRight);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(104);
                match(TURN_RIGHT);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IncrementContext increment() throws RecognitionException {
        IncrementContext _localctx = new IncrementContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_increment);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(106);
                match(INCREMENT_ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DecrementContext decrement() throws RecognitionException {
        DecrementContext _localctx = new DecrementContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_decrement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(108);
                match(DECREMENT_ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StartContext extends ParserRuleContext {
        public StartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public FunctionDefContext functionDef(int i) {
            return getRuleContext(FunctionDefContext.class, i);
        }

        public MainContext main(int i) {
            return getRuleContext(MainContext.class, i);
        }

        public List<MainContext> main() {
            return getRuleContexts(MainContext.class);
        }

        public List<FunctionDefContext> functionDef() {
            return getRuleContexts(FunctionDefContext.class);
        }

        @Override
        public int getRuleIndex() {
            return RULE_start;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterStart(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitStart(this);
            }
        }
    }

    public static class MainContext extends ParserRuleContext {
        public MainContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public TerminalNode MAIN() {
            return getToken(KodbotParser.MAIN, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_main;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterMain(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitMain(this);
            }
        }
    }

    public static class FunctionDefContext extends ParserRuleContext {
        public FunctionDefContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionDef;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterFunctionDef(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitFunctionDef(this);
            }
        }
    }

    public static class BlockContext extends ParserRuleContext {
        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode R_BRACKET() {
            return getToken(KodbotParser.R_BRACKET, 0);
        }

        public StatContext stat() {
            return getRuleContext(StatContext.class, 0);
        }

        public TerminalNode L_BRACKET() {
            return getToken(KodbotParser.L_BRACKET, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterBlock(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitBlock(this);
            }
        }
    }

    public static class StatContext extends ParserRuleContext {
        public StatContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public RepeatContext repeat(int i) {
            return getRuleContext(RepeatContext.class, i);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public List<RepeatContext> repeat() {
            return getRuleContexts(RepeatContext.class);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stat;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterStat(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitStat(this);
            }
        }
    }

    public static class ExprContext extends ParserRuleContext {
        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public DecrementContext decrement() {
            return getRuleContext(DecrementContext.class, 0);
        }

        public TurnRightContext turnRight() {
            return getRuleContext(TurnRightContext.class, 0);
        }

        public JumpContext jump() {
            return getRuleContext(JumpContext.class, 0);
        }

        public TerminalNode SEMICOLON() {
            return getToken(KodbotParser.SEMICOLON, 0);
        }

        public AssignWithSubtractionContext assignWithSubtraction() {
            return getRuleContext(AssignWithSubtractionContext.class, 0);
        }

        public AssignWithAdditionContext assignWithAddition() {
            return getRuleContext(AssignWithAdditionContext.class, 0);
        }

        public FunctionCallContext functionCall() {
            return getRuleContext(FunctionCallContext.class, 0);
        }

        public TurnLeftContext turnLeft() {
            return getRuleContext(TurnLeftContext.class, 0);
        }

        public AssignContext assign() {
            return getRuleContext(AssignContext.class, 0);
        }

        public MoveContext move() {
            return getRuleContext(MoveContext.class, 0);
        }

        public IncrementContext increment() {
            return getRuleContext(IncrementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterExpr(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitExpr(this);
            }
        }
    }

    public static class RepeatContext extends ParserRuleContext {
        public RepeatContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode REPEAT() {
            return getToken(KodbotParser.REPEAT, 0);
        }

        public TerminalNode L_PARENTHESIS() {
            return getToken(KodbotParser.L_PARENTHESIS, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public VarContext var() {
            return getRuleContext(VarContext.class, 0);
        }

        public TerminalNode R_PARENTHESIS() {
            return getToken(KodbotParser.R_PARENTHESIS, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_repeat;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterRepeat(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitRepeat(this);
            }
        }
    }

    public static class FunctionCallContext extends ParserRuleContext {
        public FunctionCallContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionCall;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterFunctionCall(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitFunctionCall(this);
            }
        }
    }

    public static class VarContext extends ParserRuleContext {
        public VarContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(KodbotParser.NUMBER, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_var;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterVar(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitVar(this);
            }
        }
    }

    public static class AssignWithAdditionContext extends ParserRuleContext {
        public VarContext firstRight;
        public VarContext secondRight;

        public AssignWithAdditionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public VarContext var(int i) {
            return getRuleContext(VarContext.class, i);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        public TerminalNode PLUS() {
            return getToken(KodbotParser.PLUS, 0);
        }

        public TerminalNode EQUAL() {
            return getToken(KodbotParser.EQUAL, 0);
        }

        public List<VarContext> var() {
            return getRuleContexts(VarContext.class);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignWithAddition;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterAssignWithAddition(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitAssignWithAddition(this);
            }
        }
    }

    public static class AssignWithSubtractionContext extends ParserRuleContext {
        public VarContext firstRight;
        public VarContext secondRight;

        public AssignWithSubtractionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public VarContext var(int i) {
            return getRuleContext(VarContext.class, i);
        }

        public TerminalNode MINUS() {
            return getToken(KodbotParser.MINUS, 0);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        public TerminalNode EQUAL() {
            return getToken(KodbotParser.EQUAL, 0);
        }

        public List<VarContext> var() {
            return getRuleContexts(VarContext.class);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignWithSubtraction;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterAssignWithSubtraction(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitAssignWithSubtraction(this);
            }
        }
    }

    public static class AssignContext extends ParserRuleContext {
        public AssignContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode ID() {
            return getToken(KodbotParser.ID, 0);
        }

        public TerminalNode EQUAL() {
            return getToken(KodbotParser.EQUAL, 0);
        }

        public VarContext var() {
            return getRuleContext(VarContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assign;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterAssign(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitAssign(this);
            }
        }
    }

    public static class MoveContext extends ParserRuleContext {
        public MoveContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode MOVE() {
            return getToken(KodbotParser.MOVE, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_move;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterMove(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitMove(this);
            }
        }
    }

    public static class JumpContext extends ParserRuleContext {
        public JumpContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode JUMP() {
            return getToken(KodbotParser.JUMP, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_jump;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterJump(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitJump(this);
            }
        }
    }

    public static class TurnLeftContext extends ParserRuleContext {
        public TurnLeftContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TURN_LEFT() {
            return getToken(KodbotParser.TURN_LEFT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_turnLeft;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterTurnLeft(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitTurnLeft(this);
            }
        }
    }

    public static class TurnRightContext extends ParserRuleContext {
        public TurnRightContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TURN_RIGHT() {
            return getToken(KodbotParser.TURN_RIGHT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_turnRight;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterTurnRight(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitTurnRight(this);
            }
        }
    }

    public static class IncrementContext extends ParserRuleContext {
        public IncrementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode INCREMENT_ID() {
            return getToken(KodbotParser.INCREMENT_ID, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_increment;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterIncrement(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitIncrement(this);
            }
        }
    }

    public static class DecrementContext extends ParserRuleContext {
        public DecrementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode DECREMENT_ID() {
            return getToken(KodbotParser.DECREMENT_ID, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decrement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).enterDecrement(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof KodbotListener) {
                ((KodbotListener) listener).exitDecrement(this);
            }
        }
    }

}
