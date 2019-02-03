package view;

import javax.swing.*;
import java.awt.*;

import static view.WindowConstants.*;

public class MainWindow extends JFrame {

    private CentralWindow centralWindow = new CentralWindow();
    private LeftPanel leftPanel = new LeftPanel();

    public MainWindow() throws HeadlessException {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(new Rectangle(WINDOW_POINT, WINDOW_SIZE));
        setLayout(new BorderLayout());

        add(centralWindow, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        setResizable(false);
        setVisible(true);
    }
}
