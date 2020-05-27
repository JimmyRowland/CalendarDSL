package model.io.ast;

import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PROCCALL extends STATEMENT {
    private String name;
    private List<String> args;

    @Override
    public void parse(Tokenizer tokenizer) {
        tokenizer.getAndCheckNext(Const.CALL);
        name = tokenizer.getNext();
        if(tokenizer.checkToken("\\(")){
            String token = tokenizer.getNext();
            args= new ArrayList<>();
            args.add(tokenizer.getNext());
            while(tokenizer.checkToken(Const.IREG)){
                token = tokenizer.getNext();
                args.add(tokenizer.getNext());
            }
            tokenizer.getAndCheckNext("\\)");
        }
    }


    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
        System.out.println("Getting " + this.name + " from symbol table");
        PROCBODY body =(PROCBODY) exp.get(name);
        body.symbolTable = new HashMap<>();
        symbolTable.forEach((k,value)->{
            body.symbolTable.put(k,value);
        });
        if(this.args!=null){
            for(int i = 0; i < this.args.size(); i++){
                List<Event> eventList = symbolTable.get(args.get(i));
                List<EXP> args = body.getArgs();
                body.symbolTable.put(args.get(i).toString(),eventList);
            }
        }
        System.out.println("Evaluating " + this.name);
        body.evaluate(exp,body.symbolTable, parser);
        return null;
    }
}
