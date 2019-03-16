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

    public int getDepth() {
        return getDepth(root, 0);
    }

    public int getDepthLeftSubTree() {
        return getDepth(root.left, 0);
    }

    public int getDepthRightSubTree() {
        return getDepth(root.right, 0);
    }

    private int getDepth(Node<K, V> current, int levelDepth) {
        if (current == null) {
            return levelDepth;
        }
        return Math.max(getDepth(current.left, levelDepth + 1),
                getDepth(current.right, levelDepth + 1));
    }

    public boolean isВalanced() {
        int n = Math.abs(getDepthLeftSubTree() - getDepthRightSubTree());
        return n == 1 || n == 0;
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

    private enum NodeTypeByHavingChild {
        NO_CHILDREN,
        ONLY_LEFT_CHILD,
        ONLY_RIGHT_CHILD,
        TWO_CHILDREN
    }

    private NodeTypeByHavingChild defineType(Node<K, V> node) {
        if (node.left == null && node.right == null) return NodeTypeByHavingChild.NO_CHILDREN;
        if (node.left == null) return NodeTypeByHavingChild.ONLY_RIGHT_CHILD;
        if (node.right == null) return NodeTypeByHavingChild.ONLY_LEFT_CHILD;
        return NodeTypeByHavingChild.TWO_CHILDREN;
    }

    private boolean isRoot(Node<K, V> node) {
        return node == root;
    }

    private void doDelete(Triplet<K, V> triplet, Node<K, V> replacement) {
        if (isRoot(triplet.current)) root = replacement;
        else if (triplet.isLeft) triplet.parent.left = replacement;
        else triplet.parent.right = replacement;
    }

    private class Triplet<K, V> {
        Node<K, V> current;
        Node<K, V> parent;
        boolean isLeft;
    }

    private Triplet<K, V> findForDelete(K key) {
        Triplet<K, V> triplet = new Triplet<>();
        triplet.current = root;
        triplet.parent = root;
        triplet.isLeft = true;
        int stateCompare;
        while (!isNull(triplet.current) && (stateCompare = key.compareTo(triplet.current.key)) != 0) {
            triplet.parent = triplet.current;
            if (stateCompare < 0) {
                triplet.current = triplet.current.left;
                triplet.isLeft = true;
            } else {
                triplet.current = triplet.current.right;
                triplet.isLeft = false;
            }
        }
        return triplet;
    }

    public boolean delete(K key) {
        if (isEmpty()) return false;
        Triplet<K, V> triplet = findForDelete(key);
        if (isNull(triplet.current)) return false;
        NodeTypeByHavingChild type = defineType(triplet.current);
        Node<K, V> replacement = null;
        switch (type) {
            case NO_CHILDREN:
                break;
            case ONLY_LEFT_CHILD:
                replacement = triplet.current.left;
                break;
            case ONLY_RIGHT_CHILD:
                replacement = triplet.current.right;
                break;
            case TWO_CHILDREN:
                Node<K, V> node = findMinByKey(triplet.current.right);
                delete(node.key);
                node.right = triplet.current.right;
                node.left = triplet.current.left;
                replacement = node;
                break;
        }
        doDelete(triplet, replacement);
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

    public int countLeftSubTree() {
        AtomicInteger count = new AtomicInteger();
        traverseInOrder(node -> count.getAndIncrement(), root.left);
        return count.get();
    }

    public int countRightSubTree() {
        AtomicInteger count = new AtomicInteger();
        traverseInOrder(node -> count.getAndIncrement(), root.right);
        return count.get();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static class Test {
        private static Random random = new Random();

        public static int nextInt(int button, int top) {
            if (button < 0) {
                button = -button;
            }
            return random.nextInt(top + button) - button;
        }

        public static void main(String[] args) throws IOException {
            BinaryTree[] treeMass = new BinaryTree[20];
            int count = 0;
            for (int i = 0; i < 20; i++) {
                treeMass[i] = new BinaryTree<Integer, Integer>();
                BinaryTree<Integer, Integer> tree = treeMass[i];
                while (tree.getDepth() < 6) {
                    int key = nextInt(-100, 100);
                    int value = nextInt(-100, 100);
                    //System.out.print(key + ":" + value + " ");
                    tree.insert(key, value);
                }
                //System.out.println("\n");
                System.out.println("[" + i + "]count = " + tree.count());
                System.out.println("[" + i + "]countLeftSubTree = " + tree.countLeftSubTree());
                System.out.println("[" + i + "]countRightSubTree = " + tree.countRightSubTree());
                System.out.println("[" + i + "]getDepth = " + tree.getDepth());
                System.out.println("[" + i + "]getDepthLeftSubTree = " + tree.getDepthLeftSubTree());
                System.out.println("[" + i + "]getDepthRightSubTree = " + tree.getDepthRightSubTree());
                System.out.println("\n");
                if (!tree.isВalanced()) count++;
            }

            System.out.println("Statistics: " + (count / 20.0 * 100));
/*
            System.out.println("displayPreOrder()");
            tree.displayPreOrder();
            System.out.println();
            */
/*
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
            System.out.println("Введите key");
            System.out.print(">> ");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
            }
            System.out.println(tree.rget(key).orElse(-1));
            System.out.println();

            key = -1;
            System.out.println("Введите key для удаления");
            System.out.print(">> ");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
            }
            System.out.println(tree.delete(key));

            System.out.println("displayPreOrder()");
            tree.displayPreOrder();
            System.out.println();
            */
        }
    }
}
