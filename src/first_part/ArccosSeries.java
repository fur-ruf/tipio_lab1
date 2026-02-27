package first_part;

public class ArccosSeries {
    private static final double EPS = 1e-8;

    public static double arccos(double x) {
        double term, sum;

        if (x < -1 || x > 1) {
            return Double.NaN;
        }

        term = x;
        sum = term;

        int n = 0;

        while (Math.abs(term) > EPS) {

            term *= ((2 * n + 1) * (2 * n + 1) * x * x)
                    / ((2 * n + 2) * (2 * n + 3));

            sum += term;
            n++;
        }

        return Math.PI / 2 - sum;
    }
//
//    public static void main(String[] args) {
//        double x = -1;
//        System.out.println("Series: " + arccos(x));
//        System.out.println("Math:   " + Math.acos(x));
//    }
}