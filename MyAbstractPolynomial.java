package integration;

public abstract class MyAbstractPolynomial implements MyPolynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        /* TODO */
        StringBuilder result = new StringBuilder();
        result.append('[');
        int count = 0;
        for (double coefficient : coefficients()) {
            if (count == 0) {
                result.append(coefficient);
            } else {
                result.append(" + ").append(coefficient).append("x^").append(count);
            }
            count++;
        }
        result.append(']');
        return result.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        /* TODO */
        double[] coefficients = coefficients();
        double[] result = new double[coefficients.length - 1];
        for (int i = 1; i < coefficients.length; i++) {
            result[i - 1] = coefficients[i] * i;
        }
        return result;
    }
}
