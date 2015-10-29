// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SystemsBiologyOntology.java

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SystemsBiologyOntology
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::String* URI_PREFIX_;
    static ::java::net::URI* NAMESPACE_;

public:
    static ::java::net::URI* type(::java::lang::String* localName);

private:
    static ::java::net::URI* CONTINUOUS_FRAMEWORK_;
    static ::java::net::URI* NON_SPATIAL_CONTINUOUS_FRAMEWORK_;
    static ::java::net::URI* SPATIAL_CONTINUOUS_FRAMEWORK_;
    static ::java::net::URI* DISCRETE_FRAMEWORK_;
    static ::java::net::URI* NON_SPATIAL_DISCRETE_FRAMEWORK_;
    static ::java::net::URI* SPATIAL_DISCRETE_FRAMEWORK_;
    static ::java::net::URI* FLUX_BALANCE_FRAMEWORK_;
    static ::java::net::URI* LOGICAL_FRAMEWORK_;
    static ::java::net::URI* BOOLEAN_LOGICAL_FRAMEWORK_;
    static ::java::net::URI* BIOLOGICAL_ACTIVITY_;
    static ::java::net::URI* PROCESS_;
    static ::java::net::URI* BIOCHEMICAL_OR_TRANSPORT_REACTION_;
    static ::java::net::URI* BIOCHEMICAL_REACTION_;
    static ::java::net::URI* ACID_BASE_REACTION_;
    static ::java::net::URI* DEPROTONATION_;
    static ::java::net::URI* PROTONATION_;
    static ::java::net::URI* CONFORMATIONAL_TRANSITION_;
    static ::java::net::URI* CONVERSION_;
    static ::java::net::URI* ADDITION_OF_A_CHEMICAL_GROUP_;
    static ::java::net::URI* ACETYLATION_;
    static ::java::net::URI* GLYCOSYLATION_;
    static ::java::net::URI* HYDROXYLATION_;
    static ::java::net::URI* METHYLATION_;
    static ::java::net::URI* MYRISTOYLATION_;
    static ::java::net::URI* PALMITOYLATION_;
    static ::java::net::URI* PHOSPHORYLATION_;
    static ::java::net::URI* PRENYLATION_;
    static ::java::net::URI* FARNESYLATION_;
    static ::java::net::URI* GERANYLGERANYLATION_;
    static ::java::net::URI* SULFATION_;
    static ::java::net::URI* UBIQUITINATION_;
    static ::java::net::URI* CLEAVAGE_;
    static ::java::net::URI* REMOVAL_OF_A_CHEMICAL_GROUP_;
    static ::java::net::URI* DEAMINATION_;
    static ::java::net::URI* DECARBONYLATION_;
    static ::java::net::URI* DECARBOXYLATION_;
    static ::java::net::URI* DEPHOSPHORYLATION_;
    static ::java::net::URI* TRANSFER_OF_A_CHEMICAL_GROUP_;
    static ::java::net::URI* TRANSAMINATION_;
    static ::java::net::URI* DEGRADATION_;
    static ::java::net::URI* DISSOCIATION_;
    static ::java::net::URI* HYDROLYSIS_;
    static ::java::net::URI* IONISATION_;
    static ::java::net::URI* ISOMERISATION_;
    static ::java::net::URI* NON_COVALENT_BINDING_;
    static ::java::net::URI* REDOX_REACTION_;
    static ::java::net::URI* OXIDATION_;
    static ::java::net::URI* REDUCTION_;
    static ::java::net::URI* TRANSPORT_REACTION_;
    static ::java::net::URI* TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION_;
    static ::java::net::URI* TRANSCELLULAR_MEMBRANE_INFLUX_REACTION_;
    static ::java::net::URI* BIOLOGICAL_EFFECT_OF_A_PERTURBATION_;
    static ::java::net::URI* COMPOSITE_BIOCHEMICAL_PROCESS_;
    static ::java::net::URI* DNA_REPLICATION_;
    static ::java::net::URI* GENETIC_PRODUCTION_;
    static ::java::net::URI* TRANSCRIPTION_;
    static ::java::net::URI* TRANSLATION_;
    static ::java::net::URI* ENCAPSULATING_PROCESS_;
    static ::java::net::URI* MOLECULAR_OR_GENETIC_INTERACTION_;
    static ::java::net::URI* GENETIC_INTERACTION_;
    static ::java::net::URI* GENETIC_ENHANCEMENT_;
    static ::java::net::URI* GENETIC_SUPPRESSION_;
    static ::java::net::URI* SYNTHETIC_LETHALITY_;
    static ::java::net::URI* MOLECULAR_INTERACTION_;
    static ::java::net::URI* PROTEIN_COMPLEX_FORMATION_;
    static ::java::net::URI* OMITTED_PROCESS_;
    static ::java::net::URI* PHENOTYPE_;
    static ::java::net::URI* STATE_VARIABLE_ASSIGNMENT_;
    static ::java::net::URI* PETRI_NET_TRANSITION_;
    static ::java::net::URI* UNCERTAIN_PROCESS_;
    static ::java::net::URI* RELATIONSHIP_;
    static ::java::net::URI* CONTROL_;
    static ::java::net::URI* ALLOSTERIC_CONTROL_;
    static ::java::net::URI* CONSUMPTION_;
    static ::java::net::URI* INHIBITION_;
    static ::java::net::URI* ABSOLUTE_INHIBITION_;
    static ::java::net::URI* PRODUCTION_;
    static ::java::net::URI* STIMULATION_;
    static ::java::net::URI* ABSOLUTE_STIMULATION_;
    static ::java::net::URI* CATALYSIS_;
    static ::java::net::URI* NECESSARY_STIMULATION_;
    static ::java::net::URI* EQUIVALENCE_;
    static ::java::net::URI* LOGICAL_COMBINATION_;
    static ::java::net::URI* AND_;
    static ::java::net::URI* NOT_;
    static ::java::net::URI* OR_;
    static ::java::net::URI* XOR_;
    static ::java::net::URI* LOGICAL_RELATIONSHIP_;
    static ::java::net::URI* POSITIONAL_RELATIONSHIP_;
    static ::java::net::URI* CIS_;
    static ::java::net::URI* CONTAINMENT_;
    static ::java::net::URI* TRANS_;
    static ::java::net::URI* FUNCTIONAL_COMPARTMENT_;
    static ::java::net::URI* MODIFIER_;
    static ::java::net::URI* DUAL_ACTIVITY_MODIFIER_;
    static ::java::net::URI* INHIBITOR_;
    static ::java::net::URI* COMPETITIVE_INHIBITOR_;
    static ::java::net::URI* NON_COMPETITIVE_INHIBITOR_;
    static ::java::net::URI* SILENCER_;
    static ::java::net::URI* MODIFIER_OF_UNKNOWN_ACTIVITY_;
    static ::java::net::URI* STIMULATOR_;
    static ::java::net::URI* CATALYST_;
    static ::java::net::URI* ENZYMATIC_CATALYST_;
    static ::java::net::URI* ESSENTIAL_ACTIVATOR_;
    static ::java::net::URI* BINDING_ACTIVATOR_;
    static ::java::net::URI* CATALYTIC_ACTIVATOR_;
    static ::java::net::URI* SPECIFIC_ACTIVATOR_;
    static ::java::net::URI* NON_ESSENTIAL_ACTIVATOR_;
    static ::java::net::URI* POTENTIATOR_;
    static ::java::net::URI* NEUTRAL_PARTICIPANT_;
    static ::java::net::URI* PRODUCT_;
    static ::java::net::URI* SIDE_PRODUCT_;
    static ::java::net::URI* PROMOTER_;
    static ::java::net::URI* REACTANT_;
    static ::java::net::URI* INTERACTOR_;
    static ::java::net::URI* SUBSTRATE_;
    static ::java::net::URI* SIDE_SUBSTRATE_;

    // Generated

