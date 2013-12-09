/* Keng Low @kl78 */ 

public class CountAppearances {
		
//	public static void main(String[] args) {
//		int number01 = 56854;
//		int digit01 = 5;
//		numberTimesAppear(number01, digit01);
//	}
	
	public int numberTimesAppear(int number, int digit) {
		String numberStr = Integer.toString(number);
		char digitChar = Character.forDigit(digit, 10);
		System.out.println("Looking for " + digitChar + " in " + numberStr);
		int numberTimes = 0;
		for (int i = 0; i < numberStr.length(); i++) {
			char numberAt = numberStr.charAt(i);
			if (numberAt == digitChar) {
				numberTimes++;
			}
		}
		return numberTimes; 
	}
}