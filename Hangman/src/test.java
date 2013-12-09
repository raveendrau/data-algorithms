import java.util.HashSet;

public class test{

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		System.out.println("\n haha");
		
		HashSet<String> set = new HashSet<String>() {{
		add("a");
		add("e");
		add("i");
		add("o");
		add("u");
	}};
	
		String[] letterStr = set.toArray(new String[set.size()]);
		
		/* Print String array */
		for (String x: letterStr) {
			System.out.print(x+",");
		}
		
		char []letterChar; 
		letterChar = new char[letterStr.length]; 
		for (int i=0;i<letterStr.length;i++) {
			letterChar[i] = letterStr[i].charAt(0);
		}
		 
		/* Print char array */
		for (Character x: letterChar) {
			System.out.print(x+",");
		}
		
	}
	
}
