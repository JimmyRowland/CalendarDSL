package libs;

import model.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {
    private final List<String> literals;
    private Queue<String> tokens;
    String reservedWord = "49*@#uHNED:LKGSD*BHW#";
    String reservedReg = "49\\*@#uHNED:LKGSD\\*BHW#";

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
    }

    public String getNext(){
        if (!tokens.isEmpty()){
            return tokens.remove();
        }
        else
            return "NULLTOKEN";
    }


    public boolean checkToken(String regexp){
        String s = tokens.isEmpty()?"No Token Left":tokens.peek();
//        System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
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

}
