// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Arrays
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;
    static constexpr int32_t INSERTIONSORT_THRESHOLD { int32_t(7) };

    /*void ctor(); (private) */

public:
    static List* asList(::java::lang::ObjectArray* a);
    static int32_t binarySearch(::int64_tArray* a, int64_t key);
    static int32_t binarySearch(::int32_tArray* a, int32_t key);
    static int32_t binarySearch(::int16_tArray* a, int16_t key);
    static int32_t binarySearch(::char16_tArray* a, char16_t key);
    static int32_t binarySearch(::int8_tArray* a, int8_t key);
    static int32_t binarySearch(::doubleArray* a, double key);
    static int32_t binarySearch(::floatArray* a, float key);
    static int32_t binarySearch(::java::lang::ObjectArray* a, ::java::lang::Object* key);
    static int32_t binarySearch(::java::lang::ObjectArray* a, ::java::lang::Object* key, Comparator* c);
    static int32_t binarySearch(::int64_tArray* a, int32_t fromIndex, int32_t toIndex, int64_t key);
    static int32_t binarySearch(::int32_tArray* a, int32_t fromIndex, int32_t toIndex, int32_t key);
    static int32_t binarySearch(::int16_tArray* a, int32_t fromIndex, int32_t toIndex, int16_t key);
    static int32_t binarySearch(::char16_tArray* a, int32_t fromIndex, int32_t toIndex, char16_t key);
    static int32_t binarySearch(::int8_tArray* a, int32_t fromIndex, int32_t toIndex, int8_t key);
    static int32_t binarySearch(::doubleArray* a, int32_t fromIndex, int32_t toIndex, double key);
    static int32_t binarySearch(::floatArray* a, int32_t fromIndex, int32_t toIndex, float key);
    static int32_t binarySearch(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, ::java::lang::Object* key);
    static int32_t binarySearch(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, ::java::lang::Object* key, Comparator* c);
    /*static int32_t binarySearch0(::int64_tArray* a, int32_t fromIndex, int32_t toIndex, int64_t key); (private) */
    /*static int32_t binarySearch0(::int32_tArray* a, int32_t fromIndex, int32_t toIndex, int32_t key); (private) */
    /*static int32_t binarySearch0(::int16_tArray* a, int32_t fromIndex, int32_t toIndex, int16_t key); (private) */
    /*static int32_t binarySearch0(::char16_tArray* a, int32_t fromIndex, int32_t toIndex, char16_t key); (private) */
    /*static int32_t binarySearch0(::int8_tArray* a, int32_t fromIndex, int32_t toIndex, int8_t key); (private) */
    /*static int32_t binarySearch0(::doubleArray* a, int32_t fromIndex, int32_t toIndex, double key); (private) */
    /*static int32_t binarySearch0(::floatArray* a, int32_t fromIndex, int32_t toIndex, float key); (private) */
    /*static int32_t binarySearch0(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, ::java::lang::Object* key); (private) */
    /*static int32_t binarySearch0(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, ::java::lang::Object* key, Comparator* c); (private) */
    static ::java::lang::ObjectArray* copyOf(::java::lang::ObjectArray* original, int32_t newLength);
    static ::int8_tArray* copyOf(::int8_tArray* original, int32_t newLength);
    static ::int16_tArray* copyOf(::int16_tArray* original, int32_t newLength);
    static ::int32_tArray* copyOf(::int32_tArray* original, int32_t newLength);
    static ::int64_tArray* copyOf(::int64_tArray* original, int32_t newLength);
    static ::char16_tArray* copyOf(::char16_tArray* original, int32_t newLength);
    static ::floatArray* copyOf(::floatArray* original, int32_t newLength);
    static ::doubleArray* copyOf(::doubleArray* original, int32_t newLength);
    static ::boolArray* copyOf(::boolArray* original, int32_t newLength);
    static ::java::lang::ObjectArray* copyOf(::java::lang::ObjectArray* original, int32_t newLength, ::java::lang::Class* newType);
    static ::java::lang::ObjectArray* copyOfRange(::java::lang::ObjectArray* original, int32_t from, int32_t to);
    static ::int8_tArray* copyOfRange(::int8_tArray* original, int32_t from, int32_t to);
    static ::int16_tArray* copyOfRange(::int16_tArray* original, int32_t from, int32_t to);
    static ::int32_tArray* copyOfRange(::int32_tArray* original, int32_t from, int32_t to);
    static ::int64_tArray* copyOfRange(::int64_tArray* original, int32_t from, int32_t to);
    static ::char16_tArray* copyOfRange(::char16_tArray* original, int32_t from, int32_t to);
    static ::floatArray* copyOfRange(::floatArray* original, int32_t from, int32_t to);
    static ::doubleArray* copyOfRange(::doubleArray* original, int32_t from, int32_t to);
    static ::boolArray* copyOfRange(::boolArray* original, int32_t from, int32_t to);
    static ::java::lang::ObjectArray* copyOfRange(::java::lang::ObjectArray* original, int32_t from, int32_t to, ::java::lang::Class* newType);
    static bool deepEquals(::java::lang::ObjectArray* a1, ::java::lang::ObjectArray* a2);

public: /* package */
    static bool deepEquals0(::java::lang::Object* e1, ::java::lang::Object* e2);

