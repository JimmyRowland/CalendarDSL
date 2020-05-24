package AST;


import model.Scheduler;
import libs.Tokenizer;


public class Description implements Setting, ASTnode {
    String desc;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("description:");
        desc = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
