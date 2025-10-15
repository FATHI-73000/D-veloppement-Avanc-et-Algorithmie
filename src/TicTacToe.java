import java.util.Scanner;

public class TicTacToe {

    private final int size = 3; // taille du plateau
    private Cell[][] board;
    Player Player ;

    // Constructeur
    public TicTacToe(Player player) {
        this.Player = player;
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

    public Player getPlayer() {
        return Player;
    }
    public int[] getMoveFromPlayer() {
        Scanner sc = new Scanner(System.in);
        int row, col;

        while (true) { // répète tant que le joueur n'a pas choisi une case valide
            System.out.print("Joueur " + Player.getRepresentation() + ", ligne : ");
            row = sc.nextInt(); // lit directement un entier

            System.out.print("Joueur " + Player.getRepresentation() + ", colonne : ");
            col = sc.nextInt(); // lit directement un entier

            // Vérifie que la ligne et la colonne sont dans le plateau
            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Coordonnées hors plateau, réessayez.");
                continue;
            }

            // Vérifie que la case est libre
            if (!board[row][col].getRepresentation().equals("   ")) {
                System.out.println("Case déjà occupée, réessayez.");
                continue;
            }

            break; // coup valide trouvé
        }

        return new int[]{row, col};
    }
    public void setOwner(int row, int col, Player player) {
        // Récupère la représentation du joueur : " X " ou " O "
        String symbol = player.getRepresentation();

        // Modifie la cellule correspondante pour y mettre le symbole du joueur
        board[row][col].setValue(symbol);
    }

}



