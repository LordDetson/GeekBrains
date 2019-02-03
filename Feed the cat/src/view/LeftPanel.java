package view;

import javax.swing.*;

import static view.WindowConstants.*;

public class LeftPanel extends JPanel {
    public LeftPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(LEFT_PANEL_SIZE);
        add(new JButton("JButton"));
        setVisible(true);
    }
}
