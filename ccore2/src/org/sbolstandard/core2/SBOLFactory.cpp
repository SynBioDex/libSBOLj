// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLFactory.java
#include <org/sbolstandard/core2/SBOLFactory.hpp>

#include <java/lang/NullPointerException.hpp>
#include <javax/xml/stream/FactoryConfigurationError.hpp>
#include <javax/xml/stream/XMLStreamException.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLReader.hpp>
#include <org/sbolstandard/core2/SBOLWriter.hpp>
#include <uk/ac/ncl/intbio/core/io/CoreIoException.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::SBOLFactory::SBOLFactory(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLFactory::SBOLFactory() 
    : SBOLFactory(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

void org::sbolstandard::core2::SBOLFactory::ctor()
{
    super::ctor();
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::SBOLFactory::read(::java::io::InputStream* in) /* throws(IOException, SBOLValidationException) */
{
    clinit();
    return SBOLReader::read(in);
}

void org::sbolstandard::core2::SBOLFactory::write(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(IOException, SBOLValidationException) */
{
    clinit();
    try {
        SBOLWriter::write(doc, out);
    } catch (::javax::xml::stream::XMLStreamException* e) {
        npc(e)->printStackTrace();
    } catch (::javax::xml::stream::FactoryConfigurationError* e) {
        npc(e)->printStackTrace();
    } catch (::uk::ac::ncl::intbio::core::io::CoreIoException* e) {
        npc(e)->printStackTrace();
    }
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLFactory::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLFactory", 34);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SBOLFactory::getClass0()
{
    return class_();
}

