// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLReader.java
#include <org/sbolstandard/core2/SBOLReader.hpp>

#include <java/io/BufferedInputStream.hpp>
#include <java/io/File.hpp>
#include <java/io/FileInputStream.hpp>
#include <java/io/IOException.hpp>
#include <java/io/InputStream.hpp>
#include <java/io/PrintStream.hpp>
#include <java/io/Reader.hpp>
#include <java/io/StringReader.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/CharSequence.hpp>
#include <java/lang/Character.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/Exception.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/Integer.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/lang/System.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/Collections.hpp>
#include <java/util/HashMap.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Map.hpp>
#include <java/util/Scanner.hpp>
#include <java/util/Set.hpp>
#include <javax/json/Json.hpp>
#include <javax/json/JsonReader.hpp>
#include <javax/json/JsonReaderFactory.hpp>
#include <javax/xml/namespace_/QName.hpp>
#include <javax/xml/stream/XMLInputFactory.hpp>
#include <javax/xml/stream/XMLStreamReader.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/Annotation.hpp>
#include <org/sbolstandard/core2/Collection.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/Cut.hpp>
#include <org/sbolstandard/core2/DirectionType.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/GenericLocation.hpp>
#include <org/sbolstandard/core2/GenericTopLevel.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Interaction.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/MapsTo.hpp>
#include <org/sbolstandard/core2/Model.hpp>
#include <org/sbolstandard/core2/Module.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/OrientationType.hpp>
#include <org/sbolstandard/core2/Participation.hpp>
#include <org/sbolstandard/core2/Range.hpp>
#include <org/sbolstandard/core2/RefinementType.hpp>
#include <org/sbolstandard/core2/RestrictionType.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLReader_SBOLPair.hpp>
#include <org/sbolstandard/core2/SBOLValidate.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/Sbol1Terms_Collection.hpp>
#include <org/sbolstandard/core2/Sbol1Terms_DNAComponent.hpp>
#include <org/sbolstandard/core2/Sbol1Terms_DNASequence.hpp>
#include <org/sbolstandard/core2/Sbol1Terms_SequenceAnnotations.hpp>
#include <org/sbolstandard/core2/Sbol1Terms.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Collection.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Component.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_ComponentDefinition.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_ComponentInstance.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Cut.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_FunctionalComponent.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_GenericLocation.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Identified.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Interaction.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Location.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_MapsTo.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Model.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Module.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_ModuleDefinition.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Participation.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Range.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Sequence.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_SequenceAnnotation.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_SequenceConstraint.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_SequenceURI.hpp>
#include <org/sbolstandard/core2/Sbol2Terms.hpp>
#include <org/sbolstandard/core2/Sequence.hpp>
#include <org/sbolstandard/core2/SequenceAnnotation.hpp>
#include <org/sbolstandard/core2/SequenceConstraint.hpp>
#include <org/sbolstandard/core2/SequenceOntology.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <uk/ac/intbio/core/io/turtle/TurtleIo.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>
#include <uk/ac/ncl/intbio/core/datatree/DocumentRoot.hpp>
#include <uk/ac/ncl/intbio/core/datatree/IdentifiableDocument.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NameTransformer.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamedProperty.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NestedDocument.hpp>
#include <uk/ac/ncl/intbio/core/datatree/PropertyValue.hpp>
#include <uk/ac/ncl/intbio/core/datatree/TopLevelDocument.hpp>
#include <uk/ac/ncl/intbio/core/io/IoReader.hpp>
#include <uk/ac/ncl/intbio/core/io/json/JsonIo.hpp>
#include <uk/ac/ncl/intbio/core/io/json/StringifyQName.hpp>
#include <uk/ac/ncl/intbio/core/io/rdf/RdfIo.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::Identified, ::java::lang::ObjectArray > IdentifiedArray;
        } // core2
    } // sbolstandard
} // org

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::SBOLReader::SBOLReader(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLReader::SBOLReader()
    : SBOLReader(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::String*& org::sbolstandard::core2::SBOLReader::URIPrefix()
{
    clinit();
    return URIPrefix_;
}
java::lang::String* org::sbolstandard::core2::SBOLReader::URIPrefix_;

java::lang::String*& org::sbolstandard::core2::SBOLReader::version()
{
    clinit();
    return version_;
}
java::lang::String* org::sbolstandard::core2::SBOLReader::version_;

bool& org::sbolstandard::core2::SBOLReader::typesInURI()
{
    clinit();
    return typesInURI_;
}
bool org::sbolstandard::core2::SBOLReader::typesInURI_;

void org::sbolstandard::core2::SBOLReader::setURIPrefix(::java::lang::String* URIprefix)
{
    clinit();
    SBOLReader::URIPrefix_ = URIprefix;
}

void org::sbolstandard::core2::SBOLReader::unsetURIPrefix()
{
    clinit();
    SBOLReader::URIPrefix_ = nullptr;
}

void org::sbolstandard::core2::SBOLReader::setVersion(::java::lang::String* version)
{
    clinit();
    SBOLReader::version_ = version;
}

void org::sbolstandard::core2::SBOLReader::setTypesInURI(bool typesInURI)
{
    clinit();
    SBOLReader::typesInURI_ = typesInURI;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::read(::java::lang::String* fileName) /* throws(Throwable) */
{
    clinit();
    return readRDF(new ::java::io::File(fileName));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readJSON(::java::lang::String* fileName) /* throws(Throwable) */
{
    clinit();
    return readJSON(new ::java::io::File(fileName));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readRDF(::java::lang::String* fileName) /* throws(Throwable) */
{
    clinit();
    return readRDF(new ::java::io::File(fileName));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readTurtle(::java::lang::String* fileName) /* throws(Throwable) */
{
    clinit();
    return readTurtle(new ::java::io::File(fileName));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readJSON(::java::io::File* file) /* throws(Throwable) */
{
    clinit();
    auto stream = new ::java::io::FileInputStream(file);
    auto buffer = new ::java::io::BufferedInputStream(stream);
    return readJSON(static_cast< ::java::io::InputStream* >(buffer));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::read(::java::io::File* file) /* throws(Throwable) */
{
    clinit();
    auto stream = new ::java::io::FileInputStream(file);
    auto buffer = new ::java::io::BufferedInputStream(stream);
    return read(static_cast< ::java::io::InputStream* >(buffer));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readRDF(::java::io::File* file) /* throws(Throwable) */
{
    clinit();
    auto stream = new ::java::io::FileInputStream(file);
    auto buffer = new ::java::io::BufferedInputStream(stream);
    return readRDF(static_cast< ::java::io::InputStream* >(buffer));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readTurtle(::java::io::File* file) /* throws(Throwable) */
{
    clinit();
    auto stream = new ::java::io::FileInputStream(file);
    auto buffer = new ::java::io::BufferedInputStream(stream);
    return readTurtle(static_cast< ::java::io::InputStream* >(buffer));
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readJSON(::java::io::InputStream* in) /* throws(Exception) */
{
    clinit();
    auto scanner = new ::java::util::Scanner(in, u"UTF-8"_j);
    auto inputStreamString = npc(npc(scanner)->useDelimiter(u"\\A"_j))->next();
    auto SBOLDoc = new SBOLDocument();
    try {
        auto document = readJSON(static_cast< ::java::io::Reader* >(new ::java::io::StringReader(inputStreamString)));
        for (auto _i = npc(npc(document)->getNamespaceBindings())->iterator(); _i->hasNext(); ) {
            ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
            {
                if(npc(npc(n)->getNamespaceURI())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::sbol1())->getNamespaceURI()))) {
                    npc(scanner)->close();
                    return readV1(document);
                }
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(n)->getNamespaceURI(), npc(n)->getPrefix()));
            }
        }
        readTopLevelDocs(SBOLDoc, document);
    } catch (::java::io::IOException* e) {
        npc(scanner)->close();
        npc(e)->printStackTrace();
    }
    npc(scanner)->close();
    try {
        SBOLValidate::validateCompliance(SBOLDoc);
    } catch (SBOLValidationException* e) {
        npc(SBOLDoc)->setCompliant(false);
    }
    return SBOLDoc;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::read(::java::io::InputStream* in)
{
    clinit();
    auto scanner = new ::java::util::Scanner(in, u"UTF-8"_j);
    auto inputStreamString = npc(npc(scanner)->useDelimiter(u"\\A"_j))->next();
    auto SBOLDoc = new SBOLDocument();
    try {
        auto document = readRDF(static_cast< ::java::io::Reader* >(new ::java::io::StringReader(inputStreamString)));
        for (auto _i = npc(npc(document)->getNamespaceBindings())->iterator(); _i->hasNext(); ) {
            ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
            {
                if(npc(npc(n)->getNamespaceURI())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::sbol1())->getNamespaceURI()))) {
                    npc(scanner)->close();
                    return readV1(document);
                }
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(n)->getNamespaceURI(), npc(n)->getPrefix()));
            }
        }
        readTopLevelDocs(SBOLDoc, document);
    } catch (::java::lang::Exception* e) {
        npc(scanner)->close();
        npc(e)->printStackTrace();
    }
    npc(scanner)->close();
    return SBOLDoc;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readRDF(::java::io::InputStream* in) /* throws(Exception) */
{
    clinit();
    auto scanner = new ::java::util::Scanner(in, u"UTF-8"_j);
    auto inputStreamString = npc(npc(scanner)->useDelimiter(u"\\A"_j))->next();
    auto SBOLDoc = new SBOLDocument();
    try {
        auto document = readRDF(static_cast< ::java::io::Reader* >(new ::java::io::StringReader(inputStreamString)));
        for (auto _i = npc(npc(document)->getNamespaceBindings())->iterator(); _i->hasNext(); ) {
            ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
            {
                if(npc(npc(n)->getNamespaceURI())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::sbol1())->getNamespaceURI()))) {
                    npc(scanner)->close();
                    return readV1(document);
                }
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(n)->getNamespaceURI(), npc(n)->getPrefix()));
            }
        }
        readTopLevelDocs(SBOLDoc, document);
    } catch (::java::io::IOException* e) {
        npc(scanner)->close();
        npc(e)->printStackTrace();
    }
    npc(scanner)->close();
    try {
        SBOLValidate::validateCompliance(SBOLDoc);
    } catch (SBOLValidationException* e) {
        npc(SBOLDoc)->setCompliant(false);
    }
    return SBOLDoc;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readTurtle(::java::io::InputStream* in) /* throws(Exception) */
{
    clinit();
    auto scanner = new ::java::util::Scanner(in, u"UTF-8"_j);
    auto inputStreamString = npc(npc(scanner)->useDelimiter(u"\\A"_j))->next();
    auto SBOLDoc = new SBOLDocument();
    try {
        auto document = readTurtle(static_cast< ::java::io::Reader* >(new ::java::io::StringReader(inputStreamString)));
        for (auto _i = npc(npc(document)->getNamespaceBindings())->iterator(); _i->hasNext(); ) {
            ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
            {
                if(npc(npc(n)->getNamespaceURI())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::sbol1())->getNamespaceURI()))) {
                    npc(scanner)->close();
                    return readV1(document);
                }
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(n)->getNamespaceURI(), npc(n)->getPrefix()));
            }
        }
        readTopLevelDocs(SBOLDoc, document);
    } catch (::java::io::IOException* e) {
        npc(scanner)->close();
        npc(e)->printStackTrace();
    }
    npc(scanner)->close();
    try {
        SBOLValidate::validateCompliance(SBOLDoc);
    } catch (SBOLValidationException* e) {
        npc(SBOLDoc)->setCompliant(false);
    }
    return SBOLDoc;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLReader::readV1(::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document)
{
    clinit();
    auto SBOLDoc = new SBOLDocument();
    for (auto _i = npc(npc(document)->getNamespaceBindings())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
        {
            if(npc(npc(n)->getNamespaceURI())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::sbol1())->getNamespaceURI()))) {
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(Sbol2Terms::sbol2())->getNamespaceURI(), npc(Sbol2Terms::sbol2())->getPrefix()));
            } else {
                npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(n)->getNamespaceURI(), npc(n)->getPrefix()));
            }
        }
    }
    npc(SBOLDoc)->addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(Sbol2Terms::prov())->getNamespaceURI(), npc(Sbol2Terms::prov())->getPrefix()));
    readTopLevelDocsV1(SBOLDoc, document);
    try {
        SBOLValidate::validateCompliance(SBOLDoc);
    } catch (SBOLValidationException* e) {
        npc(SBOLDoc)->setCompliant(false);
    }
    return SBOLDoc;
}

