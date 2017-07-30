package lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final int TUNNEL_LENGTH = 80;

    private Semaphore semaphore;

    public Tunnel(int throughput) {
        this.length = TUNNEL_LENGTH;
        this.description = "Тоннель " + length + " метров. Пропускная способность: " + throughput + " машин";
        semaphore = new Semaphore(throughput);
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " готовится к этапу(ждёт): " + description);
            semaphore.acquire();
            /** Стартует по симафору */
            System.out.println(car.getName() + " начал этап \"" + description + "\"");
            Thread.sleep(length/car.getSpeed() * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(car.getName() + " завершил этап \"" + description + "\"");
            semaphore.release();
        }
    }
}
