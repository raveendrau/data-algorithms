import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
 
public class BSTcount {
  
	private HashMap<String, Long> map = new HashMap<String, Long>();
 
	public static int[] convertIntegers(List<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}
 
	public int[][] partition(int[] values, int pivot) {
		ArrayList<Integer> less = new ArrayList<Integer>();
		ArrayList<Integer> more = new ArrayList<Integer>();
		for (int i : values) {
			if (i < pivot) {
				less.add(i);
			} else if (i > pivot) {
				more.add(i);
			}
		}
 
		int[][] arrs = { convertIntegers(less), convertIntegers(more) };
		return arrs;
	};
	
	public String makeKey(int[] values) {
		StringBuilder b = new StringBuilder();
		for (int i : values) {
			b.append(i);
			b.append(",");
		}
		return b.toString();
	}
 
	public long howMany(int[] values) {
		if (values.length <= 1)
			return 1;
		String key = makeKey(values);
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			long result = 0;
			for (int i = 0; i < values.length; i++) {
				int[][] arrs = partition(values, values[i]);
				result += howMany(arrs[0]) * howMany(arrs[1]);
			}
			map.put(key, result);
			return result;
		}
 
	}
 
 
}