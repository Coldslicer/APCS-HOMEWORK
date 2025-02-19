
/**
 * Rectangle Driver
 *
 * @author Sharvil Phadke
 */
import gpdraw.*;
public class P5_Phadke_Sharvil_RectangleDriver
{
    public static void main(String[] args) {
        int w = 1000, h = 1000;
        SketchPad pad = new SketchPad(w,h);
        Rectangle[] rects = {drawRect(pad,0,0,w/2,h/2),drawRect(pad,0,0,w/6,h/2),drawRect(pad,0,0,w/2,h/6)};
        int i = 1;
        for (Rectangle r : rects) {
            System.out.printf("rectangle %d perimeter: %.2f\n",i,r.calcPerimeter());
            System.out.printf("rectangle %d perimeter: %.2f\n",i,r.calcArea());
            i++;
        }
        
    }
    private static Rectangle drawRect(SketchPad pad, double cx, double cy, double w, double h) {
        Rectangle rect = new Rectangle(cx-w/2,cy-h/2,w,h,pad);
        rect.draw();
        return rect;
    }
    private P5_Phadke_Sharvil_RectangleDriver() {throw new InstantiationError();}
}
