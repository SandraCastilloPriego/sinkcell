Cellular Automaton Simulator (SINKCELL), developed by Sandra Castillo and Jorge González Casanovas. The project is a new insight of a spatially structured cellular automaton based on general rules of Conway "Game of Life".
Cellular automaton consist of an infinite number of cells, which have a finite number of states. These states change depending on the state of their neighbours. Each cell has the same rule for updating.


Sink

In this program, the cells can have two states: 0 or 1. This state depends on the state of their neighbours and the place where the cell is.
These are the default update rules:
> . If both the cell and path are 1, and the cell has eight neighbours, the cell becomes 0.
> . If the cell is 1, path is 0 and the cell has more than three neighbours, the cell changes to 0.
> . If both the cell and path are 0, and the cell has more than three neighbours, the cell changes to 1.
> . If the cell is 0, path is 1 and the cell has more than two neighbours, the cell becomes 1.
The user can change all the parameters of the program in the configuration menu.
