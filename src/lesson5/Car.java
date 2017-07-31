package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
/** Fields */
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier barrier;
    private CountDownLatch ready;
    private CountDownLatch finished;
    private static int CARS_COUNT = 0;
    private static final int REQUIRE_WAITING_TIME = 500;
    private static final int MAX_WAITING_TIME = 800;

/** Constructors */
    public Car(Race race, int speed, CyclicBarrier barrier, CountDownLatch ready,CountDownLatch finished) {
        this.race = race;
        this.speed = speed;
        this.barrier = barrier;
        this.ready = ready;
        this.finished = finished;
        CARS_COUNT ++;
        this.name = "Участник #" + CARS_COUNT;
    }


/** Action */
    @Override
    public void run() {
// Cyclic barrier -------------------------->>>>>>>
        System.out.println(name + " готовится");
        try {
            Thread.sleep(REQUIRE_WAITING_TIME + (int)(Math.random() * MAX_WAITING_TIME));
            System.out.println(name + " готов");
            ready.countDown();
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
//<<<<<--------------------------------------------
        System.out.println(name + " начал гонку");
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        System.out.println(name + " завершил гонку");
        finished.countDown();
    }

/** Getters */
    public Race getRace() {
        return race;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }
}
