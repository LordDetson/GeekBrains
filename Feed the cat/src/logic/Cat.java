package logic;

import javax.swing.*;
import java.awt.*;
import java.util.StringJoiner;

public class Cat {
    public static final Image SKIN_CAT_1 = new ImageIcon("img/skinCat1.png").getImage();
    public static final Image SKIN_CAT_2 = new ImageIcon("img/skinCat2.png").getImage();
    public static final Image SKIN_SPEAK = new ImageIcon("img/speak.png").getImage();

    private String name;
    private int appetite;
    private Image skin = SKIN_CAT_1;
    private String speak;
    private boolean isSpeak = false;

    public Cat(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public void speak(String s, JPanel panel) {
        this.speak = s;
        isSpeak = true;
        panel.repaint();
    }

    public void eat(Plate plate, JPanel panel) {
        if (plate.getFood() >= appetite) {
            isSpeak = false;
            changeSkin(SKIN_CAT_2);
            panel.repaint();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            plate.dicreaseFood(appetite);
            appetite = 0;

            changeSkin(SKIN_CAT_1);
            panel.repaint();
        } else {
            speak("Не хватает! Добавь " + (appetite - plate.getFood()), panel);
        }
    }

    public void changeSkin(Image skin) {
        this.skin = skin;
    }

    public void paint(Graphics2D g) {
        if (skin.equals(SKIN_CAT_1)) {
            g.drawImage(SKIN_CAT_1, 500, 350, 300, 300, null);
        } else {
            g.drawImage(SKIN_CAT_2, 100, 425, (int) (SKIN_CAT_2.getWidth(null) * 0.26),
                    (int) (SKIN_CAT_2.getHeight(null) * 0.26), null);
        }
    }

    public void paintSpeak(Graphics2D g) {
        if (isSpeak) {
            g.drawImage(SKIN_SPEAK, 700, 270, (int) (SKIN_SPEAK.getWidth(null) * 0.8),
                    (int) (SKIN_SPEAK.getHeight(null) * 0.8), null);
            g.drawString(speak, 760, 340);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cat.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("appetite=" + appetite)
                .toString();
    }
}
