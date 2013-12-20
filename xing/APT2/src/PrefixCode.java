public class PrefixCode{
	public  String isOne(String[] words) {
		boolean result = true;
		int prefix = 0;
		outerloop:
		for (int i = 0; i < words.length; i++){
			for (int j = 0; j < words.length; j++){
				if (i!=j){
					if (words[j].startsWith(words[i])){
						result = false;
						prefix =  i;
						break outerloop;
					}
				}
			}
		}
		if (result){
			return "Yes";
		}else
			return "No, "+ Integer.toString(prefix);
	}
}