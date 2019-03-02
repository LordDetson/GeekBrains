package animal;

import action.Jump;

import java.util.StringJoiner;

public class Cat extends Animal implements Jump {
    int jumpHeight;

    public Cat(int canRunDistance, int jumpHeight) {
        super(canRunDistance, "cat");
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void doJump(int height) {
        if (this.jumpHeight < height) {
            setOnDistance(false);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cat.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("canRunDistance=" + canRunDistance)
                .add("jumpHeight=" + jumpHeight)
                .add("onDistance=" + onDistance)
                .toString();
    }
}
