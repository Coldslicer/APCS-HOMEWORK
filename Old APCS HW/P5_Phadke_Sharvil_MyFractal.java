import gpdraw.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

/**
This class draws any L System fractal using a string representing an L System.<br>
<br>
It is suggested to use the draw() function that takes no parameters, and give all information in the format string <br>
the format string may be passed in to the constructor as any number of component strings, which are concatenated together <br>
The format string should start with a section that resembles a python dict <br>
In this section, specify information that is independent of the current level <br>
If the program throws errors if it fails to tokenize. Make sure there are valid key:value pairings<br>
Tokens are trimmed before being stored, so you may have a space after the colon<br>
addition tokens will be recognized and used, but won't do anything unless the code is modified<br>

Supported tokens:
<pre>
    level (int): the starting level of the fractal. Anything below 1 is interpreted as 1
    length (double): the default length of a single segment
    width (double): the default width of a single segment
    turn (double): the default degree measure of a single turm
    x (double): the x location to start the fractal from
    y (double): the y location to start the fractal from
    direction (double): the pen direction to start the fractal from
    length scales by level (boolean): option to automatically calculate scale values based of of level, should not be used with scale
    width scales by level (boolean): option to scale width based on level

    Example: {leve1: 5, length: 10, x: 200, direction: 180};
    
</pre>
After the {} section, the program interprets each character (with the exeption of parentheses) as a command <br>
This is where the L System is defined <br>
Supported commands in the string denotation of the L System:<br>
<pre>
    (...): runs a command with parameters. Works the same as java, except that they are not required
    D: draws the fractal at the current pen location at the next lower level. supports a "length" parameter
    +: turns the pen counter-clockwise by a given amount. supports a "turn" parameter
    -: turns the pen clockwise by a given amount supports a "turn" parameter
    F: moves the pen forwards and draws a line. supports a "length" parameter
    f: moves the pen forwards without drawing a line. supports a "length" parameter
    h: set the heading of the pen to a specific direction
    [: stores the current position and rotation of the pen in the stack
    ]: retrieves and deletes the most recent saved data in the stack
    

<\pre>
If no command arguments are needed, parentheses are optional <br>
Command arguments are seperated using commas and trimmed <br>
Any unrecoginzed commands or command arguments will have no effect <br>
'[' and ']' are intended to save your position if you choose to make an offshoot in your fractal <br>
Nested stack commands ('[' and ']') do work and may be need for complex fractals <br>
the format string may be passed in as any number of component strings, which are concatenated together <br>

@author Sharvil Phadke
 */
