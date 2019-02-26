import action.Jump;
import animal.Animal;

public class Wall extends Obstacle {
    public Wall(int size) {
        super(size);
    }

    @Override
    public void doIt(Animal a) {
        if (a instanceof Jump) {
            ((Jump) a).doJump(size);
        }
    }
}
