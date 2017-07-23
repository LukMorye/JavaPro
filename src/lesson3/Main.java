package lesson3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by valentintitov on 23.07.17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        task1();
    }

    private static void task1()  throws  Exception {
        System.out.println("TASK1 ----------------->>>\n");
        File file = new File("src//lesson3//first_task_file");
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            StringBuilder builder = new StringBuilder();
            int x;
            while((x = in.read()) != -1) {
                builder.append((char) x);
            }
            System.out.println(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nEND TASK1 <<<-------------");
    }
}
