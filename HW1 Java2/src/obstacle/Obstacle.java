package obstacle;

import animal.Animal;

public abstract class Obstacle {
    final int size;

    public Obstacle(int size) {
        this.size = size;
    }


    public abstract void doIt(Animal a);
}
