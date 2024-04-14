
package interfaces;

import entity.Player;

public class Main {
    public static final Player PLAYER = new Player(12, 12, "Jambon");

    public static void main(String[] args) {
        Menu.loading();
        Menu.atStart();
    }
}
