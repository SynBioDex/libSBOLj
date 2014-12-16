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
			else
			{
				//TODO: everything else should be a generic toplevel.
			}
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
			if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.type))
			{
				type.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Model.roles))
			{
				roles.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSubComponents))
			{
				components.add(parseSubComponent(((NestedDocument<QName>)namedProperty.getValue())));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequence))
			{
				structure = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceAnnotations))
			{
				sequenceAnnotations.add(parseSequenceAnnotation((NestedDocument<QName>)namedProperty.getValue()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ComponentDefinition.hasSequenceConstraints))
			{
				sequenceConstraints.add(parseSequenceConstraint(((NestedDocument<QName>)namedProperty.getValue())));
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
		c.setSequence(structure);
		c.setSubComponents(components);
		c.setSequenceAnnotations(sequenceAnnotations);
		c.setSequenceConstraints(sequenceConstraints);
		return c;
	}

	private static SequenceConstraint parseSequenceConstraint(NestedDocument<QName> sequenceConstraints)
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

		SequenceConstraint s = new SequenceConstraint(sequenceConstraints.getIdentity(), restriction, subject, object);
		return s;
	}

	private static SequenceAnnotation parseSequenceAnnotation(NestedDocument<QName> sequenceAnnotation)
	{
		Location location = null;
		String name = "";
		String description = "";
		String timeStamp = "";

		for(NamedProperty<QName> namedProperty : sequenceAnnotation.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Location.Location))
			{
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

		SequenceAnnotation s = new SequenceAnnotation(sequenceAnnotation.getIdentity(), location);
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
			l = parseRange(location);
		}
		else if(location.getType().equals(Sbol2Terms.MultiRange.MultiRange))
		{
			l = parseMultiRange(location);
		}
		else if(location.getType().equals(Sbol2Terms.Cut.Cut))
		{
			l = parseCut(location);
		}
		return l;

	}

	private static Cut parseCut(NestedDocument<QName> typeCut)
	{
		Integer at = 0;
		URI orientation = null;
		String version = "";
		for(NamedProperty<QName> namedProperty : typeCut.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Cut.at))
			{
				String temp = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
				at = Integer.parseInt(temp);
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Cut.orientation))
			{
				orientation = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		Cut c = new Cut(typeCut.getIdentity(), at);
		c.setOrientation(orientation);
		c.setVersion(version);
		return c;
	}

	private static MultiRange parseMultiRange(NestedDocument<QName> typeMultiRange)
	{
		String version = "";
		Set<URI> type = new HashSet<URI>();
		List<Range> ranges = new ArrayList<Range>(); //TODO: Should range be a List or Set?

		for(NamedProperty<QName> namedProperty : typeMultiRange.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.MultiRange.hasRanges))
			{
				ranges.add( parseRange((NestedDocument<QName>)namedProperty.getValue() ));
			}
		}

		MultiRange multiRange = new MultiRange(typeMultiRange.getIdentity());
		multiRange.setVersion(version);
		multiRange.setRanges(ranges);
		return null;
	}

	private static Range parseRange(NestedDocument<QName> typeRange)
	{
		int start = 0; //TODO: consider if start will ever be 0?
		int end = 0;   //TODO: consider if end will ever be 0? if no, err.
		URI orientation = null;
		String version = "";

		for(NamedProperty<QName> namedProperty : typeRange.getProperties())
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
				orientation = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Identified.version))
			{
				version = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
		}

		Range r = new Range(typeRange.getIdentity(), start, end);
		r.setOrientation(orientation);
		r.setVersion(version);
		return r;
	}

	private static Component parseSubComponent(NestedDocument<QName> subComponents)
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
				access = AccessType.valueOf(((Literal<QName>)namedProperty.getValue()).getValue().toString().toUpperCase());
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

		Component c = new Component(subComponents.getIdentity(), access, subComponentURI);
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
			if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.roles))
			{
				roles.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasSubModule))
			{
				subModules.add(parseSubModule(
						((NestedDocument<QName>)namedProperty.getValue())
						));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasInteractions))
			{
				interactions.add(
						parseInteraction(((NestedDocument<QName>)namedProperty.getValue())
								));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasFunctionalComponent))
			{
				functionalComponents.add(
						parseFunctionalComponents((NestedDocument<QName>)namedProperty.getValue())
						);
			}
			else if(namedProperty.getName().equals(Sbol2Terms.ModuleDefinition.hasModels))
			{
				models.add(URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString()));
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
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
		}

		ModuleDefinition moduleDefinition = SBOLDoc.createModuleDefinition(topLevel.getIdentity(), roles);
		moduleDefinition.setName(name);
		moduleDefinition.setDescription(description);
		moduleDefinition.setTimeStamp(getTimestamp(timeStamp));
		moduleDefinition.setComponents(functionalComponents);
		moduleDefinition.setInteractions(interactions);
		moduleDefinition.setModels(models);
		moduleDefinition.setSubModules(subModules);
	}

	private static Module parseSubModule(NestedDocument<QName> module)
	{
		URI subModuleURI = null;
		String name = "";
		String description = "";
		String timeStamp = "";
		List<MapsTo> mappings = new ArrayList<MapsTo>();

		for(NamedProperty<QName> namedProperty : module.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Module.hasInstantiatedModule))
			{
				subModuleURI = URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Documented.name))
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
			else if(namedProperty.getName().equals(Sbol2Terms.Module.hasMappings))
			{
				mappings.add(
						parseMapping((NestedDocument<QName>)namedProperty.getValue()));
			}
		}

		Module submodule = new Module(module.getIdentity(), subModuleURI);
		submodule.setName(name);
		submodule.setDescription(description);
		submodule.setTimeStamp(getTimestamp(timeStamp));
		submodule.setMappings(mappings);
		return submodule;
	}

	private static MapsTo parseMapping(NestedDocument<QName> mappings)
	{
		URI remote = null;
		RefinementType refinement = null;
		URI local = null;

		for(NamedProperty<QName> m : mappings.getProperties())
		{
			if(m.getName().equals(Sbol2Terms.MapsTo.refinement))
			{
				refinement = RefinementType.valueOf(((Literal<QName>)m.getValue()).getValue().toString());
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
		MapsTo map = new MapsTo(mappings.getIdentity(), refinement, local,remote);
		return map;
	}

	private static Interaction parseInteraction(NestedDocument<QName> interaction)
	{
		String name = "";
		String description = "";
		String timeStamp = "";
		Set<URI> type = new HashSet<URI>();
		List<Participation> participations = new ArrayList<Participation>();

		for(NamedProperty<QName> i : interaction.getProperties())
		{
			if(i.getName().equals(Sbol2Terms.Interaction.type))
			{
				type.add(URI.create(((Literal<QName>)i.getValue()).getValue().toString()));
			}
			else if(i.getName().equals(Sbol2Terms.Interaction.hasParticipations))
			{
				participations.add(parseParticipation(
						(NestedDocument<QName>)i.getValue()));
			}
			else if(i.getName().equals(Sbol2Terms.Documented.name))
			{
				name = ((Literal<QName>)i.getValue()).getValue().toString();
			}
			else if(i.getName().equals(Sbol2Terms.Documented.description))
			{
				description = ((Literal<QName>)i.getValue()).getValue().toString();
			}
			else if(i.getName().equals(Sbol2Terms.Identified.timeStamp))
			{
				timeStamp = ((Literal<QName>)i.getValue()).getValue().toString();
			}

		}
		Interaction i = new Interaction(interaction.getIdentity(), type, participations);
		return i;
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
			if(functionalComponent.getType().equals(Sbol2Terms.ComponentInstance.access))
			{
				access = AccessType.valueOf(((Literal<QName>)f.getValue()).getValue().toString().toUpperCase());
			}
			else if(f.getName().equals(Sbol2Terms.FunctionalComponent.direction))
			{
				direction = DirectionType.valueOf(((Literal<QName>)f.getValue()).getValue().toString());
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
		List<Annotation> annotations = new ArrayList<Annotation>();

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
				annotations.add(new Annotation(namedProperty)) ;
			}
		}

		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(), elements, encoding);
		//TODO: wait until Zhen adds features to getter() for Timestamp: sequence.setTimeStamp(new Timestamp(timestamp));
		// TODO: do not set name if blank
		sequence.setName(name);
		sequence.setDescription(description);
		sequence.setTimeStamp(getTimestamp(timeStamp));
		sequence.setAnnotations(annotations);


	}

	private static Annotation parseAnnotation(NestedDocument<QName> annotation)
	{
		//		Qname relation = null; //TODO: what is the correct way to set annotation?
		Literal literal = null;
		NamedProperty<QName> n = null;

		for(NamedProperty<QName> namedProperty : annotation.getProperties())
		{
			if(namedProperty.getName().equals(Sbol2Terms.Annotation.relation))
			{
				//				relation = ((Literal<QName>)namedProperty.getValue()).getValue().toString();
			}
			else if(namedProperty.getName().equals(Sbol2Terms.Annotation.value))
			{
				literal = ((Literal<QName>)namedProperty.getValue());
			}
			else
				n = namedProperty;
		}

		Annotation a = new Annotation(n); //Annotation(relation, literal);
		return a;
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
