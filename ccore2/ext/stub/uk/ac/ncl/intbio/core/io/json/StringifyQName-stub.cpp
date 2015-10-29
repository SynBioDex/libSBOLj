// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-json/0.1.2/sbol-data-io-json-0.1.2.jar
#include <uk/ac/ncl/intbio/core/io/json/StringifyQName.hpp>

extern void unimplemented_(const char16_t* name);
uk::ac::ncl::intbio::core::io::json::StringifyQName::StringifyQName(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

uk::ac::ncl::intbio::core::io::json::StringifyQName::StringifyQName()
    : StringifyQName(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

uk::ac::ncl::intbio::core::datatree::NameTransformer*& uk::ac::ncl::intbio::core::io::json::StringifyQName::qname2string()
{
    clinit();
    return qname2string_;
}
uk::ac::ncl::intbio::core::datatree::NameTransformer* uk::ac::ncl::intbio::core::io::json::StringifyQName::qname2string_;
uk::ac::ncl::intbio::core::datatree::NameTransformer*& uk::ac::ncl::intbio::core::io::json::StringifyQName::string2qname()
{
    clinit();
    return string2qname_;
}
uk::ac::ncl::intbio::core::datatree::NameTransformer* uk::ac::ncl::intbio::core::io::json::StringifyQName::string2qname_;

void ::uk::ac::ncl::intbio::core::io::json::StringifyQName::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::uk::ac::ncl::intbio::core::io::json::StringifyQName::ctor()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* uk::ac::ncl::intbio::core::io::json::StringifyQName::class_()
{
    static ::java::lang::Class* c = ::class_(u"uk.ac.ncl.intbio.core.io.json.StringifyQName", 44);
    return c;
}

java::lang::Class* uk::ac::ncl::intbio::core::io::json::StringifyQName::getClass0()
{
    return class_();
}

