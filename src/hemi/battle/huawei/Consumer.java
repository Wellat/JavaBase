package hemi.battle.huawei;

public class Consumer {
	private int id;
	private int netNode;
	private int needBand;
	
	@Override
	public String toString() {
		return "Consumer [id=" + id + ", netNode=" + netNode + ", needBand=" + needBand + "]";
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
	}

	public int getNetNode() {
		return netNode;
	}

	public void setNetNode(int netNode) {
		this.netNode = netNode;
	}

}
