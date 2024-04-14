import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entity.Ennemi;
import entity.EnnemiType;
import entity.Player;
import interfaces.Fight;

public class FightTest {
    
    public Player p;
    public Ennemi e;
    public Fight fight;

    @BeforeEach
    public void initialization() {
        p = new Player(3, 3, "TestPlayer");
        p.setHealth(50);
        e = new Ennemi(3, 3, EnnemiType.RAT);
        e.setHealth(25);
        fight = new Fight(p, e);
    }

    @Test
    public void inflictDamageTest() {
        p.setAmmo(5);
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
        fight.inflictDamage();
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
        p.setAmmo(-1);
        fight.inflictDamage();
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
        
    }

    @Test
    public void receiveDamageTest() {
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
        fight.receiveDamage();
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
        p.setHealth(0);
        fight.receiveDamage();
        assertEquals(50, p.getHealth());
        assertEquals(25, p.getHealth());
    }
}
