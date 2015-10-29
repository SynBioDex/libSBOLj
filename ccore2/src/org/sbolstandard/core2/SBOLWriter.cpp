// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLWriter.java
#include <org/sbolstandard/core2/SBOLWriter.hpp>

#include <java/io/BufferedOutputStream.hpp>
#include <java/io/File.hpp>
#include <java/io/FileOutputStream.hpp>
#include <java/io/IOException.hpp>
#include <java/io/OutputStream.hpp>
#include <java/io/OutputStreamWriter.hpp>
#include <java/io/PrintWriter.hpp>
#include <java/io/Writer.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Boolean.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/Throwable.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/HashMap.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Set.hpp>
#include <javanet/staxutils/IndentingXMLStreamWriter.hpp>
#include <javax/json/Json.hpp>
#include <javax/json/stream/JsonGenerator.hpp>
#include <javax/json/stream/JsonGeneratorFactory.hpp>
#include <javax/xml/namespace_/QName.hpp>
#include <javax/xml/stream/FactoryConfigurationError.hpp>
#include <javax/xml/stream/XMLOutputFactory.hpp>
#include <javax/xml/stream/XMLStreamException.hpp>
#include <javax/xml/stream/XMLStreamWriter.hpp>
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
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
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
#include <org/sbolstandard/core2/Sequence.hpp>
#include <org/sbolstandard/core2/SequenceAnnotation.hpp>
#include <org/sbolstandard/core2/SequenceConstraint.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <uk/ac/intbio/core/io/turtle/TurtleIo.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree_NamedProperties.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree_TopLevelDocuments.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>
#include <uk/ac/ncl/intbio/core/datatree/DocumentRoot.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NameTransformer.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamedProperty.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NestedDocument.hpp>
#include <uk/ac/ncl/intbio/core/datatree/TopLevelDocument.hpp>
#include <uk/ac/ncl/intbio/core/io/CoreIoException.hpp>
#include <uk/ac/ncl/intbio/core/io/IoWriter.hpp>
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

namespace
{
    template<typename F>
    struct finally_
    {
        finally_(F f) : f(f), moved(false) { }
        finally_(finally_ &&x) : f(x.f), moved(false) { x.moved = true; }
        ~finally_() { if(!moved) f(); }
    private:
        finally_(const finally_&); finally_& operator=(const finally_&);
        F f;
        bool moved;
    };

