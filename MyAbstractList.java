package statistics;

public abstract class MyAbstractList implements MyList {
    /**
     * Mô tả dữ liệu của list.
     * @return mô tả list theo định dạng [a1 a2 a3 ... an]
     */
    @Override
    public String toString() {
        /* TODO */
        if (size() == 0) {
            return "[]";  // Trường hợp list rỗng
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // Thêm các phần tử cách nhau bằng khoảng trắng
        for (int i = 0; i < size(); i++) {
            sb.append(this.get(i));
            if (i < size() - 1) {
                sb.append(" ");
            }
        }

        sb.append("]");
        return sb.toString();


    }
}
