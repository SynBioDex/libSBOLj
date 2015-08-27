// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ClassLoader.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;

        namespace reflect
        {
typedef ::SubArray< ::java::lang::reflect::AnnotatedElement, ::java::lang::ObjectArray > AnnotatedElementArray;
        } // reflect
typedef ::SubArray< ::java::lang::Package, ObjectArray, ::java::lang::reflect::AnnotatedElementArray > PackageArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang

    namespace security
    {
        namespace cert
        {
typedef ::SubArray< ::java::security::cert::Certificate, ::java::lang::ObjectArray, ::java::io::SerializableArray > CertificateArray;
        } // cert
    } // security
} // java

extern void unimplemented_(const char16_t* name);
java::lang::ClassLoader::ClassLoader(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ClassLoader::ClassLoader()
    : ClassLoader(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::ClassLoader::ClassLoader(ClassLoader* parent)
    : ClassLoader(*static_cast< ::default_init_tag* >(0))
{
    ctor(parent);
}

java::util::Vector*& java::lang::ClassLoader::loadedLibraryNames()
{
    clinit();
    return loadedLibraryNames_;
}
java::util::Vector* java::lang::ClassLoader::loadedLibraryNames_;
java::util::Stack*& java::lang::ClassLoader::nativeLibraryContext()
{
    clinit();
    return nativeLibraryContext_;
}
java::util::Stack* java::lang::ClassLoader::nativeLibraryContext_;
java::security::cert::CertificateArray*& java::lang::ClassLoader::nocerts()
{
    clinit();
    return nocerts_;
}
java::security::cert::CertificateArray* java::lang::ClassLoader::nocerts_;
java::lang::ClassLoader*& java::lang::ClassLoader::scl()
{
    clinit();
    return scl_;
}
java::lang::ClassLoader* java::lang::ClassLoader::scl_;
bool& java::lang::ClassLoader::sclSet()
{
    clinit();
    return sclSet_;
}
bool java::lang::ClassLoader::sclSet_;
java::lang::StringArray*& java::lang::ClassLoader::sys_paths()
{
    clinit();
    return sys_paths_;
}
java::lang::StringArray* java::lang::ClassLoader::sys_paths_;
java::util::Vector*& java::lang::ClassLoader::systemNativeLibraries()
{
    clinit();
    return systemNativeLibraries_;
}
java::util::Vector* java::lang::ClassLoader::systemNativeLibraries_;
java::lang::StringArray*& java::lang::ClassLoader::usr_paths()
{
    clinit();
    return usr_paths_;
}
java::lang::StringArray* java::lang::ClassLoader::usr_paths_;

void ::java::lang::ClassLoader::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ClassLoader::ctor()");
}

void ::java::lang::ClassLoader::ctor(ClassLoader* parent)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ClassLoader::ctor(ClassLoader* parent)");
}

/* private: void ::java::lang::ClassLoader::ctor(Void* unused, ClassLoader* parent) */
void java::lang::ClassLoader::addClass(Class* c)
{ /* stub */
    unimplemented_(u"void java::lang::ClassLoader::addClass(Class* c)");
}

/* private: void java::lang::ClassLoader::checkCerts(String* name, ::java::security::CodeSource* cs) */
void java::lang::ClassLoader::checkClassLoaderPermission(ClassLoader* cl, Class* caller)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::ClassLoader::checkClassLoaderPermission(ClassLoader* cl, Class* caller)");
}

/* private: java::lang::Void* java::lang::ClassLoader::checkCreateClassLoader() */
/* private: bool java::lang::ClassLoader::checkName(String* name) */
/* private: void java::lang::ClassLoader::checkPackageAccess(Class* cls, ::java::security::ProtectionDomain* pd) */
void java::lang::ClassLoader::clearAssertionStatus()
{ /* stub */
    unimplemented_(u"void java::lang::ClassLoader::clearAssertionStatus()");
}

