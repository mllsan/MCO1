import java.util.LinkedList;

public class Queue<T> {
    private LinkedList<T> elements;

    public Queue() {
        elements = new LinkedList<T>();
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public void enqueue(T elem) {
        elements.addLast(elem);
    }

    public T dequeue() {
        T dq = null;

        if (!elements.isEmpty())
            dq = elements.removeFirst();

        return dq;
    }

    public T getHead() {
        T head = null;

        if (!elements.isEmpty())
            head = elements.getFirst();

        return head;
    }

    public String getTail() {
        T tail = null;

        if (!elements.isEmpty())
            tail = elements.getLast();

        return t;
    }

    public int getSize() {
        return elements.size();
    }
}