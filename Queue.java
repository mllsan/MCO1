import java.util.*;

public class Queue {
    private LinkedList<String> chars;
    private String head;
    private String tail;
    private int size;

    public Queue() {
        chars = new LinkedList<String>();
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return chars.size() == 0;
    }

    public void enqueue(String elem) {
        if (chars.isEmpty() == true) {
            chars.addFirst(elem);
            this.head = elem;
            this.tail = elem;
            this.size = chars.size();
        }
        else {
            chars.addLast(elem);
            this.tail = elem;
            this.size = chars.size();
        }

    }

    public String dequeue() {
        String dq = null;
        if (chars.isEmpty() == false) {
            dq = chars.getFirst();
            chars.removeFirst();
            if (!chars.isEmpty()) {
                this.head = chars.getFirst();
            } 
            else {
                this.head = null;
                this.tail = null;
            }
            this.size = chars.size();
        }
        return dq;
    }

    public String getHead() {
        String h = null;

        if (chars.isEmpty() == false)
            h = this.head;

        return h;
    }

    public String getTail() {
        String t = null;

        if (chars.isEmpty() == false)
            t = this.tail;

        return t;
    }

    public int getSize() {
        return chars.size();
    }
}