// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-json/0.1.2/sbol-data-io-json-0.1.2.jar
#include <uk/ac/ncl/intbio/core/io/json/JsonIo.hpp>

extern void unimplemented_(const char16_t* name);
uk::ac::ncl::intbio::core::io::json::JsonIo::JsonIo(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

uk::ac::ncl::intbio::core::io::json::JsonIo::JsonIo()
    : JsonIo(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::uk::ac::ncl::intbio::core::io::json::JsonIo::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::uk::ac::ncl::intbio::core::io::json::JsonIo::ctor()");
}

/* private: java::util::Map* uk::ac::ncl::intbio::core::io::json::JsonIo::cholate(::java::util::List* ps) */
uk::ac::ncl::intbio::core::io::IoReader* uk::ac::ncl::intbio::core::io::json::JsonIo::createIoReader(::javax::json::JsonStructure* json)
{ /* stub */
    unimplemented_(u"uk::ac::ncl::intbio::core::io::IoReader* uk::ac::ncl::intbio::core::io::json::JsonIo::createIoReader(::javax::json::JsonStructure* json)");
    return 0;
}

uk::ac::ncl::intbio::core::io::IoWriter* uk::ac::ncl::intbio::core::io::json::JsonIo::createIoWriter(::javax::json::stream::JsonGenerator* writer)
{ /* stub */
    unimplemented_(u"uk::ac::ncl::intbio::core::io::IoWriter* uk::ac::ncl::intbio::core::io::json::JsonIo::createIoWriter(::javax::json::stream::JsonGenerator* writer)");
    return 0;
}

java::lang::String* uk::ac::ncl::intbio::core::io::json::JsonIo::getRdfAbout()
{ /* stub */
return rdfAbout ; /* getter */
}

java::lang::String* uk::ac::ncl::intbio::core::io::json::JsonIo::getRdfResource()
{ /* stub */
return rdfResource ; /* getter */
}

void uk::ac::ncl::intbio::core::io::json::JsonIo::setRdfAbout(::java::lang::String* rdfAbout)
{ /* stub */
    this->rdfAbout = rdfAbout; /* setter */
}

void uk::ac::ncl::intbio::core::io::json::JsonIo::setRdfResource(::java::lang::String* rdfResource)
{ /* stub */
    this->rdfResource = rdfResource; /* setter */
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* uk::ac::ncl::intbio::core::io::json::JsonIo::class_()
{
    static ::java::lang::Class* c = ::class_(u"uk.ac.ncl.intbio.core.io.json.JsonIo", 36);
    return c;
}

java::lang::Class* uk::ac::ncl::intbio::core::io::json::JsonIo::getClass0()
{
    return class_();
}

