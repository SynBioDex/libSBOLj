// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Map.hpp>

struct java::util::SortedMap
    : public virtual Map
{

    virtual Comparator* comparator() = 0;
    /*Set* entrySet(); (already declared) */
    virtual ::java::lang::Object* firstKey() = 0;
    virtual SortedMap* headMap(::java::lang::Object* toKey) = 0;
    /*Set* keySet(); (already declared) */
    virtual ::java::lang::Object* lastKey() = 0;
    virtual SortedMap* subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey) = 0;
    virtual SortedMap* tailMap(::java::lang::Object* fromKey) = 0;
    /*Collection* values(); (already declared) */

    // Generated
    static ::java::lang::Class *class_();
};
