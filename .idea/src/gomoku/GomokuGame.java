package gomoku;

import game.Cell;
import game.Game;
import game.Player;
import view.View;
import player.ArtificialPlayer;
import java.util.Scanner;

/**
 * Implémentation du jeu Gomoku.
 * Le but est d'aligner 5 pierres (horizontalement, verticalement ou en diagonale) sur une grille de 15x15.
 */
public class GomokuGame implements Game {

    private final int size = 15;
    private final Cell[][] board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    /**
     * Constructeur du jeu Gomoku.
     * Initialise le plateau vide et les joueurs.
     * @param player1 Le premier joueur.
     * @param player2 Le deuxième joueur.
     */
    public GomokuGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Cell[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = new Cell();
    }

    /**
     * Lance la partie.
     */
    @Override
    public void play() {
        currentPlayer = player1;
        for (int turn = 0; turn < size * size; turn++) {
            View.afficherPlateau(board);
            System.out.println("Tour " + (turn + 1));
            System.out.println("Joueur actuel : " + currentPlayer.getRepresentation());

            int[] move;
            if (currentPlayer instanceof ArtificialPlayer) {
                move = ((ArtificialPlayer) currentPlayer).getMoveFromPlayer(this);
            } else {
                move = getMoveFromPlayer(currentPlayer);
            }

            board[move[0]][move[1]].setValue(currentPlayer.getRepresentation());

            if (isWinningMove(move[0], move[1])) {
                View.afficherPlateau(board);
                View.afficherGagnant(currentPlayer);
                return;
            }

            // Alternance des joueurs
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        View.afficherPlateau(board);
        View.afficherFinPlateauRempli();
    }

    /**
     * Affiche le plateau.
     */
    @Override
    public void display() {
        View.afficherPlateau(board);
    }

    /**
     * Indique si la partie est terminée.
     * @return true si la partie est terminée (victoire ou grille pleine), false sinon.
     */
    @Override
    public boolean isOver() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j].getRepresentation().equals(" "))
                    return false;
        return true;
    }

    /**
     * Demande au joueur humain de saisir un coup valide.
     * @param player Le joueur humain.
     * @return Un tableau contenant la ligne et la colonne du coup.
     */
    private int[] getMoveFromPlayer(Player player) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            try {
                System.out.print("Joueur " + player.getRepresentation() + ", ligne (0-14) : ");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Joueur " + player.getRepresentation() + ", colonne (0-14) : ");
                col = Integer.parseInt(scanner.nextLine());

                if (row < 0 || row >= size || col < 0 || col >= size) {
                    System.out.println("Coordonnées hors grille. Réessayez.");
                    continue;
                }
                if (!board[row][col].getRepresentation().equals(" ")) {
                    System.out.println("Case déjà occupée. Réessayez.");
                    continue;
                }
                return new int[]{row, col};
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer des nombres.");
            }
        }
    }

    /**
     * Vérifie si le dernier coup est une condition de victoire.
     * @param row Ligne du dernier coup joué.
     * @param col Colonne du dernier coup joué.
     * @return true si le joueur a aligné 5 pierres, false sinon.
     */
    private boolean isWinningMove(int row, int col) {
        String symbol = board[row][col].getRepresentation();
        return (countAligned(row, col, 0, 1, symbol) + countAligned(row, col, 0, -1, symbol) >= 4) ||  // Horizontal
                (countAligned(row, col, 1, 0, symbol) + countAligned(row, col, -1, 0, symbol) >= 4) ||  // Vertical
                (countAligned(row, col, 1, 1, symbol) + countAligned(row, col, -1, -1, symbol) >= 4) || // Diagonal \
                (countAligned(row, col, 1, -1, symbol) + countAligned(row, col, -1, 1, symbol) >= 4);   // Diagonal /
    }

    /**
     * Compte les cellules alignées dans une direction donnée.
     * @param row Ligne de départ.
     * @param col Colonne de départ.
     * @param dRow Direction ligne.
     * @param dCol Direction colonne.
     * @param symbol Symbole du joueur.
     * @return Le nombre de pierres alignées dans la direction donnée.
     */
    private int countAligned(int row, int col, int dRow, int dCol, String symbol) {
        int count = 0;
        int i = row + dRow;
        int j = col + dCol;
        while (i >= 0 && i < size && j >= 0 && j < size && board[i][j].getRepresentation().equals(symbol)) {
            count++;
            i += dRow;
            j += dCol;
        }
        return count;
    }

    /**
     * Accès au plateau de jeu.
     * @return la matrice des cases.
     */
    public Cell[][] getBoard() {
        return board;
    }
}
