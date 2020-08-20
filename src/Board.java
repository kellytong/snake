import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JComponent{
    private int DOT_SIZE = 8;
    private Snake snake;

    private ArrayList<DotLocation> dotsLocation;

    public Board(ArrayList<DotLocation> dots, int size) {
        this.dotsLocation = dots;
        setOpaque(false);
        setSize(size, size);
        this.snake = new Snake(size / 2, size / 2, size);

        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {Thread.sleep(10);} catch (Exception ex) {}
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

        snake.moveSnake();
        g.fillRect(snake.getX(), snake.getY(), 10, 10);

    }

    public void gameOver() {

    }
}