uk::ac::ncl::intbio::core::datatree::DocumentRoot* org::sbolstandard::core2::SBOLReader::readJSON(::java::io::Reader* stream) /* throws(Exception) */
{
    clinit();
    auto reader = npc(::javax::json::Json::createReaderFactory(::java::util::Collections::/* <String, Object> */emptyMap()))->createReader(stream);
    auto jsonIo = new ::uk::ac::ncl::intbio::core::io::json::JsonIo();
    auto ioReader = npc(jsonIo)->createIoReader(npc(reader)->read());
    auto root = npc(ioReader)->read();
    return npc(::uk::ac::ncl::intbio::core::io::json::StringifyQName::string2qname())->mapDR(root);
}

uk::ac::ncl::intbio::core::datatree::DocumentRoot* org::sbolstandard::core2::SBOLReader::readRDF(::java::io::Reader* reader) /* throws(Exception) */
{
    clinit();
    auto xmlReader = npc(::javax::xml::stream::XMLInputFactory::newInstance())->createXMLStreamReader(reader);
    auto rdfIo = new ::uk::ac::ncl::intbio::core::io::rdf::RdfIo();
    return npc(npc(rdfIo)->createIoReader(xmlReader))->read();
}

uk::ac::ncl::intbio::core::datatree::DocumentRoot* org::sbolstandard::core2::SBOLReader::readTurtle(::java::io::Reader* reader) /* throws(Exception) */
{
    clinit();
    auto turtleIo = new ::uk::ac::intbio::core::io::turtle::TurtleIo();
    return npc(npc(turtleIo)->createIoReader(reader))->read();
}

void org::sbolstandard::core2::SBOLReader::readTopLevelDocsV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document)
{
    clinit();
    for (auto _i = npc(npc(document)->getTopLevelDocuments())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel = java_cast< ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::DNAComponent_())))
                parseDnaComponentV1(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNASequence::DNASequence_())))
                parseDnaSequenceV1(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_Collection::Collection_())))
                parseCollectionV1(SBOLDoc, topLevel);
            else {
                parseGenericTopLevel(SBOLDoc, topLevel);
            }
        }
    }
}

