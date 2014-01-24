import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


/**
 * @author keng
 *
 * You will be given a String[] pubs, each element of which describes the list 
 * of authors of a single publication and is formatted as "AUTHOR_1 AUTHOR_2 ... 
 * AUTHOR_N" (quotes for clarity only). Paul Erdos will be given as "ERDOS".
 *
 * Return the list of Erdos numbers which will be assigned to the authors of the 
 * listed publications. Each element of your return should be formatted as 
 * "AUTHOR NUMBER" if AUTHOR can be assigned a finite Erdos number, and just 
 * "AUTHOR" otherwise. The authors in your return must be ordered 
 * lexicographically.
 */
public class ErdosNumber {
	
	public String[] calculateNumbers(String[] pubs) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		HashSet<String> set = new HashSet<String>();
		
		map.put("ERDOS", 0);
		
		int i = 0;

		while (i < pubs.length) {
			for (String pub : pubs) {
				String[] writers = pub.split(" ");
				int min = 1000000;
				
				for (String writer : writers) {
					set.add(writer);
					if (map.containsKey(writer) && map.get(writer) < min)
						min = map.get(writer);
				}
				
				if (min < 1000000) {
					for (String writer : writers) {
						if (!map.containsKey(writer) || map.get(writer) > min)
							map.put(writer, min + 1);
					}
				}
			}
			i++;
		}

		ArrayList<String> ret = new ArrayList<String>(set);
		Collections.sort(ret);
		for (i = 0; i < ret.size(); ++i) {
			if (map.containsKey(ret.get(i))) {
				ret.set(i, ret.get(i) + " " + map.get(ret.get(i)));
			}
		}
		return ret.toArray(new String[ret.size()]);
	}
}
