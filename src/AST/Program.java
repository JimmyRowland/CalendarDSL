package AST;

import model.io.Tokenizer;

public class Program implements ASTnode {

    NewCalendar calendar;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        if(t.checkToken("new calendar")) {
            calendar = new NewCalendar();
            calendar.parse();
            // unique program terminator would allow for more than one cal
        } else {
            throw new RuntimeException("Invalid syntax, expected a calendar declaration");
        }
    }

    @Override
    public void evaluate() {

    }

    public NewCalendar getCalendar() {
        return calendar;
    }
}
