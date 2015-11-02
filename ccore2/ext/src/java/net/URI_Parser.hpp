// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::net::URI_Parser
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::lang::String* input {  };
    int32_t ipv6byteCount {  };
    bool requireServerAuthority {  };

public: /* package */
    URI* this$0 {  };

protected:
    void ctor(::java::lang::String* s);
    /*bool at(int32_t start, int32_t end, char16_t c); (private) */
    /*bool at(int32_t start, int32_t end, ::java::lang::String* s); (private) */
    /*char16_t charAt(int32_t p); (private) */
    /*void checkChar(int32_t p, int64_t lowMask, int64_t highMask, ::java::lang::String* what); (private) */
    /*void checkChars(int32_t start, int32_t end, int64_t lowMask, int64_t highMask, ::java::lang::String* what); (private) */
    /*void fail(::java::lang::String* reason); (private) */
    /*void fail(::java::lang::String* reason, int32_t p); (private) */
    /*void failExpecting(::java::lang::String* expected, int32_t p); (private) */
    /*void failExpecting(::java::lang::String* expected, ::java::lang::String* prior, int32_t p); (private) */

public: /* package */
    virtual void parse(bool rsa);
    /*int32_t parseAuthority(int32_t start, int32_t n); (private) */
    /*int32_t parseHierarchical(int32_t start, int32_t n); (private) */
    /*int32_t parseHostname(int32_t start, int32_t n); (private) */
    /*int32_t parseIPv4Address(int32_t start, int32_t n); (private) */
    /*int32_t parseIPv6Reference(int32_t start, int32_t n); (private) */
    /*int32_t parseServer(int32_t start, int32_t n); (private) */
    /*int32_t scan(int32_t start, int32_t end, char16_t c); (private) */
    /*int32_t scan(int32_t start, int32_t end, ::java::lang::String* err, ::java::lang::String* stop); (private) */
    /*int32_t scan(int32_t start, int32_t n, int64_t lowMask, int64_t highMask); (private) */
    /*int32_t scanByte(int32_t start, int32_t n); (private) */
    /*int32_t scanEscape(int32_t start, int32_t n, char16_t first); (private) */
    /*int32_t scanHexPost(int32_t start, int32_t n); (private) */
    /*int32_t scanHexSeq(int32_t start, int32_t n); (private) */
    /*int32_t scanIPv4Address(int32_t start, int32_t n, bool strict); (private) */
    /*::java::lang::String* substring(int32_t start, int32_t end); (private) */
    /*int32_t takeIPv4Address(int32_t start, int32_t n, ::java::lang::String* expected); (private) */

    // Generated
    URI_Parser(URI *URI_this, ::java::lang::String* s);
protected:
    URI_Parser(URI *URI_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    URI *URI_this;

private:
    virtual ::java::lang::Class* getClass0();
};
