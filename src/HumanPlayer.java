import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer(String representation) {
        super(representation);
        this.scanner = new Scanner(System.in);
    }

    public int[] getMoveFromPlayer(TicTacToe game) {
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
                if (!game.board[row][col].getRepresentation().equals("   ")) {
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
