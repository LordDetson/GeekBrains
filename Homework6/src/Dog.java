import java.time.LocalDate;

public class Dog extends Animal {

    public static final double MAX_ROAD_OBSTACLE_LENGTH = 500;
    public static final double MAX_WATER_OBSTACLE_LENGTH = 0.5;
    public static final double MAX_HEIGHT_OBSTACLE_LENGTH = 10;

    public Dog(String name, LocalDate birthday) {
        super(name, birthday, MAX_ROAD_OBSTACLE_LENGTH, MAX_WATER_OBSTACLE_LENGTH,
                MAX_HEIGHT_OBSTACLE_LENGTH);
    }

    public Dog(String name, LocalDate birthday, double length, double width, double height, double mass) {
        super(name, birthday, length, width, height, mass, MAX_ROAD_OBSTACLE_LENGTH, MAX_WATER_OBSTACLE_LENGTH,
                MAX_HEIGHT_OBSTACLE_LENGTH);
    }

    public Dog(String name, LocalDate birthday, double maxRoadObstacleLength, double maxWaterObstacleLength,
               double maxHeightObstacleLength) {
        super(name, birthday, maxRoadObstacleLength, maxWaterObstacleLength, maxHeightObstacleLength);
    }

    public Dog(String name, LocalDate birthday, double length, double width, double height, double mass,
               double maxRoadObstacleLength, double maxWaterObstacleLength, double maxHeightObstacleLength) {
        super(name, birthday, length, width, height, mass, maxRoadObstacleLength, maxWaterObstacleLength,
                maxHeightObstacleLength);
    }
}