public:
    SystemsBiologyOntology();
protected:
    SystemsBiologyOntology(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

private:
    static ::java::lang::String*& URI_PREFIX();

public:
    static ::java::net::URI*& NAMESPACE();
    static ::java::net::URI*& CONTINUOUS_FRAMEWORK();
    static ::java::net::URI*& NON_SPATIAL_CONTINUOUS_FRAMEWORK();
    static ::java::net::URI*& SPATIAL_CONTINUOUS_FRAMEWORK();
    static ::java::net::URI*& DISCRETE_FRAMEWORK();
    static ::java::net::URI*& NON_SPATIAL_DISCRETE_FRAMEWORK();
    static ::java::net::URI*& SPATIAL_DISCRETE_FRAMEWORK();
    static ::java::net::URI*& FLUX_BALANCE_FRAMEWORK();
    static ::java::net::URI*& LOGICAL_FRAMEWORK();
    static ::java::net::URI*& BOOLEAN_LOGICAL_FRAMEWORK();
    static ::java::net::URI*& BIOLOGICAL_ACTIVITY();
    static ::java::net::URI*& PROCESS();
    static ::java::net::URI*& BIOCHEMICAL_OR_TRANSPORT_REACTION();
    static ::java::net::URI*& BIOCHEMICAL_REACTION();
    static ::java::net::URI*& ACID_BASE_REACTION();
    static ::java::net::URI*& DEPROTONATION();
    static ::java::net::URI*& PROTONATION();
    static ::java::net::URI*& CONFORMATIONAL_TRANSITION();
    static ::java::net::URI*& CONVERSION();
    static ::java::net::URI*& ADDITION_OF_A_CHEMICAL_GROUP();
    static ::java::net::URI*& ACETYLATION();
    static ::java::net::URI*& GLYCOSYLATION();
    static ::java::net::URI*& HYDROXYLATION();
    static ::java::net::URI*& METHYLATION();
    static ::java::net::URI*& MYRISTOYLATION();
    static ::java::net::URI*& PALMITOYLATION();
    static ::java::net::URI*& PHOSPHORYLATION();
    static ::java::net::URI*& PRENYLATION();
    static ::java::net::URI*& FARNESYLATION();
    static ::java::net::URI*& GERANYLGERANYLATION();
    static ::java::net::URI*& SULFATION();
    static ::java::net::URI*& UBIQUITINATION();
    static ::java::net::URI*& CLEAVAGE();
    static ::java::net::URI*& REMOVAL_OF_A_CHEMICAL_GROUP();
    static ::java::net::URI*& DEAMINATION();
    static ::java::net::URI*& DECARBONYLATION();
    static ::java::net::URI*& DECARBOXYLATION();
    static ::java::net::URI*& DEPHOSPHORYLATION();
    static ::java::net::URI*& TRANSFER_OF_A_CHEMICAL_GROUP();
    static ::java::net::URI*& TRANSAMINATION();
    static ::java::net::URI*& DEGRADATION();
    static ::java::net::URI*& DISSOCIATION();
    static ::java::net::URI*& HYDROLYSIS();
    static ::java::net::URI*& IONISATION();
    static ::java::net::URI*& ISOMERISATION();
    static ::java::net::URI*& NON_COVALENT_BINDING();
    static ::java::net::URI*& REDOX_REACTION();
    static ::java::net::URI*& OXIDATION();
    static ::java::net::URI*& REDUCTION();
    static ::java::net::URI*& TRANSPORT_REACTION();
    static ::java::net::URI*& TRANSCELLULAR_MEMBRANE_EFFLUX_REACTION();
    static ::java::net::URI*& TRANSCELLULAR_MEMBRANE_INFLUX_REACTION();
    static ::java::net::URI*& BIOLOGICAL_EFFECT_OF_A_PERTURBATION();
    static ::java::net::URI*& COMPOSITE_BIOCHEMICAL_PROCESS();
    static ::java::net::URI*& DNA_REPLICATION();
    static ::java::net::URI*& GENETIC_PRODUCTION();
    static ::java::net::URI*& TRANSCRIPTION();
    static ::java::net::URI*& TRANSLATION();
    static ::java::net::URI*& ENCAPSULATING_PROCESS();
    static ::java::net::URI*& MOLECULAR_OR_GENETIC_INTERACTION();
    static ::java::net::URI*& GENETIC_INTERACTION();
    static ::java::net::URI*& GENETIC_ENHANCEMENT();
    static ::java::net::URI*& GENETIC_SUPPRESSION();
    static ::java::net::URI*& SYNTHETIC_LETHALITY();
    static ::java::net::URI*& MOLECULAR_INTERACTION();
    static ::java::net::URI*& PROTEIN_COMPLEX_FORMATION();
    static ::java::net::URI*& OMITTED_PROCESS();
    static ::java::net::URI*& PHENOTYPE();
    static ::java::net::URI*& STATE_VARIABLE_ASSIGNMENT();
    static ::java::net::URI*& PETRI_NET_TRANSITION();
    static ::java::net::URI*& UNCERTAIN_PROCESS();
    static ::java::net::URI*& RELATIONSHIP();
    static ::java::net::URI*& CONTROL();
    static ::java::net::URI*& ALLOSTERIC_CONTROL();
    static ::java::net::URI*& CONSUMPTION();
    static ::java::net::URI*& INHIBITION();
    static ::java::net::URI*& ABSOLUTE_INHIBITION();
    static ::java::net::URI*& PRODUCTION();
    static ::java::net::URI*& STIMULATION();
    static ::java::net::URI*& ABSOLUTE_STIMULATION();
    static ::java::net::URI*& CATALYSIS();
    static ::java::net::URI*& NECESSARY_STIMULATION();
    static ::java::net::URI*& EQUIVALENCE();
    static ::java::net::URI*& LOGICAL_COMBINATION();
    static ::java::net::URI*& AND();
    static ::java::net::URI*& NOT();
    static ::java::net::URI*& OR();
    static ::java::net::URI*& XOR();
    static ::java::net::URI*& LOGICAL_RELATIONSHIP();
    static ::java::net::URI*& POSITIONAL_RELATIONSHIP();
    static ::java::net::URI*& CIS();
    static ::java::net::URI*& CONTAINMENT();
    static ::java::net::URI*& TRANS();
    static ::java::net::URI*& FUNCTIONAL_COMPARTMENT();
    static ::java::net::URI*& MODIFIER();
    static ::java::net::URI*& DUAL_ACTIVITY_MODIFIER();
    static ::java::net::URI*& INHIBITOR();
    static ::java::net::URI*& COMPETITIVE_INHIBITOR();
    static ::java::net::URI*& NON_COMPETITIVE_INHIBITOR();
    static ::java::net::URI*& SILENCER();
    static ::java::net::URI*& MODIFIER_OF_UNKNOWN_ACTIVITY();
    static ::java::net::URI*& STIMULATOR();
    static ::java::net::URI*& CATALYST();
    static ::java::net::URI*& ENZYMATIC_CATALYST();
    static ::java::net::URI*& ESSENTIAL_ACTIVATOR();
    static ::java::net::URI*& BINDING_ACTIVATOR();
    static ::java::net::URI*& CATALYTIC_ACTIVATOR();
    static ::java::net::URI*& SPECIFIC_ACTIVATOR();
    static ::java::net::URI*& NON_ESSENTIAL_ACTIVATOR();
    static ::java::net::URI*& POTENTIATOR();
    static ::java::net::URI*& NEUTRAL_PARTICIPANT();
    static ::java::net::URI*& PRODUCT();
    static ::java::net::URI*& SIDE_PRODUCT();
    static ::java::net::URI*& PROMOTER();
    static ::java::net::URI*& REACTANT();
    static ::java::net::URI*& INTERACTOR();
    static ::java::net::URI*& SUBSTRATE();
    static ::java::net::URI*& SIDE_SUBSTRATE();

private:
    virtual ::java::lang::Class* getClass0();
};
