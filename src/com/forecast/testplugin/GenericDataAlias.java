package com.forecast.testplugin;

import com.forecast.lib.graph.DataAlias;

/**
 * Created by sheltonz on 4/5/2017.
 */
public class GenericDataAlias extends DataAlias {

    public GenericDataAlias() {
        super("Generic");
    }

    @Override
    public String convertData(double data) {
        return String.format("%f", data);
    }

    @Override
    public double convertValue(String value) {
        return Double.parseDouble(value);
    }

}
