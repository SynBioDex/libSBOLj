// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/System.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::System::System(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

std::atomic< java::io::Console* >& java::lang::System::cons()
{
    clinit();
    return cons_;
}
std::atomic< java::io::Console* > java::lang::System::cons_;
java::io::PrintStream*& java::lang::System::err()
{
    clinit();
    return err_;
}
java::io::PrintStream* java::lang::System::err_;
java::io::InputStream*& java::lang::System::in()
{
    clinit();
    return in_;
}
java::io::InputStream* java::lang::System::in_;
java::lang::String*& java::lang::System::lineSeparator_()
{
    clinit();
    return lineSeparator__;
}
java::lang::String* java::lang::System::lineSeparator__;
java::io::PrintStream*& java::lang::System::out()
{
    clinit();
    return out_;
}
java::io::PrintStream* java::lang::System::out_;
java::util::Properties*& java::lang::System::props()
{
    clinit();
    return props_;
}
java::util::Properties* java::lang::System::props_;
std::atomic< java::lang::SecurityManager* >& java::lang::System::security()
{
    clinit();
    return security_;
}
std::atomic< java::lang::SecurityManager* > java::lang::System::security_;

/* private: void ::java::lang::System::ctor() */
/* private: void java::lang::System::checkIO() */
/* private: void java::lang::System::checkKey(String* key) */
java::lang::String* java::lang::System::clearProperty(String* key)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::clearProperty(String* key)");
    return 0;
}

java::io::Console* java::lang::System::console()
{ /* stub */
    clinit();
    unimplemented_(u"java::io::Console* java::lang::System::console()");
    return 0;
}

void java::lang::System::exit(int32_t status)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::exit(int32_t status)");
}

void java::lang::System::gc()
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::gc()");
}

java::util::Properties* java::lang::System::getProperties()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Properties* java::lang::System::getProperties()");
    return 0;
}

java::lang::String* java::lang::System::getProperty(String* key)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::getProperty(String* key)");
    return 0;
}

java::lang::String* java::lang::System::getProperty(String* key, String* def)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::getProperty(String* key, String* def)");
    return 0;
}

java::lang::SecurityManager* java::lang::System::getSecurityManager()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::SecurityManager* java::lang::System::getSecurityManager()");
    return 0;
}

java::util::Map* java::lang::System::getenv()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Map* java::lang::System::getenv()");
    return 0;
}

java::lang::String* java::lang::System::getenv(String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::getenv(String* name)");
    return 0;
}

java::nio::channels::Channel* java::lang::System::inheritedChannel()
{ /* stub */
    clinit();
    unimplemented_(u"java::nio::channels::Channel* java::lang::System::inheritedChannel()");
    return 0;
}

/* private: void java::lang::System::initializeSystemClass() */
java::lang::String* java::lang::System::lineSeparator()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::lineSeparator()");
    return 0;
}

void java::lang::System::load(String* filename)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::load(String* filename)");
}

void java::lang::System::loadLibrary(String* libname)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::loadLibrary(String* libname)");
}

void java::lang::System::runFinalization()
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::runFinalization()");
}

void java::lang::System::runFinalizersOnExit(bool value)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::runFinalizersOnExit(bool value)");
}

void java::lang::System::setErr(::java::io::PrintStream* err)
{ /* stub */
    clinit();
    System::err() = err; /* setter */
}

void java::lang::System::setIn(::java::io::InputStream* in)
{ /* stub */
    clinit();
    System::in() = in; /* setter */
}

/* private: void java::lang::System::setJavaLangAccess() */
void java::lang::System::setOut(::java::io::PrintStream* out)
{ /* stub */
    clinit();
    System::out() = out; /* setter */
}

void java::lang::System::setProperties(::java::util::Properties* props)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::setProperties(::java::util::Properties* props)");
}

java::lang::String* java::lang::System::setProperty(String* key, String* value)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::System::setProperty(String* key, String* value)");
    return 0;
}

void java::lang::System::setSecurityManager(SecurityManager* s)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::System::setSecurityManager(SecurityManager* s)");
}

/* private: void java::lang::System::setSecurityManager0(SecurityManager* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::System::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.System", 16);
    return c;
}

java::lang::Class* java::lang::System::getClass0()
{
    return class_();
}

