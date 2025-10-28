
package player;

// On importe la classe abstraite Player depuis le package model
import model.Player;
import view.InteractionUtilisateur;

// Déclaration de la classe HumanPlayer
// "extends Player" signifie que HumanPlayer hérite de la classe abstraite Player
public class HumanPlayer extends Player {

    // Déclaration d'une variable privée pour stocker le dernier coup joué par l'utilisateur
    // "private" : accessible uniquement dans cette classe
    // "int[]" : tableau d'entiers pour [ligne, colonne]
    private int[] lastMove;

    // Constructeur public de la classe HumanPlayer
    // "public" : accessible depuis n'importe quelle autre classe
    // "HumanPlayer(String representation)" : le constructeur reçoit le symbole du joueur ("X" ou "O")
    public HumanPlayer(String representation, InteractionUtilisateur interaction) {
        // Appel du constructeur de la classe parente Player pour initialiser le symbole
        super(representation);
    }

    // Méthode publique pour que le Controller "injecte" le coup choisi par l'utilisateur
    // "void" : ne retourne rien
    // "setLastMove(int row, int col)" : prend en paramètre la ligne et la colonne du coup
    public void setLastMove(int row, int col) {
        // On crée un tableau contenant les coordonnées [ligne, colonne] et on le stocke dans lastMove
        this.lastMove = new int[]{row, col};
    }

    // Implémentation obligatoire de la méthode abstraite de Player
    // "@Override" : on remplace la méthode abstraite de la classe parente
    @Override
    public int[] getMoveFromPlayer() {
        // Retourne le dernier coup choisi par l'utilisateur
        return lastMove;
    }

}
