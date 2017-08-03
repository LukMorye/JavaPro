package lesson6;

public class Lesson6 {

    private static final int TASK1_COMPARED_VALUE = 4;
    private static final int TASK2_COMPARED_VALUE_1 = 1;
    private static final int TASK2_COMPARED_VALUE_2 = 4;




    public static int[] task1 (int[] array) {
        int size = array.length;
        if (size == 0) {throw new RuntimeException("Empty Array");}
        int f = 0;
        boolean isExistCompared = false;
        for (int i = size-1; i >= 0; i--) {
            if (array[i] == TASK1_COMPARED_VALUE) {
                int[] filtered = new int[size - (i+1)];
                for (int j = i+1; j < size; j++,f++) {
                    filtered[f] = array[j];
                }
                return filtered;
            }
        }
        if (!isExistCompared) {
            throw new RuntimeException("Compared value is absent");
        }
        return null;
    }

    public static boolean task2(int[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            int value = array[i];
            if (value != TASK2_COMPARED_VALUE_1 && value != TASK2_COMPARED_VALUE_2) {
                return false;
            }
        }
        return true;
    }

}
