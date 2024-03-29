# DiamondCircle
 Programming Languages 2 university project

Implement the game "DiamondCircle". The game is played on a matrix with minimum dimensions of 7x7 and maximum of 10x10. The game is played by a maximum of 4 players, and a minimum of 2. The dimensions of the matrix and the number of players are set before starting the application. It is necessary to validate user input.
Each player has a name (which must be unique) and owns four figures of the same color. Each figure is characterized by color and movement mode. There are three types of figures: ordinary figure, floating figure, and super-fast figure. Each figure can be red, green, blue, or yellow. An ordinary figure passes the specified number of fields, as does a floating figure. A super-fast figure passes twice as many fields as the specified number. An ordinary and super-fast figure can fall into a hole, while a floating figure remains floating above the hole. At the beginning of the game, each player is given four randomly selected figures of the same color.
In addition to the figures that players use, there is also a "ghost" figure - it starts its movement when the first player does and moves along a path "in the background," placing bonus fields - diamonds, on the path. A random number of diamonds are placed ranging from 2 to the matrix dimensions, at random positions. Placement is done every 5 seconds and lasts until the end of the game. When a figure encounters a diamond, it "picks it up," and in the continuation of the game, during its movement, the number of fields it crosses increases by the number of diamonds it has collected.
Figures move along the matrix path shown in the image below. The matrix has minimum dimensions of 7x7.
In the displayed matrix case, the starting position is taken as the 4th. The order of players is determined randomly, and players take turns playing. Moving a figure from one position to another is considered a move. When moving, make sure that if the field to which the figure should move is already occupied, the figure is placed on the next free field. Moving from field to field should take one second. The movement mode is determined based on a randomly selected card from a deck of 52 cards. There is a regular card and a special card. A regular card consists of an image and the number of fields the figure crosses. The special card only has an image on it. When a special card is drawn, holes are created at n places on the path. Holes are black. After drawing, the card returns to the deck. Drawing is considered to be the display of the card on the GUI. If a figure is on a hole and it is not a floating figure, it falls. When a figure falls, if the player has more figures, they start over. The game ends when all players run out of figures – i.e., each of the player's figures has reached the goal (field 25 in the example) or all have fallen. For each figure, remember information about the fields crossed, as well as about the movement time. When displaying a figure on a field, it is necessary for the color and type of figure to be visible in a certain way. The game can be stopped and restarted. The game proceeds automatically. At the end of the game, the results are saved in text files named as GAME_current_time.txt in the following format:
The graphical part of the application layout is suggested in the image:
By clicking on the "Show list of result files" button, a new form is opened where the list of files is displayed in tabular form. By clicking on a specific file, its content is displayed to the user in an arbitrary way.
The "Game duration" and "Description of card meaning" are refreshed every second. The number of squares on the card represents the number of fields the figure needs to cross. The movement is shown in the center matrix.
Be sure to use the Logger class to handle exceptions in all classes.

Some images of the user interface:
1. Configuration image

![Game config](https://github.com/VladanPasagic/DiamondCircle_PL2/assets/93393767/4f33d10a-e371-441d-a2a4-9054c5153d0d)

![Name config](https://github.com/VladanPasagic/DiamondCircle_PL2/assets/93393767/ca39c26c-62d6-45ee-bba1-19daa28fcee3)

2. Gameplay image

![Gameplay](https://github.com/VladanPasagic/DiamondCircle_PL2/assets/93393767/80978483-b3da-41c3-b381-45aa61b91a25)

3. Gamelist image

![Gamelist and single game](https://github.com/VladanPasagic/DiamondCircle_PL2/assets/93393767/31839022-eea3-4af1-bef2-209accefdca6)

4. Figure movement matrix

![Figure movement](https://github.com/VladanPasagic/DiamondCircle_PL2/assets/93393767/525426de-13a6-4f47-a8eb-65276453cd42)


