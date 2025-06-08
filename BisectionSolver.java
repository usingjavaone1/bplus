package hus.oop.midterm.polynomial;

public class BisectionSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public BisectionSolver(double tolerance, int maxIterations) {
        /* TODO */
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    /**
     * Tìm nghiệm của đa thức theo phương pháp chia đôi (Bisection)
     * @param polynomial
     * @param lower
     * @param upper
     * @return
     */
    @Override
    public double solve(Polynomial polynomial, double lower, double upper) {
        /* TODO */
        double mid = 0;
        int count = 0;
        while (Math.abs(upper - lower) > tolerance && count < maxIterations) {
            mid = (upper + lower) / 2;
            double fa = polynomial.evaluate(lower);
            double fmid = polynomial.evaluate(mid);
            if (fa * fmid < 0) {
                upper = mid;
            } else if (fa * fmid > 0) {
                lower = mid;
            } else {
                break;
            }
            count++;
        }
        return mid;
    }
}
