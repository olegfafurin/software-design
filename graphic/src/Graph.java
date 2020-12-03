import javafx.util.Pair;

import java.util.List;

import static java.lang.Math.*;

public abstract class Graph {

    protected int vertexCount;
    protected DrawingApi drawingApi;

    abstract List<Pair<Integer, Integer>> getEdges();

    public Graph(int n, DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
        this.vertexCount = n;
    }

    public void drawGraph() {
        long width = drawingApi.getDrawingAreaWidth();
        long height = drawingApi.getDrawingAreaHeight();
        long centerX = width / 2;
        long centerY = height / 2;
        long radius = min(centerX, centerY) * 3 / 4;
        double phi = 2 * Math.PI / vertexCount;
        for (int i = 0; i < vertexCount; i++)
            drawingApi.drawPoint((int) (centerX + radius * cos(i * phi)), (int) (centerY + radius * sin(i * phi)));
        for (Pair<Integer, Integer> e : getEdges()) {
            drawingApi.drawEdge(
                    (int) (centerX + radius * cos(e.getKey() * phi)),
                    (int) (centerY + radius * sin(e.getKey() * phi)),
                    (int) (centerX + radius * cos(e.getValue() * phi)),
                    (int) (centerY + radius * sin(e.getValue() * phi)));
        }
        drawingApi.showGraph();
    }


}