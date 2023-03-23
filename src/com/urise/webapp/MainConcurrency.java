package com.urise.webapp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS = 10000;
    private static int counter;
    private static final Lock lock = new ReentrantLock();

    public MainConcurrency() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName());
            }
        }.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Thread> list = new ArrayList<>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
//            thread.start();
//            list.add(thread);
        }
//
        latch.await();
        executorService.shutdownNow();
        System.out.println(counter);
//
//        String lock1 = "lock_1";
//        String lock2 = "lock_2";
//        deadLocking(lock1, lock2);
//        deadLocking(lock2, lock1);


    }

//    private static void deadLocking(String lock1, String lock2) {
//        new Thread(() -> {
//            System.out.println("Waiting: " + lock1);
//            synchronized (lock1) {
//                System.out.println("Hold: " + lock1);
//                System.out.println("Waiting: " + lock2);
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                synchronized (lock2) {
//                    System.out.println("Hold: " + lock2);
//                }
//            }
//        }).start();
//    }

    private void inc() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

}
