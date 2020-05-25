package AST;

import model.Scheduler;

public interface ASTnode {

    // everything in the AST should have these methods implemented

    public void parse();

    public Scheduler evaluate();

}