package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS = 10000;
    private static int counter;
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        new Thread(){
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

    }

    private synchronized void inc(){
        counter++;
    }
}
