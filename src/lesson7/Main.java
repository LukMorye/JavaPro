package lesson7;

import java.lang.reflect.Method;
import java.util.*;

public class Main {
    static Method beforeSuite;
    static ArrayList<MethodPriority> listMethods = new ArrayList<>();
    static Method afterSuite;

    public static void main(String[] args) throws Exception {
        Class aClass = Tester.class;
        String className = aClass.getName();
        start(className);
    }

    public static void start(String className) throws Exception{
        Class aClass = Class.forName(className);
        start(aClass);
    }

    public static void start(Class aClass) throws Exception {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuite != null) { throw new RuntimeException("The annotation 'BeforeSuite' must have only one instance"); }
                beforeSuite = method;
                System.out.println("Will invoke single BeforeSuite Annotation");
            }
            if (method.isAnnotationPresent(Test.class)) {
                MethodPriority mp = new MethodPriority(method,method.getAnnotation(Test.class).priority());
                listMethods.add(mp);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuite != null) { throw new RuntimeException("The annotation 'AfterSuite' must have only one instance"); }
                afterSuite = method;
                System.out.println("Will invoke single AfterSuite Annotation");

            }
        }

        Collections.sort(listMethods, Comparator.comparingInt(MethodPriority::getPriority));
        beforeSuite.invoke(null);
        for (int i = 0; i < listMethods.size(); i++) {
            MethodPriority mp = listMethods.get(i);
            Method method = mp.getMethod();
            System.out.println("Method: " + method.getName() + "; Priority: " + mp.getPriority());
            method.invoke(null);
        }
        afterSuite.invoke(null);

    }
}
