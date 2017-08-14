package com.forecast.testplugin;

import com.forecast.lib.data.DataStructure;
import com.forecast.lib.graph.DataAlias;
import com.forecast.lib.graph.DataAliasStub;
import com.forecast.lib.graph.DataPoint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheltonz on 4/4/2017.
 */
public class CSVDataStructure extends DataStructure {

    public CSVDataStructure() {
        super("CSV", "*.csv", "*.txt");
    }

    @Override
    public List<DataPoint> importData(File file, final DataAlias xAlias, final DataAlias yAlias) {
        final List<DataPoint> dataPoints = new ArrayList<>();
        try {
            final List<String> tempList = Util.readFileAsDocument(file);
            for (final String s : tempList) {
                final String[] split = s.split(",");
                final double posX = xAlias.convertValue(split[0].trim());
                final double posY = yAlias.convertValue(split[1].trim());
                dataPoints.add(new DataPoint(new DataAliasStub(posX, split[0].trim()), new DataAliasStub(posY, split[1].trim())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }
}
