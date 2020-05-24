package AST;


import libs.Tokenizer;


public class Day implements ASTnode {
    String day;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String token = t.getNext();
        day = Validator.validateDay(token);
    }

    public String getDay() {
        return day;
    }
}
