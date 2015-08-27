// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/reflect/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/security/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/security/cert/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/concurrent/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/misc/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

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

struct default_init_tag;

class java::lang::ClassLoader
    : public virtual Object
{

public:
    typedef Object super;

public: /* package */
    Object* assertionLock {  };
    ::java::util::Map* classAssertionStatus {  };

private:
    ::java::util::Vector* classes {  };
    bool defaultAssertionStatus {  };
    ::java::security::ProtectionDomain* defaultDomain {  };
    ::java::util::Set* domains {  };
    static ::java::util::Vector* loadedLibraryNames_;
    ::java::util::Vector* nativeLibraries {  };
    static ::java::util::Stack* nativeLibraryContext_;
    static ::java::security::cert::CertificateArray* nocerts_;
    ::java::util::Map* package2certs {  };
    ::java::util::Map* packageAssertionStatus {  };
    ::java::util::HashMap* packages {  };
    ::java::util::concurrent::ConcurrentHashMap* parallelLockMap {  };
    ClassLoader* parent {  };
    static ClassLoader* scl_;
    static bool sclSet_;
    static StringArray* sys_paths_;
    static ::java::util::Vector* systemNativeLibraries_;
    static StringArray* usr_paths_;

protected:
    void ctor();
    void ctor(ClassLoader* parent);
    /*void ctor(Void* unused, ClassLoader* parent); (private) */

public: /* package */
    virtual void addClass(Class* c);
    /*void checkCerts(String* name, ::java::security::CodeSource* cs); (private) */
    static void checkClassLoaderPermission(ClassLoader* cl, Class* caller);
    /*static Void* checkCreateClassLoader(); (private) */
    /*bool checkName(String* name); (private) */
    /*void checkPackageAccess(Class* cls, ::java::security::ProtectionDomain* pd); (private) */

public:
    virtual void clearAssertionStatus();
    /*bool compareCerts(::java::security::cert::CertificateArray* pcerts, ::java::security::cert::CertificateArray* certs); (private) */

public: /* protected */
    Class* defineClass(::int8_tArray* b, int32_t off, int32_t len);
    Class* defineClass(String* name, ::java::nio::ByteBuffer* b, ::java::security::ProtectionDomain* protectionDomain);
    Class* defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len);
    Class* defineClass(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* protectionDomain);
    /*Class* defineClass0(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* pd); (private) */
    /*Class* defineClass1(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* pd, String* source); (private) */
    /*Class* defineClass2(String* name, ::java::nio::ByteBuffer* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* pd, String* source); (private) */
    /*String* defineClassSourceLocation(::java::security::ProtectionDomain* pd); (private) */
    virtual Package* definePackage(String* name, String* specTitle, String* specVersion, String* specVendor, String* implTitle, String* implVersion, String* implVendor, ::java::net::URL* sealBase);
    /*Class* defineTransformedClass(String* name, ::int8_tArray* b, int32_t off, int32_t len, ::java::security::ProtectionDomain* pd, ClassFormatError* cfe, String* source); (private) */

public: /* package */
    virtual bool desiredAssertionStatus(String* className);
    /*Class* findBootstrapClass(String* name); (private) */
    /*Class* findBootstrapClassOrNull(String* name); (private) */

public: /* protected */
    virtual Class* findClass(String* name);
    virtual String* findLibrary(String* libname);
    Class* findLoadedClass(String* name);
    /*Class* findLoadedClass0(String* name); (private) */

public: /* package */
    static int64_t findNative(ClassLoader* loader, String* name);

public: /* protected */
    virtual ::java::net::URL* findResource(String* name);
    virtual ::java::util::Enumeration* findResources(String* name);
    Class* findSystemClass(String* name);

public: /* package */
    static ::sun::misc::URLClassPath* getBootstrapClassPath();
    /*static ::java::net::URL* getBootstrapResource(String* name); (private) */
    /*static ::java::util::Enumeration* getBootstrapResources(String* name); (private) */
    static ClassLoader* getClassLoader(Class* caller);

public: /* protected */
    virtual Object* getClassLoadingLock(String* className);
    virtual Package* getPackage(String* name);
    virtual PackageArray* getPackages();

public:
    ClassLoader* getParent();
    virtual ::java::net::URL* getResource(String* name);
    virtual ::java::io::InputStream* getResourceAsStream(String* name);
    virtual ::java::util::Enumeration* getResources(String* name);
    static ClassLoader* getSystemClassLoader();
    static ::java::net::URL* getSystemResource(String* name);
    static ::java::io::InputStream* getSystemResourceAsStream(String* name);
    static ::java::util::Enumeration* getSystemResources(String* name);
    /*static void initSystemClassLoader(); (private) */
    /*void initializeJavaAssertionMaps(); (private) */
    /*static StringArray* initializePath(String* propname); (private) */

public: /* package */
    virtual bool isAncestor(ClassLoader* cl);

public:
    virtual Class* loadClass(String* name);

public: /* protected */
    virtual Class* loadClass(String* name, bool resolve);
    /*Class* loadClassInternal(String* name); (private) */

public: /* package */
    static void loadLibrary(Class* fromClass, String* name, bool isAbsolute);
    /*static bool loadLibrary0(Class* fromClass, ::java::io::File* file); (private) */
    /*static bool loadLibrary1(Class* fromClass, ::java::io::File* file); (private) */
    /*static bool needsClassLoaderPermissionCheck(ClassLoader* from, ClassLoader* to); (private) */
    /*void postDefineClass(Class* c, ::java::security::ProtectionDomain* pd); (private) */
    /*::java::security::ProtectionDomain* preDefineClass(String* name, ::java::security::ProtectionDomain* pd); (private) */

public: /* protected */
    static bool registerAsParallelCapable();
    /*static void registerNatives(); (private) */
    void resolveClass(Class* c);
    /*void resolveClass0(Class* c); (private) */
    /*static AssertionStatusDirectives* retrieveDirectives(); (private) */

public:
    virtual void setClassAssertionStatus(String* className, bool enabled);
    virtual void setDefaultAssertionStatus(bool enabled);
    virtual void setPackageAssertionStatus(String* packageName, bool enabled);

public: /* protected */
    void setSigners(Class* c, ObjectArray* signers);

    // Generated
    ClassLoader();
    ClassLoader(ClassLoader* parent);
protected:
    ClassLoader(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::java::util::Vector*& loadedLibraryNames();
    static ::java::util::Stack*& nativeLibraryContext();
    static ::java::security::cert::CertificateArray*& nocerts();
    static ClassLoader*& scl();
    static bool& sclSet();
    static StringArray*& sys_paths();
    static ::java::util::Vector*& systemNativeLibraries();
    static StringArray*& usr_paths();
    virtual ::java::lang::Class* getClass0();
};
