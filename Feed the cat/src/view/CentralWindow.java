package view;

import javax.swing.*;
import java.awt.*;

import static view.WindowConstants.*;

public class CentralWindow extends JComponent {

    private Image image1;

    public CentralWindow() {
        image1 = new ImageIcon(PATH_IMG1).getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image1,0, 0, WINDOW_SIZE.width - LEFT_PANEL_SIZE.width, WINDOW_SIZE.height, null);
    }
}
