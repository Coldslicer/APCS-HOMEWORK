import java.awt.Point;

/**Math helper functions library
@author Sharvil Phadke
 */
public class P5_Phadke_Sharvil_Math
{
    /**Avagadro's constant*/
    public static final double  AVAGADRO_CONSTANT = 6.022140857E23;
    /**converts Farenheit to Celsius
    @param f the temprature in Farenheit
    @return the temprature in Celsius
     */
    public static double fToC(double f) {
        return 5*(f-32)/9;
    }
    /**converts Celcius to Farenheit
    @param c the temprature in Celsius
    @return the temprature in Farenheit
     */
    public static double cToF(double c) {
        return 9*c/5 + 32;
    }
    /**finds the volume of a sphere with given radius
    @param r the radius of the sphere
    @return the volume
     */
    public static double volume(double r) {
        return 4.0*Math.PI*Math.pow(r,3)/3;
    }
    /**finds the hypotenuse of a right triangle with given base and height
    @param b the length of the base
    @param h the length of the height
    @return the length of the hypotenuse
     */
    public static double hypotenuse(double b, double h) {
        return Math.sqrt(Math.pow(b,2)+Math.pow(h,2));
    }
    /**finds the number of atoms in a given mass of a given element, specified by atomic mass
    @param atomicMass the atomic mass used the identify the element
    @param grams the amount of the element you have
    @return the number of atoms
     */
    public static double gramsToAtoms(double atomicMass, double grams) {
        return AVAGADRO_CONSTANT*grams/atomicMass;
    }
    /**returns a random integer in an endpoint inclusive range
    @param e1 endpoint 1
    @param e2 endpoint 2
    @return a random number in the range
     */
    public static int randomBetween(int e1, int e2) {
        return (int)(Math.random()*(Math.abs(e2-e1)+1))+e1;
    }
    /**returns the perimeter of a struture vertices
    @param points the points in the structure
    @return the perimeter of the structure
     */
    public static double perimeter(Point ...points) {
        double output = 0;
        for(int i = 0; i < points.length; i++) output += points[i].distance(points[(i+1)%points.length]);
        return output;
    }
    /**This class should not be instantiated*/
    private P5_Phadke_Sharvil_Math() throws InstantiationException {throw new InstantiationException();}
}
