/*
 * Sharvil Phadke P5
 * Nov 13 2024
 * Working
 * HW Question: How do you specify what type a List (or any class that uses a generic type) holds?
 * What is the default type for Lists? If you have different types you want to put in the same
 * List, what type should you specify for the generic type?
 */

import  java.awt.geom.*; // for Point2D.Double  
import  java.util.ArrayList; // for ArrayList  
import  gpdraw.*; // for DrawingTool

public class  P5_Phadke_Sharvil_IrregularPolygon extends ArrayList<Point2D.Double> {  
    public static void main(String[] args) {
        System.out.println("testing for: (20, 10), (70, 20), (50, 50), (0, 40)");
        P5_Phadke_Sharvil_IrregularPolygon i = new P5_Phadke_Sharvil_IrregularPolygon();
        i.add(new Point2D.Double(20,10));
        i.add(new Point2D.Double(70,20));
        i.add(new Point2D.Double(50,50));
        i.add(new Point2D.Double(0,40));
        System.out.printf("perimeter: %f\n",i.perimeter());
        System.out.printf("area: %f\n",i.area());
        i.draw();
        //(20, 10), (70, 20), (50, 50), (0, 40)
        
        System.out.println("testing for: (0, 6), (4, 0), (4, −5), (−4, −5), (−4, 0)");
        P5_Phadke_Sharvil_IrregularPolygon i2 = new P5_Phadke_Sharvil_IrregularPolygon();
        i2.add(new Point2D.Double(0,6));
        i2.add(new Point2D.Double(4,0));
        i2.add(new Point2D.Double(4,-5));
        i2.add(new Point2D.Double(-4,-5));
        i2.add(new Point2D.Double(-4,0));
        System.out.printf("perimeter: %f\n",i2.perimeter());
        System.out.printf("area: %f\n",i2.area());
        i2.draw();
        //(0, 6), (4, 0), (4, −5), (−4, −5), (−4, 0)
    }
    
    public void draw() {
        DrawingTool pen = new DrawingTool(new SketchPad(1000,1000));
        pen.up();
        for (Point2D.Double p : this) {
            pen.move(p.getX(),p.getY());
            if (!pen.isDown()) pen.down();
        }
        pen.move(get(0).getX(),get(0).getY());
    }

    public double perimeter() {
        Point2D.Double previous = get(size()-1);
        double output = 0;
        for (Point2D.Double p : this) {
            output += p.distance(previous);
            previous = p;
        }
        return output;
    }

    public double  area() {
        Point2D.Double previous = get(size()-1);
        double output = 0;
        for (Point2D.Double p : this) {
            output += previous.getY()*p.getX();
            output -= p.getY()*previous.getX();
            previous = p;
        }
        return Math.abs(output)/2;
    }  
}
