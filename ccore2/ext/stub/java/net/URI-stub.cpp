// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/net/URI.hpp>

extern void unimplemented_(const char16_t* name);
java::net::URI::URI(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::net::URI::URI(::java::lang::String* str)
    : URI(*static_cast< ::default_init_tag* >(0))
{
    ctor(str);
}

java::net::URI::URI(::java::lang::String* scheme, ::java::lang::String* ssp, ::java::lang::String* fragment)
    : URI(*static_cast< ::default_init_tag* >(0))
{
    ctor(scheme, ssp, fragment);
}

java::net::URI::URI(::java::lang::String* scheme, ::java::lang::String* host, ::java::lang::String* path, ::java::lang::String* fragment)
    : URI(*static_cast< ::default_init_tag* >(0))
{
    ctor(scheme, host, path, fragment);
}

java::net::URI::URI(::java::lang::String* scheme, ::java::lang::String* authority, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)
    : URI(*static_cast< ::default_init_tag* >(0))
{
    ctor(scheme, authority, path, query, fragment);
}

java::net::URI::URI(::java::lang::String* scheme, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)
    : URI(*static_cast< ::default_init_tag* >(0))
{
    ctor(scheme, userInfo, host, port, path, query, fragment);
}

bool& java::net::URI::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::net::URI::$assertionsDisabled_;
int64_t& java::net::URI::H_ALPHA()
{
    clinit();
    return H_ALPHA_;
}
int64_t java::net::URI::H_ALPHA_;
int64_t& java::net::URI::H_ALPHANUM()
{
    clinit();
    return H_ALPHANUM_;
}
int64_t java::net::URI::H_ALPHANUM_;
int64_t& java::net::URI::H_DASH()
{
    clinit();
    return H_DASH_;
}
int64_t java::net::URI::H_DASH_;
constexpr int64_t java::net::URI::H_DIGIT;
int64_t& java::net::URI::H_DOT()
{
    clinit();
    return H_DOT_;
}
int64_t java::net::URI::H_DOT_;
constexpr int64_t java::net::URI::H_ESCAPED;
int64_t& java::net::URI::H_HEX()
{
    clinit();
    return H_HEX_;
}
int64_t java::net::URI::H_HEX_;
int64_t& java::net::URI::H_LEFT_BRACKET()
{
    clinit();
    return H_LEFT_BRACKET_;
}
int64_t java::net::URI::H_LEFT_BRACKET_;
int64_t& java::net::URI::H_LOWALPHA()
{
    clinit();
    return H_LOWALPHA_;
}
int64_t java::net::URI::H_LOWALPHA_;
int64_t& java::net::URI::H_MARK()
{
    clinit();
    return H_MARK_;
}
int64_t java::net::URI::H_MARK_;
int64_t& java::net::URI::H_PATH()
{
    clinit();
    return H_PATH_;
}
int64_t java::net::URI::H_PATH_;
int64_t& java::net::URI::H_PCHAR()
{
    clinit();
    return H_PCHAR_;
}
int64_t java::net::URI::H_PCHAR_;
int64_t& java::net::URI::H_REG_NAME()
{
    clinit();
    return H_REG_NAME_;
}
int64_t java::net::URI::H_REG_NAME_;
int64_t& java::net::URI::H_RESERVED()
{
    clinit();
    return H_RESERVED_;
}
int64_t java::net::URI::H_RESERVED_;
int64_t& java::net::URI::H_SCHEME()
{
    clinit();
    return H_SCHEME_;
}
int64_t java::net::URI::H_SCHEME_;
int64_t& java::net::URI::H_SERVER()
{
    clinit();
    return H_SERVER_;
}
int64_t java::net::URI::H_SERVER_;
int64_t& java::net::URI::H_SERVER_PERCENT()
{
    clinit();
    return H_SERVER_PERCENT_;
}
int64_t java::net::URI::H_SERVER_PERCENT_;
int64_t& java::net::URI::H_UNRESERVED()
{
    clinit();
    return H_UNRESERVED_;
}
int64_t java::net::URI::H_UNRESERVED_;
int64_t& java::net::URI::H_UPALPHA()
{
    clinit();
    return H_UPALPHA_;
}
int64_t java::net::URI::H_UPALPHA_;
int64_t& java::net::URI::H_URIC()
{
    clinit();
    return H_URIC_;
}
int64_t java::net::URI::H_URIC_;
int64_t& java::net::URI::H_URIC_NO_SLASH()
{
    clinit();
    return H_URIC_NO_SLASH_;
}
int64_t java::net::URI::H_URIC_NO_SLASH_;
int64_t& java::net::URI::H_USERINFO()
{
    clinit();
    return H_USERINFO_;
}
int64_t java::net::URI::H_USERINFO_;
constexpr int64_t java::net::URI::L_ALPHA;
int64_t& java::net::URI::L_ALPHANUM()
{
    clinit();
    return L_ALPHANUM_;
}
int64_t java::net::URI::L_ALPHANUM_;
int64_t& java::net::URI::L_DASH()
{
    clinit();
    return L_DASH_;
}
int64_t java::net::URI::L_DASH_;
int64_t& java::net::URI::L_DIGIT()
{
    clinit();
    return L_DIGIT_;
}
int64_t java::net::URI::L_DIGIT_;
int64_t& java::net::URI::L_DOT()
{
    clinit();
    return L_DOT_;
}
int64_t java::net::URI::L_DOT_;
constexpr int64_t java::net::URI::L_ESCAPED;
int64_t& java::net::URI::L_HEX()
{
    clinit();
    return L_HEX_;
}
int64_t java::net::URI::L_HEX_;
int64_t& java::net::URI::L_LEFT_BRACKET()
{
    clinit();
    return L_LEFT_BRACKET_;
}
int64_t java::net::URI::L_LEFT_BRACKET_;
constexpr int64_t java::net::URI::L_LOWALPHA;
int64_t& java::net::URI::L_MARK()
{
    clinit();
    return L_MARK_;
}
int64_t java::net::URI::L_MARK_;
int64_t& java::net::URI::L_PATH()
{
    clinit();
    return L_PATH_;
}
int64_t java::net::URI::L_PATH_;
int64_t& java::net::URI::L_PCHAR()
{
    clinit();
    return L_PCHAR_;
}
int64_t java::net::URI::L_PCHAR_;
int64_t& java::net::URI::L_REG_NAME()
{
    clinit();
    return L_REG_NAME_;
}
int64_t java::net::URI::L_REG_NAME_;
int64_t& java::net::URI::L_RESERVED()
{
    clinit();
    return L_RESERVED_;
}
int64_t java::net::URI::L_RESERVED_;
int64_t& java::net::URI::L_SCHEME()
{
    clinit();
    return L_SCHEME_;
}
int64_t java::net::URI::L_SCHEME_;
int64_t& java::net::URI::L_SERVER()
{
    clinit();
    return L_SERVER_;
}
int64_t java::net::URI::L_SERVER_;
int64_t& java::net::URI::L_SERVER_PERCENT()
{
    clinit();
    return L_SERVER_PERCENT_;
}
int64_t java::net::URI::L_SERVER_PERCENT_;
int64_t& java::net::URI::L_UNRESERVED()
{
    clinit();
    return L_UNRESERVED_;
}
int64_t java::net::URI::L_UNRESERVED_;
constexpr int64_t java::net::URI::L_UPALPHA;
int64_t& java::net::URI::L_URIC()
{
    clinit();
    return L_URIC_;
}
int64_t java::net::URI::L_URIC_;
int64_t& java::net::URI::L_URIC_NO_SLASH()
{
    clinit();
    return L_URIC_NO_SLASH_;
}
int64_t java::net::URI::L_URIC_NO_SLASH_;
int64_t& java::net::URI::L_USERINFO()
{
    clinit();
    return L_USERINFO_;
}
int64_t java::net::URI::L_USERINFO_;
char16_tArray*& java::net::URI::hexDigits()
{
    clinit();
    return hexDigits_;
}
char16_tArray* java::net::URI::hexDigits_;
constexpr int64_t java::net::URI::serialVersionUID;

/* private: void ::java::net::URI::ctor() */
void ::java::net::URI::ctor(::java::lang::String* str)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI::ctor(::java::lang::String* str)");
}

