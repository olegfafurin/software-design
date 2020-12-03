import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FxDrawingApi extends Application implements DrawingApi {

    private final int w = 800;
    private final int h = 600;
    private final int nodeRadius = 4;
    static List<Node> nodes = new ArrayList<>();
    static List<Pair<Node, Node>> edges = new ArrayList<>();

    static class Node {
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;
    }

    public FxDrawingApi() {

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("FxDrawingApi");
        Group root = new Group();
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        for (Node node : nodes) {
            gc.fillOval(node.x - nodeRadius, node.y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        }
        for (Pair<Node, Node> e : edges) {
            gc.strokeLine(e.getKey().x, e.getKey().y, e.getValue().x, e.getValue().y);
        }
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
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
        nodes.add(new Node(x, y));
    }

    @Override
    public void drawEdge(int x1, int y1, int x2, int y2) {
        edges.add(new Pair<>(new Node(x1, y1), new Node(x2, y2)));
    }

    @Override
    public void showGraph() {
        launch();
    }
}
