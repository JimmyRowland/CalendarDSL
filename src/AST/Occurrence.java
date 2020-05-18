package AST;

import java.util.Calendar;

public class Occurrence implements ASTnode {

    Calendar Day;
    DayRange dayRange;
    TimeRange timeRange;

    @Override
    public void parse() {

    }

    @Override
    public void evaluate() {

    }
}
