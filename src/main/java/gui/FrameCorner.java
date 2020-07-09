package gui;

public enum FrameCorner {

    UPPER_LEFT(0, 0),
    UPPER_RIGHT(400, 0),
    BOTTOM_LEFT(0, 400),
    BOTTOM_RIGHT(400, 400);

    private final int x;
    private final int y;

    FrameCorner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
