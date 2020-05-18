package AST;

import java.util.List;

public class Event implements ASTnode {
    String title;
    Occurrence occurrence;
    List<Setting> settings;

    @Override
    public void parse() {

    }

    @Override
    public void evaluate() {

    }
}
