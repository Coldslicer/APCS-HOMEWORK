import gpdraw.*;

public class LFractal {

	// Attributes
	SketchPad pad;
	DrawingTool pen;

	// Constructor
	public LFractal() {

		pad = new SketchPad(500, 500, 0);
		pen = new DrawingTool(pad);

		// Back the pen up so the L is drawn in the middle of the screen
		pen.up();
		pen.setDirection(270);
		pen.backward(150);
 		pen.down();
	}

	public void drawLFractal(int level, double length) {

		// Base case:  Draw an L
		if (level == 0) {

			pen.forward(length);
			pen.turnLeft(90);
			pen.forward(length);
		}


		// Recursive case:  Draw an L at each midpoint
		// of the current L's segments
		else {

			// Move to first midpoint
			pen.forward(length / 2.0);
			pen.turnRight(90);

			// Save current drawing position
			double x = pen.getXPos();
			double y = pen.getYPos();
			double dir = pen.getDirection();

			// Recursively draw another L at the midpoint
			drawLFractal(level - 1, length / 2.0);

			// Restore drawing position
			pen.up();
			pen.move(x,y);
			pen.setDirection(dir);
			pen.down();

			// Move to second midpoint
			pen.turnLeft(90);
			pen.forward(length / 2.0);
			pen.turnLeft(90);
			pen.forward(length / 2.0);
			pen.turnRight(90);

			// Save current drawing position
			x = pen.getXPos();
			y = pen.getYPos();
			dir = pen.getDirection();

			// Recursively draw another L at the midpoint
			drawLFractal(level - 1, length / 2.0);

			// Restore drawing position
			pen.up();
			pen.move(x,y);
			pen.setDirection(dir);
			pen.down();
			
			// Finish end of the L
			pen.turnLeft(90);
			pen.forward(length / 2.0);
		}
	}

	public static void main(String[] args) {
		
		LFractal fractal = new LFractal();

		// Draw LFractal with given level and side length
		fractal.drawLFractal(4, 200);
	}	


}