/* private: bool java::lang::ClassLoader::compareCerts(::java::security::cert::CertificateArray* pcerts, ::java::security::cert::CertificateArray* certs) */
java::lang::Class* java::lang::ClassLoader::defineClass(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::defineClass(::int8_tArray* b, int32_t off, int32_t len)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::java::nio::ByteBuffer* b, ::java::security::ProtectionDomain* protectionDomain)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::java::nio::ByteBuffer* b, ::java::security::ProtectionDomain* protectionDomain)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* protectionDomain)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* protectionDomain)");
    return 0;
}

/* private: java::lang::String* java::lang::ClassLoader::defineClassSourceLocation(::java::security::ProtectionDomain* pd) */
java::lang::Package* java::lang::ClassLoader::definePackage(String* name, String* specTitle, String* specVersion, String* specVendor, String* implTitle, String* implVersion, String* implVendor, ::java::net::URL* sealBase)
{ /* stub */
    unimplemented_(u"java::lang::Package* java::lang::ClassLoader::definePackage(String* name, String* specTitle, String* specVersion, String* specVendor, String* implTitle, String* implVersion, String* implVendor, ::java::net::URL* sealBase)");
    return 0;
}

/* private: java::lang::Class* java::lang::ClassLoader::defineTransformedClass(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* pd, ClassFormatError* cfe, String* source) */
bool java::lang::ClassLoader::desiredAssertionStatus(String* className)
{ /* stub */
    unimplemented_(u"bool java::lang::ClassLoader::desiredAssertionStatus(String* className)");
    return 0;
}

/* private: java::lang::Class* java::lang::ClassLoader::findBootstrapClassOrNull(String* name) */
java::lang::Class* java::lang::ClassLoader::findClass(String* name)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::findClass(String* name)");
    return 0;
}

java::lang::String* java::lang::ClassLoader::findLibrary(String* libname)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::ClassLoader::findLibrary(String* libname)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::findLoadedClass(String* name)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::findLoadedClass(String* name)");
    return 0;
}

int64_t java::lang::ClassLoader::findNative(ClassLoader* loader, String* name)
{ /* stub */
    clinit();
    unimplemented_(u"int64_t java::lang::ClassLoader::findNative(ClassLoader* loader, String* name)");
    return 0;
}

java::net::URL* java::lang::ClassLoader::findResource(String* name)
{ /* stub */
    unimplemented_(u"java::net::URL* java::lang::ClassLoader::findResource(String* name)");
    return 0;
}

java::util::Enumeration* java::lang::ClassLoader::findResources(String* name)
{ /* stub */
    unimplemented_(u"java::util::Enumeration* java::lang::ClassLoader::findResources(String* name)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::findSystemClass(String* name)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::findSystemClass(String* name)");
    return 0;
}

sun::misc::URLClassPath* java::lang::ClassLoader::getBootstrapClassPath()
{ /* stub */
    clinit();
    unimplemented_(u"sun::misc::URLClassPath* java::lang::ClassLoader::getBootstrapClassPath()");
    return 0;
}

/* private: java::net::URL* java::lang::ClassLoader::getBootstrapResource(String* name) */
/* private: java::util::Enumeration* java::lang::ClassLoader::getBootstrapResources(String* name) */
java::lang::ClassLoader* java::lang::ClassLoader::getClassLoader(Class* caller)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::ClassLoader* java::lang::ClassLoader::getClassLoader(Class* caller)");
    return 0;
}

java::lang::Object* java::lang::ClassLoader::getClassLoadingLock(String* className)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::ClassLoader::getClassLoadingLock(String* className)");
    return 0;
}

java::lang::Package* java::lang::ClassLoader::getPackage(String* name)
{ /* stub */
    unimplemented_(u"java::lang::Package* java::lang::ClassLoader::getPackage(String* name)");
    return 0;
}

java::lang::PackageArray* java::lang::ClassLoader::getPackages()
{ /* stub */
}

java::lang::ClassLoader* java::lang::ClassLoader::getParent()
{ /* stub */
return parent ; /* getter */
}

java::net::URL* java::lang::ClassLoader::getResource(String* name)
{ /* stub */
    unimplemented_(u"java::net::URL* java::lang::ClassLoader::getResource(String* name)");
    return 0;
}

