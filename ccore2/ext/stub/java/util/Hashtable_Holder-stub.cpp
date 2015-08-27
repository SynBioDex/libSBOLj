// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Hashtable_Holder.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Hashtable_Holder::Hashtable_Holder(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

int32_t& java::util::Hashtable_Holder::ALTERNATIVE_HASHING_THRESHOLD()
{
    clinit();
    return ALTERNATIVE_HASHING_THRESHOLD_;
}
int32_t java::util::Hashtable_Holder::ALTERNATIVE_HASHING_THRESHOLD_;

/* private: void ::java::util::Hashtable_Holder::ctor() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Hashtable_Holder::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Hashtable.Holder", 26);
    return c;
}

java::lang::Class* java::util::Hashtable_Holder::getClass0()
{
    return class_();
}

