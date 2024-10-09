# Snake-Game
This Java code is a basic implementation of the classic Snake Game using the Swing library for graphics and user interaction. Here's a breakdown of its structure and logic:

Key Components:
Imports:

javax.swing.* is used for creating the graphical user interface.
java.awt.* provides drawing and event handling capabilities.
java.util.Random is used to generate random positions for the food.
Class Declaration:

SnakeGame extends JPanel and implements KeyListener. This means it handles graphics drawing (via JPanel) and keyboard input (via KeyListener).
Game Constants:

WIDTH, HEIGHT, and SQUARE_SIZE define the dimensions of the game screen and the size of each square (both for the snake and the food).
Game State Variables:

snake[][]: A 2D array representing the snake's body segments, where each element stores the x and y coordinates of a segment.
food[]: An array holding the x and y coordinates of the food.
direction: An integer representing the direction of the snake's movement (1 for right, 2 for left, 3 for up, 4 for down).
score: Keeps track of the player's score.
gameOver: A boolean indicating if the game is over.
Timer and Game Loop:

A Timer is initialized to update the game state and redraw the game panel every 100 milliseconds.
ActionListener within the timer triggers updateGame() and repaint() regularly to keep the game running.
Game Initialization (Constructor):

setPreferredSize, setBackground, and setFocusable configure the game panel's size and settings.
A random food position is generated at the start.
The timer is started to keep updating the game.
Game Logic:

Food Generation (generateFood): Randomly places the food at a grid-aligned position on the game board.
Game Update (updateGame):
Moves the snake based on the current direction.
Checks for collisions with the borders of the game area or with the snake itself.
Checks if the snake eats food, increasing the score and generating new food.
Graphics (paintComponent):

Draws the snake, food, score, and game-over message if the game ends.
Keyboard Input:

The keyPressed method is used to change the snake's direction based on arrow key presses (VK_RIGHT, VK_LEFT, VK_UP, VK_DOWN).
