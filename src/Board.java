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
            snakePieces.add(new SnakePiece(boardSize / 2 - (OFFSET * i), boardSize / 2, SnakeDirection.RIGHT));
        }

        this.snake = new Snake(size / 2, size / 2, size, boardSize, snakePieces, dotsLocation);

        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (!gameEnd()) {
                    repaint();
                    try {Thread.sleep(20);} catch (Exception ex) {}
                }
            }
        });

        animationThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
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

    private void updateDirection(ArrayList<SnakePiece> snakePieces) {
        for (int i = 0; i < snakePieces.size() - 1; i++) {
            SnakeDirection direction = snakePieces.get(i).getDirection();
            if (direction != snakePieces.get(i + 1).getDirection()) {
                snakePieces.get(i + 1).setDirection(direction);
                return;
            }
        }
    }

    private boolean gameEnd() {
        if (snake.getX() > boardSize || snake.getX() < 0 || snake.getY() > boardSize || snake.getY() < 0) {
            isGameOver = true;
        }
        return isGameOver;
    }

    private void checkWalls() {
        if (snake.getX() > boardSize) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.getSnakePieces().get(0).setDirection(SnakeDirection.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.getSnakePieces().get(0).setDirection(SnakeDirection.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.getSnakePieces().get(0).setDirection(SnakeDirection.UP);
        } else {
            snake.getSnakePieces().get(0).setDirection(SnakeDirection.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}