package webrunner.obstacle;

import webrunner.core.Constants;
import webrunner.entity.GameObject;

public abstract class Obstacle extends GameObject {

    protected Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public final void update() {
        setX(getX() - Constants.SPEED);
    }
}
