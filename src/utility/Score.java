package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import entity.EnnemiType;
import interfaces.Color;
import interfaces.Menu;

public class Score {

    private int nbDeath;
    private int nbMonsterKill;
    private int nbChestsOpen;
    private int nbRoomFound;
    private int nbEtage;

    public Score() {
        this.nbDeath = 0;
        this.nbMonsterKill = 0;
        this.nbChestsOpen = 0;
        this.nbRoomFound = 0;
        this.nbEtage = 0;
    }

    public Score(int nbDeath, int nbMonsterKill, int nbChestsOpen, int nbRoomFound, int nbEtage) {
        this.nbDeath = nbDeath;
        this.nbMonsterKill = nbMonsterKill;
        this.nbChestsOpen = nbChestsOpen;
        this.nbRoomFound = nbRoomFound;
        this.nbEtage = nbEtage;
    }

    public Score(File csvFile) throws FileNotFoundException {
        Scanner sc = new Scanner(csvFile);
        sc.useDelimiter(",");
        sc.nextLine();
        this.nbDeath = sc.nextInt();
        this.nbMonsterKill = sc.nextInt();
        this.nbChestsOpen = sc.nextInt();
        this.nbRoomFound = sc.nextInt();
        this.nbEtage = sc.nextInt();
        sc.close();

    }

    public void addDeath() {
        this.nbDeath = this.nbDeath + 1;
    }

    public void addMonsterKills() {
        this.nbMonsterKill = this.nbMonsterKill + 1;
    }

    public void addChestsOpen() {
        this.nbChestsOpen = this.nbChestsOpen + 1;
    }

    public void addRoomFound() {
        this.nbRoomFound = this.nbRoomFound + 1;
    }

    public void addEtage() {
        this.nbEtage = this.nbEtage + 1;
    }

    public int getNbDeath() {
        return this.nbDeath;
    }

    public int getNbMonsterKills() {
        return this.nbMonsterKill;
    }

    public int getNbChestsOpen() {
        return this.nbChestsOpen;
    }

    public int getNbRoomFound() {
        return this.nbRoomFound;
    }

    public int getNbEtage() {
        return this.nbEtage;
    }

    @SuppressWarnings("unused")
    public Boolean save() {
        try {
            File file = new File("res/Score.csv");
            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            sc.nextLine();
            int nbDeathInCSV = sc.nextInt();
            int nbMonsterKillInCSV = sc.nextInt();
            int nbChestsOpenInCSV = sc.nextInt();
            int nbRoomFoundInCSV = sc.nextInt();
            int nbEtageInCSV = sc.nextInt();
            sc.close();
            file.delete();
            nbDeathInCSV += this.nbDeath;
            nbMonsterKillInCSV += this.nbMonsterKill;
            nbChestsOpenInCSV += this.nbChestsOpen;
            nbRoomFoundInCSV += this.nbRoomFound;
            nbEtageInCSV += this.nbEtage;
            StringBuilder str = new StringBuilder();
            str.append("nbDeath,nbMonsterKills,nbChestsOpen,nbRoomFound,nbEtage\n");
            str.append(nbDeath + "," + nbMonsterKill + "," + nbChestsOpen + "," + nbRoomFound + "," + nbEtage);
            FileWriter newFile = new FileWriter("res/Score.csv");
            newFile.write(str.toString());
            newFile.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void reset() {
        this.nbDeath = 0;
        this.nbMonsterKill = 0;
        this.nbChestsOpen = 0;
        this.nbRoomFound = 0;
        this.nbEtage = 0;
    }

    @Override
    public String toString() {
        return "Score [nbDeath=" + nbDeath + ", nbMonsterKill=" + nbMonsterKill + ", nbChestsOpen=" + nbChestsOpen
                + ", nbRoomFound=" + nbRoomFound + ", nbEtage=" + nbEtage + "]";
    }

    public void setNbDeath(int i) {
        this.nbDeath = i;
    }

    public void setNbMonsterKill(int i) {
        this.nbMonsterKill = i;
    }

    public void setNbChestsOpen(int i) {
        this.nbChestsOpen = i;
    }

    public void setNbRoomFound(int i) {
        this.nbRoomFound = i;
    }

    public void setNbEtage(int i) {
        this.nbEtage = i;
    }

    public void display() {
        Menu.cleanup();
        System.out.println("---------------------------------------------");
        System.out.println("Nombre de morts : " + this.nbDeath);
        System.out.println("Nombre de monstres tués : " + this.nbMonsterKill);
        System.out.println("Nombre de coffres ouverts : " + this.nbChestsOpen);
        System.out.println("Nombre de salles découvertes : " + this.nbRoomFound);
        System.out.println("Nombre d'étages parcourus : " + this.nbEtage);
        System.out.println("---------------------------------------------");
        System.out
                .println(
                        Color.CYAN_BOLD + "Score global : " + this.nbMonsterKill * EnnemiType.COCKROACH.getHealth() + ""
                                + Color.RESET);
        System.out.println("---------------------------------------------");
    }
}
