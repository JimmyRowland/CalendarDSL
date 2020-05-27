package libs;

import java.util.Map;

public abstract class Node {
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();

    abstract public void parse();
    abstract public Integer evaluate(Map<String, Integer> symbolTable);

}
