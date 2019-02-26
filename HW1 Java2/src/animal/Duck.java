package animal;

import action.Swimable;

import java.util.StringJoiner;

public class Duck extends Animal implements Swimable {
    int canSwimDistance;

    public Duck(int canRunDistance, int canSwimDistance) {
        super(canRunDistance, "duck");
        this.canSwimDistance = canSwimDistance;

    }

    @Override
    public void swim(int distance) {
        if (this.canSwimDistance < distance) {
            setOnDistance(false);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Duck.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("canRunDistance=" + canRunDistance)
                .add("canSwimDistance=" + canSwimDistance)
                .add("onDistance=" + onDistance)
                .toString();
    }
}