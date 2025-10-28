package controller;
// "package" : mot-clé Java pour définir un regroupement de classes.
// "controller" : le nom du paquetage, ici il regroupe tous les contrôleurs du jeu.

import model.TicTacToe;
// "import" : permet d'utiliser des classes d'autres packages.
// "model.TicTacToe" : importe la classe qui contient la logique du jeu Tic Tac Toe.

import model.Player;
// Importe la classe représentant un joueur du jeu.

import view.InteractionUtilisateur;
// Importe la classe pour gérer les interactions avec l'utilisateur (saisie, choix...).

import view.View;
// Importe la classe de la vue pour afficher des informations à l'écran.

/**
 * Contrôleur spécifique pour le jeu Tic Tac Toe
 * Il fait le lien entre le modèle (TicTacToe) et la vue (View + InteractionUtilisateur)
 */
public class TicTacToeController {
    // "public" : la classe est accessible depuis n'importe quel autre paquetage.
    // "class" : mot-clé pour définir une classe.
    // "TicTacToeController" : nom de la classe, ici le contrôleur qui coordonne jeu et interface.

    private TicTacToe game;
    // "private" : accessible uniquement à l'intérieur de cette classe.
    // "TicTacToe" : type, le modèle du jeu.
    // "game" : nom de la variable qui représente l'instance du jeu.

    private Player player1;
    // Variable pour le joueur 1.

    private Player player2;
    // Variable pour le joueur 2.

    private InteractionUtilisateur interaction;
    // Variable pour gérer la communication avec l'utilisateur.

    private int turnCount = 0;
    // Compteur de tours, initialisé à 0.
    // Sert à savoir quel joueur doit jouer.

    // Constructeur
    public TicTacToeController(TicTacToe game, Player player1, Player player2, InteractionUtilisateur interaction) {
        // "public" : accessible depuis l'extérieur.
        // Le constructeur initialise le contrôleur avec le jeu, les joueurs et l'interaction utilisateur.
        this.game = game;
        // "this" : fait référence à l'objet courant. Ici on assigne le paramètre "game" à l'attribut "game".
        this.player1 = player1;
        // Idem pour player1
        this.player2 = player2;
        // Idem pour player2
        this.interaction = interaction;
        // Idem pour l'objet InteractionUtilisateur
    }

    // Retourne le joueur courant selon le nombre de tours
    private Player getCurrentPlayer() {
        // Méthode privée qui retourne le joueur actuel.
        return (turnCount % 2 == 0) ? player1 : player2;
        // Si le nombre de tours est pair, c'est player1, sinon player2.
    }

    // Affiche le plateau via le modèle et la vue
    private void displayBoard() {
        // Méthode privée pour afficher le plateau.
        game.display();
        // Appelle la méthode display() du modèle pour montrer le plateau.
        // Cette méthode utilise View.afficherPlateau(board) à l'intérieur.
    }

    // Lancer la partie
    public void play() {
        // Méthode publique qui fait jouer le jeu.
        View.afficherMessage("=== Début du jeu Tic Tac Toe ===");
        // Affiche un message de début de jeu.
        Player current;
        // Variable locale pour le joueur courant.

        while (true) {
            // Boucle infinie jusqu'à ce qu'on sorte avec "break".
            displayBoard();
            // Affiche le plateau.
            current = getCurrentPlayer();
            // Récupère le joueur courant.

            // Récupère le coup du joueur via la méthode du modèle
            int[] move = game.getMoveFromPlayer(current);
            // Demande au joueur son coup (coordonnées), retourne un tableau [ligne, colonne].

            // Applique le coup sur le plateau
            game.setOwner(move[0], move[1], current);
            // Met à jour le plateau avec le joueur courant aux coordonnées choisies.
            turnCount++;
            // Incrémente le compteur de tours.

            // Vérifie si la partie est terminée
            if (game.isOver(move[0], move[1], current)) {
                // Vérifie si le joueur courant a gagné ou si le plateau est plein.
                displayBoard();
                // Affiche le plateau final.
                View.afficherMessage("Fin du jeu !");
                // Message de fin de partie.
                View.afficherMessage("Le joueur " + current.getRepresentation() + " a gagné ou match nul !");
                // Message indiquant le résultat (victoire ou égalité).
                break;
                // Sort de la boucle while, fin de la partie.
            }

            // Passe automatiquement au joueur suivant grâce à turnCount
            // Aucun code supplémentaire nécessaire car getCurrentPlayer() gère le changement de joueur.
        }
    }
}
