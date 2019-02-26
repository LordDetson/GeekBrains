import animal.Animal;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Team extends ArrayList<Animal> implements Iterable<Animal> {
    private static final int MAX_SIZE_PARTY = 4;
    private String name;

    public Team(String name, Animal... animals) {
        this.name = name;
        for (int i = 0; i < ((animals.length < MAX_SIZE_PARTY) ? animals.length : MAX_SIZE_PARTY); i++) {
            add(animals[i]);
        }
    }

    public Team(String name) {
        this.name = name;
    }

    public boolean add(Animal animal) {
        if (!isFull()) {
            super.add(animal);
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return size() == MAX_SIZE_PARTY;
    }

    public Iterable<Animal> iterationByOnDistance() {
        return stream()
                .filter(Animal::isOnDistance)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringJoiner s = new StringJoiner(", ", Team.class.getSimpleName() + "[", "]");
        for (Animal al : this) s.add(al.toString());
        return s.toString();
    }
}
