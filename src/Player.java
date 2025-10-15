public class Player {
   String representation;


   public Player(String representation) {
       if ( representation.equals ("X") || representation.equals("O"))
           this.representation = representation;
       else {
           throw new IllegalArgumentException("La représentation doit être \"X\" ou \"O\"");
       }
   }

public String getRepresentation() {

return representation;


}





}
