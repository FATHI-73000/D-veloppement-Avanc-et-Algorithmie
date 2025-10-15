//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         Cell thisCell = new Cell();

          System.out.println(thisCell.getRepresentation());

          TicTacToe thisTicTacToe = new TicTacToe();
        thisTicTacToe.setCell(0, 0, " F ");
        thisTicTacToe.setCell(0, 1, " A ");
        thisTicTacToe.setCell(0, 2, " T ");
        thisTicTacToe.setCell(1, 0, " H ");
        thisTicTacToe.setCell(1, 1, " I ");
        thisTicTacToe.setCell(1, 2, " F ");
        thisTicTacToe.setCell(2, 0, " A ");
        thisTicTacToe.setCell(2, 1, " T ");
        thisTicTacToe.setCell(2, 2, " H ");

        System.out.println("Plateau TicTacToe");
          thisTicTacToe.display();
    }
    }