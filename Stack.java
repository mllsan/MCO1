import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> elements;

    public Stack() {
        elements = new ArrayList<T>();
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public void push(T elem) {
        elements.add(elem);
    }

    public T pop() {
        T popped = null;

        if(!chars.isEmpty())
            popped = elements.remove(elements.size() - 1);

        return popped;
    }

    public T top() {
        T top = null;

        if(!chars.isEmpty())
            top = elements.get(elements.size() - 1);

        return top;
    }

    public int getSize() {
        return elements.size();
    }
}