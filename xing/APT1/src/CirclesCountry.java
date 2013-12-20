public class CirclesCountry {
    public int leastBorders(int[] x, int[] y, int[] r, 
                            int x1, int y1, int x2, int y2) {
        // you write code here
    	int circles = 0;
    	for(int i = 0; i<x.length; i++){
    		boolean p1 = inCircle(x[i], y[i], r[i], x1, y1);
    		boolean p2 = inCircle(x[i], y[i], r[i], x2, y2);
    		
    		if (p1 && p2){}
    		else{
    			if(p1 || p2)
    				circles++;
    		}
    	}
    		
    	return circles;
    }
    
    public boolean inCircle(int x, int y, int r, int px, int py){
    	double dist = Math.sqrt(Math.pow(px-x, 2) + Math.pow(py-y, 2));
    			if(dist<= r)
    				return true;
    	return false;
    	
    }
    
 }