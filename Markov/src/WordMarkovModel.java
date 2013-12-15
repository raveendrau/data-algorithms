import java.util.*;

public class WordMarkovModel extends AbstractModel {
	private TreeMap<WordNgram, ArrayList<WordNgram>> myMap;
	private String myString;
	private String[] myWords;
	private String prevString;
	private Random myRandom;
	@SuppressWarnings("unused")
	private int lastK;
	public static final int DEFAULT_COUNT = 100; // default # random letters
													// generated

	public WordMarkovModel() {
		myMap = new TreeMap<WordNgram, ArrayList<WordNgram>>();
		myRandom = new Random(1234);
		lastK = 0;
	}

	public void initialize(Scanner s) {
		double start = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int countChar = readChars(s);
		myWords = myString.split("\\s+");
		int countWord = myWords.length;
		double end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		super.messageViews("#read: " + countWord + " words in: " + time + " secs");
	}

	protected int readChars(Scanner s) {
		prevString = myString;
		myString = s.useDelimiter("\\Z").next();
		s.close();
		return myString.length();
	}

	/**
	 * Generate N letters using an order-K markov process where the parameter is
	 * a String containing K and N separated by whitespace with K first. If N is
	 * missing it defaults to some value.
	 */
	public void process(Object o) {
		String temp = (String) o;
		String[] nums = temp.split("\\s+");
		int k = Integer.parseInt(nums[0]);
		int numWords = DEFAULT_COUNT;
		if (nums.length > 1) {
			numWords = Integer.parseInt(nums[1]);
		}
		smart(k, numWords);
	}

	public TreeMap<WordNgram, ArrayList<WordNgram>> makeMap(int k) {
		TreeMap<WordNgram, ArrayList<WordNgram>> map = new TreeMap<WordNgram, ArrayList<WordNgram>>();
		String[] wrapAroundWords = new String[myWords.length + k];
		for (int i = 0; i < myWords.length ; i++)
			wrapAroundWords[i] = myWords[i];
		for (int j = 0; j < k; j++) 
			wrapAroundWords[myWords.length+j] = myWords[j];
		ArrayList<WordNgram> myValues = new ArrayList<WordNgram>();
		for (int i = 0; i < myWords.length; i++) {
			WordNgram fooWords = new WordNgram(wrapAroundWords, i, k);
			if (map.containsKey(fooWords)) myValues = map.get(fooWords);				
			else myValues = new ArrayList<WordNgram>();				
			WordNgram nextWordNgram = new WordNgram(wrapAroundWords, i+1, k);
			myValues.add(nextWordNgram);
			map.put(fooWords, myValues);
		}
		return map;
	}

	public void smart(int k, int numWords) {
		if (myMap.size() == 0) myMap = makeMap(k);
		else {
			List<WordNgram> myKeys = new ArrayList<WordNgram>(myMap.keySet());
			WordNgram myFirst = myKeys.get(0);
			if (myFirst.wordCount() != k || prevString != myString) {
				myMap = makeMap(k);
			}
		}
		
		int start = myRandom.nextInt(myWords.length - k + 1);
		WordNgram seed = new WordNgram(myWords, start, k);
		StringBuilder build = new StringBuilder();

		double stime = System.currentTimeMillis();
		for (int i = 0; i < numWords; i++) {
			ArrayList<WordNgram> nextWords = myMap.get(seed);
			int pick = myRandom.nextInt(nextWords.size());
			WordNgram next = nextWords.get(pick);
			String nextString = next.getWords();
			build.append(nextString+ " ");
			seed = next;
		}
		double etime = System.currentTimeMillis();
		double time = (etime - stime) / 1000.0;
		this.messageViews("Time to generate: " + time + " secs. Number of keys: " + myMap.keySet().size());
		this.notifyViews(build.toString());
	}
}
