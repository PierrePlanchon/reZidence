package interfaces;

import java.util.Scanner;
import entity.Player;
import map.Room;

public class ZombieGame {
    public static Player PLAYER = new Player(15, 15, "Jambon");

    public void start() {
        Scanner sc = new Scanner(System.in);

        int enemy = 25;
        int walls = 250;
        int utils = 2;

        Room m = new Room(30);

        m.generateMap(enemy, walls, utils);
        String str = m.toStringMap();
        System.out.println(str);
        boolean game = true;
        while (game) {
            System.out
                    .println("---------------------------------------------------------------------------------------");
            if (m.actionPlayer())
                m.moveEnemies();
            Menu.cleanup();
            System.out.println(m.toStringMap());
            if (m.getPlayer().getHealth() <= 0) {
                game = false;
                Menu.cleanup();
                System.out.println("Vous êtes mort à l'étage " + Room.levelType + "\n nullos. :3");
            }
            if (Room.levelType == 5) {
                game = false;
                Menu.cleanup();
                System.out.println("---------------------------------------------");
                System.out.println(Color.CYAN_BOLD + "Fin ! Merci d'avoir joué !" + Color.RESET);
                System.out.println("---------------------------------------------");
                Menu.wait(3000);
                System.out.println("Voulez-vous sauvegarder votre score ? (y/n)");
                String choice = sc.next();
                if (choice.equals("y")) {
                    m.getPlayer().getScore().save();
                    System.out.println("Votre score a bien été sauvegardé !");
                } else {
                    System.out.println("Votre score n'a pas été sauvegardé !");
                }
                Menu.wait(2000);
                Menu.cleanup();
                System.out.println("Voulez-vous voir vos scores ? (y/n)");
                String choice2 = sc.next();
                if (choice2.equals("y")) {
                    m.getPlayer().getScore().display();
                    Menu.wait(2000);
                } else {
                    System.out.println("Vous avez choisi de ne pas voir vos scores !");
                    Menu.wait(2000);
                }
            }
        }
        m.getPlayer().getScore().save();
        sc.close();
    }
}
