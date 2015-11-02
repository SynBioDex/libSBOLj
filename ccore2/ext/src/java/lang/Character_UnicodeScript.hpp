// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Enum.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::Enum, ObjectArray, ComparableArray, ::java::io::SerializableArray > EnumArray;
typedef ::SubArray< ::java::lang::Character_UnicodeScript, EnumArray > Character_UnicodeScriptArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::Character_UnicodeScript final
    : public Enum
{

public:
    typedef Enum super;

private:
    static Character_UnicodeScriptArray* $VALUES_;

public:
    static Character_UnicodeScript* ARABIC;
    static Character_UnicodeScript* ARMENIAN;
    static Character_UnicodeScript* AVESTAN;
    static Character_UnicodeScript* BALINESE;
    static Character_UnicodeScript* BAMUM;
    static Character_UnicodeScript* BATAK;
    static Character_UnicodeScript* BENGALI;
    static Character_UnicodeScript* BOPOMOFO;
    static Character_UnicodeScript* BRAHMI;
    static Character_UnicodeScript* BRAILLE;
    static Character_UnicodeScript* BUGINESE;
    static Character_UnicodeScript* BUHID;
    static Character_UnicodeScript* CANADIAN_ABORIGINAL;
    static Character_UnicodeScript* CARIAN;
    static Character_UnicodeScript* CHAM;
    static Character_UnicodeScript* CHEROKEE;
    static Character_UnicodeScript* COMMON;
    static Character_UnicodeScript* COPTIC;
    static Character_UnicodeScript* CUNEIFORM;
    static Character_UnicodeScript* CYPRIOT;
    static Character_UnicodeScript* CYRILLIC;
    static Character_UnicodeScript* DESERET;
    static Character_UnicodeScript* DEVANAGARI;
    static Character_UnicodeScript* EGYPTIAN_HIEROGLYPHS;
    static Character_UnicodeScript* ETHIOPIC;
    static Character_UnicodeScript* GEORGIAN;
    static Character_UnicodeScript* GLAGOLITIC;
    static Character_UnicodeScript* GOTHIC;
    static Character_UnicodeScript* GREEK;
    static Character_UnicodeScript* GUJARATI;
    static Character_UnicodeScript* GURMUKHI;
    static Character_UnicodeScript* HAN;
    static Character_UnicodeScript* HANGUL;
    static Character_UnicodeScript* HANUNOO;
    static Character_UnicodeScript* HEBREW;
    static Character_UnicodeScript* HIRAGANA;
    static Character_UnicodeScript* IMPERIAL_ARAMAIC;
    static Character_UnicodeScript* INHERITED;
    static Character_UnicodeScript* INSCRIPTIONAL_PAHLAVI;
    static Character_UnicodeScript* INSCRIPTIONAL_PARTHIAN;
    static Character_UnicodeScript* JAVANESE;
    static Character_UnicodeScript* KAITHI;
    static Character_UnicodeScript* KANNADA;
    static Character_UnicodeScript* KATAKANA;
    static Character_UnicodeScript* KAYAH_LI;
    static Character_UnicodeScript* KHAROSHTHI;
    static Character_UnicodeScript* KHMER;
    static Character_UnicodeScript* LAO;
    static Character_UnicodeScript* LATIN;
    static Character_UnicodeScript* LEPCHA;
    static Character_UnicodeScript* LIMBU;
    static Character_UnicodeScript* LINEAR_B;
    static Character_UnicodeScript* LISU;
    static Character_UnicodeScript* LYCIAN;
    static Character_UnicodeScript* LYDIAN;
    static Character_UnicodeScript* MALAYALAM;
    static Character_UnicodeScript* MANDAIC;
    static Character_UnicodeScript* MEETEI_MAYEK;
    static Character_UnicodeScript* MONGOLIAN;
    static Character_UnicodeScript* MYANMAR;
    static Character_UnicodeScript* NEW_TAI_LUE;
    static Character_UnicodeScript* NKO;
    static Character_UnicodeScript* OGHAM;
    static Character_UnicodeScript* OLD_ITALIC;
    static Character_UnicodeScript* OLD_PERSIAN;
    static Character_UnicodeScript* OLD_SOUTH_ARABIAN;
    static Character_UnicodeScript* OLD_TURKIC;
    static Character_UnicodeScript* OL_CHIKI;
    static Character_UnicodeScript* ORIYA;
    static Character_UnicodeScript* OSMANYA;
    static Character_UnicodeScript* PHAGS_PA;
    static Character_UnicodeScript* PHOENICIAN;
    static Character_UnicodeScript* REJANG;
    static Character_UnicodeScript* RUNIC;
    static Character_UnicodeScript* SAMARITAN;
    static Character_UnicodeScript* SAURASHTRA;
    static Character_UnicodeScript* SHAVIAN;
    static Character_UnicodeScript* SINHALA;
    static Character_UnicodeScript* SUNDANESE;
    static Character_UnicodeScript* SYLOTI_NAGRI;
    static Character_UnicodeScript* SYRIAC;
    static Character_UnicodeScript* TAGALOG;
    static Character_UnicodeScript* TAGBANWA;
    static Character_UnicodeScript* TAI_LE;
    static Character_UnicodeScript* TAI_THAM;
    static Character_UnicodeScript* TAI_VIET;
    static Character_UnicodeScript* TAMIL;
    static Character_UnicodeScript* TELUGU;
    static Character_UnicodeScript* THAANA;
    static Character_UnicodeScript* THAI;
    static Character_UnicodeScript* TIBETAN;
    static Character_UnicodeScript* TIFINAGH;
    static Character_UnicodeScript* UGARITIC;
    static Character_UnicodeScript* UNKNOWN;
    static Character_UnicodeScript* VAI;
    static Character_UnicodeScript* YI;

private:
    static ::java::util::HashMap* aliases_;
    static ::int32_tArray* scriptStarts_;
    static Character_UnicodeScriptArray* scripts_;

    /*void ctor(::java::lang::String* name, int ordinal); (private) */

public:
    static Character_UnicodeScript* forName(String* scriptName);
    static Character_UnicodeScript* of(int32_t codePoint);
    static Character_UnicodeScript* valueOf(String* arg0);
    static Character_UnicodeScriptArray* values();

    // Generated
    Character_UnicodeScript(::java::lang::String* name, int ordinal);
protected:
    Character_UnicodeScript(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static Enum* valueOf(Class* enumType, String* name);

private:
    static Character_UnicodeScriptArray*& $VALUES();
    static ::java::util::HashMap*& aliases();
    static ::int32_tArray*& scriptStarts();
    static Character_UnicodeScriptArray*& scripts();
    virtual ::java::lang::Class* getClass0();
};
