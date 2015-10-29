// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>

extern void unimplemented_(const char16_t* name);
uk::ac::ncl::intbio::core::datatree::NamespaceBinding::NamespaceBinding(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

uk::ac::ncl::intbio::core::datatree::NamespaceBinding::NamespaceBinding(::java::lang::String* namespaceURI, ::java::lang::String* prefix)
    : NamespaceBinding(*static_cast< ::default_init_tag* >(0))
{
    ctor(namespaceURI, prefix);
}


void ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding::ctor(::java::lang::String* namespaceURI, ::java::lang::String* prefix)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding::ctor(::java::lang::String* namespaceURI, ::java::lang::String* prefix)");
}

bool uk::ac::ncl::intbio::core::datatree::NamespaceBinding::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool uk::ac::ncl::intbio::core::datatree::NamespaceBinding::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::String* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::getNamespaceURI()
{ /* stub */
return namespaceURI ; /* getter */
}

java::lang::String* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::getPrefix()
{ /* stub */
return prefix ; /* getter */
}

int32_t uk::ac::ncl::intbio::core::datatree::NamespaceBinding::hashCode()
{ /* stub */
    unimplemented_(u"int32_t uk::ac::ncl::intbio::core::datatree::NamespaceBinding::hashCode()");
    return 0;
}

java::net::URI* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::namespacedUri(::java::lang::String* localPart)
{ /* stub */
    unimplemented_(u"java::net::URI* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::namespacedUri(::java::lang::String* localPart)");
    return 0;
}

javax::xml::namespace_::QName* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::withLocalPart(::java::lang::String* localPart)
{ /* stub */
    unimplemented_(u"javax::xml::namespace_::QName* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::withLocalPart(::java::lang::String* localPart)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::class_()
{
    static ::java::lang::Class* c = ::class_(u"uk.ac.ncl.intbio.core.datatree.NamespaceBinding", 47);
    return c;
}

java::lang::Class* uk::ac::ncl::intbio::core::datatree::NamespaceBinding::getClass0()
{
    return class_();
}

