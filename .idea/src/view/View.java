package view;

import game.Cell;
import game.Player;

/**
 * Classe utilitaire pour l'affichage du jeu.
 * Permet d'afficher le plateau, les messages et les informations liées aux joueurs.
 */
public class View {

    /**
     * Affiche le plateau de jeu.
     * @param board Le plateau sous forme de matrice de Cellules.
     */
    public static void afficherPlateau(Cell[][] board) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getRepresentation());
                if (j < size - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (i < size - 1)
                System.out.println("---+---+---");
        }
    }

    /**
     * Affiche un message simple dans la console.
     * @param message Le message à afficher.
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Affiche le joueur dont c'est le tour.
     * @param currentPlayer Le joueur courant.
     */
    public static void afficherTour(Player currentPlayer) {
        System.out.println("C'est au tour du joueur " + currentPlayer.getRepresentation());
    }

    /**
     * Affiche le joueur gagnant à la fin de la partie.
     * @param player Le joueur gagnant.
     */
    public static void afficherGagnant(Player player) {
        System.out.println("Fin de la partie ! Le joueur " + player.getRepresentation() + " a gagné !");
    }

    /**
     * Affiche un message indiquant que le plateau est rempli et que la partie est finie.
     */
    public static void afficherFinPlateauRempli() {
        System.out.println("Fin de la partie, toutes les cases sont remplies.");
    }
}
