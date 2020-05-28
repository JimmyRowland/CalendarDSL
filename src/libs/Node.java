package libs;

import jdk.nashorn.internal.parser.Token;
import model.Event;
import model.io.Parser;
import model.io.ast.EXP;

import java.util.List;
import java.util.Map;

public abstract class Node {
    abstract public void parse(Tokenizer t);
    abstract public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser);

}
