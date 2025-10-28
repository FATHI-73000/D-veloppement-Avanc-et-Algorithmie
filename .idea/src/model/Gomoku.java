package model;

import player.HumanPlayer;
import player.ArtificialPlayer;
import view.InteractionUtilisateur;
import view.View;

public class Gomoku implements Game {

    private final int size = 15;
    private final int WIN_COUNT = 5;

    private Cell[][] board;
    private Player player1, player2;
    private InteractionUtilisateur interaction;

    public Gomoku() {
        interaction = new InteractionUtilisateur();

        int mode = interaction.demanderModeJeu();

        if (mode == 1) {
            player1 = new HumanPlayer(" X ", interaction);
            player2 = new HumanPlayer(" O ", interaction);
        } else if (mode == 2) {
            player1 = new HumanPlayer(" X ", interaction);
            player2 = new ArtificialPlayer(" O ");
        } else {
            player1 = new ArtificialPlayer(" X ");
            player2 = new ArtificialPlayer(" O ");
        }

        board = new Cell[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = new Cell();
    }

    @Override
    public void display() {
        View.afficherPlateau(board);
    }

    @Override
    public void play() {
        Player current = player1;
        int moves = 0;

        while (true) {
            display();
            int[] move = interaction.demanderCoup(current, size, size); // ✅ correction
            int row = move[0];
            int col = move[1];

            if (!isValidMove(row, col)) {
                View.afficherMessage("Coup invalide !");
                continue;
            }

            board[row][col].setValue(current.getRepresentation());
            moves++;

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

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size &&
                board[row][col].getRepresentation().equals("   ");
    }

    private boolean isBoardFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j].getRepresentation().equals("   "))
                    return false;
        return true;
    }

    public boolean isOver(int row, int col, Player player) {
        String s = player.getRepresentation();

        return count(row, col, 1, 0, s) >= WIN_COUNT ||
                count(row, col, 0, 1, s) >= WIN_COUNT ||
                count(row, col, 1, 1, s) >= WIN_COUNT ||
                count(row, col, 1, -1, s) >= WIN_COUNT;
    }

    private int count(int row, int col, int dRow, int dCol, String s) {
        int count = 1;
        int r = row + dRow, c = col + dCol;
        while (r >= 0 && r < size && c >= 0 && c < size && board[r][c].getRepresentation().equals(s)) {
            count++;
            r += dRow;
            c += dCol;
        }
        r = row - dRow;
        c = col - dCol;
        while (r >= 0 && r < size && c >= 0 && c < size && board[r][c].getRepresentation().equals(s)) {
            count++;
            r -= dRow;
            c -= dCol;
        }
        return count;
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
