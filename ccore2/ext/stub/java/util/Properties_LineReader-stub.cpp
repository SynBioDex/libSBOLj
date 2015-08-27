// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Properties_LineReader.hpp>

#include <java/util/Properties.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Properties_LineReader::Properties_LineReader(Properties *Properties_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , Properties_this(Properties_this)
{
    clinit();
}

java::util::Properties_LineReader::Properties_LineReader(Properties *Properties_this, ::java::io::InputStream* inStream)
    : Properties_LineReader(Properties_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(inStream);
}

java::util::Properties_LineReader::Properties_LineReader(Properties *Properties_this, ::java::io::Reader* reader)
    : Properties_LineReader(Properties_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(reader);
}


void ::java::util::Properties_LineReader::ctor(::java::io::InputStream* inStream)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Properties_LineReader::ctor(::java::io::InputStream* inStream)");
}

void ::java::util::Properties_LineReader::ctor(::java::io::Reader* reader)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Properties_LineReader::ctor(::java::io::Reader* reader)");
}

int32_t java::util::Properties_LineReader::readLine()
{ /* stub */
    unimplemented_(u"int32_t java::util::Properties_LineReader::readLine()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Properties_LineReader::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Properties.LineReader", 31);
    return c;
}

java::lang::Class* java::util::Properties_LineReader::getClass0()
{
    return class_();
}

