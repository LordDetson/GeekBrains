import java.time.LocalDate;
import java.util.StringJoiner;

public abstract class Animal {

    private static class ZeroComparator {
        static boolean isGreaterThanZero(double value) {
            return value > 0;
        }
    }

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
        if (!setBirthday(birthday)) {
            this.birthday = LocalDate.now();
        }
    }

    protected Animal(String name, LocalDate birthday, double length, double width, double height, double mass) {
        this.name = name;
        if (!setBirthday(birthday)) {
            this.birthday = LocalDate.now();
        }
        setLength(length);
        setWidth(width);
        setHeight(height);
        setMass(mass);
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
        if (ZeroComparator.isGreaterThanZero(length)) {
            this.length = length;
            return true;
        }
        return false;
    }

    public double getWidth() {
        return width;
    }

    public boolean setWidth(double width) {
        if (ZeroComparator.isGreaterThanZero(width)) {
            this.width = width;
            return true;
        }
        return false;
    }

    public double getHeight() {
        return height;
    }

    public boolean setHeight(double height) {
        if (ZeroComparator.isGreaterThanZero(height)) {
            this.height = height;
            return true;
        }
        return false;
    }

    public double getMass() {
        return mass;
    }

    public boolean setMass(double mass) {
        if (ZeroComparator.isGreaterThanZero(mass)) {
            this.mass = mass;
            return true;
        }
        return false;
    }

    public double getMaxRoadObstacleLength() {
        return maxRoadObstacleLength;
    }

    public boolean setMaxRoadObstacleLength(double maxRoadObstacleLength) {
        if (ZeroComparator.isGreaterThanZero(maxRoadObstacleLength)) {
            this.maxRoadObstacleLength = maxRoadObstacleLength;
            return true;
        }
        return false;
    }

    public double getMaxWaterObstacleLength() {
        return maxWaterObstacleLength;
    }

    public boolean setMaxWaterObstacleLength(double maxWaterObstacleLength) {
        if (ZeroComparator.isGreaterThanZero(maxWaterObstacleLength)) {
            this.maxWaterObstacleLength = maxWaterObstacleLength;
            return true;
        }
        return false;
    }

    public double getMaxHeightObstacleLength() {
        return maxHeightObstacleLength;
    }

    public boolean setMaxHeightObstacleLength(double maxHeightObstacleLength) {
        if (ZeroComparator.isGreaterThanZero(maxHeightObstacleLength)) {
            this.maxHeightObstacleLength = maxHeightObstacleLength;
            return true;
        }
        return false;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public boolean run(double length) {
        return ZeroComparator.isGreaterThanZero(length) && maxRoadObstacleLength >= length;
    }

    public boolean swim(double length) {
        return ZeroComparator.isGreaterThanZero(length) && maxWaterObstacleLength >= length;
    }

    public boolean jump(double height) {
        return ZeroComparator.isGreaterThanZero(height) && maxHeightObstacleLength >= height;
    }

    @Override
    public String toString() {
        class Defineder {
            Object getParameterDefined(double value) {
                return ZeroComparator.isGreaterThanZero(value) ? value : "Не определено";
            }
        }
        Defineder defineder = new Defineder();
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthday=" + birthday)
                .add("length=" + defineder.getParameterDefined(length))
                .add("width=" + defineder.getParameterDefined(width))
                .add("height=" + defineder.getParameterDefined(height))
                .add("mass=" + defineder.getParameterDefined(mass))
                .add("maxRoadObstacleLength=" + defineder.getParameterDefined(maxRoadObstacleLength))
                .add("maxWaterObstacleLength=" + defineder.getParameterDefined(maxWaterObstacleLength))
                .add("maxHeightObstacleLength=" + defineder.getParameterDefined(maxHeightObstacleLength))
                .toString();
    }
}
