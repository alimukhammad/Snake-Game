import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class SnakeGameFrame extends JFrame {
    private JPanel gameBoard;
    private Snake snake;

    public SnakeGameFrame() {
        gameBoard = new GameBoard();
        snake = new Snake();
        add(gameBoard);

        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                snake.move();
                gameBoard.repaint();
            }
        });
        timer.start();

        gameBoard.setFocusable(true);
        gameBoard.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        snake.setDirection(SnakeDirection.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setDirection(SnakeDirection.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.setDirection(SnakeDirection.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setDirection(SnakeDirection.RIGHT);
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class GameBoard extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.GREEN);
            for (Point p : snake.getBody()) {
                g.fillRect(p.x, p.y, 10, 10);
            }

            g.setColor(Color.RED);
            g.fillRect(snake.getFood().x, snake.getFood().y, 10, 10);
        }

        @Override
        public int getWidth() {
            return 300;
        }

        @Override
        public int getHeight() {
            return 300;
        }
    }

    private enum SnakeDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private class Snake {
        private LinkedList<Point> body;
        private Point food;
        private SnakeDirection direction;

        public Snake() {
            body = new LinkedList<Point>();
            body.add(new Point(100, 100)); // Add the snake's starting position
            direction = SnakeDirection.RIGHT;
            spawnFood();
        }

        public LinkedList<Point> getBody() {
            return body;
        }

        public Point getFood() {
            return food;
        }

        public void setDirection(Direction direction) {
            if (this.direction != direction.opposite()) {
                this.direction = direction;
            }
        }
        

        public void move() {
            // Move the snake by adding a new head position and removing the tail position
            Point head = new Point(body.getFirst());
            switch (direction) {
                case UP:
                    head.translate(0, -10);
                    break;
                case DOWN:
                    head.translate(0, 10);
                    break;
                case LEFT:
                    head.translate(-10, 0);
                    break;
                case RIGHT:
                    head.translate(10, 0);
                    break;
            }
            body.addFirst(head);
            if (head.equals(food)) {
                spawnFood();
            } else {
                body.removeLast();
            }
        }

        private void spawnFood() {
            int x = (int) (Math.random() * 30) * 10;
            int y = (int) (Math.random() * 30) * 10;
            food = new Point(x, y);
        
            // Respawn the food if it's inside the snake's body
            while (body.contains(food)) {
                x = (int) (Math.random() * 30) * 10;
                y = (int) (Math.random() * 30) * 10;
                food = new Point(x, y);
            }
        }
    }
}
