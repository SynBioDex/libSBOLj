package org.sbolstandard.core2;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import uk.co.turingatemyhamster.opensmiles.OpenSmilesParser;

/**
 * Provides functionality for validating SBOL data models.
 * 
 * @author Zhen Zhang
 * @author Chris Myers
 * @version 2.1
 */

public class SBOLValidate {

	/**
	 * the current SBOL version
	 */
	private static final String SBOLVersion = "2.0";
	private static final String libSBOLj_Version = "2.1";
	private static List<String> errors = null;

	/**
	 * Empties the error list used to store SBOL validation exceptions.
	 */
	public static void clearErrors() {
		errors = new ArrayList<String>();
	}

	/**
	 * Returns the error list used to store SBOL validation exceptions.
	 * @return the error list used to store SBOL validation exceptions
	 */
	public static List<String> getErrors() {
		return errors;
	}

	/**
	 * Returns the number of errors in the error list.
	 * @return the number of errors in the error list
	 */
	public static int getNumErrors() {
		return errors.size();
	}

	/**
	 * Validate SBOL objects are compliant in the given {@code sbolDocument}.
	 *
	 * @param sbolDocument the SBOLDocument object to be validated
	 * @throws SBOLValidationException if any top-level objects or any of their children or grandchildren
	 * in the given {@code sbolDocument} contain a non-compliant URI.
	 */
	static void validateCompliance(SBOLDocument sbolDocument) {
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			try {
				topLevel.isURIcompliant();
			}
			catch (SBOLValidationException e) {
				errors.add(e.getMessage());
			}
		}
	}

	protected static void checkCollectionCompleteness(SBOLDocument sbolDocument,Collection collection) {
		for (URI member : collection.getMemberURIs()) {
			if (sbolDocument.getTopLevel(member)==null) {
				SBOLValidationException e = new SBOLValidationException("sbol-12103", collection);
				errors.add(e.getMessage());
			}
		}
	}

	protected static void checkComponentDefinitionCompleteness(SBOLDocument sbolDocument,ComponentDefinition componentDefinition) {
		for (URI sequenceURI : componentDefinition.getSequenceURIs()) {
			if (sbolDocument.getSequence(sequenceURI)==null) {
				errors.add(new SBOLValidationException("sbol-10513", componentDefinition).getExceptionMessage());
			}
		}
		for (Component component : componentDefinition.getComponents()) {
			if (component.getDefinition()==null) {
				errors.add(new SBOLValidationException("sbol-10604", component).getExceptionMessage());
			}
			for (MapsTo mapsTo : component.getMapsTos()) {
				if (mapsTo.getRemote()==null) {
					errors.add(new SBOLValidationException("sbol-10808", mapsTo).getExceptionMessage());
					continue;
				}
				if (mapsTo.getRemote().getAccess().equals(AccessType.PRIVATE)) {
					errors.add(new SBOLValidationException("sbol-10807", mapsTo).getExceptionMessage());
				}
				if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
					if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
						errors.add(new SBOLValidationException("sbol-10811").getExceptionMessage());
					}
				}
			}
		}
	}

	/**
	 * @param componentDefinition
	 * @param mapsTo
	 * @throws SBOLValidationException the following SBOL validation rule was violated: 10526
	 */
	protected static void checkComponentDefinitionMapsTos(ComponentDefinition componentDefinition,MapsTo mapsTo) throws SBOLValidationException {
		for (Component component : componentDefinition.getComponents()) {
			for (MapsTo mapsTo2 : component.getMapsTos()) {
				if (mapsTo==mapsTo2) continue;
				if (mapsTo.getLocalURI().equals(mapsTo2.getLocalURI()) &&
						mapsTo.getRefinement().equals(RefinementType.USEREMOTE) &&
						mapsTo2.getRefinement().equals(RefinementType.USEREMOTE)) {
					throw new SBOLValidationException("sbol-10526",componentDefinition);
				}
			}
		}
	}

	protected static void checkModuleDefinitionMapsTos(ModuleDefinition moduleDefinition,MapsTo mapsTo) throws SBOLValidationException {
		for (Module module : moduleDefinition.getModules()) {
			for (MapsTo mapsTo2 : module.getMapsTos()) {
				if (mapsTo==mapsTo2) continue;
				if (mapsTo.getLocalURI().equals(mapsTo2.getLocalURI()) &&
						mapsTo.getRefinement().equals(RefinementType.USEREMOTE) &&
						mapsTo2.getRefinement().equals(RefinementType.USEREMOTE)) {
					throw new SBOLValidationException("sbol-11609",moduleDefinition);
				}
			}
		}
		for (FunctionalComponent functionalComponent : moduleDefinition.getFunctionalComponents()) {
			for (MapsTo mapsTo2 : functionalComponent.getMapsTos()) {
				if (mapsTo==mapsTo2) continue;
				if (mapsTo.getLocalURI().equals(mapsTo2.getLocalURI()) &&
						mapsTo.getRefinement().equals(RefinementType.USEREMOTE) &&
						mapsTo2.getRefinement().equals(RefinementType.USEREMOTE)) {
					throw new SBOLValidationException("sbol-11609",moduleDefinition);
				}
			}
		}
	}

	static void validateMapsTos(SBOLDocument sbolDocument) {
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			for (Component component : componentDefinition.getComponents()) {
				for (MapsTo mapsTo : component.getMapsTos()) {
					try {
						checkComponentDefinitionMapsTos(componentDefinition,mapsTo);
					}
					catch (SBOLValidationException e) {
						errors.add(e.getMessage());
					}
				}
			}
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			for (Module module : moduleDefinition.getModules()) {
				for (MapsTo mapsTo : module.getMapsTos()) {
					try {
						checkModuleDefinitionMapsTos(moduleDefinition,mapsTo);
					}
					catch (SBOLValidationException e) {
						errors.add(e.getMessage());
					}
				}
			}
			for (FunctionalComponent functionalComponent : moduleDefinition.getFunctionalComponents()) {
				for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
					try {
						checkModuleDefinitionMapsTos(moduleDefinition,mapsTo);
					}
					catch (SBOLValidationException e) {
						errors.add(e.getMessage());
					}
				}
			}
		}
	}

	protected static void checkModuleDefinitionCompleteness(SBOLDocument sbolDocument,ModuleDefinition moduleDefinition) {
		for (URI modelURI : moduleDefinition.getModelURIs()) {
			if (sbolDocument.getModel(modelURI) == null) {
				errors.add(new SBOLValidationException("sbol-11608", moduleDefinition).getExceptionMessage());
			}
		}
		for (FunctionalComponent functionalComponent : moduleDefinition.getFunctionalComponents()) {
			if (functionalComponent.getDefinition() == null) {
				errors.add(new SBOLValidationException("sbol-10604", functionalComponent).getExceptionMessage());
			}
			for (MapsTo mapsTo : functionalComponent.getMapsTos()) {
				if (mapsTo.getRemote()==null) {
					errors.add(new SBOLValidationException("sbol-10808", mapsTo).getExceptionMessage());
					continue;
				}
				if (mapsTo.getRemote().getAccess().equals(AccessType.PRIVATE)) {
					errors.add(new SBOLValidationException("sbol-10807", mapsTo).getExceptionMessage());
				}
				if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
					if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
						errors.add(new SBOLValidationException("sbol-10811").getExceptionMessage());
					}
				}
			}
		}
		for (Module module : moduleDefinition.getModules()) {
			if (module.getDefinition() == null) {
				errors.add(new SBOLValidationException("sbol-11703", module).getExceptionMessage());
			}
			for (MapsTo mapsTo : module.getMapsTos()) {
				if (mapsTo.getRemote()==null) {
					errors.add(new SBOLValidationException("sbol-10809", mapsTo).getExceptionMessage());
					continue;
				}
				if (mapsTo.getRemote().getAccess().equals(AccessType.PRIVATE)) {
					errors.add(new SBOLValidationException("sbol-10807", mapsTo).getExceptionMessage());
				}
				if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
					if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
						errors.add(new SBOLValidationException("sbol-10811").getExceptionMessage());
					}
				}
			}
		}
	}

	/**
	 * Validate if all URI references to SBOL objects are in the same given {@code sbolDocument}.
	 *
	 * @param sbolDocument The SBOLDocument object to be validated for completeness
	 */
	static void validateCompleteness(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			checkCollectionCompleteness(sbolDocument,collection);
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			checkComponentDefinitionCompleteness(sbolDocument,componentDefinition);
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			checkModuleDefinitionCompleteness(sbolDocument,moduleDefinition);
		}
	}

	/**
	 * @param sbolDocument
	 * @param componentDefinition
	 * @param visited
	 * @throws SBOLValidationException if either of the following SBOL validation rule was violated: 10603, 10605.
	 */
	protected static void checkComponentDefinitionCycle(SBOLDocument sbolDocument,
			ComponentDefinition componentDefinition, Set<URI> visited) throws SBOLValidationException {
		if (componentDefinition==null) return;
		visited.add(componentDefinition.getIdentity());
		for (Component component : componentDefinition.getComponents()) {
			ComponentDefinition cd = component.getDefinition();
			if (cd==null) continue;
			if (visited.contains(cd.getIdentity())) {
				throw new SBOLValidationException("sbol-10603",component);
			}
			try {
				checkComponentDefinitionCycle(sbolDocument,cd,visited);
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-10605",component);
			}
		}
		visited.remove(componentDefinition.getIdentity());
		return;
	}

	protected static void checkModuleDefinitionCycle(SBOLDocument sbolDocument,
			ModuleDefinition moduleDefinition, Set<URI> visited) throws SBOLValidationException {
		if (moduleDefinition==null) return;
		visited.add(moduleDefinition.getIdentity());
		for (Module module : moduleDefinition.getModules()) {
			ModuleDefinition md = module.getDefinition();
			if (md==null) continue;
			if (visited.contains(md.getIdentity())) {
				throw new SBOLValidationException("sbol-11704",module);
			}
			try {
				checkModuleDefinitionCycle(sbolDocument,md,visited);
			} catch (SBOLValidationException e) {
				throw new SBOLValidationException("sbol-11705",module);
			}
		}
		visited.remove(moduleDefinition.getIdentity());
		return;
	}

	protected static boolean checkWasDerivedFromVersion(SBOLDocument sbolDocument, Identified identified,
			URI wasDerivedFrom) {
		Identified derivedFrom = sbolDocument.getTopLevel(wasDerivedFrom);
		if ((derivedFrom!=null) &&
				(derivedFrom.isSetPersistentIdentity() && identified.isSetPersistentIdentity()) &&
				(derivedFrom.getPersistentIdentity().equals(identified.getPersistentIdentity())) &&
				(derivedFrom.isSetVersion() && identified.isSetVersion()) &&
				(Version.isFirstVersionNewer(derivedFrom.getVersion(), identified.getVersion()))) {
			return false;
		}
		return true;
	}

	static void validateWasDerivedFromVersion(SBOLDocument sbolDocument) {
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (topLevel.isSetWasDerivedFrom()) {
				if (!checkWasDerivedFromVersion(sbolDocument,topLevel,topLevel.getWasDerivedFrom())) {
					errors.add(new SBOLValidationException("sbol-10302", topLevel).getExceptionMessage());
				}
			}
		}
	}

	/**
	 * @param sbolDocument
	 * @param identified
	 * @param wasDerivedFrom
	 * @param visited
	 * @throws SBOLValidationException if any of the following SBOL validation rule was violated: 10209, 10304.
	 */
	protected static void checkWasDerivedFromCycle(SBOLDocument sbolDocument,
			Identified identified, URI wasDerivedFrom, Set<URI> visited) throws SBOLValidationException {
		visited.add(identified.getIdentity());
		TopLevel tl = sbolDocument.getTopLevel(wasDerivedFrom);
		if (tl!=null) {
			if (visited.contains(tl.getIdentity())) {
				throw new SBOLValidationException("sbol-10303",identified);
			}
			if (tl.isSetWasDerivedFrom()) {
				try {
					checkWasDerivedFromCycle(sbolDocument,tl,tl.getWasDerivedFrom(),visited);
				} catch (SBOLValidationException e) {
					throw new SBOLValidationException("sbol-10304",identified);
				}
			} else {
				return;
			}
		}
		visited.remove(identified.getIdentity());
		return;
	}

	/**
	 * Validate if there are circular references in given {@code sbolDocument}.
	 *
	 * @param sbolDocument The SBOLDocument object to be validated for circular references
	 */
	static void validateCircularReferences(SBOLDocument sbolDocument) {
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (topLevel.isSetWasDerivedFrom()) {
				try {
					checkWasDerivedFromCycle(sbolDocument,topLevel,topLevel.getWasDerivedFrom(), new HashSet<URI>());
				} catch (SBOLValidationException e) {
					errors.add(e.getMessage());
				}
			}
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			try {
				checkComponentDefinitionCycle(sbolDocument,componentDefinition,new HashSet<URI>());
			} catch (SBOLValidationException e) {
				errors.add(e.getMessage());
			}
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			try {
				checkModuleDefinitionCycle(sbolDocument,moduleDefinition,new HashSet<URI>());
			} catch (SBOLValidationException e) {
				errors.add(e.getMessage());
			}
		}
	}

	static void checkSequenceConstraint(ComponentDefinition componentDefinition,SequenceConstraint sequenceConstraint) throws SBOLValidationException {
		SequenceAnnotation saSubject = componentDefinition.getSequenceAnnotation(sequenceConstraint.getSubject());
		SequenceAnnotation saObject = componentDefinition.getSequenceAnnotation(sequenceConstraint.getObject());
		if (saSubject==null || saObject==null) return;
		if (sequenceConstraint.getRestriction().equals(RestrictionType.PRECEDES)) {
			if (saObject.compareTo(saSubject) < 0) {
				throw new SBOLValidationException("sbol-11409", sequenceConstraint);
			}
		} else if (sequenceConstraint.getRestriction().equals(RestrictionType.SAME_ORIENTATION_AS)) {
			for (Location locSubject : saSubject.getLocations()) {
				for (Location locObject : saObject.getLocations()) {
					if (!locSubject.getOrientation().equals(locObject.getOrientation())) {
						throw new SBOLValidationException("sbol-11410", sequenceConstraint);
					}
				}
			}
		} else if (sequenceConstraint.getRestriction().equals(RestrictionType.OPPOSITE_ORIENTATION_AS)) {
			for (Location locSubject : saSubject.getLocations()) {
				for (Location locObject : saObject.getLocations()) {
					if (locSubject.getOrientation().equals(locObject.getOrientation())) {
						throw new SBOLValidationException("sbol-11411", sequenceConstraint);
					}
				}
			}
		}
	}

	static void checkInteractionTypeParticipationRole(Interaction interaction,URI type,URI role) {
		if (type.equals(SystemsBiologyOntology.INHIBITION)) {
			if (!role.equals(SystemsBiologyOntology.INHIBITOR) && !role.equals(SystemsBiologyOntology.PROMOTER)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		} else if (type.equals(SystemsBiologyOntology.STIMULATION)) {
			if (!role.equals(SystemsBiologyOntology.STIMULATOR) && !role.equals(SystemsBiologyOntology.PROMOTER)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		} else if (type.equals(SystemsBiologyOntology.NON_COVALENT_BINDING)) {
			if (!role.equals(SystemsBiologyOntology.REACTANT) && !role.equals(SystemsBiologyOntology.PRODUCT)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		} else if (type.equals(SystemsBiologyOntology.DEGRADATION)) {
			if (!role.equals(SystemsBiologyOntology.REACTANT)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		} else if (type.equals(SystemsBiologyOntology.BIOCHEMICAL_REACTION)) {
			if (!role.equals(SystemsBiologyOntology.REACTANT) && !role.equals(SystemsBiologyOntology.PRODUCT) &&
					!role.equals(SystemsBiologyOntology.MODIFIER)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		} else if (type.equals(SystemsBiologyOntology.GENETIC_PRODUCTION)) {
			if (!role.equals(SystemsBiologyOntology.PROMOTER) && !role.equals(SystemsBiologyOntology.PRODUCT)) {
				errors.add(new SBOLValidationException("sbol-11907",interaction).getExceptionMessage());
			}
		}
	}

	static void validateOntologyUsage(SBOLDocument sbolDocument) {
		SequenceOntology so = new SequenceOntology();
		SystemsBiologyOntology sbo = new SystemsBiologyOntology();
		EDAMOntology edam = new EDAMOntology();
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!sequence.getEncoding().equals(Sequence.IUPAC_DNA) &&
					!sequence.getEncoding().equals(Sequence.IUPAC_RNA) &&
					!sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN) &&
					!sequence.getEncoding().equals(Sequence.SMILES)) {
				errors.add(new SBOLValidationException("sbol-10407", sequence).getExceptionMessage());

			}
		}
		for (ComponentDefinition compDef : sbolDocument.getComponentDefinitions()) {
			int numBioPAXtypes = 0;
			for (URI type : compDef.getTypes()) {
				if (type.equals(ComponentDefinition.DNA) ||
						type.equals(ComponentDefinition.RNA) ||
						type.equals(ComponentDefinition.PROTEIN) ||
						type.equals(ComponentDefinition.COMPLEX) ||
						type.equals(ComponentDefinition.SMALL_MOLECULE)) {
					numBioPAXtypes++;
				}
			}
			if (numBioPAXtypes == 0) {
				errors.add(new SBOLValidationException("sbol-10525", compDef).getExceptionMessage());
			} else if (numBioPAXtypes > 1){
				errors.add(new SBOLValidationException("sbol-10503", compDef).getExceptionMessage());
			}
			int numSO = 0;;
			for (URI role : compDef.getRoles()) {
				try {
					if (so.isDescendantOf(role, SequenceOntology.SEQUENCE_FEATURE)) {
						numSO++;
					}
				} catch (Exception e){
				}
			}
			if (compDef.getTypes().contains(ComponentDefinition.DNA) || compDef.getTypes().contains(ComponentDefinition.RNA)) {
				if (numSO!=1) {
					errors.add(new SBOLValidationException("sbol-10527", compDef).getExceptionMessage());
				}
			} else if (!compDef.getTypes().contains(ComponentDefinition.RNA)) {
				if (numSO!=0) {
					errors.add(new SBOLValidationException("sbol-10511", compDef).getExceptionMessage());
				}
			}
			for (SequenceConstraint sc : compDef.getSequenceConstraints()) {
				try {
					RestrictionType.convertToRestrictionType(sc.getRestrictionURI());
				}
				catch (Exception e) {
					errors.add(new SBOLValidationException("sbol-11412", sc).getExceptionMessage());
				}
			}
		}
		for (Model model : sbolDocument.getModels()) {
			try {
				if (!edam.isDescendantOf(model.getLanguage(), EDAMOntology.FORMAT)) {
					errors.add(new SBOLValidationException("sbol-11507", model).getExceptionMessage());
				}
			}
			catch (Exception e) {
				errors.add(new SBOLValidationException("sbol-11507", model).getExceptionMessage());
			}
			try {
				if (!sbo.isDescendantOf(model.getFramework(), SystemsBiologyOntology.MODELING_FRAMEWORK)) {
					errors.add(new SBOLValidationException("sbol-11511", model).getExceptionMessage());
				}
			}
			catch (Exception e) {
				errors.add(new SBOLValidationException("sbol-11511", model).getExceptionMessage());
			}
		}
		for (ModuleDefinition modDef : sbolDocument.getModuleDefinitions()) {
			for (Interaction interaction : modDef.getInteractions()) {
				int numSBOtype = 0;
				URI SBOtype = null;
				for (URI type : interaction.getTypes()) {
					try {
						if (sbo.isDescendantOf(type, SystemsBiologyOntology.OCCURRING_ENTITY_REPRESENTATION)) {
							numSBOtype++;
							SBOtype = type;
						}
					}
					catch (Exception e) {
					}
				}
				if (numSBOtype != 1) {
					errors.add(new SBOLValidationException("sbol-11905").getExceptionMessage());
				}
				for (Participation participation : interaction.getParticipations()) {
					int numSBOrole = 0;
					URI SBOrole = null;
					for (URI role : participation.getRoles()) {
						try {
							if (sbo.isDescendantOf(role, SystemsBiologyOntology.PARTICIPANT_ROLE)) {
								numSBOrole++;
								SBOrole = role;
							}
						}
						catch (Exception e) {
						}
					}
					if (numSBOrole != 1) {
						errors.add(new SBOLValidationException("sbol-12007", participation).getExceptionMessage());
					} else {
						checkInteractionTypeParticipationRole(interaction,SBOtype,SBOrole);
					}
				}
			}
		}
	}

	static void validateComponentDefinitionSequences(SBOLDocument sbolDocument) {
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (componentDefinition.getSequences().size() < 1) continue;
			boolean foundNucleic = false;
			boolean foundProtein = false;
			boolean foundSmiles = false;
			int nucleicLength = -1;
			int proteinLength = -1;
			int smilesLength = -1;
			for (Sequence sequence : componentDefinition.getSequences()) {
				if (sequence.getEncoding().equals(Sequence.IUPAC_DNA) ||
						sequence.getEncoding().equals(Sequence.IUPAC_RNA)) {
					if (foundNucleic) {
						if (nucleicLength != sequence.getElements().length()) {
							errors.add(new SBOLValidationException("sbol-10518", componentDefinition).getExceptionMessage());
						}
					} else {
						foundNucleic = true;
						nucleicLength = sequence.getElements().length();
					}
					for (SequenceAnnotation sa : componentDefinition.getSequenceAnnotations()) {
						for (Location location : sa.getLocations()) {
							if (location instanceof Range) {
								Range range = (Range)location;
								if (range.getStart() <= 0 || range.getEnd() > nucleicLength) {
									errors.add(new SBOLValidationException("sbol-10523", componentDefinition).getExceptionMessage());
								}
							} else if (location instanceof Cut) {
								Cut cut = (Cut)location;
								if (cut.getAt() < 0 || cut.getAt() > nucleicLength) {
									errors.add(new SBOLValidationException("sbol-10523", componentDefinition).getExceptionMessage());
								}
							}
						}
					}
				} else if (sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN)) {
					if (foundProtein) {
						if (proteinLength != sequence.getElements().length()) {
							errors.add(new SBOLValidationException("sbol-10518", componentDefinition).getExceptionMessage());
						}
					} else {
						foundProtein = true;
						proteinLength = sequence.getElements().length();
					}
				} else if (sequence.getEncoding().equals(Sequence.SMILES)) {
					if (foundSmiles) {
						if (smilesLength != sequence.getElements().length()) {
							errors.add(new SBOLValidationException("sbol-10518", componentDefinition).getExceptionMessage());
						}
					} else {
						foundSmiles = true;
						smilesLength = sequence.getElements().length();
					}
				}
			}
			if (componentDefinition.getTypes().contains(ComponentDefinition.DNA) && !foundNucleic) {
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.RNA) && !foundNucleic) {
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.PROTEIN) && !foundProtein) {
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.SMALL_MOLECULE) && !foundSmiles) {
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			}
			if (foundNucleic) {
				if (componentDefinition.getSequenceAnnotations().size()>0) {
					String impliedElements = componentDefinition.getImpliedNucleicAcidSequence();
					Sequence dnaSequence = componentDefinition.getSequenceByEncoding(Sequence.IUPAC_DNA);
					if (!includesSequence(dnaSequence.getElements(),impliedElements)) {
						errors.add(new SBOLValidationException("sbol-10520", componentDefinition).getExceptionMessage());
					}
				}
			}
			// Cannot check this one separately, since it either violates 10516 also OR it violates
			// best practices and does not use encodings from Table 1 or types from Table 2.
			/*
			if ((!componentDefinition.getTypes().contains(ComponentDefinition.DNA) &&
					!componentDefinition.getTypes().contains(ComponentDefinition.RNA))
					&& foundNucleic) {
				errors.add(new SBOLValidationException("sbol-10517", componentDefinition).getExceptionMessage());
			} else if (!componentDefinition.getTypes().contains(ComponentDefinition.PROTEIN) && foundProtein) {
				errors.add(new SBOLValidationException("sbol-10517", componentDefinition).getExceptionMessage());
			} else if (!componentDefinition.getTypes().contains(ComponentDefinition.SMALL_MOLECULE) && foundSmiles) {
				errors.add(new SBOLValidationException("sbol-10517", componentDefinition).getExceptionMessage());
			}
			*/
		}
	}
	
	static boolean includesSequence(String specificSequence,String generalSequence) {
		//if (specificSequence.length()!=generalSequence.length()) return false;
		specificSequence = specificSequence.toLowerCase();
		generalSequence = generalSequence.toLowerCase();
		for (int i = 0; i < specificSequence.length(); i++) {
			switch (generalSequence.charAt(i)) {
			case 'a': 
			case 'c': 
			case 'g':
			case 't':
			case 'u':
				if (specificSequence.charAt(i)!=generalSequence.charAt(i)) {
					return false; 
				}
				break;
			case '.':
				if (specificSequence.charAt(i)!='.' && specificSequence.charAt(i)!='-') {
					return false;
				}
				break;
			case '-':
				if (specificSequence.charAt(i)!='.' && specificSequence.charAt(i)!='-') {
					return false;
				}
				break;
			case 'r':
				if (specificSequence.charAt(i)!='r' && specificSequence.charAt(i)!='a' &&
					specificSequence.charAt(i)!='g') {
					return false;
				}
				break;
			case 'y':
				if (specificSequence.charAt(i)!='y' && specificSequence.charAt(i)!='c' &&
					specificSequence.charAt(i)!='t') {
					return false;
				}
				break;
			case 's':
				if (specificSequence.charAt(i)!='s' && specificSequence.charAt(i)!='c' &&
					specificSequence.charAt(i)!='g') {
					return false;
				}
				break;
			case 'w':
				if (specificSequence.charAt(i)!='w' && specificSequence.charAt(i)!='a' &&
					specificSequence.charAt(i)!='t') {
					return false;
				}
				break;
			case 'k':
				if (specificSequence.charAt(i)!='k' && specificSequence.charAt(i)!='g' &&
					specificSequence.charAt(i)!='t') {
					return false;
				}
				break;
			case 'm':
				if (specificSequence.charAt(i)!='m' && specificSequence.charAt(i)!='a' &&
					specificSequence.charAt(i)!='c') {
					return false;
				}
				break;
			case 'b':
				if (specificSequence.charAt(i)!='k' && specificSequence.charAt(i)!='g' &&
					specificSequence.charAt(i)!='t' && specificSequence.charAt(i)!='c') {
					return false;
				}
				break;
			case 'd':
				if (specificSequence.charAt(i)!='d' && specificSequence.charAt(i)!='g' &&
					specificSequence.charAt(i)!='t' && specificSequence.charAt(i)!='a') {
					return false;
				}
				break;
			case 'h':
				if (specificSequence.charAt(i)!='h' && specificSequence.charAt(i)!='c' &&
					specificSequence.charAt(i)!='t' && specificSequence.charAt(i)!='a') {
					return false;
				}
				break;
			case 'v':
				if (specificSequence.charAt(i)!='v' && specificSequence.charAt(i)!='g' &&
					specificSequence.charAt(i)!='c' && specificSequence.charAt(i)!='a') {
					return false;
				}
				break;
			case 'n':
				break;
			default:
				return false;
			}
		}
		return true;
	}

	static void validateSequenceConstraints(SBOLDocument sbolDocument) {
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
				try {
					checkSequenceConstraint(componentDefinition,sequenceConstraint);
				}
				catch (SBOLValidationException e) {
					errors.add(e.getExceptionMessage());
				}
			}
		}
	}

	static void validateSequenceAnnotations(SBOLDocument sbolDocument) {
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			for (SequenceAnnotation sequenceAnnotation : componentDefinition.getSequenceAnnotations()) {
				Object[] locations = sequenceAnnotation.getLocations().toArray();
				for (int i = 0; i < locations.length-1; i++) {
					for (int j = i + 1; j < locations.length; j++) {
						Location location1 = (Location) locations[i];
						Location location2 = (Location) locations[j];
						if (location1.getIdentity().equals(location2.getIdentity())) continue;
						if (location1 instanceof Range && location2 instanceof Range) {
							if (((((Range)location1).getStart() >= ((Range)location2).getStart()) &&
									(((Range)location1).getStart() <= ((Range)location2).getEnd()))
									||
									((((Range)location2).getStart() >= ((Range)location1).getStart()) &&
											(((Range)location2).getStart() <= ((Range)location1).getEnd()))) {
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location1 instanceof Range && location2 instanceof Cut) {
							if ((((Range)location1).getEnd() > ((Cut)location2).getAt()) &&
									(((Cut)location2).getAt() >= ((Range)location1).getStart())) {
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location2 instanceof Range && location1 instanceof Cut) {
							if ((((Range)location2).getEnd() > ((Cut)location1).getAt()) &&
									(((Cut)location1).getAt() >= ((Range)location2).getStart())) {
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location2 instanceof Cut && location1 instanceof Cut) {
							if (((Cut)location2).getAt() == ((Cut)location1).getAt()) {
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						}
					}
				}
			}
		}
	}

	private static final String IUPAC_DNA_PATTERN = "([ACGTURYSWKMBDHVN\\-\\.]*)";
	private static final Pattern iupacDNAparser = Pattern.compile(IUPAC_DNA_PATTERN);
	private static final String IUPAC_PROTEIN_PATTERN = "([ABCDEFGHIKLMNPQRSTVWXYZ]*)";
	private static final Pattern iupacProteinParser = Pattern.compile(IUPAC_PROTEIN_PATTERN);
	private static OpenSmilesParser openSmilesParser = new OpenSmilesParser();

	static boolean checkSequenceEncoding(Sequence sequence) {
		if (sequence.getEncoding().equals(Sequence.IUPAC_DNA) ||
				(sequence.getEncoding().equals(Sequence.IUPAC_RNA))) {
			Matcher m = iupacDNAparser.matcher(sequence.getElements().toUpperCase());
			return m.matches();
		} else if (sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN)) {
			Matcher m = iupacProteinParser.matcher(sequence.getElements().toUpperCase());
			return m.matches();
		} else if (sequence.getEncoding().equals(Sequence.SMILES)) {
			return openSmilesParser.check(sequence.getElements());
		}
		return true;
	}

	static void validateSequenceEncodings(SBOLDocument sbolDocument) {
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!checkSequenceEncoding(sequence)) {
				errors.add(new SBOLValidationException("sbol-10405", sequence).getExceptionMessage());
			}
		}
	}

	static void validatePersistentIdentityUniqueness(SBOLDocument sbolDocument) {
		HashMap<URI, Identified> elements = new HashMap<>();
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (!topLevel.isSetPersistentIdentity()) continue;
			if (elements.get(topLevel.getPersistentIdentity())!=null) {
				Identified identified = elements.get(topLevel.getPersistentIdentity());
				if (!topLevel.getClass().equals(identified.getClass())) {
					errors.add(new SBOLValidationException("sbol-10220", topLevel).getExceptionMessage());
				}
			}
			elements.put(topLevel.getPersistentIdentity(),topLevel);
			if (topLevel instanceof ComponentDefinition) {
				for (Component c : ((ComponentDefinition) topLevel).getComponents()) {
					if (!c.isSetPersistentIdentity()) continue;
					if (elements.get(c.getPersistentIdentity())!=null) {
						Identified identified = elements.get(c.getPersistentIdentity());
						if (!c.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", c).getExceptionMessage());
						}
					}
					elements.put(c.getPersistentIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (!m.isSetPersistentIdentity()) continue;
						if (elements.get(m.getPersistentIdentity())!=null) {
							Identified identified = elements.get(m.getPersistentIdentity());
							if (!m.getClass().equals(identified.getClass())) {
								errors.add(new SBOLValidationException("sbol-10220", m).getExceptionMessage());
							}
						}
						elements.put(m.getPersistentIdentity(),m);
					}
				}
				for (SequenceAnnotation sa : ((ComponentDefinition) topLevel).getSequenceAnnotations()) {
					if (!sa.isSetPersistentIdentity()) continue;
					if (elements.get(sa.getPersistentIdentity())!=null) {
						Identified identified = elements.get(sa.getPersistentIdentity());
						if (!sa.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", sa).getExceptionMessage());
						}
					}
					elements.put(sa.getPersistentIdentity(),sa);
					for (Location l : sa.getLocations()) {
						if (!l.isSetPersistentIdentity()) continue;
						if (elements.get(l.getPersistentIdentity())!=null) {
							Identified identified = elements.get(l.getPersistentIdentity());
							if (!l.getClass().equals(identified.getClass())) {
								errors.add(new SBOLValidationException("sbol-10220", l).getExceptionMessage());
							}
						}
						elements.put(l.getPersistentIdentity(),l);
					}
				}
				for (SequenceConstraint sc : ((ComponentDefinition) topLevel).getSequenceConstraints()) {
					if (!sc.isSetPersistentIdentity()) continue;
					if (elements.get(sc.getPersistentIdentity())!=null) {
						Identified identified = elements.get(sc.getPersistentIdentity());
						if (!sc.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", sc).getExceptionMessage());
						}
					}
					elements.put(sc.getPersistentIdentity(),sc);
				}
			}
			if (topLevel instanceof ModuleDefinition) {
				for (FunctionalComponent c : ((ModuleDefinition) topLevel).getFunctionalComponents()) {
					if (!c.isSetPersistentIdentity()) continue;
					if (elements.get(c.getPersistentIdentity())!=null) {
						Identified identified = elements.get(c.getPersistentIdentity());
						if (!c.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", c).getExceptionMessage());
						}
					}
					elements.put(c.getPersistentIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (!m.isSetPersistentIdentity()) continue;
						if (elements.get(m.getPersistentIdentity())!=null) {
							Identified identified = elements.get(m.getPersistentIdentity());
							if (!m.getClass().equals(identified.getClass())) {
								errors.add(new SBOLValidationException("sbol-10220", m).getExceptionMessage());
							}
						}
						elements.put(m.getPersistentIdentity(),m);
					}
				}
				for (Module mod : ((ModuleDefinition) topLevel).getModules()) {
					if (!mod.isSetPersistentIdentity()) continue;
					if (elements.get(mod.getPersistentIdentity())!=null) {
						Identified identified = elements.get(mod.getPersistentIdentity());
						if (!mod.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", mod).getExceptionMessage());
						}
					}
					elements.put(mod.getPersistentIdentity(),mod);
					for (MapsTo m : mod.getMapsTos()) {
						if (!m.isSetPersistentIdentity()) continue;
						if (elements.get(m.getPersistentIdentity())!=null) {
							Identified identified = elements.get(m.getPersistentIdentity());
							if (!m.getClass().equals(identified.getClass())) {
								errors.add(new SBOLValidationException("sbol-10220", m).getExceptionMessage());
							}
						}
						elements.put(m.getPersistentIdentity(),m);
					}
				}
				for (Interaction i : ((ModuleDefinition) topLevel).getInteractions()) {
					if (!i.isSetPersistentIdentity()) continue;
					if (elements.get(i.getPersistentIdentity())!=null) {
						Identified identified = elements.get(i.getPersistentIdentity());
						if (!i.getClass().equals(identified.getClass())) {
							errors.add(new SBOLValidationException("sbol-10220", i).getExceptionMessage());
						}
					}
					elements.put(i.getPersistentIdentity(),i);
					for (Participation p : i.getParticipations()) {
						if (!p.isSetPersistentIdentity()) continue;
						if (elements.get(p.getPersistentIdentity())!=null) {
							Identified identified = elements.get(p.getPersistentIdentity());
							if (!p.getClass().equals(identified.getClass())) {
								errors.add(new SBOLValidationException("sbol-10220", p).getExceptionMessage());
							}
						}
						elements.put(p.getPersistentIdentity(),p);
					}
				}
			}
		}
	}

	static void validateURIuniqueness(SBOLDocument sbolDocument) {
		HashMap<URI, Identified> elements = new HashMap<>();
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (elements.get(topLevel.getIdentity())!=null) {
				Identified identified = elements.get(topLevel.getIdentity());
				if (!topLevel.equals(identified)) {
					errors.add(new SBOLValidationException("sbol-10202", topLevel).getExceptionMessage());
				}
			}
			elements.put(topLevel.getIdentity(),topLevel);
			if (topLevel instanceof ComponentDefinition) {
				for (Component c : ((ComponentDefinition) topLevel).getComponents()) {
					if (elements.get(c.getIdentity())!=null) {
						Identified identified = elements.get(c.getIdentity());
						if (!c.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", c).getExceptionMessage());
						}
					}
					elements.put(c.getIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								errors.add(new SBOLValidationException("sbol-10202", m).getExceptionMessage());
							}
						}
						elements.put(m.getIdentity(),m);
					}
				}
				for (SequenceAnnotation sa : ((ComponentDefinition) topLevel).getSequenceAnnotations()) {
					if (elements.get(sa.getIdentity())!=null) {
						Identified identified = elements.get(sa.getIdentity());
						if (!sa.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", sa).getExceptionMessage());
						}
					}
					elements.put(sa.getIdentity(),sa);
					for (Location l : sa.getLocations()) {
						if (elements.get(l.getIdentity())!=null) {
							Identified identified = elements.get(l.getIdentity());
							if (!l.equals(identified)) {
								errors.add(new SBOLValidationException("sbol-10202", l).getExceptionMessage());
							}
						}
						elements.put(l.getIdentity(),l);
					}
				}
				for (SequenceConstraint sc : ((ComponentDefinition) topLevel).getSequenceConstraints()) {
					if (elements.get(sc.getIdentity())!=null) {
						Identified identified = elements.get(sc.getIdentity());
						if (!sc.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", sc).getExceptionMessage());
						}
					}
					elements.put(sc.getIdentity(),sc);
				}
			}
			if (topLevel instanceof ModuleDefinition) {
				for (FunctionalComponent c : ((ModuleDefinition) topLevel).getFunctionalComponents()) {
					if (elements.get(c.getIdentity())!=null) {
						Identified identified = elements.get(c.getIdentity());
						if (!c.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", c).getExceptionMessage());
						}
					}
					elements.put(c.getIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								errors.add(new SBOLValidationException("sbol-10202", m).getExceptionMessage());
							}
						}
						elements.put(m.getIdentity(),m);
					}
				}
				for (Module mod : ((ModuleDefinition) topLevel).getModules()) {
					if (elements.get(mod.getIdentity())!=null) {
						Identified identified = elements.get(mod.getIdentity());
						if (!mod.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", mod).getExceptionMessage());
						}
					}
					elements.put(mod.getIdentity(),mod);
					for (MapsTo m : mod.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								errors.add(new SBOLValidationException("sbol-10202", m).getExceptionMessage());
							}
						}
						elements.put(m.getIdentity(),m);
					}
				}
				for (Interaction i : ((ModuleDefinition) topLevel).getInteractions()) {
					if (elements.get(i.getIdentity())!=null) {
						Identified identified = elements.get(i.getIdentity());
						if (!i.equals(identified)) {
							errors.add(new SBOLValidationException("sbol-10202", i).getExceptionMessage());
						}
					}
					elements.put(i.getIdentity(),i);
					for (Participation p : i.getParticipations()) {
						if (elements.get(p.getIdentity())!=null) {
							Identified identified = elements.get(p.getIdentity());
							if (!p.equals(identified)) {
								errors.add(new SBOLValidationException("sbol-10202", p).getExceptionMessage());
							}
						}
						elements.put(p.getIdentity(),p);
					}
				}
			}
		}
	}

	/**
	 * Validates the given SBOL document. Errors encountered either throw exceptions or, if not fatal, are added to the list of errors
	 * that can be accessed using the {@link #getErrors()} method. Interpretations of the complete, compliant, and bestPractice parameters 
	 * are as follows:
	 * <ul>
	 * <li> complete: A {@code true} value means that ALL references in the given SBOLDocument object can dereference to objects 
	 * in the same document; {@code false} otherwise.
	 * <li> compliant: A {@code true} value means that ALL URIs in the given SBOLDocument object are compliant; {@code false} otherwise.
	 * <li> best practice: A {@code true} value means that validation rules with the RECOMMENDED condition in the SBOL specification are
	 * checked against the given SBOLDocuemnt object; {@code false} otherwise.
	 *  </ul><p>
	 * 
	 * @param sbolDocument the given {@code SBOLDocument} object
	 * @param complete the given {@code complete} flag 
	 * @param compliant the given {@code compliant} flag
	 * @param bestPractice the given {@code bestPractice} flag
	 */
	public static void validateSBOL(SBOLDocument sbolDocument, boolean complete, boolean compliant,
			boolean bestPractice) {
		clearErrors();
		//validateSequenceEncodings(sbolDocument);
		//validateSequenceConstraints(sbolDocument);
		validateWasDerivedFromVersion(sbolDocument);
		validateCircularReferences(sbolDocument);
		validateURIuniqueness(sbolDocument);
		validatePersistentIdentityUniqueness(sbolDocument);
		validateMapsTos(sbolDocument);
		if (compliant) validateCompliance(sbolDocument);
		if (complete) validateCompleteness(sbolDocument);
		if (bestPractice) {
			validateOntologyUsage(sbolDocument);
			validateSequenceAnnotations(sbolDocument);
			validateComponentDefinitionSequences(sbolDocument);
		}
	}

	/**
	 * Compare the given two SBOLDocument objects and outputs the "standard" error output stream (System.err).  
	 * @param file1 the file name associated with {@code doc1}
	 * @param doc1 the first SBOLDocument object
	 * @param file2 the file name associated with {@code doc2}
	 * @param doc2 the second SBOLDocument object
	 */
	public static void compareDocuments(String file1, SBOLDocument doc1, String file2, SBOLDocument doc2) {
		/*if (!doc1.getNamespaces().equals(doc2.getNamespaces())) {
			System.err.println("Namespaces do not match");
			System.err.println(doc1.getNamespaces().toString());
			System.err.println(doc2.getNamespaces().toString());
		}*/
		for (QName namespace : doc1.getNamespaces()) {
			if (doc2.getNamespaces().contains(namespace)) continue;
			System.err.println("Namesapce " + namespace.toString() + " not found in " + file2);
		}
		for (QName namespace : doc2.getNamespaces()) {
			if (doc1.getNamespaces().contains(namespace)) continue;
			System.err.println("Namesapce " + namespace.toString() + " not found in " + file1);
		}
		for (GenericTopLevel genericTopLevel1 : doc1.getGenericTopLevels()) {
			GenericTopLevel genericTopLevel2 = doc2.getGenericTopLevel(genericTopLevel1.getIdentity());
			if (genericTopLevel2==null) {
				System.err.println("Collection " + genericTopLevel1.getIdentity() + " not found in " + file2);
			} else if (!genericTopLevel1.equals(genericTopLevel2)) {
				System.err.println("Collection " + genericTopLevel1.getIdentity() + " differ.");
			}
		}
		for (GenericTopLevel genericTopLevel2 : doc2.getGenericTopLevels()) {
			GenericTopLevel genericTopLevel1 = doc1.getGenericTopLevel(genericTopLevel2.getIdentity());
			if (genericTopLevel1==null) {
				System.err.println("Collection " + genericTopLevel2.getIdentity() + " not found in " + file1);
			}
		}
		/*
		if (!doc1.getCollections().equals(doc2.getCollections())) {
			System.err.println("Collections do not match");
			System.out.println(doc1.getCollections().toString());
			System.out.println(doc2.getCollections().toString());
		}
		 */
		for (Collection collection1 : doc1.getCollections()) {
			Collection collection2 = doc2.getCollection(collection1.getIdentity());
			if (collection2==null) {
				System.err.println("Collection " + collection1.getIdentity() + " not found in " + file2);
			} else if (!collection1.equals(collection2)) {
				System.err.println("Collection " + collection1.getIdentity() + " differ.");
			}
		}
		for (Collection collection2 : doc2.getCollections()) {
			Collection collection1 = doc1.getCollection(collection2.getIdentity());
			if (collection1==null) {
				System.err.println("Collection " + collection2.getIdentity() + " not found in " + file1);
			}
		}
		/*
		if (!doc1.getSequences().equals(doc2.getSequences())) {
			System.err.println("Sequences do not match");
		}
		 */
		for (Sequence sequence1 : doc1.getSequences()) {
			Sequence sequence2 = doc2.getSequence(sequence1.getIdentity());
			if (sequence2==null) {
				System.err.println("Sequence " + sequence1.getIdentity() + " not found in " + file2);
			} else if (!sequence1.equals(sequence2)) {
				System.err.println("Sequence " + sequence1.getIdentity() + " differ.");
			}
		}
		for (Sequence sequence2 : doc2.getSequences()) {
			Sequence sequence1 = doc1.getSequence(sequence2.getIdentity());
			if (sequence1==null) {
				System.err.println("Sequence " + sequence2.getIdentity() + " not found in " + file1);
			}
		}
		/*
		if (!doc1.getComponentDefinitions().equals(doc2.getComponentDefinitions())) {
			System.err.println("ComponentDefinitions do not match");
		}
		 */
		for (ComponentDefinition componentDefinition1 : doc1.getComponentDefinitions()) {
			ComponentDefinition componentDefinition2 = doc2.getComponentDefinition(componentDefinition1.getIdentity());
			if (componentDefinition2==null) {
				System.err.println("ComponentDefinition " + componentDefinition1.getIdentity() + " not found in " + file2);
			} else if (!componentDefinition1.equals(componentDefinition2)) {
				System.err.println("ComponentDefinition " + componentDefinition1.getIdentity() + " differ.");
				//System.err.println(componentDefinition1.toString());
				//System.err.println(componentDefinition2.toString());
			}
		}
		for (ComponentDefinition componentDefinition2 : doc2.getComponentDefinitions()) {
			ComponentDefinition componentDefinition1 = doc1.getComponentDefinition(componentDefinition2.getIdentity());
			if (componentDefinition1==null) {
				System.err.println("ComponentDefinition " + componentDefinition2.getIdentity() + " not found in " + file1);
			}
		}
		for (ModuleDefinition moduleDefinition1 : doc1.getModuleDefinitions()) {
			ModuleDefinition moduleDefinition2 = doc2.getModuleDefinition(moduleDefinition1.getIdentity());
			if (moduleDefinition2==null) {
				System.err.println("ModuleDefinition " + moduleDefinition1.getIdentity() + " not found in " + file2);
			} else if (!moduleDefinition1.equals(moduleDefinition2)) {
				System.err.println("ModuleDefinition " + moduleDefinition1.getIdentity() + " differ.");
			}
		}
		for (ModuleDefinition moduleDefinition2 : doc2.getModuleDefinitions()) {
			ModuleDefinition moduleDefinition1 = doc1.getModuleDefinition(moduleDefinition2.getIdentity());
			if (moduleDefinition1==null) {
				System.err.println("ModuleDefinition " + moduleDefinition2.getIdentity() + " not found in " + file1);
			}
		}
		for (Model model1 : doc1.getModels()) {
			Model model2 = doc2.getModel(model1.getIdentity());
			if (model2==null) {
				System.err.println("Model " + model1.getIdentity() + " not found in " + file2);
			} else if (!model1.equals(model2)) {
				System.err.println("Model " + model1.getIdentity() + " differ.");
			}
		}
		for (Model model2 : doc2.getModels()) {
			Model model1 = doc1.getModel(model2.getIdentity());
			if (model1==null) {
				System.err.println("Model " + model2.getIdentity() + " not found in " + file1);
			}
		}
	}
	

	private static void usage() {
		System.err.println("libSBOLj version " + libSBOLj_Version);
		System.err.println("Description: validates the contents of an SBOL " + SBOLVersion + " document, can compare two documents,\n"
				+ "and can convert to/from SBOL 1.1, GenBank, and FASTA formats.");
		System.err.println();
		System.err.println("Usage:");
		System.err.println("\tjava --jar libSBOLj.jar [options] <inputFile> [-o <outputFile> -e <compareFile>]");
		System.err.println();
		System.err.println("Options:");
		System.err.println("\t-l  <language> specfies language (SBOL1/SBOL2/GenBank/FASTA) for output (default=SBOL2)");
		System.err.println("\t-s  <topLevelURI> select only this object and those it references");
		System.err.println("\t-p  <URIprefix> used for converted objects");
		System.err.println("\t-v  <version> used for converted objects");
		System.err.println("\t-t  uses types in URIs");
		System.err.println("\t-n  allow non-compliant URIs");
		System.err.println("\t-i  allow SBOL document to be incomplete");
		System.err.println("\t-b  check best practices");
		System.err.println("\t-f  fail on first error");
		System.err.println("\t-d  display detailed error trace");
		System.exit(1);
	}


	/**
	 * Command line method for reading an input file and producing an output file.
	 * <p>
	 * By default, validations on compliance and completeness are performed, and types
	 * for top-level objects are not used in URIs.
	 * <p>
	 * Options:
	 * <p>
	 * "-o" specifies an output filename
	 * <p>
	 * "-e" specifies a file to compare if equal to
	 * <p>
	 * "-l" indicates the language for output (default=SBOL2, other options SBOL1, GenBank, FASTA)
	 * <p>
	 * "-s" select only this topLevel object and those it references
	 * <p>
	 * "-p" specifies the default URI prefix for converted objects
	 * <p>
	 * "-v" specifies version to use for converted objects
	 * <p>
	 * "-t" uses types in URIs
	 * <p>
	 * "-n" allow non-compliant URIs
	 * <p>
	 * "-i" allow SBOL document to be incomplete
	 * <p>
	 * "-b" check best practices
	 * <p>
	 * "-f" fail on first error
	 * <p>
	 * "-d" display detailed error trace
	 *
	 * @param args arguments supplied at command line
	 */
	public static void main(String[] args) {
		String fileName = "";
		String outputFile = "";
		String compareFile = "";
		String topLevelURIStr = "";
		String URIPrefix = "";
		String version = "";
		boolean complete = true;
		boolean compliant = true;
		boolean typesInURI = false;
		boolean bestPractice = false;
		boolean keepGoing = true;
		boolean showDetail = false;
		boolean genBankOut = false;
		boolean fastaOut = false;
		boolean sbolV1out = false;
		int i = 0;
		while (i < args.length) {
			if (args[i].equals("-i")) {
				complete = false;
			} else if (args[i].equals("-t")) {
				typesInURI = true;
			} else if (args[i].equals("-b")) {
				bestPractice = true;
			} else if (args[i].equals("-n")) {
				compliant = false;
			} else if (args[i].equals("-f")) {
				keepGoing = false;
			} else if (args[i].equals("-d")) {
				showDetail = true;
			} else if (args[i].equals("-s")) { 	
				if (i+1 >= args.length) {
					usage();
				}
				topLevelURIStr = args[i+1];
				i++;
			} else if (args[i].equals("-l")) {
				if (i+1 >= args.length) {
					usage();
				}
				if (args[i+1].equals("SBOL1")) {
					sbolV1out = true;
				} else if (args[i+1].equals("GenBank")) {
					genBankOut = true;
				} else if (args[i+1].equals("FASTA")) {
					fastaOut = true;
				} else if (args[i+1].equals("SBOL2")) {
				} else {
					usage();
				}
				i++;
			} else if (args[i].equals("-o")) {
				if (i+1 >= args.length) {
					usage();
				}
				outputFile = args[i+1];
				i++;
			} else if (args[i].equals("-e")) {
				if (i+1 >= args.length) {
					usage();
				}
				compareFile = args[i+1];
				i++;
			} else if (args[i].equals("-p")) {
				if (i+1 >= args.length) {
					usage();
				}
				URIPrefix = args[i+1];
				i++;
			} else if (args[i].equals("-v")) {
				if (i+1 >= args.length) {
					usage();
				}
				version = args[i+1];
				i++;
			} else if (fileName.equals("")) {
				fileName = args[i];
			} else {
				usage();
			}
			i++;
		}
		if (fileName.equals("")) usage();
		try {
			SBOLDocument doc = null;
			if (!URIPrefix.equals("")) {
				SBOLReader.setURIPrefix(URIPrefix);
			}
			if (!compliant) {
				SBOLReader.setCompliant(false);
			}
			SBOLReader.setTypesInURI(typesInURI);
			SBOLReader.setVersion(version);
			SBOLReader.setKeepGoing(keepGoing);
			SBOLWriter.setKeepGoing(keepGoing);
			if (FASTA.isFastaFile(fileName)) {
				System.err.println("Converting FASTA to SBOL Version 2");
			} else if (GenBank.isGenBankFile(fileName)) {
				System.err.println("Converting GenBank to SBOL Version 2");
			} else if (SBOLReader.getSBOLVersion(fileName).equals(SBOLReader.SBOLVERSION1)) {
				System.err.println("Converting SBOL Version 1 to SBOL Version 2");
			}
			doc = SBOLReader.read(fileName);
			doc.setTypesInURIs(typesInURI);
			if (!compareFile.equals("")) {
				SBOLDocument doc2 = SBOLReader.read(compareFile);
				File f = new File(fileName);
				String fileNameStr = f.getName();
				f = new File(compareFile);
				String compareFileStr = f.getName();
				compareDocuments(fileNameStr, doc, compareFileStr, doc2);
			}
			validateSBOL(doc, complete, compliant, bestPractice);
			if (getNumErrors()==0 && SBOLReader.getNumErrors()==0) {
				if (!topLevelURIStr.equals("")) {
					TopLevel topLevel = doc.getTopLevel(URI.create(topLevelURIStr));
					if (topLevel==null) {
						System.err.println("TopLevel " + topLevelURIStr + " not found.");
						return;
					}
					doc = doc.createRecursiveCopy(topLevel);
				}
				if (genBankOut) {
					if (outputFile.equals("")) {
						SBOLWriter.write(doc, (System.out), SBOLDocument.GENBANK);
					} else {
						System.out.println("Validation successful, no errors.");
						SBOLWriter.write(doc, outputFile, SBOLDocument.GENBANK);
					}
				} else if (sbolV1out) {
					if (outputFile.equals("")) {
						SBOLWriter.write(doc, (System.out), SBOLDocument.RDFV1);
					} else {
						System.out.println("Validation successful, no errors.");
						SBOLWriter.write(doc, outputFile, SBOLDocument.RDFV1);
					}
					if (SBOLWriter.getNumErrors()!=0) {
						for (String error : SBOLWriter.getErrors()) {
							System.err.println(error);
						}
					}
				} else if (fastaOut) {
					if (outputFile.equals("")) {
						SBOLWriter.write(doc, (System.out), SBOLDocument.FASTAformat);
					} else {
						System.out.println("Validation successful, no errors.");
						SBOLWriter.write(doc, outputFile, SBOLDocument.FASTAformat);
					}
				} else {
					if (outputFile.equals("")) {
						SBOLWriter.write(doc, (System.out));
					} else {
						System.out.println("Validation successful, no errors.");
						SBOLWriter.write(doc, outputFile);
					}
				}
			} else {
				if (getNumErrors()!=0) {
					for (String error : getErrors()) {
						System.err.println(error);
					}
				}
				if (SBOLReader.getNumErrors()!=0) {
					for (String error : SBOLReader.getErrors()) {
						System.err.println(error);
					}
				}
				System.err.println("Validation failed.\n");
			}
		}
		catch (Exception e) {
			if (showDetail) {
				e.printStackTrace();
			}
			if (e.getMessage()!=null) {
				System.err.println(e.getMessage()+"\nValidation failed.");
			} else {
				e.printStackTrace();
				System.err.println("\nValidation failed.");
			}
		}
		catch (Throwable e) {
			if (showDetail) {
				e.printStackTrace();
			}
			if (e.getMessage()!=null) {
				System.err.println(e.getMessage()+"\nValidation failed.");
			} else {
				e.printStackTrace();
				System.err.println("\nValidation failed.");
			}
		}
	}

}
