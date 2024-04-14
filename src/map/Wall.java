package map;

import utility.Info;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y, "\033[48;2;69;69;69m", "   ");
        this.addInfo(Info.IMMOVABLE);
    }
}
