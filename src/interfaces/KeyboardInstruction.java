package interfaces;

import java.util.Scanner;

public class KeyboardInstruction {

    private static Scanner sc = new Scanner(System.in);

    public static void fermerScanner() {
        sc.close();
    }

    public static String saisieClavierStr() {
        String str = sc.next();
        return str;
    }

    public static char saisieClavierChar() {
        try {
            String str = sc.next();
            char c = str.charAt(0);
            return c;
        } catch (Exception e) {

            System.out.println("Erreur de saisie, veuillez recommencer");
            return saisieClavierChar();
        }
    }

    public static int saisieClavierInt() {
        try {
            String input = sc.next();
            int i = Integer.parseInt(input);
            return i;
        } catch (Exception e) {
            System.out.println("Erreur de saisie, veuillez recommencer");
            sc.nextLine();
            return saisieClavierInt();
        }
    }
}