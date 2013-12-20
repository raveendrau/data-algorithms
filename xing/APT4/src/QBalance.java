public class QBalance {
	public int bindex(String symbols) {
		
		String left = "_([{";
		String right = "_)]}";
		int leftIndex = 0;
		int leftSymbol = '\0';
		int leftCount = 0;
		int rightCount = 0;
		for (char c : symbols.toCharArray()) {
			if (left.indexOf(c) >= 0) {
				leftIndex = symbols.indexOf(c);
				leftSymbol = left.indexOf(c);
				leftCount++;
			}
			if (right.indexOf(c) >= 0)
				rightCount ++;
		}
		if (leftCount == 0 && rightCount == 0)
			return -1;
		else {
			for (int i = leftIndex; i < symbols.length(); i++) {
				if (right.indexOf(symbols.charAt(i)) >= 0 && symbols.charAt(i) == right.charAt(leftSymbol)) {
					return bindex(symbols.substring(0,leftIndex)+" "+symbols.substring(leftIndex+1, i) + " " + symbols.substring(i+1));
				}else if (right.indexOf(symbols.charAt(i)) >= 0 && symbols.charAt(i) != right.charAt(leftSymbol)){
					return i;
				}
			}
		}
		return symbols.length();
	}
}
