package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewCalendar implements Calendar, ASTnode {
    Title title;
    List<Event> events = new ArrayList<>();
    HashMap<String, String> keys = Keyword.keywords;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(keys.get("new calendar"));
        title = new Title();
        title.parse();
        while (t.moreTokens()) {
            if (t.checkToken(keys.get("new event"))) {
                Event e = new Event();
                e.parse();
                events.add(e);
                t.getNext();
            } else if (t.checkToken("end")) {
                break;
            } else {
                throw new RuntimeException("Invalid Syntax, expected new event or END");
            }
        }
    }



    public Title getTitle() {
        return title;
    }

    public List<Event> getEvents() {
        return events;
    }

}