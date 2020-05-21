package AST;

import model.io.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repetition implements Setting, ASTnode {
    String value;
    private final List<String> repeatable = Arrays.asList("daily","MWF","TTH", "every");

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.checkToken("repeat:");
        value = Validator.validateRepetition(t.getNext());
        t.getAndCheckNext(";");
        // todo the repetition values need to be turned into some range of time
    }

    @Override
    public void evaluate() {

    }

    public String getRepetitionVal() {
        return value;
    }
}
