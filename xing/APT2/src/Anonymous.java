public class Anonymous{
	public int howMany(String[] headlines, String[] messages) {
		int count = 0;
		String all = "";
		for (String s1: headlines){
			all += s1;
		}
		all = all.replace(" ", "");
		boolean canWrite = true;
		for (String s2: messages){
			String temp = all;
			s2 = s2.replace(" ", "");
			for (int i = 0; i < s2.length(); i++){
				if(temp.contains(String.valueOf(s2.charAt(i))) || temp.contains(String.valueOf(s2.charAt(i)).toUpperCase()) || temp.contains(String.valueOf(s2.charAt(i)).toLowerCase())){
					if (temp.contains(String.valueOf(s2.charAt(i)))){
						temp = temp.replaceFirst(String.valueOf(s2.charAt(i)), "_");
					}else if(temp.contains(String.valueOf(s2.charAt(i)).toUpperCase())){
						temp = temp.replaceFirst(String.valueOf(s2.charAt(i)).toUpperCase(), "_");
					}else{
						temp = temp.replaceFirst(String.valueOf(s2.charAt(i)).toLowerCase(), "_");
					}
				}else{
					canWrite = false;
				}
			}
			if (canWrite){
				count ++;
			}
			canWrite = true;
		}
		return count;
	}
}