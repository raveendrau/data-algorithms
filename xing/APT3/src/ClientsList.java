import java.util.ArrayList;
import java.util.Collections;

public class ClientsList{
	public String[] dataCleanup(String [] names){
		
		ArrayList<Name> list = new ArrayList<Name>();
		
		for(String s: names){
			String [] n = s.split(" ");
			if(n[0].contains(",")){
				list.add(new Name(n[1], n[0].substring(0, n[0].length()-1)));
			}else
				list.add(new Name(n[0], n[1]));
		}
		Collections.sort(list);
		String[] clientList = new String[names.length];
		for (int i = 0; i < names.length; i++){
			clientList[i] = list.get(i).myFirst + " " + list.get(i).myLast;
		}
		return clientList;
	}
	
	public class Name implements Comparable<Name>{
		
		String myFirst;
		String myLast;
		
		public Name(String first, String last){
			myFirst = first;
			myLast = last;
		}
		@Override
		public int compareTo(Name arg0){
//			int i = this.myLast.compareTo(arg0.myLast);
			
			if(this.myLast.compareTo(arg0.myLast) == 0){
				return this.myFirst.compareTo(arg0.myFirst);
			}
			return this.myLast.compareTo(arg0.myLast);
		}
	}
}