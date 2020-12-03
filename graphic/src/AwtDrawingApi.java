import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;


public class AwtDrawingApi extends Frame implements DrawingApi {
    private final List<Shape> elements;
    private final int w;
    private final int h;
    private final int nodeRadius;

    public AwtDrawingApi(int w, int h, int r) {
        this.w = w;
        this.h = h;
        this.nodeRadius = r;
        this.elements = new ArrayList<>();
    }

    @Override
    public long getDrawingAreaWidth() {
        return w;
    }

    @Override
    public long getDrawingAreaHeight() {
        return h;
    }

    @Override
    public void drawPoint(int x, int y) {
        elements.add(new Ellipse2D.Float(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius));
    }

    @Override
    public void drawEdge(int x1, int y1, int x2, int y2) {
        elements.add(new Line2D.Float(x1, y1, x2, y2));
    }

    public void showGraph() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        this.setTitle("AwtDrawingApi");
        this.setSize(w, h);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D) g;
        ga.setPaint(Color.blue);
        for (Shape shape : elements) {
            ga.draw(shape);
            ga.fill(shape);
        }
    }
}
