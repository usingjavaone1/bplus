package integration;

public class TrapezoidRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public TrapezoidRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        if (lower >= upper) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound");
        }

        int n = 1; // Bắt đầu với 1 khoảng
        double previousResult = integrate(polynomial, lower, upper, n);
        double currentResult = 0;
        int iterations = 0;

        do {
            n *= 2; // Tăng gấp đôi số khoảng
            currentResult = integrate(polynomial, lower, upper, n);

            // Kiểm tra độ chính xác (sử dụng ước lượng sai số phương pháp hình thang)
            if (Math.abs(currentResult - previousResult) / 3 < precision) {
                break;
            }

            previousResult = currentResult;
            iterations++;
        } while (iterations < maxIterations);

        return currentResult;
    }

    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        if (numOfSubIntervals <= 0) {
            throw new IllegalArgumentException("Number of sub-intervals must be positive");
        }

        double h = (upper - lower) / numOfSubIntervals;
        double sum = 0.5 * (polynomial.evaluate(lower) + polynomial.evaluate(upper));

        // Tính tổng các giá trị hàm số tại các điểm bên trong
        for (int i = 1; i < numOfSubIntervals; i++) {
            double x = lower + i * h;
            sum += polynomial.evaluate(x);
        }

        return sum * h;
    }
}