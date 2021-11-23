package test;

import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

	//Algorithm to find correlation between columns
    @Override
    public void learnNormal(TimeSeries ts) {
        float[][] data = ts.getDataMatrix();

        for (int j = 0; j < data.length; j++) {
            float m = 0, c = -1;
            for (int i = j + 1; i < data[j].length; i++) {
                float p = Math.abs(StatLib.pearson(ts.getMatrixColumn(j), ts.getMatrixColumn(i)));
                if (p > m) {
					m = p;
                    c = i;
                }
            }

            if(c != -1){
                //Do something with correlated columns
            }
        }

    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }

    public List<CorrelatedFeatures> getNormalModel() {
        return null;
    }
}
