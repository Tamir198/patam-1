package test;

import java.util.LinkedList;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

    private List<CorrelatedFeatures> correlatedFeatures = new LinkedList<CorrelatedFeatures>();


    public List<CorrelatedFeatures> getCorrelatedFeatures() {
        return correlatedFeatures;
    }

    //Algorithm to find correlation between columns
    @Override
    public void learnNormal(TimeSeries ts) {
        try {
            ts.readCsvFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Point[] points = getPointsFromCorrelatedColumns(ts.getMatrixColumn(j), ts.getMatrixColumn((int) c));
                Line line = StatLib.linear_reg(points);


                correlatedFeatures.add(new CorrelatedFeatures(
                        ts.getCriteriaTitles(j),
                        ts.getCriteriaTitles((int) c),
                        m,
                        line,
                        (float) getMaxDeviation(line, points))
                );
            }
        }
    }

    private Object getMaxDeviation(Line line, Point[] points) {
        float res = 0;
        for (int i = 0; i < points.length; i++) {
            float deviation = StatLib.dev(points[i], line);
            if (deviation > res) {
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
        List<AnomalyReport> report = new LinkedList<AnomalyReport>();
        Line line;
        float[][] data = new float[0][];

        try {
            ts.getDataMatrix();
            ts.readCsvFile();
            data = ts.getDataMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ts.numOfRows; i++) {
            for (CorrelatedFeatures cr : correlatedFeatures) {

                Point tempPoint = new Point(data[i][ts.getColumnIndexOfCriteria(cr.feature1)],
                        data[i][ts.getColumnIndexOfCriteria(cr.feature2)]);

                if (StatLib.dev(tempPoint, cr.lin_reg) > cr.threshold * 1.1) {
//                    System.out.println(tempPoint.x + "-" +   tempPoint.y);
                    report.add(new AnomalyReport(cr.feature1 + "-" + cr.feature2,
                            i+1
                    ));
                }
            }
        }
        return report;
    }

    public List<CorrelatedFeatures> getNormalModel() {
        return correlatedFeatures;
    }
}
