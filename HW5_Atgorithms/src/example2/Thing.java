package example2;

import java.util.Objects;
import java.util.StringJoiner;

public class Thing {
    private int cost;
    private int weight;

    public Thing(int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return cost == thing.cost &&
                weight == thing.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, weight);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Thing.class.getSimpleName() + "[", "]")
                .add("cost=" + cost)
                .add("weight=" + weight)
                .toString();
    }
}
