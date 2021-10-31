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

        return 0;
    }

    // returns the Pearson correlation coefficient of X and Y
    public static float pearson(float[] x, float[] y) {
        return 0;
    }

    // performs a linear regression and returns the line equation
    public static Line linear_reg(Point[] points) {
        return null;
    }

    // returns the deviation between point p and the line equation of the points
    public static float dev(Point p, Point[] points) {
        return 0;
    }

    // returns the deviation between point p and the line
    public static float dev(Point p, Line l) {
        return 0;
    }
}
