package AST;


import libs.Tokenizer;


public class Duration extends Occurrence implements ASTnode {

    public void setHours(int hours) {
        this.hours = hours;
    }

    int hours;
//    Day day;

    @Override
    public void parse() {
        try {
            hours = Integer.parseInt(Tokenizer.getTokenizer().getNext());
            if (hours > 23 || hours < 1 ) {
                throw new RuntimeException("Duration out of range");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Duration not a number");
        }
    }

    public void evaluate(Program.EvalObject evalObject) {
        evalObject.setDur(hours);
    }


}
