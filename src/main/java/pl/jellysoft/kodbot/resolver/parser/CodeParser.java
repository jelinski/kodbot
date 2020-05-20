package pl.jellysoft.kodbot.resolver.parser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotBaseListener;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotLexer;
import pl.jellysoft.kodbot.resolver.parser.antlr.KodbotParser;

import java.util.ArrayList;
import java.util.List;

public class CodeParser extends pl.jellysoft.kodbot.resolver.parser.Parser {

    private final List<String> errors = new ArrayList<>();

    private final ANTLRErrorListener parserErrorListener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
            CommonToken currentToken = (CommonToken) arg1;
            KodbotParser kodbotParser = (KodbotParser) arg0;
            Vocabulary vocabulary = VocabularyImpl.fromTokenNames(kodbotParser.getTokenNames());
            String expectedTokens = kodbotParser.getExpectedTokens().toString(vocabulary);
            String error = "Blad w linii:" + arg2 + " znak:" + arg3 + " otrzymano: " + currentToken.getText() + " spodziewano sie:" + expectedTokens;
            errors.add(error);
        }
    };

    private final ANTLRErrorListener lexerErrorListener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4, RecognitionException arg5) {
            String error = "Blad skladni w linii: " + arg2 + " znak: " + arg3;
            errors.add(error);
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
        parser.start();

        if (errors.size() > 0) {
            throw new ParserException(errors.toString());
        }
        return new ParserResult(listener.getCommandCounter().intValue(), listener.getCommands());
    }

}
