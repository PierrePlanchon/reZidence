package entity;

public enum EnnemiType {
    ZOMBIE(50, 30, Item.AMO), RAT(20, 10, Item.HEAL), COCKROACH(5, 2, Item.KEY);

    private int health;
    private int damage;
    private Item drop;

    private EnnemiType(int health, int damage, Item drop) {
        this.damage = damage;
        this.health = health;
        this.drop = drop;
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


    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }


}
