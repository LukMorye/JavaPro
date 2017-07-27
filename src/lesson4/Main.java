package lesson4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by vtitov on 27.07.17.
 */
public class Main {

    private final static Object monTask1 = new Object();
    private final static int t1Count = 5;
    private volatile static char currentLetter  = 'A';

    private final static Object monTask2 = new Object();
    private static BufferedOutputStream output;

    public static void main(String[] args) {
        task1();
//        task2();
//        task3();
    }

/** ----------- TASK1 ------------ */
    private static void task1() {
        Thread t1 = new Task1Thread(() -> {
            tsk1PrintA();
        },'A');
        Thread t2 = new Task1Thread(() -> {
            tsk1PrintB();
        },'B');
        Thread t3 = new Task1Thread(() -> {
            tsk1PrintC();
        },'C');
        t1.start();
        t2.start();
        t3.start();
    }

    private static void tsk1PrintA() {
        synchronized (monTask1) {
            for (int i = 0; i < t1Count; i++) {
                try {
                    while (currentLetter != 'A') {
                        monTask1.wait();
                    }
                    monTask1.notify();
                    System.out.print("A");
                    currentLetter = 'B';
                    monTask1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void tsk1PrintB() {
        synchronized (monTask1) {
            for (int i = 0; i < t1Count; i++) {
                try {
                    while (currentLetter != 'B') {
                        monTask1.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monTask1.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void tsk1PrintC() {
        synchronized (monTask1) {
            for (int i = 0; i < t1Count; i++) {
                try {
                    while (currentLetter != 'C') {
                        monTask1.wait();
                    }
                    currentLetter = 'A';
                    System.out.print("C");
                    monTask1.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
/** ----------- TASK2 ------------ */
    private static void task2() {
        try {
            output = new BufferedOutputStream(new FileOutputStream("Threads.txt"));
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    tsk2Write("Thread 1 wrote something\n");
                }
            });

            Thread t2 = new Thread(() ->  {
                for (int i = 0; i < 10; i++) {
                    tsk2Write("Thread 2 wrote something\n");
                }
            });
            Thread t3 = new Thread(() ->  {
                for (int i = 0; i < 10; i++) {
                    tsk2Write("Thread 3 wrote something\n");
                }
            });
            t1.start();
            t2.start();
            t3.start();
            try {
                t1.join();
                t2.join();
                t3.join();
                output.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tsk2Write(String string)  {
        synchronized (monTask2) {
            try {
                byte [] bytes = string.getBytes();
                output.write(bytes);
                monTask2.wait(20);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


/** ----------- TASK3 ------------ */
    public static void task3() {
        MFD mfd = new MFD();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                mfd.print();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                mfd.print();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                mfd.print();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                mfd.scan();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                mfd.scan();
            }
        }).start();
    }

}
