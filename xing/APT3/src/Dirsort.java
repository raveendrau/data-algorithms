import java.util.Arrays;
import java.util.Comparator;

public class Dirsort {
	public String[] sort(String[] dirs) {
		Arrays.sort(dirs, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.split("/").length > s2.split("/").length) {
					return 1;
				} else if (s1.split("/").length < s2.split("/").length) {
					return -1;
				} else
					return Arrays
							.toString(s1.split("/"))
							.substring(1,Arrays.toString(s1.split("/")).length() - 1)
							.replaceAll(",", "")
							.compareTo(Arrays.toString(s2.split("/"))
											.substring(1,Arrays.toString(s2.split("/")).length() - 1)
											.replaceAll(",", ""));
			}
		});
		return dirs;
	}
}