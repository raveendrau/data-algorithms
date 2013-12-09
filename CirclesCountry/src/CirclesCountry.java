/* Keng Low @kl78 */
public class CirclesCountry {	
	/*
	so, we have a list of the x,y coordinates of city centers 
	along with the locations of the "from" and "to"
	you want to check if the the "start" and "end" 
	points are in the same circle.
	
	To learn if they are in the same circle, we get the distance, 
	from the center of that circle to the point and compare with r, 
	the radius of that circle. If distance is < r, we know that 
	it is inside that circle.
	
	If each country is on its own, that is, they are not enclosed
	within another country, then the borders to cross depend solely on
	whether the initial and final points are located in.
	
	So, we compare the two points with respect to each country center,
	and see if each point is inside that country. We keep iterate through 
	all the city centers until both cities are in the same circle, 
	and that is when we know we have reached the destination.  
	*/
	public boolean samePlace(int x, int y, int r, int i, int j) {
		double d = new Double(0d);
		int dx = x - i;
		int dy = y - j;	
		d = Math.sqrt(dx*dx + dy*dy);
		if (d < r) {
			return true;
		}	
		return false;
	}
	
	public int leastBorders(int[] x, int[] y, int[] r, 
			int x1, int y1, int x2, int y2) {
		int checkPt = 0;
		int cityNo = x.length;
		for (int i = 0; i < cityNo; i++) {
			boolean startCheck = samePlace(x[i], y[i], r[i], x1, y1);
			boolean endCheck = samePlace(x[i], y[i], r[i], x2, y2);
			if (startCheck != endCheck) {
				checkPt += 1;
			} 
		}
		
		return checkPt;
	}
}