package webrunner.entity;

import java.awt.Rectangle;

/**
 * Base class for every object in the game world (buildings, player, obstacles).
 * It is abstract: you cannot create a plain GameObject, only its subclasses.
 * Each subclass must write its own draw() and update().
 */
public abstract class GameObject implements Drawable, Updatable {

    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /** The box around this object, used to check collisions. */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }

    /** True when the object has fully left the screen on the left side. */
    public boolean isOffScreen() {
        return getRight() < 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