void org::sbolstandard::core2::SBOLReader::readTopLevelDocs(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document)
{
    clinit();
    for (auto _i = npc(npc(document)->getTopLevelDocuments())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel = java_cast< ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Collection::Collection_())))
                parseCollections(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::ModuleDefinition_())))
                parseModuleDefinition(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::Model_())))
                parseModels(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Sequence::Sequence_())))
                parseSequences(SBOLDoc, topLevel);
            else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::ComponentDefinition_())))
                parseComponentDefinitions(SBOLDoc, topLevel);
            else
                parseGenericTopLevel(SBOLDoc, topLevel);
        }
    }
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLReader::parseDnaComponentV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* componentDef)
{
    clinit();
    ::java::lang::String* displayId = nullptr;
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    ::java::net::URI* seq_identity = nullptr;
    ::java::util::Set* roles = new ::java::util::HashSet();
    auto identity = npc(componentDef)->getIdentity();
    auto persIdentity = u""_j;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    ::java::util::List* sequenceAnnotations = new ::java::util::ArrayList();
    ::java::util::List* components = new ::java::util::ArrayList();
    ::java::util::List* sequenceConstraints = new ::java::util::ArrayList();
    ::java::util::List* precedePairs = new ::java::util::ArrayList();
    ::java::util::Map* componentDefMap = new ::java::util::HashMap();
    ::java::util::Set* type = new ::java::util::HashSet();
    npc(type)->add(static_cast< ::java::lang::Object* >(ComponentDefinition::DNA()));
    auto component_num = int32_t(0);
    auto sa_num = int32_t(0);
    if(URIPrefix_ != nullptr) {
        displayId = findDisplayId(npc(npc(componentDef)->getIdentity())->toString());
        identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, version_, typesInURI_);
        persIdentity = npc(URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, u""_j, typesInURI_))->toString();
    }
    for (auto _i = npc(npc(componentDef)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                displayId = fixDisplayId(displayId);
                if(URIPrefix_ != nullptr) {
                    persIdentity = npc(URIcompliance::createCompliantURI(URIPrefix_, TopLevel::COMPONENT_DEFINITION(), displayId, u""_j, typesInURI_))->toString();
                    identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::COMPONENT_DEFINITION(), displayId, version_, typesInURI_);
                }
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::name()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::type()))) {
                auto convertedSO = SequenceOntology::convertSeqOntologyV1(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
                npc(roles)->add(static_cast< ::java::lang::Object* >(convertedSO));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::annotations()))) {
                auto sa = parseSequenceAnnotationV1(SBOLDoc, (java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())), precedePairs, persIdentity, ++sa_num);
                npc(sequenceAnnotations)->add(static_cast< ::java::lang::Object* >(sa));
                auto component_identity = URIcompliance::createCompliantURI(persIdentity, ::java::lang::StringBuilder().append(u"component"_j)->append(component_num)->toString(), version_);
                auto access = AccessType::PUBLIC;
                auto instantiatedComponent = npc(sa)->getComponentURI();
                auto originalURI = npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))->getIdentity();
                npc(componentDefMap)->put(originalURI, component_identity);
                npc(sa)->setComponent(component_identity);
                auto component = new Component(component_identity, access, instantiatedComponent);
                if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
                    npc(component)->setPersistentIdentity(URIcompliance::createCompliantURI(persIdentity, ::java::lang::StringBuilder().append(u"component"_j)->append(component_num)->toString(), u""_j));
                    npc(component)->setDisplayId(::java::lang::StringBuilder().append(u"component"_j)->append(component_num)->toString());
                    npc(component)->setVersion(version_);
                }
                component_num++;
                npc(components)->add(static_cast< ::java::lang::Object* >(component));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNAComponent::dnaSequence()))) {
                seq_identity = npc(parseDnaSequenceV1(SBOLDoc, java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))->getIdentity();
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    if(npc(roles)->isEmpty())
        npc(roles)->add(static_cast< ::java::lang::Object* >(SequenceOntology::ENGINEERED_REGION()));

    auto sc_number = int32_t(0);
    for (auto _i = npc(precedePairs)->iterator(); _i->hasNext(); ) {
        SBOLReader_SBOLPair* pair = java_cast< SBOLReader_SBOLPair* >(_i->next());
        {
            auto sc_identity = URIcompliance::createCompliantURI(persIdentity, ::java::lang::StringBuilder().append(u"sequenceConstraint"_j)->append(++sc_number)->toString(), version_);
            auto restrictionURI = RestrictionType::convertToURI(RestrictionType::PRECEDES);
            ::java::net::URI* subject = nullptr;
            ::java::net::URI* object = nullptr;
            for (auto _i = npc(npc(componentDefMap)->keySet())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* key = java_cast< ::java::net::URI* >(_i->next());
                {
                    if(npc(npc(pair)->getLeft())->equals(static_cast< ::java::lang::Object* >(key))) {
                        subject = java_cast< ::java::net::URI* >(npc(componentDefMap)->get(key));
                    } else if(npc(npc(pair)->getRight())->equals(static_cast< ::java::lang::Object* >(key))) {
                        object = java_cast< ::java::net::URI* >(npc(componentDefMap)->get(key));
                    }
                }
            }
            auto sc = new SequenceConstraint(sc_identity, restrictionURI, subject, object);
            if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
                npc(sc)->setPersistentIdentity(URIcompliance::createCompliantURI(persIdentity, ::java::lang::StringBuilder().append(u"sequenceConstraint"_j)->append(sc_number)->toString(), version_));
                npc(sc)->setDisplayId(::java::lang::StringBuilder().append(u"sequenceConstraint"_j)->append(sc_number)->toString());
                npc(sc)->setVersion(version_);
            }
            npc(sequenceConstraints)->add(static_cast< ::java::lang::Object* >(sc));
        }
    }
    auto c = npc(SBOLDoc)->getComponentDefinition(identity);
    if(c == nullptr) {
        c = npc(SBOLDoc)->createComponentDefinition(identity, type);
        if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            npc(c)->setPersistentIdentity(::java::net::URI::create(persIdentity));
            npc(c)->setVersion(version_);
        }
        if(roles != nullptr)
            npc(c)->setRoles(roles);

        if(identity != npc(componentDef)->getIdentity())
            npc(c)->setWasDerivedFrom(npc(componentDef)->getIdentity());

        if(displayId != nullptr)
            npc(c)->setDisplayId(displayId);

        if(name != nullptr && !npc(name)->isEmpty())
            npc(c)->setName(name);

        if(description != nullptr && !npc(description)->isEmpty())
            npc(c)->setDescription(description);

        if(seq_identity != nullptr)
            npc(c)->addSequence(seq_identity);

        if(!npc(annotations)->isEmpty())
            npc(c)->setAnnotations(annotations);

        if(!npc(sequenceAnnotations)->isEmpty())
            npc(c)->setSequenceAnnotations(sequenceAnnotations);

        if(!npc(components)->isEmpty())
            npc(c)->setComponents(components);

        if(!npc(sequenceConstraints)->isEmpty())
            npc(c)->setSequenceConstraints(sequenceConstraints);

    }
    return c;
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLReader::parseDnaSequenceV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* topLevel)
{
    clinit();
    ::java::lang::String* elements = nullptr;
    ::java::lang::String* displayId = nullptr;
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto identity = npc(topLevel)->getIdentity();
    ::java::net::URI* persistentIdentity = nullptr;
    auto encoding = Sbol2Terms_SequenceURI::DnaSequenceV1();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(URIPrefix_ != nullptr) {
        displayId = findDisplayId(npc(npc(topLevel)->getIdentity())->toString());
        identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, version_, typesInURI_);
        persistentIdentity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, u""_j, typesInURI_);
    }
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_DNASequence::nucleotides()))) {
                elements = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                if(URIPrefix_ != nullptr) {
                    identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, version_, typesInURI_);
                }
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto sequence = npc(SBOLDoc)->getSequence(identity);
    if(sequence == nullptr) {
        sequence = npc(SBOLDoc)->createSequence(identity, elements, encoding);
        if(persistentIdentity != nullptr) {
            npc(sequence)->setPersistentIdentity(persistentIdentity);
            npc(sequence)->setVersion(version_);
        }
        if(identity != npc(topLevel)->getIdentity())
            npc(sequence)->setWasDerivedFrom(npc(topLevel)->getIdentity());

        if(displayId != nullptr)
            npc(sequence)->setDisplayId(displayId);

        if(name != nullptr)
            npc(sequence)->setName(name);

        if(description != nullptr)
            npc(sequence)->setDescription(description);

        if(!npc(annotations)->isEmpty())
            npc(sequence)->setAnnotations(annotations);

    }
    return sequence;
}

