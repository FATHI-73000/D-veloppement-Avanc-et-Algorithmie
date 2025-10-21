package game;


public class Cell {
    private String value;

    public Cell() {
        this.value = "   ";
    }

    public String getRepresentation() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
