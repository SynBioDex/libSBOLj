// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLValidate.java
#include <org/sbolstandard/core2/SBOLValidate.hpp>

#include <java/io/OutputStream.hpp>
#include <java/io/PrintStream.hpp>
#include <java/io/Serializable.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/CharSequence.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/Comparable.hpp>
#include <java/lang/Exception.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/lang/System.hpp>
#include <java/lang/Throwable.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/Collection.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/GenericTopLevel.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Model.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLReader.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/SBOLWriter.hpp>
#include <org/sbolstandard/core2/Sequence.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <SubArray.hpp>
#include <ObjectArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

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

org::sbolstandard::core2::SBOLValidate::SBOLValidate(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLValidate::SBOLValidate()
    : SBOLValidate(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::String*& org::sbolstandard::core2::SBOLValidate::SBOLVersion()
{
    clinit();
    return SBOLVersion_;
}
java::lang::String* org::sbolstandard::core2::SBOLValidate::SBOLVersion_;

void org::sbolstandard::core2::SBOLValidate::usage()
{
    clinit();
    npc(::java::lang::System::err())->println(::java::lang::StringBuilder().append(u"libSBOLj version "_j)->append(SBOLVersion_)->toString());
    npc(::java::lang::System::err())->println(::java::lang::StringBuilder().append(::java::lang::StringBuilder().append(u"Description: Validates the contents of an SBOL document,\n"_j)->append(u"converting from SBOL 1.1 to SBOL "_j)->toString())->append(SBOLVersion_)
        ->append(u", if necessary,\n"_j)
        ->append(u"and printing the document contents if validation succeeds"_j)->toString());
    npc(::java::lang::System::err())->println();
    npc(::java::lang::System::err())->println(u"Usage:"_j);
    npc(::java::lang::System::err())->println(u"\tjava --jar libSBOLj.jar [options] <inputFile> [-o <outputFile> -p <URIprefix> -v <version>]"_j);
    npc(::java::lang::System::err())->println();
    npc(::java::lang::System::err())->println(u"-t  uses types in URIs"_j);
    npc(::java::lang::System::err())->println(u"-i  incomplete SBOL document"_j);
    npc(::java::lang::System::err())->println(u"-n  non-compliant SBOL document"_j);
    ::java::lang::System::exit(1);
}

void org::sbolstandard::core2::SBOLValidate::validateCompliance(SBOLDocument* sbolDocument)
{
    clinit();
    for (auto _i = npc(npc(sbolDocument)->getCollections())->iterator(); _i->hasNext(); ) {
        Collection* collection = java_cast< Collection* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(collection) || !npc(collection)->checkDescendantsURIcompliance())
                throw new SBOLValidationException(u"Collection contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(collection)}));

        }
    }
    for (auto _i = npc(npc(sbolDocument)->getSequences())->iterator(); _i->hasNext(); ) {
        Sequence* sequence = java_cast< Sequence* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(sequence) || !npc(sequence)->checkDescendantsURIcompliance()) {
                throw new SBOLValidationException(u"Sequence contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(sequence)}));
            }
        }
    }
    for (auto _i = npc(npc(sbolDocument)->getComponentDefinitions())->iterator(); _i->hasNext(); ) {
        ComponentDefinition* componentDefinition = java_cast< ComponentDefinition* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(componentDefinition) || !npc(componentDefinition)->checkDescendantsURIcompliance()) {
                throw new SBOLValidationException(u"Component definition contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(componentDefinition)}));
            }
        }
    }
    for (auto _i = npc(npc(sbolDocument)->getModuleDefinitions())->iterator(); _i->hasNext(); ) {
        ModuleDefinition* moduleDefinition = java_cast< ModuleDefinition* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(moduleDefinition) || !npc(moduleDefinition)->checkDescendantsURIcompliance())
                throw new SBOLValidationException(u"Module definition contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(moduleDefinition)}));

        }
    }
    for (auto _i = npc(npc(sbolDocument)->getModels())->iterator(); _i->hasNext(); ) {
        Model* model = java_cast< Model* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(model) || !npc(model)->checkDescendantsURIcompliance())
                throw new SBOLValidationException(u"Model contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(model)}));

        }
    }
    for (auto _i = npc(npc(sbolDocument)->getGenericTopLevels())->iterator(); _i->hasNext(); ) {
        GenericTopLevel* genericTopLevel = java_cast< GenericTopLevel* >(_i->next());
        {
            if(!URIcompliance::isTopLevelURIcompliant(genericTopLevel) || !npc(genericTopLevel)->checkDescendantsURIcompliance())
                throw new SBOLValidationException(u"Generic top level contains non-compliant URI"_j, new IdentifiedArray({static_cast< Identified* >(genericTopLevel)}));

        }
    }
}

