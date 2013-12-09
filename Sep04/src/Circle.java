import java.awt.Color;

public class Circle {
	
	private int myXLocation;
	private int myYLocation;
	private Color myColor;
	private int myDiameter;
	
	public Circle(int x, int y, Color c, int d)
	{
		myXLocation = x;
		myYLocation = y;
		myColor = c;
		myDiameter = d;
	}
	
	public Color getColor()
	{
		return myColor;
	}
	
	public int getXLocation()
	{
		return myXLocation;
	}
	
	public int getYLocation()
	{
		return myYLocation;
	}
	
	public int getDiameter()
	{
		return myDiameter;
	}
	
	
}
