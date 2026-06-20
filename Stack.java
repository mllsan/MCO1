import java.util.*;

public class Stack {
    private ArrayList<String> chars;
    private int size;
    private String top;

    public Stack() {
        chars = new ArrayList<String>();
        this.size = 0;
        this.top = null;
    }

    public boolean isEmpty(){
        return chars.size() == 0;
    }

    public void push(String elem) {
        chars.add(0, elem);
        this.size = chars.size();
        this.top = chars.get(0);
    }

    public String pop() {
        String popped = null;
        if(chars.isEmpty() == false) {
            popped = chars.get(0);
            chars.remove(0);
            if (!chars.isEmpty()) {
            this.top = chars.get(0);
            } 
            else {
                this.top = null;
            }
            this.size = chars.size();
        }
        return popped;
    }

    public String top() {
        String top = null;

        if(chars.isEmpty() == false)
            top = chars.get(0);

        return top;
    }

    public int getSize() {
        return this.size;
    }


}
