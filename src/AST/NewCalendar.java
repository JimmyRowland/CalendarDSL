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
//                t.getNext();
            } else if (t.checkToken(keys.get("group:"))) {
                Event g = new Group();
                g.parse();
                events.add(g);
//                t.getNext();
            }else if (t.checkToken((keys.get("done")))) {
                break;
            } else {
                throw new RuntimeException("Invalid Syntax, expected new event, group, or ;");
            }
        }
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Title getTitle() {
        return title;
    }

    public List<Event> getEvents() {
        return events;
    }

}