package map;

import utility.Info;

public class Border extends Element {
    public Border(int x, int y) {
        super(x, y, "\033[48;2;25;25;25m", "   ");
        this.addInfo(Info.IMMOVABLE);
        this.addInfo(Info.UNREPLACABLE);
    }
}
