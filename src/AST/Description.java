package AST;

<<<<<<< HEAD
import model.Scheduler;
import model.io.Tokenizer;

=======
>>>>>>> origin/dev
public class Description implements Setting, ASTnode {
    String desc;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("description:");
        desc = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public Scheduler evaluate() {

    }

    public String getDesc() {
        return desc;
    }
}
