import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphSearch {

	TreeMap<String, List<String>> myGraph = new TreeMap<String, List<String>>();

	public GraphSearch(String[] dependencies) {
		int vertex = 0;
		for (String s : dependencies) {
			String sv = "" + vertex;
			vertex++;
			List<String> list = new ArrayList<String>();
			if (s.equals(""))
				continue; // no vertices, don't parse
			String[] a = s.split(" ");

			for (String nextv : a) {
				list.add(nextv);
			}

			myGraph.put(sv, list);
		}
	}

	public void graphPathSum(String vertex, int target) {
		Set<String> visited = new TreeSet<String>();
		if (dfs(vertex, visited, target)) {
			System.out.print("Starting at " + vertex + ", a path that sums to "
					+ target + ": ");
			for (String s : visited)
				System.out.print(s + " ");
			System.out.println();
		} else {
			System.out.println("Starting at " + vertex
					+ ", there is no path that sums to " + target);
		}
	}

	private boolean dfs(String vertex, Set<String> visited, int target) {
		// TODO: add your 3 base cases
		if (target == 0)
			return true;
		if (target < 0)
			return false;
		if (visited.contains(vertex))
			return false;

		// TODO: visit your node
		visited.add(vertex);
		// TODO: update your new target value
		target = target - Integer.parseInt(vertex);
		// TODO: for each adjacent vertex, perform a dfs
		// if you found the path, you are done
		for(String adj: myGraph.get(vertex)){
			if(dfs(adj, visited, target))
				return true;
		}
		// TODO: if you did not find your path through this vertex, remove it
		// from visited
		// don't forget your return statement
		visited.remove(vertex);
		target += Integer.parseInt(vertex);
		return false;
	}

	public static void main(String[] args) {
		String[] dependencies = { "1 2 3", "0 4 5", "0 6", "0 5", "1", "1 3",
				"2" };
		GraphSearch g = new GraphSearch(dependencies);
		g.graphPathSum("0", 9);
	}
}