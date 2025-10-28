package model;

import player.HumanPlayer;
import player.ArtificialPlayer;
import view.InteractionUtilisateur;
import view.View;

public class Puissance4 implements Game {

    private final int ROWS = 6;
    private final int COLS = 7;
    private final int WIN_COUNT = 4;

    private Cell[][] board;
    private Player player1, player2;
    private InteractionUtilisateur interaction;

    public Puissance4() {
        interaction = new InteractionUtilisateur();

        int choice = interaction.demanderModeJeu();

        if (choice == 1) {
            player1 = new HumanPlayer(" X ", interaction);
            player2 = new HumanPlayer(" O ", interaction);
        } else if (choice == 2) {
            player1 = new HumanPlayer(" X ", interaction);
            player2 = new ArtificialPlayer(" O ");
        } else {
            player1 = new ArtificialPlayer(" X ");
            player2 = new ArtificialPlayer(" O ");
        }

        board = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                board[i][j] = new Cell();
    }

    @Override
    public void display() {
        View.afficherPlateau(board);
    }

    @Override
    public void play() {
        Player current = player1;

        while (true) {
            display();
            int[] move = interaction.demanderCoup(current, ROWS, COLS); // ✅ correction
            int col = move[1];

            if (!isValidMove(col)) {
                View.afficherMessage("Colonne invalide ou pleine, réessayez !");
                continue;
            }

            int row = getLowestEmptyRow(col);
            board[row][col].setValue(current.getRepresentation());

            if (isOver(row, col, current)) {
                display();
                View.afficherMessage("Victoire de " + current.getRepresentation() + " !");
                return;
            }

            if (isBoardFull()) {
                display();
                View.afficherMessage("Match nul !");
                return;
            }

            current = (current == player1) ? player2 : player1;
        }
    }

    private boolean isValidMove(int col) { // Méthode privée qui retourne vrai si le coup est valide
        if (col < 0 || col >= COLS) { // Vérifie si la colonne choisie est hors limites
            return false; // Renvoie false car la colonne n'est pas valide
        }
        // Parcourt toutes les lignes de la colonne pour vérifier s'il y a au moins une case vide
        for (int row = 0; row < ROWS; row++) { // Boucle sur chaque ligne
            if (board[row][col].getRepresentation().equals("   ")) { // Vérifie si la case est vide
                return true; // Si une case vide est trouvée, le coup est valide
            }
        }
        return false; // Si aucune case vide, la colonne est pleine → coup non valide
    }

    private int getLowestEmptyRow(int col) {
        for (int r = ROWS - 1; r >= 0; r--)
            if (board[r][col].getRepresentation().equals("   "))
                return r;
        return -1;
    }

    private boolean isBoardFull() {
        for (int j = 0; j < COLS; j++)
            if (board[0][j].getRepresentation().equals("   "))
                return false;
        return true;
    }

    private int countAlign(int row, int col, int dRow, int dCol, String s) {
        int count = 1;
        int r = row + dRow, c = col + dCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c].getRepresentation().equals(s)) {
            count++;
            r += dRow;
            c += dCol;
        }
        r = row - dRow;
        c = col - dCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c].getRepresentation().equals(s)) {
            count++;
            r -= dRow;
            c -= dCol;
        }
        return count;
    }

    public boolean isOver(int row, int col, Player player) {
        String s = player.getRepresentation();

        return countAlign(row, col, 1, 0, s) >= WIN_COUNT ||
                countAlign(row, col, 0, 1, s) >= WIN_COUNT ||
                countAlign(row, col, 1, 1, s) >= WIN_COUNT ||
                countAlign(row, col, 1, -1, s) >= WIN_COUNT;
    }


    public boolean isOver() {
        return false;
    }
}
