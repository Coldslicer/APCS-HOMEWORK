/*
 * Sharvil Phadke P5
 * Sun Dec 1 2024
 * Working: yes
 * HW question: Why can I cast my custom drawing tool class to DrawingTool,
 * but not vice versa? Why was Java probably coded this way? 
 * Hint: think about what an object actually is in terms of memory allocation
 */

import gpdraw.*;
public class P5_Phadke_Sharvil_Driver
{
    public static void main(String[] args) {
        SketchPad pad = new SketchPad(1000,1000);
        P5_Phadke_Sharvil_MyDrawingTool pen = new P5_Phadke_Sharvil_MyDrawingTool(pad);
        pen.drawLine(100, 100, 3,true);
        System.out.println(
        "To enter a keyboard command, switch over to the sketch pad panel "+
        "and enter one of the following commands:"+
        "\n\tf: toggles whether shapes drawn with the mouse are filled"+
        "\n\td: clears the drawing pad"+
        "\n\te: exits the program"+
        "\n\tc: switches to the circle draw mode"+
        "\n\tr: switches to the rectangle draw mode"+
        "\n\t+: increments the size of the pen"+
        "\n\t-: decrements the size of the pen (if possible)"+
        "\n\tn: switches to the default (line) draw mode\n"+
        "To change the draw color, simply type the name of the color while on the sketch pad panel\n"+
        "Capital letters don't trigger commands, so typing out color will not affect run any commands"+
        "List of all supported colors:");
        for (P5_Phadke_Sharvil_MyDrawingTool.NamedColor c : P5_Phadke_Sharvil_MyDrawingTool.NamedColor.values()) {
            System.out.println('\t'+c.name);
        }
    }
}
