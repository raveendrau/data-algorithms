import java.util.*;

public class Internet {

	public boolean[] connected;

	public HashMap<Integer, ArrayList<Integer>> map;

	public int articulationPoints(String[] routers) {
		map = new HashMap<Integer, ArrayList<Integer>>();
		int answer = 0;

		for (int i = 0; i < routers.length; i++) {
			String[] connectionsString = routers[i].split(" ");
			ArrayList<Integer> connections = new ArrayList<Integer>();
			for (String s : connectionsString) {
				connections.add(Integer.parseInt(s));
			}
			map.put(i, connections);
		}
		
		
		connected = new boolean[routers.length];
		checkConnection(1, 0);

		if (isArticulationPoint()) {
			answer++;
		}
		
		for (int i = 1; i < routers.length; i++) {
			connected = new boolean[routers.length];
			checkConnection(i - 1, i);
			if (isArticulationPoint()) {
				answer++;

			}
		}
		return answer;
	}

	private void checkConnection(int router, int out) {
		connected[out] = true;
		
		if (router == out || connected[router] == true) {
			return;
		}
		connected[router] = true;
		ArrayList<Integer> connections = map.get(router);
		for (int i : connections) {
			checkConnection(i, out);
		}
		
	}

	private boolean isArticulationPoint() {
		for (boolean i : connected) {
			if (!i){
				return true;
			}
		}
		return false;
	}
}