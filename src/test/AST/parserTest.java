package test.AST;

import AST.*;
import model.io.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class parserTest {
    Program prog = new Program();
    Tokenizer tokenizer;

    @BeforeEach
    void init() {
        prog = new Program();
        tokenizer = new Tokenizer(new String[]{""});
    }

    @Test
    void simpleTest() {
        String[] tokens = { "new calendar", "my calendar!!", ";",
                            "new event", "big day", ";",
                            "<", "monday", ">",
                            "description:", "blah", ";",
                            "event end",
                            "end" };
        tokenizer = new Tokenizer(tokens);
        prog.parse();
    }
}
