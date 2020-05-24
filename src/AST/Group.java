package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group implements ASTnode {
    Title title;
    List<String> events;
    HashMap<String, String> keys = Keyword.keywords;

    @Override
    public void parse() {
        events = new ArrayList<>();
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(keys.get("group:"));
        title = new Title();
        title.parse();
        t.getAndCheckNext(keys.get(">"));
        t.getAndCheckNext(keys.get("("));
        String token = t.getNext();
        while(!token.equals(keys.get(")"))){ // todo this needs testing
            events.add(Validator.validateExistingEvent(t.getNext()));
            token = t.getNext();
            if (!(token.equals(keys.get(")")) || token.equals(keys.get(",")))) {
                throw new RuntimeException("invalid grouping");
            }
            if(token.equals(keys.get(","))) {
                token = t.getNext();
                if(token.equals(keys.get(")"))) {
                    throw new RuntimeException("unexpected token recieved after comma");
                }
            }
        }

    }



    public String getTitle() {
        return title.getTitle();
    }

}
