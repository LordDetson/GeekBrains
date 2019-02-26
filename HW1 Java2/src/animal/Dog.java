package animal;

import action.Jump;
import action.Swimable;

import java.util.StringJoiner;

public class Dog extends Animal implements Swimable, Jump {
    final int canSwimDistance;
    int jumpHeight;

    public Dog(int canSwimDistance, int runDistance, int jumpHeight) {
        super(runDistance, "dog");
        this.canSwimDistance = canSwimDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void swim(int canSwimDistance) {
        if (this.canSwimDistance < canSwimDistance) {
            setOnDistance(false);
        }
    }

    @Override
    public void doJump(int height) {
        if (this.jumpHeight < height) {
            setOnDistance(false);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dog.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("canRunDistance=" + canRunDistance)
                .add("jumpHeight=" + jumpHeight)
                .add("canSwimDistance=" + canSwimDistance)
                .add("onDistance=" + onDistance)
                .toString();
    }
}
