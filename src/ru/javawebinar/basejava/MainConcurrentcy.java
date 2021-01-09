package ru.javawebinar.basejava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrentcy {

    public static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static final AtomicInteger atomicCounter = new AtomicInteger();
    //    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));


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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//        ExecutorService executorService1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrentcy.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
/*
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrentcy.inc();
                }
                latch.countDown();
            });
            thread.start();
            threads.add(thread);

*/
        }

//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println("counter= " + MainConcurrentcy.counter);
        System.out.println("counter= " + MainConcurrentcy.atomicCounter.get());

        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadlock(lock1, lock2);
        deadlock(lock2, lock1);

//        new MainConcurrentcy().inc();
    }

    private static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  waiting " + lock1);
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " holding " + lock1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting " + lock2);
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " holding " + lock2);
                }
            }
        }).start();
    }

    public void inc() {
//      synchronized (MainConcurrentcy.class) {
//        double a = Math.sin(13.);
        atomicCounter.incrementAndGet();
//        synchronized (this) {
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
//                wait();
//        }
    }
}
