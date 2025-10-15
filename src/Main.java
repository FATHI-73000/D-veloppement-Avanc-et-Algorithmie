import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         Cell thisCell = new Cell();

          System.out.println(thisCell.getRepresentation());


          Player player = new Player("X");


          TicTacToe thisTicTacToe = new TicTacToe(player);
        thisTicTacToe.setCell(0, 0, " F ");
        thisTicTacToe.setCell(0, 1, " A ");
        thisTicTacToe.setCell(0, 2, " T ");
        thisTicTacToe.setCell(1, 0, " H ");
        thisTicTacToe.setCell(1, 1, " I ");
        thisTicTacToe.setCell(1, 2, " F ");
        thisTicTacToe.setCell(2, 0, " A ");
        thisTicTacToe.setCell(2, 1, " T ");
        thisTicTacToe.setCell(2, 2, " H ");

        thisTicTacToe.setOwner(1, 1, player);
        System.out.println("Plateau TicTacToe après capture de la case 1 1 :");
        System.out.println("Plateau TicTacToe");
          thisTicTacToe.display();
    }
    }