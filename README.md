# CalendarDSL

**CalendarDSL** is a domain specific language that helps users to optimize their schedules. You can choose to generate a CVS file and import the output file into your Google Calendar. 

### Table of contents

- [EBNF](#ebnf)
- [Sample input](#sample)

## EBNF
```
PROFEAM ::= NEWCALENDAR
NEWCALENDAR ::= “newCalendar:” TITLE? EVENT+ (EVENT GROUP+)? "endOfCalendar"
TITLE ::= STRING
EVENT ::= “newEvent:” TITLE SETTING? OCCURRENCE “endOfEvent”
OCCURRENCE ::= “<” (DATES ( TIMERANGE? | DURATION)) | DURATION “>”
DATES ::= DAY | DAYLIST | DAYRANGE | REPETITION
DURATION ::= "duration:" {1...18}
TIME ::= {6...23} “:” {0...59}
TIMERANGE ::=  "from:" TIME "to:" TIME
DAY ::= “Monday” | “Tuesday” | “Wednesday” | “Thursday” | “Friday” | “Saturday” | “Sunday”
DAYRANGE ::= “from:” DAY “to:” DAY
DAYLIST ::= "[" DAY ("|" DAY){2,6} "]"
GROUP ::=
SETTING ::= LOCATION? DESCRIPTION?
LOCATION ::= “location: ” STRING
DESCRIPTION ::= “description: “ STRING
REPETITION ::= “daysOfWeek:“ “daily” | "S?M?T?W?(Th)?F?s?"
```