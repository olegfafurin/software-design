import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AdjMatrixGraph extends Graph {
    private final List<List<Boolean>> conn;

    public AdjMatrixGraph(int n, List<List<Boolean>> f, DrawingApi drawingApi) {
        super(n, drawingApi);
        conn = f;
    }

    @Override
    List<Pair<Integer, Integer>> getEdges() {
        ArrayList<Pair<Integer, Integer>> e = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < i; j++) {
                if (conn.get(i).get(j)) {
                    e.add(new Pair<>(i, j));
                }
            }
        }
        return e;
    }
}
