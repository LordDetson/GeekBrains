import animal.Animal;
import animal.Cat;
import animal.Dog;
import animal.Duck;
import obstacle.Obstacle;
import obstacle.Road;
import obstacle.Wall;
import obstacle.Water;

public class Main {
    public static void main(String[] args) {
        Team animals = new Team("Animals",
                new Cat(5, 3),
                new Dog(5, 10, 2),
                new Duck(3, 10),
                new Dog(6, 8, 3)
        );

        Course course = new Course(new Road(8), new Water(3), new Wall(3));

        course.doIt(animals);

        for (Animal al : animals) {
            System.out.println(al);
        }

        System.out.println("\nAnimals on distance true");
        for (Animal al : animals.iterationByOnDistance()) {
            System.out.println(al);
        }
    }
}
