# CalendarDSL

**CalendarDSL** is a domain specific language that helps users to optimize their schedules. You can choose to generate a CVS file and import the output file into your Google Calendar. 

### Table of contents

- [EBNF](#ebnf)
- [Sample input](#sample-input)

## EBNF
```
PROFEAM ::= NEWCALENDAR
NEWCALENDAR ::= “newCalendar:” TITLE? EVENT+ (EVENT GROUP+)? "endOfCalendar" PROCEDURE*
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
SETTING ::= LOCATION? DESCRIPTION?
LOCATION ::= “location: ” STRING
DESCRIPTION ::= “description: “ STRING
REPETITION ::= “daysOfWeek:“ “daily” | "S?M?T?W?(Th)?F?s?"
PROCEDURE ::= SCHEDULE | DEF | CALL
SCHEDULE ::= "schedule:" STRING
DEF ::= "DEF" STRING "(" STRING ("|" STRING)* ")" BODY
BODY ::= "{" (SCHEDULE|CALL)+ "}"
CALL ::= "CALL" STRING
STRING ::= \S[0-9a-zA-Z ]*
```
## Sample input

```$xslt
newCalendar:
newEvent:big day0
description:
<duration:1>
endOfEvent
newEvent:big day0
<Monday duration:1>
endOfEvent
newEvent:big day0
<Tuesday duration:1>
endOfEvent
newEvent:big day2
<Monday>
endOfEvent
newEvent:big day3
<Monday from:7:00 to:8:00>
endOfEvent
newEvent:big day4
<Tuesday from:7:00 to:8:00>
endOfEvent
newEvent:big day5
<[Monday|Thursday|Sunday] duration:1>
endOfEvent
newEvent:big day6
<[Thursday|Sunday]>
endOfEvent
newEvent:big day7
<[Thursday|Sunday|Tuesday] from:8:00 to:9:00>
endOfEvent
newEvent:big day8
<from:Monday to:Friday duration:1>
endOfEvent
newEvent:big day9
<daysOfWeek: Ss duration:1>
endOfEvent
newEvent:big day10
<daysOfWeek: Ths from:8:00 to:9:00>
endOfEvent
newEvent:big day11
<daysOfWeek: Ths from:9:00 to:10:00>
endOfEvent
newEvent:big day12
<daysOfWeek: daily duration:1>
endOfEvent
newEvent:big day13
<daysOfWeek: MTTh duration:1>
endOfEvent
newEvent:big day14
<daysOfWeek: MT duration:1>
endOfEvent
newEvent:big day15
<daysOfWeek: Ss duration:1>
endOfEvent
newEvent:big day16
description:
<duration:1>
endOfEvent
newEvent:big day17
<Monday duration:1>
endOfEvent
newEvent:big day18
<[Monday|Thursday|Sunday] duration:1>
endOfEvent
newEvent:big day19
<from:Monday to:Friday duration:1>
endOfEvent
newEvent:big day20
<daysOfWeek: Ss duration:1>
endOfEvent
newEvent:big day21
<daysOfWeek: daily duration:1>
endOfEvent
newEvent:big day22
<daysOfWeek: MTTh duration:1>
endOfEvent
newEvent:big day23
<daysOfWeek: MT duration:1>
endOfEvent
newEvent:big day24
<daysOfWeek: Ss duration:1>
endOfEvent
newEvent:big day25
<Friday duration:1>
endOfEvent
newEvent:big day26
<Friday duration:1>
endOfEvent
newEvent:big day27
<Friday duration:1>
endOfEvent
endOfCalendar
schedule: big day0
schedule: big day27
DEF sche(x|y){
schedule: x
schedule: y
}
CALL sche(big day25|big day26)

```
Output

![alt text][logo]

[logo]: /img/sample.png "Sample output"
