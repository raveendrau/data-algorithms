import java.awt.*;
import java.util.HashMap;

import javax.swing.JFrame;


public class DrawCircles extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Circle[] buildCircles(int numCircles){
		Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.CYAN};
		
		Circle c1 = new Circle(20, 20, colors[0], 30);
		
		Circle c2 = new Circle(50, 50, colors[3], 30);
		
		Circle c3 = new Circle(80, 80, colors[4], 30);
	
		Circle[] c = {c1, c2, c3};
		
		return c;	
	}
	
	@SuppressWarnings("unused")
	public void countColors(Circle[] circles){
		HashMap<Color, Integer> colorMap = new HashMap<Color, Integer>();
		
	}
	
	public static void main(String[] args){		
		DrawCircles drawCircles = new DrawCircles();
		//the number of circles you are drawing
		//you can change this number if you want to draw more
		int numCircles = 100;
		
		// Set up the display
		DrawHelper myDisplay = new DrawHelper();
		myDisplay.setTitle("Learning Objects and Classes");
		myDisplay.setSize(500,500);
		myDisplay.setBackground(Color.BLACK);
		myDisplay.setVisible(true);
		
		//this is your method call
		Circle[] c = drawCircles.buildCircles(numCircles);
		drawCircles.countColors(c);
		
		myDisplay.drawCircle(c);
		myDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

