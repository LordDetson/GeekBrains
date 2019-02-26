public class Cat extends Animal implements Jump {
    private int jumpHeight;

    public Cat(int canRunDistance, int jumpHeight) {
        super(canRunDistance, "cat");
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void doJump(int height) {
        if (this.jumpHeight < height) {
            setOnDistance(false);
        }
    }
}
