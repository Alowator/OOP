package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.ArrayList;
import java.util.List;

public class ThreadNotPrimeFinder extends NotPrimeFinder {
    public static int DEFAULT_THREADS_COUNT = 4;

    private final int threadsCount;

    public ThreadNotPrimeFinder(Integer[] array) {
        super(array);
        this.threadsCount = DEFAULT_THREADS_COUNT;
    }

    public ThreadNotPrimeFinder(Integer[] array, int threadsCount) {
        super(array);
        this.threadsCount = threadsCount;
    }

    @Override
    public Boolean call() {
        Boolean result = null;

        List<Thread> threads = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        
        for (int i = 0; i < threadsCount; i++) {
            int begin = array.length / threadsCount * i;
            int end = array.length / threadsCount * (i + 1);
            if (i == threadsCount - 1)
                end = array.length;

            Task task = new Task(array, begin, end);
            tasks.add(task);

            Thread thread = new Thread(task);
            threads.add(thread);

            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Task task : tasks) {
            if (result == null)
                result = task.getResult();
            else
                result |= task.getResult();
        }
        
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "{threadsCount=" + threadsCount + '}';
    }

    private static class Task implements Runnable {
        private final Integer[] array;
        private final int begin;
        private final int end;
        private Boolean result = null;

        public Task(Integer[] array, int begin, int end) {
            this.array = array;
            this.begin = begin;
            this.end = end;
        }

        public void run() {
            for (int i = begin; i < end; i++) {
                if (!Calculus.isPrime(array[i])) {
                    result = true;
                    return;
                }
            }
            result = false;
        }

        public Boolean getResult() {
            return result;
        }
    }

}
