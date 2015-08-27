// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/channels/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::lang::System final
    : public Object
{

public:
    typedef Object super;

private:
    static std::atomic< ::java::io::Console* > cons_;
    static ::java::io::PrintStream* err_;
    static ::java::io::InputStream* in_;
    static String* lineSeparator__;
    static ::java::io::PrintStream* out_;
    static ::java::util::Properties* props_;
    static std::atomic< SecurityManager* > security_;

    /*void ctor(); (private) */

public:
    static void arraycopy(Object* src, int32_t srcPos, Object* dest, int32_t destPos, int32_t length);
    /*static void checkIO(); (private) */
    /*static void checkKey(String* key); (private) */
    static String* clearProperty(String* key);
    static ::java::io::Console* console();
    static int64_t currentTimeMillis();
    static void exit(int32_t status);
    static void gc();
    static ::java::util::Properties* getProperties();
    static String* getProperty(String* key);
    static String* getProperty(String* key, String* def);
    static SecurityManager* getSecurityManager();
    static ::java::util::Map* getenv();
    static String* getenv(String* name);
    static int32_t identityHashCode(Object* x);
    static ::java::nio::channels::Channel* inheritedChannel();
    /*static ::java::util::Properties* initProperties(::java::util::Properties* props); (private) */
    /*static void initializeSystemClass(); (private) */
    static String* lineSeparator();
    static void load(String* filename);
    static void loadLibrary(String* libname);
    static String* mapLibraryName(String* libname);
    static int64_t nanoTime();
    /*static void registerNatives(); (private) */
    static void runFinalization();
    static void runFinalizersOnExit(bool value);
    static void setErr(::java::io::PrintStream* err);
    /*static void setErr0(::java::io::PrintStream* err); (private) */
    static void setIn(::java::io::InputStream* in);
    /*static void setIn0(::java::io::InputStream* in); (private) */
    /*static void setJavaLangAccess(); (private) */
    static void setOut(::java::io::PrintStream* out);
    /*static void setOut0(::java::io::PrintStream* out); (private) */
    static void setProperties(::java::util::Properties* props);
    static String* setProperty(String* key, String* value);
    static void setSecurityManager(SecurityManager* s);
    /*static void setSecurityManager0(SecurityManager* s); (private) */

    // Generated
    System();
protected:
    System(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static std::atomic< ::java::io::Console* >& cons();

public:
    static ::java::io::PrintStream*& err();
    static ::java::io::InputStream*& in();

private:
    static String*& lineSeparator_();

public:
    static ::java::io::PrintStream*& out();

private:
    static ::java::util::Properties*& props();
    static std::atomic< SecurityManager* >& security();
    virtual ::java::lang::Class* getClass0();
};
