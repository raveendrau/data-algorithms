public class AccessLevel {
     public String canAccess(int[] rights, int minPermission) {
            // fill in code here
    	 String canAccess  = "";
    	 for(int i = 0; i < rights.length;i++){
    		 if(rights[i]>=minPermission){
    			 canAccess = canAccess.concat("A");
    		 }else
    			 canAccess = canAccess.concat("D");
    	 }
    	 return canAccess;
     }
  }