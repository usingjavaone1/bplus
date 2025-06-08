package integration;

import java.util.Random;

public class TestIntegrationCalculator {
    private MyPolynomial polynomial;
    private static final Random random = new Random();

    public TestIntegrationCalculator(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public static void main(String[] args) {
        TestIntegrationCalculator tester = new TestIntegrationCalculator(null);

        System.out.println("========== TEST ARRAY POLYNOMIAL ==========");
        tester.testArrayPolynomial();

        System.out.println("\n========== TEST LIST POLYNOMIAL ==========");
        tester.testListPolynomial();
    }

    public void testArrayPolynomial() {
        // Tạo đa thức ngẫu nhiên
        int size = 3 + random.nextInt(5); // Từ 3 đến 7 hệ số
        MyArrayPolynomial poly = new MyArrayPolynomial();

        System.out.println("Tạo đa thức với " + size + " hệ số ngẫu nhiên:");
        for (int i = 0; i < size; i++) {
            double coeff = -5 + 10 * random.nextDouble(); // Hệ số từ -5 đến 5
            poly.append(coeff);
            System.out.printf("Hệ số bậc %d: %.2f\n", i, coeff);
        }

        // Test các chức năng cơ bản
        System.out.println("\nĐa thức ban đầu: " + poly);

        // Thêm hệ số
        double newCoeff = -2 + 4 * random.nextDouble();
        poly.add(newCoeff, 1);
        System.out.println("Sau khi thêm hệ số " + newCoeff + " vào vị trí 1: " + poly);

        // Sửa hệ số
        poly.set(1.5, 0);
        System.out.println("Sau khi đặt hệ số bậc 0 thành 1.5: " + poly);

        // Tính giá trị đa thức
        double x = 2.0;
        System.out.printf("Giá trị đa thức tại x = %.1f: %.2f\n", x, poly.evaluate(x));

        // Tạo đa thức thứ 2 để thực hiện các phép toán
        MyArrayPolynomial poly2 = new MyArrayPolynomial();
        for (int i = 0; i < size; i++) {
            poly2.append(-3 + 6 * random.nextDouble());
        }
        System.out.println("\nĐa thức thứ 2: " + poly2);

        // Cộng 2 đa thức
        System.out.println("Tổng 2 đa thức: " + poly.plus(poly2));

        // Trừ 2 đa thức
        System.out.println("Hiệu 2 đa thức: " + poly.minus(poly2));

        // Nhân 2 đa thức
        System.out.println("Tích 2 đa thức: " + poly.multiply(poly2));


        // Tính tích phân bằng cả 3 phương pháp
        double a = 1.0, b = 5.0;
        System.out.println("\nTích phân từ " + a + " đến " + b + ":");

        // Phương pháp hình thang
        MyIntegrator trapezoid = new TrapezoidRule(0.0001, 100);
        double trapezoidResult = trapezoid.integrate(poly, a, b);
        System.out.printf("Phương pháp Hình thang: %.6f\n", trapezoidResult);

        // Phương pháp điểm giữa
        MyIntegrator midpoint = new MidpointRule(0.0001, 100);
        double midpointResult = midpoint.integrate(poly, a, b);
        System.out.printf("Phương pháp Điểm giữa: %.6f\n", midpointResult);

        // Phương pháp Simpson
        MyIntegrator simpson = new SimpsonRule(0.0001, 100);
        double simpsonResult = simpson.integrate(poly, a, b);
        System.out.printf("Phương pháp Simpson:    %.6f\n", simpsonResult);
    }

    public void testListPolynomial() {
        // Tạo đa thức ngẫu nhiên
        int size = 3 + random.nextInt(5); // Từ 3 đến 7 hệ số
        MyListPolynomial poly = new MyListPolynomial();

        System.out.println("Tạo đa thức với " + size + " hệ số ngẫu nhiên:");
        for (int i = 0; i < size; i++) {
            double coeff = -5 + 10 * random.nextDouble(); // Hệ số từ -5 đến 5
            poly.append(coeff);
            System.out.printf("Hệ số bậc %d: %.2f\n", i, coeff);
        }

        // Test các chức năng cơ bản
        System.out.println("\nĐa thức ban đầu: " + poly);

        // Thêm hệ số
        double newCoeff = -2 + 4 * random.nextDouble();
        poly.add(newCoeff, 1);
        System.out.println("Sau khi thêm hệ số " + newCoeff + " vào vị trí 1: " + poly);

        // Sửa hệ số
        poly.set(1.5, 0);
        System.out.println("Sau khi đặt hệ số bậc 0 thành 1.5: " + poly);

        // Tính giá trị đa thức
        double x = 3.0;
        System.out.printf("Giá trị đa thức tại x = %.1f: %.2f\n", x, poly.evaluate(x));

        // Tạo đa thức thứ 2 để thực hiện các phép toán
        MyListPolynomial poly2 = new MyListPolynomial();
        for (int i = 0; i < size; i++) {
            poly2.append(-3 + 6 * random.nextDouble());
        }
        System.out.println("\nĐa thức thứ 2: " + poly2);

        // Cộng 2 đa thức
        System.out.println("Tổng 2 đa thức: " + poly.plus(poly2));

        // Trừ 2 đa thức
        System.out.println("Hiệu 2 đa thức: " + poly.minus(poly2));

        // Nhân 2 đa thức
        System.out.println("Tích 2 đa thức: " + poly.multiply(poly2));

        // Tính tích phân bằng cả 3 phương pháp
        double a = 2.0, b = 6.0;
        System.out.println("\nTích phân từ " + a + " đến " + b + ":");

        // Phương pháp hình thang
        MyIntegrator trapezoid = new TrapezoidRule(0.0001, 100);
        double trapezoidResult = trapezoid.integrate(poly, a, b);
        System.out.printf("Phương pháp Hình thang: %.6f\n", trapezoidResult);

        // Phương pháp điểm giữa
        MyIntegrator midpoint = new MidpointRule(0.0001, 100);
        double midpointResult = midpoint.integrate(poly, a, b);
        System.out.printf("Phương pháp Điểm giữa: %.6f\n", midpointResult);

        // Phương pháp Simpson
        MyIntegrator simpson = new SimpsonRule(0.0001, 100);
        double simpsonResult = simpson.integrate(poly, a, b);
        System.out.printf("Phương pháp Simpson:    %.6f\n", simpsonResult);
    }
}