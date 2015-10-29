// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_UnmodifiableList.hpp>
#include <java/util/RandomAccess.hpp>

struct default_init_tag;

class java::util::Collections_UnmodifiableRandomAccessList
    : public Collections_UnmodifiableList
    , public virtual RandomAccess
{

public:
    typedef Collections_UnmodifiableList super;

private:
    static constexpr int64_t serialVersionUID { int64_t(-2542308836966382001LL) };

protected:
    void ctor(List* list);

public:
    List* subList(int32_t fromIndex, int32_t toIndex) override;
    /*::java::lang::Object* writeReplace(); (private) */

    // Generated

public: /* package */
    Collections_UnmodifiableRandomAccessList(List* list);
protected:
    Collections_UnmodifiableRandomAccessList(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};
