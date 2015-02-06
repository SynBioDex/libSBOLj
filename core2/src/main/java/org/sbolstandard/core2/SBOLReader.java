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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonReader;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.sbolstandard.core2.abstract_classes.Location;
import org.sbolstandard.core2.util.SBOLPair;

import uk.ac.intbio.core.io.turtle.TurtleIo;
import uk.ac.ncl.intbio.core.datatree.DocumentRoot;
import uk.ac.ncl.intbio.core.datatree.IdentifiableDocument;
import uk.ac.ncl.intbio.core.datatree.Literal;
import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import uk.ac.ncl.intbio.core.datatree.NamespaceBinding;
import uk.ac.ncl.intbio.core.datatree.NestedDocument;
import uk.ac.ncl.intbio.core.datatree.TopLevelDocument;
import uk.ac.ncl.intbio.core.io.IoReader;
import uk.ac.ncl.intbio.core.io.json.JsonIo;
import uk.ac.ncl.intbio.core.io.json.StringifyQName;
import uk.ac.ncl.intbio.core.io.rdf.RdfIo;

public class SBOLReader {

	public static SBOLDocument read(String fileName) throws Throwable {
		FileInputStream fis = new FileInputStream(fileName);
		String inputStreamString = new Scanner(fis, "UTF-8")
				.useDelimiter("\\A").next();
		return readRdf(new File(fileName));
	}

	public static SBOLDocument readJson(String fileName) throws Throwable {
		return readJson(new File(fileName));
	}

	public static SBOLDocument readRdf(String fileName) throws Throwable {
		return readRdf(new File(fileName));
	}

	public static SBOLDocument readTurtle(String fileName) throws Throwable {
		return readTurtle(new File(fileName));
	}

	public static SBOLDocument readJson(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		String inputStreamString = new Scanner(stream, "UTF-8").useDelimiter(
				"\\A").next();

		DocumentRoot<QName> document = readJson(new StringReader(
				inputStreamString));
		return readJson(buffer, document);
	}

	public static SBOLDocument read(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);

