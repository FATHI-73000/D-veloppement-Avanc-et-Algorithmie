    package model;

    import player.ArtificialPlayer;
    import player.HumanPlayer;
    import view.InteractionUtilisateur;
    import view.View;

    /**
     * Classe représentant le jeu TicTacToe (Morpion).
     */
    public class TicTacToe implements Game {

        private int size = 3; // Taille du plateau (3x3)
        private Cell[][] board; // Plateau de jeu
        private Player player1; // Joueur 1 (humain ou IA)
        private Player player2; // Joueur 2 (humain ou IA)
        private InteractionUtilisateur interaction; // Objet pour interagir avec l'utilisateur

        /**
         * Constructeur qui initialise le plateau et les joueurs selon le mode choisi.
         */
        public TicTacToe() {
            this.interaction = new InteractionUtilisateur(); // Création de l'objet pour récupérer les coups

            int choice = interaction.demanderModeJeu(); // Demande du mode de jeu (1, 2 ou 3)

            // Initialisation des joueurs selon le choix
            if (choice == 1) {
                player1 = new HumanPlayer(" X ", interaction); // Joueur 1 humain
                player2 = new HumanPlayer(" O ", interaction); // Joueur 2 humain
            } else if (choice == 2) {
                player1 = new HumanPlayer(" X ", interaction); // Joueur 1 humain
                player2 = new ArtificialPlayer(" O "); // Joueur 2 IA
            } else if (choice == 3) {
                player1 = new ArtificialPlayer(" X "); // Joueur 1 IA
                player2 = new ArtificialPlayer(" O "); // Joueur 2 IA
            }

            // Initialisation du plateau vide
            board = new Cell[size][size]; // Création du tableau 3x3
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = new Cell(); // Chaque case est initialisée vide
                }
            }
        }

        /**
         * Affiche le plateau via la classe View.
         */
        public void display() {
            View.afficherPlateau(board); // Affichage du plateau
        }

        @Override
        public boolean isOver() {
            return false;
        }

        /**
         * Récupère un coup valide pour le joueur donné.
         * Corrigé pour appeler demanderCoup avec 3 arguments.
         */
        public int[] getMoveFromPlayer(Player player) {
            int row = -1, col = -1; // Coordonnées initiales invalides

            while (true) { // Boucle jusqu'à obtenir un coup valide
                int[] move = interaction.demanderCoup(player, size, size); // <-- CORRIGÉ : passer la taille du plateau
                row = move[0]; // Ligne choisie
                col = move[1]; // Colonne choisie

                // Vérifie que le coup est dans les limites
                if (row < 0 || row >= size || col < 0 || col >= size) {
                    View.afficherMessage("Coordonnées hors du plateau, recommencez.");
                    continue;
                }

                // Vérifie que la case est libre
                if (!board[row][col].getRepresentation().equals("   ")) {
                    View.afficherMessage("Case déjà occupée, recommencez.");
                    continue;
                }

                break; // Coup valide trouvé
            }

            return new int[]{row, col}; // Retourne le coup valide
        }

        /**
         * Place le symbole du joueur sur la case (row, col).
         */
        public void setOwner(int row, int col, Player player) {
            board[row][col].setValue(player.getRepresentation()); // Marque la case
        }

        /**
         * Boucle principale du jeu.
         */
        public void play() {
            Player current = player1; // Joueur courant
            int moves = 0; // Compteur de coups joués

            while (true) {
                display(); // Affiche le plateau
                int[] move = getMoveFromPlayer(current); // Récupère un coup valide
                setOwner(move[0], move[1], current); // Applique le coup
                moves++; // Incrémente le compteur

                // Vérifie si le jeu est terminé
                if (isOver(move[0], move[1], current)) {
                    display();
                    System.out.println("Fin du jeu !");
                    return; // Fin de la partie
                }

                // Change de joueur
                current = (current == player1) ? player2 : player1;
            }
        }

        /**
         * Vérifie si le joueur a gagné ou si le plateau est plein (match nul)
         *
         * @param row    Ligne du dernier coup
         * @param col    Colonne du dernier coup
         * @param player Joueur ayant joué
         * @return true si victoire ou match nul, false sinon
         */
        public boolean isOver(int row, int col, Player player) {
            String symbol = player.getRepresentation(); // Symbole du joueur

            // Vérification de la ligne
            boolean ligneGagne = true;
            for (int j = 0; j < size; j++) {
                if (!board[row][j].getRepresentation().equals(symbol)) {
                    ligneGagne = false;
                    break;
                }
            }
            if (ligneGagne) return true;

            // Vérification de la colonne
            boolean colonneGagne = true;
            for (int i = 0; i < size; i++) {
                if (!board[i][col].getRepresentation().equals(symbol)) {
                    colonneGagne = false;
                    break;
                }
            }
            if (colonneGagne) return true;

            // Vérification diagonale principale
            if (row == col) {
                boolean diag1Gagne = true;
                for (int i = 0; i < size; i++) {
                    if (!board[i][i].getRepresentation().equals(symbol)) {
                        diag1Gagne = false;
                        break;
                    }
                }
                if (diag1Gagne) return true;
            }

            // Vérification diagonale secondaire
            if (row + col == size - 1) {
                boolean diag2Gagne = true;
                for (int i = 0; i < size; i++) {
                    if (!board[i][size - 1 - i].getRepresentation().equals(symbol)) {
                        diag2Gagne = false;
                        break;
                    }
                }
                if (diag2Gagne) return true;
            }

            // Vérifie si le plateau est plein (match nul)
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j].getRepresentation().equals("   ")) {
                        return false; // Au moins une case libre → partie continue
                    }
                }
            }

            return true; // Plateau rempli → match nul
        }

    }
