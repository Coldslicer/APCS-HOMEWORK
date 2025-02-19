import gpdraw.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;

public class P5_Phadke_Sharvil_MyDrawingTool extends DrawingTool implements MouseListener, KeyListener  {
    
    private enum DrawMode {
        DEFAULT, CIRCLE, RECT
    }
    
    enum NamedColor {
        BLACK("BLACK",Color.BLACK),
        BLUE("BLUE",Color.BLUE),
        CYAN("CYAN",Color.CYAN),
        DARK_GRAY("DARK_GRAY",Color.DARK_GRAY),
        GRAY("GRAY",Color.GRAY),
        GREEN("GREEN",Color.GREEN),
        LIGHT_GRAY("LIGHT_GRAY",Color.LIGHT_GRAY),
        MAGENTA("MAGENTA",Color.MAGENTA),
        ORANGE("ORANGE",Color.ORANGE),
        PINK("PINK",Color.PINK),
        RED("RED",Color.RED),
        WHITE("WHITE",Color.WHITE),
        YELLOW("YELLOW",Color.YELLOW);
        
        public Color color;
        public String name;
        private NamedColor(String name,Color color) {
            this.color = color;
            this.name = name;
        }
    }
    
    // Additional attributes
    
    private DrawMode mode = DrawMode.DEFAULT;
    private static final Map<Character,DrawMode> keyToModeMappings = Map.of(
        'c',DrawMode.CIRCLE,
        'r',DrawMode.RECT,
        'n',DrawMode.DEFAULT
        //anything unregistered for default
    );
    
    private boolean isFillMode = false;
    
    private StringBuilder curShiftString = new StringBuilder();
 
    // Constructors
    
    public P5_Phadke_Sharvil_MyDrawingTool(SketchPad pad) {
        super(pad);
        setColor(Color.BLACK);
        getPadPanel().addMouseListener(this);
        getPadPanel().addKeyListener(this);
        getPadPanel().requestFocusInWindow();
    }
    
    public P5_Phadke_Sharvil_MyDrawingTool() {
        this(new SketchPad(250,250));
    }
    
    // Overridden methods
    
    @Override
    public void move(double x, double y) {
        boolean down = isDown();
        up();
        super.move(x,y);
        if (down) down();
    }
    
    @Override
    public void home() {
        move(0,0);
        setDirection(0);
        // this works the same as home, except it doesn't lift the pen up
    }
    
    // Overloaded methods
    
    public void move(Point.Double p) {
        move(p.getX(),p.getY());
    }
    
    public void move(Point p) {
        move(p.getX(),p.getY());
    }
    
    public void drawLine(Point.Double p) {
        drawLine(p.getX(),p.getY());
    }
    
    public void drawLine(Point p) {
        drawLine(p.getX(),p.getY());
    }
    
    public void drawLine(double toX, double toY, int deltaWidth,boolean growing) {
        double dir = getDirection();
        int originalWidth = getWidth();
        int curWidth = originalWidth;
        while (Math.abs(originalWidth - curWidth) < deltaWidth) {
            turn(toX,toY);
            setWidth(curWidth);
            move(Point.distance(xPos, yPos, toX, toY)/deltaWidth);
            if (growing) curWidth++;
            else curWidth--;
        }
        setDirection(dir);
    }

    
    // new methods

    
    public Point convertMouseCoordsToDrawCoords(Point p) {
        return new Point((int)(p.getX()-getPadPanel().getWidth()/2),(int)(getPadPanel().getHeight()/2-p.getY()));
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {
        move(convertMouseCoordsToDrawCoords(new Point(e.getX(),e.getY())));
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Point mouseCords = convertMouseCoordsToDrawCoords(new Point(e.getX(),e.getY()));
        switch (mode) {
            case DrawMode.DEFAULT:
                drawLine(convertMouseCoordsToDrawCoords(new Point(e.getX(),e.getY())));
                fillCircle(getWidth()/2);
                break;
            case DrawMode.CIRCLE:
                if (isFillMode) fillCircle(Point.distance(getXPos(),getYPos(),mouseCords.getX(),mouseCords.getY()));
                else drawCircle(Point.distance(getXPos(),getYPos(),mouseCords.getX(),mouseCords.getY()));
                break;
            case DrawMode.RECT:
                if (isFillMode) fillRect(2*Math.abs(mouseCords.getX()-getXPos()),2*Math.abs(mouseCords.getY()-getYPos()));
                else drawRect(2*Math.abs(mouseCords.getX()-getXPos()),2*Math.abs(mouseCords.getY()-getYPos()));
                break;
            default:
                System.err.println("Unsupported draw mode: "+mode);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char chr = e.getKeyChar();
        if (e.isShiftDown() && (chr == '_' || Character.isLetter(chr))) {
        curShiftString.append(chr);
        if (!curShiftString.isEmpty()) {
            Color next = null;
            String s = curShiftString.toString();
            for (NamedColor c : NamedColor.values()) if (s.endsWith(c.name)) next = c.color;
            if (next != null) setColor(next);
        }
        } else {
            switch (chr) {
                case 'd':
                    Point.Double pos = getPosition();
                    double rot = getDirection();
                    home();
                    Color c = getColor();
                    setColor(Color.WHITE);
                    fillRect(getPadPanel().getWidth(),getPadPanel().getHeight());
                    move(pos);
                    setDirection(rot);
                    setColor(c);
                    break;
                case 'e':
                    System.exit(0);
                    break;
                case '+':
                    setWidth(getWidth()+1);
                    break;
                case '-':
                    if (getWidth() > 1) setWidth(getWidth()-1);
                    break;
                case 'f':
                    isFillMode = !isFillMode;
                    break;
                default: 
                    if (keyToModeMappings.keySet().contains(chr)) mode = keyToModeMappings.get(chr);
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
