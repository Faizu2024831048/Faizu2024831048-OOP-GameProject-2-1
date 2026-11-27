package webrunner.obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class Antenna extends Obstacle {

    private static final Color METAL_COLOR = new Color(170, 185, 205);

    public Antenna(int x, int roofY) {
        super(x, roofY - 56, 72, 26);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = getX();
        int y = getY();

        g.setColor(METAL_COLOR);
        g.fillRect(x + 5, y + 17, 3, 39);
        g.fillRect(x + 64, y + 17, 3, 39);
        g.fillRect(x, y + 12, getWidth(), 5);
        g.fillRect(x + 12, y + 5, 48, 4);
        g.fillRect(x + 21, y, 30, 3);
    }
}
