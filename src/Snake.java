import java.awt.event.KeyEvent;

public class Snake{
    private int lastX;
    private int lastY;
    private final int SNAKE_LENGTH = 50;
    private final int SPEED = 3;
    private int boardSize;
    private SnakeDirection direction;

    public Snake(int lastX, int lastY, int boardSize) {
        this.lastX = lastX;
        this.lastY = lastY;
        this.boardSize = boardSize;
        direction = SnakeDirection.UP;
    }

    public int getX() {
        return lastX;
    }

    public int getY() {
        return lastY;
    }

    public void moveSnake() {
        int x = lastX;
        int y = lastY;

        if (direction == SnakeDirection.RIGHT) {
            x = lastX + SPEED;
        } else if (direction == SnakeDirection.LEFT) {
            x = lastX - SPEED;
        } else if (direction == SnakeDirection.UP) {
            y = lastY - SPEED;
        } else {
            y = lastY + SPEED;
        }

        lastX = x;
        lastY = y;
    }

    public void keyTyped(KeyEvent event) {

    }

}
