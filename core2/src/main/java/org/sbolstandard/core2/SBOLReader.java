package org.sbolstandard.core2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.sbolstandard.core2.abstract_classes.Location;

import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {

	private static String name;

	/*
	 * TODO: When iterating nestedDocument, remember to go through each properties.
	 */

	public static SBOLDocument read(String fileName) throws Throwable {
		FileInputStream fis = new FileInputStream(fileName);
		String inputStreamString = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
		//		System.out.println(inputStreamString);

		return readRdf(new File(fileName));
	}

	/**
	 * Takes in the given .rdf file and converts the file to an SBOLDocument
	 */
	public static SBOLDocument readRdf(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		String inputStreamString = new Scanner(stream,"UTF-8").useDelimiter("\\A").next();

		DocumentRoot<QName> document= read(new StringReader(inputStreamString));
		return readRdf(buffer, document);
	}

	public static SBOLDocument readRdf(InputStream in, DocumentRoot<QName> document)
	{
		SBOLDocument SBOLDoc = new SBOLDocument();
		readRdf(SBOLDoc,document);
		return SBOLDoc;
	}



	private static DocumentRoot<QName> read(Reader reader) throws Exception
	{
		XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();
	}

	private static void readRdf(SBOLDocument SBOLDoc, DocumentRoot<QName> document) {
		for(TopLevelDocument<QName> topLevel : document.getTopLevelDocuments())
		{
			if(topLevel.getType().equals( Sbol2Terms.Collection.Collection))
				parseCollections(SBOLDoc, topLevel);
			else if(topLevel.getType().equals( Sbol2Terms.ModuleDefinition.ModuleDefinition))
				parseModuleDefinition(SBOLDoc, topLevel);
			else if(topLevel.getType().equals( Sbol2Terms.Model.Model))
				parseModels(SBOLDoc, topLevel);
			else if(topLevel.getType().equals( Sbol2Terms.Sequence.Sequence))
				parseSequences(SBOLDoc, topLevel);
			else if(topLevel.getType().equals( Sbol2Terms.ComponentDefinition.ComponentDefinition))
				parseComponentDefinitions(SBOLDoc, topLevel);
			else if(topLevel.getType().equals( Sbol2Terms.TopLevel.TopLevel))
				parseTopLevel(SBOLDoc, topLevel);
		}
	}

	private static ComponentDefinition parseComponentDefinitions(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";

		Set<URI> type = new HashSet<URI>();
		Set<URI> roles = new HashSet<URI>();

		URI structure = null;
		List<Component> components = new ArrayList<Component>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Model.roles))
			{
				roles.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.type))
			{
				type.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		ComponentDefinition c = SBOLDoc.createComponentDefinition(topLevel.getIdentity(), type, roles);
		c.setTimeStamp(getTimestamp(timeStamp));
		c.setName(name);
		c.setDescription(description);

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequence))
			{
				structure = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSubComponents))
			{
				components.add(parseSubComponents(c, ((NestedDocument<QName>)namedProperty.getValue())));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations))
			{
				sequenceAnnotations.add(parseSequenceAnnotations(c, ((NestedDocument<QName>)namedProperty.getValue())));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceConstraints))
			{
				sequenceConstraints.add(parseSequenceConstraints(c, ((NestedDocument<QName>)namedProperty.getValue())));
			}
		}

		c.setSequence(structure);
		c.setSubComponents(components);
		c.setSequenceAnnotations(sequenceAnnotations);
		c.setSequenceConstraints(sequenceConstraints);
		return c;
	}

	private static SequenceConstraint parseSequenceConstraints(ComponentDefinition componentDefinition, NestedDocument<QName> sequenceConstraints)
	{
		URI restriction = null;
		URI subject = null;
		URI object = null;

		for(NamedProperty<QName> namedProperty : sequenceConstraints.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.restriction))
			{
				restriction = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasSubject))
			{
				subject = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.SequenceConstraint.hasObject))
			{
				object = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
		}

		SequenceConstraint s = componentDefinition.createSequenceConstraint(sequenceConstraints.getIdentity(), restriction, subject, object);

		return s;
	}

	private static SequenceAnnotation parseSequenceAnnotations(ComponentDefinition componentDefinition, NestedDocument<QName> sequenceAnnotation)
	{
		Location location = null;
		String name = "";
		String description = "";
		String timeStamp = "";

		for(NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Location.Location))
			{
				//TODO: ASK.
				location = parseLocation((NestedDocument<QName>)namedProperty.getValue());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		SequenceAnnotation s = componentDefinition.createSequenceAnnotation(sequenceAnnotation.getIdentity(), location);
		s.setTimeStamp(getTimestamp(timeStamp));
		s.setName(name);
		s.setDescription(description);

		return s;
	}

	private static Location parseLocation(NestedDocument<QName> location)
	{
		Location l = null;

		if(location.getType().equals(Sbol2Terms.Range.Range))
		{
			int start = 0; //TODO: consider if start will ever be 0?
			int end = 0;   //TODO: consider if end will ever be 0? if no, err.
			for(NamedProperty<QName> namedProperty : location.getProperties())
			{
				String temp;
				if(namedProperty.getName().equals(Sbol2Terms.Range.start))
				{
					temp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
					start = Integer.parseInt(temp);
				}
				else if(namedProperty.getName().equals(Sbol2Terms.Range.end))
				{
					temp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
					end = Integer.parseInt(temp);
				}
				else if(namedProperty.getName().equals(Sbol2Terms.Range.orientation))
				{
					//TODO: how to set orientation? method not found in Range or Location.
				}
			}
			l = new Range(location.getIdentity(), start, end);
		} // end of Range
		else if(location.getType().equals(Sbol2Terms.MultiRange.MultiRange))
		{
			String version = "";
			URI persistentIdentity = null;
			Set<URI> type = new HashSet<URI>();
			Set<URI> ranges = new HashSet<URI>();

			for(NamedProperty<QName> namedProperty : location.getProperties())
			{
				if(namedProperty.getName().equals(Sbol2Terms.Identified.persistentIdentity))
				{
					persistentIdentity = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
				}
				else if(namedProperty.getName().equals(Sbol2Terms.Identified.version))
				{
					version = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
				}
			}
			l = new MultiRange(location.getIdentity(), persistentIdentity, version, ranges);
		}
		return l;

	}

	private static Component parseSubComponents(ComponentDefinition componentDefinition, NestedDocument<QName> subComponents)
	{
		String name = "";
		String description = "";
		String timeStamp = "";

		AccessType access = null;
		URI subComponentURI = null;

		for(NamedProperty<QName> namedProperty : subComponents.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.ComponentInstance.access))
			{
				//TODO: this is wrong
				access = AccessType.valueOf(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				subComponentURI = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		Component c = componentDefinition.createSubComponent(subComponents.getIdentity(), access, subComponentURI);
		c.setTimeStamp(getTimestamp(timeStamp));
		c.setName(name);
		c.setDescription(description);
		return c;
	}

	private static TopLevel parseTopLevel(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		TopLevel t = SBOLDoc.createTopLevel(topLevel.getIdentity());
		t.setTimeStamp(getTimestamp(timeStamp));
		t.setName(name);
		t.setDescription(description);
		return t;
	}

	private static Model parseModels(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		URI source = null;
		URI language = null;
		URI framework = null;
		Set<URI> roles = new HashSet<URI>();

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Model.source))
			{
				source = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Model.language))
			{
				language = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Model.framework))
			{
				framework = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Model.roles))
			{
				roles.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		Model m = SBOLDoc.createModel(topLevel.getIdentity(), source, language, framework, roles);
		m.setTimeStamp(getTimestamp(timeStamp));
		m.setName(name);
		m.setDescription(description);
		m.setSource(source);
		m.setLanguage(language);
		m.setFramework(framework);
		m.setRoles(roles);
		return m;
	}

	private static void parseCollections(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		Set<URI> members = new HashSet<URI>();

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Collection.hasMembers))
			{
				members.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		Collection c = SBOLDoc.createCollection(topLevel.getIdentity());
		c.setTimeStamp(getTimestamp(timeStamp));
		c.setName(name);
		c.setDescription(description);
		c.setMembers(members);

	}

	private static void parseModuleDefinition(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		Set<URI> roles = new HashSet<URI>();
		List<FunctionalComponent> functionalComponents = new ArrayList<FunctionalComponent>();
		List<Interaction> interactions = new ArrayList<Interaction>();
		Set<URI> models = new HashSet<URI>();
		List<Module> subModules = new ArrayList<Module>();

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.roles))
			{
				roles.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasFunctionalComponent))
			{
				functionalComponents.add(
						parseFunctionalComponents((NestedDocument<QName>)namedProperty.getValue())
						);
			}
		}

		ModuleDefinition moduleDefinition = SBOLDoc.createModuleDefinition(topLevel.getIdentity(), roles);

		moduleDefinition.setName(name);
		moduleDefinition.setDescription(description);
		moduleDefinition.setTimeStamp(getTimestamp(timeStamp));

		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			//			if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasFunctionalComponent))
			//			{
			//				functionalComponents.add(parseFunctionalComponents(moduleDefinition,
			//						((NestedDocument<QName>)namedProperty.getValue())
			//						));
			//			}
			if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasInteractions))
			{
				interactions.add(parseInteractions(moduleDefinition,
						((NestedDocument<QName>)namedProperty.getValue())
						));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModels))
			{
				models.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasSubModule))
			{
				subModules.add(parseSubModule(moduleDefinition,
						((NestedDocument<QName>)namedProperty.getValue())
						));
			}
		}
		moduleDefinition.setComponents(functionalComponents);
		moduleDefinition.setInteractions(interactions);
		moduleDefinition.setModels(models);
		moduleDefinition.setSubModules(subModules);
	}

	private static Module parseSubModule(ModuleDefinition moduleDefinition, NestedDocument<QName> modules)
	{
		URI subModuleURI = null;
		String name = "";
		String description = "";
		String timeStamp = "";
		List<MapsTo> mappings = new ArrayList<MapsTo>();

		for(NamedProperty<QName> m : modules.getProperties())
		{
			if(m.getName().equals(Sbol2Terms.Module.hasInstantiatedModule))
			{
				subModuleURI = URI.create(((Literal<QName>)m.getValue()).getValue().toString());
			}
			else if(m.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)m.getValue()).getValue().toString();
			}
			else if(m.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)m.getValue()).getValue().toString();
			}
			else if(m.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)m.getValue()).getValue().toString();
			}
		}

		Module module = moduleDefinition.createSubModule(modules.getIdentity(), subModuleURI);

		for(NamedProperty<QName> namedProperty : modules.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Module.hasMappings))
			{
				mappings.add(parseMappings(module,
						(NestedDocument<QName>)namedProperty.getValue()));
			}
		}

		module.setName(name);
		module.setDescription(description);
		module.setTimeStamp(getTimestamp(timeStamp));
		module.setMappings(mappings);
		return module;
	}

	private static MapsTo parseMappings(Module module, NestedDocument<QName> mappings)
	{
		URI remote = null;
		RefinementType refinement = null;
		URI local = null;

		for(NamedProperty<QName> m : mappings.getProperties())
		{
			if(m.getName().equals(Sbol2Terms.MapsTo.refinement))
			{
				//TODO: how to retrieve RefinementType?
				//refinement = ((Literal<QName>)m.getValue()).getValue().toString();
				refinement = RefinementType.useLocal;
			}
			else if(m.getName().equals(Sbol2Terms.MapsTo.hasRemote))
			{
				remote = URI.create(((Literal<QName>)m.getValue()).getValue().toString());
			}
			else if(m.getName().equals(Sbol2Terms.MapsTo.hasLocal))
			{
				local = URI.create(((Literal<QName>)m.getValue()).getValue().toString());
			}
		}
		//TODO: How do you create a MapsTo to module?
		MapsTo map = new MapsTo(mappings.getIdentity(), refinement, local,remote);
		module.addMapping(map);
		return map;
	}

	private static Interaction parseInteractions(ModuleDefinition moduleDefinition, NestedDocument<QName> interactions)
	{
		Set<URI> type = new HashSet<URI>();
		List<Participation> participations = new ArrayList<Participation>();

		for(NamedProperty<QName> i : interactions.getProperties())
		{
			if(i.getName().equals(Sbol2Terms.Interaction.type))
			{
				type.add(URI.create(((Literal<QName>)i.getValue()).getValue().toString()));
			}
			else if(i.getName().equals(Sbol2Terms.Interaction.hasParticipations))
			{
				//TODO: this is wrong. ASK.
				participations.add(parseParticipation(
						(NestedDocument<QName>)i.getValue()));
			}
		}
		Interaction interaction = moduleDefinition.createInteraction(interactions.getIdentity(), type, participations);
		return interaction;
	}

	private static Participation parseParticipation(NestedDocument<QName> participation)
	{
		Set<URI> role = new HashSet<URI>();
		URI participant = null;

		for(NamedProperty<QName> p : participation.getProperties())
		{
			if(p.getName().equals(Sbol2Terms.Participation.role))
			{
				role.add(URI.create(((Literal<QName>)p.getValue()).getValue().toString()));
			}
			else if(p.getName().equals(Sbol2Terms.Participation.hasParticipant))
			{
				participant = URI.create(((Literal<QName>)p.getValue()).getValue().toString());
			}
		}
		Participation p = new Participation(participation.getIdentity(), role, participant);
		//				interaction.createParticipation(participation.getIdentity(), role, participant);

		return p;
	}

	private static FunctionalComponent parseFunctionalComponents(NestedDocument<QName> functionalComponent)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		AccessType access = null;
		DirectionType direction = null;
		URI functionalComponentURI = null;

		for(NamedProperty<QName> f : functionalComponent.getProperties())
		{
			if(functionalComponent.getType() == Sbol2Terms.ComponentInstance.access)
			{
				//TODO: ASK!
				access = AccessType.PRIVATE;
			}
			else if(f.getName().equals(Sbol2Terms.FunctionalComponent.direction))
			{
				//TODO: ASK!
				direction = DirectionType.input;
			}
			else if(f.getName().equals(Sbol2Terms.ComponentInstance.hasComponentDefinition))
			{
				functionalComponentURI = URI.create(((Literal<QName>)f.getValue()).getValue().toString());
			}
			else if(f.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)f.getValue()).getValue().toString();
			}
			else if(f.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)f.getValue()).getValue().toString();
			}
			else if(f.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)f.getValue()).getValue().toString();
			}
		}

		FunctionalComponent fc = new FunctionalComponent(functionalComponent.getIdentity(), access, functionalComponentURI, direction);
		//				moduleDefinition.createComponent(functionalComponents.getIdentity(), access, functionalComponentURI, direction);
		fc.setName(name);
		fc.setDescription(description);
		fc.setTimeStamp(getTimestamp(timeStamp));

		return fc;
	}

	private static void parseSequences(SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		String elements ="";
		URI encoding = null;


		for(NamedProperty<QName> namedProperty : topLevel.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Sequence.elements))
			{
				elements = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if( namedProperty.getName().equals( Sbol2Terms.Sequence.encoding))
			{
				encoding = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else
			{
				// TODO: Everything else will be considered as an annotation
			}
		}

		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(), elements, encoding);
		//TODO: wait until Zhen adds features to getter() for Timestamp: sequence.setTimeStamp(new Timestamp(timestamp));
		// TODO: do not set name if blank
		sequence.setName(name);
		sequence.setDescription(description);
		sequence.setTimeStamp(getTimestamp(timeStamp));


	}

	private static Timestamp getTimestamp(String timeStamp)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date date = null;
		try {
			date = sdf.parse(timeStamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}

}