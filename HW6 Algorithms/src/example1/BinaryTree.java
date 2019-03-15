package example1;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class BinaryTree<K extends Comparable<K>, V extends Comparable<V>> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("value=" + value)
                    .add("leftKey=" + (left != null ? left.key.toString() : "null"))
                    .add("leftValue=" + (left != null ? left.value.toString() : "null"))
                    .add("rightKey=" + (right != null ? right.key.toString() : "null"))
                    .add("rightValue=" + (right != null ? right.value.toString() : "null"))
                    .toString();
        }
    }

    private Node<K, V> root;

    public boolean isEmpty() {
        return root == null;
    }

    public Optional<V> rget(K key) {
        Node<K, V> result = rfind(root, key);
        return !isNull(result) ? Optional.of(result.value) : Optional.empty();
    }

    private Node<K, V> rfind(Node<K, V> current, K key) {
        int stateCompare;
        if (isNull(current))
            return null;
        if ((stateCompare = key.compareTo(current.key)) == 0)
            return current;
        if (stateCompare < 0)
            return rfind(current.left, key);
        return rfind(current.right, key);
    }

    private boolean isNull(Node<K, V> node) {
        return node == null;
    }

    public Optional<V> get(K key) {
        Node<K, V> result = find(key);
        return !isNull(result) ? Optional.of(result.value) : Optional.empty();
    }

    private Node<K, V> find(K key) {
        if (!isEmpty()) {
            Node<K, V> current = root;
            int stateCompare;
            while (!isNull(current) && (stateCompare = key.compareTo(current.key)) != 0) {
                if (stateCompare < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return current;
        }
        return null;
    }

    private Node<K, V> findMinByKey(@NotNull Node<K, V> current) {
        while (!isNull(current.left))
            current = current.left;
        return current;
    }

    private Node<K, V> findMaxByKey(@NotNull Node<K, V> current) {
        while (!isNull(current.right))
            current = current.right;
        return current;
    }

    public void insert(K key, V value) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        if (isEmpty()) {
            root = node;
        } else {
            Node<K, V> parent = root;
            Node<K, V> current = root;
            boolean isLeft = false;
            while (!isNull(current)) {
                parent = current;
                if (node.key.compareTo(current.key) <= 0) {
                    current = current.left;
                    isLeft = true;
                } else {
                    current = current.right;
                    isLeft = false;
                }
            }
            if (isLeft) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    public boolean delete(K key) {
        if (isEmpty()) return false;
        Node<K, V> current = root;
        Node<K, V> parent = root;
        int stateCompare;
        boolean isLeft = true;
        while (!isNull(current) && (stateCompare = key.compareTo(current.key)) != 0) {
            parent = current;
            if (stateCompare < 0) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
        if (isNull(current)) return false;

        //если нет потомков
        if (current.left == null && current.right == null)
            if (current == root) root = null;
            else if (isLeft) parent.left = null;
            else parent.right = null;

        //если один потомок
        if (current.left == null && current.right != null)
            if (current == root) root = current.right;
            else if (isLeft) parent.left = current.right;
            else parent.right = current.right;
        else if (current.left != null && current.right == null)
            if (current == root) root = current.left;
            else if (isLeft) parent.left = current.left;
            else parent.right = current.left;

        //если 2 потомка
        if (current.left != null && current.right != null) {
            Node<K, V> node = findMinByKey(current.right);
            delete(node.key);
            node.right = current.right;
            node.left = current.left;
            if (current == root) {
                root = node;
            } else if (isLeft) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        return true;
    }

    private void traversePreOrder(Consumer<Node<K, V>> consumer, Node<K, V> current) {
        if (!isNull(current)) {
            consumer.accept(current);
            traversePreOrder(consumer, current.left);
            traversePreOrder(consumer, current.right);
        }
    }

    private void traversePostOrder(Consumer<Node<K, V>> consumer, Node<K, V> current) {
        if (!isNull(current)) {
            traversePostOrder(consumer, current.left);
            traversePostOrder(consumer, current.right);
            consumer.accept(current);
        }
    }

    private void traverseInOrder(Consumer<Node<K, V>> consumer, Node<K, V> current) {
        if (!isNull(current)) {
            traverseInOrder(consumer, current.left);
            consumer.accept(current);
            traverseInOrder(consumer, current.right);
        }
    }

    private void traverseBFS(Consumer<Node<K, V>> consumer) {
        if (!isEmpty()) {
            Queue<Node<K, V>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                consumer.accept(node);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
    }

    public void displayPreOrder() {
        traversePreOrder(System.out::println, root);
    }

    public void displayPostOrder() {
        traversePostOrder(System.out::println, root);
    }

    public void displayInOrder() {
        traverseInOrder(System.out::println, root);
    }

    public void displayBFS() {
        traverseBFS(System.out::println);
    }

    public int count() {
        AtomicInteger count = new AtomicInteger();
        traverseInOrder(node -> count.getAndIncrement(), root);
        return count.get();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static class Test {
        private static Random random = new Random();

        public static void main(String[] args) throws IOException {
            BinaryTree<Integer, Integer> tree = new BinaryTree<>();
            for (int i = 0; i < 5; i++) {
                int key = random.nextInt(10);
                int value = random.nextInt(10);
                System.out.print(key + ":" + value + " ");
                tree.insert(key, value);
            }
            System.out.println("\n");
            System.out.println("count = " + tree.count() + "\n");

            System.out.println("displayPreOrder()");
            tree.displayPreOrder();
            System.out.println();

            System.out.println("displayPostOrder()");
            tree.displayPostOrder();
            System.out.println();

            System.out.println("displayInOrder()");
            tree.displayInOrder();
            System.out.println();

            System.out.println("displayBFS()");
            tree.displayBFS();
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            int key = -1;
            System.out.println("Введите key для удаления");
            System.out.print(">> ");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
            }
            System.out.println(tree.delete(key));

            System.out.println("displayPreOrder()");
            tree.displayPreOrder();
            System.out.println();
        }
    }
}
