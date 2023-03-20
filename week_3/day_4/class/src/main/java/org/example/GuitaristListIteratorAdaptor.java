package org.example;

import java.util.Iterator;

public class GuitaristListIteratorAdaptor implements Iterator<Guitarist> {
	GuitaristList guitaristList;
	int i=0;

	public GuitaristListIteratorAdaptor(GuitaristList guitaristList) {
		this.guitaristList = guitaristList;
	}

	@Override
	public boolean hasNext() {
		return i < guitaristList.size();
	}

	@Override
	public Guitarist next() {
		return guitaristList.get(i++);
	}

}
