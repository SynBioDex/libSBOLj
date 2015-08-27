// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLReader.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SBOLReader
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::String* URIPrefix_;
    static ::java::lang::String* version_;
    static bool typesInURI_;

public:
    static void setURIPrefix(::java::lang::String* URIprefix);
    static void unsetURIPrefix();
    static void setVersion(::java::lang::String* version);
    static void setTypesInURI(bool typesInURI);
    static SBOLDocument* read(::java::lang::String* fileName) /* throws(Throwable) */;
    static SBOLDocument* readJSON(::java::lang::String* fileName) /* throws(Throwable) */;
    static SBOLDocument* readRDF(::java::lang::String* fileName) /* throws(Throwable) */;
    static SBOLDocument* readTurtle(::java::lang::String* fileName) /* throws(Throwable) */;
    static SBOLDocument* readJSON(::java::io::File* file) /* throws(Throwable) */;
    static SBOLDocument* read(::java::io::File* file) /* throws(Throwable) */;
    static SBOLDocument* readRDF(::java::io::File* file) /* throws(Throwable) */;
    static SBOLDocument* readTurtle(::java::io::File* file) /* throws(Throwable) */;
    static SBOLDocument* readJSON(::java::io::InputStream* in) /* throws(Exception) */;
    static SBOLDocument* read(::java::io::InputStream* in);
    static SBOLDocument* readRDF(::java::io::InputStream* in) /* throws(Exception) */;
    static SBOLDocument* readTurtle(::java::io::InputStream* in) /* throws(Exception) */;

private:
    static SBOLDocument* readV1(::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document);
    static ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* readJSON(::java::io::Reader* stream) /* throws(Exception) */;
    static ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* readRDF(::java::io::Reader* reader) /* throws(Exception) */;
    static ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* readTurtle(::java::io::Reader* reader) /* throws(Exception) */;
    static void readTopLevelDocsV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document);
    static void readTopLevelDocs(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document);
    static ComponentDefinition* parseDnaComponentV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* componentDef);
    static Sequence* parseDnaSequenceV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* topLevel);
    static ::java::lang::String* fixDisplayId(::java::lang::String* displayId);
    static ::java::lang::String* findDisplayId(::java::lang::String* topLevelIdentity);
    static Collection* parseCollectionV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument* topLevel);
    static SequenceAnnotation* parseSequenceAnnotationV1(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceAnnotation, ::java::util::List* precedePairs, ::java::lang::String* parentURI, int32_t sa_num);
    static ComponentDefinition* parseComponentDefinitions(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);
    static SequenceConstraint* parseSequenceConstraint(::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceConstraint);
    static SequenceAnnotation* parseSequenceAnnotation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* sequenceAnnotation);
    static Location* parseLocation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* location);
    static GenericLocation* parseGenericLocation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeGenLoc);
    static Cut* parseCut(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeCut);
    static Location* parseRange(::uk::ac::ncl::intbio::core::datatree::NestedDocument* typeRange);
    static Component* parseComponent(::uk::ac::ncl::intbio::core::datatree::NestedDocument* component);
    static GenericTopLevel* parseGenericTopLevel(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);
    static Model* parseModels(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);
    static Collection* parseCollections(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);
    static ModuleDefinition* parseModuleDefinition(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);
    static Module* parseModule(::uk::ac::ncl::intbio::core::datatree::NestedDocument* module);
    static MapsTo* parseMapsTo(::uk::ac::ncl::intbio::core::datatree::NestedDocument* mapsTo);
    static Interaction* parseInteraction(::uk::ac::ncl::intbio::core::datatree::NestedDocument* interaction);
    static Participation* parseParticipation(::uk::ac::ncl::intbio::core::datatree::NestedDocument* participation);
    static FunctionalComponent* parseFunctionalComponent(::uk::ac::ncl::intbio::core::datatree::NestedDocument* functionalComponent);
    static Sequence* parseSequences(SBOLDocument* SBOLDoc, ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* topLevel);

    // Generated

public:
    SBOLReader();
protected:
    SBOLReader(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

private:
    static ::java::lang::String*& URIPrefix();
    static ::java::lang::String*& version();
    static bool& typesInURI();
    virtual ::java::lang::Class* getClass0();
    friend class SBOLReader_SBOLPair;
};
