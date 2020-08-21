public class SnakePiece {
    private int x;
    private int y;
    private SnakeDirection direction;

    public SnakePiece(int x, int y, SnakeDirection direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }
}