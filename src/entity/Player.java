package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import exceptions.InvalidNameException;
import map.Element;
import utility.Info;
import utility.Score;

public class Player extends Element {
    private String name;
    private int maxHealth;
    private int health;
    private int ammo;
    private State state;
    private ArrayList<Item> items;
    private int maxItem;
    private int damage;
    private int precision;
    private Score score;
    // private int ammoUsed; nombre de munitions utilisées, au bout d'un certain
    // nombre il faut recharger

    public Score getScore() {
        return score;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Player(int x, int y, String name) {
        super(x, y, "\033[38;2;255;200;241m", "😀 ");
        this.name = name;
        this.maxHealth = 100;
        this.health = 100;
        this.ammo = 25;
        this.maxItem = 10;
        this.state = State.ALIVE;
        this.damage = 15;
        this.addInfo(Info.IMMOVABLE);
        this.addInfo(Info.UNREPLACABLE);
        initInventory();
        this.precision = 65;
        this.addInventory(Item.KEY);
        try {
            this.score = new Score(new File("res/Score.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return this.getColor() + this.getSymbol();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmmo() {
        return ammo;
    }

    public State getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void initInventory() {
        this.items = new ArrayList<Item>();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String statsToString() {
        return this.name + "; hp:" + this.health + "/" + this.maxHealth + ", state: " + this.state.toString()
                + ", ammo: " + this.ammo;
    }

    public void addInventory(Item item) {
        if (this.items.size() < maxItem) {
            this.items.add(item);
        }

    }

    public boolean inventoryFull() {
        return (maxItem == 10);
    }

    public boolean supInventory(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name().equals(item.name())) {
                return items.remove(items.get(i));
            }
        }

        return false;
    }

    public int countItem(Item item) {
        int cpt = 0;
        for (Item i : items) {
            if (i.name().equals(item.name())) {
                cpt++;
            }
        }
        return cpt;
    }

    public void afficherInventaire() {
        if (items.size() == 0) {
            System.out.println("\n inventaire vide \n");
        } else {
            System.out.println("\n vous possédez : \n Clés x" + countItem(Item.KEY) + "\n Soin x" + countItem(Item.HEAL)
                    + "\n Grenade x" + countItem(Item.GRENADE) + "\n Munitions x" + countItem(Item.AMO) + "\n");
        }
    }

    public void consommerItem(Item item) {
        if (item == Item.HEAL) {
            int i = (int) (Math.random() * 11) + 20;
            this.setHealth(this.getHealth() + i);
            System.out.println("Vous avez été heal de " + i + " HP.");
            if (this.getHealth() > this.getMaxHealth())
                this.setHealth(this.getMaxHealth());
            this.supInventory(Item.HEAL);
        } else if (item == Item.AMO) {
            int i = (int) (Math.random() * 6) + 1;
            this.setAmmo(this.getAmmo() + i);
            this.supInventory(Item.AMO);
            System.out.println("Vous avez rechargé votre arme de " + i + " munitions.");
        } else if (item == Item.KEY) {
            System.out.println("Vous utilisez une clé !");
            this.supInventory(Item.KEY);

        }
        System.out.println("Vous ne pouvez pas utiliser cet objet.");
    }

    public void setName(String name) throws InvalidNameException {
        if (name.matches("^[a-zA-Z0-9_]*$")) {
            this.name = name;
        } else {
            throw new InvalidNameException("Nom de joueur invalide ! ");
        }
    }
}