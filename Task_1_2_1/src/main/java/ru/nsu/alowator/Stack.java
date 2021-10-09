package ru.nsu.alowator;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    private int count = 0;
    private T[] arr;

    final private int INITIAL_ARR_SIZE = 16;
    final private int ARR_MULTIPLIER = 2;

    @SuppressWarnings("unchecked")
    public Stack() {
        arr = (T[]) new Object[INITIAL_ARR_SIZE];

    }

    public int count() {
        return count;
    }

    public void push(T x) {
        resize(count + 1);
        arr[count] = x;
        count += 1;
    }

    public T pop() {
        if (count <= 0) {
            throw new NoSuchElementException();
        }
        count -= 1;
        return arr[count];
    }

    public void pushStack(Stack<T> stack) {
        this.resize(this.count  + stack.count);
        System.arraycopy(stack.arr, 0, this.arr, this.count, stack.count);
        this.count += stack.count;
    }

    public Stack<T> popStack(int size) {
        if (size > count || size < 0) {
            throw new NoSuchElementException();
        }

        Stack<T> stack = new Stack<>();
        stack.resize(size);
        System.arraycopy(this.arr, 0, stack.arr, 0, size);
        stack.count = size;
        this.count -= size;
        return stack;
    }

    private void resize(int toSize)  {
        while (arr.length < toSize) {
            arr = Arrays.copyOf(arr, arr.length * ARR_MULTIPLIER);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public T next() {
            cursor += 1;
            return arr[cursor - 1];
        }
    }

}
