package tqs_96145;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private LinkedList<T> stack;
    private int max;

    public TqsStack() {
        this.stack = new LinkedList<T>();
        this.max = -1;
    }

    public void bound_stack(int max) {
        this.max = max;
    }

    public void push(T e) {
        if (max > -1) {
            if (stack.size()==max)
            throw new IllegalStateException();
        }
        stack.push(e);
    }
    public T pop() throws NoSuchElementException{
        T e = stack.pop();
        return e;
    }
    public T peek() throws NoSuchElementException{
        T e = stack.element();
        return e;
    }
    public int size() {
        return stack.size();
    }
    public Boolean isEmpty() {
        return stack.isEmpty();
    }
}