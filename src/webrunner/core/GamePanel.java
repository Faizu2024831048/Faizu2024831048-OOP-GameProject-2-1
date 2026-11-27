package webrunner.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import webrunner.entity.Building;
import webrunner.entity.Player;
import webrunner.obstacle.Antenna;
import webrunner.obstacle.Drone;
import webrunner.obstacle.FlowerTub;
import webrunner.obstacle.Obstacle;

public class GamePanel extends JPanel implements ActionListener {

    private static final int OBSTACLE_CHANCE = 65;

    private static final int[][] STARS = {
        {60, 40}, {150, 110}, {230, 60}, {320, 150}, {410, 30},
        {480, 120}, {560, 70}, {640, 160}, {720, 50}, {870, 140},
        {900, 30}, {110, 200}, {380, 220}, {600, 230}, {820, 210},
        {30, 120}, {270, 190}, {520, 20}, {680, 110}, {940, 180}
    };

    private final ArrayList<Building> buildings = new ArrayList<>();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final Random random = new Random();
    private final Player player = new Player();

    public GamePanel() {
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setBackground(new Color(12, 18, 45));
        setFocusable(true);
        addKeyListener(new InputHandler(player));

        startGame();

        new Timer(Constants.FRAME_DELAY, this).start();
    }

    private void startGame() {
        buildings.clear();
        obstacles.clear();
        Building first = new Building(0, 700, 150);
        buildings.add(first);
        obstacles.add(new FlowerTub(420, first.getY()));
        player.reset(first.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        for (Building b : buildings) {
            b.update();
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
        buildings.removeIf(b -> b.isOffScreen());
        obstacles.removeIf(obstacle -> obstacle.isOffScreen());
        addBuildingIfNeeded();

        player.update();
        player.checkBuildings(buildings);
        player.checkObstacles(obstacles);
        if (player.isCrashed()) {
            startGame();
        }
    }

    private void addBuildingIfNeeded() {
        Building last = buildings.get(buildings.size() - 1);
        if (last.getRight() > Constants.WIDTH) {
            return;
        }

        int lastHeight = last.getHeight();
        boolean makeGap = random.nextInt(100) < Constants.GAP_CHANCE;
        if (lastHeight - Constants.MIN_STEP_DOWN < Constants.MIN_BUILDING_HEIGHT) {
            makeGap = true;
        }

        int gap = 0;
        int height;
        if (makeGap) {
            gap = randomBetween(Constants.MIN_GAP, Constants.MAX_GAP);
            int tallest = Math.min(Constants.MAX_BUILDING_HEIGHT, lastHeight + Constants.MAX_STEP_UP);
            height = randomBetween(Constants.MIN_BUILDING_HEIGHT, tallest);
        } else {
            height = randomBetween(Constants.MIN_BUILDING_HEIGHT, lastHeight - Constants.MIN_STEP_DOWN);
        }
        int width = randomBetween(Constants.MIN_BUILDING_WIDTH, Constants.MAX_BUILDING_WIDTH);

        Building next = new Building(last.getRight() + gap, width, height);
        buildings.add(next);
        addObstacle(next);
    }

    private void addObstacle(Building building) {
        if (building.getWidth() < 360 || random.nextInt(100) >= OBSTACLE_CHANCE) {
            return;
        }

        int x = building.getX() + randomBetween(160, building.getWidth() - 100);
        switch (random.nextInt(3)) {
            case 0:
                obstacles.add(new FlowerTub(x, building.getY()));
                break;
            case 1:
                obstacles.add(new Antenna(x, building.getY()));
                break;
            default:
                obstacles.add(new Drone(x, building.getY()));
                break;
        }
    }

    private int randomBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        for (int[] star : STARS) {
            g2.fillOval(star[0], star[1], 3, 3);
        }

        g2.setColor(new Color(245, 240, 210));
        g2.fillOval(780, 50, 70, 70);

        for (Building b : buildings) {
            b.draw(g2);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g2);
        }
        player.draw(g2);
    }
}