package AST;

import model.io.Tokenizer;

public class Day implements ASTnode {
    String day = null;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String token = t.checkNext();
        String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
        for (String s : days) {
            if (token.equalsIgnoreCase(s)) {
                day = token;
                break;
            }
        }
        if (day == null) {
            throw new RuntimeException("Not a valid day");
        }
    }

    @Override
    public void evaluate() {

    }
}
