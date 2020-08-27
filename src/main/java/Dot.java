import javax.swing.*;
import java.awt.*;

public class Dot extends JPanel{
    private int size;
    private Color dotColor;
    private int x;
    private int y;

    public Dot(int size, Color dotColor, int x, int y) {
        this.size = size;
        this.dotColor = dotColor;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(dotColor);
        setOpaque(false);
        g.drawOval(x, y, size, size);
        g.fillOval(x, y, size, size);
    }
}
