import java.util.Arrays;

public class Picture {
	public static void main(String[] args) {
		Picture p = new Picture();
		p.draw(10);
		
		
		String[] x = new String[10];
		String[] y = {"x", "x", "x", "x"};
		x = y;
		System.out.print(Arrays.toString(x));
	}

	//TODO: Improve this method through using loops
	private void draw(int loops) {	
		String output = "*"; 
		for (int i = 0; i < loops; i++){ 
			System.out.println(output);
			output += "*"; 
		}
	}
}
