import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class main {
    private static final int BOARD_SIZE = 500; // TODO change to size
    private static final Color BOARD_COLOR = Color.BLACK;
    private static final int NUMBER_OF_DOTS = 5;

    public static void main(String[] args) {
        System.out.println("Snake Game");

        JFrame f=new JFrame();//creating instance of JFrame
        f.getContentPane().setBackground(BOARD_COLOR);

        f.setSize(BOARD_SIZE,BOARD_SIZE);

        ArrayList<DotLocation>  dotsList = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < NUMBER_OF_DOTS; i++) {

            int randomX = r.nextInt(BOARD_SIZE);
            while (randomX < 4 || randomX > BOARD_SIZE - 4) {
                randomX = r.nextInt(BOARD_SIZE);
            }
            int randomY = r.nextInt(BOARD_SIZE);
            while (randomY < 4 || randomY > BOARD_SIZE - 4) {
                randomY = r.nextInt(BOARD_SIZE);
            }

            dotsList.add(new DotLocation(randomX, randomY));
            System.out.println("x:" + randomX + " y:" + randomY);
        }

        Board board = new Board(dotsList, BOARD_SIZE);
        f.add(board);

        f.setVisible(true);//making the frame visible
    }

    public static void makeDot() {

    }
}