import java.util.*;

/**
 * The model for the Markov text generation assignment. See methods
 * for details. This model uses a _extremely_ brute-force algorithm for 
 * generating text, e.g., the entire training text is rescanned each time
 * a new character is generated.
 * @author ola
 *
 */
public class MapMarkovModelOld extends AbstractModel {
	
    private int valueOfK = 0;
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    private String myString;
    private Random myRandom;
    public static final int DEFAULT_COUNT = 100; // default # random letters generated

    public MapMarkovModelOld() {
       
        myRandom = new Random(1234);
    }

    /**
     * Create a new training text for this model based on the information read
     * from the scanner.
     * @param s is the source of information
     */
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
        smart(k, numLetters);
    }

    public void makeMap(int k) {
        for (int i = 0; i < myString.length()-k; i++) {
			String kgram = myString.substring(i, i+k);
			if (i+k+1 < myString.length()) {
				String following = myString.substring(i+1, i+k+1);
				if (map.containsKey(kgram)) {
					ArrayList<String> followers = map.get(kgram);
					followers.add(following);
					map.put(kgram, followers);
				}
				else {
					ArrayList<String> followers = new ArrayList<String>();
					followers.add(following);
					map.put(kgram, followers);
				}
			}				 
		}    	
    }
    
    @SuppressWarnings("unused")
	public void smart(int k, int numLetters) {
        int start = myRandom.nextInt(myString.length() - k + 1);
        String str = myString.substring(start, start + k);   
        StringBuilder build = new StringBuilder();
        ArrayList<Character> list = new ArrayList<Character>();
        double stime = System.currentTimeMillis();
        if (valueOfK != k) {
			makeMap(k);
		}
        for (int i = 0; i < numLetters; i++) {
			ArrayList<String> followers = map.get(str);
			int pick = myRandom.nextInt(followers.size());
			String follower = followers.get(pick);
			char followerChar = follower.charAt(0);
			build.append(followerChar);
			str = str.substring(1) + followerChar;
		}
        double etime = System.currentTimeMillis();
        double time = (etime - stime) / 1000.0;
        this.messageViews("Time to generate: " + time);
        this.notifyViews(build.toString());
    }
}