		return read(buffer);
	}

	/**
	 * Takes in the given .rdf file and converts the file to an SBOLDocument
	 */
	public static SBOLDocument readRdf(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		// String inputStreamString = new
		// Scanner(stream,"UTF-8").useDelimiter("\\A").next();
		//
		// DocumentRoot<QName> document= readRdf(new
		// StringReader(inputStreamString));
		//
		// for(NamespaceBinding n: document.getNamespaceBindings())
		// {
		// if(n.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI()))
		// {
		// return readRdfV1(buffer, document);
		// }
		// }
		return readRdf(buffer);
	}

	public static SBOLDocument readTurtle(File file) throws Throwable {
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		String inputStreamString = new Scanner(stream, "UTF-8").useDelimiter(
				"\\A").next();

		DocumentRoot<QName> document = readTurtle(new StringReader(
				inputStreamString));
		return readTurtle(buffer, document);
	}

	public static SBOLDocument readJson(InputStream in,
			DocumentRoot<QName> document) {
		SBOLDocument SBOLDoc = new SBOLDocument();
		for (NamespaceBinding n : document.getNamespaceBindings()) {
			SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
					n.getPrefix());
		}
		readTopLevelDocs(SBOLDoc, document);
		return SBOLDoc;
	}

	public static SBOLDocument read(InputStream in) {
		String inputStreamString = new Scanner(in, "UTF-8").useDelimiter("\\A")
				.next();
		SBOLDocument SBOLDoc = new SBOLDocument();
		try {
			DocumentRoot<QName> document = readRdf(new StringReader(
					inputStreamString));

			for (NamespaceBinding n : document.getNamespaceBindings()) {
				if (n.getNamespaceURI().equals(
						Sbol1Terms.sbol1.getNamespaceURI())) {
					return readRdfV1(in, document);
				}
				SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
						n.getPrefix());
			}

			readTopLevelDocs(SBOLDoc, document);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SBOLDoc;
	}

	public static SBOLDocument readRdf(InputStream in) {
		String inputStreamString = new Scanner(in, "UTF-8").useDelimiter("\\A")
				.next();
		SBOLDocument SBOLDoc = new SBOLDocument();
		// DocumentRoot<QName> document;
		try {
			DocumentRoot<QName> document = readRdf(new StringReader(
					inputStreamString));

			for (NamespaceBinding n : document.getNamespaceBindings()) {
				if (n.getNamespaceURI().equals(
						Sbol1Terms.sbol1.getNamespaceURI())) {
					return readRdfV1(in, document);
				}
				SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
						n.getPrefix());
			}

			// for(NamespaceBinding n : document.getNamespaceBindings())
			// {
			// SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
			// n.getPrefix());
			// }
			readTopLevelDocs(SBOLDoc, document);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SBOLDoc;
	}

	public static SBOLDocument readRdfV1(InputStream in,
			DocumentRoot<QName> document) {
		SBOLDocument SBOLDoc = new SBOLDocument();
		for (NamespaceBinding n : document.getNamespaceBindings()) {
			if (n.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI()))
				SBOLDoc.addNameSpaceBinding(
						URI.create(Sbol2Terms.sbol2.getNamespaceURI()),
						Sbol2Terms.sbol2.getPrefix());
			else
				SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
						n.getPrefix());
		}
		readTopLevelDocsV1(SBOLDoc, document);
		return SBOLDoc;
	}

	public static SBOLDocument readTurtle(InputStream in,
			DocumentRoot<QName> document) {
		SBOLDocument SBOLDoc = new SBOLDocument();
		for (NamespaceBinding n : document.getNamespaceBindings()) {
			SBOLDoc.addNameSpaceBinding(URI.create(n.getNamespaceURI()),
					n.getPrefix());
		}
		readTopLevelDocs(SBOLDoc, document);
		return SBOLDoc;
	}

	private static DocumentRoot<QName> readJson(Reader stream) throws Exception {
		JsonReader reader = Json.createReaderFactory(
				Collections.<String, Object> emptyMap()).createReader(stream);
		JsonIo jsonIo = new JsonIo();
		IoReader<String> ioReader = jsonIo.createIoReader(reader.read());
		DocumentRoot<String> root = ioReader.read();
		return StringifyQName.string2qname.mapDR(root);
	}

	private static DocumentRoot<QName> readRdf(Reader reader) throws Exception {
		XMLStreamReader xmlReader = XMLInputFactory.newInstance()
				.createXMLStreamReader(reader);
		RdfIo rdfIo = new RdfIo();
		return rdfIo.createIoReader(xmlReader).read();
	}

	private static DocumentRoot<QName> readTurtle(Reader reader)
			throws Exception {
		TurtleIo turtleIo = new TurtleIo();
		return turtleIo.createIoReader(reader).read();
	}

	private static void readTopLevelDocsV1(SBOLDocument SBOLDoc,
			DocumentRoot<QName> document) {

		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments()) {
			if (topLevel.getType().equals(Sbol1Terms.DNAComponent.DNAComponent))
				parseDnaComponentV1(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(
					Sbol1Terms.DNASequence.DNASequence))
				parseDnaSequenceV1(SBOLDoc, topLevel);
			else if (topLevel.getType()
					.equals(Sbol1Terms.Collection.Collection))
				parseCollectionV1(SBOLDoc, topLevel);
			// TODO: else parse as genericTopLevel?
		}
	}

	private static void readTopLevelDocs(SBOLDocument SBOLDoc,
			DocumentRoot<QName> document) {
		for (TopLevelDocument<QName> topLevel : document.getTopLevelDocuments()) {
			if (topLevel.getType().equals(Sbol2Terms.Collection.Collection))
				parseCollections(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(
					Sbol2Terms.ModuleDefinition.ModuleDefinition))
				parseModuleDefinition(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol2Terms.Model.Model))
				parseModels(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(Sbol2Terms.Sequence.Sequence))
				parseSequences(SBOLDoc, topLevel);
			else if (topLevel.getType().equals(
					Sbol2Terms.ComponentDefinition.ComponentDefinition))
				parseComponentDefinitions(SBOLDoc, topLevel);
			else
				parseGenericTopLevel(SBOLDoc, topLevel);
		}
	}

	private static ComponentDefinition parseDnaComponentV1(
			SBOLDocument SBOLDoc, IdentifiableDocument<QName> componentDef) {
		String displayId = null;
		String name = null;
		String description = null;
		Set<URI> roles = new HashSet<URI>();
		URI seq_identity = null;
		List<Annotation> annotations = new ArrayList<Annotation>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();
		List<Component> components = new ArrayList<Component>();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();

		List<SBOLPair> precedePairs = new ArrayList<SBOLPair>();
		Map<IdentifiableDocument<QName>, URI> componentDefMap = new HashMap<IdentifiableDocument<QName>, URI>();

		Set<URI> type = new HashSet<URI>();
		type.add(Sbol2Terms.DnaComponentV1URI.type);

		for (NamedProperty<QName> namedProperty : componentDef.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.displayId)) {
				displayId = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.type)) {
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.annotations)) {
				URI value = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
				componentDefMap.put(componentDef, value);

				SequenceAnnotation sa = parseSequenceAnnotationV1(SBOLDoc,
						((NestedDocument<QName>) namedProperty.getValue()),
						componentDefMap, precedePairs);

				sequenceAnnotations.add(sa);

				// TODO: make component either displayid or unique counter
				URI component_identity = URI.create(componentDef.getIdentity()
						+ "/component/1/0"); // TODO: this is wrong
				URI access = Sbol2Terms.Access.PUBLIC;
				URI instantiatedComponent = sa.getComponent();
				// TODO: change sa.getComponent() to component_identity
				// TODO: add to map2 from sa.getIdentity() to component_identity

				Component component = new Component(component_identity, access,
						instantiatedComponent);
				components.add(component);

			} else if (namedProperty.getName().equals(
					Sbol1Terms.DNAComponent.dnaSequence)) {
				// TODO: if find sequence, call parseSequenceV1 returns identity
				// of the sequence
				// which is what you add to the object
				seq_identity = parseDnaSequenceV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue())
						.getIdentity();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		if (roles.isEmpty())
			roles.add(Sbol2Terms.DnaComponentV1URI.roles);

		// TODO: for each pair in the list of precedes, create a
		// sequenceConstraint of restriction
		// precedes, the subject is the component URI of the first SA in
		// the list and the object is the second SA's component
		for (SBOLPair pair : precedePairs) {
			// TODO: need parent URI in front, sequenceConstraint##
			URI sc_identity = URI.create("sequenceConstraint/1/0");
			// TODO: turn into a URI constant
			URI restriction = URI.create("precedes/1/0"); // TODO: this is
															// incorrect
			// TODO: these should use map2 to fetch component URI
			URI subject = pair.getLeft();
			URI object = pair.getRight();

			SequenceConstraint sc = new SequenceConstraint(sc_identity,
					restriction, subject, object);
			sequenceConstraints.add(sc);
		}

		ComponentDefinition c = SBOLDoc.createComponentDefinition(
				componentDef.getIdentity(), type, roles);
		if (displayId != null)
			c.setDisplayId(displayId);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (seq_identity != null)
			c.setSequence(seq_identity);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		if (!sequenceAnnotations.isEmpty())
			c.setSequenceAnnotations(sequenceAnnotations);
		if (!components.isEmpty())
			c.setSubComponents(components);
		if (!sequenceConstraints.isEmpty())
			c.setSequenceConstraints(sequenceConstraints);

		return c;
	}

	private static Sequence parseDnaSequenceV1(SBOLDocument SBOLDoc,
			IdentifiableDocument<QName> topLevel) {
		String elements = null;
		URI encoding = Sbol2Terms.SequenceURI.DnaSequenceV1;
		String displayId = null;
		String name = null;
		String description = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol1Terms.DNASequence.nucleotides)) {
				elements = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.displayId)) {
				displayId = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(),
				elements, encoding);
		if (displayId != null)
			sequence.setDisplayId(displayId);
		if (name != null)
			sequence.setName(name);
		if (description != null)
			sequence.setDescription(description);
		if (!annotations.isEmpty())
			sequence.setAnnotations(annotations);

		return sequence;
	}

	private static Collection parseCollectionV1(SBOLDocument SBOLDoc,
			IdentifiableDocument<QName> topLevel) {
		String displayId = null;
		String name = null;
		String description = null;
		Set<URI> members = new HashSet<URI>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(Sbol1Terms.Collection.displayId)) {
				displayId = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.Collection.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.Collection.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.Collection.component)) {
				members.add(parseDnaComponentV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue())
						.getIdentity());
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Collection c = SBOLDoc.createCollection(topLevel.getIdentity());
		if (displayId != null)
			c.setDisplayId(displayId);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (!members.isEmpty())
			c.setMembers(members);
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		return c;
	}

	// TODO: add a list of precedes pairs
	private static SequenceAnnotation parseSequenceAnnotationV1(
			SBOLDocument SBOLDoc, NestedDocument<QName> sequenceAnnotation,
			Map<IdentifiableDocument<QName>, URI> componentDefMap,
			List<SBOLPair> precedePairs) {
		String identity = null;
		Integer start = null;
		Integer end = null;
		String strand = null;
		URI componentURI = null;
		List<Annotation> annotations = new ArrayList<Annotation>();
		// List<SBOLPair> precedePairs = new ArrayList<SBOLPair>();
		Map<URI, IdentifiableDocument<QName>> saURIMap = new HashMap<URI, IdentifiableDocument<QName>>();

		for (NamedProperty<QName> namedProperty : sequenceAnnotation
				.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.uri)) {
				identity = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.bioStart)) {
				String temp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
				start = Integer.parseInt(temp);
			} else if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.bioEnd)) {
				String temp2 = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
				end = Integer.parseInt(temp2);
			} else if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.strand)) {
				strand = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.subComponent)) {
				componentURI = parseDnaComponentV1(SBOLDoc,
						(NestedDocument<QName>) namedProperty.getValue())
						.getIdentity();
			} else if (namedProperty.getName().equals(
					Sbol1Terms.SequenceAnnotations.precedes)) {
				// TODO: create a pair which includes URI of this annotation and
				// the URI referred to in
				// the precedes, and add this to a list of precedes pairs.
				URI left = sequenceAnnotation.getIdentity();
				URI right = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
				SBOLPair pair = new SBOLPair(left, right);
				precedePairs.add(pair);
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Location location = null; // Note: location is null. Do not create a
									// seqAnnotation if Location is empty

		if (start != null && end != null) // create SequenceAnnotation &
											// Component
		{
			URI range_identity = URI.create(sequenceAnnotation.getIdentity()
					+ "/range/1/0"); // TODO: fix the parent's identity.
										// currently null Location
			Range r = new Range(range_identity, start, end);
			if (strand != null) {
				if (strand.equals("+")) {
					r.setOrientation(Sbol2Terms.Orientation.inline);
				} else if (strand.equals("-")) {
					r.setOrientation(Sbol2Terms.Orientation.reverseComplement);
				}

				location = r;
			}
		} else {
			// TODO: create location with dummy (0) values for start/end for now
			// replace later with new location class composed of just an
			// orientation
			// Do not return here
			return null;
		}

		SequenceAnnotation s = new SequenceAnnotation(
				sequenceAnnotation.getIdentity(), location);

		if (componentURI != null)
			s.setComponent(componentURI);
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);

		return s;

	}

	private static ComponentDefinition parseComponentDefinitions(
			SBOLDocument SBOLDoc, TopLevelDocument<QName> topLevel) {
		Set<URI> type = new HashSet<URI>();
		Set<URI> roles = new HashSet<URI>();

		URI structure = null;
		List<Component> components = new ArrayList<Component>();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();

		String name = null;
		String description = null;
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol2Terms.ComponentDefinition.type)) {
				type.add(URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString()));
			} else if (namedProperty.getName().equals(Sbol2Terms.Model.roles)) {
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ComponentDefinition.hasSubComponents)) {
				components
						.add(parseSubComponent(((NestedDocument<QName>) namedProperty
								.getValue())));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ComponentDefinition.hasSequence)) {
				structure = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ComponentDefinition.hasSequenceAnnotations)) {
				sequenceAnnotations
						.add(parseSequenceAnnotation((NestedDocument<QName>) namedProperty
								.getValue()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ComponentDefinition.hasSequenceConstraints)) {
				sequenceConstraints
						.add(parseSequenceConstraint(((NestedDocument<QName>) namedProperty
								.getValue())));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		ComponentDefinition c = SBOLDoc.createComponentDefinition(
				topLevel.getIdentity(), type, roles);
		if (structure != null)
			c.setSequence(structure);
		if (!components.isEmpty())
			c.setSubComponents(components);
		if (!sequenceAnnotations.isEmpty())
			c.setSequenceAnnotations(sequenceAnnotations);
		if (!sequenceConstraints.isEmpty())
			c.setSequenceConstraints(sequenceConstraints);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (timeStamp != null)
			c.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		return c;
	}

	private static SequenceConstraint parseSequenceConstraint(
			NestedDocument<QName> sequenceConstraints) {
		URI restriction = null;
		URI subject = null;
		URI object = null;
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : sequenceConstraints
				.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.restriction)) {
				restriction = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.hasSubject)) {
				subject = URI
						.create(((Literal<QName>) namedProperty.getValue())
								.getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceConstraint.hasObject)) {
				object = URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		SequenceConstraint s = new SequenceConstraint(
				sequenceConstraints.getIdentity(), restriction, subject, object);
		// s.setTimeStamp(getTimestamp(timeStamp)); //TODO: supress for now
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		return s;
	}

	private static SequenceAnnotation parseSequenceAnnotation(
			NestedDocument<QName> sequenceAnnotation) {
		Location location = null;
		URI componentURI = null;
		String name = null;
		String description = null;
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : sequenceAnnotation
				.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Location.Location)) {
				location = parseLocation((NestedDocument<QName>) namedProperty
						.getValue());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.SequenceAnnotation.hasComponent)) {
				componentURI = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		SequenceAnnotation s = new SequenceAnnotation(
				sequenceAnnotation.getIdentity(), location);
		if (componentURI != null)
			s.setComponent(componentURI);
		if (name != null)
			s.setName(name);
		if (description != null)
			s.setDescription(description);
		if (timeStamp != null)
			s.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			s.setAnnotations(annotations);
		return s;
	}

	private static Location parseLocation(NestedDocument<QName> location) {
		Location l = null;
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		if (location.getType().equals(Sbol2Terms.Range.Range)) {
			l = parseRange(location);
		} else if (location.getType().equals(Sbol2Terms.MultiRange.MultiRange)) {
			l = parseMultiRange(location);
		} else if (location.getType().equals(Sbol2Terms.Cut.Cut)) {
			l = parseCut(location);
		} else // TODO: Ask how to handle this exception
		{
			System.out
					.println("ERR: Null. Location isn't a Range, MultiRange, or Cut.");
			return l;
		}

		// TODO: should the location members be parsed when it is already parsed
		// within range, cut,
		// and multirange?
		for (NamedProperty<QName> namedProperty : location.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		if (timeStamp != null)
			l.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			l.setAnnotations(annotations);

		return l;

	}

	private static Cut parseCut(NestedDocument<QName> typeCut) {
		Integer at = null;
		URI orientation = null;
		String version = null;
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : typeCut.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Cut.at)) {
				String temp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
				at = Integer.parseInt(temp);
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Cut.orientation)) {
				orientation = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.version)) {
				version = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		if (at == null) {
			// TODO: how to handle problems with data model when at is null
			System.out.println("ERR: at is Null. Problem is data model");
		}

		Cut c = new Cut(typeCut.getIdentity(), at);
		if (orientation != null)
			c.setOrientation(orientation);
		if (version != null)
			c.setVersion(version);
		if (timeStamp != null)
			c.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	private static MultiRange parseMultiRange(
			NestedDocument<QName> typeMultiRange) {
		String version = null;
		List<Range> ranges = new ArrayList<Range>();
		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : typeMultiRange
				.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.MultiRange.hasRanges)) {
				ranges.add(parseRange((NestedDocument<QName>) namedProperty
						.getValue()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.version)) {
				version = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		MultiRange multiRange = new MultiRange(typeMultiRange.getIdentity());
		if (!ranges.isEmpty())
			multiRange.setRanges(ranges);
		if (version != null)
			multiRange.setVersion(version);
		if (timeStamp != null)
			multiRange.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			multiRange.setAnnotations(annotations);
		return null;
	}

	private static Range parseRange(NestedDocument<QName> typeRange) {
		Integer start = null;
		Integer end = null;
		URI orientation = null;
		String version = null;

		String timeStamp = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : typeRange.getProperties()) {
			String temp;
			if (namedProperty.getName().equals(Sbol2Terms.Range.start)) {
				temp = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
				start = Integer.parseInt(temp);
			} else if (namedProperty.getName().equals(Sbol2Terms.Range.end)) {
				temp = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
				end = Integer.parseInt(temp);
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Range.orientation)) {
				orientation = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.version)) {
				version = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Range r = new Range(typeRange.getIdentity(), start, end);
		if (orientation != null)
			r.setOrientation(orientation);
		if (version != null)
			r.setVersion(version);
		if (timeStamp != null)
			r.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			r.setAnnotations(annotations);
		return r;
	}

	private static Component parseSubComponent(
			NestedDocument<QName> subComponents) {
		String name = null;
		String description = null;
		String timeStamp = null;
		URI access = Sbol2Terms.Access.PUBLIC;
		URI subComponentURI = null;
		List<Annotation> annotations = new ArrayList<Annotation>();
		List<MapsTo> mappings = new ArrayList<MapsTo>();

		for (NamedProperty<QName> namedProperty : subComponents.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol2Terms.ComponentInstance.access)) {
				access = URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString());
			}
			if (namedProperty.getName().equals(Sbol2Terms.Module.hasMappings)) {
				mappings.add(parseMapping((NestedDocument<QName>) namedProperty
						.getValue()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ComponentInstance.hasComponentDefinition)) {
				subComponentURI = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Component c = new Component(subComponents.getIdentity(), access,
				subComponentURI);
		if (access != null)
			c.setAccess(access);
		if (!mappings.isEmpty())
			c.setMappings(mappings);
		if (subComponentURI != null)
			c.setDefinition(subComponentURI);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (timeStamp != null)
			c.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);

		return c;
	}

	private static GenericTopLevel parseGenericTopLevel(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel) {
		String name = null;
		String description = null;
		String timeStamp = null;
		URI rdfType = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		GenericTopLevel t = SBOLDoc.createGenericTopLevel(
				topLevel.getIdentity(), topLevel.getType());

		if (name != null)
			t.setName(name);
		if (description != null)
			t.setDescription(description);
		// t.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			t.setAnnotations(annotations);
		return t;
	}

	private static Model parseModels(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel) {
		String name = null;
		String description = null;
		String timeStamp = null;
		URI source = null;
		URI language = null;
		URI framework = null;
		Set<URI> roles = new HashSet<URI>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Model.source)) {
				source = URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString());
			} else if (namedProperty.getName()
					.equals(Sbol2Terms.Model.language)) {
				language = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Model.framework)) {
				framework = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(Sbol2Terms.Model.roles)) {
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Model m = SBOLDoc.createModel(topLevel.getIdentity(), source, language,
				framework, roles);
		if (name != null)
			m.setName(name);
		if (description != null)
			m.setDescription(description);
		if (timeStamp != null)
			m.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			m.setAnnotations(annotations);
		return m;
	}

	private static Collection parseCollections(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel) {
		String name = null;
		String description = null;
		String timeStamp = null;
		Set<URI> members = new HashSet<URI>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName()
					.equals(Sbol2Terms.Collection.hasMembers)) {
				members.add(URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			}

			else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Collection c = SBOLDoc.createCollection(topLevel.getIdentity());
		if (!members.isEmpty())
			c.setMembers(members);
		if (name != null)
			c.setName(name);
		if (description != null)
			c.setDescription(description);
		if (timeStamp != null)
			c.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			c.setAnnotations(annotations);
		return c;
	}

	private static ModuleDefinition parseModuleDefinition(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel) {
		String name = null;
		String description = null;
		String timeStamp = null;
		Set<URI> roles = new HashSet<URI>();
		List<FunctionalComponent> functionalComponents = new ArrayList<FunctionalComponent>();
		List<Interaction> interactions = new ArrayList<Interaction>();
		Set<URI> models = new HashSet<URI>();
		List<Module> subModules = new ArrayList<Module>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(
					Sbol2Terms.ModuleDefinition.roles)) {
				roles.add(URI.create(((Literal<QName>) namedProperty.getValue())
						.getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ModuleDefinition.hasSubModule)) {
				subModules
						.add(parseSubModule(((NestedDocument<QName>) namedProperty
								.getValue())));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ModuleDefinition.hasInteractions)) {
				interactions
						.add(parseInteraction(((NestedDocument<QName>) namedProperty
								.getValue())));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ModuleDefinition.hasComponents)) {
				functionalComponents
						.add(parseFunctionalComponents((NestedDocument<QName>) namedProperty
								.getValue()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.ModuleDefinition.hasModels)) {
				models.add(URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		ModuleDefinition moduleDefinition = SBOLDoc.createModuleDefinition(
				topLevel.getIdentity(), roles);
		if (!functionalComponents.isEmpty())
			moduleDefinition.setComponents(functionalComponents);
		if (!interactions.isEmpty())
			moduleDefinition.setInteractions(interactions);
		if (!models.isEmpty())
			moduleDefinition.setModels(models);
		if (!subModules.isEmpty())
			moduleDefinition.setSubModules(subModules);
		if (name != null)
			moduleDefinition.setName(name);
		if (description != null)
			moduleDefinition.setDescription(description);
		if (timeStamp != null)
			moduleDefinition.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			moduleDefinition.setAnnotations(annotations);
		return moduleDefinition;
	}

	private static Module parseSubModule(NestedDocument<QName> module) {
		URI subModuleURI = null;
		URI definitionURI = null;
		String name = null;
		String description = null;
		String timeStamp = null;
		List<MapsTo> mappings = new ArrayList<MapsTo>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : module.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Module.hasMappings)) {
				mappings.add(parseMapping((NestedDocument<QName>) namedProperty
						.getValue()));
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Module.hasDefinition)) {
				definitionURI = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			}
			// TODO: does the UML diagram missing an instatiatedModule because
			// the constructor for
			// Module requires an instantiatedModule
			// else
			// if(namedProperty.getName().equals(Sbol2Terms.Module.hasInstantiatedModule))
			// {
			// subModuleURI =
			// URI.create(((Literal<QName>)namedProperty.getValue()).getValue().toString());
			// }
			else if (namedProperty.getName().equals(Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Module submodule = new Module(module.getIdentity(), definitionURI);
		if (!mappings.isEmpty())
			submodule.setMappings(mappings);
		// submodule.setDefinition(definitionURI);
		if (name != null)
			submodule.setName(name);
		if (description != null)
			submodule.setDescription(description);
		if (timeStamp != null)
			submodule.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			submodule.setAnnotations(annotations);
		return submodule;
	}

	private static MapsTo parseMapping(NestedDocument<QName> mappings) {
		URI remote = null;
		RefinementType refinement = null;
		URI local = null;

		for (NamedProperty<QName> m : mappings.getProperties()) {
			if (m.getName().equals(Sbol2Terms.MapsTo.refinement)) {
				refinement = RefinementType.valueOf(((Literal<QName>) m
						.getValue()).getValue().toString());
			} else if (m.getName().equals(Sbol2Terms.MapsTo.hasRemote)) {
				remote = URI.create(((Literal<QName>) m.getValue()).getValue()
						.toString());
			} else if (m.getName().equals(Sbol2Terms.MapsTo.hasLocal)) {
				local = URI.create(((Literal<QName>) m.getValue()).getValue()
						.toString());
			}
		}

		MapsTo map = new MapsTo(mappings.getIdentity(), refinement, local,
				remote);
		return map;
	}

	private static Interaction parseInteraction(
			NestedDocument<QName> interaction) {
		String name = null;
		String description = null;
		String timeStamp = null;
		Set<URI> type = new HashSet<URI>();
		List<Participation> participations = new ArrayList<Participation>();
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> i : interaction.getProperties()) {
			if (i.getName().equals(Sbol2Terms.Interaction.type)) {
				type.add(URI.create(((Literal<QName>) i.getValue()).getValue()
						.toString()));
			} else if (i.getName().equals(
					Sbol2Terms.Interaction.hasParticipations)) {
				participations.add(parseParticipation((NestedDocument<QName>) i
						.getValue()));
			} else if (i.getName().equals(Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) i.getValue()).getValue().toString();
			} else if (i.getName().equals(Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) i.getValue()).getValue()
						.toString();
			} else if (i.getName().equals(Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) i.getValue()).getValue()
						.toString();
			} else {
				annotations.add(new Annotation(i));
			}
		}

		Interaction i = new Interaction(interaction.getIdentity(), type,
				participations);
		if (name != null)
			i.setName(name);
		if (description != null)
			i.setDescription(description);
		if (timeStamp != null)
			i.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			i.setAnnotations(annotations);
		return i;
	}

	private static Participation parseParticipation(
			NestedDocument<QName> participation) {
		Set<URI> role = new HashSet<URI>();
		URI participant = null;

		for (NamedProperty<QName> p : participation.getProperties()) {
			if (p.getName().equals(Sbol2Terms.Participation.role)) {
				role.add(URI.create(((Literal<QName>) p.getValue()).getValue()
						.toString()));
			} else if (p.getName().equals(
					Sbol2Terms.Participation.hasParticipant)) {
				participant = URI.create(((Literal<QName>) p.getValue())
						.getValue().toString());
			}
		}

		Participation p = new Participation(participation.getIdentity(), role,
				participant);
		return p;
	}

	private static FunctionalComponent parseFunctionalComponents(
			NestedDocument<QName> functionalComponent) {
		String name = null;
		String description = null;
		String timeStamp = null;
		URI access = null;
		URI direction = null;
		URI functionalComponentURI = null;
		List<Annotation> annotations = new ArrayList<Annotation>();
		List<MapsTo> mappings = new ArrayList<MapsTo>();

		for (NamedProperty<QName> f : functionalComponent.getProperties()) {
			if (functionalComponent.getType().equals(
					Sbol2Terms.ComponentInstance.access)) {
				access = URI.create(((Literal<QName>) f.getValue()).getValue()
						.toString());
			} else if (f.getName().equals(
					Sbol2Terms.FunctionalComponent.direction)) {
				direction = URI.create(((Literal<QName>) f.getValue())
						.getValue().toString());
			}
			if (f.getName().equals(Sbol2Terms.ComponentInstance.hasMappings)) {
				mappings.add(parseMapping((NestedDocument<QName>) f.getValue()));
			} else if (f.getName().equals(
					Sbol2Terms.ComponentInstance.hasComponentDefinition)) {
				functionalComponentURI = URI.create(((Literal<QName>) f
						.getValue()).getValue().toString());
			} else if (f.getName().equals(Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) f.getValue()).getValue().toString();
			} else if (f.getName().equals(Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) f.getValue()).getValue()
						.toString();
			} else if (f.getName().equals(Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) f.getValue()).getValue()
						.toString();
			} else {
				annotations.add(new Annotation(f));
			}

		}

		FunctionalComponent fc = new FunctionalComponent(
				functionalComponent.getIdentity(), access,
				functionalComponentURI, direction);
		if (!mappings.isEmpty())
			fc.setMappings(mappings);
		if (name != null)
			fc.setName(name);
		if (description != null)
			fc.setDescription(description);
		if (timeStamp != null)
			fc.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			fc.setAnnotations(annotations);
		return fc;
	}

	private static Sequence parseSequences(SBOLDocument SBOLDoc,
			TopLevelDocument<QName> topLevel) {
		String name = null;
		String description = null;
		String timeStamp = null;
		String elements = null;
		URI encoding = null;
		List<Annotation> annotations = new ArrayList<Annotation>();

		for (NamedProperty<QName> namedProperty : topLevel.getProperties()) {
			if (namedProperty.getName().equals(Sbol2Terms.Sequence.elements)) {
				elements = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Sequence.encoding)) {
				encoding = URI.create(((Literal<QName>) namedProperty
						.getValue()).getValue().toString());
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.name)) {
				name = ((Literal<QName>) namedProperty.getValue()).getValue()
						.toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Documented.description)) {
				description = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else if (namedProperty.getName().equals(
					Sbol2Terms.Identified.timeStamp)) {
				timeStamp = ((Literal<QName>) namedProperty.getValue())
						.getValue().toString();
			} else {
				annotations.add(new Annotation(namedProperty));
			}
		}

		Sequence sequence = SBOLDoc.createSequence(topLevel.getIdentity(),
				elements, encoding);
		if (name != null)
			sequence.setName(name);
		if (description != null)
			sequence.setDescription(description);
		if (timeStamp != null)
			sequence.setTimeStamp(getTimestamp(timeStamp));
		if (!annotations.isEmpty())
			sequence.setAnnotations(annotations);
		return sequence;
	}

	private static Timestamp getTimestamp(String timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date date = null;
		try {
			date = sdf.parse(timeStamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}

}
