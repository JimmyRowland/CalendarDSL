package AST;

import model.io.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class NewCalendar implements Calendar, ASTnode {
    Title title;
    List<Event> events = new ArrayList<>();

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("new calendar");
        title = new Title();
        title.parse();
        while (t.moreTokens()) {
            if (t.checkToken("new event")) {
                Event e = new Event();
                e.parse();
                events.add(e);
                // t.getNext();
            } else if (t.checkToken("end")) {
                break;
            } else {
                throw new RuntimeException("Invalid Syntax, expected new event or END");
            }
        }
    }

    @Override
    public void evaluate() {

    }

    public Title getTitle() {
        return title;
    }

    public List<Event> getEvents() {
        return events;
    }
}