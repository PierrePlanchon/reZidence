package interfaces;

import java.util.Random;

import entity.Ennemi;
import entity.Item;
import entity.Player;

public class Fight {

    public Player p;
    public Ennemi e;

    private Boolean haveTryToEscape = false;

    public Fight(Player p, Ennemi e) {
        this.p = p;
        this.e = e;
    }

    public void inflictDamage() {
        String criticalOrNot = "";
        if (p.getAmmo() > 0) {
            int damageCaused = p.getDamage();

            if ((int) (Math.random() * 6) == 1) {
                damageCaused *= 2;
                criticalOrNot = "COUP CRITIQUE !";
            }

            e.setHealth(e.getHealth() - damageCaused);

            p.setAmmo(p.getAmmo() - 1);
            System.out.println("--------------------------------------------------------------------");
            System.out.println(
                    Color.RED_BOLD + criticalOrNot + "-" + damageCaused + " points de vie (ennemie)" + Color.RESET);
            System.out.println(Color.RED + "                     " + e.getType() + " a maintenant " + e.getHealth()
                    + " points de vie" + Color.RESET);
            System.out.println("--------------------------------------------------------------------");
            criticalOrNot = "";
            Menu.wait(1500);
        } else {
            System.out.println("Vous n'avez plus de munitions ! \n");
            Menu.wait(500);
        }
    }

    public void receiveDamage() {
        int damageCaused = e.getDamage();
        p.setHealth(p.getHealth() - damageCaused);

        System.out.println("--------------------------------------------------------------------");
        System.out.println(Color.BLUE_BOLD + "-" + damageCaused + " points de vie (joueur)" + Color.RESET);
        System.out.println(Color.BLUE + "                     " + p.getName() + " a maintenant " + p.getHealth()
                + " points de vie" + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        Menu.wait(500);

    }

    public Boolean startFight() {
        while (p.getHealth() > 0 && e.getHealth() > 0) {
            Menu.cleanup();
            System.out.println("Vous avez : " + p.getHealth() + " points de vie");
            System.out.println("L'ennemi a : " + e.getHealth() + " points de vie");
            System.out.println("Il vous reste : " + p.getAmmo() + " munitions");
            if (e.getType().name().toLowerCase().equals("COCKROACH".toLowerCase()))
                System.out.println(Menu.caffardDisplay());
            if (e.getType().name().toLowerCase().equals("RAT".toLowerCase()))
                System.out.println(Menu.ratDisplay());
            if (e.getType().name().toLowerCase().equals("ZOMBIE".toLowerCase()))
                System.out.println(Menu.zombieDisplay());

            System.out.println("--------------------------------------------------------------------");
            System.out.println(
                    Color.CYAN_BOLD + "                      Que voulez vous faire ?                      "
                            + Color.RESET);
            System.out.println("--------------------------------------------------------------------");
            System.out.println("1 - Attaquer");
            System.out.println("2 - Utiliser un objet");
            System.out.print((this.haveTryToEscape != true) ? "3 - Fuir\n" : "");
            System.out.println("--------------------------------------------------------------------");
            int choice = KeyboardInstruction.saisieClavierInt();
            switch (choice) {
                case 1:
                    inflictDamage();
                    receiveDamage();
                    Menu.wait(3000);
                    return startFight();
                case 3:

                    if (this.haveTryToEscape == true) {
                        Menu.cleanup();
                        System.out.println(Color.RED + "Veuillez saisir un choix valide !" + Color.RESET);
                        Menu.wait(1000);
                        return startFight();
                    }
                    this.haveTryToEscape = true;
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println(Color.CYAN_BOLD
                            + "                   Vous tenter de fuir le combat                   " + Color.RESET);
                    System.out.println("--------------------------------------------------------------------");
                    Random r = new Random();
                    int random = r.nextInt(100);
                    Menu.wait(1000);
                    Menu.cleanup();
                    if (random < 50) {
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println(Color.GREEN + "Vous avez réussi à fuir" + Color.RESET);
                        System.out.println("--------------------------------------------------------------------");
                        Menu.wait(1000);
                        return true;
                    } else {
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println(Color.RED + "Vous n'avez pas réussi à fuir" + Color.RESET);
                        System.out.println("--------------------------------------------------------------------");
                        Menu.wait(1000);
                        return startFight();
                    }

                case 2:
                    return choiceUseItem();

                default:
                    System.out.println("Veuillez saisir un choix valide");
                    return startFight();
            }
        }
        if (p.getHealth() <= 0) {
            System.out.println(Color.RED + "Vous avez perdu" + Color.RESET);
            return false;
        } else {
            System.out.println(Color.GREEN + "Vous avez gagné" + Color.RESET);
            if ((int) (Math.random() * 2) == 1) {
                p.addInventory(e.getDrop());
                if (!p.inventoryFull())
                    e.getDrop().youFound();
                else
                    System.out.println("Vous avez eu un item mais vous étiez plein");
            }
            return true;
        }

    }

    public Boolean choiceUseItem() {
        Menu.cleanup();
        System.out.println("--------------------------------------------------------------------");
        System.out.println(Color.CYAN_BOLD + "                 Quel objet voulez vous utiliser ?                 "
                + Color.RESET);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("1 - Soin\n2 - Grenade\n3 - Recharger\n4 - Retour");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Choix : ");
        int choice = KeyboardInstruction.saisieClavierInt();
        Menu.cleanup();
        switch (choice) {
            case 1:
                Menu.cleanup();
                if (p.countItem(Item.HEAL) > 0) {
                    p.consommerItem(Item.HEAL);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println(Color.GREEN + "Vous utilisez un soin" + Color.RESET);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Vous avez maintenant " + p.getHealth() + " points de vie");
                    Menu.wait(3000);
                    return startFight();
                } else {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Vous n'avez pas de soin !");
                    System.out.println("--------------------------------------------------------------------");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return choiceUseItem();
                }
            case 2:
                Menu.cleanup();
                if (p.countItem(Item.GRENADE) > 0) {
                    p.consommerItem(Item.GRENADE);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Vous utilisez une grenade");
                    System.out.println("--------------------------------------------------------------------");
                    e.setHealth(e.getHealth() - Item.GRENADE.getDamage());
                    System.out.println("L'ennemi a maintenant " + e.getHealth() + " points de vie");
                    p.consommerItem(Item.GRENADE);
                    return startFight();
                } else {
                    Menu.cleanup();
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Vous n'avez pas de grenade !");
                    System.out.println("--------------------------------------------------------------------");
                    Menu.wait(3000);
                    return choiceUseItem();
                }
            case 3:
                Menu.cleanup();
                if (p.countItem(Item.AMO) > 0) {
                    System.out.println("--------------------------------------------------------------------");
                    p.consommerItem(Item.AMO);
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("L'ennemi a maintenant " + e.getHealth() + " points de vie");
                    return startFight();
                } else {
                    Menu.cleanup();
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Vous n'avez pas de munition ! (so long mon reuf)");
                    System.out.println("--------------------------------------------------------------------");
                    Menu.wait(3000);
                    return choiceUseItem();
                }
            case 4:
                Menu.cleanup();
                System.out.println("--------------------------------------------------------------------");
                System.out.println("Vous retournez au combat");
                System.out.println("--------------------------------------------------------------------");
                Menu.wait(3000);
                return startFight();
            default:
                System.out.println("Veuillez saisir un choix valide");
                return choiceUseItem();
        }
    }

}
