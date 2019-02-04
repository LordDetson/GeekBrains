package logic;

import javax.swing.*;
import java.awt.*;
import java.util.StringJoiner;

public class Plate {
    public static final Image SKIN_PLATE_1 = new ImageIcon("img/plate1.png").getImage();
    public static final Image SKIN_PLATE_2 = new ImageIcon("img/plate2.png").getImage();

    private int food;
    private Image skin = SKIN_PLATE_1;

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void dicreaseFood(int food) {
        this.food -= food;
        if (this.food == 0)
            changeSkin(SKIN_PLATE_1);
    }

    public void putFood(int food) {
        this.food += food;
        changeSkin(SKIN_PLATE_2);
    }

    public void changeSkin(Image skin) {
        this.skin = skin;
    }

    public void paint(Graphics2D g) {
            g.drawImage(skin, 130, 560, (int) (skin.getWidth(null) * 0.2),
                    (int) (skin.getHeight(null) * 0.2), null);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Plate.class.getSimpleName() + "[", "]")
                .add("food=" + food)
                .toString();
    }
}
