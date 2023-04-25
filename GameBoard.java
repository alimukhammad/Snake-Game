import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class GameBoard extends JPanel {
    private Snake snake;

    public GameBoard(Snake snake) {
        this.snake = snake;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (Point p : snake.getBody()) {
            g.fillRect(p.x, p.y, 10, 10);
        }
    }
}
