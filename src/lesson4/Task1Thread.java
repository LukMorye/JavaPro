package lesson4;

/**
 * Created by vtitov on 27.07.17.
 */
public class Task1Thread extends Thread {

    char symbol;

    public Task1Thread(Runnable target, char symbol) {
        super(target);
        this.symbol = symbol;
    }


    public char getSymbol() {
        return symbol;
    }
}
