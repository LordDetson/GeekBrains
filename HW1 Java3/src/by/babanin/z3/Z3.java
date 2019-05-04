package by.babanin.z3;

import java.util.Random;

public class Z3 {
    public static void main(String[] args) {
        BoxWithFruits<Apple> appleBox = new BoxWithFruits<>();
        BoxWithFruits<Orange> orangeBox = new BoxWithFruits<>();
        int border = 11;
        Random random = new Random();
        for (int i = 0; i < random.nextInt(border); i++) {
            appleBox.add(new Apple(random.nextInt(border)));
            orangeBox.add(new Orange(random.nextInt(border)));
        }
        System.out.println("appleBoxWight = " + appleBox.getWeight());
        System.out.println("orangeBoxWight = " + orangeBox.getWeight());
        System.out.println("appleBox.compareByWeight(orangeBox)" + appleBox.compareByWeight(orangeBox));
        BoxWithFruits<Apple> appleBox2 = new BoxWithFruits<>();
        appleBox.removeInBox(appleBox2);
        System.out.println("appleBoxWight = " + appleBox.getWeight());
        System.out.println("appleBox2Wight = " + appleBox2.getWeight());
    }
}
