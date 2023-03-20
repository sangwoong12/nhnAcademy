package org.example;

import java.util.List;

public class ListEnumerator implements Enumerator{

	public ListEnumerator(GuitaristList guitaristList) {
		this.list = guitaristList;
	}

	GuitaristList list;
	int count = 0;
	@Override
	public boolean hasMoreElement() {
		return count < list.size();
	}

	@Override
	public Guitarist current() {
		return list.get(count++);
	}

}
