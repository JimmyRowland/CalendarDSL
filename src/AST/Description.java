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



    public String getDesc() {
        return desc;
    }
}
