package map;

public class Staircase extends Exit {
    
    Staircase(int x, int y) {
        super(x,y);
        this.setColor("\033[48;2;255;255;0m");
    }
}
