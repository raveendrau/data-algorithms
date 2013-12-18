
public class RatRoute {

	public static int numRoutes(String[] enc) {
		return 0;
	}

	public static void main(String[] args) {
		String[] enc0 = {
				".R...",
				"..X..",
				"....X",
				"X.X.X",
				"...C."};
		System.out.println(numRoutes(enc0)); // Returns 3
		String[] enc1 = {
				"...X.C",
		"R....."};
		System.out.println(numRoutes(enc1)); // Returns 2
		String[] enc2 = {
				"C..X.",
				".....",
				"X..R.",
				"....."};
		System.out.println(numRoutes(enc2)); // Returns 8
		String[] enc3 = {
				"C........R",
		".........."};
		System.out.println(numRoutes(enc3)); // Returns 1
		String[] enc4 = { "C",
				".",
				".",
				".",
				".",
				".",
				".",
				"X",
				".",
				"R"};
		System.out.println(numRoutes(enc4)); // Returns 0
		String[] enc5 = { 
				"....X",
				".CXX.",
				".XX..",
				"....R"};
		System.out.println(numRoutes(enc5)); // Returns 0
	}

}
