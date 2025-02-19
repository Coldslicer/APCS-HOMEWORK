import gpdraw.*;
import java.awt.Color;
public class CheckerBoard {
    public static final double DEFAULT_SIZE = 200;
    public static void drawCheckerBoardHardcoded() {
        double w = DEFAULT_SIZE / 16;
        SketchPad pad = new SketchPad(300,300);
        DrawingTool pen = new DrawingTool(pad);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                pen.up();
                pen.setColor(((x+y)%2==0) ? Color.RED : Color.BLACK);
                pen.move((-DEFAULT_SIZE-w)/4 + x*w,(DEFAULT_SIZE)/4 - y*w);
                pen.down();
                pen.fillRect(w,w);
            }
        }
    }
    public static void drawCheckerBoard(int totalHeight,int totalWidth, int n, int m) {
        double w = (double)totalWidth/n;
        double h = (double)totalHeight/m;
        SketchPad pad = new SketchPad(300,300);
        DrawingTool pen = new DrawingTool(pad);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                pen.up();
                pen.setColor(((x+y)%2==0) ? Color.RED : Color.BLACK);
                pen.move((-DEFAULT_SIZE-w)/4 + x*w,(DEFAULT_SIZE)/4 - y*h);
                pen.down();
                pen.fillRect(w,h);
            }
        }
    }
    public static void drawCheckerBoardRecursive() {

    }
}