java::lang::String* org::sbolstandard::core2::SBOLReader::fixDisplayId(::java::lang::String* displayId)
{
    clinit();
    displayId = npc(displayId)->replaceAll(u"[^a-zA-Z0-9_]"_j, u"_"_j);
    displayId = npc(displayId)->replace(static_cast< ::java::lang::CharSequence* >(u" "_j), static_cast< ::java::lang::CharSequence* >(u"_"_j));
    if(::java::lang::Character::isDigit(npc(displayId)->charAt(int32_t(0)))) {
        displayId = ::java::lang::StringBuilder().append(u"_"_j)->append(displayId)->toString();
    }
    return displayId;
}

java::lang::String* org::sbolstandard::core2::SBOLReader::findDisplayId(::java::lang::String* topLevelIdentity)
{
    clinit();
    ::java::lang::String* displayId = nullptr;
    topLevelIdentity = npc(topLevelIdentity)->trim();
    while (npc(topLevelIdentity)->endsWith(u"/"_j) || npc(topLevelIdentity)->endsWith(u"#"_j) || npc(topLevelIdentity)->endsWith(u":"_j)) {
        topLevelIdentity = npc(topLevelIdentity)->replaceAll(u"/$"_j, u""_j);
        topLevelIdentity = npc(topLevelIdentity)->replaceAll(u"#$"_j, u""_j);
        topLevelIdentity = npc(topLevelIdentity)->replaceAll(u":$"_j, u""_j);
    }
    auto slash = npc(topLevelIdentity)->lastIndexOf(static_cast< int32_t >(u'/'));
    auto pound = npc(topLevelIdentity)->lastIndexOf(static_cast< int32_t >(u'#'));
    auto colon = npc(topLevelIdentity)->lastIndexOf(static_cast< int32_t >(u':'));
    if(slash != -int32_t(1) && slash > pound && slash > colon) {
        displayId = npc(topLevelIdentity)->substring(slash + int32_t(1));
    } else if(pound != -int32_t(1) && pound > colon) {
        displayId = npc(topLevelIdentity)->substring(pound + int32_t(1));
    } else if(colon != -int32_t(1)) {
        displayId = npc(topLevelIdentity)->substring(colon + int32_t(1));
    } else {
        displayId = npc(topLevelIdentity)->toString();
    }
    displayId = fixDisplayId(displayId);
    return displayId;
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLReader::parseCollectionV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* topLevel)
{
    clinit();
    auto identity = npc(topLevel)->getIdentity();
    ::java::net::URI* persistentIdentity = nullptr;
    ::java::lang::String* displayId = nullptr;
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    ::java::util::Set* members = new ::java::util::HashSet();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(URIPrefix_ != nullptr) {
        displayId = findDisplayId(npc(npc(topLevel)->getIdentity())->toString());
        identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, version_, typesInURI_);
        persistentIdentity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::SEQUENCE(), displayId, u""_j, typesInURI_);
    }
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_Collection::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                displayId = fixDisplayId(displayId);
                if(URIPrefix_ != nullptr) {
                    identity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::COLLECTION(), displayId, version_, typesInURI_);
                    persistentIdentity = URIcompliance::createCompliantURI(URIPrefix_, TopLevel::COLLECTION(), displayId, u""_j, typesInURI_);
                }
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_Collection::name()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_Collection::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_Collection::component()))) {
                npc(members)->add(static_cast< ::java::lang::Object* >(npc(parseDnaComponentV1(SBOLDoc, java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))->getIdentity()));
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto c = npc(SBOLDoc)->createCollection(identity);
    if(persistentIdentity != nullptr) {
        npc(c)->setPersistentIdentity(persistentIdentity);
        npc(c)->setVersion(version_);
    }
    if(identity != npc(topLevel)->getIdentity())
        npc(c)->setWasDerivedFrom(npc(topLevel)->getIdentity());

    if(displayId != nullptr)
        npc(c)->setDisplayId(displayId);

    if(name != nullptr)
        npc(c)->setName(name);

    if(description != nullptr)
        npc(c)->setDescription(description);

    if(!npc(members)->isEmpty())
        npc(c)->setMembers(members);

    if(!npc(annotations)->isEmpty())
        npc(c)->setAnnotations(annotations);

    return c;
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::SBOLReader::parseSequenceAnnotationV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceAnnotation, ::java::util::List* precedePairs, ::java::lang::String* parentURI, int32_t sa_num)
{
    clinit();
    ::java::lang::Integer* start = nullptr;
    ::java::lang::Integer* end = nullptr;
    ::java::lang::String* strand = nullptr;
    ::java::net::URI* componentURI = nullptr;
    auto identity = npc(sequenceAnnotation)->getIdentity();
    auto persIdentity = u""_j;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(URIPrefix_ != nullptr) {
        persIdentity = npc(URIcompliance::createCompliantURI(parentURI, ::java::lang::StringBuilder().append(u"annotation"_j)->append(sa_num)->toString(), u""_j))->toString();
        identity = URIcompliance::createCompliantURI(parentURI, ::java::lang::StringBuilder().append(u"annotation"_j)->append(sa_num)->toString(), version_);
    }
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(sequenceAnnotation)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::SequenceAnnotation()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be"_j)->append(npc(Sbol1Terms_SequenceAnnotations::SequenceAnnotation())->toString())->toString());
    }
    for (auto _i = npc(npc(sequenceAnnotation)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::bioStart()))) {
                auto temp = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                start = ::java::lang::Integer::valueOf(::java::lang::Integer::parseInt(temp));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::bioEnd()))) {
                auto temp2 = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                end = ::java::lang::Integer::valueOf(::java::lang::Integer::parseInt(temp2));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::strand()))) {
                strand = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::subComponent()))) {
                componentURI = npc(parseDnaComponentV1(SBOLDoc, java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))->getIdentity();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol1Terms_SequenceAnnotations::precedes()))) {
                auto left = npc(sequenceAnnotation)->getIdentity();
                auto right = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
                auto pair = new SBOLReader_SBOLPair(left, right);
                npc(precedePairs)->add(static_cast< ::java::lang::Object* >(pair));
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    Location* location = nullptr;
    if(start != nullptr && end != nullptr) {
        auto range_identity = URIcompliance::createCompliantURI(persIdentity, u"range"_j, version_);
        location = new Range(range_identity, (npc(start))->intValue(), (npc(end))->intValue());
        if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            npc(location)->setPersistentIdentity(URIcompliance::createCompliantURI(persIdentity, u"range"_j, u""_j));
            npc(location)->setDisplayId(u"range"_j);
            npc(location)->setVersion(version_);
        }
        if(strand != nullptr) {
            if(npc(strand)->equals(static_cast< ::java::lang::Object* >(u"+"_j))) {
                npc(location)->setOrientation(OrientationType::convertToOrientationType(OrientationType::inline_()));
            } else if(npc(strand)->equals(static_cast< ::java::lang::Object* >(u"-"_j))) {
                npc(location)->setOrientation(OrientationType::convertToOrientationType(OrientationType::reverseComplement()));
            }
        }
    } else {
        auto dummyGenericLoc_id = URIcompliance::createCompliantURI(persIdentity, u"genericLocation"_j, version_);
        location = new GenericLocation(dummyGenericLoc_id);
        if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            npc(location)->setPersistentIdentity(URIcompliance::createCompliantURI(persIdentity, u"genericLocation"_j, u""_j));
            npc(location)->setDisplayId(u"genericLocation"_j);
            npc(location)->setVersion(version_);
        }
        if(strand != nullptr) {
            if(npc(strand)->equals(static_cast< ::java::lang::Object* >(u"+"_j))) {
                npc(location)->setOrientation(OrientationType::convertToOrientationType(OrientationType::inline_()));
            } else if(npc(strand)->equals(static_cast< ::java::lang::Object* >(u"-"_j))) {
                npc(location)->setOrientation(OrientationType::convertToOrientationType(OrientationType::reverseComplement()));
            }
        }
    }
    ::java::util::List* locations = new ::java::util::ArrayList();
    npc(locations)->add(static_cast< ::java::lang::Object* >(location));
    auto s = new SequenceAnnotation(identity, locations);
    if(!npc(persIdentity)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
        npc(s)->setPersistentIdentity(::java::net::URI::create(persIdentity));
        npc(s)->setDisplayId(::java::lang::StringBuilder().append(u"annotation"_j)->append(sa_num)->toString());
        npc(s)->setVersion(version_);
    }
    if(identity != npc(sequenceAnnotation)->getIdentity())
        npc(s)->setWasDerivedFrom(npc(sequenceAnnotation)->getIdentity());

    if(componentURI != nullptr)
        npc(s)->setComponent(componentURI);

    if(!npc(annotations)->isEmpty())
        npc(s)->setAnnotations(annotations);

    return s;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLReader::parseComponentDefinitions(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::net::URI* structure = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::Set* type = new ::java::util::HashSet();
    ::java::util::Set* roles = new ::java::util::HashSet();
    ::java::util::List* components = new ::java::util::ArrayList();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    ::java::util::List* sequenceAnnotations = new ::java::util::ArrayList();
    ::java::util::List* sequenceConstraints = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::type()))) {
                npc(type)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::roles()))) {
                npc(roles)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasComponent()))) {
                npc(components)->add(static_cast< ::java::lang::Object* >(parseComponent((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSubComponent()))) {
                npc(::java::lang::System::out())->println(u"Warning: tag should be sbol:component, not sbol:subComponent."_j);
                npc(components)->add(static_cast< ::java::lang::Object* >(parseComponent((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequence()))) {
                structure = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequenceAnnotations()))) {
                npc(sequenceAnnotations)->add(static_cast< ::java::lang::Object* >(parseSequenceAnnotation(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequenceConstraints()))) {
                npc(sequenceConstraints)->add(static_cast< ::java::lang::Object* >(parseSequenceConstraint((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto c = npc(SBOLDoc)->createComponentDefinition(npc(topLevel)->getIdentity(), type);
    if(roles != nullptr)
        npc(c)->setRoles(roles);

    if(displayId != nullptr)
        npc(c)->setDisplayId(displayId);

    if(persistentIdentity != nullptr)
        npc(c)->setPersistentIdentity(persistentIdentity);

    if(structure != nullptr)
        npc(c)->addSequence(structure);

    if(!npc(components)->isEmpty())
        npc(c)->setComponents(components);

    if(!npc(sequenceAnnotations)->isEmpty())
        npc(c)->setSequenceAnnotations(sequenceAnnotations);

    if(!npc(sequenceConstraints)->isEmpty())
        npc(c)->setSequenceConstraints(sequenceConstraints);

    if(name != nullptr)
        npc(c)->setName(name);

    if(description != nullptr)
        npc(c)->setDescription(description);

    if(!npc(annotations)->isEmpty())
        npc(c)->setAnnotations(annotations);

    if(version != nullptr)
        npc(c)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(c)->setWasDerivedFrom(wasDerivedFrom);

    return c;
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::SBOLReader::parseSequenceConstraint(::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceConstraint)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(sequenceConstraint)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(sequenceConstraint)->getIdentity()));
    ::java::net::URI* restriction = nullptr;
    ::java::net::URI* subject = nullptr;
    ::java::net::URI* object = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(sequenceConstraint)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::SequenceConstraint_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(sequenceConstraint)->getType())))->append(u" is not a valid sequence constraint."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(sequenceConstraint)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::restriction()))) {
                restriction = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::hasSubject()))) {
                subject = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::hasObject()))) {
                object = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto s = new SequenceConstraint(npc(sequenceConstraint)->getIdentity(), restriction, subject, object);
    if(displayId != nullptr)
        npc(s)->setDisplayId(displayId);

    if(name != nullptr)
        npc(s)->setName(name);

    if(description != nullptr)
        npc(s)->setDescription(description);

    if(persistentIdentity != nullptr)
        npc(s)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(s)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(s)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(s)->setAnnotations(annotations);

    return s;
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::SBOLReader::parseSequenceAnnotation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceAnnotation)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(sequenceAnnotation)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(sequenceAnnotation)->getIdentity()));
    Location* location = nullptr;
    ::java::net::URI* componentURI = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* locations = new ::java::util::ArrayList();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(sequenceAnnotation)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceAnnotation::SequenceAnnotation_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(sequenceAnnotation)->getType())))->append(u" is not a valid sequence annotation."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(sequenceAnnotation)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Location::Location_()))) {
                location = parseLocation(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()));
                npc(locations)->add(static_cast< ::java::lang::Object* >(location));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceAnnotation::hasComponent()))) {
                componentURI = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto s = new SequenceAnnotation(npc(sequenceAnnotation)->getIdentity(), locations);
    if(persistentIdentity != nullptr)
        npc(s)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(s)->setVersion(version);

    if(displayId != nullptr)
        npc(s)->setDisplayId(displayId);

    if(componentURI != nullptr)
        npc(s)->setComponent(componentURI);

    if(name != nullptr)
        npc(s)->setName(name);

    if(description != nullptr)
        npc(s)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(s)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(s)->setAnnotations(annotations);

    return s;
}

