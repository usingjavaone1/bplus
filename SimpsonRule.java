package integration;

public class SimpsonRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public SimpsonRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        if (lower >= upper) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound");
        }

        int n = 2; // Bắt đầu với 2 khoảng
        double previousResult = integrate(polynomial, lower, upper, n);
        double currentResult;
        int iterations = 0;

        do {
            n *= 2;
            currentResult = integrate(polynomial, lower, upper, n);

            // Kiểm tra độ chính xác
            if (Math.abs(currentResult - previousResult) / 15 < precision) {
                break;
            }

            previousResult = currentResult;
            iterations++;
        } while (iterations < maxIterations);

        return currentResult;
    }

    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        if (numOfSubIntervals % 2 != 0) {
            throw new IllegalArgumentException("Number of sub-intervals must be even");
        }

        double h = (upper - lower) / numOfSubIntervals;
        double sum = polynomial.evaluate(lower) + polynomial.evaluate(upper);

        // Tính tổng các hệ số
        for (int i = 1; i < numOfSubIntervals; i++) {
            double x = lower + i * h;
            if (i % 2 == 0) {
                sum += 2 * polynomial.evaluate(x);
            } else {
                sum += 4 * polynomial.evaluate(x);
            }
        }

        return sum * h / 3;
    }
}