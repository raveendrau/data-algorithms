import java.util.*;

/*
 * This class encapsulates N words/strings so that the
 * group of N words can be treated as a key in a map or an
 * element in a set, or an item to be searched for in an array.
 * <P>
 * @author Xing Su
 */

public class WordNgram implements Comparable<WordNgram>{
    
    private String[] myWords;
    
    /*
     * Store the n words that begin at index start of array list as
     * the N words of this N-gram.
     * @param list contains at least n words beginning at index start
     * @start is the first of the N worsd to be stored in this N-gram
     * @n is the number of words to be stored (the n in this N-gram)
     */
    public WordNgram(String[] list, int start, int n) {
        myWords = new String[n];
        System.arraycopy(list, start, myWords, 0, n);
    }
    
    public int compareTo(WordNgram wg) {
    	return this.toString().compareTo(wg.toString());

    }
    
    public boolean equals(Object o){
        return this.toString().equals(((WordNgram) o).toString());
    }
    
    public int hashCode(){
        return this.toString().hashCode();
    }
    
    public String get(int i){
    	return myWords[i % myWords.length];
    }
    
    public void append(String s){
    	for (int i = 0; i < myWords.length-1; i++){
    		myWords[i] = myWords[i+1];
    	}
    	myWords[myWords.length-1] = s;
    }
    
    public String toString(){
    	return Arrays.toString(myWords);
    }
}
