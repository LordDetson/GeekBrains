import animal.Animal;

public abstract class Obstacle {
    protected final int size;

    protected Obstacle(int size) {
        this.size = size;
    }


    public abstract void doIt(Animal a);
}
