// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Stack.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Stack::Stack(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Stack::Stack()
    : Stack(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

constexpr int64_t java::util::Stack::serialVersionUID;

void ::java::util::Stack::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Stack::ctor()");
}

bool java::util::Stack::empty()
{ /* stub */
    unimplemented_(u"bool java::util::Stack::empty()");
    return 0;
}

java::lang::Object* java::util::Stack::peek()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Stack::peek()");
    return 0;
}

java::lang::Object* java::util::Stack::pop()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Stack::pop()");
    return 0;
}

java::lang::Object* java::util::Stack::push(::java::lang::Object* item)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Stack::push(::java::lang::Object* item)");
    return 0;
}

int32_t java::util::Stack::search(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Stack::search(::java::lang::Object* o)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Stack::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Stack", 15);
    return c;
}

java::lang::Class* java::util::Stack::getClass0()
{
    return class_();
}

