package example1;

public class CalcPow {
    private static int pow(int value, int power) {
        if (power == 1) {
            return value;
        }
        return pow(value, --power) * value;
    }
    public static void main(String[] args) {
        System.out.println(pow(2, 4));
    }
}
