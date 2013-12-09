//import java.io.BufferedReader;
//import java.io.InputStreamReader;

/* Keng Low @kl78 */

public class Gravity {
	
	public double falling(double time, double velo){
	
		/* 
		d = velo * time + 0.5 * 9.8 * time ** 2
		*/
		
		double falling = velo * time + 0.5 * 9.8 * Math.pow(time, 2);
		return falling;
	}
	
	
//	public static void main(String[] args) {
//		String s1 = getInput("Enter a value for time: ");
//		String s2 = getInput("Enter a value for velocity: ");
//
//		double d1 = Double.parseDouble(s1);
//		double d2 = Double.parseDouble(s2);
//		double result = falling(d1, d2);
//		
//		System.out.println("The answer is " + result);
//	
//	}
	
//	private static String getInput(String prompt) {
//		BufferedReader stdin = new BufferedReader(
//				new InputStreamReader(System.in));
//
//		System.out.print(prompt);
//		System.out.flush();
//		
//		try {
//			return stdin.readLine();
//		} catch (Exception e) {
//			return "Error: " + e.getMessage();
//		}
//	}
	
}
