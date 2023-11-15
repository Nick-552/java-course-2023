package edu.hw5.ex2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Friday13thFinder {

    private static final int DEVIL_NUMBER = 13;

    public static List<LocalDate> allFridays13inYear(int year) {
        LocalDate currentDate = LocalDate.of(year, Month.JANUARY, 1);
        currentDate = nextFriday13(currentDate);
        List<LocalDate> fridays13th = new ArrayList<>();
        while (currentDate.getYear() == year) {
            fridays13th.add(currentDate);
            currentDate = nextFriday13(currentDate);
        }
        return fridays13th;
    }

    public static LocalDate nextFriday13(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        LocalDate currentDate = date;
        currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (currentDate.getDayOfMonth() != DEVIL_NUMBER) {
            currentDate = currentDate.with(
                TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusWeeks(1))
            );
        }
        return currentDate;
    }
}
