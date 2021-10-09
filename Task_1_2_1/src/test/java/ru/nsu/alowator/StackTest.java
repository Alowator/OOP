package ru.nsu.alowator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


class StackTest {

    class CustomClass{};

    Stack<Integer> integerStack;

    final int bigTestArraySize = 1000000;

    @BeforeEach
    void stack_init() {
        integerStack = new Stack<>();
    }


    @Test
    void push_pop_integer() {
        integerStack.push(8);
        integerStack.push(0);
        integerStack.push(-15);

        Assertions.assertEquals(-15, integerStack.pop());
        Assertions.assertEquals(0, integerStack.pop());
        Assertions.assertEquals(8, integerStack.pop());
    }

    @Test
    void push_pop_bigInteger() {
        Integer[] arr = new Integer[bigTestArraySize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
            integerStack.push(arr[i]);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            Assertions.assertEquals(arr[i], integerStack.pop());
        }

    }

    @Test
    void push_pop_customType() {
        Stack<CustomClass> stack = new Stack<>();
        CustomClass customClass = new CustomClass();
        stack.push(customClass);

        Assertions.assertEquals(customClass, stack.pop());
    }

    @Test
    void pop_exception() {
        integerStack.push(1);
        integerStack.push(2);
        integerStack.pop();
        integerStack.pop();
        Assertions.assertThrows(NoSuchElementException.class, () -> {integerStack.pop();});
    }


    @Test
    void count_bigInteger() {
        final int bigTestArraySize = 1000000;
        Integer[] arr = new Integer[bigTestArraySize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
            integerStack.push(arr[i]);
            Assertions.assertEquals(i + 1, integerStack.count());
        }
    }


    @Test
    void pushStack_integer() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        integerStack.pushStack(stack);

        Assertions.assertEquals(3, integerStack.pop());
        Assertions.assertEquals(2, integerStack.pop());
        Assertions.assertEquals(1, integerStack.pop());
    }

    @Test
    void pushStack_empty() {
        Stack<Integer> stack = new Stack<>();
        integerStack.pushStack(stack);

        Assertions.assertEquals(0, integerStack.count());
    }

    @Test
    void pushStack_customType() {
        Stack<CustomClass> stack1 = new Stack<>();
        Stack<CustomClass> stack2 = new Stack<>();
        CustomClass customClass = new CustomClass();
        stack2.push(customClass);

        stack1.pushStack(stack2);

        Assertions.assertEquals(customClass, stack1.pop());
    }


    @Test
    void popStack_integer() {
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);

        Stack<Integer> stack = integerStack.popStack(3);

        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertEquals(0, integerStack.count());
    }

    @Test
    void popStack_empty() {
        Stack<Integer> stack = integerStack.popStack(0);
        Assertions.assertEquals(0, stack.count());
    }

    @Test
    void popStack_customType() {
        Stack<CustomClass> stack1 = new Stack<>();
        CustomClass customClass = new CustomClass();
        stack1.push(customClass);

        Stack<CustomClass> stack2 = stack1.popStack(1);

        Assertions.assertEquals(customClass, stack2.pop());
    }

    @Test
    void popStack_exception() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {integerStack.popStack(-1);});
    }


    @Test
    void foreach_integer() {
        integerStack.push(8);
        integerStack.push(0);
        integerStack.push(-15);

        Integer[] expected = {8, 0, -15};
        List<Integer> actual = new ArrayList<>();

        for (Integer x : integerStack) {
            actual.add(x);
        }

        Assertions.assertArrayEquals(expected, actual.toArray());
    }

}