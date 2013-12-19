
public class AccessLevel {

	public String canAccess(int[] rights, int minPermission) {
		StringBuilder ret = new StringBuilder();
		for (int right : rights) {
			if (right >= minPermission) {
				ret.append("A");
			}
			else if (right < minPermission){
				ret.append("D");
			}
		}
		return ret.toString();
	}

	public static void main(String[] args) {
		System.out.println(canAccess({0,1,2,3,4,5},2)); // Returns "DDAAAA" 
		System.out.println(canAccess({5,3,2,10,0},20)); // Returns "DDDDD"
		System.out.println(canAccess({},20)); // Returns ""
		System.out.println(canAccess({34,78,9,52,11,1},49)); // Returns "DADADD"
	}

}
