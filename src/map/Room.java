package map;

import utility.Coordinates;
import utility.Info;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Ennemi;
import entity.EnnemiType;
import entity.Item;
import entity.Player;
import entity.Chest;
import interfaces.Fight;
import interfaces.ZombieGame;

public class Room {

    public static ArrayList<Element[][]> everyMaps = new ArrayList<Element[][]>();
    public static ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
    private static final Scanner sc = new Scanner(System.in);
    public static int levelType = 0;
    private int xy;
    private Element[][] map;
    private Player player;

    private static boolean beginning = true;

    public Room(int xy, int lvl) {
        this.xy = xy;
        this.map = new Element[xy][xy];
        this.player = ZombieGame.PLAYER;
    }

    public Room(int xy) {
        this(xy, 0);
    }

    public Room() {
        this(25);
    }

    public Player getPlayer() {
        return player;
    }

    public String toStringMap() {
        String str = "";
        for (int i = 0; i < this.xy; i++) {
            for (int j = 0; j < this.xy; j++) {
                if (this.map[i][j] == null)
                    str += "   ";
                else
                    str += this.map[i][j].toString() + "\033[0m";
            }
            str += "\n";
        }
        return str;
    }

    public void generateMap(int nbenemy, int nbwall, int nbutil) {
        this.generateWalls(nbwall);
        this.generateEnemies(nbenemy);
        this.generateUtil(nbutil);
        for (int i = 0; i < this.xy; i++) {
            for (int j = 0; j < this.xy; j++) {
                if (i == 0 || i == this.xy - 1 || j == 0 || j == this.xy - 1)
                    this.map[i][j] = new Border(i, j);
            }
        }
        this.generateExits();
        if (beginning)
            this.map[this.player.getPosX()][this.player.getPosY()] = this.player;
        if (!beginning) {
            this.generateBackExit();
            if ((int)(Math.random()*10) == 7)
                generateStaircase(new Coordinates(gvbb(),(gvbb())));
        }
        beginning = false;
    }

    private void generateBackExit() {
        Exit e = new Exit(xy/2,xy/2);
        e.removeInfo(Info.EXIT);
        e.addInfo(Info.GOBACKDOOR);
        this.map[xy/2][xy/2] = e;
    }

    private EnnemiType getMapSpawnRate() {
        int i;
        if (levelType == 0) {
            return EnnemiType.RAT;
        }
        else if (levelType == 1) {
            if ((int) Math.random() * 2 == 0) {
                return EnnemiType.RAT;
            }
            else {
                return EnnemiType.COCKROACH;
            }
        }
        else if (levelType == 2) {
            i = (int) (Math.random() * 3);
            if (i == 0) {
                return EnnemiType.RAT;
            }
            else if (i == 1) {
                return EnnemiType.COCKROACH;
            }
            else {
                return EnnemiType.ZOMBIE;
            }
        }
        else if (levelType == 3) {
            i = (int) (Math.random() * 3);
            if (i == 0) {
                return EnnemiType.COCKROACH;
            }
            else {
                return EnnemiType.ZOMBIE;
            }
        }
        return EnnemiType.ZOMBIE;
    }

    private void generateEnemies(int nb) {
        for (int i = 1; i < this.xy - 1; i++) {
            for (int j = 1; j < this.xy - 1; j++) {
                if (this.map[i][j] == null && (int) (Math.random() * 1000) <= nb) {
                    this.map[i][j] = new Ennemi(i, j, this.getMapSpawnRate());
                }
            }
        }
    }

    private void generateWalls(int nb) {
        for (int i = 1; i < xy - 1; i++) {
            for (int j = 1; j < xy - 1; j++) {
                if (this.map[i][j] == null && Math.random() * 1000 <= nb) {
                    this.map[i][j] = new Wall(i, j);
                }
            }
        }
        for (int i = 2; i < xy - 2; i++) {
            for (int j = 2; j < xy - 2; j++) {
                if (i == 2 || i == this.xy - 3 || j == 2 || j == this.xy - 3)
                    this.map[i][j] = null;
            }
        }
    }

    
    private void generateUtil(int nb) {
        for (int i = 1 ; i < xy-1 ; i++) {
            for (int j = 1 ; j < xy-1 ; j++) {
                if (this.map[i][j] == null && Math.random()*1000 <= nb) {
                    this.map[i][j] = new Chest(i,j,Item.values()[(int)(Math.random()*Item.values().length)]);
                }
            }
        }
    }

