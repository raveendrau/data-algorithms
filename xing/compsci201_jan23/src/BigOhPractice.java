
public class BigOhPractice {
	public int numberOne(int n){
		return n;
	}

	public int numberTwo(int n){
		int answer = 1;
		for(int i = 0; i < n; i++)
			answer *= n;
		return answer;
	}

	public int numberThree(int n){
		int answer = 1;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				answer *= n;
		return answer;
	}

	public int numberFour(int n){
		int answer = 1;
		for(int i = 0; i < n; i++)
			answer *= n;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				answer *= n;
		return answer;
	}

	public int numberFive(int n){
		int answer = 1;
		for(int i = 1; i <= n; i=i*2)
			answer *= n;
		return answer;
	}

	public int numberSix(int n){
		int answer = 1;
		for(int i = 1; i <= n; i=i*2)
			for(int j = 0; j < n; j++)
				answer *= n;
		return answer;
	}

	public int numberSeven(int n){
		if(numberTwo(n) > 10000){
			return n;
		}
		else
			return numberFive(n);
	}


	public static void main(String[] args){

		BigOhPractice big = new BigOhPractice();
		for(int i = 0; i < 999999; i+=5000){
			double start = System.currentTimeMillis();
			big.numberSeven(i);
			double end = System.currentTimeMillis();
			System.out.printf("%d: total time = %f\n", i, (end - start) / 1000);
		}
	}

}
