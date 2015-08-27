// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLWriter.java

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SBOLWriter
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;
    static void write(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */;
    static void write(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */;
    static void write(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */;
    static void writeJSON(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */;
    static void writeJSON(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(FactoryConfigurationError, Exception) */;
    static void writeJSON(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */;
    static void writeRDF(SBOLDocument* doc, ::java::io::File* file) /* throws(FileNotFoundException) */;
    static void writeRDF(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */;
    static void writeRDF(SBOLDocument* doc, ::java::lang::String* filename) /* throws(FileNotFoundException) */;
    static void writeTurtle(SBOLDocument* doc, ::java::io::File* file) /* throws(Throwable) */;
    static void writeTurtle(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(FactoryConfigurationError, Exception) */;
    static void writeTurtle(SBOLDocument* doc, ::java::lang::String* filename) /* throws(Throwable) */;

private:
    static void writeJSON(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(Exception) */;
    static void writeRDF(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(XMLStreamException, FactoryConfigurationError, CoreIoException) */;
    static void writeTurtle(::java::io::Writer* stream, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) /* throws(Exception) */;
    static void formatCollections(::java::util::Set* collections, ::java::util::List* topLevelDoc);
    static void formatCommonIdentifiedData(::java::util::List* list, Identified* t);
    static void formatCommonTopLevelData(::java::util::List* list, TopLevel* t);
    static void formatComponentDefinitions(::java::util::Set* componentDefinitions, ::java::util::List* topLevelDoc);
    static void formatFunctionalComponents(::java::util::Set* functionalInstantiation, ::java::util::List* properties);
    static void formatInteractions(::java::util::Set* interactions, ::java::util::List* properties);
    static void formatModels(::java::util::Set* models, ::java::util::List* topLevelDoc);
    static void formatModelProperties(::java::util::Set* models, ::java::util::List* list);
    static void formatModule(::java::util::Set* module, ::java::util::List* properties);
    static void formatModuleDefinitions(::java::util::Set* module, ::java::util::List* topLevelDoc);
    static ::java::util::List* formatParticipations(::java::util::Set* participations);
    static void formatSequence(::java::net::URI* sequence, ::java::util::List* list);
    static void formatSequenceAnnotations(::java::util::Set* sequenceAnnotations, ::java::util::List* properties);
    static void formatSequenceConstraints(::java::util::Set* sequenceConstraint, ::java::util::List* properties);
    static void formatSequences(::java::util::Set* sequences, ::java::util::List* topLevelDoc);
    static void formatComponents(::java::util::Set* components, ::java::util::List* properties);
    static void formatGenericTopLevel(::java::util::Set* topLevels, ::java::util::List* topLevelDoc);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* getLocation(Location* location);
    static ::java::util::List* getMapsTo(::java::util::Set* references);
    static ::java::util::List* getTopLevelDocument(SBOLDocument* doc);

    // Generated

public:
    SBOLWriter();
protected:
    SBOLWriter(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};
