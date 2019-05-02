package TreeNode;

public class TreeNode {
	private int index;
	private String charactor;
	private int parentIndex;

	public TreeNode(int index, String charactor, int parentIndex) {
		super();
		this.index = index;
		this.charactor = charactor;
		this.parentIndex = parentIndex;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCharactor() {
		return charactor;
	}
	public void setCharactor(String charactor) {
		this.charactor = charactor;
	}
	public int getParentIndex() {
		return parentIndex;
	}
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

    @Override
    public String toString() {
        return "TreeNode{" + "index=" + index + ", charactor=" + charactor + ", parentIndex=" + parentIndex + '}';
    }
	
}