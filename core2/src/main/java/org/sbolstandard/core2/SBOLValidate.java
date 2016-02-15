package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.turingatemyhamster.opensmiles.OpenSmilesParser;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class SBOLValidate {
	
	/**
	 * the current SBOL version
	 */
	private static final String SBOLVersion = "2.0";
	private static List<String> errors = null;
	
	public static void clearErrors() {
		errors = new ArrayList<String>();
	}
	
	public static List<String> getErrors() {
		return errors;
	}
	
	public static int getNumErrors() {
		return errors.size();
	}
	
	/**
	 * Validate SBOL objects are compliant in the given {@code sbolDocument}.
	 * 
	 * @param sbolDocument
	 * @throws SBOLValidationException if any top-level objects or any of their children or grandchildren 
	 * in the given {@code sbolDocument} contain a non-compliant URI.
	 */
	static void validateCompliance(SBOLDocument sbolDocument) {
		for (Collection collection : sbolDocument.getCollections()) {
			if (!URIcompliance.isTopLevelURIcompliant(collection) || !collection.checkDescendantsURIcompliance()) {
				errors.add("Collection " + collection.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
			}
		}
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!URIcompliance.isTopLevelURIcompliant(sequence) || !sequence.checkDescendantsURIcompliance()) {
				errors.add("Sequence " + sequence.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
			}
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (!URIcompliance.isTopLevelURIcompliant(componentDefinition) || !componentDefinition.checkDescendantsURIcompliance()) {
				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
			}
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (!URIcompliance.isTopLevelURIcompliant(moduleDefinition) || !moduleDefinition.checkDescendantsURIcompliance()) 	
				errors.add("ModuleDefinition " + moduleDefinition.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
		}
		for (Model model : sbolDocument.getModels()) {
			if (!URIcompliance.isTopLevelURIcompliant(model) || !model.checkDescendantsURIcompliance()) 
				errors.add("Model " + model.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
		}
		for (GenericTopLevel genericTopLevel : sbolDocument.getGenericTopLevels()) {
			if (!URIcompliance.isTopLevelURIcompliant(genericTopLevel) || !genericTopLevel.checkDescendantsURIcompliance()) 
				errors.add("GenericTopLevel " + genericTopLevel.getIdentity() + " is not URI compliant.");
				// TODO: (Validation) missing rule: compliant URI identity
		}
	}
	
	protected static void checkCollectionCompleteness(SBOLDocument sbolDocument,Collection collection) {
		for (URI member : collection.getMemberURIs()) {
			if (sbolDocument.getTopLevel(member)==null) {
				errors.add("Collection " + collection.getIdentity() + " member " + member + " not found in document.");
				// TODO: (Validation) missing rule: compliant URI identity
			}
		}
	}

	protected static void checkComponentDefinitionCompleteness(SBOLDocument sbolDocument,ComponentDefinition componentDefinition) {
		for (URI sequenceURI : componentDefinition.getSequenceURIs()) {
			if (sbolDocument.getSequence(sequenceURI)==null) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + " sequence " + 
//						sequenceURI + " not found in document.");
				errors.add(new SBOLValidationException("sbol-10513", componentDefinition).getExceptionMessage());
				// TODO: (Validation) print URI for sequenceURI
			}
		}
		for (Component component : componentDefinition.getComponents()) {
			if (component.getDefinition()==null) {
//				errors.add("Component " + component.getIdentity() + " definition " + 
//						component.getDefinitionURI() + " not found in document.");
				errors.add(new SBOLValidationException("sbol-10604", component).getExceptionMessage());
				// TODO: (Validation) print URI for component.getDefinitionURI()
			}
		}
	}

	protected static void checkModuleDefinitionCompleteness(SBOLDocument sbolDocument,ModuleDefinition moduleDefinition) {
		for (URI modelURI : moduleDefinition.getModelURIs()) {
			if (sbolDocument.getModel(modelURI) == null) {
//				errors.add("ModuleDefinition " + moduleDefinition.getIdentity() + " model " + 
//						modelURI + " not found in document.");
				errors.add(new SBOLValidationException("sbol-10608", moduleDefinition).getExceptionMessage());
				// TODO: (Validation) print URI for modelURI
			}
		}
		for (FunctionalComponent functionalComponent : moduleDefinition.getFunctionalComponents()) {
			if (functionalComponent.getDefinition() == null) {
//				errors.add("FunctionalComponent " + functionalComponent.getIdentity() + " definition " + 
//						functionalComponent.getDefinitionURI() + " not found in document.");
				errors.add(new SBOLValidationException("sbol-10604", functionalComponent).getExceptionMessage());
				// TODO: (Validation) print URI for functionalComponent.getDefinitionURI()
			} 
		}
		for (Module module : moduleDefinition.getModules()) {
			if (module.getDefinition() == null) {
//				errors.add("Module " + module.getIdentity() + " definition " + 
//						module.getDefinitionURI() + " not found in document.");
				errors.add(new SBOLValidationException("sbol-11703", module).getExceptionMessage());
				// TODO: (Validation) print URI for module.getDefinitionURI()
			}
			for (MapsTo mapsTo : module.getMapsTos()) {
				if (mapsTo.getRemote()==null) {
//					errors.add("MapsTo " + mapsTo.getIdentity() + " remote functional component " + 
//							mapsTo.getRemoteURI() + " not found in module definition " + module.getDefinitionURI());
					errors.add(new SBOLValidationException("sbol-10809", mapsTo).getExceptionMessage());
					// TODO: (Validation) print URI for mapsTo.getRemoteURI and module.getDefinitionURI
					continue;
				}
				if (mapsTo.getRemote().getAccess().equals(AccessType.PRIVATE)) {
					//errors.add("MapsTo '" + mapsTo.getIdentity() + "' has a private remote Functional Component '" + mapsTo.getRemoteURI());
					errors.add(new SBOLValidationException("sbol-10807", mapsTo).getExceptionMessage());
				}
				if (mapsTo.getRefinement().equals(RefinementType.VERIFYIDENTICAL)) {
					if (!mapsTo.getLocal().getDefinitionURI().equals(mapsTo.getRemote().getDefinitionURI())) {
						//errors.add("MapsTo '" + mapsTo.getIdentity() + "' have non-identical local and remote Functional Component");
						errors.add(new SBOLValidationException("sbol-10811").getExceptionMessage());
					}
				}
			}
		}
	}

	/**
	 * Validate if all URI references to SBOL objects are in the same given {@code sbolDocument}.
	 * 
	 * @param sbolDocument
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
	
	protected static boolean checkComponentDefinitionCycle(SBOLDocument sbolDocument, 
			ComponentDefinition componentDefinition, Set<URI> visited) {
		if (componentDefinition==null) return false;
		visited.add(componentDefinition.getIdentity());
		for (Component component : componentDefinition.getComponents()) {
			ComponentDefinition cd = component.getDefinition();
			if (cd==null) continue;
			if (visited.contains(cd.getIdentity())) return true;
			if (checkComponentDefinitionCycle(sbolDocument,cd,visited)) return true;
		}
		visited.remove(componentDefinition.getIdentity());
		return false;
	}
	
	protected static boolean checkModuleDefinitionCycle(SBOLDocument sbolDocument, 
			ModuleDefinition moduleDefinition, Set<URI> visited) {
		if (moduleDefinition==null) return false;
		visited.add(moduleDefinition.getIdentity());
		for (Module module : moduleDefinition.getModules()) {
			ModuleDefinition md = module.getDefinition();
			if (md==null) continue;
			if (visited.contains(md.getIdentity())) return true;
			if (checkModuleDefinitionCycle(sbolDocument,md,visited)) return true;
		}
		visited.remove(moduleDefinition.getIdentity());
		return false;
	}
	
	protected static boolean checkWasDerivedFromCycle(SBOLDocument sbolDocument, 
			Identified identified, URI wasDerivedFrom, Set<URI> visited) {
		visited.add(identified.getIdentity());
		TopLevel tl = sbolDocument.getTopLevel(wasDerivedFrom);
		if (tl!=null) {
			if (visited.contains(tl.getIdentity())) return true;
			if (tl.isSetWasDerivedFrom()) {
				if (checkWasDerivedFromCycle(sbolDocument,tl,tl.getWasDerivedFrom(),visited)) return true;
			} else {
				return false;
			}
		}
		visited.remove(identified.getIdentity());
		return false;
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
					errors.add(topLevel.getIdentity() + " is derived from " + topLevel.getWasDerivedFrom() + 
							" but has older version.");
					// TODO: (Validation) which rule?
				}
			}
		}
	}

	/**
	 * Validate if there are circular references in given {@code sbolDocument}.
	 * 
	 * @param sbolDocument
	 */
	static void validateCircularReferences(SBOLDocument sbolDocument) {
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (topLevel.isSetWasDerivedFrom()) {
				if (checkWasDerivedFromCycle(sbolDocument,topLevel,topLevel.getWasDerivedFrom(), new HashSet<URI>())) {
					errors.add("Cycle found in '" + topLevel.getIdentity() + "' was derived from link.");
					// TODO: (Validation) which rule? Could be sbol-10209 or sbol-10210. 
					
				}
			}
		}
		for (ComponentDefinition componentDefinition : sbolDocument.getComponentDefinitions()) {
			if (checkComponentDefinitionCycle(sbolDocument,componentDefinition,new HashSet<URI>())) {
				errors.add("Cycle found in ComponentDefinition '" + componentDefinition.getIdentity() + "'");
				//TODO: (Validation) which rule? Could be sbol-10603 or sbol-10605.
			}
		}
		for (ModuleDefinition moduleDefinition : sbolDocument.getModuleDefinitions()) {
			if (checkModuleDefinitionCycle(sbolDocument,moduleDefinition,new HashSet<URI>())) {
				errors.add("Cycle found in ModuleDefinition '" + moduleDefinition.getIdentity() + "'");
				//TODO: (Validation) which rule? Could be sbol-11704 or sbol-11705.
			}
		}
	}
	
	static void validateOntologyUsage(SBOLDocument sbolDocument) {
		SequenceOntology so = new SequenceOntology();
		SystemsBiologyOntology sbo = new SystemsBiologyOntology();
		for (Sequence sequence : sbolDocument.getSequences()) {
			if (!sequence.getEncoding().equals(Sequence.IUPAC_DNA) &&
				!sequence.getEncoding().equals(Sequence.IUPAC_RNA) &&
				!sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN) &&
				!sequence.getEncoding().equals(Sequence.SMILES)) {
				//errors.add("Sequence " + sequence.getIdentity() + " has unrecoginized encoding (see Table 1): " + sequence.getEncoding());
				errors.add(new SBOLValidationException("sbol-10406", sequence).getExceptionMessage());

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
				//errors.add("ComponentDefinition " + compDef.getIdentity() + " does not have a recognized BioPAX type (see Table 2).");
				errors.add(new SBOLValidationException("sbol-10505", compDef).getExceptionMessage());
			} else if (numBioPAXtypes > 1){
				//errors.add("ComponentDefinition " + compDef.getIdentity() + " has conflicting BioPAX types (see Table 2).");
				errors.add(new SBOLValidationException("sbol-10503", compDef).getExceptionMessage());
			}
			if (compDef.getTypes().contains(ComponentDefinition.DNA)) {
				boolean foundSO = false;
				for (URI role : compDef.getRoles()) {
					try { 
						so.getName(role);
						foundSO = true;
						break;
					} catch (Exception e){
					}
				}
				if (!foundSO) {
					//errors.add("DNA ComponentDefinition " + compDef.getIdentity() + " does not have a recognized SO role.");
					errors.add(new SBOLValidationException("sbol-10510", compDef).getExceptionMessage());
				}
			}
			for (SequenceConstraint sc : compDef.getSequenceConstraints()) {
				try {
					sc.getRestriction();
				}
				catch (Exception e) {
					//errors.add("SequenceConstraint " + sc.getIdentity() + " does not have a recognized restriction type (Table 7): " + sc.getRestrictionURI());
					errors.add(new SBOLValidationException("sbol-11412", sc).getExceptionMessage());
				}
			}
		}
		for (Model model : sbolDocument.getModels()) {
			if (!model.getLanguage().equals(Model.SBML) &&
				!model.getLanguage().equals(Model.CELLML) &&
				!model.getLanguage().equals(Model.BIOPAX)) {
				//errors.add("Model " + model.getIdentity() + " has unrecoginized language (see Table 8): " + model.getLanguage());
				errors.add(new SBOLValidationException("sbol-11506", model).getExceptionMessage());
			}
			try {
				if (!sbo.isDescendantOf(model.getFramework(), SystemsBiologyOntology.MODELING_FRAMEWORK)) {
					//errors.add("Model " + model.getIdentity() + " does not have a recoginized SBO modeling framework: " + model.getFramework());
					errors.add(new SBOLValidationException("sbol-11511", model).getExceptionMessage());
					// TODO: (Validation) print model.getFramework.
				}
			}
			catch (Exception e) {
				//errors.add("Model " + model.getIdentity() + " does not have a recoginized SBO modeling framework: " + model.getFramework());
				errors.add(new SBOLValidationException("sbol-11510", model).getExceptionMessage());
				// TODO: (Validation) print model.getFramework.
			}
		}
		for (ModuleDefinition modDef : sbolDocument.getModuleDefinitions()) {
			for (Interaction interaction : modDef.getInteractions()) {
				int numSBOtype = 0;
				for (URI type : interaction.getTypes()) {
					try {
						if (sbo.isDescendantOf(type, SystemsBiologyOntology.OCCURRING_ENTITY_REPRESENTATION)) {
							numSBOtype++;
						}
					}
					catch (Exception e) {
					}
				}
				if (numSBOtype == 0) {
//					errors.add("Interaction " + interaction.getIdentity() + 
//							" has no type from occurring entity branch of the SBO.");
					errors.add(new SBOLValidationException("sbol-11905").getExceptionMessage());
				} else if (numSBOtype > 1) {
//					errors.add("Interaction " + interaction.getIdentity() + 
//							" has more than one type from occurring entity branch of the SBO.");
					errors.add(new SBOLValidationException("sbol-11904", interaction).getExceptionMessage());
				}
				for (Participation participation : interaction.getParticipations()) {
					int numSBOrole = 0;
					for (URI role : participation.getRoles()) {
						try {
							if (sbo.isDescendantOf(role, SystemsBiologyOntology.PARTICIPANT_ROLE)) {
								numSBOrole++;
							}
						}
						catch (Exception e) {
						}
					}
					if (numSBOrole == 0) {
//						errors.add("Participation " + participation.getIdentity() + 
//								" has no role from participant role branch of the SBO.");
						errors.add(new SBOLValidationException("sbol-12007", participation).getExceptionMessage());
					} else if (numSBOrole > 1) {
//						errors.add("Participation " + participation.getIdentity() + 
//								" has more than one role from participant role branch of the SBO.");
						errors.add(new SBOLValidationException("sbol-12006", participation).getExceptionMessage());
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
					if (foundProtein || foundSmiles) {
//						errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//								" has multiple sequences with conflicting encodings.");
						errors.add(new SBOLValidationException("sbol-10515", componentDefinition).getExceptionMessage()); 
					} 
					if (foundNucleic) {
						if (nucleicLength != sequence.getElements().length()) {
//							errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//									" has multiple sequences with IUPAC DNA/RNA encodings of different lengths.");
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
//									errors.add("SequenceAnnotation " + sa.getIdentity() + " has location outside of Sequence " 
//											+ sequence.getIdentity() + " scope.");
									errors.add(new SBOLValidationException("sbol-10523", sa, sequence).getExceptionMessage());
									// TODO: (Validation) right rule?
								}
							} else if (location instanceof Cut) {
								Cut cut = (Cut)location;
								if (cut.getAt() < 0 || cut.getAt() > nucleicLength) {
//									errors.add("SequenceAnnotation " + sa.getIdentity() + " has location outside of Sequence " 
//											+ sequence.getIdentity() + " scope.");
									errors.add(new SBOLValidationException("sbol-10523", sa, sequence).getExceptionMessage());
									// TODO: (Validation) right rule?
								}								
							}
						}
					}
				} else if (sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN)) {
					if (foundNucleic || foundSmiles) {
//						errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//								" has multiple sequences with conflicting encodings.");
						errors.add(new SBOLValidationException("sbol-10515", componentDefinition).getExceptionMessage());
					} 					
					if (foundProtein) {
						if (proteinLength != sequence.getElements().length()) {
//							errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//									" has multiple sequences with IUPAC Protein encodings of different lengths.");
							errors.add(new SBOLValidationException("sbol-10518", componentDefinition).getExceptionMessage());
						}
					} else {
						foundProtein = true;
						proteinLength = sequence.getElements().length();
					}
				} else if (sequence.getEncoding().equals(Sequence.SMILES)) {
					if (foundNucleic || foundProtein) {
//						errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//								" has multiple sequences with conflicting encodings.");
						errors.add(new SBOLValidationException("sbol-10515", componentDefinition).getExceptionMessage());
					} 	
					if (foundSmiles) {
						if (smilesLength != sequence.getElements().length()) {
//							errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//									" has multiple sequences with SMILES encodings of different lengths.");
							errors.add(new SBOLValidationException("sbol-10518", componentDefinition).getExceptionMessage());
						}
					} else {
						foundSmiles = true;
						smilesLength = sequence.getElements().length();
					}
				}
			}
			if (componentDefinition.getTypes().contains(ComponentDefinition.DNA) && !foundNucleic) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" is DNA type but no IUPAC DNA encoded sequence found.");
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.RNA) && !foundNucleic) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" is RNA type but no IUPAC RNA encoded sequence found.");
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.PROTEIN) && !foundProtein) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" is protein type but no IUPAC Protein encoded sequence found.");
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
			} else if (componentDefinition.getTypes().contains(ComponentDefinition.SMALL_MOLECULE) && !foundSmiles) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" is small molecule type but no SMILES encoded sequence found.");				
				errors.add(new SBOLValidationException("sbol-10516", componentDefinition).getExceptionMessage());
				// TODO: (Validation) print precise encoding type from Table 1.
			}
			if ((!componentDefinition.getTypes().contains(ComponentDefinition.DNA) &&
					!componentDefinition.getTypes().contains(ComponentDefinition.RNA))
					&& foundNucleic) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" has IUPAC DNA/RNA encoded sequence but is not DNA/RNA type.");
				errors.add(new SBOLValidationException("sbol-10514", componentDefinition).getExceptionMessage());
			} else if (!componentDefinition.getTypes().contains(ComponentDefinition.PROTEIN) && foundProtein) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" has IUPAC Protein encoded sequence but is not protein type.");
				errors.add(new SBOLValidationException("sbol-10514", componentDefinition).getExceptionMessage());
			} else if (!componentDefinition.getTypes().contains(ComponentDefinition.SMALL_MOLECULE) && foundSmiles) {
//				errors.add("ComponentDefinition " + componentDefinition.getIdentity() + 
//						" has SMILES encoded sequence but is not small molecule type.");
				errors.add(new SBOLValidationException("sbol-10514", componentDefinition).getExceptionMessage());
				// TODO: (Validation) print the problematic sequence.
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
								//errors.add("Locations " + location1.getIdentity() + " and " + location2.getIdentity() + " overlap.");
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location1 instanceof Range && location2 instanceof Cut) {
							if ((((Range)location1).getEnd() > ((Cut)location2).getAt()) &&
									(((Cut)location2).getAt() >= ((Range)location1).getStart())) {
								//errors.add("Locations " + location1.getIdentity() + " and " + location2.getIdentity() + " overlap.");
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location2 instanceof Range && location1 instanceof Cut) {
							if ((((Range)location2).getEnd() > ((Cut)location1).getAt()) &&
									(((Cut)location1).getAt() >= ((Range)location2).getStart())) {
								//errors.add("Locations " + location1.getIdentity() + " and " + location2.getIdentity() + " overlap.");
								errors.add(new SBOLValidationException("sbol-10903", location1, location2).getExceptionMessage());
							}
						} else if (location2 instanceof Cut && location1 instanceof Cut) {
							if (((Cut)location2).getAt() == ((Cut)location1).getAt()) {
								//errors.add("Locations " + location1.getIdentity() + " and " + location2.getIdentity() + " overlap.");
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
//				errors.add("Sequence '" + sequence.getIdentity() + "' that uses encoding " + sequence.getEncoding() + 
//						" does not have a valid sequence.");
				errors.add(new SBOLValidationException("sbol-10406", sequence).getExceptionMessage());
				// TODO: (Validation) print sequence.getEncoding()
			}
		}
	}
	
	static void validateURIuniqueness(SBOLDocument sbolDocument) {
		HashMap<URI, Identified> elements = new HashMap<>();
		for (TopLevel topLevel : sbolDocument.getTopLevels()) {
			if (elements.get(topLevel.getIdentity())!=null) {
				Identified identified = elements.get(topLevel.getIdentity());
				if (!topLevel.equals(identified)) {
					//errors.add("Multiple elements with identity " + topLevel.getIdentity());
					errors.add(new SBOLValidationException("sbol-10202", topLevel).getExceptionMessage());
				}
 			}
			elements.put(topLevel.getIdentity(),topLevel);
			if (topLevel instanceof ComponentDefinition) {
				for (Component c : ((ComponentDefinition) topLevel).getComponents()) {
					if (elements.get(c.getIdentity())!=null) {
						Identified identified = elements.get(c.getIdentity());
						if (!c.equals(identified)) {
							//errors.add("Multiple elements with identity " + c.getIdentity());
							errors.add(new SBOLValidationException("sbol-10202", c).getExceptionMessage());
						}
		 			}
					elements.put(c.getIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								//errors.add("Multiple elements with identity " + m.getIdentity());
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
							//errors.add("Multiple elements with identity " + sa.getIdentity());
							errors.add(new SBOLValidationException("sbol-10202", sa).getExceptionMessage());
						}
		 			}					
					elements.put(sa.getIdentity(),sa);
					for (Location l : sa.getLocations()) {
						if (elements.get(l.getIdentity())!=null) {
							Identified identified = elements.get(l.getIdentity());
							if (!l.equals(identified)) {
								//errors.add("Multiple elements with identity " + l.getIdentity());
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
							//errors.add("Multiple elements with identity " + sc.getIdentity());
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
							//errors.add("Multiple elements with identity " + c.getIdentity());
							errors.add(new SBOLValidationException("sbol-10202", c).getExceptionMessage());
						}
		 			}
					elements.put(c.getIdentity(),c);
					for (MapsTo m : c.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								//errors.add("Multiple elements with identity " + m.getIdentity());
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
							//errors.add("Multiple elements with identity " + mod.getIdentity());
							errors.add(new SBOLValidationException("sbol-10202", mod).getExceptionMessage());
						}
		 			}
					elements.put(mod.getIdentity(),mod);
					for (MapsTo m : mod.getMapsTos()) {
						if (elements.get(m.getIdentity())!=null) {
							Identified identified = elements.get(m.getIdentity());
							if (!m.equals(identified)) {
								//errors.add("Multiple elements with identity " + m.getIdentity());
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
							//errors.add("Multiple elements with identity " + i.getIdentity());
							errors.add(new SBOLValidationException("sbol-10202", i).getExceptionMessage());
						}
		 			}					
					elements.put(i.getIdentity(),i);
					for (Participation p : i.getParticipations()) {
						if (elements.get(p.getIdentity())!=null) {
							Identified identified = elements.get(p.getIdentity());
							if (!p.equals(identified)) {
								//errors.add("Multiple elements with identity " + p.getIdentity());
								errors.add(new SBOLValidationException("sbol-10202", p).getExceptionMessage());
							}
			 			}
						elements.put(p.getIdentity(),p);
					}
				}
			}
		}
	}
	
	/* 
	 * Validate SBOL document.  Errors either throw exceptions or, if not fatal, add to the list of errors
	 * that can be accessed using the getErrors() method.
	 * 
	 * @param sbolDocument
	 * @param complete
	 * @param compliant
	 * @param bestPractice
	 */
	public static void validateSBOL(SBOLDocument sbolDocument, boolean complete, boolean compliant, 
			boolean bestPractice) {
		clearErrors();
		validateSequenceEncodings(sbolDocument);
		validateWasDerivedFromVersion(sbolDocument);
		validateCircularReferences(sbolDocument);
		validateURIuniqueness(sbolDocument);
        if (compliant) validateCompliance(sbolDocument);
        if (complete) validateCompleteness(sbolDocument);
        if (bestPractice) {
        	validateOntologyUsage(sbolDocument);
        	validateSequenceAnnotations(sbolDocument);
        	validateComponentDefinitionSequences(sbolDocument);
        }
	}
	
	private static void usage() {	
		// TODO: update
		System.err.println("libSBOLj version " + SBOLVersion);
		System.err.println("Description: Validates the contents of an SBOL document,\n" 
				+ "converting from SBOL 1.1 to SBOL " + SBOLVersion + ", if necessary,\n" 
				+ "and printing the document contents if validation succeeds");
		System.err.println();
		System.err.println("Usage:");
		System.err.println("\tjava --jar libSBOLj.jar [options] <inputFile> [-o <outputFile> -p <URIprefix> -v <version>]");
		System.err.println();
		System.err.println("-g  convert GenBank file");
		System.err.println("-c <componentDefinitionURI> specifies top-level ComponentDefinition");
		System.err.println("-t  uses types in URIs");
		System.err.println("-i  incomplete SBOL document");
		System.err.println("-n  non-compliant SBOL document");
		System.err.println("-b  perform best practice checking");
		System.err.println("-f  fail on first error");
		System.err.println("-d  provide detailed error trace");
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
	 * "-t" uses types in URIs,
	 * <p>
	 * "-i" turns off completeness checking,
	 * <p>
	 * "-b" turns on best practice checking,
	 * <p>
	 * "-n" indicates a non-compliant SBOL document,
	 * <p>
	 * "-g" indicates conversion of GenBank file,
	 * <p>
	 * "-c" specifies a selected top-level component definition
	 * <p>
	 * "-o" specifies an output filename,
	 * <p>
	 * "-p" specifies the default URI prefix of the output file,
	 * <p>
	 * "-v" specifies version to use for converted objects, 
	 * <p>
	 * "-f" fail on first error, and
	 * <p>
	 * "-d" show detailed error trace.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "";
		String outputFile = "";
		String componentDefinitionStr = "";
		String URIPrefix = "";
		String version = "";
		boolean complete = true;
		boolean compliant = true;
		boolean typesInURI = false;
		boolean bestPractice = false;
		boolean keepGoing = true;
		boolean showDetail = false;
		boolean genBankIn = false;
		boolean genBankOut = false;
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
			} else if (args[i].equals("-g")) {
				genBankIn = true;
			} else if (args[i].equals("-f")) {
				keepGoing = false;
			} else if (args[i].equals("-d")) {
				showDetail = true;
			} else if (args[i].equals("-c")) {
				genBankOut = true;
				if (i+1 >= args.length) {
					usage();
				}
				componentDefinitionStr = args[i+1];
				i++;
			} else if (args[i].equals("-o")) {
				if (i+1 >= args.length) {
					usage();
				}
				outputFile = args[i+1];
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
			if (genBankIn) {
				if (!URIPrefix.equals("")) {
					GenBank.setURIPrefix(URIPrefix);
				}
				//GenBank.setTypesInURI(typesInURI);
				//GenBank.setVersion(version);
				doc = GenBank.read(fileName);
		        //doc.setTypesInURIs(typesInURI);
			} else {
				if (!URIPrefix.equals("")) {
					SBOLReader.setURIPrefix(URIPrefix);
				}
				if (!compliant) {
					SBOLReader.setCompliant(false);
				}
				SBOLReader.setTypesInURI(typesInURI);
				SBOLReader.setVersion(version);
				SBOLReader.setKeepGoing(keepGoing);
				if (SBOLReader.getSBOLVersion(fileName).equals(SBOLReader.SBOLVERSION1)) {
					System.err.println("Converting SBOL Version 1 to SBOL Version 2");
				}
				doc = SBOLReader.read(fileName);
		        doc.setTypesInURIs(typesInURI);
			} 
	        validateSBOL(doc, complete, compliant, bestPractice);
	        if (getNumErrors()==0 && SBOLReader.getNumErrors()==0) {
	        	if (outputFile.equals("")) {
	        		if (genBankOut) {
	        			ComponentDefinition componentDefinition = doc.getComponentDefinition(URI.create(componentDefinitionStr));
	        			GenBank.write(componentDefinition, (System.out));
	        		} else {
	        			SBOLWriter.write(doc, (System.out));
	        		}
	        	} else {
	        		System.out.println("Validation successful, no errors.");
	        		if (genBankOut) {
	        			ComponentDefinition componentDefinition = doc.getComponentDefinition(URI.create(componentDefinitionStr));
	        			GenBank.write(componentDefinition, outputFile);
	        		} else {
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
        	System.err.println(e.getMessage()+"\nValidation failed.");
		}
		catch (Throwable e) {
			if (showDetail) {
				e.printStackTrace();
			}
        	System.err.println(e.getMessage()+"\nValidation failed.");
		}
	}

}
