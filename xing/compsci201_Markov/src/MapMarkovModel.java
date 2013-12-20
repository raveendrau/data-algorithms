import java.util.*;

public class MapMarkovModel extends AbstractModel {
	private HashMap<String, ArrayList<String>> map;
    private String myString;
    private Random myRandom;
    private int lastK;
    public static final int DEFAULT_COUNT = 100; // default # random letters generated

    public MapMarkovModel() {
    	map = new HashMap<String, ArrayList<String>>();
        myRandom = new Random(1234);
        lastK = 0;
    }

    public void initialize(Scanner s) {
        double start = System.currentTimeMillis();
        int count = readChars(s);
        double end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        super.messageViews("#read: " + count + " chars in: " + time + " secs");
    }

    protected int readChars(Scanner s) {
        myString = s.useDelimiter("\\Z").next();
        s.close();      
        return myString.length();
    }

    /**
     * Generate N letters using an order-K markov process where
     * the parameter is a String containing K and N separated by
     * whitespace with K first. If N is missing it defaults to some
     * value.
     */
    public void process(Object o) {
        String temp = (String) o;
        String[] nums = temp.split("\\s+");
        int k = Integer.parseInt(nums[0]);
        int numLetters = DEFAULT_COUNT;
        if (nums.length > 1) {
            numLetters = Integer.parseInt(nums[1]);
        }
        map(k, numLetters);
    }

    private void generateMap(int k){
    	 map.clear();
    	 String wrapAroundString = myString + myString.substring(0,k);   
         
         for (int i = 0; i < myString.length(); i++){
             String str = wrapAroundString.substring(i, i + k);
             String str_next = wrapAroundString.substring(i+1, i + k+1);
         	if(!map.keySet().contains(str)){
         		map.put(str, new ArrayList<String>(Arrays.asList(str_next)));
         	}else{
         		ArrayList<String> temp = map.get(str);
         		temp.add(str_next);
         	}
         }    
         
    }

    public void map(int k, int numLetters) {
    	double st = System.currentTimeMillis();
    	if (k != lastK){
        	generateMap(k);
        	lastK = k;
        }
        double et = System.currentTimeMillis();
        double time = (et - st) / 1000.0;
//        super.messageViews("#built " + k + "-order map in: " + time + " secs.");
        
        int start = myRandom.nextInt(myString.length() - k + 1);
        String seed = myString.substring(start, start + k);
        StringBuilder build = new StringBuilder();
        
        double stime = System.currentTimeMillis();
        for (int i = 0; i < numLetters; i++) {
        	ArrayList<String> wordList = map.get(seed);
            String value = wordList.get(myRandom.nextInt(wordList.size()));
            char nextChar = value.charAt(k-1);
            build.append(nextChar);
            seed = seed.substring(1) + nextChar;
        }
        double etime = System.currentTimeMillis();
        double time2 = (etime - stime) / 1000.0;
        this.messageViews("#Built map in: " + time + " secs.\nText generated in: " + time2);
        this.notifyViews(build.toString());
    }
}
