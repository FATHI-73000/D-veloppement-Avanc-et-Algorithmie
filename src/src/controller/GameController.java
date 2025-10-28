package controller;

// Import de l'interface Game représentant un jeu générique
import model.Game;

// Import de la classe abstraite Player représentant un joueur
import model.Player;

// Import de la classe InteractionUtilisateur pour récupérer les coups des joueurs humains
import view.InteractionUtilisateur;

// Import de la classe View pour afficher le plateau et les messages
import view.View;

/**
 * Contrôleur générique abstrait pour gérer une partie de jeu avec machine à états.
 * Cette classe ne lit jamais System.in et n'affiche jamais System.out directement.
 */
public abstract class GameController {

    // ================================
    // Attributs principaux
    // ================================



    protected Game game;
    // protected : accessible dans cette classe et les classes qui héritent de GameController
    // Game : type de la variable, ici un jeu abstrait
    // game : nom de la variable représentant le jeu actuel

    protected Player player1, player2;
    // Deux joueurs participant à la partie

    protected InteractionUtilisateur interaction;
    // Objet pour récupérer les coups des joueurs humains

    // ================================
    // Machine à états
    // ================================

    protected enum GameState {
        START,         // Début de la partie
        PLAYER1_TURN,  // Tour du joueur 1
        PLAYER2_TURN,  // Tour du joueur 2
        GAME_OVER      // Fin de la partie
    }

    protected GameState state;
    // Attribut pour stocker l'état actuel du jeu

    // ================================
    // Constructeur
    // ================================

    public GameController(Game game, Player player1, Player player2,
                          InteractionUtilisateur interaction) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.interaction = interaction;
        this.state = GameState.START; // On commence au début
    }

    // ================================
    // Boucle principale de jeu
    // ================================

    public void play() {
        // Boucle infinie qui sera stoppée quand l'état sera GAME_OVER
        while (state != GameState.GAME_OVER) {
            switch (state) {
                case START:
                    // Initialisation ou affichage du plateau au début
                    displayBoard();
                    // Passer directement au tour du joueur 1
                    state = GameState.PLAYER1_TURN;
                    break;

                case PLAYER1_TURN:
                    playTurn(player1);
                    // Vérifier si le jeu est terminé après le coup
                    if (game.isOver()) {
                        state = GameState.GAME_OVER;
                    } else {
                        state = GameState.PLAYER2_TURN;
                    }
                    break;

                case PLAYER2_TURN:
                    playTurn(player2);
                    // Vérifier si le jeu est terminé après le coup
                    if (game.isOver()) {
                        state = GameState.GAME_OVER;
                    } else {
                        state = GameState.PLAYER1_TURN;
                    }
                    break;

                case GAME_OVER:
                    // Affichage du gagnant ou égalité
                    View.afficherFinPartie(determineWinner());
                    break;
            }
        }
    }

    // ================================
    // Méthode pour jouer un tour d'un joueur
    // ================================

    private void playTurn(Player currentPlayer) {
        int[] move;
        int rows = getRows(); // abstrait, défini dans la sous-classe
        int cols = getCols(); // abstrait, défini dans la sous-classe

        if (currentPlayer instanceof player.HumanPlayer) {
            move = interaction.demanderCoup(currentPlayer, rows, cols);
            ((player.HumanPlayer) currentPlayer).setLastMove(move[0], move[1]);
        } else {
            move = currentPlayer.getMoveFromPlayer();
        }

        applyMove(currentPlayer, move);
        displayBoard();
    }

    // Ajouter ces méthodes abstraites à la classe GameController
    protected abstract int getRows();
    protected abstract int getCols();

    // ================================
    // Méthodes abstraites que chaque contrôleur spécifique doit implémenter
    // ================================

    protected abstract Player getCurrentPlayer();
    // Retourne le joueur dont c'est le tour (utilisé si nécessaire)

    protected abstract void displayBoard();
    // Affiche le plateau de jeu

    protected abstract String determineWinner();
    // Retourne le gagnant ou null si égalité

    protected abstract void applyMove(Player player, int[] move);
    // Applique un coup sur le plateau
}
