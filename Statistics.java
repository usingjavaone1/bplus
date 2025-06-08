package statistics;

public class Statistics {
    private MyList data;

    /**
     * Khởi tạo dữ liệu cho BasicStatistic.
     */
    public Statistics(MyList data) {
        /* TODO */
        this.data = data;
    }

    /**
     * Lấy giá trị lớn nhất trong list.
     * @return giá trị lớn nhất.
     */
    public double max() {
        if (data == null || data.size() == 0) {
            throw new IllegalStateException("Dataset is empty or null");
        }

        MyIterator iterator = data.iterator(0);
        double max = Double.NEGATIVE_INFINITY;

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Number) {
                double value = ((Number) obj).doubleValue();
                if (value > max) {
                    max = value;
                }
            }
        }

        return max;
    }

    /**
     * Lấy giá trị nhỏ nhất trong list.
     * @return giá trị nhỏ nhất.
     */

    public double min() {
        if (data == null || data.size() == 0) {
            throw new IllegalStateException("Dataset is empty or null");
        }

        MyIterator iterator = data.iterator(0);
        double min = Double.POSITIVE_INFINITY;

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Number) {
                double value = ((Number) obj).doubleValue();
                if (value < min) {
                    min = value;
                }
            }
        }

        return min;
    }

    /**
     * Tính kỳ vọng của mẫu lưu trong list.
     * @return kỳ vọng.
     */

    public double mean() {
        if (data == null || data.size() == 0) {
            throw new IllegalStateException("Dataset is empty or null");
        }

        MyIterator iterator = data.iterator(0);
        double sum = 0;
        int count = 0;

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Number) {
                sum += ((Number) obj).doubleValue();
                count++;
            }
        }

        if (count == 0) {
            throw new IllegalStateException("No numeric values in dataset");
        }

        return sum / count;
    }

    /**
     * Tính phương sai của mẫu lưu trong list.
     * @return phương sai.
     */
    public double variance() {
        double mean = mean();
        MyIterator iterator = data.iterator(0);
        double sum = 0;
        int count = 0;

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Number) {
                double value = ((Number) obj).doubleValue();
                sum += Math.pow(value - mean, 2);
                count++;
            }
        }

        if (count == 0) {
            throw new IllegalStateException("No numeric values in dataset");
        }

        return sum / count;
    }

    /**
     * Tìm kiếm trong list có phẩn tử nào có giá trị bằng data không, sử dụng binarySearch trong list.
     * Trả về index một phần tử có giá trị bằng data, nếu không tìm thấy thì trả về -1.
     * @return
     */
    public int search(double data1) {
        /* TODO */
        if (data == null || data.size() == 0) {
            return -1;
        }

        // Sử dụng phương thức binarySearch có sẵn của MyList
        int result = data.sortIncreasing().binarySearch(data1);

        // Kiểm tra kết quả trả về
        return result >= 0 ? result : -1;
    }

    /**
     * Tính rank của các phần tử trong list.
     * @return rank của các phần tử trong list
     */
    public double[] rank() {
        /* TODO */
        if (data == null || data.size() == 0) {
            return new double[0];
        }

        // Tạo danh sách đã sắp xếp
        MyList sortedList = data.sortIncreasing();

        // Tạo mảng lưu giá trị và index gốc
        double[] ranks = new double[data.size()];
        java.util.Arrays.fill(ranks, Double.NaN);

        // Duyệt qua list gốc để tính rank
        MyIterator iterator = data.iterator(0);
        int originalIndex = 0;
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Number) {
                double value = ((Number)obj).doubleValue();
                // Tìm vị trí trong danh sách đã sắp xếp
                int pos = sortedList.binarySearch(value);
                if (pos >= 0) {
                    // Tính số lượng phần tử có cùng giá trị
                    int count = 1;
                    int left = pos - 1;
                    while (left >= 0 && sortedList.get(left).equals(obj)) {
                        count++;
                        left--;
                    }
                    int right = pos + 1;
                    while (right < sortedList.size() && sortedList.get(right).equals(obj)) {
                        count++;
                        right++;
                    }
                    // Tính rank trung bình
                    ranks[originalIndex] = (left + right + 1) / 2.0;
                }
            }
            originalIndex++;
        }

        return ranks;
    }
}
