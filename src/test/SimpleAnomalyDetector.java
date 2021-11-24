package test;

import java.util.LinkedList;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

    List<CorrelatedFeatures> correlatedFeatures = new LinkedList<CorrelatedFeatures>();

    //Algorithm to find correlation between columns
    @Override
    public void learnNormal(TimeSeries ts) {
        float[][] data = ts.getDataMatrix();
        double threshold = ts.threshold;

        for (int j = 0; j < data.length; j++) {
            float m = 0, c = -1;
            for (int i = j + 1; i < data[j].length; i++) {
                float p = Math.abs(StatLib.pearson(ts.getMatrixColumn(j), ts.getMatrixColumn(i)));
                if (p > m) {
                    m = p;
                    c = i;
                }
            }

            if (c != -1 && m * 1.1 > threshold) {
                Point [] points = getPointsFromCorrelatedColumns(ts.getMatrixColumn(j), ts.getMatrixColumn((int) c));
                Line line = StatLib.linear_reg(points);


                correlatedFeatures.add(new CorrelatedFeatures(
                        ts.getCriteriaTitles(j),
                        ts.getCriteriaTitles((int) c),
                        m,
                        line,
                        (float) getMaxDeviation(line,points))
                );
            }
        }
    }

    private Object getMaxDeviation(Line line,Point[] points) {
        float res = 0;
        for (int i = 0; i < points.length  ; i++) {
            float deviation = StatLib.dev(points[i],line);
            if(deviation > res){
                res = deviation;
            }

        }
        return res;
    }

    private Point[] getPointsFromCorrelatedColumns(float[] x, float[] y) {
        Point[] res = new Point[x.length];
        Point tempPoint;

        for (int i = 0; i < x.length; i++) {
            tempPoint = new Point(x[i], y[i]);
            res[i] = tempPoint;
        }

        return res;
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }

    public List<CorrelatedFeatures> getNormalModel() {
        return correlatedFeatures;
    }
}
