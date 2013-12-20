import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class ListRemove {
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> temp = new ArrayList<String>();

	
	public void fillList(String file) throws FileNotFoundException{
		temp.clear();
		Scanner s = new Scanner(new FileInputStream(file));
		while(s.hasNext()){
			temp.add(s.next());
		}
	}

	public void print(){
		System.out.println("The List");
		if(!list.isEmpty()){
			for (String s: list)
				System.out.print(s + " ");
			System.out.print("\n");
		}else{
			for (String s: temp)
				System.out.print(s + " ");
			System.out.print("\n");
		}
	}
	
	public void removeFromList(){
		for(int i=0; i<temp.size(); i++){
			if(!temp.get(i).startsWith("a")){
				list.add(temp.get(i));
			}else
				temp.remove(i);

		}
	}
	
	public void removeFromListIterator(){
		Iterator<String> it = temp.iterator();
		while(it.hasNext()){
			if(it.next().startsWith("a")){
				it.remove();
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		ListRemove remove1 = new ListRemove();
		
		remove1.fillList("fruit2.txt");
		remove1.print();
		remove1.removeFromList();
		remove1.print();
		
		ListRemove remove2 = new ListRemove();

		remove2.fillList("fruit2.txt");
		remove2.print();
		remove2.removeFromListIterator();
		remove2.print();
	}
}
