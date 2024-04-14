package interfaces;

import java.io.File;
import java.util.Scanner;

import exceptions.InvalidNameException;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cleanup() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String zombieDisplay() {
        File file = new File("res/acsiiArt/zombie.txt");
        try {
            StringBuilder str = new StringBuilder();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                str = str.append(sc.nextLine() + "\n");
            }
            sc.close();
            return str.toString();
        } catch (Exception e) {
            System.out.println("Erreur de lecture du fichier / fichier inexistant");
            return null;
        }
    }

    public static String ratDisplay() {
        File file = new File("res/acsiiArt/rat1.txt");
        try {
            StringBuilder str = new StringBuilder();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                str = str.append(sc.nextLine() + "\n");
            }
            sc.close();
            return str.toString();
        } catch (Exception e) {
            System.out.println("Erreur de lecture du fichier / fichier inexistant");
            return null;
        }
    }

    public static String caffardDisplay() {
        File file = new File("res/acsiiArt/caffard.txt");
        try {
            StringBuilder str = new StringBuilder();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                str = str.append(sc.nextLine() + "\n");
            }
            sc.close();
            return str.toString();
        } catch (Exception e) {
            System.out.println("Erreur de lecture du fichier / fichier inexistant");
            return null;
        }
    }

    public static String menu() {
        String str;
        str = "--------------------------------------------------------------------\n";
        str += "1 - Commencer une nouvelle partie \n";
        str += "2 - Explications\n";
        str += "3 - Credits\n";
        str += "4 - Définir le nom\n";
        str += "5 - Voir les scores\n";
        str += "6 - Quitter\n";
        str += "--------------------------------------------------------------------\n";

        return str;
    }

    public static void loading() {
        cleanup();
        File file1 = new File("res/acsiiArt/rat1.txt");
        File file2 = new File("res/acsiiArt/rat2.txt");
        for (int index = 0; index < 4; index++) {
            try {
                StringBuilder str = new StringBuilder();
                Scanner sc = new Scanner(file1);
                while (sc.hasNextLine()) {
                    str = str.append(sc.nextLine() + "\n");
                }
                System.out.println(str.toString());
                Thread.sleep(150);
                cleanup();
                sc.close();
            } catch (Exception e) {
                System.out.println("Erreur de lecture du fichier / fichier inexistant");
            }
            try {
                StringBuilder str = new StringBuilder();
                Scanner sc = new Scanner(file2);
                while (sc.hasNextLine()) {
                    str = str.append(sc.nextLine() + "\n");
                }
                System.out.println(str.toString());
                Thread.sleep(500);
                cleanup();
                sc.close();
            } catch (Exception e) {
                System.out.println("Erreur de lecture du fichier / fichier inexistant");
            }

        }
    }

    public static String welcome() {
        String str = "";
        str += "--------------------------------------------------------------------\n";
        str += "                       Bienvenue sur reZidence !\n";
        str += "--------------------------------------------------------------------\n";
        return str;
    }

    public static void atStart() {
        // cliquez sur entré pour continuer ...
        System.out.println(welcome());
        System.out.println(
                Color.CYAN_BOLD + "              >>> Appuyez sur entrée pour continuer <<<             " + Color.RESET);
        sc.nextLine();
        choiced();
    }

    public static void choiced() {
        cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out.println(
                Color.CYAN_BOLD + "           Veuillez choisir l'une des options ci-dessous :          " + Color.RESET);
        System.out.println(menu());
        int choice = KeyboardInstruction.saisieClavierInt();
        switch (choice) {
            case 1:
                choiceStartAGame();
                choiced();
            case 2:
                choiceExplain();
                choiced();
            case 3:
                choiceCredits();
                choiced();
            case 4:
                choiceDefName();
                sc.nextLine();
                choiced();
            case 5:
                choiceSeeScore();
                choiced();
            case 6:
                choiceQuit();
                choiced();
            default:
                System.out.println("Erreur de saisie, veuillez recommencer");
                choiced();
                break;
        }
    }

    public static void choiceStartAGame() {
        cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out
                .println(Color.CYAN_BOLD + "         Vous avez choisi de commencer une nouvelle partie !        "
                        + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ZombieGame game = new ZombieGame();
        game.start();
    }

    public static void choiceExplain() {
        cleanup();
        File file = new File("res/text/explain.txt");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(
                Color.CYAN_BOLD + "         Vous avez choisi de lire les explications !        " + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.println(
                    Color.CYAN_BOLD + "               Appuyez sur entrée pour continuer ...                "
                            + Color.RESET);
            Menu.sc.nextLine();
            sc.close();
        } catch (Exception e) {
            System.out.println("Erreur de lecture du fichier / fichier inexistant");
        }
    }

    public static void choiceCredits() {
        cleanup();
        File file = new File("res/text/credits.txt");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(
                Color.CYAN_BOLD + "                         Voici les crédits !                        " + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
                Thread.sleep(200);
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.println(
                    Color.CYAN_BOLD + "               Appuyez sur entrée pour continuer ...               "
                            + Color.RESET);
            Menu.sc.nextLine();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de lecture du fichier / fichier inexistant");
        }
    }

    public static void choiceQuit() {
        cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out
                .println(Color.RED + "                Vous avez choisi de quitter le jeu !                "
                        + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void choiceDefName() {
        cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out.println(Color.CYAN_BOLD + "         Vous avez choisi de définir votre nom !        " + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Veuillez saisir votre nom : ");
        String name = KeyboardInstruction.saisieClavierStr();
        try {
            ZombieGame.PLAYER.setName(name);
        } catch (InvalidNameException e) {
            System.out.println("Erreur de saisie, veuillez recommencer");
            Menu.wait(2000);
            choiceDefName();
        }

        System.out.println("Votre nom est maintenant : " + ZombieGame.PLAYER.getName());
        Menu.wait(2500);
    }

    public static void choiceSeeScore() {
        cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out.println(Color.CYAN_BOLD + "         Vous avez choisi de voir les scores !        " + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        ZombieGame.PLAYER.getScore().display();
        Menu.wait(5000);
    }

}
