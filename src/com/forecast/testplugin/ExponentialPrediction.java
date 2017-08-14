package com.forecast.testplugin;

import com.forecast.lib.graph.DataAlias;
import com.forecast.lib.graph.DataAliasStub;
import com.forecast.lib.graph.DataPoint;
import com.forecast.lib.prediction.Prediction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 4/3/2017.
 */
public class ExponentialPrediction extends Prediction {

    private DataPoint lastPoint;

    public ExponentialPrediction() {
        super("Exponential");
    }

    private double calculateSlope(List<DataPoint> dataPoints) {
        this.lastPoint = dataPoints.get(dataPoints.size() - 1);
        final List<Double> tempList = new ArrayList<Double>();
        double lastTime = -1;
        double lastPrice = -1;
        for (final DataPoint p : dataPoints) {
            if (lastTime == -1 && lastPrice == -1) {
                lastTime = p.getPosX().getData();
                lastPrice = p.getPosY().getData();
                continue;
            }
            tempList.add((p.getPosY().getData() - lastPrice) / (p.getPosX().getData() - lastTime));
//            System.out.println(getDays(p.getDate()));
//            System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(p.getDate().getTime()));
//            System.out.println((p.getPrice() - lastPoint) / (getDays(p.getDate()) - lastTime));
            lastTime = p.getPosX().getData();
            lastPrice = p.getPosY().getData();
        }
        double result = 0;
        for (final double d : tempList) {
            result += d;
        }
        return result / tempList.size();
    }

    @Override
    protected DataPoint predictPosX(final DataPoint dataPoint, final DataAlias alias, final List<DataPoint> dataPoints) {
        final double slope = calculateSlope(dataPoints);
        final double data = (((dataPoint.getPosY().getData() - lastPoint.getPosY().getData()) / slope) + lastPoint.getPosX().getData());
        final DataAliasStub stub = new DataAliasStub(data, alias.convertData(data));
        return new DataPoint(stub, dataPoint.getPosY());
    }

    @Override
    protected DataPoint predictPosY(final DataPoint dataPoint, final DataAlias alias, final List<DataPoint> dataPoints) {
        final double slope = calculateSlope(dataPoints);
        final double data = slope * (dataPoint.getPosX().getData() - lastPoint.getPosX().getData()) + lastPoint.getPosY().getData();
        final DataAliasStub stub = new DataAliasStub(data, alias.convertData(data));
        return new DataPoint(dataPoint.getPosX(), stub);
    }
}
