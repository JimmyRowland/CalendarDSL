package model.io;
import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.EventCreator;
import model.Scheduler;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    JSONObject jsonObject;
    Tokenizer t;
    Scheduler scheduler;
    List<String> literals = Arrays.asList(Const.ENDOFEVENT, Const.DAYSOFWEEK, Const.DESCRIPTION, Const.NEWCALENDAR,
            Const.NEWEVENT, Const.OCCURRENCELESSTHAN, Const.OCCURRENCEGREATERTHAN, Const.DURATION, Const.FROM, Const.TO, Const.LEFTBRACKET,
            Const.RIGHTBRACKET, Const.I, Const.LOCATION, Const.ENDOFCALENDAR);
    List<Event> event;
    public Parser(String path){
        this.t = new Tokenizer(path, literals);
        this.jsonObject = new JSONObject();
//        this.t = Tokenizer.getTokenizer();
        this.scheduler = new Scheduler();
        this.event = new ArrayList<>();
    }

    public Scheduler calendar() throws Exception{
        String name;
        this.t.getAndCheckNext(Const.NEWCALENDAR);
        if(!t.checkToken(Const.NEWEVENT)){
            name = t.getNext();
        }
        while(t.checkToken(Const.NEWEVENT)){
            this.event.add(event());
        }
        String token = t.getNext();
        return this.scheduler;
    }


    public Event event() throws Exception{
        t.getAndCheckNext(Const.NEWEVENT);
        String name = t.getNext();
        String location = location();
        String description = description();
        List<Integer> daysOfWeek = null;
        int duration = 0;
        int dayOfWeek = 0;
        String startTime = null;
        String endTime = null;
        t.getAndCheckNext(Const.OCCURRENCELESSTHAN);
        if(t.checkToken(Const.DURATION)){
            t.getNext();
            duration = Integer.parseInt(t.getNext());
            Event flexibleEvent = EventCreator.createEvent(duration,name,location,description);
            scheduler.addEvent(EventCreator.createEvent(duration,name,location,description));
            t.getAndCheckNext(Const.OCCURRENCEGREATERTHAN);
            t.getAndCheckNext(Const.ENDOFEVENT);
            return flexibleEvent;
        }else if(t.checkToken(Const.LEFTBRACKETREG)){
            daysOfWeek = dayList();
        }else if(t.checkToken(Const.FROM)){
            daysOfWeek = dayRange();
        }else if(t.checkToken(Const.DAYSOFWEEK)){
            daysOfWeek = getDaysOfWeek();
        } else{
            dayOfWeek = Const.dayOfWeek.get(t.getNext());
        }
        if(t.checkToken(Const.DURATION)){
            t.getNext();
            duration = Integer.parseInt(t.getNext());
        }else{
            t.getAndCheckNext(Const.FROM);
            startTime = t.getNext();
            t.getAndCheckNext(Const.TO);
            endTime = t.getNext();
        }
        t.getAndCheckNext(Const.OCCURRENCEGREATERTHAN);
        t.getAndCheckNext(Const.ENDOFEVENT);
        Event event = EventCreator.createEvent(startTime,endTime,name,location,description,duration,dayOfWeek,daysOfWeek);
        scheduler.addEvent(event);
        return event;
    }

    private List<Integer> getDaysOfWeek() throws Exception{
        List<Integer> daylist = new ArrayList<>();
        t.getAndCheckNext(Const.DAYSOFWEEK);
        if(t.checkToken("daily")){
            t.getNext();
            return Arrays.asList(1,2,3,4,5,6,7);
        }else if(!(t.checkToken(Const.FROM)||t.checkToken(Const.DURATION)||t.checkToken(Const.OCCURRENCEGREATERTHAN))){
            String days = t.getNext();
            if(!days.matches("S?M?T?W?(Th)?F?s?")){
                throw new Exception("Invalid days of week");
            }
            if(days.contains("Th")){
                daylist.add(5);
                days = days.replace("Th","");
            }
            for(int i = 0; i < days.length(); i++){
                daylist.add(Const.dayOfWeek.get(days.substring(i,i+1)));
            }
        }else{
            daylist = Arrays.asList(1,2,3,4,5,6,7);
        }
        return daylist;
    }

    private List<Integer> dayRange() throws Exception {
        List<Integer> daylist = new ArrayList<>();
        t.getAndCheckNext(Const.FROM);
        int startWod = Const.dayOfWeek.get(t.getNext());
        t.getAndCheckNext(Const.TO);
        int endWod = Const.dayOfWeek.get(t.getNext());
        if(endWod<=startWod){
            throw new Exception("End date must be after start date");
        }
        for(int i = startWod; i <= endWod; i++){
            daylist.add(i);
        }
        return daylist;
    }

    private List<Integer> dayList(){
        List<Integer> daylist = new ArrayList<>();
        t.getAndCheckNext(Const.LEFTBRACKETREG);
        daylist.add(Const.dayOfWeek.get(t.getNext()));
        while(t.checkToken(Const.IREG)){
            t.getNext();
            daylist.add(Const.dayOfWeek.get(t.getNext()));
        }
        t.getAndCheckNext(Const.RIGHTBRACKETREG);
        return daylist;
    }

    private String description() {
        String description = "";
        if(t.checkToken(Const.DESCRIPTION)){
            t.getNext();
            if(!(t.checkToken(Const.OCCURRENCELESSTHAN))){
                description = t.getNext();
            }
        }
        return description;
    }

    private String location() {
        String location = "";
        if(t.checkToken(Const.LOCATION)){
            t.getNext();
            if(!(t.checkToken(Const.OCCURRENCELESSTHAN)||t.checkToken(Const.DESCRIPTION))){
                location = t.getNext();
            }
        }
        return location;
    }


}
