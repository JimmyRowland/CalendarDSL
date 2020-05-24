package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class Repetition implements Setting, ASTnode {
    String value;
    private final List<String> repeatable = Arrays.asList("daily","MWF","TTH", "every");

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.checkToken(Keyword.keywords.get("repeat:"));
        value = Validator.validateRepetition(t.getNext());
        t.getAndCheckNext(Keyword.keywords.get(";"));
        // todo the repetition values need to be turned into some range of time
    }



    public String getRepetitionVal() {
        return value;
    }
}
