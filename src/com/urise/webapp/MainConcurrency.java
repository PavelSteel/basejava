package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS = 10000;
    private static int counter;

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
        List<Thread> list = new ArrayList<>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            list.add(thread);
        }
        list.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(counter);

        String lock1 = "lock_1";
        String lock2 = "lock_2";
        deadLocking(lock1, lock2);
        deadLocking(lock2, lock1);


    }

    private static void deadLocking(String lock1, String lock2) {
        new Thread(() -> {
            System.out.println("Waiting: " + lock1);
            synchronized (lock1) {
                System.out.println("Hold: " + lock1);
                System.out.println("Waiting: " + lock2);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Hold: " + lock2);
                }
            }
        }).start();
    }

    private synchronized void inc() {
        counter++;
    }

}
