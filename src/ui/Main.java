package ui;


import libs.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
     List<String> literals = Arrays.asList(";", "done", "new calendar", "new event", "event end",
                "group:", "<", ">", "(", ",", ")", "|", "start", "finish", "location:", "repeat:",
                "daily", "every", "priority", "description:", "done","only at");

    Tokenizer.makeTokenizer("src/test/AST/test1",literals);
    }

}
