package view; // Déclare le package dans lequel se trouve cette classe

import java.util.Scanner; // Importation de Scanner pour lire les entrées utilisateur
import model.Cell;        // Importation de la classe Cell du modèle
import model.Game;        // Importation de l'interface Game
import model.Puissance4;  // Importation de la classe Puissance4

public class View {

    private static Scanner scanner = new Scanner(System.in); // Scanner statique pour lire les entrées utilisateur

    // Méthode pour afficher le menu principal et gérer le choix de l'utilisateur
    public static void afficherMenu() {
        while (true) { // Boucle infinie pour revenir au menu après chaque partie
            System.out.println("\n=== MENU DES JEUX ==="); // Affiche le titre du menu
            System.out.println("1. Puissance 4");        // Option 1
            System.out.println("2. Quitter");            // Option 2
            System.out.print("Choisissez un jeu : ");    // Invite l'utilisateur à choisir

            int choix; // Variable pour stocker le choix de l'utilisateur
            try {
                choix = Integer.parseInt(scanner.nextLine()); // Lit une ligne et la convertit en entier
            } catch (NumberFormatException e) { // Si l'utilisateur entre autre chose qu'un nombre
                System.out.println("Entrée invalide !");
                continue; // Reboucle pour redemander le choix
            }

            switch (choix) { // Gestion des différentes options
                case 1:
                    Game jeu = new Puissance4(); // Crée une partie de Puissance 4
                    jeu.play();                  // Lance le jeu
                    break;
                case 2:
                    System.out.println("Au revoir !"); // Message de sortie
                    System.exit(0);                   // Quitte l'application
                    break;
                default:
                    System.out.println("Choix invalide !"); // Message si l'utilisateur entre un autre chiffre
            }
        }
    }

    // Méthode pour afficher le plateau de Puissance 4
    public static void afficherPlateau(Cell[][] board) {
        int rows = board.length;     // Nombre de lignes du plateau
        int cols = board[0].length;  // Nombre de colonnes du plateau

        for (int i = 0; i < rows; i++) {       // Parcourt toutes les lignes
            for (int j = 0; j < cols; j++) {   // Parcourt toutes les colonnes
                System.out.print(board[i][j].getRepresentation()); // Affiche la représentation de la cellule
                if (j < cols - 1) System.out.print("|");          // Ajoute un séparateur vertical sauf à la fin
            }
            System.out.println(); // Passe à la ligne suivante

            if (i < rows - 1) { // Si ce n'est pas la dernière ligne
                for (int j = 0; j < cols; j++) {
                    System.out.print("---");      // Dessine les séparateurs horizontaux
                    if (j < cols - 1) System.out.print("+"); // Ajoute un "+" entre les colonnes
                }
                System.out.println(); // Passe à la ligne suivante
            }
        }
        System.out.println(); // Ligne vide pour l'esthétique
    }

    // Méthode pour afficher un message simple
    public static void afficherMessage(String message) {
        System.out.println(message); // Affiche le message passé en paramètre
    }

    // Méthode pour afficher la fin d'une partie
    public static void afficherFinPartie(String winner) {
        if (winner != null && !winner.isEmpty()) { // Si un joueur a gagné
            System.out.println("Le joueur " + winner + " a gagné !");
        } else { // Sinon c'est un match nul
            System.out.println("Match nul !");
        }
    }
}
