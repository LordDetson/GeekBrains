package example4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(5)) {
                integers.add(6);
            }
        }
    }
}
