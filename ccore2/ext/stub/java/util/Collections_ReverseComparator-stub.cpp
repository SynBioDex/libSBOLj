// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_ReverseComparator.hpp>

#include <java/lang/Comparable.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_ReverseComparator::Collections_ReverseComparator(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_ReverseComparator*& java::util::Collections_ReverseComparator::REVERSE_ORDER()
{
    clinit();
    return REVERSE_ORDER_;
}
java::util::Collections_ReverseComparator* java::util::Collections_ReverseComparator::REVERSE_ORDER_;
constexpr int64_t java::util::Collections_ReverseComparator::serialVersionUID;

/* private: void ::java::util::Collections_ReverseComparator::ctor() */
int32_t java::util::Collections_ReverseComparator::compare(::java::lang::Comparable* c1, ::java::lang::Comparable* c2)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_ReverseComparator::compare(::java::lang::Comparable* c1, ::java::lang::Comparable* c2)");
    return 0;
}

int32_t java::util::Collections_ReverseComparator::compare(::java::lang::Object* o1, ::java::lang::Object* o2)
{ 
    return compare(dynamic_cast< ::java::lang::Comparable* >(o1), dynamic_cast< ::java::lang::Comparable* >(o2));
}

/* private: java::lang::Object* java::util::Collections_ReverseComparator::readResolve() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_ReverseComparator::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.ReverseComparator", 39);
    return c;
}

bool java::util::Collections_ReverseComparator::equals(::java::lang::Object* obj)
{
    return Object::equals(obj);
}

java::lang::Class* java::util::Collections_ReverseComparator::getClass0()
{
    return class_();
}

