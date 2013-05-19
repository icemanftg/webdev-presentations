package ro.teamnet.presentation.workingdays;

import org.joda.time.DateMidnight;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.Date;

/**
 * @author Alexandru Burghelea
 * @since 25.09.2012
 */
public class JodaWorkingDaysCounter implements WorkingDaysCounter {

    /**
     * Counts days between two dates (regardless of timezone/ and time).
     * <p/>
     * It accepts {@link java.util.Date}, but it converts them to
     * {@link org.joda.time.LocalDate}.
     * This method is only a wrapper for
     * {@link #countWorkingDaysBetween(org.joda.time.LocalDate, org.joda.time.LocalDate)}.
     *
     * @param from start Date for counting (inclusive count)
     * @param to   end Date for counting (exclusive)
     * @return number of working days between the dates
     */
    @Override
    public int countWorkingDaysBetween(Date from, Date to) {

        LocalDate localFrom = (new DateMidnight(from.getTime())).toLocalDate();
        LocalDate localTo = (new DateMidnight(to.getTime())).toLocalDate();

        return countWorkingDaysBetween(localFrom, localTo);
    }

    /**
     * Counts days between two dates.
     * If from is after to, the dates are reversed.
     *
     * @param from start LocarDate for counting(inclusive count)
     * @param to   end LocalDate for counting(exclusive count)
     * @return number of working days between the dates
     */
    public int countWorkingDaysBetween(LocalDate from, LocalDate to) {

        if (from.isAfter(to)) {
            LocalDate auxLocalDate = from;
            from = to;
            to = auxLocalDate;
        }

        int weekDays = 0;
        while (from.isBefore(to)) {
            if (!isWeekendDay(from.getDayOfWeek()))
                weekDays++;
            from = from.plusDays(1);
        }

        return weekDays;
    }

    /**
     * Checks if a day of week index represents a weekend day (joda style).
     *
     * @param dayOfWeek The index that the day has in a week
     * @return <code>true</code> if the dayOfWeek reprezents SATURDAY or SUNDAY,
     *         false otherwise.
     */
    private boolean isWeekendDay(int dayOfWeek) {
        return dayOfWeek == DateTimeConstants.SATURDAY || dayOfWeek == DateTimeConstants.SUNDAY;
    }
}
