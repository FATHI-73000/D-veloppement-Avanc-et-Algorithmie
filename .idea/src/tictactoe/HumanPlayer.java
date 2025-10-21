package tictactoe;

import game.Game;
import game.Player;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(String representation) {
        super(representation);
        this.scanner = new Scanner(System.in);
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

        while (true) {
            try {
                System.out.print("Joueur " + getRepresentation() + ", entrez la ligne (0-2) : ");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Joueur " + getRepresentation() + ", entrez la colonne (0-2) : ");
                col = Integer.parseInt(scanner.nextLine());

                // Vérification des coordonnées
                if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                    System.out.println(" Coordonnées invalides, réessayez !");
                    continue;
                }

                // Vérification que la case est libre
                if (!game.getBoard()[row][col].getRepresentation().equals("   ")) {
                    System.out.println(" Case déjà occupée, choisissez une autre !");
                    continue;
                }

                break; // coordonnées valides
            } catch (NumberFormatException e) {
                System.out.println(" Entrée invalide, veuillez entrer des nombres !");
            }
        }

        return new int[]{row, col};
    }
}
