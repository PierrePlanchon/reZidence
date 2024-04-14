package entity;

import interfaces.Menu;
import map.Element;

public class Chest extends Element {
    private Item item;

    public Chest(int x, int y, Item item) {
        super(x, y, "\033[38;2;255;128;0m", " 🧺");
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean useChest(Player player) {
        if (player.countItem(Item.KEY) > 0) {
            player.consommerItem(Item.KEY);
            player.getScore().addChestsOpen();
        } else {
            System.out.println("Vous n'avez pas de clé pour ouvrir ce coffre.");
            Menu.wait(3000);
            return false;
        }
        if (this.item == Item.NONE) {
            System.out.println("Ce coffre est vide...");
            return true;
        } else if (this.item == Item.SCOPE) {
            if (player.getPrecision() < 90) {
                System.out.println("Vous ramassez un scope et votre précision vient d'augmenter !");
                player.setPrecision(player.getPrecision() + 5);
            } else
                System.out.println("Vous ramassez un scope mais votre précision est déjà au maximum, dommage.");
            Menu.wait(3000);
            return true;
        } else if (player.getMaxItem() == player.getItems().size()) {
            System.out.println("Votre inventaire est plein...");
            Menu.wait(3000);
            return false;
        } else {
            player.addInventory(this.getItem());
            String itemName = this.getItem().name();
            this.setItem(null);
            System.out.println("Vous avez obtenu: " + itemName + " !");
            Menu.wait(3000);
            return true;
        }
    }

}
