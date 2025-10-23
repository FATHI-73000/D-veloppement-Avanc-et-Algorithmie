package puissance4;

import game.Game;
import game.Player;
import view.View;
import java.util.Scanner;

/**
 * Implémentation du jeu Puissance 4.
 * Le but est d'aligner 4 jetons identiques sur une grille de 6 lignes et 7 colonnes.
 */
public class Puissance4Game implements Game {

    private final int ROWS = 6;
    private final int COLS = 7;
    private final String[][] board;

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    /**
     * Constructeur du jeu Puissance 4.
     * @param player1 Premier joueur.
     * @param player2 Deuxième joueur.
     */
    public Puissance4Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new String[ROWS][COLS];
        initializeBoard();
    }

    /**
     * Initialise la grille vide.
     */
    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                board[i][j] = " ";
    }

    /**
     * Lance la partie de Puissance 4.
     */
    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        currentPlayer = player1;
        boolean gameOver = false;

        while (!gameOver) {
            display();
            System.out.println("Tour du joueur : " + currentPlayer.getRepresentation());
            int col = getValidColumn(scanner);

            // Place le jeton dans la colonne choisie
            int row = placeToken(col, currentPlayer.getRepresentation());

            // Vérifie la victoire
            if (isWinningMove(row, col)) {
                display();
                System.out.println("🎉 Le joueur " + currentPlayer.getRepresentation() + " a gagné !");
                gameOver = true;
            }
            // Vérifie si la grille est pleine
            else if (isOver()) {
                display();
                System.out.println("Match nul ! La grille est pleine.");
                gameOver = true;
            }
            // Change de joueur
            else {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }

    /**
     * Affiche la grille.
     */
    @Override
    public void display() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println(" 0 1 2 3 4 5 6 ");
        System.out.println();
    }

    /**
     * Vérifie si la grille est pleine.
     */
    @Override
    public boolean isOver() {
        for (int j = 0; j < COLS; j++)
            if (board[0][j].equals(" "))
                return false;
        return true;
    }

    /**
     * Demande une colonne valide au joueur.
     */
    private int getValidColumn(Scanner scanner) {
        int col;
        while (true) {
            System.out.print("Choisissez une colonne (0 à 6) : ");
            try {
                col = Integer.parseInt(scanner.nextLine());
                if (col < 0 || col >= COLS) {
                    System.out.println("Colonne invalide. Réessayez.");
                } else if (!board[0][col].equals(" ")) {
                    System.out.println("Colonne pleine. Réessayez.");
                } else {
                    return col;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    /**
     * Fait tomber le jeton dans la colonne choisie.
     */
    private int placeToken(int col, String symbol) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col].equals(" ")) {
                board[row][col] = symbol;
                return row;
            }
        }
        return -1; // (ne devrait jamais arriver)
    }

    /**
     * Vérifie si le joueur vient d'aligner 4 jetons.
     */
    private boolean isWinningMove(int row, int col) {
        String symbol = board[row][col];
        return (countAligned(row, col, 0, 1, symbol) + countAligned(row, col, 0, -1, symbol) >= 3) ||  // Horizontal
                (countAligned(row, col, 1, 0, symbol) >= 3) ||                                           // Vertical
                (countAligned(row, col, 1, 1, symbol) + countAligned(row, col, -1, -1, symbol) >= 3) || // Diagonale ↘
                (countAligned(row, col, 1, -1, symbol) + countAligned(row, col, -1, 1, symbol) >= 3);   // Diagonale ↗
    }

    /**
     * Compte le nombre de jetons identiques alignés dans une direction donnée.
     */
    private int countAligned(int row, int col, int dRow, int dCol, String symbol) {
        int count = 0;
        int i = row + dRow;
        int j = col + dCol;

        while (i >= 0 && i < ROWS && j >= 0 && j < COLS && board[i][j].equals(symbol)) {
            count++;
            i += dRow;
            j += dCol;
        }
        return count;
    }
}
