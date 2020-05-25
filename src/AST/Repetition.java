package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class Repetition implements Setting, ASTnode {
    String value;
    List<String> dayList;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("repeat:"));
        dayList = Validator.validateRepetition(t.getNext());
        setRepetition();
        t.getAndCheckNext(Keyword.keywords.get(";"));
        // todo the repetition values need to be turned into some range of time
    }

    public void setRepetition() {
        if (dayList.get(0).equals("MWF")) {
            dayList.clear();
            dayList.add("monday");
            dayList.add("wednesday");
            dayList.add("friday");
        }
        if (dayList.get(0).equals("TTH")) {
            dayList.clear();
            dayList.add("tuesday");
            dayList.add("thursday");
        }
        if (dayList.get(0).equals("daily")) {
            dayList.clear();
            dayList.addAll(Arrays.asList(Keyword.days));
        }
    }

    public List<String> getRepetition() {
        return dayList;
    }
}
