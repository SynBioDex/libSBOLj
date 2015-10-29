// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Comparable.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::net::URI final
    : public virtual ::java::lang::Object
    , public ::java::lang::Comparable
    , public ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;
    static int64_t H_ALPHA_;
    static int64_t H_ALPHANUM_;
    static int64_t H_DASH_;
    static constexpr int64_t H_DIGIT { int64_t(0LL) };
    static int64_t H_DOT_;
    static constexpr int64_t H_ESCAPED { int64_t(0LL) };
    static int64_t H_HEX_;
    static int64_t H_LEFT_BRACKET_;
    static int64_t H_LOWALPHA_;
    static int64_t H_MARK_;
    static int64_t H_PATH_;
    static int64_t H_PCHAR_;
    static int64_t H_REG_NAME_;
    static int64_t H_RESERVED_;
    static int64_t H_SCHEME_;
    static int64_t H_SERVER_;
    static int64_t H_SERVER_PERCENT_;
    static int64_t H_UNRESERVED_;
    static int64_t H_UPALPHA_;
    static int64_t H_URIC_;
    static int64_t H_URIC_NO_SLASH_;
    static int64_t H_USERINFO_;
    static constexpr int64_t L_ALPHA { int64_t(0LL) };
    static int64_t L_ALPHANUM_;
    static int64_t L_DASH_;
    static int64_t L_DIGIT_;
    static int64_t L_DOT_;
    static constexpr int64_t L_ESCAPED { int64_t(1LL) };
    static int64_t L_HEX_;
    static int64_t L_LEFT_BRACKET_;
    static constexpr int64_t L_LOWALPHA { int64_t(0LL) };
    static int64_t L_MARK_;
    static int64_t L_PATH_;
    static int64_t L_PCHAR_;
    static int64_t L_REG_NAME_;
    static int64_t L_RESERVED_;
    static int64_t L_SCHEME_;
    static int64_t L_SERVER_;
    static int64_t L_SERVER_PERCENT_;
    static int64_t L_UNRESERVED_;
    static constexpr int64_t L_UPALPHA { int64_t(0LL) };
    static int64_t L_URIC_;
    static int64_t L_URIC_NO_SLASH_;
    static int64_t L_USERINFO_;
    ::java::lang::String* authority {  };
    std::atomic< ::java::lang::String* > decodedAuthority {  };
    std::atomic< ::java::lang::String* > decodedFragment {  };
    std::atomic< ::java::lang::String* > decodedPath {  };
    std::atomic< ::java::lang::String* > decodedQuery {  };
    std::atomic< ::java::lang::String* > decodedSchemeSpecificPart {  };
    std::atomic< ::java::lang::String* > decodedUserInfo {  };
    ::java::lang::String* fragment {  };
    std::atomic< int32_t > hash_ {  };
    static ::char16_tArray* hexDigits_;
    ::java::lang::String* host {  };
    ::java::lang::String* path {  };
    int32_t port {  };
    ::java::lang::String* query {  };
    ::java::lang::String* scheme {  };
    std::atomic< ::java::lang::String* > schemeSpecificPart {  };

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(-6052424284110960213LL) };

private:
    std::atomic< ::java::lang::String* > string {  };
    ::java::lang::String* userInfo {  };

    /*void ctor(); (private) */
protected:
    void ctor(::java::lang::String* str);
    void ctor(::java::lang::String* scheme, ::java::lang::String* ssp, ::java::lang::String* fragment);
    void ctor(::java::lang::String* scheme, ::java::lang::String* host, ::java::lang::String* path, ::java::lang::String* fragment);
    void ctor(::java::lang::String* scheme, ::java::lang::String* authority, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment);
    void ctor(::java::lang::String* scheme, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment);
    /*void appendAuthority(::java::lang::StringBuffer* sb, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port); (private) */
    /*static void appendEncoded(::java::lang::StringBuffer* sb, char16_t c); (private) */
    /*static void appendEscape(::java::lang::StringBuffer* sb, int8_t b); (private) */
    /*void appendFragment(::java::lang::StringBuffer* sb, ::java::lang::String* fragment); (private) */
    /*void appendSchemeSpecificPart(::java::lang::StringBuffer* sb, ::java::lang::String* opaquePart, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query); (private) */
    /*static void checkPath(::java::lang::String* s, ::java::lang::String* scheme, ::java::lang::String* path); (private) */
    /*static int32_t compare(::java::lang::String* s, ::java::lang::String* t); (private) */
    /*static int32_t compareIgnoringCase(::java::lang::String* s, ::java::lang::String* t); (private) */

