package com.titov;


import java.util.ArrayList;

/**
 * Created by valentintitov on 16.07.17.
 */

public class Main {

    public static void main(String[] args) {
/** В комментарии вызов реализации первых двух задач из домашнего задания
 *
        Object[] arr = new Object[]{1,2,4,6};
        int size = arr.length;
        Object[] newArray = replace(arr,1,3);
        for (int i = 0; i < size; i++) {
            System.out.print(newArray[i] + ", ");
        }
        ArrayList list = toArrayList(newArray);
        System.out.println("\n" + list.toString());
 */
        Box<Orange> box1 = generatedOrangesBox(4);
        Box<Orange> box2 = generatedOrangesBox(7);
        Box<Apple> box3 = generatedAppleBox(6);
        System.out.println("Is weight of box1 equal to box2? - " + box1.compareTo(box2));
        System.out.println("Is weight of box1 equal to box3? - " + box1.compareTo(box3));
        System.out.println("Pour box1 with weight " + box1.getWeight() + " to box2 with weight " + box2.getWeight());
        System.out.println("New weight of box2: " + box1.pourTo(box2).getWeight());
        System.out.println("Weight of box1: " + box1.getWeight());
// Попытка пересыпать апельсины в яблоки. При раскомментировании должна выдаваться ошибка
//        box1.pourTo(box3);

    }

    public static Box<Orange> generatedOrangesBox(int size) {
        Box<Orange> box = new Box<>();
        for (int i = 0; i < size; i++) {
            box.put(new Orange());
        }
        return box;
    }

    public static Box<Apple> generatedAppleBox(int size) {
        ArrayList<Apple> orangesList1 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            orangesList1.add(new Apple());
        }
        return new Box<>(orangesList1);
    }


/** Part 1 */
    private static Object[] replace(Object[] array, int srcPosition, int dstPosition) {
        int size = array.length;
        if (srcPosition < 0 || srcPosition >=size) {
            throw new RuntimeException("Bad source element position: " + srcPosition);
        }
        if (dstPosition < 0  || dstPosition >= size) {
            throw new RuntimeException("Bad destination element position: " + dstPosition);
        }
        Object tmp = array[srcPosition];
        array[srcPosition] = array[dstPosition];
        array[dstPosition] = tmp;
        return array;
    }

    private static ArrayList<Object> toArrayList(Object[] array) {
        if (array == null) {
            throw new RuntimeException("null array as argument");
        }
        int size = array.length;
        ArrayList<Object> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(array[i]);
        }
        return list;
    }


}


