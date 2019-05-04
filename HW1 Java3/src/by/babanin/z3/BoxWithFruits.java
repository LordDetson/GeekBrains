package by.babanin.z3;

import java.util.*;

public class BoxWithFruits<T extends Fruit> {
    private List<T> fruits;

    public BoxWithFruits() {
        fruits = new ArrayList<>();
    }

    public float getWeight() {
        Optional<Float> reduce = fruits.stream()
                .map(Fruit::getWeight)
                .reduce((aFloat, aFloat2) -> aFloat + aFloat2);
        return reduce.orElse(0f);
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void addAll(Collection<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public void addAll(T... fruits) {
        Collections.addAll(this.fruits, fruits);
    }

    public void removeInBox(BoxWithFruits<T> box) {
        box.addAll(fruits);
        fruits = new ArrayList<>();
    }

    public boolean compareByWeight(BoxWithFruits<?> b2) {
        if(getWeight() == b2.getWeight()) return true;
        return false;
    }
}
