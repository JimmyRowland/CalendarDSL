package model.io.ast;

import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.List;
import java.util.Map;

public class EVENT extends EXP {
    private String name;

    @Override
    public void parse(Tokenizer tokenizer) {
        name = tokenizer.getAndCheckNext("\\S[a-z0-9A-Z ]*");
    }

    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
//        System.out.println("!!!!!!!!!!");
//        System.out.println(Main.symbolTable);
//        if(this.name.equals("y")){
//            System.out.println(name);
//        }
        return symbolTable.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
