import java.time.LocalDate;
import java.util.StringJoiner;

public abstract class Animal {

    private String name;
    private LocalDate birthday;
    private double length;
    private double width;
    private double height;
    private double mass;
    private double maxRoadObstacleLength;
    private double maxWaterObstacleLength;
    private double maxHeightObstacleLength;

    protected Animal(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    protected Animal(String name, LocalDate birthday, double length, double width, double height, double mass) {
        this.name = name;
        this.birthday = birthday;
        this.length = length;
        this.width = width;
        this.height = height;
        this.mass = mass;
    }

    protected Animal(String name, LocalDate birthday, double maxRoadObstacleLength, double maxWaterObstacleLength, double maxHeightObstacleLength) {
        this.name = name;
        if (!setBirthday(birthday)) {
            this.birthday = LocalDate.now();
        }
        setMaxRoadObstacleLength(maxRoadObstacleLength);
        setMaxWaterObstacleLength(maxWaterObstacleLength);
        setMaxHeightObstacleLength(maxHeightObstacleLength);
    }

    protected Animal(String name, LocalDate birthday, double length, double width, double height, double mass,
                     double maxRoadObstacleLength, double maxWaterObstacleLength, double maxHeightObstacleLength) {
        this.name = name;
        if (!setBirthday(birthday)) {
            this.birthday = LocalDate.now();
        }
        setLength(length);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setMaxRoadObstacleLength(maxRoadObstacleLength);
        setMaxWaterObstacleLength(maxWaterObstacleLength);
        setMaxHeightObstacleLength(maxHeightObstacleLength);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean setBirthday(LocalDate birthday) {
        if (LocalDate.now().isAfter(birthday)) {
            this.birthday = birthday;
            return true;
        }
        return false;
    }

    public double getLength() {
        return length;
    }

    public boolean setLength(double length) {
        if (isGreaterThanZero(length)) {
            this.length = length;
            return true;
        }
        return false;
    }

    public double getWidth() {
        return width;
    }

    public boolean setWidth(double width) {
        if (isGreaterThanZero(width)) {
            this.width = width;
            return true;
        }
        return false;
    }

    public double getHeight() {
        return height;
    }

    public boolean setHeight(double height) {
        if (isGreaterThanZero(height)) {
            this.height = height;
            return true;
        }
        return false;
    }

    public double getMass() {
        return mass;
    }

    public boolean setMass(double mass) {
        if (isGreaterThanZero(mass)) {
            this.mass = mass;
            return true;
        }
        return false;
    }

    public double getMaxRoadObstacleLength() {
        return maxRoadObstacleLength;
    }

    public boolean setMaxRoadObstacleLength(double maxRoadObstacleLength) {
        if (isGreaterThanZero(maxRoadObstacleLength)) {
            this.maxRoadObstacleLength = maxRoadObstacleLength;
            return true;
        }
        return false;
    }

    public double getMaxWaterObstacleLength() {
        return maxWaterObstacleLength;
    }

    public void setMaxWaterObstacleLength(double maxWaterObstacleLength) {
        if (isGreaterThanZero(maxWaterObstacleLength))
            this.maxWaterObstacleLength = maxWaterObstacleLength;
    }

    public double getMaxHeightObstacleLength() {
        return maxHeightObstacleLength;
    }

    public void setMaxHeightObstacleLength(double maxHeightObstacleLength) {
        if (isGreaterThanZero(maxHeightObstacleLength))
            this.maxHeightObstacleLength = maxHeightObstacleLength;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public boolean run(double length) {
        return isGreaterThanZero(length) && maxRoadObstacleLength >= length;
    }

    public boolean swim(double length) {
        return isGreaterThanZero(length) && maxWaterObstacleLength >= length;
    }

    public boolean jump(double height) {
        return isGreaterThanZero(height) && maxHeightObstacleLength >= height;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Animal.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthday=" + birthday)
                .add("length=" + isTheParameterDefined(length))
                .add("width=" + isTheParameterDefined(width))
                .add("height=" + isTheParameterDefined(height))
                .add("mass=" + isTheParameterDefined(mass))
                .add("maxRoadObstacleLength=" + isTheParameterDefined(maxRoadObstacleLength))
                .add("maxWaterObstacleLength=" + isTheParameterDefined(maxWaterObstacleLength))
                .add("maxHeightObstacleLength=" + isTheParameterDefined(maxHeightObstacleLength))
                .toString();
    }

    private static boolean isGreaterThanZero(double value) {
        return value > 0;
    }

    private static Object isTheParameterDefined(double value) {
        return isGreaterThanZero(value) ? value : "Не определено";
    }
}
