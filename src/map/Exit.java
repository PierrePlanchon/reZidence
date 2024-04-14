package map;
import utility.Info;

public class Exit extends Element {
    public Exit(int x, int y) {
        super(x, y,"\033[42m","   ");
        this.addInfo(Info.EXIT);
        this.addInfo(Info.UNREPLACABLE);
        this.addInfo(Info.HURTFUL);
    }
}
