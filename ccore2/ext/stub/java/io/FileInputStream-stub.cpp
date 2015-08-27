// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/FileInputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::FileInputStream::FileInputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::FileInputStream::FileInputStream(::java::lang::String* name)
    : FileInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(name);
}

java::io::FileInputStream::FileInputStream(File* file)
    : FileInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(file);
}

java::io::FileInputStream::FileInputStream(FileDescriptor* fdObj)
    : FileInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(fdObj);
}

java::lang::ThreadLocal*& java::io::FileInputStream::runningFinalize()
{
    clinit();
    return runningFinalize_;
}
java::lang::ThreadLocal* java::io::FileInputStream::runningFinalize_;

void ::java::io::FileInputStream::ctor(::java::lang::String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileInputStream::ctor(::java::lang::String* name)");
}

void ::java::io::FileInputStream::ctor(File* file)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileInputStream::ctor(File* file)");
}

void ::java::io::FileInputStream::ctor(FileDescriptor* fdObj)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileInputStream::ctor(FileDescriptor* fdObj)");
}

void java::io::FileInputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::FileInputStream::close()");
}

void java::io::FileInputStream::finalize()
{ /* stub */
    unimplemented_(u"void java::io::FileInputStream::finalize()");
}

java::nio::channels::FileChannel* java::io::FileInputStream::getChannel()
{ /* stub */
return channel ; /* getter */
}

java::io::FileDescriptor* java::io::FileInputStream::getFD()
{ /* stub */
    unimplemented_(u"java::io::FileDescriptor* java::io::FileInputStream::getFD()");
    return 0;
}

/* private: bool java::io::FileInputStream::isRunningFinalize() */
int32_t java::io::FileInputStream::read()
{ /* stub */
    unimplemented_(u"int32_t java::io::FileInputStream::read()");
    return 0;
}

int32_t java::io::FileInputStream::read(::int8_tArray* b)
{ /* stub */
    unimplemented_(u"int32_t java::io::FileInputStream::read(::int8_tArray* b)");
    return 0;
}

int32_t java::io::FileInputStream::read(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"int32_t java::io::FileInputStream::read(::int8_tArray* b, int32_t off, int32_t len)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::FileInputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.FileInputStream", 23);
    return c;
}

java::lang::Class* java::io::FileInputStream::getClass0()
{
    return class_();
}

