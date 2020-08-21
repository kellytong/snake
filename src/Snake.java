import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private int lastX;
    private int lastY;
    private final int SPEED = 3;
    private final int BOARD_SIZE;
    private ArrayList<SnakePiece> snakePieces;
    private ArrayList<DotLocation> dots;
    private static final int EATING_DISTANCE = 10;


    public Snake(int lastX, int lastY, int boardSize, int board_size, ArrayList<SnakePiece> pieces, ArrayList<DotLocation> dots) {
        this.lastX = lastX;
        this.lastY = lastY;
        BOARD_SIZE = board_size;
        this.snakePieces = pieces;
        this.dots = dots;
    }

    public int getX() {
        return lastX;
    }

    public int getY() {
        return lastY;
    }

    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }

    public void moveSnake() {
        // follows piece before it --> snake body
        for (int i = snakePieces.size() - 1; i > 0; i--) {
            snakePieces.get(i).setX(snakePieces.get(i - 1).getX());
            snakePieces.get(i).setY(snakePieces.get(i - 1).getY());
        }

        // snake head
        int x = snakePieces.get(0).getX();
        int y = snakePieces.get(0).getY();
        SnakeDirection direction = snakePieces.get(0).getDirection();

        if (direction == SnakeDirection.RIGHT) {
            x = x + SPEED;
        } else if (direction == SnakeDirection.LEFT) {
            x = x - SPEED;
        } else if (direction == SnakeDirection.UP) {
            y = y - SPEED;
        } else {
            y = y + SPEED;
        }

        snakePieces.get(0).setX(x);
        snakePieces.get(0).setY(y);
    }

    public void eatApples() {
        for (int i = 0; i < dots.size(); i++) {
            int x = dots.get(i).getX();
            int y = dots.get(i).getY();

            if (Math.abs(x - snakePieces.get(0).getX()) < EATING_DISTANCE &&
                    Math.abs(y - snakePieces.get(0).getY()) < EATING_DISTANCE) {
                dots.remove(i);

                Random r = new Random();

                int randomX = r.nextInt(BOARD_SIZE);
                int randomY = r.nextInt(BOARD_SIZE);

                dots.add(new DotLocation(randomX, randomY));
                return;
            }
        }
    }
}
