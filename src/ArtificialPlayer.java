import java.util.Random;

public class ArtificialPlayer extends Player {
    private Random random;

    public ArtificialPlayer(String representation) {
        super(representation);
        random = new Random();
    }

    public int[] getMoveFromPlayer(TicTacToe game) {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!game.board[row][col].getRepresentation().equals("   "));
        System.out.println("IA joue en " + row + "," + col);
        return new int[]{row, col};
    }
}
