// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_ReverseComparator2.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_ReverseComparator2::Collections_ReverseComparator2(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_ReverseComparator2::Collections_ReverseComparator2(Comparator* cmp)
    : Collections_ReverseComparator2(*static_cast< ::default_init_tag* >(0))
{
    ctor(cmp);
}

bool& java::util::Collections_ReverseComparator2::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::util::Collections_ReverseComparator2::$assertionsDisabled_;
constexpr int64_t java::util::Collections_ReverseComparator2::serialVersionUID;

void ::java::util::Collections_ReverseComparator2::ctor(Comparator* cmp)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_ReverseComparator2::ctor(Comparator* cmp)");
}

int32_t java::util::Collections_ReverseComparator2::compare(::java::lang::Object* t1, ::java::lang::Object* t2)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_ReverseComparator2::compare(::java::lang::Object* t1, ::java::lang::Object* t2)");
    return 0;
}

bool java::util::Collections_ReverseComparator2::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_ReverseComparator2::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_ReverseComparator2::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_ReverseComparator2::hashCode()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_ReverseComparator2::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.ReverseComparator2", 40);
    return c;
}

java::lang::Class* java::util::Collections_ReverseComparator2::getClass0()
{
    return class_();
}

