// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Iterator.hpp>

struct default_init_tag;

class java::util::HashMap_HashIterator
    : public virtual ::java::lang::Object
    , public virtual Iterator
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    HashMap_Entry* current {  };
    int32_t expectedModCount {  };
    int32_t index {  };
    HashMap_Entry* next {  };
    HashMap* this$0 {  };

protected:
    void ctor();

public:
    bool hasNext() override;

public: /* package */
    HashMap_Entry* nextEntry();

public:
    void remove() override;

    // Generated

public: /* package */
    HashMap_HashIterator(HashMap *HashMap_this);
protected:
    HashMap_HashIterator(HashMap *HashMap_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    HashMap *HashMap_this;

private:
    virtual ::java::lang::Class* getClass0();
};
