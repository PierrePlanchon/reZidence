package utility;

import map.Element;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public static Coordinates getElemCoordinates(Element e) {
        return new Coordinates(e.getPosX(), e.getPosY());
    }
}
