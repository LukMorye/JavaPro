package lesson4;

/**
 * Created by vtitov on 27.07.17.
 */
public class MFD {
    private static int printed = 0;
    private static int scanned = 0;
    private Object monPrint = new Object();
    private Object monScan = new Object();


    public void print() {
        synchronized (monPrint) {
            System.out.println("Отпечатано " + (++printed) + " страниц(ы)");
        }
    }

    public void scan() {
        synchronized (monScan) {
            System.out.println("Отсканировано " + (++scanned) + " страниц(ы)");
        }
    }

}
