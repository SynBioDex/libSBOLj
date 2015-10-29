// Generated from /Users/myers/.m2/repository/net/java/dev/stax-utils/stax-utils/20070216/stax-utils-20070216.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javanet/staxutils/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct javanet::staxutils::Indentation
    : public virtual ::java::lang::Object
{

private:
    static ::java::lang::String* DEFAULT_INDENT_;
    static ::java::lang::String* NORMAL_END_OF_LINE_;


public:
    virtual ::java::lang::String* getIndent() = 0;
    virtual ::java::lang::String* getNewLine() = 0;
    virtual void setIndent(::java::lang::String* indent) = 0;
    virtual void setNewLine(::java::lang::String* newLine) = 0;

    // Generated
    static ::java::lang::Class *class_();
    static ::java::lang::String*& DEFAULT_INDENT();
    static ::java::lang::String*& NORMAL_END_OF_LINE();
};
