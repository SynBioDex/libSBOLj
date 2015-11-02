// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/FileOutputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::FileOutputStream::FileOutputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::FileOutputStream::FileOutputStream(::java::lang::String* name)
    : FileOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(name);
}

java::io::FileOutputStream::FileOutputStream(File* file)
    : FileOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(file);
}

java::io::FileOutputStream::FileOutputStream(FileDescriptor* fdObj)
    : FileOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(fdObj);
}

java::io::FileOutputStream::FileOutputStream(::java::lang::String* name, bool append)
    : FileOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, append);
}

java::io::FileOutputStream::FileOutputStream(File* file, bool append)
    : FileOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(file, append);
}

java::lang::ThreadLocal*& java::io::FileOutputStream::runningFinalize()
{
    clinit();
    return runningFinalize_;
}
java::lang::ThreadLocal* java::io::FileOutputStream::runningFinalize_;

void ::java::io::FileOutputStream::ctor(::java::lang::String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileOutputStream::ctor(::java::lang::String* name)");
}

void ::java::io::FileOutputStream::ctor(File* file)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileOutputStream::ctor(File* file)");
}

void ::java::io::FileOutputStream::ctor(FileDescriptor* fdObj)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileOutputStream::ctor(FileDescriptor* fdObj)");
}

void ::java::io::FileOutputStream::ctor(::java::lang::String* name, bool append)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileOutputStream::ctor(::java::lang::String* name, bool append)");
}

void ::java::io::FileOutputStream::ctor(File* file, bool append)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FileOutputStream::ctor(File* file, bool append)");
}

void java::io::FileOutputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::FileOutputStream::close()");
}

void java::io::FileOutputStream::finalize()
{ /* stub */
    unimplemented_(u"void java::io::FileOutputStream::finalize()");
}

java::nio::channels::FileChannel* java::io::FileOutputStream::getChannel()
{ /* stub */
return channel ; /* getter */
}

java::io::FileDescriptor* java::io::FileOutputStream::getFD()
{ /* stub */
    unimplemented_(u"java::io::FileDescriptor* java::io::FileOutputStream::getFD()");
    return 0;
}

/* private: bool java::io::FileOutputStream::isRunningFinalize() */
void java::io::FileOutputStream::write(int32_t b)
{ /* stub */
    unimplemented_(u"void java::io::FileOutputStream::write(int32_t b)");
}

void java::io::FileOutputStream::write(::int8_tArray* b)
{ /* stub */
    unimplemented_(u"void java::io::FileOutputStream::write(::int8_tArray* b)");
}

void java::io::FileOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::FileOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::FileOutputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.FileOutputStream", 24);
    return c;
}

java::lang::Class* java::io::FileOutputStream::getClass0()
{
    return class_();
}

