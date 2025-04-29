package net.coder.io.res;

import java.awt.*;

public final class Values {

    public static final String FILE_PATH;

    public static final Font DEFAULT_FONT;

    public static final Color DEFAULT_COLOUR;

    static {
        FILE_PATH = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\YouTube\\";
        DEFAULT_FONT = new Font("Trebuchet MS", Font.PLAIN, 12);
        DEFAULT_COLOUR = new Color(238, 238, 238);
    }

    private Values() {
    }

}
