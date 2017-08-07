package lesson7;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodPriority  implements Comparable {

    private int priority;
    private Method method;

    public MethodPriority(Method method,int priority) {
        this.priority = priority;
        this.method = method;
    }

    public int getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }


    @Override
    public int compareTo(Object o) {
        MethodPriority obj = (MethodPriority) o;
        return ((MethodPriority) o).priority - obj.priority;
    }
}

