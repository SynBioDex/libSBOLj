package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.oboparser.obo.OBOOntology;
import org.oboparser.obo.OBOStanza;

/**
 * This class provides methods to 
 * 
 * @author Zhen Zhang
 * @version 2.1
 *
 */
public abstract class Ontology {

	protected OBOOntology sequenceOntology = null;
	protected static String URI_PREFIX = null;


	/**
	 * Returns the extracted ID of the given stanza's URI.
	 *
	 * @param stanzaURI the given stanza's URI
	 * @return the extracted ID of the given stanza's URI.
	 */
	public final String getId(URI stanzaURI) {
		String stanzaURIstr = stanzaURI.toString().trim();
		if (!stanzaURIstr.contains(URI_PREFIX)) {
			try {
				throw new IllegalArgumentException("Illegal " + stanzaURI.toString() + ". It does not contain URI prefix " + URI_PREFIX);
			}
			catch (IllegalArgumentException e) {
				return null;
			}
		}
		int beginIndex = stanzaURIstr.lastIndexOf("/") + 1;
		int endIndex = stanzaURIstr.length() -1 ;
		return stanzaURIstr.substring(beginIndex, endIndex);
	}

	/**
	 * Returns the ID field of the stanza whose name matches the given name. If multiple matches are found, only the first matching
	 * one is returned.
	 *
	 * @param stanzaName the given name for the stanza
	 * @return the ID the matching stanza, or {@code null} if no match is found.
	 */
	public final String getId(String stanzaName) {
		//return sequenceOntology.getStanza(stanzaName).getName();
		List<String> IdList = new ArrayList<String>();
		for (OBOStanza stanza : sequenceOntology.getStanzas()) {
			if (stanzaName.trim().equals(stanza.getName().trim())) {
				IdList.add(stanza.getId());
			}
		}
		if (IdList.isEmpty()) {
			return null;
		}
		return IdList.get(0);
	}


	/**
	 * Returns the name field of the stanza that matches the ID for the given stanzaURI.
	 *
	 * @param stanzaURI the given stanzaURI
	 * @return the name field of the stanza that matches the ID in the given stanzaURI, or {@code null} if this no match is found.
	 */
	public final String getName(URI stanzaURI) {
		String oboURIstr = stanzaURI.toString().trim();
		if (!oboURIstr.contains(URI_PREFIX)) {
			try {
				throw new IllegalArgumentException("Illegal " + stanzaURI.toString() + ". It does not contain URI prefix " + URI_PREFIX);
			}
			catch (IllegalArgumentException e) {
				return null;
			}
		}
		int beginIndex = oboURIstr.lastIndexOf("/") + 1;
		int endIndex = oboURIstr.length() -1 ;
		String id = oboURIstr.substring(beginIndex, endIndex);
		OBOStanza oboStanza = sequenceOntology.getStanza(id);
		return oboStanza.getName();
	}

	/**
	 * Returns the name field of the stanza that matches the ID in the given stanzaURI.
	 *
	 * @param stanzaId the given stanzaURI
	 * @return the name field of the stanza that matches the ID in the given stanzaURI,
				or {@code null} if this no match is found.
	 */
	public final String getName(String stanzaId) {
		return sequenceOntology.getStanza(stanzaId).getName();
	}

	/**
	 * Returns the URI, i.e. the Sequence Ontology namespace URL followed by an ID of an sequence ontology term,
	 * of the stanza whose name matches the given name. If multiple matches are found, only the first matching
	 * one is returned.
	 *
	 * @param stanzaName the given stanza name
	 * @return the URI of the given sequence ontology name, or {@code null} if no match is found.
	 */
	public final URI getURIbyName(String stanzaName) {
		String stanzaId = getId(stanzaName);
		if (stanzaId == null) {
			return null;
		}
		return URI.create(stanzaId);
	}

	/**
	 * Creates a new URI from the Sequence Ontology namespace with the given ID. For example, the function call
	 * <code>type("SO:0000001")</code> will return the URI <a>http://identifiers.org/so/SO:0000001</a>
	 * @param stanzaId the given stanzaId
	 * @return the created URI
	 */
	public final URI getURIbyId(String stanzaId) {
		//return type(stanzaId.trim());
		return URI.create(URI_PREFIX+stanzaId);
	}

	/**
	 * Creates a new URI from the Sequence Ontology namespace with the given ID. For example, the function call
	 * <code>type("SO:0000001")</code> will return the URI <a>http://identifiers.org/so/SO:0000001</a>
	 * @param id the given ID for creating a new URI
	 * @return the created URI
	 */
	public final static URI type(String id) {
		return URI.create(URI_PREFIX+id);
	}

	/**
	 * Returns {@code true} if the stanza with Id1 is a descendant of the stanza with Id2.
	 * @param Id1 the given stanzaId
	 * @param Id2 the given stanzaId
	 * @return {@code true} if the stanza with Id1 is a descendant of the stanza with Id2, {@code false} otherwise.
	 */
	public boolean isDescendantOf(String Id1, String Id2) {
		OBOStanza stanza1 = sequenceOntology.getStanza(Id1);
		OBOStanza stanza2 = sequenceOntology.getStanza(Id2);
		if (stanza1 == null) {
			try {
				throw new IllegalArgumentException("Illegal ID: " + Id1 + ". No match was found.");
			}
			catch (IllegalArgumentException e) {
				return false;
			}
		}
		if (stanza2 == null) {
			try {
				throw new IllegalArgumentException("Illegal ID: " + Id2 + ". No match was found.");
			}
			catch (IllegalArgumentException e) {
				return false;
			}
		}
		return sequenceOntology.isDescendantOf(stanza1, stanza2);
	}

}