    template<typename F> finally_<F> finally(F f) { return finally_<F>(f); }
}
org::sbolstandard::core2::SBOLWriter::SBOLWriter(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLWriter::SBOLWriter()
    : SBOLWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

void org::sbolstandard::core2::SBOLWriter::write(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */
{
    clinit();
    auto stream = new ::java::io::FileOutputStream(file);
    auto buffer = new ::java::io::BufferedOutputStream(stream);
    {
        auto finally0 = finally([&] {
            try {
                {
                    auto finally0 = finally([&] {
                        npc(buffer)->close();
                    });
                    {
                        npc(stream)->close();
                    }
                }

            } catch (::java::io::IOException* e) {
            }
        });
        try {
            write(doc, static_cast< ::java::io::OutputStream* >(buffer));
        } catch (::javax::xml::stream::XMLStreamException* e) {
        } catch (::javax::xml::stream::FactoryConfigurationError* e) {
        } catch (::uk::ac::ncl::intbio::core::io::CoreIoException* e) {
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::write(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */
{
    clinit();
    writeRDF(static_cast< ::java::io::Writer* >(new ::java::io::OutputStreamWriter(out)), ::uk::ac::ncl::intbio::core::datatree::Datatree::DocumentRoot(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBindings(npc(doc)->getNamespaceBindings()), ::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocuments(static_cast< ::java::util::List* >(getTopLevelDocument(doc)))));
}

void org::sbolstandard::core2::SBOLWriter::write(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */
{
    clinit();
    write(doc, new ::java::io::File(filename));
}

void org::sbolstandard::core2::SBOLWriter::writeJSON(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */
{
    clinit();
    auto stream = new ::java::io::FileOutputStream(file);
    auto buffer = new ::java::io::BufferedOutputStream(stream);
    {
        auto finally2 = finally([&] {
            try {
                {
                    auto finally2 = finally([&] {
                        npc(buffer)->close();
                    });
                    {
                        npc(stream)->close();
                    }
                }

            } catch (::java::io::IOException* e) {
            }
        });
        try {
            writeJSON(doc, static_cast< ::java::io::OutputStream* >(buffer));
        } catch (::javax::xml::stream::XMLStreamException* e) {
        } catch (::javax::xml::stream::FactoryConfigurationError* e) {
        } catch (::uk::ac::ncl::intbio::core::io::CoreIoException* e) {
        } catch (::java::lang::Throwable* e) {
            npc(e)->printStackTrace();
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::writeJSON(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(FactoryConfigurationError, Exception) */
{
    clinit();
    writeJSON(static_cast< ::java::io::Writer* >(new ::java::io::OutputStreamWriter(out)), ::uk::ac::ncl::intbio::core::datatree::Datatree::DocumentRoot(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBindings(npc(doc)->getNamespaceBindings()), ::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocuments(static_cast< ::java::util::List* >(getTopLevelDocument(doc)))));
}

void org::sbolstandard::core2::SBOLWriter::writeJSON(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */
{
    clinit();
    writeJSON(doc, new ::java::io::File(filename));
}

void org::sbolstandard::core2::SBOLWriter::writeRDF(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */
{
    clinit();
    auto stream = new ::java::io::FileOutputStream(file);
    auto buffer = new ::java::io::BufferedOutputStream(stream);
    {
        auto finally4 = finally([&] {
            try {
                {
                    auto finally4 = finally([&] {
                        npc(buffer)->close();
                    });
                    {
                        npc(stream)->close();
                    }
                }

            } catch (::java::io::IOException* e) {
            }
        });
        try {
            writeRDF(doc, static_cast< ::java::io::OutputStream* >(buffer));
        } catch (::javax::xml::stream::XMLStreamException* e) {
        } catch (::javax::xml::stream::FactoryConfigurationError* e) {
        } catch (::uk::ac::ncl::intbio::core::io::CoreIoException* e) {
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::writeRDF(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */
{
    clinit();
    writeRDF(static_cast< ::java::io::Writer* >(new ::java::io::OutputStreamWriter(out)), ::uk::ac::ncl::intbio::core::datatree::Datatree::DocumentRoot(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBindings(npc(doc)->getNamespaceBindings()), ::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocuments(static_cast< ::java::util::List* >(getTopLevelDocument(doc)))));
}

void org::sbolstandard::core2::SBOLWriter::writeRDF(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */
{
    clinit();
    writeRDF(doc, new ::java::io::File(filename));
}

void org::sbolstandard::core2::SBOLWriter::writeTurtle(SBOLDocument* doc, ::java::io::File* file) /* throws(Throwable) */
{
    clinit();
    auto stream = new ::java::io::FileOutputStream(file);
    auto buffer = new ::java::io::BufferedOutputStream(stream);
    {
        auto finally6 = finally([&] {
            try {
                {
                    auto finally6 = finally([&] {
                        npc(buffer)->close();
                    });
                    {
                        npc(stream)->close();
                    }
                }

            } catch (::java::io::IOException* e) {
            }
        });
        try {
            writeTurtle(doc, static_cast< ::java::io::OutputStream* >(buffer));
        } catch (::javax::xml::stream::XMLStreamException* e) {
        } catch (::javax::xml::stream::FactoryConfigurationError* e) {
        } catch (::uk::ac::ncl::intbio::core::io::CoreIoException* e) {
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::writeTurtle(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(FactoryConfigurationError, Exception) */
{
    clinit();
    writeTurtle(static_cast< ::java::io::Writer* >(new ::java::io::OutputStreamWriter(out)), ::uk::ac::ncl::intbio::core::datatree::Datatree::DocumentRoot(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBindings(npc(doc)->getNamespaceBindings()), ::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocuments(static_cast< ::java::util::List* >(getTopLevelDocument(doc)))));
}

void org::sbolstandard::core2::SBOLWriter::writeTurtle(SBOLDocument* doc, ::java::lang::String* filename) /* throws(Throwable) */
{
    clinit();
    writeTurtle(doc, new ::java::io::File(filename));
}

void org::sbolstandard::core2::SBOLWriter::writeJSON(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(Exception) */
{
    clinit();
    auto config = new ::java::util::HashMap();
    npc(config)->put(static_cast< ::java::lang::Object* >(::javax::json::stream::JsonGenerator::PRETTY_PRINTING()), ::java::lang::Boolean::valueOf(true));
    auto writer = npc(::javax::json::Json::createGeneratorFactory(config))->createGenerator(stream);
    auto jsonIo = new ::uk::ac::ncl::intbio::core::io::json::JsonIo();
    npc(npc(jsonIo)->createIoWriter(writer))->write(npc(::uk::ac::ncl::intbio::core::io::json::StringifyQName::qname2string())->mapDR(document));
    npc(writer)->flush();
    npc(writer)->close();
}

void org::sbolstandard::core2::SBOLWriter::writeRDF(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */
{
    clinit();
    ::javax::xml::stream::XMLStreamWriter* xmlWriter = new ::javanet::staxutils::IndentingXMLStreamWriter(npc(::javax::xml::stream::XMLOutputFactory::newInstance())->createXMLStreamWriter(stream));
    auto rdfIo = new ::uk::ac::ncl::intbio::core::io::rdf::RdfIo();
    npc(npc(rdfIo)->createIoWriter(xmlWriter))->write(document);
    npc(xmlWriter)->flush();
    npc(xmlWriter)->close();
}

void org::sbolstandard::core2::SBOLWriter::writeTurtle(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(Exception) */
{
    clinit();
    auto printWriter = new ::java::io::PrintWriter(stream);
    auto turtleIo = new ::uk::ac::intbio::core::io::turtle::TurtleIo();
    npc(npc(turtleIo)->createIoWriter(printWriter))->write(document);
    npc(printWriter)->flush();
}

void org::sbolstandard::core2::SBOLWriter::formatCollections(::java::util::Set* collections, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(collections)->iterator(); _i->hasNext(); ) {
        Collection* c = java_cast< Collection* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, c);
            for (auto _i = npc(npc(c)->getMemberURIs())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* member = java_cast< ::java::net::URI* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Collection::hasMembers()), member)));
                }
            }
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(Sbol2Terms_Collection::Collection_(), npc(c)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatCommonIdentifiedData(::java::util::List* list, Identified* t)
{
    clinit();
    if(npc(t)->isSetPersistentIdentity())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::persistentIdentity()), npc(t)->getPersistentIdentity())));

    if(npc(t)->isSetDisplayId())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::displayId()), npc(t)->getDisplayId())));

    if(npc(t)->isSetVersion())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::version()), npc(t)->getVersion())));

    if(npc(t)->isSetWasDerivedFrom())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::wasDerivedFrom()), npc(t)->getWasDerivedFrom())));

    if(npc(t)->isSetName())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::title()), npc(t)->getName())));

    if(npc(t)->isSetDescription())
        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::description()), npc(t)->getDescription())));

    for (auto _i = npc(npc(t)->getAnnotations())->iterator(); _i->hasNext(); ) {
        Annotation* annotation = java_cast< Annotation* >(_i->next());
        {
            if(!npc(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(npc(annotation)->getValue())->getName()))->getPrefix())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j)))
                npc(list)->add(static_cast< ::java::lang::Object* >(npc(annotation)->getValue()));

        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatCommonTopLevelData(::java::util::List* list, TopLevel* t)
{
    clinit();
    formatCommonIdentifiedData(list, t);
}