org::sbolstandard::core2::Location* org::sbolstandard::core2::SBOLReader::parseLocation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* location)
{
    clinit();
    Location* l = nullptr;
    if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(location)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::Range_()))) {
        l = parseRange(location);
    } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(location)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::Cut_()))) {
        l = parseCut(location);
    } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(location)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_GenericLocation::GenericLocation_()))) {
        l = parseGenericLocation(location);
    } else {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(location)->getType())))->append(u" is not a valid location type."_j)->toString(), new IdentifiedArray());
    }
    return l;
}

org::sbolstandard::core2::GenericLocation* org::sbolstandard::core2::SBOLReader::parseGenericLocation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeGenLoc)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(typeGenLoc)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(typeGenLoc)->getIdentity()));
    ::java::net::URI* orientation = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(typeGenLoc)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_GenericLocation::GenericLocation_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be"_j)->append(npc(Sbol2Terms_GenericLocation::GenericLocation_())->toString())->toString());
    }
    for (auto _i = npc(npc(typeGenLoc)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_GenericLocation::orientation()))) {
                orientation = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto gl = new GenericLocation(npc(typeGenLoc)->getIdentity());
    if(displayId != nullptr)
        npc(gl)->setDisplayId(displayId);

    if(name != nullptr)
        npc(gl)->setName(name);

    if(description != nullptr)
        npc(gl)->setDescription(description);

    if(orientation != nullptr)
        npc(gl)->setOrientation(OrientationType::convertToOrientationType(orientation));

    if(persistentIdentity != nullptr)
        npc(gl)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(gl)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(gl)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(gl)->setAnnotations(annotations);

    return gl;
}

