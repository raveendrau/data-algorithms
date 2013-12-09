/* Keng Low @kl78 */


public class DNAcgcount {

	/**
	 * @param args
	 */
	
	public double ratio(String dna) {
		
		double cgCount = 0;
		double total = dna.length();
				
		for (int i = 0; i < dna.length(); i++) {
			char c = dna.charAt(i);
			if (c == 'c' || c == 'g') {
				System.out.println("We have found a C or G nucleotide");
				cgCount++;
				System.out.println("The number of CG nucleotides are " + cgCount);
			}
		}

		if (cgCount == 0 || total == 0) {
			return 0.0;
		}
		
		System.out.println("CG # at " + cgCount + " with a total of " + total);
		
		double ratio = cgCount/total; 
		
		System.out.println("Ratio obtanied is " + ratio);
		
		return ratio;
		
	}
	
//	public static void main(String[] args) {
//		 
//		String test = "agatc";
//		System.out.println(ratio(test));
//	}
	

}
