import java.util.*; 

public class uniqueCount{
	 
 
	 public static void uniqueCounts(String[] list) {
 
    Arrays.sort(list);
    String last = list[0];
    int count = 1;
    // Invariant: count is number of occurrences of last seen so far

    for (int k = 1; k < list.length; k++) {
        if (!list[k].equals(last)) {
        	System.out.println(count + "\t" + last);
        	count = 1;
        	last = list[k];
        }else
        	count++;
    	}
	}
	
	 public static void main(String[] args) {
		 String[] in = new String[5];
		 in[0] = "Apple";
		 in[1] = "Apple";
		 in[2] = "Apple";
		 in[3] = "banana";
		 in[4] = "banana";
		 
		 uniqueCounts(in);
	 }


}

        