// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Throwable_SentinelHolder.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Throwable_SentinelHolder::Throwable_SentinelHolder(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::StackTraceElement*& java::lang::Throwable_SentinelHolder::STACK_TRACE_ELEMENT_SENTINEL()
{
    clinit();
    return STACK_TRACE_ELEMENT_SENTINEL_;
}
java::lang::StackTraceElement* java::lang::Throwable_SentinelHolder::STACK_TRACE_ELEMENT_SENTINEL_;
java::lang::StackTraceElementArray*& java::lang::Throwable_SentinelHolder::STACK_TRACE_SENTINEL()
{
    clinit();
    return STACK_TRACE_SENTINEL_;
}
java::lang::StackTraceElementArray* java::lang::Throwable_SentinelHolder::STACK_TRACE_SENTINEL_;

/* private: void ::java::lang::Throwable_SentinelHolder::ctor() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Throwable_SentinelHolder::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Throwable.SentinelHolder", 34);
    return c;
}

java::lang::Class* java::lang::Throwable_SentinelHolder::getClass0()
{
    return class_();
}

