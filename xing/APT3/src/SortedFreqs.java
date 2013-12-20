import java.util.TreeMap;

public class SortedFreqs {
    public int[] freqs(String[] data) {
        TreeMap<String, Integer> freq = new TreeMap<String, Integer>();
        for(String s: data){
        	if(!freq.containsKey(s)){
        		freq.put(s, 1);
        	}else{
        		freq.put(s, freq.get(s)+1);
        	}
        }
        int[] sorted = new int[freq.size()];
        
        for (int i = 0; i < freq.values().size(); i++){
        	sorted[i] = (int) freq.values().toArray()[i];
        }
        return sorted;
    }
    
}