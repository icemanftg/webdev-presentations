package ro.teamnet.presentation.intuitive;

import org.joda.time.*;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import ro.teamnet.presentation.reflection.Runnable;

import java.util.Locale;

/**
 * @author Alexandru Burghelea
 * @since 25.09.2012
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class JodaExamples {


    private static final String DATE_FORMAT = "dd-MMM-yyyy";

    /**
     * Calculates Days, Months, Years between two dates
     */
    @Runnable
    public void countingBetweenDates() {
        DateMidnight start = new DateMidnight("2010-02-01");
        DateMidnight end = new DateMidnight("2012-02-07");

        int days = Days.daysBetween(start, end).getDays();
        int months = Months.monthsBetween(start, end).getMonths();
        int years = Years.yearsBetween(start, end).getYears();

        System.out.println("Numarul de zile intre " +
                start.toString(DATE_FORMAT) + " si " +
                end.toString(DATE_FORMAT) + " = " + days);
        System.out.println("Numarul de luni intre " +
                start.toString(DATE_FORMAT) + " si " +
                end.toString(DATE_FORMAT) + " = " + months);
        System.out.println("Numarul de ani intre " +
                start.toString(DATE_FORMAT) + " si " +
                end.toString(DATE_FORMAT) + " = " + years);
    }

    /**
     * Calculating last day of the current month
     */
    @Runnable
    public void lastDayOfCurrentMonth() {
        DateTime dateTime = new DateTime();
        DateTime lastDate = dateTime.dayOfMonth().withMaximumValue();

        System.out.println("Data = " + lastDate.toString());
        System.out.println("Ultima zi  = " + lastDate.dayOfWeek().getAsText());
        System.out.println("Bonus chinezesc = " + lastDate.toString("E dd/MM/yyyy", Locale.CHINESE));
    }

    /**
     * Calculating last day of february from 5 years ago
     */
    @Runnable
    public void dateAndMonth() {
        DateTime now = DateTime.now();
        DateTime then = now.minusYears(5)                   // 5 Years ago
                .monthOfYear()                              // Month property
                .setCopy(DateTimeConstants.FEBRUARY)        // set it to February
                .dayOfMonth()                               // Day propery
                .withMaximumValue();                        // setting it to the last day

        System.out.println(then.toString("dd-MM-yyyy") + " a fost " + then.toString("EEEE", new Locale("ro", "RO")));
    }

    /**
     * Working with intervals and periods
     */
    @Runnable
    public void intervalVsPeriod() {
        DateTime startDate = new DateTime();
        DateTime endDate = startDate.plus(Months.months(2)).plus(Days.days(3));
        Interval interval = new Interval(startDate, endDate);

        System.out.println("Interval = " + interval.toString());
        System.out.println("Start    = " + interval.getStart().toString("dd-MMM-yyyy"));
        System.out.println("End      = " + interval.getEnd().toString("dd-MMM-yyyy"));

        interval = interval.withEnd(interval.getEnd().plusMonths(1));
        System.out.println("Interval = " + interval.toString());

        Period period = interval.toPeriod(PeriodType.yearMonthDay());
        System.out.println("Perioada = " + period.toString());
        System.out.println("Perioada multiplicata " + period.multipliedBy(2).toString());
        System.out.println("Perioada negata = " + period.negated().toString());
        System.out.println("Perioada in zile = " + period.toString(freekyFormatter()));

        Duration duration = interval.toDuration();
        System.out.println("Durata in zile = " + duration.toStandardDays().getDays());
    }

    /**
     * Defining your own formatter;
     *
     * @return a custome("freeky")
     */
    private PeriodFormatter freekyFormatter() {
        return new PeriodFormatterBuilder()
                .appendDays()
                .appendSuffix(" zi", " zile")
                .appendSeparator(" ")
                .appendHours()
                .appendSeparator(":")
                .appendMinutes().minimumPrintedDigits(2)
                .appendSeparator(":")
                .appendSeconds().minimumPrintedDigits(2)
                .toFormatter();
    }

    /**
     * Working with "callendars"
     */
    @Runnable
    public void chronologyShowDown() {
        Chronology coptic = CopticChronology.getInstance();
        Chronology buddhist = BuddhistChronology.getInstance();

        DateTime now = new DateTime();
        DateTime copticNow = new DateTime(now, coptic);
        DateTime buddhistNow = new DateTime(buddhist);

        System.out.println("An coptic =" + copticNow.getYear());
        System.out.println("An buddist = " + buddhistNow.getYear());
    }
}
