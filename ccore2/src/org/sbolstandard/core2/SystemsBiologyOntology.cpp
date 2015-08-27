// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SystemsBiologyOntology.java
#include <org/sbolstandard/core2/SystemsBiologyOntology.hpp>

#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>

org::sbolstandard::core2::SystemsBiologyOntology::SystemsBiologyOntology(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SystemsBiologyOntology::SystemsBiologyOntology()
    : SystemsBiologyOntology(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::String*& org::sbolstandard::core2::SystemsBiologyOntology::URI_PREFIX()
{
    clinit();
    return URI_PREFIX_;
}
java::lang::String* org::sbolstandard::core2::SystemsBiologyOntology::URI_PREFIX_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NAMESPACE()
{
    clinit();
    return NAMESPACE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NAMESPACE_;

java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::type(::java::lang::String* localName)
{
    clinit();
    return ::java::net::URI::create(::java::lang::StringBuilder().append(URI_PREFIX_)->append(localName)->toString());
}

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONTINUOUS_FRAMEWORK()
{
    clinit();
    return CONTINUOUS_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONTINUOUS_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NON_SPATIAL_CONTINUOUS_FRAMEWORK()
{
    clinit();
    return NON_SPATIAL_CONTINUOUS_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NON_SPATIAL_CONTINUOUS_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SPATIAL_CONTINUOUS_FRAMEWORK()
{
    clinit();
    return SPATIAL_CONTINUOUS_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SPATIAL_CONTINUOUS_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DISCRETE_FRAMEWORK()
{
    clinit();
    return DISCRETE_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DISCRETE_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NON_SPATIAL_DISCRETE_FRAMEWORK()
{
    clinit();
    return NON_SPATIAL_DISCRETE_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NON_SPATIAL_DISCRETE_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SPATIAL_DISCRETE_FRAMEWORK()
{
    clinit();
    return SPATIAL_DISCRETE_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SPATIAL_DISCRETE_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::FLUX_BALANCE_FRAMEWORK()
{
    clinit();
    return FLUX_BALANCE_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::FLUX_BALANCE_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_FRAMEWORK()
{
    clinit();
    return LOGICAL_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BOOLEAN_LOGICAL_FRAMEWORK()
{
    clinit();
    return BOOLEAN_LOGICAL_FRAMEWORK_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BOOLEAN_LOGICAL_FRAMEWORK_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BIOLOGICAL_ACTIVITY()
{
    clinit();
    return BIOLOGICAL_ACTIVITY_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BIOLOGICAL_ACTIVITY_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PROCESS()
{
    clinit();
    return PROCESS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PROCESS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BIOCHEMICAL_OR_TRANSPORT_REACTION()
{
    clinit();
    return BIOCHEMICAL_OR_TRANSPORT_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BIOCHEMICAL_OR_TRANSPORT_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BIOCHEMICAL_REACTION()
{
    clinit();
    return BIOCHEMICAL_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BIOCHEMICAL_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ACID_BASE_REACTION()
{
    clinit();
    return ACID_BASE_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ACID_BASE_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DEPROTONATION()
{
    clinit();
    return DEPROTONATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DEPROTONATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PROTONATION()
{
    clinit();
    return PROTONATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PROTONATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONFORMATIONAL_TRANSITION()
{
    clinit();
    return CONFORMATIONAL_TRANSITION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONFORMATIONAL_TRANSITION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONVERSION()
{
    clinit();
    return CONVERSION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONVERSION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ADDITION_OF_A_CHEMICAL_GROUP()
{
    clinit();
    return ADDITION_OF_A_CHEMICAL_GROUP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ADDITION_OF_A_CHEMICAL_GROUP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ACETYLATION()
{
    clinit();
    return ACETYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ACETYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GLYCOSYLATION()
{
    clinit();
    return GLYCOSYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GLYCOSYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::HYDROXYLATION()
{
    clinit();
    return HYDROXYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::HYDROXYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::METHYLATION()
{
    clinit();
    return METHYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::METHYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::MYRISTOYLATION()
{
    clinit();
    return MYRISTOYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::MYRISTOYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PALMITOYLATION()
{
    clinit();
    return PALMITOYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PALMITOYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PHOSPHORYLATION()
{
    clinit();
    return PHOSPHORYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PHOSPHORYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PRENYLATION()
{
    clinit();
    return PRENYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PRENYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::FARNESYLATION()
{
    clinit();
    return FARNESYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::FARNESYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GERANYLGERANYLATION()
{
    clinit();
    return GERANYLGERANYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GERANYLGERANYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SULFATION()
{
    clinit();
    return SULFATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SULFATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::UBIQUITINATION()
{
    clinit();
    return UBIQUITINATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::UBIQUITINATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CLEAVAGE()
{
    clinit();
    return CLEAVAGE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CLEAVAGE_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::REMOVAL_OF_A_CHEMICAL_GROUP()
{
    clinit();
    return REMOVAL_OF_A_CHEMICAL_GROUP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::REMOVAL_OF_A_CHEMICAL_GROUP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DEAMINATION()
{
    clinit();
    return DEAMINATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DEAMINATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DECARBONYLATION()
{
    clinit();
    return DECARBONYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DECARBONYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DECARBOXYLATION()
{
    clinit();
    return DECARBOXYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DECARBOXYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DEPHOSPHORYLATION()
{
    clinit();
    return DEPHOSPHORYLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DEPHOSPHORYLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSFER_OF_A_CHEMICAL_GROUP()
{
    clinit();
    return TRANSFER_OF_A_CHEMICAL_GROUP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSFER_OF_A_CHEMICAL_GROUP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSAMINATION()
{
    clinit();
    return TRANSAMINATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSAMINATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DEGRADATION()
{
    clinit();
    return DEGRADATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DEGRADATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DISSOCIATION()
{
    clinit();
    return DISSOCIATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DISSOCIATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::HYDROLYSIS()
{
    clinit();
    return HYDROLYSIS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::HYDROLYSIS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::IONISATION()
{
    clinit();
    return IONISATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::IONISATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ISOMERISATION()
{
    clinit();
    return ISOMERISATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ISOMERISATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NON_COVALENT_BINDING()
{
    clinit();
    return NON_COVALENT_BINDING_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NON_COVALENT_BINDING_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::REDOX_REACTION()
{
    clinit();
    return REDOX_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::REDOX_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::OXIDATION()
{
    clinit();
    return OXIDATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::OXIDATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::REDUCTION()
{
    clinit();
    return REDUCTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::REDUCTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSPORT_REACTION()
{
    clinit();
    return TRANSPORT_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSPORT_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION()
{
    clinit();
    return TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSCELLULAR_MEMBRANE_INFLUX_REACTION()
{
    clinit();
    return TRANSCELLULAR_MEMBRANE_INFLUX_REACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSCELLULAR_MEMBRANE_INFLUX_REACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BIOLOGICAL_EFFECT_OF_A_PERTURBATION()
{
    clinit();
    return BIOLOGICAL_EFFECT_OF_A_PERTURBATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BIOLOGICAL_EFFECT_OF_A_PERTURBATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::COMPOSITE_BIOCHEMICAL_PROCESS()
{
    clinit();
    return COMPOSITE_BIOCHEMICAL_PROCESS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::COMPOSITE_BIOCHEMICAL_PROCESS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DNA_REPLICATION()
{
    clinit();
    return DNA_REPLICATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DNA_REPLICATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_PRODUCTION()
{
    clinit();
    return GENETIC_PRODUCTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_PRODUCTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSCRIPTION()
{
    clinit();
    return TRANSCRIPTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSCRIPTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANSLATION()
{
    clinit();
    return TRANSLATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANSLATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ENCAPSULATING_PROCESS()
{
    clinit();
    return ENCAPSULATING_PROCESS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ENCAPSULATING_PROCESS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::MOLECULAR_OR_GENETIC_INTERACTION()
{
    clinit();
    return MOLECULAR_OR_GENETIC_INTERACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::MOLECULAR_OR_GENETIC_INTERACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_INTERACTION()
{
    clinit();
    return GENETIC_INTERACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_INTERACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_ENHANCEMENT()
{
    clinit();
    return GENETIC_ENHANCEMENT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_ENHANCEMENT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_SUPPRESSION()
{
    clinit();
    return GENETIC_SUPPRESSION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::GENETIC_SUPPRESSION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SYNTHETIC_LETHALITY()
{
    clinit();
    return SYNTHETIC_LETHALITY_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SYNTHETIC_LETHALITY_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::MOLECULAR_INTERACTION()
{
    clinit();
    return MOLECULAR_INTERACTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::MOLECULAR_INTERACTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PROTEIN_COMPLEX_FORMATION()
{
    clinit();
    return PROTEIN_COMPLEX_FORMATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PROTEIN_COMPLEX_FORMATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::OMITTED_PROCESS()
{
    clinit();
    return OMITTED_PROCESS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::OMITTED_PROCESS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PHENOTYPE()
{
    clinit();
    return PHENOTYPE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PHENOTYPE_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::STATE_VARIABLE_ASSIGNMENT()
{
    clinit();
    return STATE_VARIABLE_ASSIGNMENT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::STATE_VARIABLE_ASSIGNMENT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PETRI_NET_TRANSITION()
{
    clinit();
    return PETRI_NET_TRANSITION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PETRI_NET_TRANSITION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::UNCERTAIN_PROCESS()
{
    clinit();
    return UNCERTAIN_PROCESS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::UNCERTAIN_PROCESS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::RELATIONSHIP()
{
    clinit();
    return RELATIONSHIP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::RELATIONSHIP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONTROL()
{
    clinit();
    return CONTROL_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONTROL_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ALLOSTERIC_CONTROL()
{
    clinit();
    return ALLOSTERIC_CONTROL_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ALLOSTERIC_CONTROL_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONSUMPTION()
{
    clinit();
    return CONSUMPTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONSUMPTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::INHIBITION()
{
    clinit();
    return INHIBITION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::INHIBITION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ABSOLUTE_INHIBITION()
{
    clinit();
    return ABSOLUTE_INHIBITION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ABSOLUTE_INHIBITION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PRODUCTION()
{
    clinit();
    return PRODUCTION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PRODUCTION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::STIMULATION()
{
    clinit();
    return STIMULATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::STIMULATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ABSOLUTE_STIMULATION()
{
    clinit();
    return ABSOLUTE_STIMULATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ABSOLUTE_STIMULATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CATALYSIS()
{
    clinit();
    return CATALYSIS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CATALYSIS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NECESSARY_STIMULATION()
{
    clinit();
    return NECESSARY_STIMULATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NECESSARY_STIMULATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::EQUIVALENCE()
{
    clinit();
    return EQUIVALENCE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::EQUIVALENCE_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_COMBINATION()
{
    clinit();
    return LOGICAL_COMBINATION_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_COMBINATION_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::AND()
{
    clinit();
    return AND_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::AND_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NOT()
{
    clinit();
    return NOT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NOT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::OR()
{
    clinit();
    return OR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::OR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::XOR()
{
    clinit();
    return XOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::XOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_RELATIONSHIP()
{
    clinit();
    return LOGICAL_RELATIONSHIP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::LOGICAL_RELATIONSHIP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::POSITIONAL_RELATIONSHIP()
{
    clinit();
    return POSITIONAL_RELATIONSHIP_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::POSITIONAL_RELATIONSHIP_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CIS()
{
    clinit();
    return CIS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CIS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CONTAINMENT()
{
    clinit();
    return CONTAINMENT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CONTAINMENT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::TRANS()
{
    clinit();
    return TRANS_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::TRANS_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::FUNCTIONAL_COMPARTMENT()
{
    clinit();
    return FUNCTIONAL_COMPARTMENT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::FUNCTIONAL_COMPARTMENT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::MODIFIER()
{
    clinit();
    return MODIFIER_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::MODIFIER_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::DUAL_ACTIVITY_MODIFIER()
{
    clinit();
    return DUAL_ACTIVITY_MODIFIER_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::DUAL_ACTIVITY_MODIFIER_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::INHIBITOR()
{
    clinit();
    return INHIBITOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::INHIBITOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::COMPETITIVE_INHIBITOR()
{
    clinit();
    return COMPETITIVE_INHIBITOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::COMPETITIVE_INHIBITOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NON_COMPETITIVE_INHIBITOR()
{
    clinit();
    return NON_COMPETITIVE_INHIBITOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NON_COMPETITIVE_INHIBITOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SILENCER()
{
    clinit();
    return SILENCER_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SILENCER_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::MODIFIER_OF_UNKNOWN_ACTIVITY()
{
    clinit();
    return MODIFIER_OF_UNKNOWN_ACTIVITY_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::MODIFIER_OF_UNKNOWN_ACTIVITY_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::STIMULATOR()
{
    clinit();
    return STIMULATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::STIMULATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CATALYST()
{
    clinit();
    return CATALYST_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CATALYST_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ENZYMATIC_CATALYST()
{
    clinit();
    return ENZYMATIC_CATALYST_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ENZYMATIC_CATALYST_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::ESSENTIAL_ACTIVATOR()
{
    clinit();
    return ESSENTIAL_ACTIVATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::ESSENTIAL_ACTIVATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::BINDING_ACTIVATOR()
{
    clinit();
    return BINDING_ACTIVATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::BINDING_ACTIVATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::CATALYTIC_ACTIVATOR()
{
    clinit();
    return CATALYTIC_ACTIVATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::CATALYTIC_ACTIVATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SPECIFIC_ACTIVATOR()
{
    clinit();
    return SPECIFIC_ACTIVATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SPECIFIC_ACTIVATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NON_ESSENTIAL_ACTIVATOR()
{
    clinit();
    return NON_ESSENTIAL_ACTIVATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NON_ESSENTIAL_ACTIVATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::POTENTIATOR()
{
    clinit();
    return POTENTIATOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::POTENTIATOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::NEUTRAL_PARTICIPANT()
{
    clinit();
    return NEUTRAL_PARTICIPANT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::NEUTRAL_PARTICIPANT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PRODUCT()
{
    clinit();
    return PRODUCT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PRODUCT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SIDE_PRODUCT()
{
    clinit();
    return SIDE_PRODUCT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SIDE_PRODUCT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::PROMOTER()
{
    clinit();
    return PROMOTER_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::PROMOTER_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::REACTANT()
{
    clinit();
    return REACTANT_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::REACTANT_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::INTERACTOR()
{
    clinit();
    return INTERACTOR_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::INTERACTOR_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SUBSTRATE()
{
    clinit();
    return SUBSTRATE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SUBSTRATE_;

java::net::URI*& org::sbolstandard::core2::SystemsBiologyOntology::SIDE_SUBSTRATE()
{
    clinit();
    return SIDE_SUBSTRATE_;
}
java::net::URI* org::sbolstandard::core2::SystemsBiologyOntology::SIDE_SUBSTRATE_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SystemsBiologyOntology::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SystemsBiologyOntology", 45);
    return c;
}

void org::sbolstandard::core2::SystemsBiologyOntology::clinit()
{
struct string_init_ {
    string_init_() {
    URI_PREFIX_ = u"http://identifiers.org/biomodels.sbo/"_j;
    }
};

    static string_init_ string_init_instance;

    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        NAMESPACE_ = ::java::net::URI::create(URI_PREFIX_);
        CONTINUOUS_FRAMEWORK_ = type(u"SBO:0000062"_j);
        NON_SPATIAL_CONTINUOUS_FRAMEWORK_ = type(u"SBO:0000293"_j);
        SPATIAL_CONTINUOUS_FRAMEWORK_ = type(u"SBO:0000292"_j);
        DISCRETE_FRAMEWORK_ = type(u"SBO:0000063"_j);
        NON_SPATIAL_DISCRETE_FRAMEWORK_ = type(u"SBO:0000295"_j);
        SPATIAL_DISCRETE_FRAMEWORK_ = type(u"SBO:0000294"_j);
        FLUX_BALANCE_FRAMEWORK_ = type(u"SBO:0000624"_j);
        LOGICAL_FRAMEWORK_ = type(u"SBO:0000234"_j);
        BOOLEAN_LOGICAL_FRAMEWORK_ = type(u"SBO:0000547"_j);
        BIOLOGICAL_ACTIVITY_ = type(u"SBO:0000412"_j);
        PROCESS_ = type(u"SBO:0000375"_j);
        BIOCHEMICAL_OR_TRANSPORT_REACTION_ = type(u"SBO:0000167"_j);
        BIOCHEMICAL_REACTION_ = type(u"SBO:0000176"_j);
        ACID_BASE_REACTION_ = type(u"SBO:0000208"_j);
        DEPROTONATION_ = type(u"SBO:0000213"_j);
        PROTONATION_ = type(u"SBO:0000212"_j);
        CONFORMATIONAL_TRANSITION_ = type(u"SBO:0000181"_j);
        CONVERSION_ = type(u"SBO:0000182"_j);
        ADDITION_OF_A_CHEMICAL_GROUP_ = type(u"SBO:0000210"_j);
        ACETYLATION_ = type(u"SBO:0000215"_j);
        GLYCOSYLATION_ = type(u"SBO:0000217"_j);
        HYDROXYLATION_ = type(u"SBO:0000233"_j);
        METHYLATION_ = type(u"SBO:0000214"_j);
        MYRISTOYLATION_ = type(u"SBO:0000219"_j);
        PALMITOYLATION_ = type(u"SBO:0000218"_j);
        PHOSPHORYLATION_ = type(u"SBO:0000216"_j);
        PRENYLATION_ = type(u"SBO:0000221"_j);
        FARNESYLATION_ = type(u"SBO:0000222"_j);
        GERANYLGERANYLATION_ = type(u"SBO:0000223"_j);
        SULFATION_ = type(u"SBO:0000220"_j);
        UBIQUITINATION_ = type(u"SBO:0000224"_j);
        CLEAVAGE_ = type(u"SBO:0000178"_j);
        REMOVAL_OF_A_CHEMICAL_GROUP_ = type(u"SBO:0000211"_j);
        DEAMINATION_ = type(u"SBO:0000401"_j);
        DECARBONYLATION_ = type(u"SBO:0000400"_j);
        DECARBOXYLATION_ = type(u"SBO:0000399"_j);
        DEPHOSPHORYLATION_ = type(u"SBO:0000330"_j);
        TRANSFER_OF_A_CHEMICAL_GROUP_ = type(u"SBO:0000402"_j);
        TRANSAMINATION_ = type(u"SBO:0000403"_j);
        DEGRADATION_ = type(u"SBO:0000179"_j);
        DISSOCIATION_ = type(u"SBO:0000180"_j);
        HYDROLYSIS_ = type(u"SBO:0000376"_j);
        IONISATION_ = type(u"SBO:0000209"_j);
        ISOMERISATION_ = type(u"SBO:0000377"_j);
        NON_COVALENT_BINDING_ = type(u"SBO:0000177"_j);
        REDOX_REACTION_ = type(u"SBO:0000200"_j);
        OXIDATION_ = type(u"SBO:0000201"_j);
        REDUCTION_ = type(u"SBO:0000202"_j);
        TRANSPORT_REACTION_ = type(u"SBO:0000185"_j);
        TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION_ = type(u"SBO:0000588"_j);
        TRANSCELLULAR_MEMBRANE_INFLUX_REACTION_ = type(u"SBO:0000587"_j);
        BIOLOGICAL_EFFECT_OF_A_PERTURBATION_ = type(u"SBO:0000357"_j);
        COMPOSITE_BIOCHEMICAL_PROCESS_ = type(u"SBO:0000205"_j);
        DNA_REPLICATION_ = type(u"SBO:0000204"_j);
        GENETIC_PRODUCTION_ = type(u"SBO:0000589"_j);
        TRANSCRIPTION_ = type(u"SBO:0000183"_j);
        TRANSLATION_ = type(u"SBO:0000184"_j);
        ENCAPSULATING_PROCESS_ = type(u"SBO:0000395"_j);
        MOLECULAR_OR_GENETIC_INTERACTION_ = type(u"SBO:0000342"_j);
        GENETIC_INTERACTION_ = type(u"SBO:0000343"_j);
        GENETIC_ENHANCEMENT_ = type(u"SBO:0000501"_j);
        GENETIC_SUPPRESSION_ = type(u"SBO:0000500"_j);
        SYNTHETIC_LETHALITY_ = type(u"SBO:0000502"_j);
        MOLECULAR_INTERACTION_ = type(u"SBO:0000344"_j);
        PROTEIN_COMPLEX_FORMATION_ = type(u"SBO:0000526"_j);
        OMITTED_PROCESS_ = type(u"SBO:0000397"_j);
        PHENOTYPE_ = type(u"SBO:0000358"_j);
        STATE_VARIABLE_ASSIGNMENT_ = type(u"SBO:0000464"_j);
        PETRI_NET_TRANSITION_ = type(u"SBO:0000591"_j);
        UNCERTAIN_PROCESS_ = type(u"SBO:0000396"_j);
        RELATIONSHIP_ = type(u"SBO:0000374"_j);
        CONTROL_ = type(u"SBO:0000168"_j);
        ALLOSTERIC_CONTROL_ = type(u"SBO:0000239"_j);
        CONSUMPTION_ = type(u"SBO:0000394"_j);
        INHIBITION_ = type(u"SBO:0000169"_j);
        ABSOLUTE_INHIBITION_ = type(u"SBO:0000407"_j);
        PRODUCTION_ = type(u"SBO:0000393"_j);
        STIMULATION_ = type(u"SBO:0000170"_j);
        ABSOLUTE_STIMULATION_ = type(u"SBO:0000411"_j);
        CATALYSIS_ = type(u"SBO:0000172"_j);
        NECESSARY_STIMULATION_ = type(u"SBO:0000171"_j);
        EQUIVALENCE_ = type(u"SBO:0000392"_j);
        LOGICAL_COMBINATION_ = type(u"SBO:0000237"_j);
        AND_ = type(u"SBO:0000173"_j);
        NOT_ = type(u"SBO:0000238"_j);
        OR_ = type(u"SBO:0000174"_j);
        XOR_ = type(u"SBO:0000175"_j);
        LOGICAL_RELATIONSHIP_ = type(u"SBO:0000398"_j);
        POSITIONAL_RELATIONSHIP_ = type(u"SBO:0000413"_j);
        CIS_ = type(u"SBO:0000414"_j);
        CONTAINMENT_ = type(u"SBO:0000469"_j);
        TRANS_ = type(u"SBO:0000415"_j);
        FUNCTIONAL_COMPARTMENT_ = type(u"SBO:0000289"_j);
        MODIFIER_ = type(u"SBO:0000019"_j);
        DUAL_ACTIVITY_MODIFIER_ = type(u"SBO:0000595"_j);
        INHIBITOR_ = type(u"SBO:0000020"_j);
        COMPETITIVE_INHIBITOR_ = type(u"SBO:0000206"_j);
        NON_COMPETITIVE_INHIBITOR_ = type(u"SBO:0000207"_j);
        SILENCER_ = type(u"SBO:0000597"_j);
        MODIFIER_OF_UNKNOWN_ACTIVITY_ = type(u"SBO:0000596"_j);
        STIMULATOR_ = type(u"SBO:0000459"_j);
        CATALYST_ = type(u"SBO:0000013"_j);
        ENZYMATIC_CATALYST_ = type(u"SBO:0000460"_j);
        ESSENTIAL_ACTIVATOR_ = type(u"SBO:0000461"_j);
        BINDING_ACTIVATOR_ = type(u"SBO:0000535"_j);
        CATALYTIC_ACTIVATOR_ = type(u"SBO:0000534"_j);
        SPECIFIC_ACTIVATOR_ = type(u"SBO:0000533"_j);
        NON_ESSENTIAL_ACTIVATOR_ = type(u"SBO:0000462"_j);
        POTENTIATOR_ = type(u"SBO:0000021"_j);
        NEUTRAL_PARTICIPANT_ = type(u"SBO:0000594"_j);
        PRODUCT_ = type(u"SBO:0000011"_j);
        SIDE_PRODUCT_ = type(u"SBO:0000603"_j);
        PROMOTER_ = type(u"SBO:0000598"_j);
        REACTANT_ = type(u"SBO:0000010"_j);
        INTERACTOR_ = type(u"SBO:0000336"_j);
        SUBSTRATE_ = type(u"SBO:0000015"_j);
        SIDE_SUBSTRATE_ = type(u"SBO:0000604"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::SystemsBiologyOntology::getClass0()
{
    return class_();
}

