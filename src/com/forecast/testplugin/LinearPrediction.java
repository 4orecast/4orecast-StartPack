package com.forecast.testplugin;

import com.forecast.lib.graph.DataAlias;
import com.forecast.lib.graph.DataAliasStub;
import com.forecast.lib.graph.DataPoint;
import com.forecast.lib.prediction.Prediction;

import java.util.List;

/**
 * Created by sheltonz on 4/10/2017.
 */
public class LinearPrediction extends Prediction {
    public LinearPrediction() {
        super("Linear");
    }

    private double generateSlope(final List<DataPoint> dataPoints) {
        double totalSlope = 0;
        for (final DataPoint p : dataPoints) {
            totalSlope += p.getPosY().getData() / p.getPosX().getData();
        }
        return totalSlope / dataPoints.size();
    }

    private double calculateYIntercept(final DataPoint dataPoint, final double slope) {
        return dataPoint.getPosY().getData() + (slope * dataPoint.getPosX().getData());
    }

    @Override
    protected DataPoint predictPosX(DataPoint dataPoint, DataAlias alias, List<DataPoint> dataPoints) {
        final DataPoint firstPoint = dataPoints.get(0);
        final double slope = generateSlope(dataPoints);
        final double data = (dataPoint.getPosY().getData() - calculateYIntercept(firstPoint, slope)) / slope;
        final DataAliasStub stub = new DataAliasStub(data, alias.convertData(data));
        return new DataPoint(stub, dataPoint.getPosY());

    }

    @Override
    protected DataPoint predictPosY(DataPoint dataPoint, DataAlias alias, List<DataPoint> dataPoints) {
        final DataPoint firstPoint = dataPoints.get(0);
        final double slope = generateSlope(dataPoints);
        final double data = slope * dataPoint.getPosX().getData() + calculateYIntercept(firstPoint, slope);
        final DataAliasStub stub = new DataAliasStub(data, alias.convertData(data));
        return new DataPoint(dataPoint.getPosX(), stub);
    }
}
