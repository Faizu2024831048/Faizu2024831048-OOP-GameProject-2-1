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

    /** Private constructor: this class only holds constants, so no objects are made. */
    private Constants() {
    }
}
