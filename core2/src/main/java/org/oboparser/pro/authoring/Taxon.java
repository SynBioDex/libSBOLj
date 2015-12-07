package org.oboparser.pro.authoring;

import java.lang.reflect.Array;

public class Taxon extends TermRequest {
	
	public static <T> T[] append(T[] array, T value) { 
		int length = array.length;
		T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), length+1);
		for(int i = 0; i < length; i++) { 
			newArray[i] = array[i];
		}
		newArray[length] = value;
		return newArray;
	}
	
	public static TermRequest organism = new TermRequest("OBI:0100026", "organism");

	public Taxon(String n, TermRequest... isa) {
		super(n, append(isa, organism));
	}

	public Taxon(String id, String n, TermRequest... isa) {
		super(id, n, append(isa, organism));
	}

}
