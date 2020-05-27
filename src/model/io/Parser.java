package model.io;
import libs.Const;
import libs.Tokenizer;
import model.Event;
import model.EventCreator;
import model.Scheduler;
import model.io.ast.EXP;
import model.io.ast.PROGRAM;
import org.json.JSONObject;

import java.util.*;

public class Parser {
    JSONObject jsonObject;
    Tokenizer t;
    Scheduler scheduler;
    List<String> literals = Arrays.asList(Const.ENDOFEVENT, Const.DAYSOFWEEK, Const.DESCRIPTION, Const.NEWCALENDAR,
            Const.NEWEVENT, Const.OCCURRENCELESSTHAN, Const.OCCURRENCEGREATERTHAN, Const.DURATION, Const.FROM, Const.TO, Const.LEFTBRACKET,
            Const.RIGHTBRACKET, Const.I, Const.LOCATION, Const.ENDOFCALENDAR, Const.SCHEDULE, Const.CURLYBRACKETLEFT, Const.CURLYBRACKETRIGHT,
            Const.DEF, Const.CALL, Const.SMALLBRACKETLEFT, Const.SMALLBRACKETRIGHT);
    Map<String,List<Event>> events;
    public Map<String, EXP> exp;
    public Parser(String path){
        this.t = new Tokenizer(path, literals);
        this.jsonObject = new JSONObject();
//        this.t = Tokenizer.getTokenizer();
        this.scheduler = new Scheduler();
        this.events = new HashMap<>();
        exp = new HashMap<>();
    }

    public void scheduleEvents(List<Event> events){
        for(Event event:events){
            scheduler.addEvent(event);
        }
        scheduler.allocateFlexibleEvents();
        events.clear();
    }

    public Scheduler calendar() throws Exception{
        String name;
        this.t.getAndCheckNext(Const.NEWCALENDAR);
        if(!t.checkToken(Const.NEWEVENT)){
            name = t.getNext();
        }
        while(t.checkToken(Const.NEWEVENT)){
            Event event = event();
        }
        String token = t.getAndCheckNext(Const.ENDOFCALENDAR);
//        token = t.getNext();
        PROGRAM program = new PROGRAM();
        program.parse(t);
        program.evaluate(exp,events,this);

        events.forEach((k,v)->{
            for(Event event:v){
                scheduler.addEvent(event);
            }
        });
        return this.scheduler;
    }

    private void addFlexEventToMap(Event event){
        if(!this.events.containsKey(event.getName())){
            events.put(event.getName(),new ArrayList<>());
        }
        events.get(event.getName()).add(event);
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
//            scheduler.addEvent(flexibleEvent);
            t.getAndCheckNext(Const.OCCURRENCEGREATERTHAN);
            t.getAndCheckNext(Const.ENDOFEVENT);
            addFlexEventToMap(flexibleEvent);
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
            Event event = EventCreator.createEvent(null,null,name,location,description,duration,dayOfWeek,daysOfWeek);
            addFlexEventToMap(event);
            t.getAndCheckNext(Const.OCCURRENCEGREATERTHAN);
            t.getAndCheckNext(Const.ENDOFEVENT);
            return event;
        }else if(t.checkToken(Const.FROM)){
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

    public Scheduler getScheduler() {
        return scheduler;
    }

    public Map<String, List<Event>> getEvents() {
        return events;
    }
}
