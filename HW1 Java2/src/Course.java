import animal.Animal;
import obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Collections;

public class Course extends ArrayList<Obstacle> {
    public Course(Obstacle... obstacles) {
        Collections.addAll(this, obstacles);
    }

    public void doIt(Team team) {
        for (Obstacle obstacle : this) {
            for (Animal animal : team) {
                obstacle.doIt(animal);
            }
        }
    }
}
