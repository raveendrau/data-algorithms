import java.util.*;

public class Encryption {
	public String encrypt(String message){
		String encrypted = "";
		int letter = 97; 
		HashMap<Character, String> store = new HashMap<Character, String>();
		for (int i = 0; i < message.length(); i++){
			if(store.containsKey(message.charAt(i))){
				encrypted += store.get(message.charAt(i));
			}else{
				store.put(message.charAt(i), String.valueOf((char) (letter)));
				encrypted += store.get(message.charAt(i));
				letter ++;
			}		
		}
		return encrypted;
    }
}