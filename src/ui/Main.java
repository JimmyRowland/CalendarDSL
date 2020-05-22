package ui;


import libs.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //make me a square called Biff please
        //connect Fido to Biff
//        List<String> literals = Arrays.asList("Hello!", "make me a", "called", "please", "connect", "to", "Thanks!");

        List<String> literals = Arrays.asList(";", "done", "new calendar", "called:", "new event", "event end",
                "group:", "<", ">", "(", ",", ")", "|", "start", "finish", "location:", "repeat:",
                "daily", "every", "priority", "description:", "only at");
        Tokenizer.makeTokenizer("input.tdot",literals);

    }

}
