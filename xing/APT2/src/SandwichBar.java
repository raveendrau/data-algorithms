import java.util.*;

public class SandwichBar{
	public int whichOrder(String[] available, String[] orders){
		HashSet<String> sandwichBar = new HashSet<String>();
		boolean canOrder = true;
		for (String s: available){
			sandwichBar.add(s);
		}
		for (int i = 0; i < orders.length; i++){
			for(String s2: orders[i].split("\\s+")){
				if (!sandwichBar.contains(s2)){
					canOrder = false;
				}
			}
			if (canOrder){
				return i;
			}
			canOrder = true;
		}
		return -1;
	}
}