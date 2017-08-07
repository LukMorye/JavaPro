package lesson7;

public class Tester {

    static Calculator calculator;

    @BeforeSuite
    public static void init() {
        calculator = new Calculator();
        System.out.println("Calculator initialized");
    }


//    @BeforeSuite
//    public void init2() {
//        calculator = new Calculator();
//        System.out.println("Calculator initialized");
//    }

    @Test(priority = 5)
    public static void test1() {
        int a = 3;
        int b = 2;
        int expected = 5;
        int result = calculator.add(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @Test(priority = 2)
    public static void test2() {
        int a = 3;
        int b = 7;
        int expected = 10;
        int result = calculator.add(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @Test(priority = 4)
    public static void test3() {
        int a = 3;
        int b = 2;
        int expected = 1;
        int result = calculator.sub(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @Test(priority = 2)
    public static void test4() {
        int a = 4;
        int b = 2;
        float expected = 2;
        float result = calculator.div(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @Test(priority = 1)
    public static void test5() {
        int a = 3;
        int b = 2;
        int expected = 6;
        int result = calculator.multiple(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @Test (priority = 3)
    public static void test6() {
        int a = 8;
        int b = 2;
        int expected = 5;
        int result = calculator.sub(a,b);
        if (expected == result) {
            System.out.println("Test true. Expected: " + expected + " Result: " + result);
        } else {
            System.out.println("Test false. Expected: " + expected + " Result: " + result);
        }
    }

    @AfterSuite
    public static void deinit() {
        calculator = null;
        System.out.println("Called deinitialization");
    }


    @AfterSuite
    public static void deinit2() {
        calculator = null;
        System.out.println("Called deinitialization");
    }


}
