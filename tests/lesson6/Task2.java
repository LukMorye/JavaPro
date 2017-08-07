package lesson6;

import org.junit.Assert;
import org.junit.Test;

public class Task2 {

    @Test
    public  void test1() {
        int[] array = new int[]{1};
        Assert.assertTrue(Lesson6.task2(array));
    }

    @Test
    public  void test2() {
        int[] array = new int[]{1,3,4};
        Assert.assertTrue(Lesson6.task2(array));
    }
}
