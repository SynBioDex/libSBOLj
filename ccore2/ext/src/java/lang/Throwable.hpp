// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::StackTraceElement, ObjectArray, ::java::io::SerializableArray > StackTraceElementArray;
typedef ::SubArray< ::java::lang::Throwable, ObjectArray, ::java::io::SerializableArray > ThrowableArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::Throwable
    : public virtual Object
    , public virtual ::java::io::Serializable
{

public:
    typedef Object super;

private:
    static bool $assertionsDisabled_;
    static String* CAUSE_CAPTION_;
    static ThrowableArray* EMPTY_THROWABLE_ARRAY_;
    static String* NULL_CAUSE_MESSAGE_;
    static String* SELF_SUPPRESSION_MESSAGE_;
    static String* SUPPRESSED_CAPTION_;
    static ::java::util::List* SUPPRESSED_SENTINEL_;
    static StackTraceElementArray* UNASSIGNED_STACK_;
    Object* backtrace {  };
    Throwable* cause {  };
    String* detailMessage {  };
    static constexpr int64_t serialVersionUID { int64_t(-3042686055658047285LL) };
    StackTraceElementArray* stackTrace {  };
    ::java::util::List* suppressedExceptions {  };

protected:
    void ctor();
    void ctor(String* message);
    void ctor(Throwable* cause);
    void ctor(String* message, Throwable* cause);
    void ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);

public:
    void addSuppressed(Throwable* exception);
    virtual Throwable* fillInStackTrace();
    /*Throwable* fillInStackTrace(int32_t dummy); (private) */
    virtual Throwable* getCause();
    virtual String* getLocalizedMessage();
    virtual String* getMessage();
    /*StackTraceElementArray* getOurStackTrace(); (private) */
    virtual StackTraceElementArray* getStackTrace();

public: /* package */
    virtual int32_t getStackTraceDepth();
    virtual StackTraceElement* getStackTraceElement(int32_t index);

public:
    ThrowableArray* getSuppressed();
    virtual Throwable* initCause(Throwable* cause);
    /*void printEnclosedStackTrace(Throwable_PrintStreamOrWriter* s, StackTraceElementArray* enclosingTrace, String* caption, String* prefix, ::java::util::Set* dejaVu); (private) */
    virtual void printStackTrace();
    virtual void printStackTrace(::java::io::PrintStream* s);
    /*void printStackTrace(Throwable_PrintStreamOrWriter* s); (private) */
    virtual void printStackTrace(::java::io::PrintWriter* s);
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    virtual void setStackTrace(StackTraceElementArray* stackTrace);
    String* toString() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    Throwable();
    Throwable(String* message);
    Throwable(Throwable* cause);
    Throwable(String* message, Throwable* cause);

public: /* protected */
    Throwable(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);
protected:
    Throwable(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static bool& $assertionsDisabled();

private:
    static String*& CAUSE_CAPTION();
    static ThrowableArray*& EMPTY_THROWABLE_ARRAY();
    static String*& NULL_CAUSE_MESSAGE();
    static String*& SELF_SUPPRESSION_MESSAGE();
    static String*& SUPPRESSED_CAPTION();
    static ::java::util::List*& SUPPRESSED_SENTINEL();
    static StackTraceElementArray*& UNASSIGNED_STACK();
    virtual ::java::lang::Class* getClass0();
};
