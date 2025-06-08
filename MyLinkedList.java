package statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;  // Node đầu tiên trong danh sách

    /**
     * Khởi tạo danh sách liên kết rỗng
     */
    public MyLinkedList() {
        this.top = null;
    }

    @Override
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public void add(double data) {
        insert(data, size());
    }

    @Override
    public Object get(int index) {
        /* TODO */
        return getNodeByIndex(index).getData();

    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        MyNode newNode = new MyNode(data);
        if (index == 0) {
            newNode.setNext(top);
            top = newNode;
        } else {
            MyNode previous = getNodeByIndex(index - 1);
            newNode.setNext(previous.getNext());
            previous.setNext(newNode);
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            top = top.getNext();
        } else {
            MyNode previous = getNodeByIndex(index - 1);
            previous.setNext(previous.getNext().getNext());
        }
    }

    @Override
    public MyLinkedList sortIncreasing() {
        // Sử dụng bubble sort
        boolean swapped;
        do {
            swapped = false;
            MyNode current = top;
            MyNode previous = null;

            while (current != null && current.getNext() != null) {
                if (current.getData() > current.getNext().getData()) {
                    // Hoán đổi dữ liệu
                    double temp = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(temp);
                    swapped = true;
                }
                previous = current;
                current = current.getNext();
            }
        } while (swapped);

        return this;
    }

    @Override
    public int binarySearch(double data) {
        // Đảm bảo danh sách đã được sắp xếp
        this.sortIncreasing();

        int left = 0;
        int right = size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            double midValue = getNodeByIndex(mid).getData();

            if (midValue == data) {
                return mid;
            } else if (midValue < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    @Override
    public MyIterator iterator(int start) {
        return new MyLinkedListIterator(start);
    }

    /**
     * Lấy node ở vị trí index
     * @param index vị trí cần lấy
     * @return node tại vị trí index
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private class MyLinkedListIterator implements MyIterator {
        private int currentPosition;

        public MyLinkedListIterator(int position) {
            if (position < 0 || position > size()) {
                throw new IndexOutOfBoundsException("Invalid position: " + position);
            }
            this.currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            double data = getNodeByIndex(currentPosition).getData();
            currentPosition++;
            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }
}