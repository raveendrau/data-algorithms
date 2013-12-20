import java.util.*;

public class SimpleWordGame {
    public  int points(String[] player, String[] dictionary) {
    	int points = 0;
    	HashSet<String> words = new HashSet<String>();
    	HashSet<String> remembered = new HashSet<String>();
    	for (String s2: dictionary){
    		words.add(s2);
    	}
    	for (int i = 0; i < player.length; i++){
    		if(words.contains(player[i]) && remembered.add(player[i])){
    			remembered.add(player[i]);
    			points += player[i].length()*player[i].length();
    		}
    	}
    	return points;
    }
}