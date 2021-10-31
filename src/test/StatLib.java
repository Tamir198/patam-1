package test;

public class StatLib {

    // simple average
    public static float avg(float[] x) {
        float total = 0;

        for (float num : x) {
            total += num;
        }

        return total / x.length;
    }

    // returns the variance of X and Y
    public static float var(float[] x) {
        final double mean = getMean(x, x.length);
        float res = 0;

        for (double num : x)
            res += (num - mean) * (num - mean);

        return res / x.length;
    }

    private static double getMean(float[] data, int size) {
        double sum = 0.0;

        for (double a : data)
            sum += a;
        return sum / size;
    }

    // returns the covariance of X and Y
    public static float cov(float[] x, float[] y) {
        float sum = 0;
        final int n = x.length;

        for (int i = 0; i < n; i++)
            sum += (x[i] - getMean(x, n)) * (y[i] - getMean(y, n));
        return sum / (n);
    }

    // returns the Pearson correlation coefficient of X and Y
    public static float pearson(float[] x, float[] y) {
        return (float) (cov(x, y) / (Math.sqrt(var(x)) * Math.sqrt(var(y))));
    }

    // performs a linear regression and returns the line equation
    public static Line linear_reg(Point[] points) {
        float a, b;
        float[] xArr = new float[points.length];
        float[] yArr = new float[points.length];

        for (int i = 0; i < points.length; i++) {
            xArr[i] = points[i].x;
            yArr[i] = points[i].y;
        }

        a = cov(xArr, yArr) / var(xArr);
        b = avg(yArr) - a * avg(xArr);

        return new Line(a, b);
    }

    // returns the deviation between point p and the line equation of the points
    public static float dev(Point p, Point[] points) {

    }

    // returns the deviation between point p and the line
    public static float dev(Point p, Line l) {
        return 0;
    }
}
