package AST;

import model.io.Tokenizer;

import java.util.List;

public class NewCalendar implements Calendar, ASTnode {
    Title title;
    List<Event> events;

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
            }else if (t.checkToken("end")) {

            } else {
                throw new RuntimeException("Invalid Syntax, expected new event or END");
            }
        }
    }

    @Override
    public void evaluate() {

    }
}