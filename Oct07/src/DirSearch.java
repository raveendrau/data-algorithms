import java.io.File;


public class DirSearch {

	public boolean dirSearch(File root, String fileName){
		@SuppressWarnings("unused")
		File[] files = root.listFiles();

		
		return false;
	}

	public static void main(String[] args){
		DirSearch search = new DirSearch();
		String fileName = "DirSearch.java";
		@SuppressWarnings("unused")
		boolean found = search.dirSearch(new File("/Users"), fileName); //Mac
		//File f = search.dirSearch(new File("../../../"), fileName); //Windows

	}
}