public class P5_Phadke_Sharvil_MyFractal
{
    protected static Map<Character,Command> commands;
    static {
        commands = new HashMap();
        commands.put('F',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    double length;
                    if (params.get("args") != null) {
                        length = Double.parseDouble(((List<String>)(params.get("args"))).get(0));
                    }
                    else length = Double.parseDouble(params.getOrDefault("length","0").toString());
                    double scale = Double.parseDouble(params.getOrDefault("scale","1").toString());
                    double width = Double.parseDouble(params.getOrDefault("width","1").toString());
                    int level = Integer.parseInt(params.getOrDefault("level","1").toString());
                    int maxLevel = Integer.parseInt(params.getOrDefault("max level","1").toString());
                    if (Boolean.parseBoolean(params.getOrDefault("width scales by level","false").toString())) width *= (level+1.0)/(maxLevel+1);
                    if (Boolean.parseBoolean(params.getOrDefault("length scales by level","false").toString())) length *= (level+1.0)/(maxLevel+1);
                    self.pen.setWidth((int)(width > 1 ? width : 1));
                    self.pen.move(length*scale);
                }
            }
        );
        commands.put('D',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    int level = Integer.parseInt(params.getOrDefault("level","0").toString());
                    double turn = Double.parseDouble(params.getOrDefault("turn","0").toString());
                    double scale = Double.parseDouble(params.getOrDefault("scale","1").toString());
                    if (level > 1) {
                        Map reference = params;
                        params = new HashMap();
                        params.putAll(reference);
                        params.put("level",level-1);
                        params.put("first",false);
                        params.put("scale", scale*scale);
                        self.draw(params);
                    }
                    else commands.get('F').run(params);
                }
            }
        );
        commands.put('f',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    double length;
                    if (params.get("args") != null) {
                        length = Double.parseDouble(((List<String>)(params.get("args"))).get(0));
                    }
                    else length = Double.parseDouble(params.getOrDefault("length","0").toString());
                    boolean isDown = self.pen.isDown();
                    self.pen.up();
                    self.pen.move(length);
                    if (isDown) self.pen.down();
                }
            }
        );
        commands.put('[',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    self.stash.add(self.pen.getDirection(),self.pen.getYPos(),self.pen.getXPos());
                }
            }
        );
        commands.put(']',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    boolean isDown = self.pen.isDown();
                    self.pen.up();
                    self.pen.move(self.stash.retrieve(),self.stash.retrieve());
                    self.pen.setDirection(self.stash.retrieve());
                    if (isDown) self.pen.down();
                }
            }
        );
        commands.put('-',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    double turn;
                    if (params.get("args") != null) {
                        turn = Double.parseDouble(((List<String>)(params.get("args"))).get(0));
                    }
                    else turn = Double.parseDouble(params.getOrDefault("turn","0").toString());
                    self.pen.turnRight(turn);
                }
            }
        );
        commands.put('+',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    double turn;
                    if (params.get("args") != null) {
                        turn = Double.parseDouble(((List<String>)(params.get("args"))).get(0));
                    }
                    else turn = Double.parseDouble(params.getOrDefault("turn","0").toString());
                    self.pen.turnLeft(turn);
                }
            }
        );
        
        
        
        commands.put('h',
            new Command() {
                @Override
                public void run(Map<String,Object> params) {
                    System.out.println("why is this running");
                    P5_Phadke_Sharvil_MyFractal self = (P5_Phadke_Sharvil_MyFractal)(params.get("self"));
                    double heading;
                    if (params.get("args") != null) {
                        heading = Double.parseDouble(((List<String>)(params.get("args"))).get(0));
                    }
                    else heading = Double.parseDouble(params.getOrDefault("heading","0").toString());
                    self.pen.setDirection(heading);
                }
            }
        );
        commands.put(null,
            new Command() {
                @Override
                public void run(Map<String,Object> args) {}
            }
        );
    }
    
    private DrawingTool pen;
    
    private Stash<Double> stash = new Stash();
    
    private Command[] sequence;
    private List<String>[] commandArgs;
    private Map<String,String> sequenceArgs;
    private Random r;
    
    public P5_Phadke_Sharvil_MyFractal(SketchPad pad,String... rule) {
        pen = new DrawingTool(pad);
        r = new Random();
        pen.setDirection(0);
        if (rule.length > 0) compile(rule);
    }
    
    public P5_Phadke_Sharvil_MyFractal(DrawingTool pen,String... rule) {
        this.pen = pen;
        r = new Random();
        if (rule.length > 0) compile(rule);
    }
    
    public void draw(Map params) {
        if (params == null) params = new HashMap<String,Object>();
        else {
            Map map = params;
            params = new HashMap();
            params.putAll(map);
        }
        if ((Boolean)(params.getOrDefault("first",true))) {
            if (sequenceArgs != null) {
                pen.up();
                pen.move(Double.parseDouble(sequenceArgs.getOrDefault("x","0")),Double.parseDouble(sequenceArgs.getOrDefault("y","0")));
                pen.setDirection(Double.parseDouble(sequenceArgs.getOrDefault("direction","0")));
                pen.down();
                params.putAll(sequenceArgs);
            }
            params.put("max level",params.get("level"));
        }
        params.put("self",this);
        for (int i = 0; i < sequence.length; i++) {
            params.put("args",commandArgs[i]);
            Command c = sequence[i];
            if (c != null) c.run(params);
        }
    }

    public P5_Phadke_Sharvil_MyFractal draw() {
        draw(null);
        return this;
    }
    
    public P5_Phadke_Sharvil_MyFractal compile(String... strings) {
        StringBuilder b = new StringBuilder();
        for (String s : strings) {
            b.append(s);
        }
        String s = b.toString();
        sequence = new Command[s.length()];
        commandArgs = new List[s.length()];
        if (s.charAt(0) == '{') {
            String sequenceInput = s.substring(1,s.indexOf('}'));
            sequenceArgs = new HashMap<String,String>();
            int index = 0;
            while (true) {
                int colonIndex = s.indexOf(':',index+1);
                int commaIndex = s.indexOf(',',index+1);
                if (commaIndex == -1) {
                    sequenceArgs.put(s.substring(index+1,colonIndex).trim(),s.substring(colonIndex+1,sequenceInput.length()+1).trim());
                    break;
                } else {
                    sequenceArgs.put(s.substring(index+1,colonIndex).trim(),s.substring(colonIndex+1,commaIndex).trim());
                    index = commaIndex;
                }
            }
        }
        int start = s.indexOf("}");
        if (start == -1) start = 0;
        for (int i = start; i < s.length(); i++) {
            if (commands.keySet().contains(s.charAt(i))) {
                if (i+1 < s.length() && s.charAt(i+1) == '(') {
                    int closeIndex = s.indexOf(')',i+2);
                    String commandInput = s.substring(i+2,closeIndex);
                    List<String> commandArgs = new ArrayList();
                    int index = i+1;
                    while (true) {
                        int commaIndex = s.indexOf(',',index+1,closeIndex);
                        if (commaIndex == -1) {
                            commandArgs.add(s.substring(index+1,closeIndex).trim());
                            break;
                        } else {
                            commandArgs.add(s.substring(index+1,commaIndex).trim());
                            index = commaIndex;
                        }
                    }
                    this.commandArgs[i] = commandArgs;
                }
                sequence[i] = commands.get(s.charAt(i));
            }
            else sequence[i] = commands.get(null);
        }
        return this;
    }
    
    public static abstract class Command {
        public abstract void run(Map<String,Object> params);
        private Map data = new HashMap();
    }
    
    public static class Stash<E> {
        private LinkedList<E> data;
        
        public Stash() {
            data = new LinkedList();
        }
        
        public void add(E... input) {
            for (E obj : input) {
                data.addFirst(obj);
            }
        }
        
        // FIRST-IN FIRST-OUT STRUCTURE
        public E retrieve() {
            return data.removeFirst();
        }
    }
}
