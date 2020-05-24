package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("location:"));
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }



    public String getLocation() {
        return name;
    }
}
