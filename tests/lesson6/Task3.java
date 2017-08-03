package lesson6;

import org.junit.*;


public class Task3 {

    static DBManager dbManager;
    private static String NAME = "Гена";
    private static int SCORE = 11;
    private static int NEW_SCORE = 20;

    @BeforeClass
    public static void init() {
        System.out.println("Called init");
        dbManager = new DBManager();
        dbManager.connect();
        dbManager.setAutoCommit(false);
    }

    @Test
    public  void test1() {
        dbManager.addStudent(NAME,SCORE);
        Assert.assertTrue(dbManager.isExistStudent(NAME,SCORE));
    }

    @Test
    public  void test2() {
        dbManager.updateScore(NAME, NEW_SCORE);
        Assert.assertEquals(NEW_SCORE,dbManager.getScore(NAME));
    }

    @AfterClass
    public static void deinit() {
        System.out.println("Called deinit");
        dbManager.disconnect();
    }
}
