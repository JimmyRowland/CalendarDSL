package model.io;

import model.Scheduler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// option
public class Writer {
    public static void write(String fileName, Scheduler scheduler){
        // todo walk scheduler to format data to text
        // todo write file
        String path = "./data/";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path+fileName));
            writer.write(CVS.toCVS(scheduler));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
