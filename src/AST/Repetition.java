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
        t.getAndCheckNext("repeat:");
        String token = t.getNext();
        // todo the repetition values need to be turned into some range of time
        if (repeatable.contains(token)) {
            if(t.equals("every")) {
                value = token + t.getNext();
            }else {
                value = token;
            }
        } else {
            throw new RuntimeException("Not valid repetition interval");
        }
    }

    @Override
    public void evaluate() {

    }

}
