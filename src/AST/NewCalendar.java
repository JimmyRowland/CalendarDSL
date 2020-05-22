package AST;

<<<<<<< HEAD
import model.Scheduler;
import model.io.Tokenizer;

=======
>>>>>>> origin/dev
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
<<<<<<< HEAD
                t.getNext();
            } else if (t.checkToken("end")) {

=======
                // t.getNext();
            } else if (t.checkToken("end")) {
                break;
>>>>>>> origin/dev
            } else {
                throw new RuntimeException("Invalid Syntax, expected new event or END");
            }
        }
    }

    @Override
    public Scheduler evaluate() {

    }

    public Title getTitle() {
        return title;
    }

    public List<Event> getEvents() {
        return events;
    }
<<<<<<< HEAD
=======

    public Title getTitle() {
        return title;
    }

    public List<Event> getEvents() {
        return events;
    }
>>>>>>> origin/dev
}