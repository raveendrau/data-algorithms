public class BigOh{
	public int numOne(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			sum++;
		
		return sum;
	}
	
	public int numTwo(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				sum++;
		return sum;
	}
	
	
	public int numThree(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n*n; j++)
				sum++;
		return sum;
	}
	
	public int numFour(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < i; j++)
				sum++;
		return sum;
	}
	
	public int numFive(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < i*i; j++)
				for(int k = 0; k < j; k++)
					sum++;
		return sum;
	}
	
	public int numSix(int n){
		int sum = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < i*i; j++)
				if(j%i == 0)
					for(int k = 0; k < j; k++)
						sum++;
		
		return sum;
	}
	
	public static void main(String[] args){

		BigOh big = new BigOh();
		int result = 0;
		for(int i = 0; i < 10000; i+=500){
			double start = System.currentTimeMillis();
			result = big.numThree(i);
//			System.out.print(result);
			double end = System.currentTimeMillis();
			System.out.printf("%d: total time = %f   \n", i, (end - start) / 1000);
		}
	}
	
}

	


