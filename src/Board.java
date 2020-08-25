import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends JComponent implements KeyListener {
    private int DOT_SIZE = 8;
    private Snake snake;
    private boolean isGameOver;
    private int boardSize;
    private static final int OFFSET = 3;
    private static final int BODY_SIZE = 20;

    private ArrayList<DotLocation> dotsLocation;

    public Board(ArrayList<DotLocation> dots, int size) {
        this.addKeyListener(this);
        setPreferredSize(new Dimension(size,size));
        this.setFocusable(true);

        isGameOver = false;
        boardSize = size;

        this.dotsLocation = dots;
        setOpaque(false);

        // create starting snake
        ArrayList<SnakePiece> snakePieces = new ArrayList<>();

        for (int i = 0; i < BODY_SIZE; i++) {
            snakePieces.add(new SnakePiece(boardSize / 2 - (OFFSET * i), boardSize / 2));
        }

        this.snake = new Snake(snakePieces, dotsLocation, SnakeDirection.RIGHT);

        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (!isGameOver) {
                    repaint();
                    gameEnd();
                    try {Thread.sleep(20);} catch (Exception ex) {}
                }
            }
        });

        animationThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isGameOver) {
            g.setColor(Color.PINK);
            g.drawString("Game Over -- Score: " + snake.getNumApples(), boardSize / 3, boardSize / 2);
        } else {
            for (DotLocation location : this.dotsLocation) {
                g.setColor(Color.RED);
                g.drawOval(location.getX(), location.getY(), DOT_SIZE, DOT_SIZE);
                g.fillOval(location.getX(), location.getY(), DOT_SIZE, DOT_SIZE);
            }

            g.setColor(Color.GREEN);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(10));

            updateDirection(snake.getSnakePieces());
            snake.moveSnake();
            snake.eatApples();
            for (SnakePiece piece : snake.getSnakePieces()) {
                g.fillRect(piece.getX(), piece.getY(), DOT_SIZE, DOT_SIZE);
            }
        }
    }

    private void updateDirection(ArrayList<SnakePiece> snakePieces) {
        for (int i = 0; i < snakePieces.size() - 1; i++) {
            SnakeDirection direction = snake.getDirection();
            if (direction != snake.getDirection()) {
                snake.setDirection(direction);
                return;
            }
        }
    }

    private void gameEnd() {
        SnakePiece snakeHead = snake.getSnakePieces().get(0);

        if (snakeHead.getX() > boardSize - main.BORDER_OFFSET || snakeHead.getX() < 0
                || snakeHead.getY() > boardSize - main.BORDER_OFFSET * 3 || snakeHead.getY() < 0) {
            isGameOver = true;
        } else {
            isGameOver = snake.touchesItself();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(SnakeDirection.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(SnakeDirection.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(SnakeDirection.UP);
        } else {
            snake.setDirection(SnakeDirection.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}