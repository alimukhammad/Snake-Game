
import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;

    public Snake() {
        body = new LinkedList<Point>();
        body.add(new Point(0, 0)); // Add the snake's starting position
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void move(int dx, int dy) {
        // Move the snake by adding a new head position and removing the tail position
        Point head = new Point(body.getFirst());
        head.translate(dx, dy);
        body.addFirst(head);
        body.removeLast();
    }
}
