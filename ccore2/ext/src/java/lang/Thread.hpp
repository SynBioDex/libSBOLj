// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/security/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/concurrent/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/nio/ch/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Runnable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Cloneable, ObjectArray > CloneableArray;
typedef ::SubArray< ::java::lang::Runnable, ObjectArray > RunnableArray;
typedef ::SubArray< ::java::lang::StackTraceElement, ObjectArray, ::java::io::SerializableArray > StackTraceElementArray;
typedef ::SubArray< ::java::lang::Thread, ObjectArray, RunnableArray > ThreadArray;
typedef ::SubArray< ::java::lang::StackTraceElementArray, CloneableArray, ::java::io::SerializableArray > StackTraceElementArrayArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::Thread
    : public virtual Object
    , public virtual Runnable
{

public:
    typedef Object super;

private:
    static StackTraceElementArray* EMPTY_STACK_TRACE_;

public:
    static constexpr int32_t MAX_PRIORITY { int32_t(10) };
    static constexpr int32_t MIN_PRIORITY { int32_t(1) };
    static constexpr int32_t NORM_PRIORITY { int32_t(5) };

private:
    static RuntimePermission* SUBCLASS_IMPLEMENTATION_PERMISSION_;
    std::atomic< ::sun::nio::ch::Interruptible* > blocker {  };
    Object* blockerLock {  };
    ClassLoader* contextClassLoader {  };
    bool daemon {  };
    static std::atomic< Thread_UncaughtExceptionHandler* > defaultUncaughtExceptionHandler_;
    int64_t eetop {  };
    ThreadGroup* group {  };

public: /* package */
    ThreadLocal_ThreadLocalMap* inheritableThreadLocals {  };

private:
    ::java::security::AccessControlContext* inheritedAccessControlContext {  };
    ::char16_tArray* name {  };
    int64_t nativeParkEventPointer {  };

public: /* package */
    std::atomic< Object* > parkBlocker {  };

private:
    int32_t priority {  };
    bool single_step {  };
    int64_t stackSize {  };
    bool stillborn {  };
    Runnable* target {  };
    static int32_t threadInitNumber_;

public: /* package */
    ThreadLocal_ThreadLocalMap* threadLocals {  };

private:
    Thread* threadQ {  };
    static int64_t threadSeqNumber_;
    std::atomic< int32_t > threadStatus {  };
    int64_t tid {  };
    std::atomic< Thread_UncaughtExceptionHandler* > uncaughtExceptionHandler {  };

protected:
    void ctor();
    void ctor(Runnable* target);
    void ctor(String* name);
    void ctor(Runnable* target, ::java::security::AccessControlContext* acc);
    void ctor(ThreadGroup* group, Runnable* target);
    void ctor(ThreadGroup* group, String* name);
    void ctor(Runnable* target, String* name);
    void ctor(ThreadGroup* group, Runnable* target, String* name);
    void ctor(ThreadGroup* group, Runnable* target, String* name, int64_t stackSize);

public:
    static int32_t activeCount();
    /*static bool auditSubclass(Class* subcl); (private) */

public: /* package */
    virtual void blockedOn(::sun::nio::ch::Interruptible* b);

public:
    void checkAccess();

public: /* protected */
    Object* clone() override;

public:
    virtual int32_t countStackFrames();
    static Thread* currentThread();
    virtual void destroy();
    /*void dispatchUncaughtException(Throwable* e); (private) */
    static void dumpStack();
    /*static StackTraceElementArrayArray* dumpThreads(ThreadArray* threads); (private) */
    static int32_t enumerate(ThreadArray* tarray);
    /*void exit(); (private) */
    static ::java::util::Map* getAllStackTraces();
    virtual ClassLoader* getContextClassLoader();
    static Thread_UncaughtExceptionHandler* getDefaultUncaughtExceptionHandler();
    virtual int64_t getId();
    String* getName();
    int32_t getPriority();
    virtual StackTraceElementArray* getStackTrace();
    virtual Thread_State* getState();
    ThreadGroup* getThreadGroup();
    /*static ThreadArray* getThreads(); (private) */
    virtual Thread_UncaughtExceptionHandler* getUncaughtExceptionHandler();
    static bool holdsLock(Object* obj);
    /*void init_(ThreadGroup* g, Runnable* target, String* name, int64_t stackSize); (private) */
    /*void init_(ThreadGroup* g, Runnable* target, String* name, int64_t stackSize, ::java::security::AccessControlContext* acc); (private) */
    virtual void interrupt();
    /*void interrupt0(); (private) */
    static bool interrupted();
    bool isAlive();
    /*static bool isCCLOverridden(Class* cl); (private) */
    bool isDaemon();
    virtual bool isInterrupted();
    /*bool isInterrupted(bool ClearInterrupted); (private) */
    void join();
    void join(int64_t millis);
    void join(int64_t millis, int32_t nanos);
    /*static int64_t nextThreadID(); (private) */
    /*static int32_t nextThreadNum(); (private) */

public: /* package */
    static void processQueue(::java::lang::ref::ReferenceQueue* queue, ::java::util::concurrent::ConcurrentMap* map);
    /*static void registerNatives(); (private) */

public:
    void resume();
    /*void resume0(); (private) */
    void run() override;
    virtual void setContextClassLoader(ClassLoader* cl);
    void setDaemon(bool on);
    static void setDefaultUncaughtExceptionHandler(Thread_UncaughtExceptionHandler* eh);
    void setName(String* name);
    /*void setNativeName(String* name); (private) */
    void setPriority(int32_t newPriority);
    /*void setPriority0(int32_t newPriority); (private) */
    virtual void setUncaughtExceptionHandler(Thread_UncaughtExceptionHandler* eh);
    static void sleep(int64_t millis);
    static void sleep(int64_t millis, int32_t nanos);
    virtual void start();
    /*void start0(); (private) */
    void stop();
    void stop(Throwable* obj);
    /*void stop0(Object* o); (private) */
    void suspend();
    /*void suspend0(); (private) */
    String* toString() override;
    static void yield();

    // Generated
    Thread();
    Thread(Runnable* target);
    Thread(String* name);

public: /* package */
    Thread(Runnable* target, ::java::security::AccessControlContext* acc);

public:
    Thread(ThreadGroup* group, Runnable* target);
    Thread(ThreadGroup* group, String* name);
    Thread(Runnable* target, String* name);
    Thread(ThreadGroup* group, Runnable* target, String* name);
    Thread(ThreadGroup* group, Runnable* target, String* name, int64_t stackSize);
protected:
    Thread(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static StackTraceElementArray*& EMPTY_STACK_TRACE();
    static RuntimePermission*& SUBCLASS_IMPLEMENTATION_PERMISSION();
    static std::atomic< Thread_UncaughtExceptionHandler* >& defaultUncaughtExceptionHandler();
    static int32_t& threadInitNumber();
    static int64_t& threadSeqNumber();
    virtual ::java::lang::Class* getClass0();
};
