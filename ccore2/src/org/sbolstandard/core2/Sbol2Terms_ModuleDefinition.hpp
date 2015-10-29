// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java

#pragma once

#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Sbol2Terms_ModuleDefinition final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::javax::xml::namespace_::QName* ModuleDefinition__;
    static ::javax::xml::namespace_::QName* roles_;
    static ::javax::xml::namespace_::QName* hasModule_;
    static ::javax::xml::namespace_::QName* hasSubModule_;
    static ::javax::xml::namespace_::QName* hasInteractions_;
    static ::javax::xml::namespace_::QName* hasModels_;
    static ::javax::xml::namespace_::QName* hasfunctionalComponent_;

    // Generated

public:
    Sbol2Terms_ModuleDefinition();
protected:
    Sbol2Terms_ModuleDefinition(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::javax::xml::namespace_::QName*& ModuleDefinition_();
    static ::javax::xml::namespace_::QName*& roles();
    static ::javax::xml::namespace_::QName*& hasModule();
    static ::javax::xml::namespace_::QName*& hasSubModule();
    static ::javax::xml::namespace_::QName*& hasInteractions();
    static ::javax::xml::namespace_::QName*& hasModels();
    static ::javax::xml::namespace_::QName*& hasfunctionalComponent();

private:
    virtual ::java::lang::Class* getClass0();
    friend class Sbol2Terms;
    friend class Sbol2Terms_Annotation;
    friend class Sbol2Terms_Component;
    friend class Sbol2Terms_Collection;
    friend class Sbol2Terms_ComponentDefinition;
    friend class Sbol2Terms_ComponentInstance;
    friend class Sbol2Terms_Cut;
    friend class Sbol2Terms_FunctionalComponent;
    friend class Sbol2Terms_GenericLocation;
    friend class Sbol2Terms_Identified;
    friend class Sbol2Terms_Interaction;
    friend class Sbol2Terms_Location;
    friend class Sbol2Terms_MapsTo;
    friend class Sbol2Terms_Model;
    friend class Sbol2Terms_Module;
    friend class Sbol2Terms_MultiRange;
    friend class Sbol2Terms_Participation;
    friend class Sbol2Terms_Range;
    friend class Sbol2Terms_Sequence;
    friend class Sbol2Terms_SequenceAnnotation;
    friend class Sbol2Terms_SequenceConstraint;
    friend class Sbol2Terms_GenericTopLevel;
    friend class Sbol2Terms_SequenceURI;
};
