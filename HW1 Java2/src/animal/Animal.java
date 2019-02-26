package animal;

import action.Run;

import java.util.StringJoiner;

public class Animal implements Run {

    String name;
    int canRunDistance;
    boolean onDistance = true;

    public Animal(int canRunDistance, String name) {
        this.canRunDistance = canRunDistance;
        this.name = name;
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    public Animal setOnDistance(boolean onDistance) {
        this.onDistance = onDistance;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run(int dist) {
        if (this.canRunDistance < dist) {
            this.onDistance = false;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Animal.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("canRunDistance=" + canRunDistance)
                .add("onDistance=" + onDistance)
                .toString();
    }
}