void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* ssp, ::java::lang::String* fragment)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* ssp, ::java::lang::String* fragment)");
}

void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* host, ::java::lang::String* path, ::java::lang::String* fragment)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* host, ::java::lang::String* path, ::java::lang::String* fragment)");
}

void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* authority, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* authority, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)");
}

void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::net::URI::ctor(::java::lang::String* scheme, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment)");
}

/* private: void java::net::URI::appendAuthority(::java::lang::StringBuffer* sb, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port) */
/* private: void java::net::URI::appendEncoded(::java::lang::StringBuffer* sb, char16_t c) */
/* private: void java::net::URI::appendEscape(::java::lang::StringBuffer* sb, int8_t b) */
/* private: void java::net::URI::appendFragment(::java::lang::StringBuffer* sb, ::java::lang::String* fragment) */
/* private: void java::net::URI::appendSchemeSpecificPart(::java::lang::StringBuffer* sb, ::java::lang::String* opaquePart, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query) */
/* private: void java::net::URI::checkPath(::java::lang::String* s, ::java::lang::String* scheme, ::java::lang::String* path) */
/* private: int32_t java::net::URI::compare(::java::lang::String* s, ::java::lang::String* t) */
/* private: int32_t java::net::URI::compareIgnoringCase(::java::lang::String* s, ::java::lang::String* t) */
int32_t java::net::URI::compareTo(URI* that)
{ /* stub */
    unimplemented_(u"int32_t java::net::URI::compareTo(URI* that)");
    return 0;
}

