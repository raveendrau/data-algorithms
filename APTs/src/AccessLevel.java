
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

//	public static void main(String[] args) {
//		int[] array01 = {0,1,2,3,4,5};
//		System.out.println(canAccess(array01, 2)); // Returns "DDAAAA"
//		int[] array02 =  {5,3,2,10,0};
//		System.out.println(canAccess(array02, 20)); // Returns "DDDDD"
//		int[] array03 = {};
//		System.out.println(canAccess(array03, 20)); // Returns ""
//		int[] array04 = {34,78,9,52,11,1};
//		System.out.println(canAccess(array04, 49)); // Returns "DADADD"
//	}

}
