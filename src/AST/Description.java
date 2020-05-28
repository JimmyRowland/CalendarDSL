package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Description implements Setting, ASTnode {
    String desc;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("description:"));
        desc = Validator.validateString(t.getNext());
        t.getAndCheckNext(Keyword.keywords.get(";"));
    }

    @Override
    public void evaluate(Program.EvalObject evalObject) {
        evalObject.setDesc(desc);
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
