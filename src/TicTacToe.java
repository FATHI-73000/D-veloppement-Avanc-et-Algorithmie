public class TicTacToe {

    private final int size = 3;
    protected Cell[][] board;
    private Player Player;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe(Player player1, Player player) {
        this.Player = player;
        this.player1 = player1;
        this.player2 = player;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getRepresentation());
                if (j < size - 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) System.out.println("---+---+---");
        }
    }

    public void setCell(int i, int j, String s) {
        board[i][j].setValue(s);
    }

    public Player getPlayer() {
        return Player;
    }

    public int[] getMoveFromPlayer() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int row, col;

        while (true) {
            System.out.print("Joueur " + Player.getRepresentation() + ", ligne : ");
            row = sc.nextInt();
            System.out.print("Joueur " + Player.getRepresentation() + ", colonne : ");
            col = sc.nextInt();

            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Coordonnées hors plateau, réessayez.");
                continue;
            }

            if (!board[row][col].getRepresentation().equals("   ")) {
                System.out.println("Case déjà occupée, réessayez.");
                continue;
            }

            break;
        }

        return new int[]{row, col};
    }

    public void setOwner(int row, int col, Player player) {
        String symbol = player.getRepresentation();
        board[row][col].setValue(symbol);
    }

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
                Player = currentPlayer;
                move = getMoveFromPlayer();
            }

            setOwner(move[0], move[1], currentPlayer);

            if (isOver()) {
                System.out.println("Fin de la partie ! Le joueur " + currentPlayer.getRepresentation() + " a gagné !");
                display();
                return;
            }

            // Remplacement du ternaire par un if/else
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        System.out.println("Fin de la partie, toutes les cases sont remplies.");
        display();
    }

    public boolean isOver() {
        for (int i = 0; i < size; i++) {
            if (!board[i][0].getRepresentation().equals("   ") &&
                    board[i][0].getRepresentation().equals(board[i][1].getRepresentation()) &&
                    board[i][0].getRepresentation().equals(board[i][2].getRepresentation())) {
                return true;
            }
        }

        for (int j = 0; j < size; j++) {
            if (!board[0][j].getRepresentation().equals("   ") &&
                    board[0][j].getRepresentation().equals(board[1][j].getRepresentation()) &&
                    board[0][j].getRepresentation().equals(board[2][j].getRepresentation())) {
                return true;
            }
        }

        if (!board[0][0].getRepresentation().equals("   ") &&
                board[0][0].getRepresentation().equals(board[1][1].getRepresentation()) &&
                board[0][0].getRepresentation().equals(board[2][2].getRepresentation())) {
            return true;
        }

        if (!board[0][2].getRepresentation().equals("   ") &&
                board[0][2].getRepresentation().equals(board[1][1].getRepresentation()) &&
                board[0][2].getRepresentation().equals(board[2][0].getRepresentation())) {
            return true;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getRepresentation().equals("   ")) {
                    return false;
                }
            }
        }

        return true;
    }
}
