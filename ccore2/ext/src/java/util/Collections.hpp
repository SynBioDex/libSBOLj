// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Collections
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static constexpr int32_t BINARYSEARCH_THRESHOLD { int32_t(5000) };
    static constexpr int32_t COPY_THRESHOLD { int32_t(10) };
    static List* EMPTY_LIST_;
    static Map* EMPTY_MAP_;
    static Set* EMPTY_SET_;
    static constexpr int32_t FILL_THRESHOLD { int32_t(25) };
    static constexpr int32_t INDEXOFSUBLIST_THRESHOLD { int32_t(35) };
    static constexpr int32_t REPLACEALL_THRESHOLD { int32_t(11) };
    static constexpr int32_t REVERSE_THRESHOLD { int32_t(18) };
    static constexpr int32_t ROTATE_THRESHOLD { int32_t(100) };
    static constexpr int32_t SHUFFLE_THRESHOLD { int32_t(5) };
    static Random* r_;

    /*void ctor(); (private) */

public:
    static bool addAll(Collection* c, ::java::lang::ObjectArray* elements);
    static Queue* asLifoQueue(Deque* deque);
    static int32_t binarySearch(List* list, ::java::lang::Object* key);
    static int32_t binarySearch(List* list, ::java::lang::Object* key, Comparator* c);
    static Collection* checkedCollection(Collection* c, ::java::lang::Class* type);
    static List* checkedList(List* list, ::java::lang::Class* type);
    static Map* checkedMap(Map* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType);
    static Set* checkedSet(Set* s, ::java::lang::Class* type);
    static SortedMap* checkedSortedMap(SortedMap* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType);
    static SortedSet* checkedSortedSet(SortedSet* s, ::java::lang::Class* type);
    static void copy(List* dest, List* src);
    static bool disjoint(Collection* c1, Collection* c2);
    static Enumeration* emptyEnumeration();
    static Iterator* emptyIterator();
    static List* emptyList();
    static ListIterator* emptyListIterator();
    static Map* emptyMap();
    static Set* emptySet();
    static Enumeration* enumeration(Collection* c);

public: /* package */
    static bool eq(::java::lang::Object* o1, ::java::lang::Object* o2);

public:
    static void fill(List* list, ::java::lang::Object* obj);
    static int32_t frequency(Collection* c, ::java::lang::Object* o);
    /*static ::java::lang::Object* get(ListIterator* i, int32_t index); (private) */
    static int32_t indexOfSubList(List* source, List* target);
    /*static int32_t indexedBinarySearch(List* list, ::java::lang::Object* key); (private) */
    /*static int32_t indexedBinarySearch(List* l, ::java::lang::Object* key, Comparator* c); (private) */
    /*static int32_t iteratorBinarySearch(List* list, ::java::lang::Object* key); (private) */
    /*static int32_t iteratorBinarySearch(List* l, ::java::lang::Object* key, Comparator* c); (private) */
    static int32_t lastIndexOfSubList(List* source, List* target);
    static ArrayList* list(Enumeration* e);
    static ::java::lang::Object* max(Collection* coll);
    static ::java::lang::Object* max(Collection* coll, Comparator* comp);
    static ::java::lang::Object* min(Collection* coll);
    static ::java::lang::Object* min(Collection* coll, Comparator* comp);
    static List* nCopies(int32_t n, ::java::lang::Object* o);
    static Set* newSetFromMap(Map* map);
    static bool replaceAll(List* list, ::java::lang::Object* oldVal, ::java::lang::Object* newVal);
    static void reverse(List* list);
    static Comparator* reverseOrder();
    static Comparator* reverseOrder(Comparator* cmp);
    static void rotate(List* list, int32_t distance);
    /*static void rotate1(List* list, int32_t distance); (private) */
    /*static void rotate2(List* list, int32_t distance); (private) */
    static void shuffle(List* list);
    static void shuffle(List* list, Random* rnd);
    static Set* singleton(::java::lang::Object* o);

public: /* package */
    static Iterator* singletonIterator(::java::lang::Object* e);

public:
    static List* singletonList(::java::lang::Object* o);
    static Map* singletonMap(::java::lang::Object* key, ::java::lang::Object* value);
    static void sort(List* list);
    static void sort(List* list, Comparator* c);
    static void swap(List* list, int32_t i, int32_t j);
    /*static void swap(::java::lang::ObjectArray* arr, int32_t i, int32_t j); (private) */
    static Collection* synchronizedCollection(Collection* c);

public: /* package */
    static Collection* synchronizedCollection(Collection* c, ::java::lang::Object* mutex);

public:
    static List* synchronizedList(List* list);

public: /* package */
    static List* synchronizedList(List* list, ::java::lang::Object* mutex);

public:
    static Map* synchronizedMap(Map* m);
    static Set* synchronizedSet(Set* s);

public: /* package */
    static Set* synchronizedSet(Set* s, ::java::lang::Object* mutex);

public:
    static SortedMap* synchronizedSortedMap(SortedMap* m);
    static SortedSet* synchronizedSortedSet(SortedSet* s);
    static Collection* unmodifiableCollection(Collection* c);
    static List* unmodifiableList(List* list);
    static Map* unmodifiableMap(Map* m);
    static Set* unmodifiableSet(Set* s);
    static SortedMap* unmodifiableSortedMap(SortedMap* m);
    static SortedSet* unmodifiableSortedSet(SortedSet* s);

public: /* package */
    static ::java::lang::ObjectArray* zeroLengthArray_(::java::lang::Class* type);

    // Generated

public:
    Collections();
protected:
    Collections(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static List*& EMPTY_LIST();
    static Map*& EMPTY_MAP();
    static Set*& EMPTY_SET();

private:
    static Random*& r();
    virtual ::java::lang::Class* getClass0();
};
