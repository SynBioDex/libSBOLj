// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Thread_State.hpp>

#include <java/lang/Enum.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::Enum, ObjectArray, ComparableArray, ::java::io::SerializableArray > EnumArray;
typedef ::SubArray< ::java::lang::Thread_State, EnumArray > Thread_StateArray;
    } // lang
} // java

extern void unimplemented_(const char16_t* name);
java::lang::Thread_State::Thread_State(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Thread_StateArray*& java::lang::Thread_State::$VALUES()
{
    clinit();
    return $VALUES_;
}
java::lang::Thread_StateArray* java::lang::Thread_State::$VALUES_;
java::lang::Thread_State* java::lang::Thread_State::BLOCKED;
java::lang::Thread_State* java::lang::Thread_State::NEW;
java::lang::Thread_State* java::lang::Thread_State::RUNNABLE;
java::lang::Thread_State* java::lang::Thread_State::TERMINATED;
java::lang::Thread_State* java::lang::Thread_State::TIMED_WAITING;
java::lang::Thread_State* java::lang::Thread_State::WAITING;

/* private: void ::java::lang::Thread_State::ctor(::java::lang::String* name, int ordinal) */
java::lang::Thread_State* java::lang::Thread_State::valueOf(String* arg0)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Thread_State* java::lang::Thread_State::valueOf(String* arg0)");
    return 0;
}

java::lang::Thread_StateArray* java::lang::Thread_State::values()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Thread_StateArray* java::lang::Thread_State::values()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Thread_State::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Thread.State", 22);
    return c;
}

java::lang::Enum* java::lang::Thread_State::valueOf(Class* enumType, String* name)
{
    return super::valueOf(enumType, name);
}

java::lang::Class* java::lang::Thread_State::getClass0()
{
    return class_();
}

