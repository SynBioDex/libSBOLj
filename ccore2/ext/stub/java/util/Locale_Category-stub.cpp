// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Locale_Category.hpp>

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
    } // lang

    namespace util
    {
typedef ::SubArray< ::java::util::Locale_Category, ::java::lang::EnumArray > Locale_CategoryArray;
    } // util
} // java

extern void unimplemented_(const char16_t* name);
java::util::Locale_Category::Locale_Category(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Locale_CategoryArray*& java::util::Locale_Category::$VALUES()
{
    clinit();
    return $VALUES_;
}
java::util::Locale_CategoryArray* java::util::Locale_Category::$VALUES_;
java::util::Locale_Category* java::util::Locale_Category::DISPLAY;
java::util::Locale_Category* java::util::Locale_Category::FORMAT;

/* private: void ::java::util::Locale_Category::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* arg0, ::java::lang::String* arg1, ::java::lang::String* arg2, ::java::lang::String* arg3) */
java::util::Locale_Category* java::util::Locale_Category::valueOf(::java::lang::String* arg0)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale_Category* java::util::Locale_Category::valueOf(::java::lang::String* arg0)");
    return 0;
}

java::util::Locale_CategoryArray* java::util::Locale_Category::values()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale_CategoryArray* java::util::Locale_Category::values()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Locale_Category::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Locale.Category", 25);
    return c;
}

java::lang::Enum* java::util::Locale_Category::valueOf(::java::lang::Class* enumType, ::java::lang::String* name)
{
    return super::valueOf(enumType, name);
}

java::lang::Class* java::util::Locale_Category::getClass0()
{
    return class_();
}

