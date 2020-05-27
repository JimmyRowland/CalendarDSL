package model.io.ast;

import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.List;
import java.util.Map;

public class PROCDEF extends STATEMENT {
    private String name;
    private PROCBODY body;

    @Override
    public void parse(Tokenizer tokenizer) {
        tokenizer.getAndCheckNext(Const.DEF);
        name = tokenizer.getNext();
        body = new PROCBODY(name);
        body.parse(tokenizer);

    }

    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
        System.out.println("Putting " + this.name + " into symbol table");
        exp.put(name,body); // no value yet; use null as a placeholder
        return null;
    }
}
