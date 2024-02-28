package classForTestList;

import java.util.Comparator;

public class SortById implements Comparator<Example> {
    @Override
    public int compare(Example o1, Example o2) {
        return o1.getId() - o2.getId();
    }
}
