import gpdraw.*;

public class CopyOfLFractal {

    // Attributes
    SketchPad pad;
    DrawingTool pen;

    // Constructor
    public CopyOfLFractal() {

        pad = new SketchPad(500, 500, 0);
        pen = new DrawingTool(pad);

        // Back the pen up so the L is drawn in the middle of the screen
        //pen.up();
        //pen.setDirection(270);
        //pen.backward(150);
         //pen.down();
    }

    public void drawLFractal(int level, double length) {

        // Base case:  Draw an L
        if (level == 0) {
            pen.turn(180);
                        for (int i = 0; i < 3; i++) {
                            pen.turnLeft(120);
                            pen.down();
                            pen.forward(length);
                            pen.up();
                            pen.backward(length);
                            pen.down();
                        }
        }


        // Recursive case:  Draw an L at each midpoint
        // of the current L's segments
        else {
            drawLFractal(0, length);
            
                // Save current drawing position
            double x = pen.getXPos();
            double y = pen.getYPos();
            double dir = pen.getDirection();
            
                        for (int i = 1; i <= 3; i++) {
                        pen.up();
                        pen.forward(length/2.0);
                        pen.turnLeft(90);
                        pen.forward(length/2.0);
                    
                        // Recursively draw another L at the midpoint
                        drawLFractal(level - 1, length / 2.0);
                
                        // Restore drawing position
                        pen.up();
                        pen.move(x,y);
                        pen.setDirection(dir);
                        pen.turnLeft(120*i);
                        pen.down();
                        }
        }
    }

    public static void main(String[] args) {
        
        CopyOfLFractal fractal = new CopyOfLFractal();

        // Draw CopyOfLFractal with given level and side length
        fractal.drawLFractal(5, 200);
    }    


}
