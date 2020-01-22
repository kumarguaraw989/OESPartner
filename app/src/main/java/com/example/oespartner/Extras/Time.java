package com.example.oespartner.Extras;

public class Time {

    int seconds;
    int minutes;
    int hours;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static String getTime(int attendanceHour, int attendanceMinute, int attendanceSecond) {
        Time start = new Time(10, 00, 00),
                stop = new Time(attendanceHour, attendanceMinute, attendanceSecond),
                diff;

        diff = difference(start, stop);

        System.out.printf("TIME DIFFERENCE: %d:%d:%d - ", start.hours, start.minutes, start.seconds);
        System.out.printf("%d:%d:%d ", stop.hours, stop.minutes, stop.seconds);
        System.out.printf("= %d:%d:%d\n", diff.hours, diff.minutes, diff.seconds);
        return diff.hours + ":" + diff.minutes + ":" + diff.minutes;
    }

    public static Time difference(Time start, Time stop) {
        Time diff = new Time(0, 0, 0);

        if (stop.seconds > start.seconds) {
            --start.minutes;
            start.seconds += 60;
        }

        diff.seconds = start.seconds - stop.seconds;
        if (stop.minutes > start.minutes) {
            --start.hours;
            start.minutes += 60;
        }

        diff.minutes = start.minutes - stop.minutes;
        diff.hours = start.hours - stop.hours;

        return (diff);
    }
}