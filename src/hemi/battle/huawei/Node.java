package hemi.battle.huawei;

import java.util.ArrayList;

public class Node {
	private int id = 0;
	private ArrayList<Integer> relationNodes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getRelationNodes() {
		return relationNodes;
	}

	public void setRelationNodes(ArrayList<Integer> relationNodes) {
		this.relationNodes = relationNodes;
	}

}
