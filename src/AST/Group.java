package AST;


import model.Scheduler;
import libs.Tokenizer;
import java.util.ArrayList;
import java.util.List;

public class Group implements ASTnode {
    Title title;
    List<String> events;

    @Override
    public void parse() {
        events = new ArrayList<>();
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("group:");
        title = new Title();
        title.parse();
        t.getAndCheckNext(">");
        t.getAndCheckNext("(");
        String token = t.getNext();
        while(!token.equals(")")){ // todo this needs testing
            events.add(Validator.validateExistingEvent(t.getNext()));
            token = t.getNext();
            if (!(token.equals(")") || token.equals(","))) {
                throw new RuntimeException("invalid grouping");
            }
            if(token.equals(",")) {
                token = t.getNext();
                if(token.equals(")")) {
                    throw new RuntimeException("unexpected token recieved after comma");
                }
            }
        }

    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String getTitle() {
        return title.getTitle();
    }

}
