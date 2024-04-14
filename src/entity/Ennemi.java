package entity;

import map.Element;

public class Ennemi extends Element implements Comparable<Ennemi> {

    private int health;
    private int damage;
    // private int range;
    private Item drop;
    private EnnemiType type;
    // ajouter drop, taux de drop

    public Ennemi(int x, int y, EnnemiType type) {
        super(x, y, "\033[0;31m", pickEnemySymbol(type));
        this.type = type;
        this.health = type.getHealth();
        this.damage = type.getDamage();
        this.drop = type.getDrop();
    }

    public static String pickEnemySymbol(EnnemiType t) {
        if (t == EnnemiType.RAT)
            return " 🐀";
        if (t == EnnemiType.COCKROACH)
            return " 🪳";
        // if (t == EnnemiType.ZOMBIE)
        return " 🧟";
    }

    public String toString() {
        return this.getColor() + this.getSymbol();
    }

    public Item getDrop() {
        return drop;
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public EnnemiType getType() {
        return type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setType(EnnemiType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Ennemi o) {
        return (this.getDamage() + this.getHealth()) / 3
                - (o.getDamage() + o.getHealth()) / 3;
    }

}
