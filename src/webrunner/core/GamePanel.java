package webrunner.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The drawing area of the game and the home of the game loop.
 * A Swing Timer "ticks" about 60 times per second. On every tick the game
 * is updated and then repainted. This class should only run the loop and
 * paint; real game logic will live in other classes.
 */
public class GamePanel extends JPanel implements ActionListener {

    private static final Color SKY_COLOR = new Color(12, 18, 45);
    private static final Color MOON_COLOR = new Color(245, 240, 210);
    private static final Color ROOF_COLOR = new Color(45, 45, 60);
    private static final Color ROOF_EDGE_COLOR = new Color(90, 90, 110);

    /** Star positions as {x, y} pairs. Fixed, so the stars do not flicker. */
    private static final int[][] STARS = {
        {60, 40}, {150, 110}, {230, 60}, {320, 150}, {410, 30},
        {480, 120}, {560, 70}, {640, 160}, {720, 50}, {870, 140},
        {900, 60}, {110, 200}, {380, 220}, {600, 230}, {820, 210}
    };

    private final Timer timer;
    private int frameCount;

    /** Sets up the panel size and starts the game loop. */
    public GamePanel() {
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setBackground(SKY_COLOR);
        setDoubleBuffered(true); // draw off-screen first, so there is no flicker

        timer = new Timer(Constants.FRAME_DELAY, this);
        timer.start();
    }

    /**
     * Called by the Timer on every tick. This is one turn of the game loop.
     *
     * @param e the timer event (not used)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint(); // asks Swing to call paintComponent() soon
    }

    /** Moves the game forward by one frame. Game objects will be updated here later. */
    private void update() {
        frameCount++;
    }

    /**
     * Draws the whole screen. Swing calls this after repaint().
     *
     * @param g the graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clears the old frame
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawSky(g2);
        drawRoof(g2);
        drawFrameCounter(g2);
    }

    /** Draws the stars and the moon. */
    private void drawSky(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        for (int[] star : STARS) {
            g2.fillOval(star[0], star[1], 3, 3);
        }

        g2.setColor(MOON_COLOR);
        g2.fillOval(780, 50, 70, 70);
    }

    /** Draws the flat rooftop the hero will run on. */
    private void drawRoof(Graphics2D g2) {
        g2.setColor(ROOF_COLOR);
        g2.fillRect(0, Constants.GROUND_Y, Constants.WIDTH, Constants.HEIGHT - Constants.GROUND_Y);

        g2.setColor(ROOF_EDGE_COLOR);
        g2.fillRect(0, Constants.GROUND_Y, Constants.WIDTH, 6);
    }

    /** Temporary: shows the frame number to prove the loop is running. */
    private void drawFrameCounter(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("Frame: " + frameCount, 10, 20);
    }
}
