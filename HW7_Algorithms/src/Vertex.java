public class Vertex {
    public char label;

    public boolean wasVisited;

    public int previus = -1;

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }
}
