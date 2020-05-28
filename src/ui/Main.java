package ui;


import libs.Tokenizer;
import model.Event;
import model.Scheduler;
import model.io.Parser;
import model.io.Writer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Parser parse = new Parser(args[0]);
        if(args.length == 0){
            System.out.println("usage: main path_to_input_file");
            return;
        }
        try{
            Scheduler scheduler = parse.calendar();
            scheduler.allocateFlexibleEvents();
            String[] pathList = args[0].split("/");
            String name = pathList[pathList.length-1] + ".cvs";
            Writer.write(name,scheduler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
