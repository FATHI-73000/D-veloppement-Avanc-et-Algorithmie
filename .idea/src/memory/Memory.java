package memory;

import game.Game;

public class Memory implements Game {

    @Override
    public void play() {
        System.out.println("Démarrage du jeu Memory (en cours de développement).");
    }

    @Override
    public void display() {
        // Affichage du plateau de cartes
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
