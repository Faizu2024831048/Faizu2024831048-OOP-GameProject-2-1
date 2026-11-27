package webrunner.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import webrunner.entity.Building;

/**
 * The game screen. Holds the game loop: every tick it updates, then repaints.
 */
public class GamePanel extends JPanel implements ActionListener {

    private static final int[][] STARS = {
        {60, 40}, {150, 110}, {230, 60}, {320, 150}, {410, 30},
        {480, 120}, {560, 70}, {640, 160}, {720, 50}, {870, 140},
        {900, 30}, {110, 200}, {380, 220}, {600, 230}, {820, 210},
        {30, 120}, {270, 190}, {520, 20}, {680, 110}, {940, 180}
    };

    private final ArrayList<Building> buildings = new ArrayList<>();
    private final Random random = new Random();

    public GamePanel() {
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setBackground(new Color(12, 18, 45));

        buildings.add(new Building(0, 500, 150));

        new Timer(Constants.FRAME_DELAY, this).start();
    }

    /** Game loop: runs about 60 times per second. */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        for (Building b : buildings) {
            b.update();
        }
        buildings.removeIf(b -> b.isOffScreen());
        addBuildingIfNeeded();
    }

    /** When the last building's right edge comes on screen, add a new one after it. */
    private void addBuildingIfNeeded() {
        Building last = buildings.get(buildings.size() - 1);
        if (last.getRight() > Constants.WIDTH) {
            return;
        }

        int gap = 0;
        if (random.nextInt(100) < Constants.GAP_CHANCE) {
            gap = randomBetween(Constants.MIN_GAP, Constants.MAX_GAP);
        }
        int width = randomBetween(Constants.MIN_BUILDING_WIDTH, Constants.MAX_BUILDING_WIDTH);
        int height = randomBetween(Constants.MIN_BUILDING_HEIGHT, Constants.MAX_BUILDING_HEIGHT);

        buildings.add(new Building(last.getRight() + gap, width, height));
    }

    private int randomBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        for (int[] star : STARS) {
            g2.fillOval(star[0], star[1], 3, 3);
        }

        g2.setColor(new Color(245, 240, 210));
        g2.fillOval(780, 50, 70, 70);

        for (Building b : buildings) {
            b.draw(g2);
        }
    }
}
