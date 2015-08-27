// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLReader.java
#include <org/sbolstandard/core2/SBOLReader_SBOLPair.hpp>

#include <java/net/URI.hpp>

org::sbolstandard::core2::SBOLReader_SBOLPair::SBOLReader_SBOLPair(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLReader_SBOLPair::SBOLReader_SBOLPair(::java::net::URI* left, ::java::net::URI* right) 
    : SBOLReader_SBOLPair(*static_cast< ::default_init_tag* >(0))
{
    ctor(left,right);
}

void org::sbolstandard::core2::SBOLReader_SBOLPair::ctor(::java::net::URI* left, ::java::net::URI* right)
{
    super::ctor();
    this->left = left;
    this->right = right;
}

java::net::URI* org::sbolstandard::core2::SBOLReader_SBOLPair::getLeft()
{
    return left;
}

void org::sbolstandard::core2::SBOLReader_SBOLPair::setLeft(::java::net::URI* left)
{
    this->left = left;
}

java::net::URI* org::sbolstandard::core2::SBOLReader_SBOLPair::getRight()
{
    return right;
}

void org::sbolstandard::core2::SBOLReader_SBOLPair::setRight(::java::net::URI* right)
{
    this->right = right;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLReader_SBOLPair::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLReader.SBOLPair", 42);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SBOLReader_SBOLPair::getClass0()
{
    return class_();
}

