package de.hpi.pjung.playground;

public class Element<T> {
	
	private T value = null;
	private Element<T> nextElement = null;
	
	public Element<T> getNextElement() {
		return nextElement;
	}

	public void setNextElement(Element<T> nextElement) {
		this.nextElement = nextElement;
	}

	public Element(T element){
			this.value = element;
	}
	
	@Override
	public String toString() {
		return "Element [value=" + value + ", nextElement=" + nextElement + "]";
	}

	public static void main(String args[]){
		String word = "Hallo";
		Element<String> elem = new Element<String>(word);
		System.out.println(elem);
		
	}
}

