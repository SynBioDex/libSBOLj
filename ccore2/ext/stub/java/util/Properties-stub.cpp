// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Properties.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Properties::Properties(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Properties::Properties()
    : Properties(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::util::Properties::Properties(Properties* defaults)
    : Properties(*static_cast< ::default_init_tag* >(0))
{
    ctor(defaults);
}

char16_tArray*& java::util::Properties::hexDigit()
{
    clinit();
    return hexDigit_;
}
char16_tArray* java::util::Properties::hexDigit_;
constexpr int64_t java::util::Properties::serialVersionUID;

void ::java::util::Properties::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Properties::ctor()");
}

void ::java::util::Properties::ctor(Properties* defaults)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Properties::ctor(Properties* defaults)");
}

/* private: void java::util::Properties::enumerate(Hashtable* h) */
/* private: void java::util::Properties::enumerateStringProperties(Hashtable* h) */
java::lang::String* java::util::Properties::getProperty(::java::lang::String* key)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Properties::getProperty(::java::lang::String* key)");
    return 0;
}

java::lang::String* java::util::Properties::getProperty(::java::lang::String* key, ::java::lang::String* defaultValue)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Properties::getProperty(::java::lang::String* key, ::java::lang::String* defaultValue)");
    return 0;
}

void java::util::Properties::list(::java::io::PrintStream* out)
{ /* stub */
    unimplemented_(u"void java::util::Properties::list(::java::io::PrintStream* out)");
}

void java::util::Properties::list(::java::io::PrintWriter* out)
{ /* stub */
    unimplemented_(u"void java::util::Properties::list(::java::io::PrintWriter* out)");
}

void java::util::Properties::load(::java::io::Reader* reader)
{ /* stub */
    unimplemented_(u"void java::util::Properties::load(::java::io::Reader* reader)");
}

void java::util::Properties::load(::java::io::InputStream* inStream)
{ /* stub */
    unimplemented_(u"void java::util::Properties::load(::java::io::InputStream* inStream)");
}

/* private: void java::util::Properties::load0(Properties_LineReader* lr) */
/* private: java::lang::String* java::util::Properties::loadConvert(::char16_tArray* in, int32_t off, int32_t len, ::char16_tArray* convtBuf) */
void java::util::Properties::loadFromXML(::java::io::InputStream* in)
{ /* stub */
    unimplemented_(u"void java::util::Properties::loadFromXML(::java::io::InputStream* in)");
}

java::util::Enumeration* java::util::Properties::propertyNames()
{ /* stub */
    unimplemented_(u"java::util::Enumeration* java::util::Properties::propertyNames()");
    return 0;
}

void java::util::Properties::save(::java::io::OutputStream* out, ::java::lang::String* comments)
{ /* stub */
    unimplemented_(u"void java::util::Properties::save(::java::io::OutputStream* out, ::java::lang::String* comments)");
}

/* private: java::lang::String* java::util::Properties::saveConvert(::java::lang::String* theString, bool escapeSpace, bool escapeUnicode) */
java::lang::Object* java::util::Properties::setProperty(::java::lang::String* key, ::java::lang::String* value)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Properties::setProperty(::java::lang::String* key, ::java::lang::String* value)");
    return 0;
}

void java::util::Properties::store(::java::io::Writer* writer, ::java::lang::String* comments)
{ /* stub */
    unimplemented_(u"void java::util::Properties::store(::java::io::Writer* writer, ::java::lang::String* comments)");
}

void java::util::Properties::store(::java::io::OutputStream* out, ::java::lang::String* comments)
{ /* stub */
    unimplemented_(u"void java::util::Properties::store(::java::io::OutputStream* out, ::java::lang::String* comments)");
}

/* private: void java::util::Properties::store0(::java::io::BufferedWriter* bw, ::java::lang::String* comments, bool escUnicode) */
void java::util::Properties::storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment)
{ /* stub */
    unimplemented_(u"void java::util::Properties::storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment)");
}

void java::util::Properties::storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment, ::java::lang::String* encoding)
{ /* stub */
    unimplemented_(u"void java::util::Properties::storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment, ::java::lang::String* encoding)");
}

java::util::Set* java::util::Properties::stringPropertyNames()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Properties::stringPropertyNames()");
    return 0;
}

/* private: char16_t java::util::Properties::toHex(int32_t nibble) */
/* private: void java::util::Properties::writeComments(::java::io::BufferedWriter* bw, ::java::lang::String* comments) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Properties::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Properties", 20);
    return c;
}

java::lang::Class* java::util::Properties::getClass0()
{
    return class_();
}

