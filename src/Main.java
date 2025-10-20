
    public class Main {
        public static void main(String[] args) {
            Cell thisCell = new Cell();
            System.out.println(thisCell.getRepresentation());

            Player[] players = new InteractionUtilisateur().choosePlayers();
            Player player1 = players[0];
            Player player2 = players[1];
            System.out.println("Joueur 1 : " + player1.getRepresentation());
            System.out.println("Joueur 2 : " + player2.getRepresentation());

            TicTacToe thisTicTacToe = new TicTacToe(player1, player2);
            thisTicTacToe.display();
            thisTicTacToe.setOwner(1, 1, player1);

            System.out.println("Plateau TicTacToe après capture de la case 11 :");
            System.out.println("Plateau TicTacToe");
            thisTicTacToe.display();

            thisTicTacToe.play(player1, player2);
        }
    }
