

import java.util.Comparator;

public class IntegerComparator implements Comparator{
    @Override
    public int compare(Object t1, Object t2) {
        return (Integer) t1 - (Integer) t2;
    }
}
