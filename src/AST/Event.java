package AST;

import model.io.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Event implements ASTnode {
    Title title;
    Occurrence occurrence;
    List<Setting> settings;
    Group group;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        occurrence = null;
        settings = new ArrayList<>();
        // todo add group functionality
        String token = t.getNext();
        if (token.equals("new event")) {
            title = new Title();
            title.parse();
            token = t.checkNext();
            if (token.equals("<")) {
                occurrence = new Occurrence();
                occurrence.parse();
                token = t.checkNext();
            }
            // loop for settings
            while (!token.equals("event end")) {
                if (!Validator.getValidSettingKeyword(token)) {
                    throw new RuntimeException("Invalid setting type");
                }
                Setting s = Validator.getSettingType(token);
                s.parse();
                settings.add(s);
                token = t.getNext();
            }
        } else if (token.equals("group:")) {
            group = new Group();
            group.parse();
            title = group.title;
        } else {
            throw new RuntimeException("Expected event declaration");
        }
    }

    @Override
    public void evaluate() {
        // todo build event into model here?
    }
}
