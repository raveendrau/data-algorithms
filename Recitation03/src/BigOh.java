


public class BigOh {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] inputs = {250};
		for (int i = 0; i < inputs.length; i++) {
			int input = inputs[i];
			System.out.println("Result of **One** is " + one(input) + " with input of " + input);
			System.out.println("Result of **Two** is " + two(input) + " with input of " + input);
			System.out.println("Result of **Three** is " + three(input) + " with input of " + input);
			System.out.println("Result of **Four** is " + four(input) + " with input of " + input);
			System.out.println("Result of **Five** is " + five(input) + " with input of " + input);
			System.out.println("Result of **Six** is " + six(input) + " with input of " + input);
		}
		
		
	
	}
	
	
	public static int one(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)  
			sum++;
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**One** took a duration of " + time + "ms");
		return sum;
	}
	
	
	public static int two(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				sum++;
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**Two** took a duration of " + time + "ms");
		return sum;
	}
	
	public static int three(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n * n; j++)
				sum++;
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**Three** took a duration of " + time + "ms");
		return sum;
	}
	
	public static int four(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i; j++)
				sum++;
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**Four** took a duration of " + time + "ms");
		return sum;
	}
	
	public static int five(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i * i; j++)
				for (int k = 0; k < j; k++)
					sum++;	
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**Five** took a duration of " + time + "ms");
		return sum;
	}
	
	public static int six(int n) {
		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i * i; j++)
				if (j % i == 0) 
					for (int k = 0; k < j; k++)
						sum++;		
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("**Six** took a duration of " + time + "ms");
		return sum;
	}

}
