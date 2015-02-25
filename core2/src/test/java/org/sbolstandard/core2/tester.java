package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashMap;

public class tester {
	
	public static void main(String[] args) {
		HashMap<URI, String> tmp = new HashMap<URI, String>();
		URI uri1 = URI.create("http://www.async.ece.utah.edu/pLactetRSeq/1/0");
		URI uri2 = URI.create("http://www.async.ece.utah.edu/tetRSeq/1/0");
		URI uri3 = URI.create("http://www.async.ece.utah.edu/pLacSeq/1/0");
		tmp.put(uri1, "uri1");
		tmp.put(uri2, "uri2");
		tmp.put(uri3, "uri3");
		
		System.out.println(uri1.toString());
		URI uri4 = URI.create("http://www.async.ece.utah.edu/pLactetRseq/1/0");
		System.out.println("uri4 exists in map? " + tmp.containsKey(uri4));
		System.out.println("get uri4 form map: " + tmp.get(uri4));
		System.out.println(uri4.equals(uri1));
		
			
			
	}
	
}
