package view;

import logic.Cat;
import logic.Plate;

import javax.swing.*;
import java.awt.*;

import static view.WindowConstants.*;

public class CentralWindow extends JPanel {

    private Image image1;
    private Cat cat;
    private Plate plate;

    public CentralWindow() {
        setPreferredSize(CENTRAL_WINDOW_SIZE);
        image1 = new ImageIcon(PATH_IMG1).getImage();

    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image1, 0, -500, CENTRAL_WINDOW_SIZE.width + 500,
                CENTRAL_WINDOW_SIZE.height + 500, null);
        cat.paint(g2);
        cat.paintSpeak(g2);
        plate.paint(g2);
    }
}
