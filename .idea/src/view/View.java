 package view;
 import game.Cell;
 import game.Player;

public class View {


    public static void afficherPlateau(Cell[][] board) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getRepresentation());
                if (j < size - 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) System.out.println("---+---+---");
        }
    }

    // Affiche un message simple
    public static void print(String message) {
        System.out.println(message);
    }

    // Affiche le joueur courant
    public static void afficherTour(Player currentPlayer) {
        System.out.println("C'est au tour du joueur " + currentPlayer.getRepresentation());
    }

    // Affiche le gagnant
    public static void afficherGagnant(Player player) {
        System.out.println("Fin de la partie ! Le joueur " + player.getRepresentation() + " a gagné !");
    }

    // Affiche message fin partie (plateau rempli)
    public static void afficherFinPlateauRempli() {
        System.out.println("Fin de la partie, toutes les cases sont remplies.");
    }
}
