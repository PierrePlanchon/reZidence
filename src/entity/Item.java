package entity;

public enum Item {
    KEY(0, 0, "key", "ouvre un coffre"),
    HEAL(10, 0, "heal", "permet de recupérer des pv"),
    GRENADE(0, 50, "grenade", "inflige beaucoup de dégats"),
    AMO(0, 0, "amo", "donne des munitions supplémentaires"),
    NONE(0, 0, "rien", ""),
    SCOPE(0, 0, "scope+", "augmente la précision de l'arme");

    private final int H;
    private final int DAMAGE;
    private final String NAME;
    private final String DESC;

    private Item(int heal, int damage, String name, String desc) {
        this.H = heal;
        this.DAMAGE = damage;
        this.NAME = name;
        this.DESC = desc;
    }

    public int getH() {
        return this.H;
    }

    public int getDamage() {
        return this.DAMAGE;
    }

    public String getName() {
        return this.NAME;
    }

    public String getDesc() {
        return this.DESC;
    }

    public String toString() {
        return getName() + " : " + getDesc() + " --> " + getH() + " pv, " + getDamage() + " damage.";
    }

    public void youFound() {
        System.out.println("Vous avez trouvé : " + this + ".");
    }
}
