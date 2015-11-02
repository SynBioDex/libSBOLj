// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Arrays_ArrayList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Arrays_ArrayList::Arrays_ArrayList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Arrays_ArrayList::Arrays_ArrayList(::java::lang::ObjectArray* array)
    : Arrays_ArrayList(*static_cast< ::default_init_tag* >(0))
{
    ctor(array);
}

constexpr int64_t java::util::Arrays_ArrayList::serialVersionUID;

void ::java::util::Arrays_ArrayList::ctor(::java::lang::ObjectArray* array)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Arrays_ArrayList::ctor(::java::lang::ObjectArray* array)");
}

bool java::util::Arrays_ArrayList::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Arrays_ArrayList::contains(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Arrays_ArrayList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Arrays_ArrayList::get(int32_t index)");
    return 0;
}

int32_t java::util::Arrays_ArrayList::indexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Arrays_ArrayList::indexOf(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Arrays_ArrayList::set(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Arrays_ArrayList::set(int32_t index, ::java::lang::Object* element)");
    return 0;
}

int32_t java::util::Arrays_ArrayList::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Arrays_ArrayList::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Arrays_ArrayList::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Arrays_ArrayList::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Arrays_ArrayList::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Arrays_ArrayList::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Arrays_ArrayList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Arrays.ArrayList", 26);
    return c;
}

java::lang::Class* java::util::Arrays_ArrayList::getClass0()
{
    return class_();
}

