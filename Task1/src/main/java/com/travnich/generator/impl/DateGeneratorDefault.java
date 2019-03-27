package com.travnich.generator.impl;

import com.travnich.generator.DateGenerator;
import com.travnich.generator.NumberGenerator;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGeneratorDefault implements DateGenerator {

    @Override
    public LocalDate generate(int yearsAgo) {
        LocalDate curDate = LocalDate.now();
        int curYear = curDate.getYear();
        int curDateOfYear = curDate.getDayOfYear();
        NumberGenerator numberGenerator = new NumberGeneratorDefault();

        GregorianCalendar gc = new GregorianCalendar();
        int year = numberGenerator.generate(curYear - yearsAgo, curYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear;
        if (year == curYear) {
            dayOfYear = numberGenerator.generate(1, curDate.getDayOfYear());
        }
        else if (year + yearsAgo == curYear){
            dayOfYear = numberGenerator.generate(curDateOfYear, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        }
        else {
            dayOfYear = numberGenerator.generate(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        }
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return LocalDate.of(year,
                gc.get(Calendar.MONTH) + 1,
                gc.get(Calendar.DAY_OF_MONTH));
    }
}
