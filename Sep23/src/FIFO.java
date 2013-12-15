import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class FIFO {

	public static void FIFO1() {
		Queue<String> q = new LinkedList<String>();
		q.add("Apple ");
		q.add("iPad ");
		q.add("mini ");
		q.add("with ");
		q.add("Retina ");
		q.add("Display ");
		while (!q.isEmpty()) {
			System.out.print(q.remove());
		}
	}
	
	public static void LIFO() {
		System.out.println("");
		Stack<String> q = new Stack<String>();
		q.push("Apple ");
		q.push("iPad ");
		q.push("mini ");
		q.push("with ");
		q.push("Retina ");
		q.push("Display ");
		while (!q.isEmpty()) {
			System.out.print(q.pop());
		}
	}
	
	public static void main(String[] args) {
		FIFO1();
		LIFO();
	}

}
