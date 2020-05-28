package model.io.ast;

import libs.Const;
import libs.Node;
import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PROGRAM extends Node {
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse(Tokenizer tokenizer) {
//        String token1 = tokenizer.getNext();
        while (tokenizer.moreTokens()) {
            STATEMENT s = null;
            if (tokenizer.checkToken(Const.SCHEDULE)) {
                s = new SCHEDULE();
            } else if(tokenizer.checkToken(Const.DEF)){
                s = new PROCDEF();
            } else if(tokenizer.checkToken(Const.CALL)){
                s = new PROCCALL();
            }
            else {
                String token = tokenizer.getNext();
                throw new RuntimeException("Unknown statement:" + tokenizer.getNext());
            }
            s.parse(tokenizer);
            statements.add(s);
        }

    }

    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
        for (STATEMENT s : statements){
            s.evaluate(exp, symbolTable, parser);
        }
        return null;
    }
}
