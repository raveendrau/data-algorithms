import java.io.File;


public class DirSearch {

	public File dirSearch(File root, String fileName){
		File[] files = root.listFiles();
		for(File f:files){
			if(f.getName().equals(fileName)){
				return f;
			}
			else{
				if(f.isDirectory()){
					File foundFile = dirSearch(f, fileName);
					if(foundFile != null){
						return foundFile;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args){
		DirSearch search = new DirSearch();
		File f = search.dirSearch(new File("/Users"), "DirSearch.java"); //Mac
		//File f = search.dirSearch(new File("C:\\"), "DirSearch.java"); //Windows
		System.out.println(f.toString());
	}
}
