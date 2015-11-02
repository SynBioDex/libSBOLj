#include <org/sbolstandard/core2/SBOLValidate.hpp>

extern void init_jvm();
extern java::lang::StringArray* make_args(int args, char** argv);

int main(int argc, char** argv)
{
    init_jvm();
    
    ::org::sbolstandard::core2::SBOLValidate::main(make_args(argc, argv));
    
    return 0;
}
