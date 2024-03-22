package com.bj.disc.activity;

import java.util.Arrays;
import java.util.Comparator;

public class CanAttendMeetingsTest {

    public static void main(String[] args) {
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        boolean canAttendMeetings = canAttendMeetings(intervals);
        System.out.println("canAttendMeetings = " + canAttendMeetings);

    }

    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
