import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import entity.Item;
import entity.Player;


public class PlayerTest {
    
    public Player player;
    public Item cle;
    public ArrayList<Item> compare;
    public Item cle2;

    public PlayerTest(){
        this.player = new Player(10, 10, "Player 1");
        this.compare = new ArrayList<Item>();
        this.cle = Item.KEY;
        this.cle2 = Item.KEY;
    }

    @Test
    public void statsToStringTest(){
        assertEquals("Player 1; hp:100/100, state: "+player.getState().toString()+", ammo: 10", player.statsToString());
    }
    
    @Test
    public void addInventoryTest(){
        compare.add(cle2);
        player.addInventory(cle);
        assertEquals(player.getItems(), compare);
    }

    @Test
    public void supInventoryTest(){
        player.addInventory(cle);
        assertEquals(1, player.countItem(Item.KEY));
        assertTrue(player.supInventory(cle));
        assertFalse(player.supInventory(Item.HEAL));
    }

    @Test
    public void countItemTest(){
        player.addInventory(Item.GRENADE);
        player.addInventory(Item.GRENADE);
        assertEquals(2, player.countItem(Item.GRENADE));
        assertEquals(0, player.countItem(Item.HEAL));
    }

    @Test
    public void consommerItemTest(){
        player.setHealth(50);
        player.addInventory(Item.HEAL);
        player.consommerItem(Item.HEAL);
        assertEquals(60, player.getHealth());

        player.setHealth(100);
        player.addInventory(Item.HEAL);
        player.consommerItem(Item.HEAL);
        assertEquals(100, player.getHealth());

        player.setHealth(95);
        player.addInventory(Item.HEAL);
        player.consommerItem(Item.HEAL);
        assertEquals(100, player.getHealth());

        player.addInventory(Item.AMO);
        player.consommerItem(Item.AMO);
        assertEquals(15, player.getAmmo());
    }
}
