package model.io.ast;

import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PROCBODY extends EXP {
    private List<STATEMENT> statements = new ArrayList<>();
    private String name;
    private EXP returnValue;
    private List<EXP> args;
    Map<String, List<Event>> symbolTable;

    public PROCBODY(String name){
        this.name = name;
    }

    @Override
    public void parse(Tokenizer tokenizer) {
        if(tokenizer.checkToken("\\(")){
            String token = tokenizer.getNext();
            args= new ArrayList<>();
            EVENT event = new EVENT();
            args.add(event);
            event.parse(tokenizer);
            while(tokenizer.checkToken(Const.IREG)){
                token = tokenizer.getNext();
                event = new EVENT();
                event.parse(tokenizer);
                args.add(event);
            }
            tokenizer.getAndCheckNext("\\)");
        }
        tokenizer.getAndCheckNext("\\{");
        while (!tokenizer.checkToken("\\}")) {
            STATEMENT s = null;
            if (tokenizer.checkToken(Const.SCHEDULE)) {
                s = new SCHEDULE();
            }else if(tokenizer.checkToken(Const.CALL)){
                s = new PROCCALL();
            } else {
                throw new RuntimeException("Unknown statement:" + tokenizer.getNext());
            }
//            if(s == null){
//                String token = tokenizer.getNext();
//                System.out.println(token);;
//            }
            s.parse(tokenizer);
            statements.add(s);
        }
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
        for (STATEMENT s : statements){
            s.evaluate(exp, this.symbolTable, parser);
        }
        return null;
    }

    @Override
    public String toString() {
        return name+"()";
    }


    public List<EXP> getArgs() {
        return args;
    }
}
