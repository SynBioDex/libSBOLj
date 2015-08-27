// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Thread.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Runnable, ObjectArray > RunnableArray;
typedef ::SubArray< ::java::lang::StackTraceElement, ObjectArray, ::java::io::SerializableArray > StackTraceElementArray;
typedef ::SubArray< ::java::lang::Thread, ObjectArray, RunnableArray > ThreadArray;
    } // lang
} // java

extern void unimplemented_(const char16_t* name);
java::lang::Thread::Thread(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Thread::Thread()
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::Thread::Thread(Runnable* target)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(target);
}

java::lang::Thread::Thread(String* name)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(name);
}

java::lang::Thread::Thread(Runnable* target, ::java::security::AccessControlContext* acc)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(target, acc);
}

java::lang::Thread::Thread(ThreadGroup* group, Runnable* target)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(group, target);
}

java::lang::Thread::Thread(ThreadGroup* group, String* name)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(group, name);
}

java::lang::Thread::Thread(Runnable* target, String* name)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(target, name);
}

java::lang::Thread::Thread(ThreadGroup* group, Runnable* target, String* name)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(group, target, name);
}

java::lang::Thread::Thread(ThreadGroup* group, Runnable* target, String* name, int64_t stackSize)
    : Thread(*static_cast< ::default_init_tag* >(0))
{
    ctor(group, target, name, stackSize);
}

java::lang::StackTraceElementArray*& java::lang::Thread::EMPTY_STACK_TRACE()
{
    clinit();
    return EMPTY_STACK_TRACE_;
}
java::lang::StackTraceElementArray* java::lang::Thread::EMPTY_STACK_TRACE_;
constexpr int32_t java::lang::Thread::MAX_PRIORITY;
constexpr int32_t java::lang::Thread::MIN_PRIORITY;
constexpr int32_t java::lang::Thread::NORM_PRIORITY;
java::lang::RuntimePermission*& java::lang::Thread::SUBCLASS_IMPLEMENTATION_PERMISSION()
{
    clinit();
    return SUBCLASS_IMPLEMENTATION_PERMISSION_;
}
java::lang::RuntimePermission* java::lang::Thread::SUBCLASS_IMPLEMENTATION_PERMISSION_;
std::atomic< java::lang::Thread_UncaughtExceptionHandler* >& java::lang::Thread::defaultUncaughtExceptionHandler()
{
    clinit();
    return defaultUncaughtExceptionHandler_;
}
std::atomic< java::lang::Thread_UncaughtExceptionHandler* > java::lang::Thread::defaultUncaughtExceptionHandler_;
int32_t& java::lang::Thread::threadInitNumber()
{
    clinit();
    return threadInitNumber_;
}
int32_t java::lang::Thread::threadInitNumber_;
int64_t& java::lang::Thread::threadSeqNumber()
{
    clinit();
    return threadSeqNumber_;
}
int64_t java::lang::Thread::threadSeqNumber_;

void ::java::lang::Thread::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor()");
}

void ::java::lang::Thread::ctor(Runnable* target)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(Runnable* target)");
}

void ::java::lang::Thread::ctor(String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(String* name)");
}

void ::java::lang::Thread::ctor(Runnable* target, ::java::security::AccessControlContext* acc)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(Runnable* target, ::java::security::AccessControlContext* acc)");
}

void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target)");
}

void ::java::lang::Thread::ctor(ThreadGroup* group, String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(ThreadGroup* group, String* name)");
}

void ::java::lang::Thread::ctor(Runnable* target, String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(Runnable* target, String* name)");
}

void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target, String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target, String* name)");
}

void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target, String* name, int64_t stackSize)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread::ctor(ThreadGroup* group, Runnable* target, String* name, int64_t stackSize)");
}

int32_t java::lang::Thread::activeCount()
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::Thread::activeCount()");
    return 0;
}

/* private: bool java::lang::Thread::auditSubclass(Class* subcl) */
void java::lang::Thread::blockedOn(::sun::nio::ch::Interruptible* b)
{ /* stub */
    unimplemented_(u"void java::lang::Thread::blockedOn(::sun::nio::ch::Interruptible* b)");
}

void java::lang::Thread::checkAccess()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::checkAccess()");
}

java::lang::Object* java::lang::Thread::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::Thread::clone()");
    return 0;
}

void java::lang::Thread::destroy()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::destroy()");
}

/* private: void java::lang::Thread::dispatchUncaughtException(Throwable* e) */
void java::lang::Thread::dumpStack()
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::Thread::dumpStack()");
}

int32_t java::lang::Thread::enumerate(ThreadArray* tarray)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::Thread::enumerate(ThreadArray* tarray)");
    return 0;
}

/* private: void java::lang::Thread::exit() */
java::util::Map* java::lang::Thread::getAllStackTraces()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Map* java::lang::Thread::getAllStackTraces()");
    return 0;
}

java::lang::ClassLoader* java::lang::Thread::getContextClassLoader()
{ /* stub */
return contextClassLoader ; /* getter */
}

