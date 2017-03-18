package hemi.battle.huawei;

import java.util.ArrayList;

public class Node {
	private int id;
	private int flag;
	private ArrayList<Integer> relationNodes;

	public boolean isServer() {
		return flag == 1;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

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

	@Override
	public String toString() {
		return "Node [id=" + id + ", flag=" + flag + ", relationNodes=" + relationNodes + "]";
	}
}
