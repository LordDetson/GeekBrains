package view;

import logic.Cat;
import logic.Plate;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static view.WindowConstants.*;

public class MainWindow extends JFrame {

    private Random random = new Random();
    private CentralWindow centralWindow = new CentralWindow();
    private LeftPanel leftPanel = new LeftPanel();
    private Cat cat;
    private Plate plate;

    public MainWindow() throws HeadlessException {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(new Rectangle(WINDOW_POINT, WINDOW_SIZE));
        setLayout(new BorderLayout());

        cat = new Cat("Феликс");
        plate = new Plate();
        centralWindow.setCat(cat);
        centralWindow.setPlate(plate);
        leftPanel.run(plate);
        add(centralWindow, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        setResizable(false);
        setVisible(true);
    }

    public void run() {
        while (true) {
            plate.changeSkin(Plate.SKIN_PLATE_1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cat.setAppetite(random.nextInt(20));
            cat.speak("Я хочу кушать " + cat.getAppetite(), centralWindow);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cat.eat(plate, centralWindow);
        }
    }
}
