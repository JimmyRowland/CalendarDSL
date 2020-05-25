package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.List;

public class Repetition implements Setting, ASTnode {
    String value;
    List<String> dayList;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("repeat:"));
        dayList = Validator.validateRepetition(t.getNext());
        t.getAndCheckNext(Keyword.keywords.get(";"));
        // todo the repetition values need to be turned into some range of time
    }



    public String getRepetitionVal() {
        return value;
    }
}