public:
    int32_t compareTo(URI* that);
    static URI* create(::java::lang::String* str);
    /*static int32_t decode(char16_t c); (private) */
    /*static ::java::lang::String* decode(::java::lang::String* s); (private) */
    /*static int8_t decode(char16_t c1, char16_t c2); (private) */
    /*void defineSchemeSpecificPart(); (private) */
    /*void defineString(); (private) */
    /*static ::java::lang::String* encode(::java::lang::String* s); (private) */
    /*static bool equal(::java::lang::String* s, ::java::lang::String* t); (private) */
    /*static bool equalIgnoringCase(::java::lang::String* s, ::java::lang::String* t); (private) */
    bool equals(::java::lang::Object* ob) override;
    ::java::lang::String* getAuthority();
    ::java::lang::String* getFragment();
    ::java::lang::String* getHost();
    ::java::lang::String* getPath();
    int32_t getPort();
    ::java::lang::String* getQuery();
    ::java::lang::String* getRawAuthority();
    ::java::lang::String* getRawFragment();
    ::java::lang::String* getRawPath();
    ::java::lang::String* getRawQuery();
    ::java::lang::String* getRawSchemeSpecificPart();
    ::java::lang::String* getRawUserInfo();
    ::java::lang::String* getScheme();
    ::java::lang::String* getSchemeSpecificPart();
    ::java::lang::String* getUserInfo();
    /*static int32_t hash(int32_t hash, ::java::lang::String* s); (private) */
    int32_t hashCode() override;
    /*static int32_t hashIgnoringCase(int32_t hash, ::java::lang::String* s); (private) */
    /*static int64_t highMask(::java::lang::String* chars); (private) */
    /*static int64_t highMask(char16_t first, char16_t last); (private) */
    bool isAbsolute();
    bool isOpaque();
    /*static int32_t join(::char16_tArray* path, ::int32_tArray* segs); (private) */
    /*static int64_t lowMask(::java::lang::String* chars); (private) */
    /*static int64_t lowMask(char16_t first, char16_t last); (private) */
    /*static bool match(char16_t c, int64_t lowMask, int64_t highMask); (private) */
    /*static void maybeAddLeadingDot(::char16_tArray* path, ::int32_tArray* segs); (private) */
    /*static int32_t needsNormalization(::java::lang::String* path); (private) */
    URI* normalize();
    /*static URI* normalize(URI* u); (private) */
    /*static ::java::lang::String* normalize(::java::lang::String* ps); (private) */
    URI* parseServerAuthority();
    /*static ::java::lang::String* quote(::java::lang::String* s, int64_t lowMask, int64_t highMask); (private) */
    /*void readObject(::java::io::ObjectInputStream* is); (private) */
    URI* relativize(URI* uri);
    /*static URI* relativize(URI* base, URI* child); (private) */
    /*static void removeDots(::char16_tArray* path, ::int32_tArray* segs); (private) */
    URI* resolve(URI* uri);
    URI* resolve(::java::lang::String* str);
    /*static URI* resolve(URI* base, URI* child); (private) */
    /*static ::java::lang::String* resolvePath(::java::lang::String* base, ::java::lang::String* child, bool absolute); (private) */
    /*static void split(::char16_tArray* path, ::int32_tArray* segs); (private) */
    ::java::lang::String* toASCIIString();
    /*static int32_t toLower(char16_t c); (private) */
    ::java::lang::String* toString() override;
    /*::java::lang::String* toString(::java::lang::String* scheme, ::java::lang::String* opaquePart, ::java::lang::String* authority, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment); (private) */
    URL* toURL();
    /*void writeObject(::java::io::ObjectOutputStream* os); (private) */

    // Generated
    URI(::java::lang::String* str);
    URI(::java::lang::String* scheme, ::java::lang::String* ssp, ::java::lang::String* fragment);
    URI(::java::lang::String* scheme, ::java::lang::String* host, ::java::lang::String* path, ::java::lang::String* fragment);
    URI(::java::lang::String* scheme, ::java::lang::String* authority, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment);
    URI(::java::lang::String* scheme, ::java::lang::String* userInfo, ::java::lang::String* host, int32_t port, ::java::lang::String* path, ::java::lang::String* query, ::java::lang::String* fragment);
protected:
    URI(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(::java::lang::Object* o) override;

public: /* package */
    static bool& $assertionsDisabled();

private:
    static int64_t& H_ALPHA();
    static int64_t& H_ALPHANUM();
    static int64_t& H_DASH();
    static int64_t& H_DOT();
    static int64_t& H_HEX();
    static int64_t& H_LEFT_BRACKET();
    static int64_t& H_LOWALPHA();
    static int64_t& H_MARK();
    static int64_t& H_PATH();
    static int64_t& H_PCHAR();
    static int64_t& H_REG_NAME();
    static int64_t& H_RESERVED();
    static int64_t& H_SCHEME();
    static int64_t& H_SERVER();
    static int64_t& H_SERVER_PERCENT();
    static int64_t& H_UNRESERVED();
    static int64_t& H_UPALPHA();
    static int64_t& H_URIC();
    static int64_t& H_URIC_NO_SLASH();
    static int64_t& H_USERINFO();
    static int64_t& L_ALPHANUM();
    static int64_t& L_DASH();
    static int64_t& L_DIGIT();
    static int64_t& L_DOT();
    static int64_t& L_HEX();
    static int64_t& L_LEFT_BRACKET();
    static int64_t& L_MARK();
    static int64_t& L_PATH();
    static int64_t& L_PCHAR();
    static int64_t& L_REG_NAME();
    static int64_t& L_RESERVED();
    static int64_t& L_SCHEME();
    static int64_t& L_SERVER();
    static int64_t& L_SERVER_PERCENT();
    static int64_t& L_UNRESERVED();
    static int64_t& L_URIC();
    static int64_t& L_URIC_NO_SLASH();
    static int64_t& L_USERINFO();
    static ::char16_tArray*& hexDigits();
    virtual ::java::lang::Class* getClass0();
};
