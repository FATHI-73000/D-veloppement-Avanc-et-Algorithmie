package model;

public class Cell {
    String value;



    public Cell() {

        this.value = "   ";
    }

  public String getRepresentation(){

    return this.value;


    }

  public void setValue(String representation) {
    this.value= representation;

  }

}
