import java.util.*;



public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    
    /**
     * The existing code stores words in a ArrayList. 
     * You’ll need to modify the code to sort in method.wordStatus 
     * to use Collections.binarySearch to search the list.
     * 
     * Note that when the index value returned is less than zero, 
     * the value can be used to determine 
     * where a word should be in a sorted list. 
     * For example, when looking up “ela”, 
     * the value returned might be -137. 
     * This means that “ela” is not in the lexicon, 
     * but if it were to be inserted it would be at index 136. 
     * This also means that if the word at index 136 
     * does not begin with “ela”,
     * then no word in the lexicon has a prefix of “ela”.  
     */
    public LexStatus wordStatus(String s) {
        
        // You need to make this code use Binary Search
        int index = Collections.binarySearch(myWords, s);
        /**
         * So, any non-negative value returned by binarySearch 
         * means the status of a word is LexStatus.WORD.
         */
        if (index >= 0) {
        	return LexStatus.WORD;
		}
    	
        /**
         * If the value returned is negative, 
         * one call of the appropriate String.startsWith() 
         * method can determine if LexStatus.PREFIX 
         * should be returned (make sure you don’t go off 
         * the end of the array of words in 
         * the lexicon when calling startsWith).
         */
        else {
        	int insertionPoint = (index * -1) - 1;
        	if (insertionPoint < myWords.size()) {
				if (myWords.get(insertionPoint).startsWith(s)) {
					return LexStatus.PREFIX;
				}
			}
        }
        
        
        return LexStatus.NOT_WORD;
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
