/*
Sharvil Phadke
Sep 30
P5
is working : yes

hw question:
it is possible to memoize a fractal? What are the limitations of making fractals?
As I add more depth to a factor, how does the runtime increase? (what is the
O notation of drawing most fractals)
 */

import gpdraw.*;
public class P5_Phadke_Sharvil_KochCurve
{
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 1000;
    public static final int[] turns = {60,-120,60};
    
    private SketchPad pad;
    public DrawingTool pen;
    
    public P5_Phadke_Sharvil_KochCurve() {
        this(new SketchPad(DEFAULT_WIDTH,DEFAULT_HEIGHT));
    }
    
    public P5_Phadke_Sharvil_KochCurve(SketchPad pad) {
        this.pad = pad;
        this.pen = new DrawingTool(this.pad);
        pen.turn(-90);
        pen.setWidth(1);
    }
    
    public void drawKochCurve(int level, double length) {
        if (level < 1) pen.move(length);
        else {
            drawKochCurve(level - 1, length/3);
            for (int turn : turns) {
                pen.turn(turn);
                drawKochCurve(level - 1, length/3);
            }
        }
    }
    public void drawKochCurve(int level, double length, double x, double y) {
        pen.up();
        pen.move(x,y);
        pen.setDirection(0);
        pen.down();
        if (level < 1) pen.move(length);
        else {
            drawKochCurve(level - 1, length/3);
            for (int turn : turns) {
                pen.turn(turn);
                drawKochCurve(level - 1, length/3);
            }
        }
    }
    
    public void drawKochSnowflake(int level, double length) {
        for (int i = 0; i < 6; i++) {
            drawKochCurve(level, length);
            pen.turn(-60);
        }
    }
    
    public void drawKochSnowflake(int level, double length, double x, double y) {
        pen.up();
        pen.move(x-length/2,y+length*Math.sqrt(3)/2);
        pen.setDirection(0);
        pen.down();
        for (int i = 0; i < 6; i++) {
            drawKochCurve(level, length);
            pen.turn(-60);
        }
    }
    
    public void drawInvertedKochSnowflake(int level, double length) {
        for (int i = 0; i < 6; i++) {
            drawKochCurve(level, length);
            pen.turn(-60);
        }
    }
    
    public void drawInvertedKochSnowflake(int level, double length, double x, double y) {
        pen.up();
        pen.move(x+length/2,y+length*Math.sqrt(3)/2);
        pen.setDirection(0);
        pen.down();
        for (int i = 0; i < 6; i++) {
            pen.turn(180);
            drawKochCurve(level, length);
            pen.turn(-180);
            pen.turn(60);
        }
    }
    
    
    public static void test(String[] args) {
        P5_Phadke_Sharvil_KochCurve kochCurve = new P5_Phadke_Sharvil_KochCurve();
        for (int w = 1000; w > 1; w /= 2) {
            kochCurve.drawKochSnowflake(4, w,0,0);
            kochCurve.drawInvertedKochSnowflake(4, w,0,0);
        }
        
        
        /*
        for (int i = 2; i < 5; i++) {
            kochCurve.drawKochSnowflake(i, i*25,0,0);
            kochCurve.drawInvertedKochSnowflake(i, i*50,0,0);
        }
        */
        
        /*
        P5_Phadke_Sharvil_KochCurve kochCurve = new P5_Phadke_Sharvil_KochCurve();
        kochCurve.pen.up();
        kochCurve.pen.move(-P5_Phadke_Sharvil_KochCurve.DEFAULT_WIDTH/2.0,-P5_Phadke_Sharvil_KochCurve.DEFAULT_HEIGHT/4.0);
        kochCurve.pen.setDirection(0);
        kochCurve.pen.down();
        kochCurve.drawKochCurve(3,DEFAULT_WIDTH);
        */
    }
}
