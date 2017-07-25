package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by valentintitov on 23.07.17.
 */
public class Main {

    final static private int FILES_COUNT = 5;

    public static void main(String[] args) throws Exception {
//        task1();
        task2();
    }

    private static void task1()  throws  Exception {
        System.out.println("TASK1 ----------------->>>\n");
        File file = new File("src//lesson3//first_task_file");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        StringBuilder builder = new StringBuilder();
        int x;
        while((x = in.read()) != -1) {
            builder.append((char) x);
        }
        System.out.println(builder.toString());
        System.out.println("\nEND TASK1 <<<-------------");
    }

    private static void task2() throws Exception {
        Enumeration<InputStream> e = Collections.enumeration(readFiles());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("concatenated.txt"));
        while (e.hasMoreElements()) {
            BufferedInputStream in = (BufferedInputStream) e.nextElement();
            byte [] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            out.write(bytes);
        }
        out.close();

    }

    private static ArrayList<InputStream> readFiles() throws Exception {
        ArrayList<InputStream> inStreams = new ArrayList<>(FILES_COUNT);
        String directory = "src//lesson3//";
        String filename = "second_task_file";
        for (int i = 0; i < FILES_COUNT; i++) {
            File file = new File(directory + filename + (i+1));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            inStreams.add(in);
        }
        return inStreams;
    }
}
