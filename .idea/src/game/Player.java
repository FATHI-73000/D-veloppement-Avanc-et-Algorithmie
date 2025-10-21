package game;

public abstract class Player {
    private String representation;

    public Player(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public abstract int[] getMoveFromPlayer(Game game);
}
