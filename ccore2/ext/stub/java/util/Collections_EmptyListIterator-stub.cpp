// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_EmptyListIterator.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/lang/Object.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::Collections_EmptyListIterator::Collections_EmptyListIterator(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_EmptyListIterator*& java::util::Collections_EmptyListIterator::EMPTY_ITERATOR()
{
    clinit();
    return EMPTY_ITERATOR_;
}
java::util::Collections_EmptyListIterator* java::util::Collections_EmptyListIterator::EMPTY_ITERATOR_;

/* private: void ::java::util::Collections_EmptyListIterator::ctor() */
void java::util::Collections_EmptyListIterator::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::Collections_EmptyListIterator::add(::java::lang::Object* e)");
}

bool java::util::Collections_EmptyListIterator::hasPrevious()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyListIterator::hasPrevious()");
    return 0;
}

int32_t java::util::Collections_EmptyListIterator::nextIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_EmptyListIterator::nextIndex()");
    return 0;
}

java::lang::Object* java::util::Collections_EmptyListIterator::previous()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_EmptyListIterator::previous()");
    return 0;
}

int32_t java::util::Collections_EmptyListIterator::previousIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_EmptyListIterator::previousIndex()");
    return 0;
}

void java::util::Collections_EmptyListIterator::set(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::Collections_EmptyListIterator::set(::java::lang::Object* e)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_EmptyListIterator::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.EmptyListIterator", 39);
    return c;
}

bool java::util::Collections_EmptyListIterator::hasNext()
{
    return Collections_EmptyIterator::hasNext();
}

java::lang::Object* java::util::Collections_EmptyListIterator::next()
{
    return java_cast< ::java::lang::Object* >(Collections_EmptyIterator::next());
}

void java::util::Collections_EmptyListIterator::remove()
{
    Collections_EmptyIterator::remove();
}

java::lang::Class* java::util::Collections_EmptyListIterator::getClass0()
{
    return class_();
}

