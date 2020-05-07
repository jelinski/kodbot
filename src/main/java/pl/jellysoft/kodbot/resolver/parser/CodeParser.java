package pl.jellysoft.kodbot.resolver.parser;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotBaseListener;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotLexer;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotParser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class CodeParser extends pl.jellysoft.kodbot.resolver.parser.Parser {

    private final List<String> errors = new ArrayList<>();

    private final ANTLRErrorListener parserErrorListener = new ANTLRErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
            CommonToken currentToken = (CommonToken) arg1;
            KodbotParser kodbotParser = (KodbotParser) arg0;
            Vocabulary vocabulary = VocabularyImpl.fromTokenNames(kodbotParser.getTokenNames());
            String expectedTokens = kodbotParser.getExpectedTokens().toString(vocabulary);
            String error = "Blad w linii:" + arg2 + " znak:" + arg3 + " otrzymano: " + currentToken.getText() + " spodziewano sie:" + expectedTokens;
            System.out.println(error);
            errors.add(error);
        }

        @Override
        public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {
            System.out.println("parserErrorListener reportContextSensitivity");
        }

        @Override
        public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
            System.out.println("parserErrorListener reportAttemptingFullContext");
        }

        @Override
        public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5, ATNConfigSet arg6) {
            System.out.println("parserErrorListener reportAmbiguity");
        }
    };

    //TODO prawdopodobnie do usuniecia
    private final ANTLRErrorListener lexerErrorListener = new ANTLRErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
            String error = "Blad skladni w linii: " + arg2 + " znak: " + arg3;
            errors.add(error);
        }

        @Override
        public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {
            System.out.println("lexerErrorListener reportContextSensitivity");
        }

        @Override
        public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
            System.out.println("lexerErrorListener reportAttemptingFullContext");
        }

        @Override
        public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5, ATNConfigSet arg6) {
            System.out.println("lexerErrorListener reportAmbiguity");
        }
    };

    @Override
    public ParserResult parse(String code) throws ParserException {
        ANTLRInputStream input = new ANTLRInputStream(code);
        KodbotLexer lexer = new KodbotLexer(input);
        lexer.addErrorListener(lexerErrorListener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KodbotParser parser = new KodbotParser(tokens);
        KodbotBaseListener listener = new KodbotBaseListener();
        parser.addParseListener(listener);
        parser.addErrorListener(parserErrorListener);

        try {
            parser.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (errors.size() > 0) {
            throw new ParserException(errors.toString());
        }
        return new ParserResult(listener.getCommandCounter(), listener.getCommands());
    }

}
