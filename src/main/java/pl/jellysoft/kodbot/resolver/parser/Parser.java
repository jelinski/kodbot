package pl.jellysoft.kodbot.resolver.parser;

public abstract class Parser {

    public abstract ParserResult parse(String input) throws ParserException;

}