int32_t java::net::URI::compareTo(::java::lang::Object* o)
{ 
    return compareTo(dynamic_cast< URI* >(o));
}

java::net::URI* java::net::URI::create(::java::lang::String* str)
{ /* stub */
    clinit();
    unimplemented_(u"java::net::URI* java::net::URI::create(::java::lang::String* str)");
    return 0;
}

/* private: int32_t java::net::URI::decode(char16_t c) */
/* private: java::lang::String* java::net::URI::decode(::java::lang::String* s) */
/* private: int8_t java::net::URI::decode(char16_t c1, char16_t c2) */
/* private: void java::net::URI::defineSchemeSpecificPart() */
/* private: void java::net::URI::defineString() */
/* private: java::lang::String* java::net::URI::encode(::java::lang::String* s) */
/* private: bool java::net::URI::equal(::java::lang::String* s, ::java::lang::String* t) */
/* private: bool java::net::URI::equalIgnoringCase(::java::lang::String* s, ::java::lang::String* t) */
bool java::net::URI::equals(::java::lang::Object* ob)
{ /* stub */
    unimplemented_(u"bool java::net::URI::equals(::java::lang::Object* ob)");
    return 0;
}

java::lang::String* java::net::URI::getAuthority()
{ /* stub */
return authority ; /* getter */
}

java::lang::String* java::net::URI::getFragment()
{ /* stub */
return fragment ; /* getter */
}

java::lang::String* java::net::URI::getHost()
{ /* stub */
return host ; /* getter */
}

java::lang::String* java::net::URI::getPath()
{ /* stub */
return path ; /* getter */
}

int32_t java::net::URI::getPort()
{ /* stub */
return port ; /* getter */
}

java::lang::String* java::net::URI::getQuery()
{ /* stub */
return query ; /* getter */
}

java::lang::String* java::net::URI::getRawAuthority()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawAuthority()");
    return 0;
}

java::lang::String* java::net::URI::getRawFragment()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawFragment()");
    return 0;
}

java::lang::String* java::net::URI::getRawPath()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawPath()");
    return 0;
}

java::lang::String* java::net::URI::getRawQuery()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawQuery()");
    return 0;
}

java::lang::String* java::net::URI::getRawSchemeSpecificPart()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawSchemeSpecificPart()");
    return 0;
}

java::lang::String* java::net::URI::getRawUserInfo()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::getRawUserInfo()");
    return 0;
}

java::lang::String* java::net::URI::getScheme()
{ /* stub */
return scheme ; /* getter */
}

