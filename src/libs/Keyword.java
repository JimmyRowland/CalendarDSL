package libs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Keyword {

    public static HashMap<String, String> keywords = new HashMap<>();
    // don't change this list - this is the literals that we had when implementing parser
    public static List<String> literals = Arrays.asList("new calendar", "done", "end", "called:", ";", "new event",
            "event end", "group", ">", "(", ",", ")", "<", "at", ">", "from", "to", "start",
            "finish", "location:", "repeat:","|", "description:" );
    public static String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
    public static String[] settingkeys = {"location:", "repeat:", "description:"};
    public static String[] reservedWords = { ";", "<", ">", ":" };
    public static String[] repeatable = {"daily","MWF","TTH"};

    public Keyword() {
        for (String s : literals) {
            keywords.put(s, s);
        }
        updateKeywords();
    }

    // any changes to literals can be added here
    public void updateKeywords() {
        keywords.replace("done", ";");
        keywords.replace("end", ";");
        keywords.replace("at", "@");
        keywords.replace("(", "\\(");
    }
}
