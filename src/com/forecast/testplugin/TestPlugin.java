package com.forecast.testplugin;

import com.forecast.lib.Plugin;
import com.forecast.lib.data.DataStructure;
import com.forecast.lib.graph.DataAlias;
import com.forecast.lib.prediction.Prediction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zachary on 4/3/2017.
 */
public class TestPlugin implements Plugin {

    @Override
    public List<Prediction> predictions() {
        final List<Prediction> predictions = new ArrayList<>();
        predictions.add(new ExponentialPrediction());
        predictions.add(new LinearPrediction());
        return predictions;
    }

    @Override
    public List<DataStructure> dataStructures() {
        final List<DataStructure> dataStructures = new ArrayList<>();
        dataStructures.add(new CSVDataStructure());
        return dataStructures;
    }

    @Override
    public List<DataAlias> dataAliases() {
        final List<DataAlias> aliases = new ArrayList<>();
        aliases.add(new GenericDataAlias());
        aliases.add(new DateDataAlias());
        return aliases;
    }

}
