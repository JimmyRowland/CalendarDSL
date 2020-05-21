package ui;


import libs.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("new calendar", "end", "called:", ";", "new event", "event end",
                    "group:", "<", ">", "(", ",", ")", "at", "|", ":", "start", "finish", "location:", "repeat:",
                    "daily", "every", "priority", "description");

        Tokenizer.makeTokenizer("input.thtml",literals);
    }

}