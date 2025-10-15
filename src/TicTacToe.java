public class TicTacToe {

    private final int size = 3; // taille du plateau
    private Cell[][] board;      // plateau de cellules

    // Constructeur
    public TicTacToe() {
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
                System.out.print(board[i][j].getRepresentation()); // affiche la case
                if (j < size - 1) System.out.print("|");           // séparateur de colonnes
            }
            System.out.println(); // nouvelle ligne après chaque ligne du plateau
            if (i < size - 1) {
                System.out.println("---+---+---"); // séparateur de lignes
            }
        }
    }
    // Méthode pour modifier une cellule
    public void setCell(int i, int j, String s) {
        board[i][j].setValue(s);
    }
}
