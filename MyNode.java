package statistics;

public class MyNode {
    public double data;
    public MyNode next;
    public MyNode previous;

    public MyNode(double data) {
        /* TODO */
        this.data = data;
    }

    public MyNode(double data, MyNode next, MyNode previous) {
        /* TODO */
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public MyNode getPrevious() {
        return previous;
    }

    public void setPrevious(MyNode previous) {
        this.previous = previous;
    }
}