java::lang::Thread_UncaughtExceptionHandler* java::lang::Thread::getDefaultUncaughtExceptionHandler()
{ /* stub */
    clinit();
return defaultUncaughtExceptionHandler() ; /* getter */
}

int64_t java::lang::Thread::getId()
{ /* stub */
    unimplemented_(u"int64_t java::lang::Thread::getId()");
    return 0;
}

java::lang::String* java::lang::Thread::getName()
{ /* stub */
}

int32_t java::lang::Thread::getPriority()
{ /* stub */
return priority ; /* getter */
}

java::lang::StackTraceElementArray* java::lang::Thread::getStackTrace()
{ /* stub */
    unimplemented_(u"java::lang::StackTraceElementArray* java::lang::Thread::getStackTrace()");
    return 0;
}

java::lang::Thread_State* java::lang::Thread::getState()
{ /* stub */
    unimplemented_(u"java::lang::Thread_State* java::lang::Thread::getState()");
    return 0;
}

java::lang::ThreadGroup* java::lang::Thread::getThreadGroup()
{ /* stub */
    unimplemented_(u"java::lang::ThreadGroup* java::lang::Thread::getThreadGroup()");
    return 0;
}

java::lang::Thread_UncaughtExceptionHandler* java::lang::Thread::getUncaughtExceptionHandler()
{ /* stub */
return uncaughtExceptionHandler ; /* getter */
}

/* private: void java::lang::Thread::init_(ThreadGroup* g, Runnable* target, String* name, int64_t stackSize) */
/* private: void java::lang::Thread::init_(ThreadGroup* g, Runnable* target, String* name, int64_t stackSize, ::java::security::AccessControlContext* acc) */
void java::lang::Thread::interrupt()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::interrupt()");
}

bool java::lang::Thread::interrupted()
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::Thread::interrupted()");
    return 0;
}

/* private: bool java::lang::Thread::isCCLOverridden(Class* cl) */
bool java::lang::Thread::isDaemon()
{ /* stub */
    unimplemented_(u"bool java::lang::Thread::isDaemon()");
    return 0;
}

bool java::lang::Thread::isInterrupted()
{ /* stub */
    unimplemented_(u"bool java::lang::Thread::isInterrupted()");
    return 0;
}

void java::lang::Thread::join()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::join()");
}

void java::lang::Thread::join(int64_t millis)
{ /* stub */
    unimplemented_(u"void java::lang::Thread::join(int64_t millis)");
}

void java::lang::Thread::join(int64_t millis, int32_t nanos)
{ /* stub */
    unimplemented_(u"void java::lang::Thread::join(int64_t millis, int32_t nanos)");
}

/* private: int64_t java::lang::Thread::nextThreadID() */
/* private: int32_t java::lang::Thread::nextThreadNum() */
void java::lang::Thread::processQueue(::java::lang::ref::ReferenceQueue* queue, ::java::util::concurrent::ConcurrentMap* map)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::Thread::processQueue(::java::lang::ref::ReferenceQueue* queue, ::java::util::concurrent::ConcurrentMap* map)");
}

void java::lang::Thread::resume()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::resume()");
}

void java::lang::Thread::run()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::run()");
}

void java::lang::Thread::setContextClassLoader(ClassLoader* cl)
{ /* stub */
    this->contextClassLoader = cl; /* setter */
}

void java::lang::Thread::setDaemon(bool on)
{ /* stub */
    this->daemon = on; /* setter */
}

void java::lang::Thread::setDefaultUncaughtExceptionHandler(Thread_UncaughtExceptionHandler* eh)
{ /* stub */
    clinit();
    Thread::defaultUncaughtExceptionHandler() = eh; /* setter */
}

void java::lang::Thread::setName(String* name)
{ /* stub */
}

void java::lang::Thread::setPriority(int32_t newPriority)
{ /* stub */
    this->priority = newPriority; /* setter */
}

void java::lang::Thread::setUncaughtExceptionHandler(Thread_UncaughtExceptionHandler* eh)
{ /* stub */
    this->uncaughtExceptionHandler = eh; /* setter */
}

void java::lang::Thread::sleep(int64_t millis, int32_t nanos)
{ /* stub */
    clinit();
    unimplemented_(u"void java::lang::Thread::sleep(int64_t millis, int32_t nanos)");
}

void java::lang::Thread::start()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::start()");
}

void java::lang::Thread::stop()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::stop()");
}

void java::lang::Thread::stop(Throwable* obj)
{ /* stub */
    unimplemented_(u"void java::lang::Thread::stop(Throwable* obj)");
}

void java::lang::Thread::suspend()
{ /* stub */
    unimplemented_(u"void java::lang::Thread::suspend()");
}

java::lang::String* java::lang::Thread::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Thread::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Thread::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Thread", 16);
    return c;
}

java::lang::Class* java::lang::Thread::getClass0()
{
    return class_();
}

