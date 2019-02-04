package view;

import logic.Plate;

import javax.swing.*;

import java.awt.*;

import static view.WindowConstants.*;

public class LeftPanel extends JPanel {

    private JLabel label = new JLabel("Количество еды");
    private JTextField textField = new JTextField();
    private JButton button = new JButton("Положить");

    public LeftPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(LEFT_PANEL_SIZE);

        setVisible(true);
    }

    public void run(Plate plate) {
        add(label);
        textField.setMaximumSize(new Dimension(200, 25));
        add(textField);
        button.addActionListener(e -> {
            plate.putFood(Integer.valueOf(textField.getText()));
        });
        add(button);
    }
}