void org::sbolstandard::core2::SBOLWriter::formatComponentDefinitions(::java::util::Set* componentDefinitions, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(componentDefinitions)->iterator(); _i->hasNext(); ) {
        ComponentDefinition* c = java_cast< ComponentDefinition* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, c);
            for (auto _i = npc(npc(c)->getTypes())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* types = java_cast< ::java::net::URI* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::type()), types)));
                }
            }
            for (auto _i = npc(npc(c)->getRoles())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* roles = java_cast< ::java::net::URI* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::roles()), roles)));
                }
            }
            formatComponents(npc(c)->getComponents(), list);
            formatSequenceAnnotations(npc(c)->getSequenceAnnotations(), list);
            formatSequenceConstraints(npc(c)->getSequenceConstraints(), list);
            for (auto _i = npc(npc(c)->getSequenceURIs())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* sUri = java_cast< ::java::net::URI* >(_i->next());
                
                                        formatSequence(sUri, list);

            }
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(Sbol2Terms_ComponentDefinition::ComponentDefinition_(), npc(c)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatFunctionalComponents(::java::util::Set* functionalInstantiation, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(functionalInstantiation)->iterator(); _i->hasNext(); ) {
        FunctionalComponent* f = java_cast< FunctionalComponent* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, f);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasComponentDefinition()), npc(f)->getDefinitionURI())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::access()), AccessType::convertToURI(npc(f)->getAccess()))));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_FunctionalComponent::direction()), DirectionType::convertToURI(npc(f)->getDirection()))));
            auto referenceList = getMapsTo(npc(f)->getMapsTos());
            for (auto _i = npc(referenceList)->iterator(); _i->hasNext(); ) {
                ::uk::ac::ncl::intbio::core::datatree::NestedDocument* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasMapsTo()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(n))));
                }
            }
            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasfunctionalComponent()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_FunctionalComponent::FunctionalComponent_(), npc(f)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatInteractions(::java::util::Set* interactions, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(interactions)->iterator(); _i->hasNext(); ) {
        Interaction* i = java_cast< Interaction* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, i);
            for (auto _i = npc(npc(i)->getTypes())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* type = java_cast< ::java::net::URI* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Interaction::type()), type)));
                }
            }
            auto participantList = formatParticipations(npc(i)->getParticipations());
            for (auto _i = npc(participantList)->iterator(); _i->hasNext(); ) {
                ::uk::ac::ncl::intbio::core::datatree::NestedDocument* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Interaction::hasParticipations()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(n))));
                }
            }
            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasInteractions()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Interaction::Interaction_(), npc(i)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatModels(::java::util::Set* models, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        Model* m = java_cast< Model* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, m);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::source()), npc(m)->getSource())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::language()), npc(m)->getLanguage())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Model::framework()), npc(m)->getFramework())));
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(Sbol2Terms_Model::Model_(), npc(m)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatModelProperties(::java::util::Set* models, ::java::util::List* list)
{
    clinit();
    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* m = java_cast< ::java::net::URI* >(_i->next());
        {
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasModels()), m)));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatModule(::java::util::Set* module, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(module)->iterator(); _i->hasNext(); ) {
        Module* m = java_cast< Module* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, m);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasDefinition()), npc(m)->getDefinitionURI())));
            auto referenceList = getMapsTo(npc(m)->getMapsTos());
            for (auto _i = npc(referenceList)->iterator(); _i->hasNext(); ) {
                ::uk::ac::ncl::intbio::core::datatree::NestedDocument* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Module::hasMapsTo()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(n))));
                }
            }
            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::hasModule()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Module::Module_(), npc(m)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatModuleDefinitions(::java::util::Set* module, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(module)->iterator(); _i->hasNext(); ) {
        ModuleDefinition* m = java_cast< ModuleDefinition* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, m);
            for (auto _i = npc(npc(m)->getRoles())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ModuleDefinition::roles()), role)));
                }
            }
            formatFunctionalComponents(npc(m)->getFunctionalComponents(), list);
            formatInteractions(npc(m)->getInteractions(), list);
            formatModelProperties(npc(m)->getModelURIs(), list);
            formatModule(npc(m)->getModules(), list);
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(Sbol2Terms_ModuleDefinition::ModuleDefinition_(), npc(m)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

java::util::List* org::sbolstandard::core2::SBOLWriter::formatParticipations(::java::util::Set* participations)
{
    clinit();
    ::java::util::List* nestedDoc = new ::java::util::ArrayList();
    for (auto _i = npc(participations)->iterator(); _i->hasNext(); ) {
        Participation* p = java_cast< Participation* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, p);
            for (auto _i = npc(npc(p)->getRoles())->iterator(); _i->hasNext(); ) {
                ::java::net::URI* r = java_cast< ::java::net::URI* >(_i->next());
                
                                        npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::role()), r)));

            }
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Participation::hasParticipant()), npc(p)->getParticipantURI())));
            npc(nestedDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Participation::Participation_(), npc(p)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
    return nestedDoc;
}

