
public class Encryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
	}
	

	
	public String encrypt(String message) {
		char[] map = new char[26];
		for (int i = 0; i < 26; i++) {
			map[i] = '*';
			
		String res = "";
		int ct = 0;
		char rep;
		for (Character ch:message.toCharArray()) {
			if (map[ch - 'a']! = '*') {
				res += map[ch-'a'];
			
			}
		}
			
		}	
	}	
		
	