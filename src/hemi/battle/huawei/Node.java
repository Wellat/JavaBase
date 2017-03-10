package hemi.battle.huawei;

public class Node {
	private int id;
	private boolean isVisited;

	public Node(int id, boolean isVisited) {
		super();
		this.id = id;
		this.isVisited = isVisited;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", isVisited=" + isVisited + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

}
