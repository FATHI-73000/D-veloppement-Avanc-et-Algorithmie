package tictactoe;

import game.Cell;
import game.Game;
import game.Player;
import player.ArtificialPlayer;

/**
 * Implémentation du jeu TicTacToe.
 * Gère le plateau, les joueurs, les tours, et la vérification de fin de partie.
 */
public class TicTacToeGame implements Game {

    private final int size = 3;
    private Cell[][] board;
    private Player inputPlayer;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    /**
     * Constructeur initialisant un plateau vide et les joueurs.
     *
     * @param player1 Premier joueur.
     * @param player2 Deuxième joueur.
     */
    public TicTacToeGame(Player player1, Player player2) {
        this.inputPlayer = player2;
        this.player1 = player1;
        this.player2 = player2;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Retourne le plateau de jeu.
     * @return Une matrice de cellules représentant le plateau.
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Méthode de jeu non utilisée dans cette implémentation.
     */
    @Override
    public void play() {
        // non utilisé dans cette implémentation
    }

    /**
     * Lance la partie avec les deux joueurs spécifiés.
     * Alterne les tours jusqu'à la fin de la partie.
     *
     * @param player1 Premier joueur.
     * @param player2 Deuxième joueur.
     */
    public void play(Player player1, Player player2) {
        currentPlayer = player1;
        for (int turn = 0; turn < size * size; turn++) {
            System.out.println("tour " + (turn + 1));
            display();
            System.out.println("c'est au tour du joueur " + currentPlayer.getRepresentation());
            int[] move;
            if (currentPlayer instanceof ArtificialPlayer) {
                move = ((ArtificialPlayer) currentPlayer).getMoveFromPlayer(this);
            } else {
                inputPlayer = currentPlayer;
                move = getMoveFromPlayer();
            }
            setOwner(move[0], move[1], currentPlayer);
            if (isOver()) {
                System.out.println("Fin de la partie ! Le joueur " + currentPlayer.getRepresentation() + " a gagné !");
                display();
                return;
            }
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
        System.out.println("Fin de la partie, toutes les cases sont remplies.");
        display();
    }

    /**
     * Attribue une case du plateau à un joueur.
     * @param row Ligne de la case.
     * @param col Colonne de la case.
     * @param player Joueur qui prend possession de la case.
     */
    public void setOwner(int row, int col, Player player) {
        board[row][col].setValue(player.getRepresentation());
    }

    /**
     * Demande au joueur courant de saisir un coup valide.
     * @return Un tableau contenant la ligne et la colonne choisies.
     */
    public int[] getMoveFromPlayer() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int row, col;
        while (true) {
            System.out.print("Joueur " + inputPlayer.getRepresentation() + ", ligne : ");
            row = sc.nextInt();
            System.out.print("Joueur " + inputPlayer.getRepresentation() + ", colonne : ");
            col = sc.nextInt();
            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Coordonnées hors plateau, réessayez.");
                continue;
            }
            if (!board[row][col].getRepresentation().equals(" ")) {
                System.out.println("Case déjà occupée, réessayez.");
                continue;
            }
            break;
        }
        return new int[]{row, col};
    }

    /**
     * Affiche le plateau de jeu dans la console.
     */
    @Override
    public void display() {
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
     * Vérifie si la partie est terminée (victoire ou plateau rempli).
     * @return true si la partie est terminée, false sinon.
     */
    @Override
    public boolean isOver() {
        for (int i = 0; i < size; i++) {
            if (!board[i][0].getRepresentation().equals(" ") &&
                    board[i][0].getRepresentation().equals(board[i][1].getRepresentation()) &&
                    board[i][0].getRepresentation().equals(board[i][2].getRepresentation())) {
                return true;
            }
        }
        for (int j = 0; j < size; j++) {
            if (!board[0][j].getRepresentation().equals(" ") &&
                    board[0][j].getRepresentation().equals(board[1][j].getRepresentation()) &&
                    board[0][j].getRepresentation().equals(board[2][j].getRepresentation())) {
                return true;
            }
        }
        if (!board[0][0].getRepresentation().equals(" ") &&
                board[0][0].getRepresentation().equals(board[1][1].getRepresentation()) &&
                board[0][0].getRepresentation().equals(board[2][2].getRepresentation())) {
            return true;
        }
        if (!board[0][2].getRepresentation().equals(" ") &&
                board[0][2].getRepresentation().equals(board[1][1].getRepresentation()) &&
                board[0][2].getRepresentation().equals(board[2][0].getRepresentation())) {
            return true;
        }
        for (
