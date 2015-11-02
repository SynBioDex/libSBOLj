// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Throwable.hpp>

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

extern void unimplemented_(const char16_t* name);
java::lang::Throwable::Throwable(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Throwable::Throwable()
    : Throwable(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::Throwable::Throwable(String* message)
    : Throwable(*static_cast< ::default_init_tag* >(0))
{
    ctor(message);
}

java::lang::Throwable::Throwable(Throwable* cause)
    : Throwable(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::Throwable::Throwable(String* message, Throwable* cause)
    : Throwable(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

java::lang::Throwable::Throwable(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
    : Throwable(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause, enableSuppression, writableStackTrace);
}

bool& java::lang::Throwable::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::lang::Throwable::$assertionsDisabled_;
java::lang::String*& java::lang::Throwable::CAUSE_CAPTION()
{
    clinit();
    return CAUSE_CAPTION_;
}
java::lang::String* java::lang::Throwable::CAUSE_CAPTION_;
java::lang::ThrowableArray*& java::lang::Throwable::EMPTY_THROWABLE_ARRAY()
{
    clinit();
    return EMPTY_THROWABLE_ARRAY_;
}
java::lang::ThrowableArray* java::lang::Throwable::EMPTY_THROWABLE_ARRAY_;
java::lang::String*& java::lang::Throwable::NULL_CAUSE_MESSAGE()
{
    clinit();
    return NULL_CAUSE_MESSAGE_;
}
java::lang::String* java::lang::Throwable::NULL_CAUSE_MESSAGE_;
java::lang::String*& java::lang::Throwable::SELF_SUPPRESSION_MESSAGE()
{
    clinit();
    return SELF_SUPPRESSION_MESSAGE_;
}
java::lang::String* java::lang::Throwable::SELF_SUPPRESSION_MESSAGE_;
java::lang::String*& java::lang::Throwable::SUPPRESSED_CAPTION()
{
    clinit();
    return SUPPRESSED_CAPTION_;
}
java::lang::String* java::lang::Throwable::SUPPRESSED_CAPTION_;
java::util::List*& java::lang::Throwable::SUPPRESSED_SENTINEL()
{
    clinit();
    return SUPPRESSED_SENTINEL_;
}
java::util::List* java::lang::Throwable::SUPPRESSED_SENTINEL_;
java::lang::StackTraceElementArray*& java::lang::Throwable::UNASSIGNED_STACK()
{
    clinit();
    return UNASSIGNED_STACK_;
}
java::lang::StackTraceElementArray* java::lang::Throwable::UNASSIGNED_STACK_;
constexpr int64_t java::lang::Throwable::serialVersionUID;

void ::java::lang::Throwable::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable::ctor()");
}

void ::java::lang::Throwable::ctor(String* message)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable::ctor(String* message)");
}

void ::java::lang::Throwable::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable::ctor(Throwable* cause)");
}

void ::java::lang::Throwable::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable::ctor(String* message, Throwable* cause)");
}

void ::java::lang::Throwable::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)");
}

void java::lang::Throwable::addSuppressed(Throwable* exception)
{ /* stub */
    unimplemented_(u"void java::lang::Throwable::addSuppressed(Throwable* exception)");
}

java::lang::Throwable* java::lang::Throwable::fillInStackTrace()
{ /* stub */
    unimplemented_(u"java::lang::Throwable* java::lang::Throwable::fillInStackTrace()");
    return 0;
}

java::lang::Throwable* java::lang::Throwable::getCause()
{ /* stub */
return cause ; /* getter */
}

java::lang::String* java::lang::Throwable::getLocalizedMessage()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Throwable::getLocalizedMessage()");
    return 0;
}

java::lang::String* java::lang::Throwable::getMessage()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Throwable::getMessage()");
    return 0;
}

/* private: java::lang::StackTraceElementArray* java::lang::Throwable::getOurStackTrace() */
java::lang::StackTraceElementArray* java::lang::Throwable::getStackTrace()
{ /* stub */
return stackTrace ; /* getter */
}

java::lang::ThrowableArray* java::lang::Throwable::getSuppressed()
{ /* stub */
    unimplemented_(u"java::lang::ThrowableArray* java::lang::Throwable::getSuppressed()");
    return 0;
}

java::lang::Throwable* java::lang::Throwable::initCause(Throwable* cause)
{ /* stub */
    unimplemented_(u"java::lang::Throwable* java::lang::Throwable::initCause(Throwable* cause)");
    return 0;
}

/* private: void java::lang::Throwable::printEnclosedStackTrace(Throwable_PrintStreamOrWriter* s, StackTraceElementArray* enclosingTrace, String* caption, String* prefix, ::java::util::Set* dejaVu) */
void java::lang::Throwable::printStackTrace()
{ /* stub */
    unimplemented_(u"void java::lang::Throwable::printStackTrace()");
}

void java::lang::Throwable::printStackTrace(::java::io::PrintStream* s)
{ /* stub */
    unimplemented_(u"void java::lang::Throwable::printStackTrace(::java::io::PrintStream* s)");
}

/* private: void java::lang::Throwable::printStackTrace(Throwable_PrintStreamOrWriter* s) */
void java::lang::Throwable::printStackTrace(::java::io::PrintWriter* s)
{ /* stub */
    unimplemented_(u"void java::lang::Throwable::printStackTrace(::java::io::PrintWriter* s)");
}

/* private: void java::lang::Throwable::readObject(::java::io::ObjectInputStream* s) */
void java::lang::Throwable::setStackTrace(StackTraceElementArray* stackTrace)
{ /* stub */
    this->stackTrace = stackTrace; /* setter */
}

java::lang::String* java::lang::Throwable::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Throwable::toString()");
    return 0;
}

/* private: void java::lang::Throwable::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Throwable::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Throwable", 19);
    return c;
}

java::lang::Class* java::lang::Throwable::getClass0()
{
    return class_();
}

