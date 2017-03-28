package hemi.battle.huawei;

public class Consumer {
	private int id;
	private int netNode;
	private int needBand;
	private int flag = 1 ;//when begin they are all server.
	private int leftBand;

	public boolean isServer() {
		return flag == 1;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getLeftBand() {
		return leftBand;
	}

	public void setLeftBand(int deltBand) {
		this.leftBand -= deltBand;
	}

	@Override
	public String toString() {
		return "Consumer [id=" + id + ", netNode=" + netNode + ", needBand=" + needBand + ", flag=" + flag
				+ ", leftBand=" + leftBand + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNeedBand() {
		return needBand;
	}

	public void setNeedBand(int needBand) {
		this.needBand = needBand;
		this.leftBand = needBand;
	}

	public int getNetNode() {
		return netNode;
	}

	public void setNetNode(int netNode) {
		this.netNode = netNode;
	}

}
