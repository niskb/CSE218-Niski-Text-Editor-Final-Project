package model;

public class Link {

	public String sData;
	public Link next;
	public Link previous;

	public Link(String sData) {
		this.sData = sData;
	}

	public void displayLink() {
		System.out.print(sData + " ");
	}

}
