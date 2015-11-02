// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <javax/xml/namespace_/QName.hpp>

extern void unimplemented_(const char16_t* name);
javax::xml::namespace_::QName::QName(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

javax::xml::namespace_::QName::QName(::java::lang::String* localPart)
    : QName(*static_cast< ::default_init_tag* >(0))
{
    ctor(localPart);
}

javax::xml::namespace_::QName::QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart)
    : QName(*static_cast< ::default_init_tag* >(0))
{
    ctor(namespaceURI, localPart);
}

javax::xml::namespace_::QName::QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix)
    : QName(*static_cast< ::default_init_tag* >(0))
{
    ctor(namespaceURI, localPart, prefix);
}

constexpr int64_t javax::xml::namespace_::QName::compatibleSerialVersionUID;
constexpr int64_t javax::xml::namespace_::QName::defaultSerialVersionUID;
int64_t& javax::xml::namespace_::QName::serialVersionUID()
{
    clinit();
    return serialVersionUID_;
}
int64_t javax::xml::namespace_::QName::serialVersionUID_;
bool& javax::xml::namespace_::QName::useDefaultSerialVersionUID()
{
    clinit();
    return useDefaultSerialVersionUID_;
}
bool javax::xml::namespace_::QName::useDefaultSerialVersionUID_;

void ::javax::xml::namespace_::QName::ctor(::java::lang::String* localPart)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::javax::xml::namespace_::QName::ctor(::java::lang::String* localPart)");
}

void ::javax::xml::namespace_::QName::ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::javax::xml::namespace_::QName::ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart)");
}

void ::javax::xml::namespace_::QName::ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::javax::xml::namespace_::QName::ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix)");
}

bool javax::xml::namespace_::QName::equals(::java::lang::Object* objectToTest)
{ /* stub */
    unimplemented_(u"bool javax::xml::namespace_::QName::equals(::java::lang::Object* objectToTest)");
    return 0;
}

java::lang::String* javax::xml::namespace_::QName::getLocalPart()
{ /* stub */
return localPart ; /* getter */
}

java::lang::String* javax::xml::namespace_::QName::getNamespaceURI()
{ /* stub */
return namespaceURI ; /* getter */
}

java::lang::String* javax::xml::namespace_::QName::getPrefix()
{ /* stub */
return prefix ; /* getter */
}

int32_t javax::xml::namespace_::QName::hashCode()
{ /* stub */
    unimplemented_(u"int32_t javax::xml::namespace_::QName::hashCode()");
    return 0;
}

java::lang::String* javax::xml::namespace_::QName::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* javax::xml::namespace_::QName::toString()");
    return 0;
}

javax::xml::namespace_::QName* javax::xml::namespace_::QName::valueOf(::java::lang::String* qNameAsString)
{ /* stub */
    clinit();
    unimplemented_(u"javax::xml::namespace_::QName* javax::xml::namespace_::QName::valueOf(::java::lang::String* qNameAsString)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* javax::xml::namespace_::QName::class_()
{
    static ::java::lang::Class* c = ::class_(u"javax.xml.namespace.QName", 25);
    return c;
}

java::lang::Class* javax::xml::namespace_::QName::getClass0()
{
    return class_();
}

