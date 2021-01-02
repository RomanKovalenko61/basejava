package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrentcy {

    public static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " , " + getState());
                throw new IllegalStateException();
            }

            public void inc() {
                synchronized (this) {
                    counter++;
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " , " + Thread.currentThread().getState()));
        thread2.start();

        System.out.println("State thread1 = " + thread1.getState());

        final MainConcurrentcy mainConcurrentcy = new MainConcurrentcy();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrentcy.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("counter= " + MainConcurrentcy.counter);

//        new MainConcurrentcy().inc();
    }

    public synchronized void inc() {
//      synchronized (MainConcurrentcy.class) {
//        double a = Math.sin(13.);
//        synchronized (this) {
        counter++;
//                wait();
//        }
    }
}
