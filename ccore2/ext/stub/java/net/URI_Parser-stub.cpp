// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/net/URI_Parser.hpp>

#include <java/net/URI.hpp>

extern void unimplemented_(const char16_t* name);
java::net::URI_Parser::URI_Parser(URI *URI_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , URI_this(URI_this)
{
    clinit();
}

java::net::URI_Parser::URI_Parser(URI *URI_this, ::java::lang::String* s)
    : URI_Parser(URI_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}


void ::java::net::URI_Parser::ctor(::java::lang::String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI_Parser::ctor(::java::lang::String* s)");
}

/* private: bool java::net::URI_Parser::at(int32_t start, int32_t end, char16_t c) */
/* private: bool java::net::URI_Parser::at(int32_t start, int32_t end, ::java::lang::String* s) */
/* private: char16_t java::net::URI_Parser::charAt(int32_t p) */
/* private: void java::net::URI_Parser::checkChar(int32_t p, int64_t lowMask, int64_t highMask, ::java::lang::String* what) */
/* private: void java::net::URI_Parser::checkChars(int32_t start, int32_t end, int64_t lowMask, int64_t highMask, ::java::lang::String* what) */
/* private: void java::net::URI_Parser::fail(::java::lang::String* reason) */
/* private: void java::net::URI_Parser::fail(::java::lang::String* reason, int32_t p) */
/* private: void java::net::URI_Parser::failExpecting(::java::lang::String* expected, int32_t p) */
/* private: void java::net::URI_Parser::failExpecting(::java::lang::String* expected, ::java::lang::String* prior, int32_t p) */
void java::net::URI_Parser::parse(bool rsa)
{ /* stub */
    unimplemented_(u"void java::net::URI_Parser::parse(bool rsa)");
}

/* private: int32_t java::net::URI_Parser::parseAuthority(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::parseHierarchical(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::parseHostname(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::parseIPv4Address(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::parseIPv6Reference(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::parseServer(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::scan(int32_t start, int32_t end, char16_t c) */
/* private: int32_t java::net::URI_Parser::scan(int32_t start, int32_t end, ::java::lang::String* err, ::java::lang::String* stop) */
/* private: int32_t java::net::URI_Parser::scan(int32_t start, int32_t n, int64_t lowMask, int64_t highMask) */
/* private: int32_t java::net::URI_Parser::scanByte(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::scanEscape(int32_t start, int32_t n, char16_t first) */
/* private: int32_t java::net::URI_Parser::scanHexPost(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::scanHexSeq(int32_t start, int32_t n) */
/* private: int32_t java::net::URI_Parser::scanIPv4Address(int32_t start, int32_t n, bool strict) */
/* private: java::lang::String* java::net::URI_Parser::substring(int32_t start, int32_t end) */
/* private: int32_t java::net::URI_Parser::takeIPv4Address(int32_t start, int32_t n, ::java::lang::String* expected) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::net::URI_Parser::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.net.URI.Parser", 19);
    return c;
}

java::lang::Class* java::net::URI_Parser::getClass0()
{
    return class_();
}

