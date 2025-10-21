package pfc;


import game.Game;

public class PFCGame implements Game {

    @Override
    public void play() {
        System.out.println("Pierre - Feuille - Ciseaux : à implémenter !");
    }

    @Override
    public void display() {

    }

    @Override
    public boolean isOver() {
        return true;
    }
}
