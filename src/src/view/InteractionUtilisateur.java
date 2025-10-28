package view;

import java.util.Scanner;
import model.Game;
import model.TicTacToe;
import model.Gomoku;
import model.Puissance4;
import model.Player;

/**
 * Classe qui gère toutes les interactions avec l'utilisateur via la console.
 * Elle affiche les menus et lit les entrées de l'utilisateur.
 */
public class InteractionUtilisateur {

    // Scanner pour lire les entrées clavier
    private final Scanner scanner;

    public InteractionUtilisateur() {
        scanner = new Scanner(System.in); // Initialisation du scanner
    }

    /**
     * Affiche le menu principal des jeux et renvoie le choix de l'utilisateur.
     */
    public int afficherMenuJeux() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1 - TicTacToe");
        System.out.println("2 - Gomoku");
        System.out.println("3 - Puissance 4");
        System.out.println("0 - Quitter");
        System.out.print("Votre choix : ");

        while (true) {
            try {
                int choix = Integer.parseInt(scanner.nextLine());
                if (choix >= 0 && choix <= 3) return choix;
                System.out.print("Veuillez entrer un nombre entre 0 et 3 : ");
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, recommencez : ");
            }
        }
    }

    /**
     * Affiche le menu du mode de jeu et renvoie le choix (1 à 3).
     */
    public int demanderModeJeu() {
        System.out.println("\n=== CHOIX DU MODE DE JEU ===");
        System.out.println("1 - Humain vs Humain");
        System.out.println("2 - Humain vs Ordinateur");
        System.out.println("3 - Ordinateur vs Ordinateur");
        System.out.print("Votre choix : ");

        while (true) {
            try {
                int choix = Integer.parseInt(scanner.nextLine());
                if (choix >= 1 && choix <= 3) return choix;
                System.out.print("Veuillez entrer un nombre entre 1 et 3 : ");
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, recommencez : ");
            }
        }
    }

    /**
     * Méthode générique pour demander un coup à un joueur (ligne et colonne).
     * Compatible avec TicTacToe, Gomoku et Puissance 4.
     */
    public int[] demanderCoup(Player joueur, int rows, int cols) {
        System.out.println("\nC'est à " + joueur.getRepresentation() + " de jouer !");
        int row = -1;
        int col = -1;

        while (true) {
            try {
                System.out.print("Entrez le numéro de ligne (0-" + (rows - 1) + ") : ");
                row = Integer.parseInt(scanner.nextLine());

                System.out.print("Entrez le numéro de colonne (0-" + (cols - 1) + ") : ");
                col = Integer.parseInt(scanner.nextLine());

                if (row >= 0 && row < rows && col >= 0 && col < cols)
                    return new int[]{row, col}; // Retourne le coup
                else
                    System.out.println("Coordonnées hors du plateau, recommencez !");
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez entrer des nombres entiers !");
            }
        }
    }

    /**
     * Menu principal : permet de choisir le jeu et de le lancer.
     */
    public void lancerMenuPrincipal() {
        while (true) {
            int choix = afficherMenuJeux();

            switch (choix) {
                case 1 -> {
                    System.out.println("\n=== TicTacToe ===");
                    Game jeu = new TicTacToe();  // <-- instanciation correcte
                    jeu.play();
                }
                case 2 -> {
                    System.out.println("\n=== Gomoku ===");
                    Game jeu = new Gomoku();     // <-- instanciation correcte
                    jeu.play();
                }
                case 3 -> {
                    System.out.println("\n=== Puissance 4 ===");
                    Game jeu = new Puissance4(); // <-- instanciation correcte
                    jeu.play();
                }
                case 0 -> {
                    System.out.println("Merci d'avoir joué ! À bientôt !");
                    return;
                }
            }
        }
    }
}