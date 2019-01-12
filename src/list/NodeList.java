package list;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeList {
	public ArrayList<Node1> nodeList = new ArrayList<Node1>();
	
	public boolean isExist(String name) {
		Iterator<Node1> itr = nodeList.iterator();
		while(itr.hasNext()) {
			if (itr.next().getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNode(Node1 n) {
		nodeList.add(n);
	}

}
