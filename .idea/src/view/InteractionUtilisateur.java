package view;

import game.Game;
import game.Player;
import memory.Memory;
import pfc.PFCGame;
import player.ArtificialPlayer;
import player.HumanPlayer;

import java.util.Scanner;

/**
 * Classe gérant l'interaction avec l'utilisateur.
 * Permet de choisir les joueurs, démarrer une nouvelle partie et afficher le menu des jeux.
 */
public class InteractionUtilisateur {

    private Scanner scanner;

    /**
     * Constructeur initialisant le scanner pour la saisie utilisateur.
     */
    public InteractionUtilisateur() {
        scanner = new Scanner(System.in);
    }

    /**
     * Demande à l'utilisateur s'il souhaite commencer une nouvelle partie.
     * @return true si l'utilisateur souhaite commencer, false sinon.
     */
    public boolean demanderNouvellePartie() {
        System.out.println("Voulez-vous commencer une nouvelle partie ? (o/n)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        return reponse.equals("o");
    }

    /**
     * Permet à l'utilisateur de choisir le mode de jeu (Humain vs Humain, Humain vs IA, IA vs IA).
     * @return Un tableau contenant les deux joueurs sélectionnés.
     */
    public Player[] choosePlayers() {
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1 : Humain vs Humain");
        System.out.println("2 : Humain vs IA");
        System.out.println("3 : IA vs IA");
        int choix = -1;
        while (true) {
            try {
                choix = Integer.parseInt(scanner.nextLine());
                if (choix < 1 || choix > 3)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide, réessayez (1, 2 ou 3) :");
            }
        }
        Player player1;
        Player player2;
        switch (choix) {
            case 1 -> {
                player1 = new HumanPlayer(" X ");
                player2 = new HumanPlayer(" O ");
            }
            case 2 -> {
                player1 = new HumanPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            default -> {
                player1 = new ArtificialPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
        }
        return new Player[]{player1, player2};
    }

    /**
     * Affiche le menu des jeux disponibles et permet de lancer le jeu choisi.
     */
    public void afficherMenuJeux() {
        while (true) {
            System.out.println("\n=== Menu des Jeux ===");
            System.out.println("1 : Rejouer à TicTacToe");
            System.out.println("2 : Lancer Memory");
            System.out.println("3 : Lancer Pierre-Feuille-Ciseaux");
            System.out.println("0 : Quitter");
            System.out.print("Votre choix : ");
            int choix;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                continue;
            }
            switch (choix) {
                case 1 -> {
                    Player[] newPlayers = choosePlayers();
                    Game newGame = new tictactoe.TicTacToeGame(newPlayers[0], newPlayers[1]);
                    newGame.play();
                }
                case 2 -> {
                    Game memory = new Memory();
                    memory.play();
                }
                case 3 -> {
                    Game pfc = new PFCGame();
                    pfc.play();
                }
                case 0 -> {
                    System.out.println("Fin du programme.");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }
}
