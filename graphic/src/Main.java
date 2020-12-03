import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        assert args.length > 3;

        String filename = args[0];
        String inputType = args[1];
        String apiType = args[2];

        DrawingApi drawApi = switch (apiType) {
            case "fx" -> new FxDrawingApi();
            case "awt" -> {
                assert args.length >= 6;
                yield new AwtDrawingApi(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            }
            default -> throw new IllegalArgumentException("Unknown api");
        };

        Graph g = switch (inputType) {
            case "matrix" -> readFileMatrix(filename, drawApi);
            case "edges" -> readFileEdges(filename, drawApi);
            default -> throw new IllegalArgumentException("Unknown input type");
        };

        g.drawGraph();
    }


    static AdjMatrixGraph readFileMatrix(String filename, DrawingApi api) throws IOException {
        List<List<Boolean>> m = new ArrayList<>();
        for (String s : Files.readAllLines(Paths.get(filename))) {
            if (s.isEmpty()) break;
            m.add(Arrays.stream(s.split("\\s")).map(Integer::parseInt).map(Main::toBool).collect(Collectors.toList()));
        }
        return new AdjMatrixGraph(m.size(), m, api);
    }

    static EdgeListGraph readFileEdges(String filename, DrawingApi api) throws IOException {
        Scanner s = new Scanner(new File(filename));
        int n = s.nextInt();
        List<Pair<Integer, Integer>> e = new ArrayList<>();
        while (s.hasNext()) {
            e.add(new Pair<>(s.nextInt(), s.nextInt()));
        }
        return new EdgeListGraph(n, e, api);
    }

    static Pair<Integer, Integer> toEdge(String s) {
        Scanner scanner = new Scanner(s);
        return new Pair<>(scanner.nextInt(), scanner.nextInt());
    }

    static Boolean toBool(Integer val) throws IllegalArgumentException {
        return switch (val) {
            case 0 -> Boolean.FALSE;
            case 1 -> Boolean.TRUE;
            default -> throw new IllegalArgumentException("Connection matrix should only contain ones and zeroes");
        };
    }

}
