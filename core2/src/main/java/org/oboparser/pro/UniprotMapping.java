package org.oboparser.pro;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class UniprotMapping {
	
	private Map<String,Set<String>> proAssignments;
	private Map<String,Set<String>> uniprotAssignments;
	
	public boolean containsProID(String pro) { return proAssignments.containsKey(pro); }
	public boolean containsUniprotID(String id) { return uniprotAssignments.containsKey(id); }
	
	public Collection<String> getProIDs(String id) { return uniprotAssignments.get(id); }
	public Collection<String> getUniprotIDs(String pro) { return proAssignments.get(pro); }
	
	public UniprotMapping(File f) throws IOException { 
		BufferedReader br = new BufferedReader(new FileReader(f));
		proAssignments = new TreeMap<String,Set<String>>();
		uniprotAssignments = new TreeMap<String,Set<String>>();
		
		Pattern kbpattern = Pattern.compile(
				"([^:]+):(.*)");
		
		String line;
		while((line = br.readLine()) != null) { 
			String[] array = line.split("\t");
			Matcher m = kbpattern.matcher(array[1]);
			if(!m.matches()) { 
				throw new IllegalArgumentException(line);
			}
			
			String kb = m.group(1);
			String[] ids = m.group(2).split(",");
			String pro = array[0];
			
			if(!proAssignments.containsKey(pro)) { 
				proAssignments.put(pro, new TreeSet<String>());
			}
			
			for(String id : ids) { 
				String iid = id.trim();
				proAssignments.get(pro).add(iid);
				if(!uniprotAssignments.containsKey(iid)) { 
					uniprotAssignments.put(iid, new TreeSet<String>());
				}
				uniprotAssignments.get(iid).add(pro);
			}
		}
		
		br.close();
	}
}
