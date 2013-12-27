import java.util.*;


public class OlympicCandles {
 
	public static int longest(Integer[] candles) {
		// let's find the longest candle 
		Arrays.sort(candles);
		int longest = candles[candles.length - 1];
		return longest;
	}

	@SuppressWarnings("unused")
	public static Integer[] use(int night, Integer[] candles) {
		ArrayList<Integer> candlesList = new ArrayList<Integer>(Arrays.asList(candles));
		ArrayList<Integer> used = new ArrayList<Integer>();
		while (night > 0) {
			int candle = longest(candles);
			night++;
		}
		Integer[] candlesStep = candlesList.toArray(new Integer[candlesList.size()]);
		return candlesStep;
	}
	
	@SuppressWarnings("unused")
	public int numberOfNights(int[] candles) {
		int night = 0;
		
		return 0;
	}

//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//		int[] candles0 = {2, 2, 2};
//		numberOfNights(candles0);
//		// Returns 3
//		int[] candles1 = {2, 2, 2, 4};
//		numberOfNights(candles0);
//		// Returns 4
//		int[] candles2 = {5, 2, 2, 1};
//		numberOfNights(candles0);
//		// Returns 3
//		int[] candles3 = {1, 2, 3, 4, 5, 6};
//		numberOfNights(candles0);
//		// Returns 6
//		int[] candles4 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//		numberOfNights(candles0);
//		// Returns 4
//	}

}
 