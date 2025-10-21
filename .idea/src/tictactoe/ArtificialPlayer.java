package tictactoe;

import game.Game;
import game.Player;
import java.util.Random;

public class ArtificialPlayer extends Player {
    private Random random;

    public ArtificialPlayer(String representation) {
        super(representation);
        random = new Random();
    }

    @Override
    public int[] getMoveFromPlayer(Game game) {
        // Est-ce que le jeu est un TicTacToe ?
        if (game instanceof TicTacToeGame) {
            // Oui, on demande au joueur de saisir un coup pour le TicTacToe
            return getMoveFromPlayer((TicTacToeGame) game);
        }
        // Sinon, on ne sait pas gérer ce jeu, on retourne un coup "vide"
        return new int[]{-1, -1};
    }

    public int[] getMoveFromPlayer(TicTacToeGame game) {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!game.getBoard()[row][col].getRepresentation().equals("   "));
        System.out.println("IA joue en " + row + "," + col);
        return new int[]{row, col};
    }
}
