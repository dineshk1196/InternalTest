package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtilities {
    private static Logger log = LoggerFactory.getLogger(DateUtilities.class);

    private static final String DATE_PATTERN = "MM-dd-yyyy";
    private static final String EST_TIME_ZONE = "America/New_York";

    /**
     * Gets date with modified day (either in future or in past )
     *
     * @param addOrRemoveDays : indicates number of days to be added
     * @return
     */
    public static String getDateWithModifiedDay (int addOrRemoveDays) {
        return LocalDate.now().plusDays(addOrRemoveDays).format(DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of(EST_TIME_ZONE)));
    }

    /**
     * Gets date with modified month ( either in future or in past)
     *
     * @param addOrRemoveMonth : indicates number of months to be added
     * @return
     */
    public static String getDateWithModifiedMonth (int addOrRemoveMonth) {
        return LocalDate.now().minusMonths(addOrRemoveMonth).format(DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of(EST_TIME_ZONE)));
    }

    public static String getdate_time(){

        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of(EST_TIME_ZONE)))+"-"+RandomGenerator.numeric(4);
    }

    /**
     * Gets date with modified year ( either in future or in past)
     *
     * @param addOrRemoveYear : indicates number of year to be added
     * @return
     */
    public static String getDateWithModifiedYear (int addOrRemoveYear) {
        return LocalDate.now().minusYears(addOrRemoveYear).format(DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of(EST_TIME_ZONE)));

    }
    /*
     * Get date with additional days  ( + weekend )
     */
    public static String getDateWithWeekEnd(int days){
        List<DayOfWeek> weekend= Arrays.asList(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY);
        for(int i=0;i<7;i++) {
            if(weekend.contains(LocalDate.now().plusDays(days+i).getDayOfWeek())){
                return LocalDate.now().plusDays(days+i).format(DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of(EST_TIME_ZONE)));
            }
        }
        return null;
    }

    public static String getAddDateToDays(String oldDate, int noOfDays) {
        String dt = oldDate;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, noOfDays);  // number of days to add
        dt = sdf.format(c.getTime());
        return dt;
    }

    public static String getCurrentYear(int addOrRemoveYears){
        SimpleDateFormat format = new SimpleDateFormat("YYYY");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, addOrRemoveYears);

        Date d = c.getTime();
        String res = format.format(d);

        return res;
    }

    public static String getMonthNumber(int addOrRemoveMonths){
        SimpleDateFormat format = new SimpleDateFormat("MM");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, addOrRemoveMonths);

        Date d = c.getTime();
        String res = format.format(d);

        return res;
    }
}