void org::sbolstandard::core2::SBOLWriter::formatSequence(::java::net::URI* sequence, ::java::util::List* list)
{
    clinit();
    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequence()), sequence)));
}

void org::sbolstandard::core2::SBOLWriter::formatSequenceAnnotations(::java::util::Set* sequenceAnnotations, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(sequenceAnnotations)->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* s = java_cast< SequenceAnnotation* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, s);
            for (auto _i = npc(npc(s)->getLocations())->iterator(); _i->hasNext(); ) {
                Location* location = java_cast< Location* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(getLocation(location)));
                }
            }
            if(npc(s)->isSetComponent())
                npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceAnnotation::hasComponent()), npc(s)->getComponentURI())));

            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequenceAnnotations()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_SequenceAnnotation::SequenceAnnotation_(), npc(s)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatSequenceConstraints(::java::util::Set* sequenceConstraint, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(sequenceConstraint)->iterator(); _i->hasNext(); ) {
        SequenceConstraint* s = java_cast< SequenceConstraint* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, s);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::restriction()), npc(s)->getRestrictionURI())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::hasSubject()), npc(s)->getSubjectURI())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_SequenceConstraint::hasObject()), npc(s)->getObjectURI())));
            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasSequenceConstraints()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_SequenceConstraint::SequenceConstraint_(), npc(s)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatSequences(::java::util::Set* sequences, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(sequences)->iterator(); _i->hasNext(); ) {
        Sequence* s = java_cast< Sequence* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, s);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Sequence::elements()), npc(s)->getElements())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Sequence::encoding()), npc(s)->getEncoding())));
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(Sbol2Terms_Sequence::Sequence_(), npc(s)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatComponents(::java::util::Set* components, ::java::util::List* properties)
{
    clinit();
    for (auto _i = npc(components)->iterator(); _i->hasNext(); ) {
        Component* s = java_cast< Component* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, s);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::access()), AccessType::convertToURI(npc(s)->getAccess()))));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasComponentDefinition()), npc(s)->getDefinitionURI())));
            auto referenceList = getMapsTo(npc(s)->getMapsTos());
            for (auto _i = npc(referenceList)->iterator(); _i->hasNext(); ) {
                ::uk::ac::ncl::intbio::core::datatree::NestedDocument* n = java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(_i->next());
                {
                    npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentInstance::hasMapsTo()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(n))));
                }
            }
            npc(properties)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_ComponentDefinition::hasComponent()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Component::Component_(), npc(s)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))))));
        }
    }
}

