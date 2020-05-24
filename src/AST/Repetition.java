package AST;


import model.Scheduler;
import libs.Tokenizer;

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



    public String getRepetitionVal() {
        return value;
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
