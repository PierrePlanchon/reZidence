package test.utility;

import org.junit.Test;

import utility.Score;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;

public class ScoreTest {

    public Score score;

    public ScoreTest() {
        this.score = new Score(3, 0, 10, 8, 0);
    }

    @Test
    public void getNbDeathTest() {
        assertEquals(3, this.score.getNbDeath());
    }

    @Test
    public void getNbMonsterKillTest() {
        assertEquals(0, this.score.getNbMonsterKills());
    }

    @Test
    public void getNbChestsOpenTest() {
        assertEquals(10, this.score.getNbChestsOpen());
    }

    @Test
    public void getNbRoomFoundTest() {
        assertEquals(8, this.score.getNbRoomFound());
    }

    @Test
    public void getNbEtageTest() {
        assertEquals(0, this.score.getNbEtage());
    }

    @Test
    public void saveTest() {
        Score score2;
        try {
            score2 = new Score(new File("res/tmp/ScoreTest.csv"));
            assertEquals(10, score2.getNbDeath());
            score2.setNbDeath(1);
            score2.setNbMonsterKill(2);
            score2.setNbChestsOpen(3);
            score2.setNbRoomFound(4);
            score2.setNbEtage(5);
            score2.save();
            assertEquals(1, score2.getNbDeath());
            assertEquals(2, score2.getNbMonsterKills());
            assertEquals(3, score2.getNbChestsOpen());
            assertEquals(4, score2.getNbRoomFound());
            assertEquals(5, score2.getNbEtage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void resetTest() {
        this.score.reset();
        assertEquals(0, this.score.getNbDeath());
        assertEquals(0, this.score.getNbMonsterKills());
        assertEquals(0, this.score.getNbChestsOpen());
        assertEquals(0, this.score.getNbRoomFound());
        assertEquals(0, this.score.getNbEtage());
    }

    @Test
    public void toStringTest() {
        assertEquals("Score [nbDeath=3, nbMonsterKill=0, nbChestsOpen=10, nbRoomFound=8, nbEtage=0]",
                this.score.toString());
    }
}
