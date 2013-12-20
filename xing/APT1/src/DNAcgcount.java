public class DNAcgcount
{
    public  double ratio(String dna)
    {
            // fill in code here
    	if(dna.length()!=0){
	    		int count = 0;
	    	for(int i = 0; i < dna.length(); i++){
	    		if(dna.charAt(i) == 'c'||dna.charAt(i) == 'g'){
	    			count++;
	    		}
	    	}
	    	return Double.valueOf(count)/dna.length();
    	}
    	else
    		return 0.0;
    }

}