java::io::InputStream* java::lang::ClassLoader::getResourceAsStream(String* name)
{ /* stub */
    unimplemented_(u"java::io::InputStream* java::lang::ClassLoader::getResourceAsStream(String* name)");
    return 0;
}

java::util::Enumeration* java::lang::ClassLoader::getResources(String* name)
{ /* stub */
    unimplemented_(u"java::util::Enumeration* java::lang::ClassLoader::getResources(String* name)");
    return 0;
}

java::lang::ClassLoader* java::lang::ClassLoader::getSystemClassLoader()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::ClassLoader* java::lang::ClassLoader::getSystemClassLoader()");
    return 0;
}

java::net::URL* java::lang::ClassLoader::getSystemResource(String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::net::URL* java::lang::ClassLoader::getSystemResource(String* name)");
    return 0;
}

java::io::InputStream* java::lang::ClassLoader::getSystemResourceAsStream(String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::io::InputStream* java::lang::ClassLoader::getSystemResourceAsStream(String* name)");
    return 0;
}

java::util::Enumeration* java::lang::ClassLoader::getSystemResources(String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Enumeration* java::lang::ClassLoader::getSystemResources(String* name)");
    return 0;
}

/* private: void java::lang::ClassLoader::initSystemClassLoader() */
/* private: void java::lang::ClassLoader::initializeJavaAssertionMaps() */
/* private: java::lang::StringArray* java::lang::ClassLoader::initializePath(String* propname) */
bool java::lang::ClassLoader::isAncestor(ClassLoader* cl)
{ /* stub */
    unimplemented_(u"bool java::lang::ClassLoader::isAncestor(ClassLoader* cl)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::loadClass(String* name)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::loadClass(String* name)");
    return 0;
}

java::lang::Class* java::lang::ClassLoader::loadClass(String* name, bool resolve)
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::ClassLoader::loadClass(String* name, bool resolve)");
    return 0;
}

/* private: java::lang::Class* java::lang::ClassLoader::loadClassInternal(String* name) */
void java::lang::ClassLoader::loadLibrary(Class* fromClass, String* name, bool isAbsolute)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::ClassLoader::loadLibrary(Class* fromClass, String* name, bool isAbsolute)");
}

/* private: bool java::lang::ClassLoader::loadLibrary0(Class* fromClass, ::java::io::File* file) */
/* private: bool java::lang::ClassLoader::loadLibrary1(Class* fromClass, ::java::io::File* file) */
/* private: bool java::lang::ClassLoader::needsClassLoaderPermissionCheck(ClassLoader* from, ClassLoader* to) */
/* private: void java::lang::ClassLoader::postDefineClass(Class* c, ::java::security::ProtectionDomain* pd) */
/* private: java::security::ProtectionDomain* java::lang::ClassLoader::preDefineClass(String* name, ::java::security::ProtectionDomain* pd) */
bool java::lang::ClassLoader::registerAsParallelCapable()
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::ClassLoader::registerAsParallelCapable()");
    return 0;
}

void java::lang::ClassLoader::resolveClass(Class* c)
{ /* stub */
    unimplemented_(u"void java::lang::ClassLoader::resolveClass(Class* c)");
}

void java::lang::ClassLoader::setClassAssertionStatus(String* className, bool enabled)
{ /* stub */
}

void java::lang::ClassLoader::setDefaultAssertionStatus(bool enabled)
{ /* stub */
    this->defaultAssertionStatus = enabled; /* setter */
}

void java::lang::ClassLoader::setPackageAssertionStatus(String* packageName, bool enabled)
{ /* stub */
}

void java::lang::ClassLoader::setSigners(Class* c, ObjectArray* signers)
{ /* stub */
    unimplemented_(u"void java::lang::ClassLoader::setSigners(Class* c, ObjectArray* signers)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ClassLoader::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ClassLoader", 21);
    return c;
}

java::lang::Class* java::lang::ClassLoader::getClass0()
{
    return class_();
}

