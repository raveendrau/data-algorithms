
public class ThreeInts {

	private static Integer[] ThreeInts(int i, int j, int k) {
		Integer[] fives = new Integer[]{i, j, k};
		return fives;
	}
	
	private static void compare() {
		Integer[] a = ThreeInts(5,5,5);
		Integer[] b = ThreeInts(5,5,5);
		if (a.equals(b)) {
			System.out.println("Equal");
		}
		else {
			System.out.println("Not equal");
		}
		
	}

	public static void main(String[] args) {
		compare();

	}

}
