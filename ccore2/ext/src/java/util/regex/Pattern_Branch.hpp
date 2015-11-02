// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
        namespace regex
        {
typedef ::SubArray< ::java::util::regex::Pattern_Node, ::java::lang::ObjectArray > Pattern_NodeArray;
        } // regex
    } // util
} // java

struct default_init_tag;

class java::util::regex::Pattern_Branch final
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    Pattern_NodeArray* atoms {  };
    Pattern_Node* conn {  };
    int32_t size {  };

protected:
    void ctor(Pattern_Node* first, Pattern_Node* second, Pattern_Node* branchConn);

public: /* package */
    void add(Pattern_Node* node);
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_Branch(Pattern_Node* first, Pattern_Node* second, Pattern_Node* branchConn);
protected:
    Pattern_Branch(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};
