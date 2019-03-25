import java.util.*;

public class Graph {
    private final int MAX_VERTS = 32;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int size;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        size = 0;
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.print(vertexList[vertex].label);
    }

    private int getAdjUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMat[ver][i] == 1 && !vertexList[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    public void displayDfs() {
        int startIndex = 0;
        Stack<Integer> stack = new Stack<>();
        visitVertex(startIndex, stack);
        displayVertex(startIndex);
        int v;
        while (!stack.isEmpty()) {
            v = getAdjUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                visitVertex(v, stack);
                displayVertex(v);
            }
        }
        resetWasVisited();
    }

    private void visitVertex(int indexVertex, Collection<Integer> collection) {
        vertexList[indexVertex].wasVisited = true;
        collection.add(indexVertex);
    }

    private void resetWasVisited() {
        for (int i = 0; i < size; i++)
            vertexList[i].wasVisited = false;
    }

    public void displayBfs() {
        int startIndex = 0;
        Queue<Integer> queue = new LinkedList<>();
        visitVertex(startIndex, queue);
        displayVertex(startIndex);
        int v2;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                visitVertex(v2, queue);
                displayVertex(v2);
            }
        }
        resetWasVisited();
    }

    public List<Integer> bfs(int startIndex, int finishIndex) {
        LinkedList<Integer> path = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        visitVertex(startIndex, queue);
        boolean isFind;
        int v2;
        while (!queue.isEmpty()) {
            isFind = false;
            int v1 = queue.remove();
            path.add(v1);
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                visitVertex(v2, queue);
                path.add(v2);
                if (v2 == finishIndex) {
                    isFind = true;
                    break;
                } else {
                    path.removeLast();
                }
            }
            if (isFind) {
                break;
            }
        }
        resetWasVisited();
        return path;
    }

    public void displayPath(List<Integer> path) {
        path.forEach(this::displayVertex);
        System.out.println();
    }

}