void org::sbolstandard::core2::SBOLValidate::validateCompleteness(SBOLDocument* sbolDocument)
{
    clinit();
    for (auto _i = npc(npc(sbolDocument)->getCollections())->iterator(); _i->hasNext(); ) {
        Collection* collection = java_cast< Collection* >(_i->next());
        {
            if(!npc(collection)->isComplete())
                throw new SBOLValidationException(u"Collection is not complete"_j, new IdentifiedArray({static_cast< Identified* >(collection)}));

        }
    }
    for (auto _i = npc(npc(sbolDocument)->getComponentDefinitions())->iterator(); _i->hasNext(); ) {
        ComponentDefinition* componentDefinition = java_cast< ComponentDefinition* >(_i->next());
        {
            if(!npc(componentDefinition)->isComplete())
                throw new SBOLValidationException(u"Component definition is not complete"_j, new IdentifiedArray({static_cast< Identified* >(componentDefinition)}));

        }
    }
    for (auto _i = npc(npc(sbolDocument)->getModuleDefinitions())->iterator(); _i->hasNext(); ) {
        ModuleDefinition* moduleDefinition = java_cast< ModuleDefinition* >(_i->next());
        {
            if(!npc(moduleDefinition)->isComplete())
                throw new SBOLValidationException(u"Module definition is not complete"_j, new IdentifiedArray({static_cast< Identified* >(moduleDefinition)}));

        }
    }
}

void org::sbolstandard::core2::SBOLValidate::main(::java::lang::StringArray* args)
{
    clinit();
    auto fileName = u""_j;
    auto outputFile = u""_j;
    auto URIPrefix = u""_j;
    auto version = u""_j;
    auto complete = true;
    auto compliant = true;
    auto typesInURI = false;
    auto i = int32_t(0);
    while (i < npc(args)->length) {
        if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-i"_j))) {
            complete = false;
        } else if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-t"_j))) {
            typesInURI = true;
        } else if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-n"_j))) {
            compliant = false;
        } else if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-o"_j))) {
            if(i + int32_t(1) >= npc(args)->length) {
                usage();
            }
            outputFile = (*args)[i + int32_t(1)];
            i++;
        } else if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-p"_j))) {
            if(i + int32_t(1) >= npc(args)->length) {
                usage();
            }
            URIPrefix = (*args)[i + int32_t(1)];
            i++;
        } else if(npc((*args)[i])->equals(static_cast< ::java::lang::Object* >(u"-v"_j))) {
            if(i + int32_t(1) >= npc(args)->length) {
                usage();
            }
            version = (*args)[i + int32_t(1)];
            i++;
        } else if(npc(fileName)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            fileName = (*args)[i];
        } else {
            usage();
        }
        i++;
    }
    if(npc(fileName)->equals(static_cast< ::java::lang::Object* >(u""_j)))
        usage();

    try {
        if(!npc(URIPrefix)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            SBOLReader::setURIPrefix(URIPrefix);
        }
        SBOLReader::setTypesInURI(typesInURI);
        SBOLReader::setVersion(version);
        auto doc = SBOLReader::read(fileName);
        npc(doc)->setTypesInURIs(typesInURI);
        if(compliant)
            validateCompliance(doc);

        if(complete)
            validateCompleteness(doc);

        npc(::java::lang::System::out())->println(u"Validation successful, no errors."_j);
        if(npc(outputFile)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
            SBOLWriter::write(doc, static_cast< ::java::io::OutputStream* >((::java::lang::System::out())));
        } else {
            SBOLWriter::write(doc, outputFile);
        }
    } catch (::java::lang::Exception* e) {
        npc(::java::lang::System::err())->println(::java::lang::StringBuilder().append(u"Validation failed.\n"_j)->append(npc(e)->getMessage())->toString());
    } catch (::java::lang::Throwable* e) {
        npc(::java::lang::System::err())->println(::java::lang::StringBuilder().append(u"Validation failed.\n"_j)->append(npc(e)->getMessage())->toString());
    }
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLValidate::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLValidate", 35);
    return c;
}

void org::sbolstandard::core2::SBOLValidate::clinit()
{
struct string_init_ {
    string_init_() {
    SBOLVersion_ = u"2.0"_j;
    }
};

    static string_init_ string_init_instance;

    super::clinit();
}

java::lang::Class* org::sbolstandard::core2::SBOLValidate::getClass0()
{
    return class_();
}

