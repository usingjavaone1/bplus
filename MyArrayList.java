package statistics;

public class MyArrayList extends MyAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo danh sách với dung lượng mặc định
     */
    public MyArrayList() {
        this.data = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object get(int index) {
        return data[index];
    }

    @Override
    public void add(double data) {
        if (size == this.data.length) {
            allocateMore();
        }
        this.data[size++] = data;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (size == this.data.length) {
            allocateMore();
        }

        // Dịch các phần tử sang phải để chèn
        System.arraycopy(this.data, index, this.data, index + 1, size - index);
        this.data[index] = data;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        // Dịch các phần tử sang trái để xóa
        System.arraycopy(this.data, index + 1, this.data, index, size - index - 1);
        size--;
    }

    @Override
    public MyArrayList sortIncreasing() {
        // Sử dụng Arrays.sort để sắp xếp
        java.util.Arrays.sort(this.data, 0, size);
        return this;
    }

    @Override
    public int binarySearch(double data1) {
        // Sử dụng Arrays.binarySearch
        this.sortIncreasing();

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // Tránh tràn số

            if (data[mid] == data1) {
                return mid;  // Tìm thấy
            } else if (data[mid] < data1) {
                left = mid + 1;  // Tìm bên phải
            } else {
                right = mid - 1;  // Tìm bên trái
            }
        }

        return -1;
    }

    @Override
    public MyIterator iterator(int start) {
        return new MyArrayListIterator(start);
    }

    /**
     * Tăng gấp đôi dung lượng mảng khi cần thiết
     */
    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private class MyArrayListIterator implements MyIterator {
        private int currentPosition;
        private boolean canRemove;

        public MyArrayListIterator(int position) {
            if (position < 0 || position > size) {
                throw new IndexOutOfBoundsException("Invalid position: " + position);
            }
            this.currentPosition = position;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            canRemove = true;
            return data[currentPosition++];
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("remove() can only be called once per next()");
            }
            MyArrayList.this.remove(--currentPosition);
            canRemove = false;
        }
    }
}