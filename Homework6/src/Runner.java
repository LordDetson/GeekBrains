import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        animals.addAll(Arrays.asList(
                new Cat("Cat1", LocalDate.of(2010, Month.APRIL, 21)),
                new Cat("Cat2", LocalDate.of(2011, Month.MAY, 22),
                        0.4, 0.18, 0.18, 2.5),
                new Cat("Cat3", LocalDate.of(2012, Month.AUGUST, 23),
                        0.4, 0.18, 0.18, 2.5,
                        122, 60, 3),
                new Dog("Dog1", LocalDate.of(2011, Month.JANUARY, 16)),
                new Dog("Dog2", LocalDate.of(2015, Month.FEBRUARY, 5),
                        0.6, 0.24, 0.18, 3.1),
                new Dog("Dog3", LocalDate.of(2005, Month.OCTOBER, 29),
                        0.6, 0.24, 0.18, 3.1,
                        400, 240, 1.2)

        ));

        animals.forEach(System.out::println);
    }
}