public:
    static int32_t deepHashCode(::java::lang::ObjectArray* a);
    static ::java::lang::String* deepToString(::java::lang::ObjectArray* a);
    /*static void deepToString(::java::lang::ObjectArray* a, ::java::lang::StringBuilder* buf, Set* dejaVu); (private) */
    static bool equals(::int64_tArray* a, ::int64_tArray* a2);
    static bool equals(::int32_tArray* a, ::int32_tArray* a2);
    static bool equals(::int16_tArray* a, ::int16_tArray* a2);
    static bool equals(::char16_tArray* a, ::char16_tArray* a2);
    static bool equals(::int8_tArray* a, ::int8_tArray* a2);
    static bool equals(::boolArray* a, ::boolArray* a2);
    static bool equals(::doubleArray* a, ::doubleArray* a2);
    static bool equals(::floatArray* a, ::floatArray* a2);
    static bool equals(::java::lang::ObjectArray* a, ::java::lang::ObjectArray* a2);
    static void fill(::int64_tArray* a, int64_t val);
    static void fill(::int32_tArray* a, int32_t val);
    static void fill(::int16_tArray* a, int16_t val);
    static void fill(::char16_tArray* a, char16_t val);
    static void fill(::int8_tArray* a, int8_t val);
    static void fill(::boolArray* a, bool val);
    static void fill(::doubleArray* a, double val);
    static void fill(::floatArray* a, float val);
    static void fill(::java::lang::ObjectArray* a, ::java::lang::Object* val);
    static void fill(::int64_tArray* a, int32_t fromIndex, int32_t toIndex, int64_t val);
    static void fill(::int32_tArray* a, int32_t fromIndex, int32_t toIndex, int32_t val);
    static void fill(::int16_tArray* a, int32_t fromIndex, int32_t toIndex, int16_t val);
    static void fill(::char16_tArray* a, int32_t fromIndex, int32_t toIndex, char16_t val);
    static void fill(::int8_tArray* a, int32_t fromIndex, int32_t toIndex, int8_t val);
    static void fill(::boolArray* a, int32_t fromIndex, int32_t toIndex, bool val);
    static void fill(::doubleArray* a, int32_t fromIndex, int32_t toIndex, double val);
    static void fill(::floatArray* a, int32_t fromIndex, int32_t toIndex, float val);
    static void fill(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, ::java::lang::Object* val);
    static int32_t hashCode(::int64_tArray* a);
    static int32_t hashCode(::int32_tArray* a);
    static int32_t hashCode(::int16_tArray* a);
    static int32_t hashCode(::char16_tArray* a);
    static int32_t hashCode(::int8_tArray* a);
    static int32_t hashCode(::boolArray* a);
    static int32_t hashCode(::floatArray* a);
    static int32_t hashCode(::doubleArray* a);
    static int32_t hashCode(::java::lang::ObjectArray* a);
    /*static void legacyMergeSort(::java::lang::ObjectArray* a); (private) */
    /*static void legacyMergeSort(::java::lang::ObjectArray* a, Comparator* c); (private) */
    /*static void legacyMergeSort(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex); (private) */
    /*static void legacyMergeSort(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, Comparator* c); (private) */
    /*static void mergeSort(::java::lang::ObjectArray* src, ::java::lang::ObjectArray* dest, int32_t low, int32_t high, int32_t off); (private) */
    /*static void mergeSort(::java::lang::ObjectArray* src, ::java::lang::ObjectArray* dest, int32_t low, int32_t high, int32_t off, Comparator* c); (private) */
    /*static void rangeCheck(int32_t length, int32_t fromIndex, int32_t toIndex); (private) */
    static void sort(::int32_tArray* a);
    static void sort(::int64_tArray* a);
    static void sort(::int16_tArray* a);
    static void sort(::char16_tArray* a);
    static void sort(::int8_tArray* a);
    static void sort(::floatArray* a);
    static void sort(::doubleArray* a);
    static void sort(::java::lang::ObjectArray* a);
    static void sort(::java::lang::ObjectArray* a, Comparator* c);
    static void sort(::int32_tArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::int64_tArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::int16_tArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::char16_tArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::int8_tArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::floatArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::doubleArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex);
    static void sort(::java::lang::ObjectArray* a, int32_t fromIndex, int32_t toIndex, Comparator* c);
    /*static void swap(::java::lang::ObjectArray* x, int32_t a, int32_t b); (private) */
    static ::java::lang::String* toString(::int64_tArray* a);
    static ::java::lang::String* toString(::int32_tArray* a);
    static ::java::lang::String* toString(::int16_tArray* a);
    static ::java::lang::String* toString(::char16_tArray* a);
    static ::java::lang::String* toString(::int8_tArray* a);
    static ::java::lang::String* toString(::boolArray* a);
    static ::java::lang::String* toString(::floatArray* a);
    static ::java::lang::String* toString(::doubleArray* a);
    static ::java::lang::String* toString(::java::lang::ObjectArray* a);

    // Generated
    Arrays();
protected:
    Arrays(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(::java::lang::Object* obj);
    virtual int32_t hashCode();
    virtual ::java::lang::String* toString();

public: /* package */
    static bool& $assertionsDisabled();

private:
    virtual ::java::lang::Class* getClass0();
};
