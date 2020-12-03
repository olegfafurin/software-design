public interface DrawingApi {
    
    long getDrawingAreaWidth();

    long getDrawingAreaHeight();

    void drawPoint(int x, int y);

    void drawEdge(int x1, int y1, int x2, int y2);

    void showGraph();

}