    private void generateExits() {
        int exits = (int) (Math.ceil(Math.random() * 4));
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(0); ints.add(1); ints.add(2); ints.add(3);
        Collections.shuffle(ints);
        int k;
        for (int i = 0; i < exits; i++) {
            if (ints.get(0) == 0) { //UP
                k = gvbb();
                this.map[1][k] = null;
                this.map[0][k] = new Exit(0, k);
            } else if (ints.get(0) == 1) { //DOWN
                k = gvbb();
                this.map[this.xy - 2][k] = null;
                this.map[this.xy - 1][k] = new Exit(this.xy - 1, k);
            } else if (ints.get(0) == 2) { //LEFT
                k = gvbb();
                this.map[k][1] = null;
                this.map[k][0] = new Exit(k, 0); 
            } else { //RIGHT
                k = gvbb();
                this.map[k][this.xy - 2] = null;
                this.map[k][this.xy - 1] = new Exit(k, this.xy - 1);
            }
            ints.remove(0);
        }
    }

    private void generateStaircase(Coordinates c) {
        generateAirGap(c, 5);
        this.map[c.getX()][c.getY()] = new Staircase(c.getX(),c.getY());
    }

    private void generateAirGap(Coordinates c, int size) {
        for (int i = c.getX()-size/2 ; i < c.getX()+size/2 ; i++) {
            for (int j = c.getY()-size/2 ; j < c.getY()+size/2 ; j++) {
                if (i >= 0 && i < this.xy && j >= 0 && j < this.xy && this.map[i][j] != null && !this.map[i][j].isInfo(Info.UNREPLACABLE)) {
                    this.map[i][j] = null;
                }
            }
        }
    }

    private int gvbb() { //(generateValueBetweenBounds)
        int x = (int) (Math.random() * this.xy -1);
        if (x==0) return 1;
        else if (x==this.xy-1) return this.xy-2;
        return x;
    }

    private void moveRoom() {
        everyMaps.add(this.map);
        coords.add(new Coordinates(player.getPosX(),player.getPosY()));
        this.xy = 30;
        this.map = new Element[xy][xy];
        this.player.setPosX(xy/2);
        this.player.setPosY(xy/2);
        generateMap(25,250,2);
        this.player.getScore().addRoomFound();
    }

    private void moveBack() {
        Element[][] e = everyMaps.get(everyMaps.size()-1);
        this.xy = e.length;
        this.map = e;
        Coordinates c = coords.get(coords.size()-1);
        player.setPosX(c.getX());
        player.setPosY(c.getY());
        everyMaps.remove(e);
        coords.remove(c);
        System.out.println(toStringMap());
    }

    public int getSize() {
        return this.xy;
    }

    public Element[][] getMap() {
        return this.map;
    }

    private boolean checkElementValidity(Element s, Info i) {
        if (s == null)
            return false;
        if (s.isInfo(i))
            return true;
        if (s instanceof Staircase) {
            this.resetToNewStage();
            return false;
        }
        if (s.isInfo(Info.EXIT)) {
            this.moveRoom();
            this.player.setHealth(player.getHealth()-7);
            return false;
        }
        if(s instanceof Ennemi){
            
            Fight f = new Fight(this.player, (Ennemi)s);
            this.player.getScore().addMonsterKills();
            return !f.startFight();
        }
        if(s instanceof Chest){
            return !((Chest)s).useChest(player);
        }
        else if (s.isInfo(Info.GOBACKDOOR)) {
            this.moveBack();
            return false;
        }
        return false;
    }

    public void resetToNewStage() {
        levelType++;
        beginning = true;
        this.map = new Element[xy][xy];
        Coordinates c = Coordinates.getElemCoordinates(this.player);
        generateMap(25,250,2);
        everyMaps.clear();
        coords.clear();
        this.map[c.getX()][c.getY()] = null;
        this.player.getScore().addEtage();
    }

