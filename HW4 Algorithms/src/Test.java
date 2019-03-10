import example1.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Stack test");
        Stack<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            stack.push(i);
        }
        System.out.println();
        System.out.println(stack.peek());
        Integer buf;
        while ((buf = stack.pop()) != null) {
            System.out.print(buf);
        }
        System.out.println();


        System.out.println();
        System.out.println("Queue test");
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            queue.add(i);
        }
        System.out.println();
        System.out.println(queue.peek());
        while ((buf = queue.pop()) != null) {
            System.out.print(buf);
        }
        System.out.println();


        System.out.println();
        System.out.println("Deque test");
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            deque.addLast(i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            deque.addFirst(i);
        }
        System.out.println();
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        while ((buf = deque.removeLast()) != null) {
            System.out.print(buf);
        }
        System.out.println();


        System.out.println();
        System.out.println("List test");
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            list.add(i);
        }
        System.out.println();
        System.out.println(list);
        list.add(2, 10);
        System.out.println(list);
        list.add(9, 10);
        System.out.println(list);
        list.add(0, 10);
        System.out.println(list);
        list.add(13, 10);
        System.out.println(list);
        list.remove();
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.remove(11);
        System.out.println(list);
        System.out.println(list.find(5) + " = " + list.get(list.find(5)));
        System.out.println(list.find(11) + " = " + list.get(list.find(11)));
        list.removeValue(5);
        System.out.println(list);
        list.removeValue(11);
        System.out.println(list);

        NodeIterator<Integer> iterator = list.iterator();
        while (!iterator.atEnd()) {
            System.out.print(iterator.getCurrent() + " ");
            iterator.next();
        }
        System.out.println();

        iterator.reset();
        int countElements = list.size() / 2;
        for (int i = 0; i < countElements; i++) {
            iterator.deleteCurrent();
            iterator.reset();
        }
        System.out.println(list);

        iterator.reset();
        iterator.insertBefore(100);
        System.out.println(list);

        iterator.insertAfter(100);
        System.out.println(list);
    }
}
