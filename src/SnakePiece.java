public class SnakePiece {
    private int x;
    private int y;

    public SnakePiece(int x, int y) {
        this.x = x;
        this.y = y;
        //this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}