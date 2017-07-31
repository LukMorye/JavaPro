package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 4;
    private static final int MIN_CAR_SPEED = 20;
    private static final int DELTA_CAR_SPEED = 10;
    /** Race parameters */
    public static final int FIRST_ROAD_LENGTH = 100;
    public static final int SECOND_ROAD_LENGTH = 300;
    public static final int THIRD_ROAD_LENGTH = 100;
    public static final int FIRST_TUNNEL_THROUGHPUT = 2;
    public static final int SECOND_TUNNEL_THROUGHPUT = 3;


    public static void main(String[] args) {


        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(FIRST_ROAD_LENGTH),new Tunnel(FIRST_TUNNEL_THROUGHPUT), new Road(SECOND_ROAD_LENGTH), new Tunnel(SECOND_TUNNEL_THROUGHPUT), new Road(THIRD_ROAD_LENGTH));
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT);
        CountDownLatch readyLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch finished = new CountDownLatch(CARS_COUNT);
        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race,MIN_CAR_SPEED + (int) (Math.random() * DELTA_CAR_SPEED),barrier,readyLatch,finished);
        }
        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(cars[i]).start();
        }
        try {
            readyLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
            finished.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
