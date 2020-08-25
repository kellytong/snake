import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private final int SPEED = 3;
    private final int BOARD_SIZE = 500;
    private ArrayList<SnakePiece> snakePieces;
    private ArrayList<DotLocation> dots;
    private static final int EATING_DISTANCE = 10;
    private static final int OFFSET = 3;
    private SnakeDirection snakeDirection;
    private static final int SNAKE_GROWTH = 5;
    private int numApples;


    public Snake(ArrayList<SnakePiece> pieces, ArrayList<DotLocation> dots, SnakeDirection direction) {
        this.snakePieces = pieces;
        this.dots = dots;
        this.snakeDirection = direction;
        numApples = 0;
    }

    public int getNumApples() {return numApples; }

    public ArrayList<SnakePiece> getSnakePieces() {
        return snakePieces;
    }

    public SnakeDirection getDirection() {
        return snakeDirection;
    }

    public void setDirection(SnakeDirection direction) {
        this.snakeDirection = direction;
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

        if (snakeDirection == SnakeDirection.RIGHT) {
            x = x + SPEED;
        } else if (snakeDirection == SnakeDirection.LEFT) {
            x = x - SPEED;
        } else if (snakeDirection == SnakeDirection.UP) {
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
                numApples++;

                Random r = new Random();

                int randomX = r.nextInt(BOARD_SIZE);
                int randomY = r.nextInt(BOARD_SIZE);

                dots.add(new DotLocation(randomX, randomY));

                growSnake();
                System.out.println(snakePieces.size());
                return;
            }
        }
    }

    private void growSnake() {
        int lastPiece = snakePieces.size() - 1;
        int newX = snakePieces.get(lastPiece).getX();
        int newY = snakePieces.get(lastPiece).getY();

        for (int i = 0; i < SNAKE_GROWTH; i++) {
            if (snakeDirection == SnakeDirection.LEFT) {
                snakePieces.add(new SnakePiece(newX + (OFFSET * i), newY));
            } else if (snakeDirection == SnakeDirection.RIGHT) {
                snakePieces.add(new SnakePiece(newX - (OFFSET * i), newY));
            } else if (snakeDirection == SnakeDirection.UP) {
                snakePieces.add(new SnakePiece(newX, newY + (OFFSET * i)));
            } else {
                snakePieces.add(new SnakePiece(newX, newY - (OFFSET * i)));
            }
        }
    }

    public boolean touchesItself() {
        int xHead = snakePieces.get(0).getX();
        int yHead = snakePieces.get(0).getY();

        for (int i = 1; i < snakePieces.size(); i++) {
            int xPiece = snakePieces.get(i).getX();
            int yPiece = snakePieces.get(i).getY();

            if (Math.abs(xPiece - xHead) < OFFSET && Math.abs(yPiece - yHead) < OFFSET) {
                return true;
            }
        }
        return false;
    }
}
