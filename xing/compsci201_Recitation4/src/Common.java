public class Common {
   public int count (String a, String b) {
        // TODO: fill in code here
	   int count = 0;
	   for(char c: b.toCharArray()){
		   if(a.indexOf(c)>=0 && b.indexOf(c)>=0){
			   a = a.replaceFirst(Character.toString(c), "_");
			   b = b .replaceFirst(Character.toString(c), "_");
			   count ++;			   
		   }	   
	   }
	   return count;
   }  
}