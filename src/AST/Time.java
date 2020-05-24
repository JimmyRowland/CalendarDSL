package AST;


import libs.Tokenizer;


public class Time implements ASTnode{
    int time;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String timeStr = t.getNext();
        String[] times = timeStr.split(":");
        time = Validator.validateTime(times[0], 0,23) * 100;
        time += Validator.validateTime(times[1], 0, 59);
    }



    public int getTime() {
        return time;
    }


}
