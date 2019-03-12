package example2;

import java.util.*;
import java.util.stream.Collectors;

public class BackpackProblem {
    private static class CombinationGeneration {
        private static boolean hasNext(int[] a, int m) {
            int k = m;
            for (int i = k - 1; i >= 0; i--)
                if (a[i] < a.length - k + i + 1) {
                    ++a[i];
                    for (int j = i + 1; j < k; j++)
                        a[j] = a[j - 1] + 1;
                    return true;
                }
            return false;
        }

        static List<List<Integer>> generation(int size) {
            List<List<Integer>> result = new ArrayList<>();
            int[] a = new int[size];
            for (int i = size; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    a[j] = j + 1;
                }
                List<Integer> buf;
                do {
                    buf = new ArrayList<>(i);
                    for (int j = 0; j < i; j++) {
                        buf.add(a[j] - 1);
                    }
                    result.add(buf);
                } while (hasNext(a, i));
            }
            return result;
        }

        public static void main(String[] args) {
            generation(6).forEach(System.out::println);
        }
    }

    public static void execute(Backpack backpack, List<Thing> things) {
        List<List<Integer>> indexesList = CombinationGeneration.generation(things.size());
        List<List<Thing>> list = new ArrayList<>();
        for (int i = 0; i < indexesList.size(); i++) {
            List<Thing> buf = new ArrayList<>();
            for (int j = 0; j < indexesList.get(i).size(); j++) {
                buf.add(things.get(indexesList.get(i).get(j)));
            }
            if (getVolume(buf) <= backpack.getMaxVolume())
                list.add(buf);
        }

        List<Thing> thingsMaxCost = list.stream().max((o1, o2) -> {
            int c1 = getCost(o1);
            int c2 = getCost(o2);
            if (c1 > c2) {
                return 1;
            } else if (c1 < c2) {
                return -1;
            }
            return 0;
        }).orElse(null);

        backpack.putAll(thingsMaxCost);
    }

    private static int getVolume(List<Thing> things) {
        return things.parallelStream()
                .map(Thing::getWeight)
                .collect(Collectors.toList()).parallelStream()
                .reduce((integer, integer2) -> integer + integer2).orElse(0);
    }

    private static int getCost(List<Thing> things) {
        return things.parallelStream()
                .map(Thing::getCost)
                .collect(Collectors.toList()).parallelStream()
                .reduce((integer, integer2) -> integer + integer2).orElse(0);
    }

    private static class Test {
        private static int size = 6;
        private static List<Thing> things = new ArrayList<>();

        public static void main(String[] args) throws IncorrectVolume {
            Backpack backpack = new Backpack(122);
            for (int i = 1; i <= size; i++) {
                things.add(new Thing(i * 10, (i * 10) + 10));
            }
            System.out.println(things);
            execute(backpack, things);
            System.out.println(backpack);
        }
    }
}
