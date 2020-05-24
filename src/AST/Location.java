package AST;


import model.Scheduler;
import libs.Tokenizer;


public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.checkToken("location:");
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return name;
    }
}
