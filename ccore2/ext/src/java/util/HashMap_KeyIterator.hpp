// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/HashMap_HashIterator.hpp>

struct default_init_tag;

class java::util::HashMap_KeyIterator final
    : public HashMap_HashIterator
{

public:
    typedef HashMap_HashIterator super;

public: /* package */
    HashMap* this$0 {  };

    /*void ctor(); (private) */

public:
    ::java::lang::Object* next() override;

    // Generated
    HashMap_KeyIterator(HashMap *HashMap_this);
protected:
    HashMap_KeyIterator(HashMap *HashMap_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};
