package model;

public class LinkList {
	private Link first;
	private Link last;

	public LinkList() {
		first = null;
		last = null;
	}

	public boolean insertBefore(String key, String sData) {
		Link current = first;
		while (!current.sData.equals(key)) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		Link newLink = new Link(sData);
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
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}

	public void displayBackward() {
		System.out.println("List (Last -> First): ");
		Link current = last;
		while (current != null) {
			current.displayLink();
			current = current.previous;
		}
		System.out.println();
	}

	public Link deleteKey(String key) {
		Link current = first;
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
		Link current = first;
		while (!current.sData.equals(key)) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		Link newLink = new Link(sData);
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

	public Link deleteLast() {
		Link temp = last;
		if (first.next == null) { // only one link on the list
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return temp;
	}

	public Link deleteFirst() { // assume non-empty list
		Link temp = first;
		if (first.next == null) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = first.next;
		return temp;
	}

	public void insertLast(String sData) {
		Link newLink = new Link(sData);
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
		Link newLink = new Link(sData);
		if (isEmpty()) {
			last = newLink;
		} else {
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}

	public Link getFirst() {
		return first;
	}

	public Link getLast() {
		return last;
	}

	public int getLinkListLength() {
		Link current = first;
		int length = 0;
		while (current != null) {
			current = current.next;
			length++;
		}
		return length;
	}

	public Link getLinkAtIndex(int key) {
		Link current = first;
		for (int i = 0; i < key; i++) {
			current = current.next;
		}
		return current;
	}

}
