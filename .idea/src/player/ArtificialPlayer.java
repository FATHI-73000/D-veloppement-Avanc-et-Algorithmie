
package player;

// On importe la classe abstraite Player
import model.Player;

// On importe la classe Cell si nécessaire pour vérifier le plateau
import model.Cell;

// On importe Random pour générer des mouvements aléatoires
import java.util.Random;

// Déclaration de la classe ArtificialPlayer qui hérite de Player
public class ArtificialPlayer extends Player {

    // Création d'un objet Random pour générer des nombres aléatoires
    private Random rand = new Random();

    // Constructeur de la classe ArtificialPlayer
    public ArtificialPlayer(String representation) {
        // Appelle le constructeur de Player pour initialiser le symbole du joueur
        super(representation);
    }

    // Méthode pour générer un mouvement aléatoire sur le plateau
    // Retourne un tableau d'entiers [ligne, colonne]
    // "Cell[][] board" : le plateau de jeu
    // "int size" : la taille du plateau
    public int[] getRandomMove(Cell[][] board, int size) {
        int row, col; // Déclaration des variables pour la ligne et la colonne

        // Boucle infinie jusqu'à trouver une case vide
        while (true) {
            // Génère un entier aléatoire pour la ligne
            row = rand.nextInt(size);
            // Génère un entier aléatoire pour la colonne
            col = rand.nextInt(size);

            // Vérifie si la case est vide
            if (board[row][col].getRepresentation().equals("  ")) {
                // Si oui, retourne les coordonnées
                return new int[]{row, col};
            }
            // Sinon, continue la boucle
        }
    }

    // Implémentation obligatoire de la méthode abstraite de Player
    @Override
    public int[] getMoveFromPlayer() {
        // Ici, on pourrait appeler getRandomMove avec la taille et le plateau
        // Mais comme on n'a pas le plateau ici, on peut lancer une exception ou la gérer ailleurs
        return null; // On laisse null pour l'instant, à remplacer dans le Controller
    }
}
