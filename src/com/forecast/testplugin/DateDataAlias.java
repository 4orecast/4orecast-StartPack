package com.forecast.testplugin;

import com.forecast.lib.graph.DataAlias;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sheltonz on 4/6/2017.
 */
public class DateDataAlias extends DataAlias {
    private final List<SimpleDateFormat> dateFormats = new ArrayList<>();

    private SimpleDateFormat lastFormat = null;

    public DateDataAlias() {
        super("Date");
        dateFormats.add(new SimpleDateFormat("dd-MMM-yy"));
        dateFormats.add(new SimpleDateFormat("dd-MM-yy"));
        dateFormats.add(new SimpleDateFormat("dd-MM-yyyy"));
        dateFormats.add(new SimpleDateFormat("dd-MMM-yyyy"));
        dateFormats.add(new SimpleDateFormat("dd/MMM/yy"));
        dateFormats.add(new SimpleDateFormat("dd/MM/yy"));
        dateFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
        dateFormats.add(new SimpleDateFormat("dd/MMM/yyyy"));
    }

    @Override
    public String convertData(double data) {
        final Date date = new Date((long) data);
        if (lastFormat == null) {
            return dateFormats.get(0).format(date);
        }
        return lastFormat.format(date);
    }

    @Override
    public double convertValue(String value) {
        for (final SimpleDateFormat f : dateFormats) {
            try {
                lastFormat = f;
                return f.parse(value).getTime();
            } catch (final Exception e) {
//                e.printStackTrace();
            }
        }
        return Double.MIN_VALUE;
    }
}
