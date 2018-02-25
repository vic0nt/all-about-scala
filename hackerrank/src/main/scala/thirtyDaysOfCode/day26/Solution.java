package main.scala.thirtyDaysOfCode.day26;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;

/**
 * Created by vicont on 24.08.2016.
 */
public class Solution {

    private static Integer getFee(LocalDate dateActual, LocalDate dateExpected) {
        if (Period.between(dateExpected, dateActual).getDays() < 1) return 0;
        if (YearMonth.from(dateExpected).equals(YearMonth.from(dateActual)))
            return Period.between(dateExpected, dateActual).getDays() * 15;
        if (Year.from(dateExpected).equals(Year.from(dateActual)))
            return Period.between(dateExpected, dateActual).getMonths() * 500;
        else return 10000;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int dayActual = scan.nextInt();
        int monthActual = scan.nextInt();
        int yearActual = scan.nextInt();
        int dayExpected = scan.nextInt();
        int monthExpected = scan.nextInt();
        int yearExpected = scan.nextInt();

        LocalDate dateExpected = LocalDate.of(yearExpected, monthExpected, dayExpected);
        LocalDate dateActual = LocalDate.of(yearActual, monthActual, dayActual);
        System.out.println(getFee(dateActual, dateExpected));
    }
}
