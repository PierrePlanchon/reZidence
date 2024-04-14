import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entity.Chest;
import entity.Item;
import entity.Player;

public class ChestTest {
    
    public Chest chest;
    public Player player;
    
    public ChestTest(){
        this.chest = new Chest(0, 0, null);
        this.player = new Player(0, 0, "Player 1");
    }

    @Test
    public void useChestTest(){

        assertEquals("Ce coffre est vide...", chest.useChest(player));

        player.setMaxItem(1);
        player.addInventory(Item.KEY);
        chest.setItem(Item.GRENADE);
        assertEquals("Votre inventaire est plein...", chest.useChest(player));

        player.setMaxItem(2);
        assertEquals("Vous avez obtenu: GRENADE !", chest.useChest(player));
        assertEquals("Ce coffre est vide...", chest.useChest(player));
    }
}
