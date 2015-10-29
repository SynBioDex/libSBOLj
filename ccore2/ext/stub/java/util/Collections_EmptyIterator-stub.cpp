// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_EmptyIterator.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_EmptyIterator::Collections_EmptyIterator(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_EmptyIterator*& java::util::Collections_EmptyIterator::EMPTY_ITERATOR()
{
    clinit();
    return EMPTY_ITERATOR_;
}
java::util::Collections_EmptyIterator* java::util::Collections_EmptyIterator::EMPTY_ITERATOR_;

/* private: void ::java::util::Collections_EmptyIterator::ctor() */
bool java::util::Collections_EmptyIterator::hasNext()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyIterator::hasNext()");
    return 0;
}

java::lang::Object* java::util::Collections_EmptyIterator::next()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_EmptyIterator::next()");
    return 0;
}

void java::util::Collections_EmptyIterator::remove()
{ /* stub */
    unimplemented_(u"void java::util::Collections_EmptyIterator::remove()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_EmptyIterator::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.EmptyIterator", 35);
    return c;
}

java::lang::Class* java::util::Collections_EmptyIterator::getClass0()
{
    return class_();
}

