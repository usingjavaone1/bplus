package integration;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficients;
    private int size;

    /**
     * Khởi tạo đa thức với dung lượng mặc định
     */
    public MyArrayPolynomial() {
        this.coefficients = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return coefficients[index];
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[size];
        System.arraycopy(coefficients, 0, result, 0, size);
        return result;
    }

    @Override
    public MyArrayPolynomial append(double coefficient) {
        if (size == coefficients.length) {
            allocateMore();
        }
        coefficients[size++] = coefficient;
        return this;
    }

    @Override
    public MyArrayPolynomial add(double coefficient, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (size == coefficients.length) {
            allocateMore();
        }

        // Dịch các phần tử sang phải để chèn
        System.arraycopy(coefficients, index, coefficients, index + 1, size - index);
        coefficients[index] = coefficient;
        size++;
        return this;
    }

    @Override
    public MyArrayPolynomial set(double coefficient, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        coefficients[index] = coefficient;
        return this;
    }

    @Override
    public int degree() {
        return size - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial derivative() {
        if (size == 0) {
            return new MyArrayPolynomial();
        }

        MyArrayPolynomial derivative = new MyArrayPolynomial();
        for (int i = 1; i < size; i++) {
            derivative.append(coefficients[i] * i);
        }
        return derivative;
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxDegree = Math.max(this.degree(), right.degree());

        for (int i = 0; i <= maxDegree; i++) {
            double coeff = 0;
            if (i <= this.degree()) coeff += this.coefficient(i);
            if (i <= right.degree()) coeff += right.coefficient(i);
            result.append(coeff);
        }

        return result;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxDegree = Math.max(this.degree(), right.degree());

        for (int i = 0; i <= maxDegree; i++) {
            double coeff = 0;
            if (i <= this.degree()) coeff += this.coefficient(i);
            if (i <= right.degree()) coeff -= right.coefficient(i);
            result.append(coeff);
        }

        return result;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int resultDegree = this.degree() + right.degree();

        for (int i = 0; i <= resultDegree; i++) {
            double coeff = 0;
            for (int j = 0; j <= i; j++) {
                if (j <= this.degree() && (i - j) <= right.degree()) {
                    coeff += this.coefficient(j) * right.coefficient(i - j);
                }
            }
            result.append(coeff);
        }

        return result;
    }

    /**
     * Tăng gấp đôi kích thước mảng hệ số
     */
    private void allocateMore() {
        double[] newCoefficients = new double[coefficients.length * 2];
        System.arraycopy(coefficients, 0, newCoefficients, 0, size);
        coefficients = newCoefficients;
    }
}