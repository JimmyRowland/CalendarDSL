package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.ArrayList;
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

    public List<Integer> evaluate(){
        List<Integer> ret = new ArrayList<>();
        switch (this.value) {
            case "daily":
                for (int i = 1; i <= 7; i++){
                    ret.add(i);
                }
                return ret;

            case "MWF":
                for (int i = 2; i <=6; i++){
                    ret.add(i);
                }
                return ret;
            case "TTH":
                ret.add(3);
                ret.add(5);
                return ret;
            case "every":
                // need to finish, not sure
                return null;
            default:
                return null;
        }
    }

    public void setValue(String value) {
        this.value = value;
    }
}
