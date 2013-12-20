import java.util.*;

public class DrawTree {

	private int index;
	private Map<String, String> map;
	private Map<String, Queue<String>> parentToChild;
	private String[] result;

	public String[] draw(int[] parents, String[] names) {
		index = 1;
		result = new String[parents.length];
		map = new TreeMap<String, String>();
		parentToChild = new TreeMap<String, Queue<String>>();
		if (parents[0] == -1) {
			map.put(names[0], "+-" + names[0]);

			for (int i = 1; i < parents.length; i++) {
				StringBuilder base = new StringBuilder();
				String parentName = names[parents[i]];
				if (parentToChild.get(parentName) != null
						&& parentToChild.get(parentName).size() >= 1) {
					Queue<String> children = parentToChild.get(parentName);
					parentToChild.remove(parentName);
					children.add(names[i]);
					parentToChild.put(parentName, children);
				} else {
					Queue<String> children = new LinkedList<String>();
					children.add(names[i]);
					parentToChild.put(parentName, children);
				}
				String parentOutput = map.get(parentName);
				base.append("  "
						+ parentOutput.substring(0,
								parentOutput.indexOf('-') + 1));
				base.append(names[i]);
				map.put(names[i], base.toString());

			}

			result[0] = map.get(names[0]);
			fill(names[0]);

		} else {
			int rootIndex = names.length - 1;
			map.put(names[rootIndex], "+-" + names[rootIndex]);

			for (int i = rootIndex - 1; i >= 0; i--) {
				StringBuilder base = new StringBuilder();
				String parentName = names[parents[i]];
				if (parentToChild.get(parentName) != null
						&& parentToChild.get(parentName).size() >= 1) {
					Queue<String> children = parentToChild.get(parentName);
					parentToChild.remove(parentName);
					children.add(names[i]);
					parentToChild.put(parentName, children);
				} else {
					Queue<String> children = new PriorityQueue<String>();
					children.add(names[i]);
					parentToChild.put(parentName, children);
				}
				String parentOutput = map.get(parentName);
				base.append("  "
						+ parentOutput.substring(0,
								parentOutput.indexOf('-') + 1));
				base.append(names[i]);
				map.put(names[i], base.toString());

			}

			result[0] = map.get(names[rootIndex]);
			fill(names[rootIndex]);
		}

		return format(result);
	}

	public void fill(String root) {
		Queue<String> children = parentToChild.get(root);
		while (!children.isEmpty()) {
			String child = children.remove();
			result[index] = map.get(child);
			index++;
			if (parentToChild.get(child) != null)
				fill(child);
		}
	}

	public String[] format(String[] output) {
		ArrayList<Integer> checked = new ArrayList<Integer>();
		for (int i = 1; i < output.length; i++) {
			if (checked.contains(i)) {
				continue;
			}
			int nextSiblingIndex = findNextSiblingIndex(i, output);
			if (nextSiblingIndex != -1) {
				checked.add(nextSiblingIndex);
				int n = output[i].indexOf('+');
				for (int j = i + 1; j < nextSiblingIndex; j++) {
					StringBuilder temp = new StringBuilder(output[j]);
					temp.setCharAt(n, '|');
					output[j] = temp.toString();
				}
			}
		}
		return output;
	}

	public int findNextSiblingIndex(int index, String[] output) {
		int plusLocation = output[index].indexOf('+');
		for (int i = index; i < output.length; i++) {
			if (i != index && output[i].indexOf('+') == plusLocation)
				return i;
		}
		return -1;
	}

}