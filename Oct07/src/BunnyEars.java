
public class BunnyEars {

	/**
	 * Without loops or multiplication
	 * @param bunnies have two ears
	 * @return
	 */
	public int bunnyEars(int bunnies) {
		if (bunnies == 0) return 0;
		return 2 + bunnyEars(bunnies - 1);
	}

}
