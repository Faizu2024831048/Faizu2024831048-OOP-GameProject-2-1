package webrunner.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import webrunner.core.Constants;

public class Player extends GameObject {

    private static final Color SUIT_RED = new Color(210, 30, 45);
    private static final Color SUIT_BLUE = new Color(30, 70, 190);

    private double velocityY;
    private PlayerState state;
    private boolean crashed;
    private int stepTimer;

    public Player() {
        super(Constants.PLAYER_X, 0, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }

    public void reset(int groundY) {
        setHeight(Constants.PLAYER_HEIGHT);
        setY(groundY - Constants.PLAYER_HEIGHT);
        velocityY = 0;
        state = PlayerState.RUNNING;
        crashed = false;
    }

    public void jump() {
        if (state == PlayerState.RUNNING) {
            velocityY = -Constants.JUMP_SPEED;
            state = PlayerState.JUMPING;
        }
    }

    public void duck() {
        if (state == PlayerState.RUNNING) {
            state = PlayerState.DUCKING;
            setY(getY() + Constants.PLAYER_HEIGHT - Constants.PLAYER_DUCK_HEIGHT);
            setHeight(Constants.PLAYER_DUCK_HEIGHT);
        }
    }

    public void standUp() {
        if (state == PlayerState.DUCKING) {
            state = PlayerState.RUNNING;
            setY(getY() - Constants.PLAYER_HEIGHT + Constants.PLAYER_DUCK_HEIGHT);
            setHeight(Constants.PLAYER_HEIGHT);
        }
    }

    @Override
    public void update() {
        velocityY += Constants.GRAVITY;
        setY((int) (getY() + velocityY));
        stepTimer++;
    }

    public void checkBuildings(List<Building> buildings) {
        boolean onGround = false;

        for (Building b : buildings) {
            boolean aboveIt = getRight() > b.getX() && getX() < b.getRight();
            if (!aboveIt) {
                continue;
            }

            int previousBottom = (int) (getBottom() - velocityY);
            if (velocityY >= 0 && getBottom() >= b.getY() && previousBottom <= b.getY() + 1) {
                setY(b.getY() - getHeight());
                velocityY = 0;
                onGround = true;
            } else if (getBottom() > b.getY() + 10) {
                crashed = true;
            }
        }

        if (onGround && state == PlayerState.JUMPING) {
            state = PlayerState.RUNNING;
        } else if (!onGround && state == PlayerState.RUNNING) {
            state = PlayerState.JUMPING;
        }

        if (getY() > Constants.HEIGHT) {
            crashed = true;
        }
    }

    public boolean isCrashed() {
        return crashed;
    }

    @Override
    public void draw(Graphics2D g) {
        int x = getX();
        int y = getY();

        if (state == PlayerState.DUCKING) {
            g.setColor(SUIT_BLUE);
            g.fillRect(x, y + 20, 30, 10);
            g.setColor(SUIT_RED);
            g.fillRect(x, y + 8, 24, 14);
            g.fillOval(x + 14, y, 18, 18);
            g.setColor(Color.WHITE);
            g.fillOval(x + 22, y + 5, 6, 4);
            return;
        }

        int legShift = 0;
        if (state == PlayerState.RUNNING && (stepTimer / 6) % 2 == 0) {
            legShift = 4;
        }

        g.setColor(SUIT_BLUE);
        g.fillRect(x + 5 + legShift, y + 34, 8, 16);
        g.fillRect(x + 17 - legShift, y + 34, 8, 16);
        g.setColor(SUIT_RED);
        g.fillRect(x + 4, y + 16, 22, 20);
        g.fillOval(x + 6, y, 18, 18);
        g.setColor(Color.WHITE);
        g.fillOval(x + 9, y + 6, 5, 4);
        g.fillOval(x + 16, y + 6, 5, 4);
    }
}
