// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/HashMap_ValueIterator.hpp>

#include <java/util/HashMap.hpp>

extern void unimplemented_(const char16_t* name);
java::util::HashMap_ValueIterator::HashMap_ValueIterator(HashMap *HashMap_this, const ::default_init_tag&)
    : super(HashMap_this, *static_cast< ::default_init_tag* >(0))
{
    clinit();
}


/* private: void ::java::util::HashMap_ValueIterator::ctor() */
java::lang::Object* java::util::HashMap_ValueIterator::next()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashMap_ValueIterator::next()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::HashMap_ValueIterator::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.HashMap.ValueIterator", 31);
    return c;
}

java::lang::Class* java::util::HashMap_ValueIterator::getClass0()
{
    return class_();
}

