package com.titov;

import java.util.ArrayList;

/**
 * Created by vtitov on 17.07.17.
 */
public class Box <T extends Fruit> {

    private ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public void put(T fruit) {
        fruits.add(fruit);
    }

    public double getWeight() {
        double weight = 0d;
        int size = fruits.size();
        for (int i = 0; i < size; i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public boolean compareTo(Box<?> box) {
        if (getWeight() == box.getWeight()) {
            return true;
        }
        return false;
    }

    public Box<T> pourTo(Box<T> dstBox) {
        int size = fruits.size();
        for (int i = size-1; i >= 0; i--) {
            dstBox.put(fruits.remove(i));
        }
        return dstBox;
    }
}
