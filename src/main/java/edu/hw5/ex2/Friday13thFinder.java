package edu.hw5.ex2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Friday13thFinder {

    private static final int DEVIL_NUMBER = 13;

    private Friday13thFinder() {}

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

    public static LocalDate nextFriday13(@NotNull LocalDate date) {
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