void org::sbolstandard::core2::SBOLWriter::formatGenericTopLevel(::java::util::Set* topLevels, ::java::util::List* topLevelDoc)
{
    clinit();
    for (auto _i = npc(topLevels)->iterator(); _i->hasNext(); ) {
        GenericTopLevel* t = java_cast< GenericTopLevel* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonTopLevelData(list, t);
            npc(topLevelDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::TopLevelDocument(npc(t)->getRDFType(), npc(t)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
}

uk::ac::ncl::intbio::core::datatree::NamedProperty* org::sbolstandard::core2::SBOLWriter::getLocation(Location* location)
{
    clinit();
    ::java::util::List* property = new ::java::util::ArrayList();
    formatCommonIdentifiedData(property, location);
    if(dynamic_cast< Range* >(location) != nullptr) {
        auto range = java_cast< Range* >(location);
        npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::start()), npc(range)->getStart())));
        npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::end()), npc(range)->getEnd())));
        if(npc(range)->isSetOrientation())
            npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Range::orientation()), OrientationType::convertToURI(npc(range)->getOrientation()))));

        return ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Location::Location_()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Range::Range_(), npc(range)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(property)))));
    } else if(dynamic_cast< Cut* >(location) != nullptr) {
        auto cut = java_cast< Cut* >(location);
        npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::at()), npc(cut)->getAt())));
        if(npc(cut)->isSetOrientation())
            npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Cut::orientation()), OrientationType::convertToURI(npc(cut)->getOrientation()))));

        return ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Location::Location_()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_Cut::Cut_(), npc(cut)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(property)))));
    } else if(dynamic_cast< GenericLocation* >(location) != nullptr) {
        auto genericLocation = java_cast< GenericLocation* >(location);
        if(npc(genericLocation)->isSetOrientation())
            npc(property)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_GenericLocation::orientation()), OrientationType::convertToURI(npc(genericLocation)->getOrientation()))));

        return ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_Location::Location_()), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_GenericLocation::GenericLocation_(), npc(genericLocation)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(property)))));
    }
    throw new SBOLValidationException(u"Invalid location class."_j, new IdentifiedArray());
}

