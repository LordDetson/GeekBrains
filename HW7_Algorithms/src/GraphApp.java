public class GraphApp {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');

        graph.addEdge(0, 1); //AB
        graph.addEdge(0, 2); //AC
        graph.addEdge(0, 3); //AD
        graph.addEdge(1, 4); //BE
        graph.addEdge(2, 5); //CF
        graph.addEdge(3, 6); //DG
        graph.addEdge(4, 7); //EH

        graph.displayDfs();
        System.out.println();
        graph.displayBfs();
        System.out.println();

        graph.displayPath(graph.bfs(0,5));
    }
}
