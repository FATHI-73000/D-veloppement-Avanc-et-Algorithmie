
package model;

// Déclaration d'une classe abstraite Player
// "abstract" : cette classe ne peut pas être instanciée directement, elle sert de modèle pour ses enfants
public abstract class Player {

    // Variable protégée pour stocker le symbole du joueur ("X" ou "O")
    // "protected" : accessible dans les classes qui héritent de Player
    protected String representation;

    // Constructeur public de la classe Player
    // "String representation" : paramètre pour initialiser le symbole du joueur
    public Player(String representation) {
        // "this.representation" fait référence à l'attribut de l'objet
        this.representation = representation;
    }

    // Méthode abstraite que toutes les classes enfants doivent implémenter
    // "@abstract" n'existe pas en Java, on utilise juste "abstract" devant la méthode
    // Elle doit retourner un tableau d'entiers [ligne, colonne]
    public abstract int[] getMoveFromPlayer();

    // Méthode publique pour récupérer la représentation du joueur
    public String getRepresentation() {
        return representation;
    }


}
