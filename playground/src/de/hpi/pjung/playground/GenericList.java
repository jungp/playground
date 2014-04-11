package de.hpi.pjung.playground;

public class GenericList<T> {
	
	private Element<T> first = null;
	private Element<T> last = null;
	private int length = 0;
	
	public int getLength() {
		return length;
	}

	public GenericList(){
		this.first = null;
	}
	
	// add in the end of list
	private GenericList<T> add(Element<T> elem){
		if (this.first == null){
			this.first = elem;
			this.last = elem;
		} else {
			this.last.setNextElement(elem);
			this.last = elem;
		}
		length++;
		return this;
	}
	
	// remove from beginning of list
	private  GenericList<T> remove(){
		if (this.first != null){
			this.first = this.first.getNextElement();
		} 
		
		if (length > 0) length--;
		return this;
	}

	
	public static void main(String args[]){
		GenericList<String> list = new GenericList<String>();
		
		String var1 = "Hello";
		String var2 = "World!";
		String var3 = "It's me.";
		
		Element<String> element1 = new Element<String>(var1);
		Element<String> element2 = new Element<String>(var2);
		Element<String> element3 = new Element<String>(var3);

//		list.add(element1);
//		System.out.println(list.length);
//		list.add(element2);
//		System.out.println(list.length);
//		list.add(element3);
//		System.out.println(list.length);
		
		list.remove();
		System.out.println(list.length);
		list.remove();
		System.out.println(list.length);
		list.remove();
		System.out.println(list.length);
		list.remove();
		System.out.println(list.length);
		
		System.out.println(list.first);
		System.out.println(list.last);
		
	}
}
