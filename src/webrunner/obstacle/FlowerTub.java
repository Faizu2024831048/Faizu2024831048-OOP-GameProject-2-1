package webrunner.obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class FlowerTub extends Obstacle {

    private static final Color TUB_COLOR = new Color(170, 75, 45);
    private static final Color LEAF_COLOR = new Color(55, 150, 70);
    private static final Color FLOWER_COLOR = new Color(255, 190, 65);

    public FlowerTub(int x, int roofY) {
        super(x, roofY - 38, 42, 38);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = getX();
        int y = getY();

        g.setColor(LEAF_COLOR);
        g.drawLine(x + 21, y + 15, x + 12, y + 2);
        g.drawLine(x + 21, y + 15, x + 30, y + 1);
        g.drawLine(x + 21, y + 14, x + 21, y);
        g.setColor(FLOWER_COLOR);
        g.fillOval(x + 8, y - 2, 9, 9);
        g.fillOval(x + 17, y - 5, 9, 9);
        g.fillOval(x + 26, y - 2, 9, 9);

        g.setColor(TUB_COLOR);
        int[] tubX = {x + 3, x + 39, x + 34, x + 8};
        int[] tubY = {y + 17, y + 17, y + 38, y + 38};
        g.fillPolygon(tubX, tubY, 4);
        g.setColor(TUB_COLOR.brighter());
        g.fillRect(x + 2, y + 15, 38, 5);
    }
}
