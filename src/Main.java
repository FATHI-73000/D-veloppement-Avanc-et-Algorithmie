//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Cell thisCell = new Cell();
        System.out.println(thisCell.getRepresentation());

        Player player1 = new Player(" X ");
        Player player2 = new Player(" O ");

        TicTacToe thisTicTacToe = new TicTacToe(player1,player2);
        thisTicTacToe.display();
        thisTicTacToe.setOwner(1, 1, player1);

        System.out.println("Plateau TicTacToe après capture de la case 11 :");
        System.out.println("Plateau TicTacToe");
        thisTicTacToe.display();

        thisTicTacToe.play(player1,player2);
    }



}
