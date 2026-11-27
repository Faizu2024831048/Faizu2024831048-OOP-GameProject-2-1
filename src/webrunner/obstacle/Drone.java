package webrunner.obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class Drone extends Obstacle {

    private static final Color BODY_COLOR = new Color(105, 125, 155);
    private static final Color LIGHT_COLOR = new Color(255, 85, 75);

    public Drone(int x, int roofY) {
        super(x, roofY - 58, 58, 28);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = getX();
        int y = getY();

        g.setColor(BODY_COLOR);
        g.fillRoundRect(x + 10, y + 8, 38, 15, 8, 8);
        g.fillRect(x + 2, y + 5, 54, 3);
        g.fillRect(x + 2, y + 23, 54, 3);
        g.fillOval(x, y + 3, 9, 6);
        g.fillOval(x + 49, y + 3, 9, 6);
        g.fillOval(x, y + 21, 9, 6);
        g.fillOval(x + 49, y + 21, 9, 6);

        g.setColor(LIGHT_COLOR);
        g.fillOval(x + 27, y + 13, 5, 5);
    }
}