java::util::List* org::sbolstandard::core2::SBOLWriter::getMapsTo(::java::util::Set* references)
{
    clinit();
    ::java::util::List* nestedDoc = new ::java::util::ArrayList();
    for (auto _i = npc(references)->iterator(); _i->hasNext(); ) {
        MapsTo* m = java_cast< MapsTo* >(_i->next());
        {
            ::java::util::List* list = new ::java::util::ArrayList();
            formatCommonIdentifiedData(list, m);
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::refinement()), RefinementType::convertToURI(npc(m)->getRefinement()))));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::hasRemote()), npc(m)->getRemoteURI())));
            npc(list)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(Sbol2Terms_MapsTo::hasLocal()), npc(m)->getLocalURI())));
            npc(nestedDoc)->add(static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(Sbol2Terms_MapsTo::MapsTo_(), npc(m)->getIdentity(), ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
        }
    }
    return nestedDoc;
}

java::util::List* org::sbolstandard::core2::SBOLWriter::getTopLevelDocument(SBOLDocument* doc)
{
    clinit();
    ::java::util::List* topLevelDoc = new ::java::util::ArrayList();
    formatCollections(npc(doc)->getCollections(), topLevelDoc);
    formatModuleDefinitions(npc(doc)->getModuleDefinitions(), topLevelDoc);
    formatModels(npc(doc)->getModels(), topLevelDoc);
    formatComponentDefinitions(npc(doc)->getComponentDefinitions(), topLevelDoc);
    formatSequences(npc(doc)->getSequences(), topLevelDoc);
    formatGenericTopLevel(npc(doc)->getGenericTopLevels(), topLevelDoc);
    return topLevelDoc;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLWriter::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLWriter", 33);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SBOLWriter::getClass0()
{
    return class_();
}

