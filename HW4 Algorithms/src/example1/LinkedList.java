package example1;

import java.util.StringJoiner;

public class LinkedList<T> implements List<T>, Stack<T>, Queue<T>, Deque<T> {
    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        Node(T value) {
            this.value = value;
        }

        Node(T value, Node<T> next, Node<T> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        T getValue() {
            return value;
        }

        void setValue(T value) {
            this.value = value;
        }

        Node<T> getNext() {
            return next;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        Node<T> getPrevious() {
            return previous;
        }

        void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("next=" + (next != null ? next.getValue() : "null"))
                    .add("previous=" + (previous != null ? previous.getValue() : "null"))
                    .toString();
        }
    }

    private Node<T> top;
    private Node<T> bottom;
    private int size;

    public boolean isEmpty() {
        return top == null && bottom == null;
    }

    @Override
    public boolean remove(int index) {
        Node<T> bufNode = getNode(index);
        if (bufNode != null) {
            if (bufNode == top) {
                return removeFirst() != null ? true : false;
            }
            if (bufNode == bottom) {
                return removeLast() != null ? true : false;
            }
            bufNode.getNext().setPrevious(bufNode.getPrevious());
            bufNode.getPrevious().setNext(bufNode.getNext());
            bufNode.setNext(null);
            bufNode.setPrevious(null);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (bottom == null) {
            bottom = node;
        }
        if (top != null) {
            top.setPrevious(node);
            node.setNext(top);
        }
        top = node;
        size++;
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (top == null) {
            top = node;
        }
        if (bottom != null) {
            bottom.setNext(node);
            node.setPrevious(bottom);
        }
        bottom = node;
        size++;
    }

    @Override
    public T getFirst() {
        return (top != null ? top.getValue() : null);
    }

    @Override
    public T getLast() {
        return (bottom != null ? bottom.getValue() : null);
    }

    @Override
    public T removeFirst() {
        Node<T> bufNode;
        T buf;
        if ((buf = getFirst()) != null) {
            bufNode = top.getNext();
            top.setNext(null);
            top = bufNode;
            top.setPrevious(null);
            size--;
        }
        return buf;
    }

    @Override
    public T removeLast() {
        Node<T> bufNode;
        T buf;
        if ((buf = getLast()) != null) {
            bufNode = bottom.getPrevious();
            bottom.setPrevious(null);
            bottom = bufNode;
            bottom.setNext(null);
            size--;
        }
        return buf;
    }

    public void add(int index, T value) {
        insert(new Node<>(value), getNode(index));
    }

    @Override
    public T get(int index) {
        return getNode(index).getValue();
    }

    private Node<T> getNode(int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                return top;
            } else if (index == size) {
                return bottom;
            }
            int indexCurrent = 1;
            Node<T> current;
            if (index < size / 2) {
                current = top;
                while (indexCurrent < size / 2) {
                    current = current.getNext();
                    if (indexCurrent == index) {
                        return current;
                    }
                    indexCurrent++;
                }
            } else {
                current = bottom;
                indexCurrent = size - 1;
                while (indexCurrent >= size / 2) {
                    if (indexCurrent == index) {
                        return current;
                    }
                    current = current.getPrevious();
                    indexCurrent--;
                }
            }
        }
        return null;
    }

    private void insert(Node<T> node, Node<T> current) {
        if (current == top) {
            addFirst(node.value);
            return;
        }
        node.setNext(current);
        node.setPrevious(current.getPrevious());
        current.getPrevious().setNext(node);
        current.setPrevious(node);
        size++;
    }

    public void add(T value) {
        addLast(value);
    }

    @Override
    public void push(T value) {
        addFirst(value);
    }

    @Override
    public T peek() {
        return getFirst();
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    public void display() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<T> current = top;
        while (current != null) {
            joiner.add(current.getValue().toString());
            current = current.getNext();
        }
        System.out.println(joiner);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LinkedList.class.getSimpleName() + "[", "]")
                .add("top=" + top)
                .add("bottom=" + bottom)
                .add("size=" + size)
                .toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }
        list.display();
        System.out.println(list);
        list.add(2, 10);
        list.display();
        System.out.println(list);
        list.add(9, 10);
        list.display();
        System.out.println(list);
        list.add(0, 10);
        list.display();
        System.out.println(list);
        list.add(13, 10);
        list.display();
        System.out.println(list);
        list.removeFirst();
        list.display();
        System.out.println(list);
        list.removeLast();
        list.display();
        System.out.println(list);
        list.remove(2);
        list.display();
        System.out.println(list);
        list.remove(11);
        list.display();
        System.out.println(list);
    }

}
