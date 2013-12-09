import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dirsort {
	
	public String[] sort(String[] dirs) {
		
		Map<Integer, ArrayList<String>> table = new HashMap<Integer, ArrayList<String>>();
		
		ArrayList<String> newDirs = new ArrayList<String>();
		
		for (String dir: dirs) {
			System.out.print(dir);
			int key = dir.split("/").length;
			System.out.println(" key: "+key);
			
			if (table.containsKey(key)) {
				System.out.println("Contains key: "+key);
				ArrayList<String> value = table.get(key);
				value.add(dir);
				System.out.println(table);
			}
			
			else {
				ArrayList<String> value = new ArrayList<String>();
				value.add(dir);
				table.put(key, value);
				System.out.println(table);
			}
		}
		
		for (ArrayList<String> value: table.values()) {
			Collections.sort(value);
			for (String dir: value) newDirs.add(dir);
		}
		
		String[] lastDirs = newDirs.toArray(new String[newDirs.size()]);
		System.out.println(newDirs);
		return lastDirs;
	}

//	public static void main(String[] args) {
////		String games = "games";
////		String homework = "homework";
////		System.out.println(homework.compareTo(games));
//		
//		String[] dir0 = {"/","/usr/","/usr/local/","/usr/local/bin/","/games/","/games/snake/","/homework/","/temp/downloads/"};
////		sort(dir0);
//		// { "/", "/games/", "/homework/", "/usr/", "/games/snake/", "/temp/downloads/", "/usr/local/", "/usr/local/bin/" }	
//		String[] dir1 = {"/usr/","/usr/local/","/bin/","/usr/local/bin/","/usr/bin/","/bin/local/","/bin/local/"};
////		sort(dir1);
//		// { "/bin/", "/usr/", "/bin/local/", "/bin/local/", "/usr/bin/", "/usr/local/", "/usr/local/bin/" }
//		String[] dir2 = {"/","/a/","/b/","/c/","/d/","/e/","/f/","/g/"};
////		sort(dir2);
//		// { "/", "/a/", "/b/", "/c/", "/d/", "/e/", "/f/", "/g/" }
//		String[] dir3 = {"/","/a/","/b/","/c/","/d/","/e/","/f/","/g/","/a/a/","/b/g/c/","/g/f/"};
////		sort(dir3);
//		// { "/", "/a/", "/b/", "/c/", "/d/", "/e/", "/f/", "/g/", "/a/a/", "/g/f/", "/b/g/c/" }
//		String[] dir4 = {"/a/b/c/d/e/f/g/h/i/j/k/l/m/n/","/o/p/q/r/s/t/u/v/w/x/y/z/"};
////		sort(dir4);
//	    // { "/o/p/q/r/s/t/u/v/w/x/y/z/", "/a/b/c/d/e/f/g/h/i/j/k/l/m/n/" }
//		String[] dir5 = {"/a/b/","/ab/cd/","/c/d/","/a/b/c/","/ab/c/d/","/a/bc/d/","/a/b/cd/"};
////		sort(dir5);
//		// { "/a/b/", "/ab/cd/", "/c/d/", "/a/b/c/", "/a/b/cd/", "/a/bc/d/", "/ab/c/d/" }
//	}
	
}
