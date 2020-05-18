package model.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {


    private static String program;
    private static List<String> literals;
    private String[] tokens;
    private int currentToken = 0;
    private static Tokenizer theTokenizer;

    private Tokenizer(String filename, List<String> literalsList){
        literals = literalsList;
//        try {
//            program = Files.readString(Paths.get(filename));
//        } catch (IOException e) {
//            System.out.println("Didn't find file");
//            System.exit(0);
//        }
//        tokenize();
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has no spaces around tokens.
    private void tokenize (){
//        //0. Pick some RESERVEDWORD (string which never occurs in your input) : we'll use _
//        //1. Read the whole program into a single string; kill the newlines
//        String tokenizedProgram = program.replace("\n", "");
//        System.out.println(tokenizedProgram);
//        //2. Replace all constant literals with “RESERVEDWORD”<the literal>“RESERVEDWORD”
//        for(String s : literals) {
//            tokenizedProgram = tokenizedProgram.replace(s, "_" + s + "_");
//            System.out.println(tokenizedProgram);
//        }
//        //3. Replace all “RESERVEDWORDRESERVEDWORD” with just “RESERVEDWORD”
//        tokenizedProgram = tokenizedProgram.replace("__","_");
//        System.out.println(tokenizedProgram);
//        //4. Remove leading “_” character, then split on “_”
//        if(tokenizedProgram.length() > 0 && tokenizedProgram.charAt(0) == '_') {
//            tokenizedProgram = tokenizedProgram.substring(1); // without first character
//        }
//        tokens = tokenizedProgram.split("_");
//        System.out.println(Arrays.asList(tokens));
//        //5. Trim whitespace around tokens (unless you want it)
//        for (int i = 0; i < tokens.length; i++) {
//            tokens[i] = tokens[i].trim();
//        }
//        System.out.println(Arrays.asList(tokens));
    }

























    private String checkNext(){
//        String token="";
//        if (currentToken<tokens.length){
//            token = tokens[currentToken];
//        }
//        else
//            token="NO_MORE_TOKENS";
//        return token;
    }

    public String getNext(){
//        String token="";
//        if (currentToken<tokens.length){
//            token = tokens[currentToken];
//            currentToken++;
//        }
//        else
//            token="NULLTOKEN";
//        return token;
    }


    public boolean checkToken(String regexp){
//        String s = checkNext();
//        System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
//        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
//        String s = getNext();
//        if (!s.matches(regexp)) {
//            throw new RuntimeException("Unexpected next token for Parsing! Expected something matching: " + regexp + " but got: " + s);
//        }
//        System.out.println("matched: "+s+"  to  "+regexp);
//        return s;
    }

    public boolean moreTokens(){
        return currentToken<tokens.length;
    }

    public static void makeTokenizer(String filename, List<String> literals){
//        if (theTokenizer==null){
//            theTokenizer = new Tokenizer(filename,literals);
//        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}
