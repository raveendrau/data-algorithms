public class CountAppearances {
     public  int numberTimesAppear(int number, int digit) {
    	int num = 0;
    	String a = Integer.toString(number);
    	int[] c = new int[a.length()];
    	for(int i=0; i < a.length();i++){
    		c[i] = Character.digit(a.charAt(i), 10);
    		if(c[i]==digit){
    			num++;
    		}
    		}
    	
    	return num;
     }
   }