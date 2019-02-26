public class Duck extends Animal implements Swimable {
    int canSwimDistance;

    public Duck(int canRunDistance, int canSwimDistance) {
        super(canRunDistance, "duck");
        this.canSwimDistance = canSwimDistance;

    }

    @Override
    public void swim(int distance) {
        if (this.canSwimDistance < distance) {
            setOnDistance(false);
        }
    }
}