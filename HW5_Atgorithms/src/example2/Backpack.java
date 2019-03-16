package example2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Backpack {
    private List<Thing> things;
    private int maxVolume;

    public Backpack(int maxVolume) throws IncorrectVolume {
        if (maxVolume <= 0) {
            throw new IncorrectVolume("maxVolume = " + maxVolume + "; Expected maxVolume > 0");
        }
        this.maxVolume = maxVolume;
        things = new ArrayList<>();
    }

    public void putAll(List<Thing> things) {
        for (Thing thing : things) {
            put(thing);
        }
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public int getVolume() {
        return things.parallelStream()
                .map(Thing::getWeight)
                .collect(Collectors.toList()).parallelStream()
                .reduce((integer, integer2) -> integer + integer2).orElse(0);
    }

    public int getCost() {
        return things.parallelStream()
                .map(Thing::getCost)
                .collect(Collectors.toList()).parallelStream()
                .reduce((integer, integer2) -> integer + integer2).orElse(0);
    }

    public boolean isOverload(Thing thing) {
        return thing.getWeight() + getVolume() > getMaxVolume();
    }

    public int getCountThings() {
        return things.size();
    }

    public boolean isEmpty() {
        return things.isEmpty();
    }

    public boolean put(Thing thing) {
        if (!isOverload(thing)) {
            things.add(thing);
            return true;
        }
        return false;
    }

    public Thing takeOut(int index) {
        return things.remove(index);
    }

    public boolean takeOut(Thing thing) {
        return things.remove(thing);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Backpack.class.getSimpleName() + "[", "]")
                .add("cost=" + getCost())
                .add("volume=" + getVolume())
                .add("maxVolume=" + maxVolume)
                .add("things=" + things)
                .toString();
    }

}
