/**
 * An object representing a single node in a Twenty Questions game tree.
 * 
 * @author Mac Mason <mac@cs.duke.edu>
 */
public class AnimalNode {
	private AnimalNode myYesChild;
	private AnimalNode myNoChild;
	private String myData;

	public AnimalNode(String data, AnimalNode yes, AnimalNode no) {
		myYesChild = yes;
		myNoChild = no;
		myData = data;
	}

	public String toString() {
		return myData;
	}

	public String print() {
		if (myData.startsWith("#Q"))
			return myData.substring(3);
		else
			return myData;
	}

	public AnimalNode getYes() {
		return myYesChild;
	}

	public AnimalNode getNo() {
		return myNoChild;
	}
	
	public void setData(String data) {
		myData = data;
	}
	
	public void setYes(AnimalNode yes) {
		myYesChild = yes;
	}

	public void setNo(AnimalNode no) {
		myNoChild = no;
	}
}
