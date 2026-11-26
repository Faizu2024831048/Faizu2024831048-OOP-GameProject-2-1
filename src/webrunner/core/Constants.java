package webrunner.core;

/**
 * Holds fixed values used across the game, so there are no "magic numbers"
 * scattered in the code. To change the window size, change it here only.
 */
public final class Constants {

    /** Title shown on the window. */
    public static final String GAME_TITLE = "Web Runner";

    /** Width of the game area in pixels. */
    public static final int WIDTH = 960;

    /** Height of the game area in pixels. */
    public static final int HEIGHT = 540;

    /** How many times per second the game updates and redraws. */
    public static final int FPS = 60;

    /** Milliseconds between two frames (1000 ms / 60 frames = about 16 ms). */
    public static final int FRAME_DELAY = 1000 / FPS;

    /** Y position of the rooftop surface (the hero runs on this line). */
    public static final int GROUND_Y = 440;

    /** Private constructor: this class only holds constants, so no objects are made. */
    private Constants() {
    }
}
