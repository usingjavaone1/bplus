package matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class MySquareMatrix {
    private int[][] data;

    /**
     * Hàm dựng, khởi tạo một ma trận có các phần tử được sinh ngẫu nhiên trong khoảng [1, 100]
     * @param size
     */
    public MySquareMatrix(int size) {
        initRandom(size);
    }
    public MySquareMatrix(int[][] data) {
        this.data = data;
    }

    /**
     * Phương thức khởi tạo ma trận, các phần tử của ma trận được sinh ngẫu nhiên trong đoạn [10, 90]
     * @param size
     */
    private void initRandom(int size) {
        /* TODO */
        data = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = (int) (1 + Math.random() * 10);
            }
        }

    }

    /**
     * Lấy ra các phần tử trên đường chéo chính của ma trận.
     * @return đường chéo chính của ma trận.
     */
    public int[] principalDiagonal() {
        /* TODO */
        int[] diagonal = new int[data.length];
        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] = data[i][i];
        }
        return diagonal;
    }

    /**
     * Lấy ra các phần tử trên đường chéo phụ của ma trận.
     * @return đường chéo phụ của ma trận.
     */
    public int[] secondaryDiagonal() {
        /* TODO */
        int[] diagonal = new int[data.length];
        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] = data[i][data.length - 1 - i];
        }
        return diagonal;

    }

    /**
     * Phương thức lấy ra các số là số nguyên tố trong ma trận.
     * @return các số nguyên tố trong ma trận.
     */
    public int[] primes() {
        /* TODO */
        ArrayList<Integer> primes = new ArrayList<Integer>();

        for(int i=0; i<data.length; i++){
            for(int j=0; j<data.length; j++){
                if(isPrime(data[i][j])){
                    primes.add(data[i][j]);
                }
            }
        }
        return primes.stream().mapToInt(i->i).toArray();

    }
    private boolean isPrime(int number) {
        if(number <=1){
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Sắp xếp các phần tử của ma trận theo thứ tự giảm dần.
     * @return ma trận có các phần tử là phần tử của ma trận ban đầu được sắp xếp theo thứ tự giảm dần.
     * Các phần tử được sắp xếp theo thứ tự từ trái sang phải ở mỗi hàng, và từ trên xuống dưới.
     */
    public MySquareMatrix getSortedMatrix() {
        /* TODO */
        int n = data.length;
        int totalElements = n * n;
        int[] flat = new int[totalElements];

        // Bước 1: Dồn các phần tử vào mảng 1 chiều
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flat[index++] = data[i][j];
            }
        }

        // Bước 2: Sắp xếp mảng 1 chiều
        Arrays.sort(flat);

        // Bước 3: Đổ ngược lại vào ma trận mới
        int[][] sortedData = new int[n][n];
        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sortedData[i][j] = flat[index++];
            }
        }

        // Bước 4: Trả về một SquareMatrix mới chứa ma trận đã sắp xếp
        return new MySquareMatrix(sortedData);
    }

    /**
     * Lấy giá trị phần tử ở vị trí (row, col).
     * @param row
     * @param col
     * @return
     */
    public int get(int row, int col) {
        /* TODO */
        return data[row][col];
    }

    /**
     * Sửa giá trị phần tử ở vị trí (row, col) thành value.
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, int value) {
        /* TODO */
        data[row][col] = value;
    }

    /**
     * Cộng ma trận hiện tại với một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận tổng của 2 ma trận.
     */
    public MySquareMatrix add(MySquareMatrix that) {
        /* TODO */
        int n = data.length;
        if(that.data.length != n|| that.data[0].length != n){
            throw new IllegalArgumentException("Square matrix should have the same length");
        }
        int[][] newData = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newData[i][j] = data[i][j] + that.data[i][j];
            }
        }
        return new MySquareMatrix(newData);

    }

    /**
     * Trừ ma trận hiện tại cho một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận hiệu của ma trận hiện tại và ma trận truyền vào.
     */
    public MySquareMatrix minus(MySquareMatrix that) {
        /* TODO */
        int n = data.length;
        if(that.data.length != n|| that.data[0].length != n){
            throw new IllegalArgumentException("Square matrix should have the same length");
        }
        int[][] newData = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newData[i][j] = data[i][j] - that.data[i][j];
            }
        }
        return new MySquareMatrix(newData);
    }

    /**
     * Nhân ma trận hiện tại với một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận nhân của ma trận hiện tại với ma trận truyền vào.
     */
    public MySquareMatrix multiply(MySquareMatrix that) {
        /* TODO */
        if (this.data[0].length != that.data.length) {
            throw new IllegalArgumentException("Number of columns in first matrix must equal number of rows in second matrix");
        }
        MySquareMatrix result = new MySquareMatrix(data.length);
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < that.data.length; j++) {
                int sum = 0;
                for (int k = 0; k < this.data.length; k++) {
                    sum += this.data[i][k] * that.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;

    }

    /**
     * Nhân ma trận với một số vô hướng.
     * @param value
     * @return ma trận mới là ma trận hiện tại được nhân với một số vô hướng.
     */
    public MySquareMatrix scaled(int value) {
        /* TODO */
        int[][] newData = new int[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                newData[i][j] = data[i][j] * value;
            }
        }
        return new MySquareMatrix(newData);

    }

    /**
     * Phương thức lấy ma trận chuyển vị.
     * @return ma trận mới là ma trận chuyển vị của ma trận hiện tại.
     */
    public MySquareMatrix transpose() {
        /* TODO */
        int[][] newMatrix = new int[data.length][data.length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length; j++) {
                newMatrix[j][i] = data[i][j];
            }
        }
        return new MySquareMatrix(newMatrix);
    }

    /**
     * Mô tả ma trận theo định dạng biểu diễn ma trận, ví dụ
     *   1 2 3
     *   4 5 6
     *   7 8 9
     * @return một chuỗi mô tả ma trận.
     */
    @Override
    public String toString() {
        /* TODO */
        StringBuilder sb = new StringBuilder();
        for (int[] datum : data) {
            for (int j = 0; j < data.length; j++) {
                sb.append(datum[j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();

    }
}
