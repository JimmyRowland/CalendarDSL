package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.HashMap;
import java.util.List;

public class Event implements ASTnode {
    Title title;
    Occurrence occurrence;
    Group group;
    Location location;
    Repetition repeat;
    Description description;
    HashMap<String, String> keys = Keyword.keywords;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        occurrence = null;
        // todo add group functionality
        String token = t.getNext();
        if (token.equals(keys.get("new event"))) {
            title = new Title();
            title.parse();
            token = t.checkNext();
            if (token.equals("<")) {
                occurrence = new Occurrence();
                occurrence.parse();
                token = t.checkNext();
            }
            // loop for settings
            while (!token.equals(keys.get("event end"))) {
                if (!Validator.getValidSettingKeyword(token)) {
                    throw new RuntimeException("Invalid setting type: " + token);
                }
                Setting s = Validator.getAndSettingType(token, this);
                s.parse();
                token = t.checkNext();
            }
            t.getNext();
        } else if (token.equals(keys.get("group:"))) {
            group = new Group();
            group.parse();
            title = group.title;
        } else {
            throw new RuntimeException("Expected event declaration");
        }
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRepeat(Repetition repeat) {
        this.repeat = repeat;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getTitle() {
        if (title != null) {
            return title.getTitle();
        }
        return null;
    }

    public Object getOccurrence() {
        if (occurrence != null) {
            return this.occurrence.getRange();
        }
        return null;
    }

    public String getLocation() {
        if (location != null) {
            return location.getLocation();
        }
        return null;
    }

    public List<String> getRepeat() {
        if (repeat != null) {
            return repeat.dayList;
        }
        return null;
    }

    public String getDescription() {
        if (description != null) {
            return description.desc;
        }
        return null;
    }

    public String getGroupTitle() {
        if (group != null) {
            return group.title.getTitle();
        }
        return null;
    }

    public List<Event> getGroupEvents() {
        if (group != null) {
            return group.events;
        }
        return null;
    }
}