    public boolean actionPlayer() {
        boolean b = true;
        int x = this.player.getPosX();
        int y = this.player.getPosY();
        this.map[x][y] = null;
        System.out.print("Movement ZQSD | Inventory I : ");
        String typing = sc.nextLine();
        if (typing.equalsIgnoreCase("z")
                && !(checkElementValidity(this.map[this.player.getPosX() - 1][this.player.getPosY()], Info.IMMOVABLE)))
            this.player.setPosX(this.player.getPosX() - 1);
        else if (typing.equalsIgnoreCase("q")
                && !(checkElementValidity(this.map[this.player.getPosX()][this.player.getPosY() - 1], Info.IMMOVABLE)))
            this.player.setPosY(this.player.getPosY() - 1);
        else if (typing.equalsIgnoreCase("s")
                && !(checkElementValidity(this.map[this.player.getPosX() + 1][this.player.getPosY()], Info.IMMOVABLE)))
            this.player.setPosX(this.player.getPosX() + 1);
        else if (typing.equalsIgnoreCase("d")
                && !(checkElementValidity(this.map[this.player.getPosX()][this.player.getPosY() + 1], Info.IMMOVABLE)))
            this.player.setPosY(this.player.getPosY() + 1);
        else if (typing.equalsIgnoreCase("i")) {
            System.out.flush();
            System.out.println("- - - - - Inventaire - - - - -");
            System.out.println(this.player.statsToString() + "\n");
            this.player.afficherInventaire();
            System.out.println("Appuyez sur H pour vous soigner, R pour recharger n'importe quelle touche pour quitter");
            typing = sc.nextLine();
            if (typing.equalsIgnoreCase("H"))
                if (this.player.countItem(Item.HEAL) > 0)
                    this.player.consommerItem(Item.HEAL);
                else
                    System.out.println("Vous n'avez pas de soin.");
            else if (typing.equalsIgnoreCase("R"))
                if (this.player.countItem(Item.AMO) > 0)
                    this.player.consommerItem(Item.AMO);
                else
                    System.out.println("Vous n'avez pas de munitions.");
        }
        else
            b = false;
        this.map[this.player.getPosX()][this.player.getPosY()] = this.player;
        return b;
    }

    public void refreshEnemytyping() {
        for (int i = 0 ; i < this.xy ; i++) {
            for (int j = 0 ; j < this.xy ; j++) {
                if (this.map[i][j] instanceof Ennemi) {
                    this.map[i][j].removeInfo(Info.IMMOVABLE);
                }
            }
        }
    }


    public void moveEnemies() {
        for (int i = this.player.getPosX()-4 ; i < this.player.getPosX()+4 ; i++) {
            for (int j = this.player.getPosY()-4 ; j < this.player.getPosY()+4 ; j++) {
                if (i >= 0 && i < this.xy && j >= 0 && j < this.xy) {
                    if (this.map[i][j] instanceof Ennemi && !this.map[i][j].isInfo(Info.IMMOVABLE)) {
                        if (i < this.player.getPosX() && this.map[i+1][j] == null) {
                            this.map[i+1][j] = this.map[i][j];
                            this.map[i][j] = null;
                            this.map[i+1][j].addInfo(Info.IMMOVABLE);
                        }
                        else if (i > this.player.getPosX() && this.map[i-1][j] == null) {
                            this.map[i-1][j] = this.map[i][j];
                            this.map[i][j] = null;
                            this.map[i-1][j].addInfo(Info.IMMOVABLE);
                        }
                        else if (j < this.player.getPosY() && this.map[i][j+1] == null) {
                            this.map[i][j+1] = this.map[i][j];
                            this.map[i][j] = null;
                            this.map[i][j+1].addInfo(Info.IMMOVABLE);
                        }
                        else if (j > this.player.getPosY() && this.map[i][j-1] == null) {
                            this.map[i][j-1] = this.map[i][j];
                            this.map[i][j] = null;
                            this.map[i][j-1].addInfo(Info.IMMOVABLE);
                        }
                    }
                }
            }
        }
        this.refreshEnemytyping();
    }
}