org::sbolstandard::core2::Cut* org::sbolstandard::core2::SBOLReader::parseCut(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeCut)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(typeCut)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(typeCut)->getIdentity()));
    ::java::lang::Integer* at = nullptr;
    ::java::net::URI* orientation = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(typeCut)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::Cut_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be"_j)->append(npc(Sbol2Terms_Cut::Cut_())->toString())->toString());
    }
    for (auto _i = npc(npc(typeCut)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::at()))) {
                auto temp = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                at = ::java::lang::Integer::valueOf(::java::lang::Integer::parseInt(temp));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::orientation()))) {
                orientation = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    if(at == nullptr) {
        throw new SBOLValidationException(u"Cut requires at property."_j, new IdentifiedArray());
    }
    auto c = new Cut(npc(typeCut)->getIdentity(), (npc(at))->intValue());
    if(persistentIdentity != nullptr)
        npc(c)->setPersistentIdentity(persistentIdentity);

    if(displayId != nullptr)
        npc(c)->setDisplayId(displayId);

    if(name != nullptr)
        npc(c)->setName(name);

    if(description != nullptr)
        npc(c)->setDescription(description);

    if(orientation != nullptr)
        npc(c)->setOrientation(OrientationType::convertToOrientationType(orientation));

    if(version != nullptr)
        npc(c)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(c)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(c)->setAnnotations(annotations);

    return c;
}

