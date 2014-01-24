import java.util.*;

public class GalaxyTrip {
	public boolean[] dependent;
	public boolean[] canTake;
	public HashMap<Integer, ArrayList<Integer>> map;
	public ArrayList<Integer> answer;

	public int[] possibleValues(String[] dependencies) {
		map = new HashMap<Integer, ArrayList<Integer>>();
		answer = new ArrayList<Integer>();

		String isEmpty = "";
		int[] list = new int[dependencies.length];
		for (int i = 0; i < dependencies.length; i++) {
			isEmpty += dependencies[i];
			list[i] = i+1;
		}
		
		if(isEmpty.equals("")){
			return list;
		}

		for (int i = 0; i < dependencies.length; i++) {
			String[] dependents = dependencies[i].split(" ");
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(i);
			if (!dependents[0].equals("")) {
				for (String j : dependents) {
					temp.add(Integer.parseInt(j));
				}
			}
			map.put(i, temp);
		}

		canTake = new boolean[dependencies.length];
		for (int i = 0; i < dependencies.length; i++) {
			dependent = new boolean[dependencies.length];
			if (canTake[i])
				continue;
			checkDependency(i);
			count();
		}

		int[] finalKValues = new int[answer.size()];
		for (int i = 0; i < answer.size(); i++) {
			finalKValues[i] = answer.get(i).intValue();
		}
		answer.clear();
		answer = sum(finalKValues);
		TreeSet<Integer> temp = new TreeSet<Integer>();
		temp.addAll(answer);
		answer.clear();
		answer.addAll(temp);
		finalKValues = new int[answer.size()];
		for (int i = 0; i < answer.size(); i++) {
			finalKValues[i] = answer.get(i).intValue();
		}
		Arrays.sort(finalKValues);
		return finalKValues;
	}

	private void checkDependency(int machine) {
		canTake[machine] = true;
		if (dependent[machine] == true) {
			return;
		}
		dependent[machine] = true;
		ArrayList<Integer> dependents = map.get(machine);
		for (int i : dependents) {
			checkDependency(i);
		}
	}

	private void count() {
		int number = 0;
		for (boolean con : dependent) {
			if (con) {
				number++;
			}
		}
		answer.add(number);
	}

	public ArrayList<Integer> sum(int[] myValues) {
		ArrayList<Integer> sums = new ArrayList<Integer>();
		int limit = (int) (Math.pow(2, myValues.length));
		for (int d = 1; d < limit; d++) {
			int num = d;
			int sum = 0;
			for (int k = 0; k < myValues.length; k++) {
				if (num % 2 == 1) {
					sum += myValues[k];
				}
				num /= 2;
			}
			sums.add(sum);
		}
		return sums;
	}

}