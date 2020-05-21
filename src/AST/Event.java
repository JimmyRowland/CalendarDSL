package AST;

import model.io.Tokenizer;

public class Event implements ASTnode {
    Title title;
    Occurrence occurrence;
    Group group;
    Location location;
    Repetition repeat;
    Description description;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        occurrence = null;
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
                Setting s = Validator.getAndSettingType(token, this);
                s.parse();
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
