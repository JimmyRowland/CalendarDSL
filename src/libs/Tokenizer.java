package libs;

import model.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {
    private static List<String> literals;
    private static Queue<String> tokens;
    String reservedWord = "#####~~~~~~";
    String reservedReg = "#####~~~~~~";
    private static Tokenizer theTokenizer;

    public Tokenizer(String path, List<String> literals){
        String input = Reader.read(path);
        this.literals = literals;
        tokenize(input);
    }

    private void tokenize (String input){
        input = input.replaceAll("\n","");
        for(String s : literals) {
            input = input.replace(s, reservedWord + s + reservedWord);
        }
        input = input.replaceAll(reservedReg+reservedReg,reservedWord);
        input = input.replaceAll("^"+reservedReg,"");
        Stream<String> tokens = Arrays.stream(input.split(reservedReg));
        tokens = tokens.map(String::trim);
        tokens = tokens.filter((token)->{
            return token.length()>0;
        });
        this.tokens = tokens.collect(Collectors.toCollection(LinkedList::new));
        System.out.println(this.tokens);
        this.theTokenizer = this;
    }

    public String getNext(){
        if (!tokens.isEmpty()){
            return tokens.remove();
        }
        else
            return "NULLTOKEN";
    }
    public String checkNext(){
        String token="";
        if (moreTokens()){
            token = tokens.peek();
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }


    public boolean checkToken(String regexp){
        String s = tokens.isEmpty()?"No Token Left":tokens.peek();
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) {
            throw new RuntimeException("!!!!" + regexp + " does not match " + s);
        }
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }

    public boolean moreTokens(){
        return !tokens.isEmpty();
    }

    public static void makeTokenizer(String filename, List<String> literals){
        if (theTokenizer==null){
            theTokenizer = new Tokenizer(filename,literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

    public String[] getTokenArray(){
        return (String[]) tokens.toArray();
    }

}
