package model.io.ast;

import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.io.Parser;

import java.util.List;
import java.util.Map;

public class SCHEDULE extends STATEMENT {
    private EXP scheduled;

    @Override
    public void parse(Tokenizer tokenizer) {
        tokenizer.getAndCheckNext(Const.SCHEDULE);
        scheduled = new EVENT();
        scheduled.parse(tokenizer);
    }

    @Override
    public List<Event> evaluate(Map<String, EXP> exp, Map<String, List<Event>> symbolTable, Parser parser) {
        System.out.println("SCHEDULING: " + scheduled);
        List<Event> events = scheduled.evaluate(exp, symbolTable, parser);
        if(events == null || events.size() == 0){
            throw new RuntimeException("Unknown event"+scheduled);
        }
        parser.scheduleEvents(events);
        return null; // we only return a value for expressions (EXP); evaluation of statements is via side-effects
    }
}
