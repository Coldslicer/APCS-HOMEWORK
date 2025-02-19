/**
 * tester for P5_Phadke_Sharvil_Math
 *
 * @author Sharvil Phadke
 */
public class P5_Phadke_Sharvil_MathDriver
{
    public static void main(String[] args) {
        System.out.printf("%.1f\u00B0F --> %.1f\u00B0C\n%.1f\u00B0F --> %.1f\u00B0C\n%.1f\u00B0C --> %.1f\u00B0F\n%.1f\u00B0C --> %.1f\u00B0F\n",212.0,P5_Phadke_Sharvil_Math.fToC(212),98.6,P5_Phadke_Sharvil_Math.fToC(98.6),37.0,P5_Phadke_Sharvil_Math.cToF(37),-15.0,P5_Phadke_Sharvil_Math.cToF(-15));

        System.out.printf("Volume of a sphere with radius %.1f is %f\nVolume of a sphere with radius %.1f is %f\n",1.0,P5_Phadke_Sharvil_Math.volume(1.0),6.8,P5_Phadke_Sharvil_Math.volume(6.8));
        
        System.out.printf("A right triangle with sides %d and %d has hypotenuse %.1f\nA right triangle with sides %.1f and %.2f has hypotenuse %f\n",3,4,P5_Phadke_Sharvil_Math.hypotenuse(3,4),2.5,9.25,P5_Phadke_Sharvil_Math.hypotenuse(2.5,9.25));
        
        System.out.printf("%.2fg of \"Fe\" contains %e atoms\n%.2fg of \"Au\" contains %e atoms\n%.1fg of \"Ne\" contains %e atoms\n",0.75,P5_Phadke_Sharvil_Math.gramsToAtoms(55.845,0.75),5.24,P5_Phadke_Sharvil_Math.gramsToAtoms(196.97,5.24),2.0,P5_Phadke_Sharvil_Math.gramsToAtoms(20.18,2.0));
        
        for (int i = 0; i < 6; i++) {
            System.out.printf("A random number between %d and %d is:  %d\n",7,9,P5_Phadke_Sharvil_Math.randomBetween(7,9));
        }
        /*
        A triangle with vertices:
            java.awt.Point[x=1,y=2]
            java.awt.Point[x=3,y=4]
            java.awt.Point[x=5,y=1]
        has perimeter 10.557084025827841*/
        java.awt.Point[] points = {
            new java.awt.Point(1,2),
            new java.awt.Point(3,4),
            new java.awt.Point(5,1)
        };
        System.out.printf("A triangle with vertices:\n\t%s\n\t%s\n\t%s\nhas perimeter %f\n",points[0].toString(),points[1].toString(),points[2].toString(),P5_Phadke_Sharvil_Math.perimeter(points));
        
            
    }
    private P5_Phadke_Sharvil_MathDriver() throws InstantiationException {throw new InstantiationException();}
}
