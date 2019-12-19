package model;

public class MasterLink {

	public String sData;
	public MasterLink next;
	public MasterLink previous;
	public LinkList nextWordList = new LinkList();

	public MasterLink(String sData) {
		this.sData = sData;
	}

	public void displayLink() {
		System.out.print(sData + " ");
		System.out.println();
		nextWordList.displayForward();
	}

}
