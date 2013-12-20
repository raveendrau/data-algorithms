import java.util.*;

public class WordMarkovModel extends AbstractModel {
	private HashMap<WordNgram, ArrayList<WordNgram>> map;
	private String myString;
	private Random myRandom;
	private int lastK;
	public static final int DEFAULT_COUNT = 100; // default # random letters
													// generated

	public WordMarkovModel() {
		map = new HashMap<WordNgram, ArrayList<WordNgram>>();
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
	 * Generate N letters using an order-K markov process where the parameter is
	 * a String containing K and N separated by whitespace with K first. If N is
	 * missing it defaults to some value.
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

	private void generateMap(int k) {
		map.clear();
		String[] wordList = myString.split("\\s+");
		String[] wrapAroundWordList = new String[wordList.length + k];
		for (int i = 0; i < wrapAroundWordList.length; i++) {
			wrapAroundWordList[i] = wordList[i % wordList.length];
		}

		for (int i = 0; i < wordList.length; i++) {
			WordNgram word = new WordNgram(wrapAroundWordList, i, k);
			WordNgram nextWord = new WordNgram(wrapAroundWordList, i + 1, k);
			;
			if (!map.keySet().contains(word)) {
				map.put(word, new ArrayList<WordNgram>(Arrays.asList(nextWord)));
			} else {
				ArrayList<WordNgram> temp = map.get(word);
				temp.add(nextWord);
			}
		}
	}

	public void map(int k, int numLetters) {
		if (k != lastK) {
			generateMap(k);
			lastK = k;
		}
		String[] words = myString.split("\\s+");
		int start = myRandom.nextInt(words.length - k + 1);
		WordNgram seed = new WordNgram(words, start, k);
		StringBuilder build = new StringBuilder();

		double stime = System.currentTimeMillis();
		for (int i = 0; i < numLetters; i++) {
			ArrayList<WordNgram> wordList = map.get(seed);
			WordNgram value = wordList.get(myRandom.nextInt(wordList.size()));
			String nextWord = value.get(k - 1);
			build.append(nextWord + " ");
			seed.append(nextWord);
		}
		double etime = System.currentTimeMillis();
		double time = (etime - stime) / 1000.0;
		this.messageViews("Time to generate: " + time + " secs. Number of keys: " + map.keySet().size());
		this.notifyViews(build.toString());
	}
}
