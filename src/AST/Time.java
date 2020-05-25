package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Time implements ASTnode{
    int time;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("at"));
        String timeStr = t.getNext();
        time = Validator.validateTime(timeStr, 0,23) * 100;
        t.getAndCheckNext(Keyword.keywords.get(":"));
        timeStr = t.getNext();
        time += Validator.validateTime(timeStr, 0, 59);
    }



    public int getTime() {
        return time;
    }


}
