package lesson6;


import org.junit.Assert;
import org.junit.Test;


public class Task1 {

    @Test
    public  void test1() {
        int[] array = new int[]{1,2,4,4,2,3,4,1,7};
        int[] trueArray = new int[]{1,7};
        Assert.assertArrayEquals(trueArray,Lesson6.task1(array));
    }

    @Test
    public  void test2() {
        int[] array = new int[]{1,5,4};
        int[] trueArray = new int[]{};
        Assert.assertArrayEquals(trueArray,Lesson6.task1(array));
    }

    @Test (expected = RuntimeException.class)
    public  void test3() {
        int[] array = new int[]{1,5};
        int[] trueArray = new int[]{};
        Assert.assertArrayEquals(trueArray,Lesson6.task1(array));
    }
}
