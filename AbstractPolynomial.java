package hus.oop.midterm.polynomial;

public abstract class AbstractPolynomial implements Polynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        /* TODO */
        StringBuilder str = new StringBuilder(Double.toString(coefficients()[0]));
        for (int i = 1; i < getSize(); i++) {
            str.append(" + ").append(
                    Double.toString(coefficients()[i])).append(i == 1 ? "x" : "x^" + i);
        }
        return str.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        /* TODO */
        double[] newCoefficients = new double[coefficients().length - 1];
        for (int i = 1; i < coefficients().length; i++) {
            newCoefficients[i - 1] = coefficients()[i] * i;
        }
        return newCoefficients;
    }
}
