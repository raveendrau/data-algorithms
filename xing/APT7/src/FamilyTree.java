import java.util.*;

public class FamilyTree {
	public Map<String, Set<String>> familyTree;
	
	public int firstBad(String[] data) {
		familyTree = new TreeMap<String, Set<String>>();
		int index;
		for (int i = 0; i < data.length; i++){
			index = i+1;
			String[] names = data[i].split(" ");
			if(!familyTree.containsKey(names[0])){
				Set<String> parents = new TreeSet<String>();
				parents.add(names[1]);
				familyTree.put(names[0], parents);
			}else{
				familyTree.get(names[0]).add(names[1]);
			}
			
			if(!isFamilyTree()){
				return index-1;
			}
		}
		return -1;
	}
	
	private boolean isFamilyTree() {
		// TODO Auto-generated method stub
		for(String child: familyTree.keySet()){
			Set<String> parents = familyTree.get(child);
//			if(parents.contains("m") || parents.contains("f")){
//				String gender = parents.ge
//			}
			if(parents.size()>3){
				return false;
			}
			
		}
		
		return true;
	}

//	public static void main(String[] args){
//		FamilyTree f = new FamilyTree();
//		String[] x ={"BOB JOHN", "CARLA BOB", "JOHN CARLA"};
//		System.out.print(f.firstBad(x) + "\n");
//		
//		for(String s: f.familyTree.keySet()){
//			System.out.print("\n " + s + f.familyTree.get(s).toString());
//		}
//	}
}