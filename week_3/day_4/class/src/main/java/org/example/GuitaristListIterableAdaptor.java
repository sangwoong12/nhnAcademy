package org.example;

import java.util.Iterator;

public class GuitaristListIterableAdaptor implements Iterable<Guitarist>{

	GuitaristList guitaristList;

	public GuitaristListIterableAdaptor(GuitaristList guitaristList) {
		this.guitaristList = guitaristList;
	}


	@Override
	public Iterator iterator() {
		return new GuitaristListIteratorAdaptor(guitaristList);
	}

}
