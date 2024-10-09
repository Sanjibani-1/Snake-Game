import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JPanel implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SQUARE_SIZE = 20;

    private int[][] snake;
    private int[] food;
    private int direction;
    private int score;
    private boolean gameOver;

    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        snake = new int[100][2];
        food = new int[2];
        direction = 1; // right
        score = 0;
        gameOver = false;

        generateFood();

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        timer.start();
    }

    private void generateFood() {
        Random random = new Random();
        food[0] = random.nextInt(WIDTH / SQUARE_SIZE) * SQUARE_SIZE;
        food[1] = random.nextInt(HEIGHT / SQUARE_SIZE) * SQUARE_SIZE;
    }

    private void updateGame() {
        if (gameOver) {
            return;
        }

        // move snake
        for (int i = snake.length - 1; i > 0; i--) {
            snake[i][0] = snake[i - 1][0];
            snake[i][1] = snake[i - 1][1];
        }

        if (direction == 1) { // right
            snake[0][0] += SQUARE_SIZE;
        } else if (direction == 2) { // left
            snake[0][0] -= SQUARE_SIZE;
        } else if (direction == 3) { // up
            snake[0][1] -= SQUARE_SIZE;
        } else if (direction == 4) { // down
            snake[0][1] += SQUARE_SIZE;
        }

        // check collision with border
        if (snake[0][0] < 0 || snake[0][0] >= WIDTH || snake[0][1] < 0 || snake[0][1] >= HEIGHT) {
            gameOver = true;
        }

        // check collision with self
        for (int i = 1; i < snake.length; i++) {
            if (snake[0][0] == snake[i][0] && snake[0][1] == snake[i][1]) {
                gameOver = true;
            }
        }

        // check if snake eats food
        if (snake[0][0] == food[0] && snake[0][1] == food[1]) {
            score++;
            generateFood();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw snake
        for (int i = 0; i < snake.length; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(snake[i][0], snake[i][1], SQUARE_SIZE, SQUARE_SIZE);
        }

        // draw food
        g.setColor(Color.RED);
        g.fillRect(food[0], food[1], SQUARE_SIZE, SQUARE_SIZE);

        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);

        // draw game over message
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2 - 24);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT && direction != 2) {
            direction = 1;
        } else if (keyCode == KeyEvent.VK_LEFT && direction != 1) {
            direction = 2;
        } else if (keyCode == KeyEvent.VK_UP && direction != 4) {
            direction = 3;
        } else if (keyCode == KeyEvent.VK_DOWN && direction != 3) {
            direction = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}