java::lang::String* java::net::URI::getSchemeSpecificPart()
{ /* stub */
return schemeSpecificPart ; /* getter */
}

java::lang::String* java::net::URI::getUserInfo()
{ /* stub */
return userInfo ; /* getter */
}

/* private: int32_t java::net::URI::hash(int32_t hash, ::java::lang::String* s) */
int32_t java::net::URI::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::net::URI::hashCode()");
    return 0;
}

/* private: int32_t java::net::URI::hashIgnoringCase(int32_t hash, ::java::lang::String* s) */
/* private: int64_t java::net::URI::highMask(::java::lang::String* chars) */
/* private: int64_t java::net::URI::highMask(char16_t first, char16_t last) */
bool java::net::URI::isAbsolute()
{ /* stub */
    unimplemented_(u"bool java::net::URI::isAbsolute()");
    return 0;
}

bool java::net::URI::isOpaque()
{ /* stub */
    unimplemented_(u"bool java::net::URI::isOpaque()");
    return 0;
}

/* private: int32_t java::net::URI::join(::char16_tArray* path, ::int32_tArray* segs) */
/* private: int64_t java::net::URI::lowMask(::java::lang::String* chars) */
/* private: int64_t java::net::URI::lowMask(char16_t first, char16_t last) */
/* private: bool java::net::URI::match(char16_t c, int64_t lowMask, int64_t highMask) */
/* private: void java::net::URI::maybeAddLeadingDot(::char16_tArray* path, ::int32_tArray* segs) */
/* private: int32_t java::net::URI::needsNormalization(::java::lang::String* path) */
java::net::URI* java::net::URI::normalize()
{ /* stub */
    unimplemented_(u"java::net::URI* java::net::URI::normalize()");
    return 0;
}

/* private: java::net::URI* java::net::URI::normalize(URI* u) */
/* private: java::lang::String* java::net::URI::normalize(::java::lang::String* ps) */
java::net::URI* java::net::URI::parseServerAuthority()
{ /* stub */
    unimplemented_(u"java::net::URI* java::net::URI::parseServerAuthority()");
    return 0;
}

/* private: java::lang::String* java::net::URI::quote(::java::lang::String* s, int64_t lowMask, int64_t highMask) */
/* private: void java::net::URI::readObject(::java::io::ObjectInputStream* is) */
java::net::URI* java::net::URI::relativize(URI* uri)
{ /* stub */
    unimplemented_(u"java::net::URI* java::net::URI::relativize(URI* uri)");
    return 0;
}

/* private: java::net::URI* java::net::URI::relativize(URI* base, URI* child) */
/* private: void java::net::URI::removeDots(::char16_tArray* path, ::int32_tArray* segs) */
java::net::URI* java::net::URI::resolve(URI* uri)
{ /* stub */
    unimplemented_(u"java::net::URI* java::net::URI::resolve(URI* uri)");
    return 0;
}

java::net::URI* java::net::URI::resolve(::java::lang::String* str)
{ /* stub */
    unimplemented_(u"java::net::URI* java::net::URI::resolve(::java::lang::String* str)");
    return 0;
}

/* private: java::net::URI* java::net::URI::resolve(URI* base, URI* child) */
/* private: java::lang::String* java::net::URI::resolvePath(::java::lang::String* base, ::java::lang::String* child, bool absolute) */
/* private: void java::net::URI::split(::char16_tArray* path, ::int32_tArray* segs) */
java::lang::String* java::net::URI::toASCIIString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::toASCIIString()");
    return 0;
}

/* private: int32_t java::net::URI::toLower(char16_t c) */
java::lang::String* java::net::URI::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::net::URI::toString()");
    return 0;
}

/* private: java::lang::String* java::net::URI::toString(::java::lang::String* scheme, ::java::lang::String* opaquePart, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment) */
java::net::URL* java::net::URI::toURL()
{ /* stub */
    unimplemented_(u"java::net::URL* java::net::URI::toURL()");
    return 0;
}

/* private: void java::net::URI::writeObject(::java::io::ObjectOutputStream* os) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::net::URI::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.net.URI", 12);
    return c;
}

java::lang::Class* java::net::URI::getClass0()
{
    return class_();
}

