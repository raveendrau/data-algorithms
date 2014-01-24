/**
 * Two strings have a letter in common if it appears in both strings. 
 * The position of letter is not important for it to be counted as common. 
 * Once a particular letter is counted as in common, it cannot be counted again. 
 * For example, an o appearing twice in one word but only once in the other 
 * counts only as one letter in common. 
 * However, an o appearing twice both words counts as two letters in common.
 * @author keng
 *
 */
public class Common {
	/**
	 * 
	 * @param String a
	 * @param String b
	 * @return the number of letters they have in common
	 */
	public int count (String a, String b) {
    
		// Convert String to character array
		char[] aArray = a.toCharArray();
		char[] bArray = b.toCharArray();
    
		// Initialize counter
		int count = 0;
    
		// Iterate over each letter of the string
		// We can do this without worrying about different string lengths, since
		// String a and String b are equal in lengths.
		for(int i = 0; i < aArray.length; i++){
			char aChar = aArray[i];
			// Find the first occuernce of the same character in String b
			int posB = b.indexOf(aChar);
			if(posB >= 0){
				// Put a placeholder to mark it as counted
				bArray[posB] = '.';
				count++;
				// Update the new string
				b = new String(bArray);
			}
		}
    return count;
  }
}