org::sbolstandard::core2::Location* org::sbolstandard::core2::SBOLReader::parseRange(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeRange)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(typeRange)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(typeRange)->getIdentity()));
    ::java::lang::Integer* start = nullptr;
    ::java::lang::Integer* end = nullptr;
    ::java::net::URI* orientation = nullptr;
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(typeRange)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::Range_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be"_j)->append(npc(Sbol2Terms_Range::Range_())->toString())->toString());
    }
    for (auto _i = npc(npc(typeRange)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            ::java::lang::String* temp;
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::start()))) {
                temp = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                start = ::java::lang::Integer::valueOf(::java::lang::Integer::parseInt(temp));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::end()))) {
                temp = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                end = ::java::lang::Integer::valueOf(::java::lang::Integer::parseInt(temp));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::orientation()))) {
                orientation = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    Location* r = new Range(npc(typeRange)->getIdentity(), (npc(start))->intValue(), (npc(end))->intValue());
    if(displayId != nullptr)
        npc(r)->setDisplayId(displayId);

    if(name != nullptr)
        npc(r)->setName(name);

    if(description != nullptr)
        npc(r)->setDescription(description);

    if(persistentIdentity != nullptr)
        npc(r)->setPersistentIdentity(persistentIdentity);

    if(orientation != nullptr)
        npc(r)->setOrientation(OrientationType::convertToOrientationType(orientation));

    if(version != nullptr)
        npc(r)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(r)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(r)->setAnnotations(annotations);

    return r;
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::SBOLReader::parseComponent(::uk::ac::ncl::intbio::core::datatree::NestedDocument* component)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(component)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(component)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* subComponentURI = nullptr;
    AccessType* access = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    ::java::util::List* mapsTo = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(component)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Component::Component_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be "_j)->append(npc(Sbol2Terms_Component::Component_())->toString())->toString());
    }
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(component)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Component::Component_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(component)->getType())))->append(u" is not a valid component."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(component)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::access()))) {
                auto accessTypeStr = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
                if(npc(accessTypeStr)->startsWith(u"http://www.sbolstandard.org/"_j)) {
                    npc(::java::lang::System::out())->println(u"Warning: namespace for access types should be http://sbols.org/v2#"_j);
                    accessTypeStr = npc(accessTypeStr)->replace(static_cast< ::java::lang::CharSequence* >(u"http://www.sbolstandard.org/"_j), static_cast< ::java::lang::CharSequence* >(u"http://sbols.org/v2#"_j));
                }
                access = AccessType::convertToAccessType(::java::net::URI::create(accessTypeStr));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasMapsTo()))) {
                npc(mapsTo)->add(static_cast< ::java::lang::Object* >(parseMapsTo(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasComponentDefinition()))) {
                subComponentURI = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto c = new Component(npc(component)->getIdentity(), access, subComponentURI);
    if(persistentIdentity != nullptr)
        npc(c)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(c)->setVersion(version);

    if(displayId != nullptr)
        npc(c)->setDisplayId(displayId);

    if(access != nullptr)
        npc(c)->setAccess(access);

    if(!npc(mapsTo)->isEmpty())
        npc(c)->setMapsTo(mapsTo);

    if(subComponentURI != nullptr)
        npc(c)->setDefinition(subComponentURI);

    if(name != nullptr)
        npc(c)->setName(name);

    if(description != nullptr)
        npc(c)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(c)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(c)->setAnnotations(annotations);

    return c;
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLReader::parseGenericTopLevel(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto t = npc(SBOLDoc)->createGenericTopLevel(npc(topLevel)->getIdentity(), java_cast< ::javax::xml::namespace_::QName* >(npc(topLevel)->getType()));
    if(persistentIdentity != nullptr)
        npc(t)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(t)->setVersion(version);

    if(displayId != nullptr)
        npc(t)->setDisplayId(displayId);

    if(name != nullptr)
        npc(t)->setName(name);

    if(description != nullptr)
        npc(t)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(t)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(t)->setAnnotations(annotations);

    return t;
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLReader::parseModels(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* source = nullptr;
    ::java::net::URI* language = nullptr;
    ::java::net::URI* framework = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::source()))) {
                source = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::language()))) {
                language = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::framework()))) {
                framework = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto m = npc(SBOLDoc)->createModel(npc(topLevel)->getIdentity(), source, language, framework);
    if(persistentIdentity != nullptr)
        npc(m)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(m)->setVersion(version);

    if(displayId != nullptr)
        npc(m)->setDisplayId(displayId);

    if(name != nullptr)
        npc(m)->setName(name);

    if(description != nullptr)
        npc(m)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(m)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(m)->setAnnotations(annotations);

    return m;
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLReader::parseCollections(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::Set* members = new ::java::util::HashSet();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Collection::hasMembers()))) {
                npc(members)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto c = npc(SBOLDoc)->createCollection(npc(topLevel)->getIdentity());
    if(displayId != nullptr)
        npc(c)->setDisplayId(displayId);

    if(version != nullptr)
        npc(c)->setVersion(version);

    if(persistentIdentity != nullptr)
        npc(c)->setPersistentIdentity(persistentIdentity);

    if(!npc(members)->isEmpty())
        npc(c)->setMembers(members);

    if(name != nullptr)
        npc(c)->setName(name);

    if(description != nullptr)
        npc(c)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(c)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(c)->setAnnotations(annotations);

    return c;
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLReader::parseModuleDefinition(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::Set* roles = new ::java::util::HashSet();
    ::java::util::Set* models = new ::java::util::HashSet();
    ::java::util::List* functionalComponents = new ::java::util::ArrayList();
    ::java::util::List* interactions = new ::java::util::ArrayList();
    ::java::util::List* subModules = new ::java::util::ArrayList();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::roles()))) {
                npc(roles)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasModule()))) {
                npc(subModules)->add(static_cast< ::java::lang::Object* >(parseModule((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasSubModule()))) {
                npc(::java::lang::System::out())->println(u"Warning: tag should be sbol:module, not sbol:subModule."_j);
                npc(subModules)->add(static_cast< ::java::lang::Object* >(parseModule((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasInteractions()))) {
                npc(interactions)->add(static_cast< ::java::lang::Object* >(parseInteraction((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue())))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasfunctionalComponent()))) {
                npc(functionalComponents)->add(static_cast< ::java::lang::Object* >(parseFunctionalComponent(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasComponent()))) {
                npc(::java::lang::System::out())->println(u"Warning: tag should be sbol:functionalComponent, not sbol:component."_j);
                npc(functionalComponents)->add(static_cast< ::java::lang::Object* >(parseFunctionalComponent(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasModels()))) {
                npc(models)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto moduleDefinition = npc(SBOLDoc)->createModuleDefinition(npc(topLevel)->getIdentity());
    if(!npc(roles)->isEmpty())
        npc(moduleDefinition)->setRoles(roles);

    if(persistentIdentity != nullptr)
        npc(moduleDefinition)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(moduleDefinition)->setVersion(version);

    if(displayId != nullptr)
        npc(moduleDefinition)->setDisplayId(displayId);

    if(!npc(functionalComponents)->isEmpty())
        npc(moduleDefinition)->setFunctionalComponents(functionalComponents);

    if(!npc(interactions)->isEmpty())
        npc(moduleDefinition)->setInteractions(interactions);

    if(!npc(models)->isEmpty())
        npc(moduleDefinition)->setModels(models);

    if(!npc(subModules)->isEmpty())
        npc(moduleDefinition)->setModules(subModules);

    if(name != nullptr)
        npc(moduleDefinition)->setName(name);

    if(description != nullptr)
        npc(moduleDefinition)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(moduleDefinition)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(moduleDefinition)->setAnnotations(annotations);

    return moduleDefinition;
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::SBOLReader::parseModule(::uk::ac::ncl::intbio::core::datatree::NestedDocument* module)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(module)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(module)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* definitionURI = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* mappings = new ::java::util::ArrayList();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(module)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::Module_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be "_j)->append(npc(Sbol2Terms_Module::Module_())->toString())->toString());
    }
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(module)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::Module_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(module)->getType())))->append(u" is not a valid module."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(module)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasMapsTo()))) {
                npc(mappings)->add(static_cast< ::java::lang::Object* >(parseMapsTo(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasMapping()))) {
                npc(::java::lang::System::out())->println(u"Warning: tag should be sbol:mapTo, not sbol:mapping."_j);
                npc(mappings)->add(static_cast< ::java::lang::Object* >(parseMapsTo(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(namedProperty)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasDefinition()))) {
                definitionURI = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto submodule = new Module(npc(module)->getIdentity(), definitionURI);
    if(persistentIdentity != nullptr)
        npc(submodule)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(submodule)->setVersion(version);

    if(displayId != nullptr)
        npc(submodule)->setDisplayId(displayId);

    if(!npc(mappings)->isEmpty())
        npc(submodule)->setMapsTos(mappings);

    if(name != nullptr)
        npc(submodule)->setName(name);

    if(description != nullptr)
        npc(submodule)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(submodule)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(submodule)->setAnnotations(annotations);

    return submodule;
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::SBOLReader::parseMapsTo(::uk::ac::ncl::intbio::core::datatree::NestedDocument* mapsTo)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(mapsTo)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(mapsTo)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* remote = nullptr;
    RefinementType* refinement = nullptr;
    ::java::net::URI* local = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(mapsTo)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::MapsTo_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(mapsTo)->getType())))->append(u" is not a valid mapsTo."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(mapsTo)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* m = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::refinement()))) {
                auto refinementStr = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString();
                if(!npc(refinementStr)->startsWith(u"http://sbols.org/v2#"_j)) {
                    npc(::java::lang::System::out())->println(u"Warning: namespace for refinement types should be http://sbols.org/v2#"_j);
                    refinementStr = ::java::lang::StringBuilder().append(u"http://sbols.org/v2#"_j)->append(refinementStr)->toString();
                }
                refinement = RefinementType::convertToRefinementType(::java::net::URI::create(refinementStr));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::hasRemote()))) {
                remote = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::hasLocal()))) {
                local = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(m)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(m)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(m)));
            }
        }
    }
    auto map = new MapsTo(npc(mapsTo)->getIdentity(), refinement, local, remote);
    if(displayId != nullptr)
        npc(map)->setDisplayId(displayId);

    if(name != nullptr)
        npc(map)->setName(name);

    if(description != nullptr)
        npc(map)->setDescription(description);

    if(persistentIdentity != nullptr)
        npc(map)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(map)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(map)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(map)->setAnnotations(annotations);

    return map;
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::SBOLReader::parseInteraction(::uk::ac::ncl::intbio::core::datatree::NestedDocument* interaction)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(interaction)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(interaction)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::Set* type = new ::java::util::HashSet();
    ::java::util::List* participations = new ::java::util::ArrayList();
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(interaction)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Interaction::Interaction_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(interaction)->getType())))->append(u" is not a valid interaction."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(interaction)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* i = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Interaction::type()))) {
                npc(type)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Interaction::hasParticipations()))) {
                npc(participations)->add(static_cast< ::java::lang::Object* >(parseParticipation(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(i)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(i)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(i)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(i)));
            }
        }
    }
    auto i = new Interaction(npc(interaction)->getIdentity(), type);
    if(!npc(participations)->isEmpty())
        npc(i)->setParticipations(participations);

    if(persistentIdentity != nullptr)
        npc(i)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(i)->setVersion(version);

    if(displayId != nullptr)
        npc(i)->setDisplayId(displayId);

    if(name != nullptr)
        npc(i)->setName(name);

    if(description != nullptr)
        npc(i)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(i)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(i)->setAnnotations(annotations);

    return i;
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::SBOLReader::parseParticipation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* participation)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(participation)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(participation)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::util::Set* roles = new ::java::util::HashSet();
    ::java::net::URI* participant = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(participation)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::Participation_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be "_j)->append(npc(Sbol2Terms_Participation::Participation_())->toString())->toString());
    }
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(participation)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::Participation_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(participation)->getType())))->append(u" is not a valid participation."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(participation)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* p = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::role()))) {
                npc(roles)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString())));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::hasParticipant()))) {
                participant = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(p)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(p)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(p)));
            }
        }
    }
    auto p = new Participation(npc(participation)->getIdentity(), participant);
    if(!npc(roles)->isEmpty())
        npc(p)->setRoles(roles);

    if(displayId != nullptr)
        npc(p)->setDisplayId(displayId);

    if(name != nullptr)
        npc(p)->setName(name);

    if(description != nullptr)
        npc(p)->setDescription(description);

    if(persistentIdentity != nullptr)
        npc(p)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(p)->setVersion(version);

    if(wasDerivedFrom != nullptr)
        npc(p)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(p)->setAnnotations(annotations);

    return p;
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::SBOLReader::parseFunctionalComponent(::uk::ac::ncl::intbio::core::datatree::NestedDocument* functionalComponent)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(functionalComponent)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(functionalComponent)->getIdentity()));
    ::java::lang::String* version = nullptr;
    AccessType* access = nullptr;
    DirectionType* direction = nullptr;
    ::java::net::URI* functionalComponentURI = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    ::java::util::List* mappings = new ::java::util::ArrayList();
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(functionalComponent)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_FunctionalComponent::FunctionalComponent_()))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"QName has to be "_j)->append(npc(Sbol2Terms_FunctionalComponent::FunctionalComponent_())->toString())->toString());
    }
    if(!npc(java_cast< ::javax::xml::namespace_::QName* >(npc(functionalComponent)->getType()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_FunctionalComponent::FunctionalComponent_()))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(java_cast< ::javax::xml::namespace_::QName* >(npc(functionalComponent)->getType())))->append(u" is not a valid functional component."_j)->toString(), new IdentifiedArray());
    }
    for (auto _i = npc(npc(functionalComponent)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* f = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::access()))) {
                auto accessTypeStr = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
                if(npc(accessTypeStr)->startsWith(u"http://www.sbolstandard.org/"_j)) {
                    npc(::java::lang::System::out())->println(u"Warning: namespace for access types should be http://sbols.org/v2#"_j);
                    accessTypeStr = npc(accessTypeStr)->replace(static_cast< ::java::lang::CharSequence* >(u"http://www.sbolstandard.org/"_j), static_cast< ::java::lang::CharSequence* >(u"http://sbols.org/v2#"_j));
                }
                access = AccessType::convertToAccessType(::java::net::URI::create(accessTypeStr));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_FunctionalComponent::direction()))) {
                auto directionTypeStr = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
                if(npc(directionTypeStr)->startsWith(u"http://www.sbolstandard.org/"_j)) {
                    npc(::java::lang::System::out())->println(u"Warning: namespace for direction types should be http://sbols.org/v2#"_j);
                    directionTypeStr = npc(directionTypeStr)->replace(static_cast< ::java::lang::CharSequence* >(u"http://www.sbolstandard.org/"_j), static_cast< ::java::lang::CharSequence* >(u"http://sbols.org/v2#"_j));
                    directionTypeStr = npc(directionTypeStr)->replace(static_cast< ::java::lang::CharSequence* >(u"input"_j), static_cast< ::java::lang::CharSequence* >(u"in"_j));
                    directionTypeStr = npc(directionTypeStr)->replace(static_cast< ::java::lang::CharSequence* >(u"output"_j), static_cast< ::java::lang::CharSequence* >(u"out"_j));
                }
                direction = DirectionType::convertToDirectionType(::java::net::URI::create(directionTypeStr));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasMapsTo()))) {
                npc(mappings)->add(static_cast< ::java::lang::Object* >(parseMapsTo(java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(f)->getValue()))));
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasComponentDefinition()))) {
                functionalComponentURI = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(f)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(f)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(f)));
            }
        }
    }
    auto fc = new FunctionalComponent(npc(functionalComponent)->getIdentity(), access, functionalComponentURI, direction);
    if(persistentIdentity != nullptr)
        npc(fc)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(fc)->setVersion(version);

    if(displayId != nullptr)
        npc(fc)->setDisplayId(displayId);

    if(!npc(mappings)->isEmpty())
        npc(fc)->setMapsTo(mappings);

    if(name != nullptr)
        npc(fc)->setName(name);

    if(description != nullptr)
        npc(fc)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(fc)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(fc)->setAnnotations(annotations);

    return fc;
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLReader::parseSequences(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel)
{
    clinit();
    auto displayId = URIcompliance::extractDisplayId(npc(topLevel)->getIdentity());
    ::java::lang::String* name = nullptr;
    ::java::lang::String* description = nullptr;
    auto persistentIdentity = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(topLevel)->getIdentity()));
    ::java::lang::String* version = nullptr;
    ::java::lang::String* elements = nullptr;
    ::java::net::URI* encoding = nullptr;
    ::java::net::URI* wasDerivedFrom = nullptr;
    ::java::util::List* annotations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(topLevel)->getProperties())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
        {
            if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()))) {
                persistentIdentity = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()))) {
                version = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()))) {
                displayId = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Sequence::elements()))) {
                elements = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Sequence::encoding()))) {
                encoding = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()))) {
                name = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()))) {
                description = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString();
            } else if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(namedProperty)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()))) {
                wasDerivedFrom = ::java::net::URI::create(npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(namedProperty)->getValue())))->getValue())->toString());
            } else {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
    }
    auto sequence = npc(SBOLDoc)->createSequence(npc(topLevel)->getIdentity(), elements, encoding);
    if(persistentIdentity != nullptr)
        npc(sequence)->setPersistentIdentity(persistentIdentity);

    if(version != nullptr)
        npc(sequence)->setVersion(version);

    if(displayId != nullptr)
        npc(sequence)->setDisplayId(displayId);

    if(name != nullptr)
        npc(sequence)->setName(name);

    if(description != nullptr)
        npc(sequence)->setDescription(description);

    if(wasDerivedFrom != nullptr)
        npc(sequence)->setWasDerivedFrom(wasDerivedFrom);

    if(!npc(annotations)->isEmpty())
        npc(sequence)->setAnnotations(annotations);

    return sequence;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLReader::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLReader", 33);
    return c;
}

void org::sbolstandard::core2::SBOLReader::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        URIPrefix_ = nullptr;
        version_ = u""_j;
        typesInURI_ = false;
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::SBOLReader::getClass0()
{
    return class_();
}

