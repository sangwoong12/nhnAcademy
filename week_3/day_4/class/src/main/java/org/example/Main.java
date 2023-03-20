package org.example;

import org.example.Guitarist.Builder;

public class Main {

	public static void main(String[] args) {
		Guitarist guitarist1 = new Builder(1, "SangWoong")
			.genre("genre")
			.guitarType("guitarType")
			.teamName("nhnAcademy")
			.playingType("조용하게")
			.guitarMaker("guitarMaker")
			.build();
		Guitarist guitarist2 = new Builder(2, "seoYeon")
			.genre("genre")
			.guitarType("guitarType")
			.teamName("조폭")
			.playingType("시끄럽게")
			.guitarMaker("guitarMaker")
			.build();


		GuitaristList guitaristList = new GuitaristList();
		guitaristList.add(guitarist1);
		guitaristList.add(guitarist2);

		Enumerator enumerator = guitaristList.enumerator();
		while (enumerator.hasMoreElement()){
			Guitarist current = enumerator.current();
			System.out.println(current);
		}

		GuitaristListIterableAdaptor iterable = new GuitaristListIterableAdaptor(guitaristList);
		for (Guitarist guitarist : iterable) {
			System.out.println(guitarist.toString());
		}
	}
}