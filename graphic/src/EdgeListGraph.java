import javafx.util.Pair;

import java.util.List;

public class EdgeListGraph extends Graph {
    private final List<Pair<Integer, Integer>> edges;

    public EdgeListGraph(int n, List<Pair<Integer, Integer>> edges, DrawingApi drawingApi) {
        super(n, drawingApi);
        this.edges = edges;
    }

    @Override
    List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

}
