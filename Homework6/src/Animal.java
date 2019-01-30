import java.time.LocalDate;
import java.util.StringJoiner;

public abstract class Animal {
    private String name;
    private LocalDate birthday;
    private double length;
    private double width;
    private double height;
    private double mass;

    Animal(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    Animal(String name, LocalDate birthday, double length, double width, double height, double mass) {
        this.name = name;
        this.birthday = birthday;
        this.length = length;
        this.width = width;
        this.height = height;
        this.mass = mass;
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length >= 0)
            this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width >= 0)
            this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height >= 0)
            this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        if (mass >= 0)
            this.mass = mass;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Animal.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthday=" + birthday)
                .add("length=" + length)
                .add("width=" + width)
                .add("height=" + height)
                .add("mass=" + mass)
                .toString();
    }
}
