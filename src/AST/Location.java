package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("location:"));
        // t.getAndCheckNext(Keyword.keywords.get("location:"));
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate(Program.EvalObject evalObject) {
        evalObject.setLocation(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return name;
    }
}
