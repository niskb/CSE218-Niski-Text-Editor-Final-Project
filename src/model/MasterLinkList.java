package model;

public class MasterLinkList {
	private MasterLink first;
	private MasterLink last;

	public MasterLinkList() {
		first = null;
		last = null;
	}

	public boolean insertBefore(String key, String sData) {
		MasterLink current = first;
		while (!current.sData.equals(key)) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		MasterLink newLink = new MasterLink(sData);
		if (current == first) { // current is the first link
			newLink.next = first;
			first.previous = newLink;
			first = newLink;
		} else {
			newLink.next = current;
			current.previous.next = newLink;
			newLink.previous = current.previous;
			current.previous = newLink;
		}
		return true;
	}

	public void displayForward() {
		System.out.println("List (first -> last): ");
		MasterLink current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}

	public void displayBackward() {
		System.out.println("List (Last -> First): ");
		MasterLink current = last;
		while (current != null) {
			current.displayLink();
			current = current.previous;
		}
		System.out.println();
	}

	public MasterLink deleteKey(String key) {
		MasterLink current = first;
		while (!current.sData.equals(key)) {
			current = current.next;
			if (current == null) {
				return null; // not found
			}
		}
		if (current == first) { // found it, first item?
			first = current.next;
		} else {
			current.previous.next = current.next;
		}
		if (current == last) { // found it, last item?
			last = current.previous;
		} else {
			current.next.previous = current.previous;
		}
		return current;
	}

	public boolean insertAfter(String key, String sData) {
		MasterLink current = first;
		while (!current.sData.equals(key)) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		MasterLink newLink = new MasterLink(sData);
		if (current == last) { // if last link
			newLink.next = null;
			last = newLink;
		} else {
			newLink.next = current.next;
			current.next.previous = newLink;
		}
		newLink.previous = current;
		current.next = newLink;
		return true;
	}

	public MasterLink deleteLast() {
		MasterLink temp = last;
		if (first.next == null) { // only one link on the list
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return temp;
	}

	public MasterLink deleteFirst() { // assume non-empty list
		MasterLink temp = first;
		if (first.next == null) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = first.next;
		return temp;
	}

	public void insertLast(String sData) {
		MasterLink newLink = new MasterLink(sData);
		if (isEmpty()) {
			first = newLink;
		} else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(String sData) {
		MasterLink newLink = new MasterLink(sData);
		if (isEmpty()) {
			last = newLink;
		} else {
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}

	public MasterLink getFirst() {
		return first;
	}

	public MasterLink getLast() {
		return last;
	}

	public int getMasterLinkListLength() {
		MasterLink current = first;
		int length = 0;
		while (current != null) {
			current = current.next;
			length++;
		}
		return length;
	}

	public MasterLink getMasterLinkAtIndex(int key) {
		MasterLink current = first;
		for (int i = 0; i < key; i++) {
			current = current.next;
		}
		return current;
	}

	public MasterLink getMasterLinkFromWordData(String sData) {
		MasterLink current = first;
		while (current != null) {
			if (current.sData.equalsIgnoreCase(sData)) {
				return current;
			} else {
				current = current.next;
			}
		}
		return null;
	}

}
