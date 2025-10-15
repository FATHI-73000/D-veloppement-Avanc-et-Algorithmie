                ---------------------------
                │          Game           │
                │-------------------------│
                │ - players : Player[]    │  ← liste des 2 joueurs
                │ - board : Board         │  ← plateau 3x3
                │ - currentTurn : int     │  ←  c’est le tour du joueur actuel
                │ - isFinished : bool     │  ← la partie est-elle finie ?
                │-------------------------│
                │ + start()               │  ← lance la partie
                │ + playMove(x,y)         │     ← joue un coup
                │ + checkWinner()         |  ← vérifie si quelqu’un a gagné
                |  + switchTurn()         │ ← changer de joueur
                ---------------------------
                             │
            -----------------------------------
             ▼                                ▼
     ---------------                   ---------------
     │ HumanPlayer |                   |              |
     │ 1 ou 2      |                   |    AIPlayer  │
     │-------------│                   │------------- │
     │ + getInput)│  ← reçoit           + computeMove() │ ← calcule les coups 
     ---------------                  ----------------     automatiquement
             ▲    les coups du joueur                      avec un algorithme.
             │
        ------------
        │  Player  │
        │----------│
        │ - id     │
        │ - name   │
        │ - symbol │  ← x et y ou vide position joueur sur le plateau.
        ------------

               --------------------
                │      Board      │
                │-----------------│
                │ - size : int:3  │  ← taille du plateau
                │ - cells : Cell[] │ ← tableau de 3x3 cellules
                │-----------------│
                │ + reset()       │  ← remet le plateau à zéro
                │ + getCell(x,y)  │
                │ + setCell(x,y,v)│<- v = valeur ce qu’on veut mettre dans la case (x et 
                                                                      y ou  vide ).
                -------------------
                           │
                           ▼
                    -----------
                    │  Cell   │
                    │---------│
                    │ Player  │  ←      
                    │         │
                    │         │
                    ------------
