package com.ballfish.utility;

public class TimeManager {
    public static String milliSecondsToTimer(long milliseconds) {
        String result = "";

        int hours = (int) (milliseconds / 3600000);
        int minutes = (int) (milliseconds % 3600000 / 60000);
        int seconds = (int) (milliseconds % 3600000 % 60000 / 1000);

        if (hours > 0) {
            result += hours + ":";
        }

        if (minutes < 10) {
            result += "0" + minutes + ":";
        } else {
            result += minutes + ":";
        }

        if (seconds < 10) {
            result += "0" + seconds;
        } else {
            result += seconds;
        }

        return result;
    }
}
