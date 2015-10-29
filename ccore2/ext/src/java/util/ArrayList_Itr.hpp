// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Iterator.hpp>

struct default_init_tag;

class java::util::ArrayList_Itr
    : public virtual ::java::lang::Object
    , public virtual Iterator
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    int32_t cursor {  };
    int32_t expectedModCount {  };
    int32_t lastRet {  };
    ArrayList* this$0 {  };

    /*void ctor(); (private) */
    void checkForComodification();

public:
    bool hasNext() override;
    ::java::lang::Object* next() override;
    void remove() override;

    // Generated
    ArrayList_Itr(ArrayList *ArrayList_this);
protected:
    ArrayList_Itr(ArrayList *ArrayList_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    ArrayList *ArrayList_this;

private:
    virtual ::java::lang::Class* getClass0();
};
