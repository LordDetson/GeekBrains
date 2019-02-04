package view;

import java.awt.*;

public class WindowConstants {
    private static final int WINDOW_HEIGHT = 720;
    private static final int WINDOW_WIDTH = 1080;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static final String TITLE_OF_PROGRAM = "Feed the cat";
    public static final Dimension WINDOW_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    public static final Point WINDOW_POINT = new Point((int)(SCREEN_SIZE.getWidth() / 2 - WINDOW_SIZE.getWidth() / 2),
            (int)(SCREEN_SIZE.getHeight() / 2 - WINDOW_SIZE.getHeight() / 2));
    public static final Dimension LEFT_PANEL_SIZE = new Dimension(100, WINDOW_SIZE.height);
    public static final Dimension CENTRAL_WINDOW_SIZE = new Dimension(WINDOW_SIZE.width - LEFT_PANEL_SIZE.width,
            WINDOW_SIZE.height);

    public static final String PATH_IMG1 = "img/img1.jpg";
}
