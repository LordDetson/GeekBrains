package obstacle;

import animal.Animal;

public class Road extends Obstacle {
    public Road(int size) {
        super(size);
    }

    @Override
    public void doIt(Animal a) {
        a.run(size);
    }
}
