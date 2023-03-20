package org.example;

public class GuitaristList implements Enumerable {
	Guitarist[] guitarists = new Guitarist[1000];
	private int size = 0;

	@Override
	public Enumerator enumerator() {
		return new ListEnumerator(this);
	}

	public void add(Guitarist guitarist){
		guitarists[size++] = guitarist;
	}

	public int size(){
		return size;
	}
	public Guitarist get(int index){
		return guitarists[index];
	}
}
