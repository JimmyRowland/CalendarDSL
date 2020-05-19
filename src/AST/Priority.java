package AST;

import model.io.Tokenizer;

public class Priority implements Setting, ASTnode {
    int value;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("priority:");
        try {
            value = Integer.parseInt(t.getNext());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid priority value");
        }
        if(value < 1 || value > 2) {
            throw new RuntimeException("Priority value out of range");
        }
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }
}
