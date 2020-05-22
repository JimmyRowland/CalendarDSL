package AST;

<<<<<<< HEAD
import model.Scheduler;
import model.io.Tokenizer;

=======
>>>>>>> origin/dev
public class Day implements ASTnode {
    String day;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String token = t.getNext();
        day = Validator.validateDay(token);
    }

    @Override
    public Scheduler evaluate() {

    }

    public String getDay() {
        return day;
    }
}
