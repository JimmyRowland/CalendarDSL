package AST;


import libs.Keyword;
import libs.Tokenizer;


public class DayRange extends Occurrence implements ASTnode{
    Day from;
    Day to;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("from"));
        from = new Day();
        from.parse();
        t.getAndCheckNext(Keyword.keywords.get("to"));
        to = new Day();
        to.parse();
    }

    public void setFrom(Day from) {
        this.from = from;
    }

    public void setTo(Day to) {
        this.to = to;
    }


    public Day getFrom() {
        return from;
    }

    public Day getTo() {
        return to;
    }

    public String getDayRange() {
        return from + "to" + to;
    }
}
