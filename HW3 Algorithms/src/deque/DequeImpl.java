package deque;

import java.util.*;
import java.util.stream.Collectors;

//вы разрешили мне деку реализовать методами JavaSE
public final class DequeImpl<T> extends LinkedList<T> implements Deque<T> {
    public DequeImpl() {
        super();
    }

    public DequeImpl(Collection<? extends T> c) {
        super(c);
    }

    private static class Test {
        private static Random random = new Random();
        private static int maxSize = 10;

        public static void main(String[] args) {
            ArrayList<Integer> integers = random.ints(maxSize, -50, 51)
                    .boxed().collect(Collectors.toCollection(ArrayList::new));
            Deque<Integer> integerDeque = new DequeImpl<>(integers);
            System.out.println(integerDeque);
            while (!integerDeque.isEmpty()) {
                System.out.println("pollFirst " + integerDeque.pollFirst());
            }
            System.out.println(integerDeque);
            integerDeque = new DequeImpl<>(integers);
            System.out.println(integerDeque);
            while (!integerDeque.isEmpty()) {
                System.out.println("pollLast " + integerDeque.pollLast());
            }
            System.out.println(integerDeque);
        }
    }
}
