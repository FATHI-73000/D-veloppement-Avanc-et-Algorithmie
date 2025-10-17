import java.util.Scanner;

public class InteractionUtilisateur {
    private Scanner scanner;

    public InteractionUtilisateur() {
        scanner = new Scanner(System.in);
    }

    // Demande si on veut démarrer une nouvelle partie
    public boolean demanderNouvellePartie() {
        System.out.println("Voulez-vous commencer une nouvelle partie ? (o/n)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        return reponse.equals("o");
    }

    // Demande les coordonnées du coup pour un joueur humain
    public int[] demanderCoup(String representation) {
        int row = -1, col = -1;
        while (true) {
            try {
                System.out.print("Joueur " + representation + ", ligne (0-2) : ");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Joueur " + representation + ", colonne (0-2) : ");
                col = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, réessayez.");
            }
        }
        return new int[]{row, col};
    }

    // Choix du mode de jeu
    public Player[] choosePlayers() {
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1 : Humain vs Humain");
        System.out.println("2 : Humain vs IA");
        System.out.println("3 : IA vs IA");

        int choix = -1;
        while (true) {
            try {
                choix = Integer.parseInt(scanner.nextLine());
                if (choix < 1 || choix > 3) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide, réessayez (1, 2 ou 3) :");
            }
        }

        Player player1;
        Player player2;

        switch (choix) {
            case 1 -> {
                player1 = new Player(" X ");
                player2 = new Player(" O ");
            }
            case 2 -> {
                player1 = new Player(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            default -> {
                player1 = new ArtificialPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
        }

        return new Player[]{player1, player2};
    }
}
