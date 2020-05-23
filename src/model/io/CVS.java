package model.io;

import model.Day;
import model.Event;
import model.Scheduler;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CVS {
    public static String toCVS(Scheduler scheduler){
        String result = "Subject,Start Date,Start Time,End Date,End Time,All Day Event,Description,Location,Private\n";
        for(Day day: scheduler.getDays()){
            for(Event event: day.getEvents()){
                result+= CVS.eventToString(event);
            }
        }
        return result;
    }

    public static String eventToString(Event event){
        String subject = event.getName();
        String startDate = CVS.getCalendarDate(event.getStart());
        String endDate = startDate;
        String startTime = CVS.getCalendarTime(event.getStart());
        String endTime = CVS.getCalendarTime(event.getEnd());
        String allDayEvent = "False";
        String description = event.getDescription();
        String location = event.getLocation();
        String isEventPrivate = "False";
        String result = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n", subject,
                startDate,startTime,endDate,endTime,allDayEvent,description,location,isEventPrivate);
        return result;
    }

    public static String getCalendarDate(Calendar calendar){
        return String.format("%s/%s/%s" ,CVS.monthToString(calendar.get(Calendar.MONTH)+1),calendar.get(Calendar.DATE),calendar.get(Calendar.YEAR));
    }

    public static String monthToString(int month){
        return month>9 ? ""+month : "0"+month;
    }

    public static String getCalendarTime(Calendar calendar){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String ampm = "";
        String hourString = "";
        if(hour>12){
            hourString = ""+(hour-12);
            ampm = "PM";
        }else{
            hourString = ""+hour;
            ampm = "AM";
        }
        int minute = calendar.get(Calendar.MINUTE);
        String minuteString = minute >9? ""+minute : "0"+minute;
        return String.format("%s:%s %s", hourString,minuteString,ampm);
    }
}
